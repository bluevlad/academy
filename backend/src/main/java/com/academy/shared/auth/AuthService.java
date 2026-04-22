package com.academy.shared.auth;

import com.academy.login.service.LoginService;
import com.academy.login.service.MemberVO;
import com.academy.shared.auth.dto.LoginRequest;
import com.academy.shared.auth.dto.TokenResponse;
import com.academy.shared.security.Audience;
import com.academy.shared.security.JwtTokenProvider;
import com.academy.shared.security.RefreshTokenStore;
import com.academy.shared.security.Role;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * 로그인·리프레시·로그아웃 비즈니스 (ADR-002).
 *
 * <p>Sprint 1-1a 에서는:
 * <ul>
 *   <li>관리자 = Spring Security {@link AuthenticationManager} (기존 InMemory admin)</li>
 *   <li>수강생 = {@link LoginService#getUser} (기존 TB_MA_MEMBER 경로)</li>
 * </ul>
 * Sprint 1-1b 에서 관리자 저장소를 {@code id_admin} DB 로 교체 예정.
 */
@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenStore refreshStore;

    public AuthService(
        AuthenticationManager authenticationManager,
        LoginService loginService,
        JwtTokenProvider tokenProvider,
        RefreshTokenStore refreshStore
    ) {
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.tokenProvider = tokenProvider;
        this.refreshStore = refreshStore;
    }

    public TokenResponse login(LoginRequest req) {
        Audience audience = Audience.of(req.audience());
        return switch (audience) {
            case ADMIN -> loginAdmin(req.userId(), req.password());
            case USER -> loginUser(req.userId(), req.password());
        };
    }

    private TokenResponse loginAdmin(String userId, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId, password)
            );
            boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
            if (!isAdmin) {
                throw new AuthFailedException("관리자 권한이 없습니다.");
            }
            return issueTokens(userId, Role.ADMIN, Audience.ADMIN);
        } catch (AuthenticationException e) {
            log.debug("admin 로그인 실패: userId={} reason={}", userId, e.getMessage());
            throw new AuthFailedException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    private TokenResponse loginUser(String userId, String password) {
        MemberVO vo = new MemberVO();
        vo.setUserId(userId);
        vo.setUserPwd(password);
        JSONObject info = loginService.getUser(vo);
        if (info == null || info.isEmpty()) {
            log.debug("user 로그인 실패: userId={}", userId);
            throw new AuthFailedException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return issueTokens(userId, Role.USER, Audience.USER);
    }

    private TokenResponse issueTokens(String userId, Role role, Audience audience) {
        String access = tokenProvider.createAccessToken(userId, role, audience);
        JwtTokenProvider.IssuedRefreshToken refresh = tokenProvider.createRefreshToken(userId, role, audience);
        refreshStore.save(audience, userId, refresh.jti(), refresh.ttl());
        Instant expiresAt = Instant.now().plus(tokenProvider.accessTtl());
        return new TokenResponse(access, refresh.token(), userId, role.name(), audience.value(), expiresAt);
    }

    public TokenResponse refresh(String refreshToken) {
        JwtTokenProvider.ParsedToken parsed;
        try {
            parsed = tokenProvider.parse(refreshToken);
        } catch (JwtTokenProvider.InvalidTokenException e) {
            throw new AuthFailedException("refresh 토큰이 유효하지 않습니다.");
        }
        if (!parsed.isRefresh()) {
            throw new AuthFailedException("refresh 토큰이 아닙니다.");
        }
        if (!refreshStore.exists(parsed.audience(), parsed.userId(), parsed.jti())) {
            throw new AuthFailedException("만료되었거나 폐기된 refresh 토큰입니다.");
        }
        return issueTokens(parsed.userId(), parsed.role(), parsed.audience());
    }

    public void logout(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            return;
        }
        try {
            JwtTokenProvider.ParsedToken parsed = tokenProvider.parse(refreshToken);
            if (parsed.isRefresh()) {
                refreshStore.delete(parsed.audience(), parsed.userId(), parsed.jti());
            }
        } catch (JwtTokenProvider.InvalidTokenException ignored) {
            // 이미 유효하지 않은 토큰 — 멱등 처리
        }
    }

    public void logoutAll(Audience audience, String userId) {
        refreshStore.deleteAll(audience, userId);
    }

    public static class AuthFailedException extends RuntimeException {
        public AuthFailedException(String message) {
            super(message);
        }
    }
}
