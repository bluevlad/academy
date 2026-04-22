package com.academy.shared.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 관리자 경로 Bearer JWT 검증 (ADR-002).
 *
 * <p>Sprint 1-5 확장: 기존 admin 컨트롤러 대부분이 {@code /api/admin/} prefix 가 아니라
 * {@code /api/board/}·{@code /api/book/}·{@code /api/banner/} 등 단일 모듈 경로를 사용.
 * 이를 모두 감싸기 위해 필터 적용 범위를 {@code /api/} 전체로 확장하고, {@code /api/user/**}·
 * {@code /api/auth/**}·{@code /api/shared/**} 와 static 자원은 {@link #shouldNotFilter} 로 제외.
 */
@Component
public class AdminJwtAuthenticationFilter extends AbstractJwtAuthenticationFilter {

    public AdminJwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        super(tokenProvider);
    }

    @Override
    protected Audience expectedAudience() {
        return Audience.ADMIN;
    }

    @Override
    protected String pathPrefix() {
        return "/api/";
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (!uri.startsWith(pathPrefix())) return true;
        return uri.startsWith("/api/user/")
            || uri.startsWith("/api/auth/")
            || uri.startsWith("/api/shared/");
    }
}
