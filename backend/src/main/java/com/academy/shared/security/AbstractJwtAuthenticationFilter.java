package com.academy.shared.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Bearer JWT 파싱 → SecurityContext 에 Authentication 주입 (ADR-002).
 *
 * <p>URL prefix 분기는 {@link #shouldNotFilter} 와 {@link #expectedAudience()} 로 제어.
 * Admin 경로와 User 경로를 다른 필터 인스턴스가 각각 담당하므로 role 오탑재 방지.
 */
public abstract class AbstractJwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AbstractJwtAuthenticationFilter.class);
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    protected final JwtTokenProvider tokenProvider;

    protected AbstractJwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    protected abstract Audience expectedAudience();

    protected abstract String pathPrefix();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(pathPrefix());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {

        String header = request.getHeader(HEADER);
        if (header == null || !header.startsWith(PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(PREFIX.length()).trim();
        try {
            JwtTokenProvider.ParsedToken parsed = tokenProvider.parse(token);
            if (!parsed.isAccess()) {
                log.debug("access 토큰이 아닌 요청: uri={} type={}", request.getRequestURI(), parsed.type());
                chain.doFilter(request, response);
                return;
            }
            if (parsed.audience() != expectedAudience()) {
                log.debug("audience 불일치: uri={} got={} expected={}",
                    request.getRequestURI(), parsed.audience(), expectedAudience());
                chain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                parsed.userId(), null, List.of(parsed.role().asAuthority())
            );
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (JwtTokenProvider.InvalidTokenException e) {
            log.debug("JWT 파싱 실패: uri={} reason={}", request.getRequestURI(), e.getMessage());
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }
}
