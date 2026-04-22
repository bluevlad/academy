package com.academy.shared.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Admin/User JwtFilter 가 서로의 경로를 간섭하지 않는지 & audience 불일치 시
 * SecurityContext 를 세팅하지 않는지 확인.
 */
class JwtAuthenticationFilterTest {

    private JwtTokenProvider provider;
    private AdminJwtAuthenticationFilter adminFilter;
    private UserJwtAuthenticationFilter userFilter;

    @BeforeEach
    void setUp() {
        provider = new JwtTokenProvider(
            "filter-test-secret-at-least-32-bytes-in-length",
            Duration.ofMinutes(30),
            Duration.ofDays(14)
        );
        adminFilter = new AdminJwtAuthenticationFilter(provider);
        userFilter = new UserJwtAuthenticationFilter(provider);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void admin_filter_sets_context_for_admin_token_on_admin_path() throws ServletException, IOException {
        String token = provider.createAccessToken("admin-1", Role.ADMIN, Audience.ADMIN);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/admin/board/list");
        req.addHeader("Authorization", "Bearer " + token);

        adminFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertThat(auth).isNotNull();
        assertThat(auth.getName()).isEqualTo("admin-1");
        assertThat(auth.getAuthorities()).anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
    }

    @Test
    void admin_filter_skips_non_admin_path() throws ServletException, IOException {
        String token = provider.createAccessToken("u-1", Role.USER, Audience.USER);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/user/lecture/list");
        req.addHeader("Authorization", "Bearer " + token);

        adminFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void admin_filter_rejects_user_audience_token_on_admin_path() throws ServletException, IOException {
        String token = provider.createAccessToken("u-1", Role.USER, Audience.USER);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/admin/board/list");
        req.addHeader("Authorization", "Bearer " + token);

        adminFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void user_filter_sets_context_for_user_token_on_user_path() throws ServletException, IOException {
        String token = provider.createAccessToken("stu-7", Role.USER, Audience.USER);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/user/mypage/profile");
        req.addHeader("Authorization", "Bearer " + token);

        userFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertThat(auth).isNotNull();
        assertThat(auth.getName()).isEqualTo("stu-7");
        assertThat(auth.getAuthorities()).anyMatch(a -> "ROLE_USER".equals(a.getAuthority()));
    }

    @Test
    void refresh_token_does_not_authenticate() throws ServletException, IOException {
        JwtTokenProvider.IssuedRefreshToken refresh =
            provider.createRefreshToken("u-1", Role.USER, Audience.USER);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/user/mypage");
        req.addHeader("Authorization", "Bearer " + refresh.token());

        userFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void invalid_token_clears_context() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/admin/foo");
        req.addHeader("Authorization", "Bearer not-a-valid-jwt");

        adminFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void no_authorization_header_passes_through() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/admin/board/list");

        adminFilter.doFilter(req, new MockHttpServletResponse(), new MockFilterChain());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}
