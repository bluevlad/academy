package com.academy.shared.security;

import org.springframework.stereotype.Component;

/**
 * {@code /api/admin/**} 경로만 검증 (ADR-002).
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
        return "/api/admin/";
    }
}
