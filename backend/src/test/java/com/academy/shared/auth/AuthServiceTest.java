package com.academy.shared.auth;

import com.academy.login.service.LoginService;
import com.academy.login.service.MemberVO;
import com.academy.shared.auth.dto.LoginRequest;
import com.academy.shared.auth.dto.TokenResponse;
import com.academy.shared.security.Audience;
import com.academy.shared.security.InMemoryRefreshTokenStore;
import com.academy.shared.security.JwtTokenProvider;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * AuthService — 로그인·리프레시·로그아웃 비즈니스 검증.
 * Redis/DB 없이 in-memory store + Mockito stub 으로 독립 검증.
 */
class AuthServiceTest {

    private AuthenticationManager authManager;
    private LoginService loginService;
    private JwtTokenProvider tokenProvider;
    private InMemoryRefreshTokenStore refreshStore;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authManager = mock(AuthenticationManager.class);
        loginService = mock(LoginService.class);
        tokenProvider = new JwtTokenProvider(
            "unit-test-secret-at-least-32-bytes-please-ok",
            Duration.ofMinutes(30),
            Duration.ofDays(14)
        );
        refreshStore = new InMemoryRefreshTokenStore();
        authService = new AuthService(authManager, loginService, tokenProvider, refreshStore);
    }

    @Test
    void admin_login_success_issues_tokens_and_stores_refresh() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            "admin", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        when(authManager.authenticate(any())).thenReturn(auth);

        TokenResponse token = authService.login(new LoginRequest("admin", "pw", "admin"));

        assertThat(token.accessToken()).isNotBlank();
        assertThat(token.refreshToken()).isNotBlank();
        assertThat(token.role()).isEqualTo("ADMIN");
        assertThat(token.audience()).isEqualTo("admin");

        JwtTokenProvider.ParsedToken refreshParsed = tokenProvider.parse(token.refreshToken());
        assertThat(refreshStore.exists(Audience.ADMIN, "admin", refreshParsed.jti())).isTrue();
    }

    @Test
    void admin_login_bad_password_fails() {
        when(authManager.authenticate(any())).thenThrow(new BadCredentialsException("bad"));

        assertThatThrownBy(() ->
            authService.login(new LoginRequest("admin", "wrong", "admin"))
        ).isInstanceOf(AuthService.AuthFailedException.class);
    }

    @Test
    void user_login_success_uses_login_service() {
        JSONObject info = new JSONObject();
        info.put("userId", "u-10");
        when(loginService.getUser(any(MemberVO.class))).thenReturn(info);

        TokenResponse token = authService.login(new LoginRequest("u-10", "pw", "user"));

        assertThat(token.role()).isEqualTo("USER");
        assertThat(token.audience()).isEqualTo("user");

        JwtTokenProvider.ParsedToken refreshParsed = tokenProvider.parse(token.refreshToken());
        assertThat(refreshStore.exists(Audience.USER, "u-10", refreshParsed.jti())).isTrue();
    }

    @Test
    void user_login_unknown_user_fails() {
        when(loginService.getUser(any(MemberVO.class))).thenReturn(null);

        assertThatThrownBy(() ->
            authService.login(new LoginRequest("nope", "pw", "user"))
        ).isInstanceOf(AuthService.AuthFailedException.class);
    }

    @Test
    void refresh_with_valid_token_returns_new_access() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            "admin", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        when(authManager.authenticate(any())).thenReturn(auth);
        TokenResponse issued = authService.login(new LoginRequest("admin", "pw", "admin"));

        TokenResponse refreshed = authService.refresh(issued.refreshToken());

        assertThat(refreshed.accessToken()).isNotEqualTo(issued.accessToken());
        assertThat(refreshed.role()).isEqualTo("ADMIN");
    }

    @Test
    void refresh_with_access_token_rejected() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            "admin", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        when(authManager.authenticate(any())).thenReturn(auth);
        TokenResponse issued = authService.login(new LoginRequest("admin", "pw", "admin"));

        assertThatThrownBy(() -> authService.refresh(issued.accessToken()))
            .isInstanceOf(AuthService.AuthFailedException.class)
            .hasMessageContaining("refresh 토큰이 아닙니다");
    }

    @Test
    void logout_deletes_refresh_from_store() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            "admin", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        when(authManager.authenticate(any())).thenReturn(auth);
        TokenResponse issued = authService.login(new LoginRequest("admin", "pw", "admin"));

        authService.logout(issued.refreshToken());

        JwtTokenProvider.ParsedToken parsed = tokenProvider.parse(issued.refreshToken());
        assertThat(refreshStore.exists(Audience.ADMIN, "admin", parsed.jti())).isFalse();
    }

    @Test
    void refresh_after_logout_fails() {
        Authentication auth = new UsernamePasswordAuthenticationToken(
            "admin", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        when(authManager.authenticate(any())).thenReturn(auth);
        TokenResponse issued = authService.login(new LoginRequest("admin", "pw", "admin"));
        authService.logout(issued.refreshToken());

        assertThatThrownBy(() -> authService.refresh(issued.refreshToken()))
            .isInstanceOf(AuthService.AuthFailedException.class)
            .hasMessageContaining("폐기");
    }
}
