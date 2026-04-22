package com.academy.shared.security;

import org.springframework.stereotype.Component;

/**
 * {@code /api/user/**} 경로만 검증 (ADR-002).
 */
@Component
public class UserJwtAuthenticationFilter extends AbstractJwtAuthenticationFilter {

    public UserJwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        super(tokenProvider);
    }

    @Override
    protected Audience expectedAudience() {
        return Audience.USER;
    }

    @Override
    protected String pathPrefix() {
        return "/api/user/";
    }
}
