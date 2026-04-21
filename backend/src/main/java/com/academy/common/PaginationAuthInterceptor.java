package com.academy.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 페이지네이션 권한 체크 인터셉터
 *
 * - 기본적으로 첫 페이지(pageIndex=1)만 조회 가능
 * - 다음 페이지(pageIndex > 1) 조회 조건:
 *   1. 유효한 JWT 토큰이 Authorization 헤더에 포함되어 있어야 함
 *   2. 토큰에 포함된 userRole이 'ADMIN'이어야 함
 *
 * @author rainend
 * @since 2025.12.29
 */
@Component
public class PaginationAuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int FIRST_PAGE = 1;

    private final JwtUtil jwtUtil;

    @Autowired
    public PaginationAuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        // OPTIONS 요청은 통과
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // pageIndex 파라미터 확인
        int pageIndex = getPageIndex(request);

        // 첫 페이지(pageIndex=1)는 인증 없이 조회 가능
        if (pageIndex <= FIRST_PAGE) {
            return true;
        }

        // 2페이지 이상 요청 시 ADMIN 권한 확인
        String token = extractToken(request);

        if (token == null || token.isEmpty()) {
            sendUnauthorizedResponse(response, "다음 페이지 조회를 위해 로그인이 필요합니다.");
            return false;
        }

        // 토큰 유효성 및 ADMIN 권한 확인
        if (!jwtUtil.validateAdminToken(token)) {
            sendForbiddenResponse(response, "다음 페이지 조회 권한이 없습니다. ADMIN 권한이 필요합니다.");
            return false;
        }

        return true;
    }

    /**
     * 요청에서 pageIndex 파라미터 추출
     */
    private int getPageIndex(HttpServletRequest request) {
        String pageIndexStr = request.getParameter("pageIndex");
        if (pageIndexStr == null || pageIndexStr.isEmpty()) {
            // currentPage도 확인 (alias)
            pageIndexStr = request.getParameter("currentPage");
        }
        if (pageIndexStr == null || pageIndexStr.isEmpty()) {
            return FIRST_PAGE; // 기본값
        }
        try {
            return Integer.parseInt(pageIndexStr);
        } catch (NumberFormatException e) {
            return FIRST_PAGE;
        }
    }

    /**
     * Authorization 헤더에서 Bearer 토큰 추출
     */
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }
        // 헤더에 없으면 파라미터에서도 확인
        return request.getParameter("token");
    }

    /**
     * 401 Unauthorized 응답
     */
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"retMsg\":\"FAIL\",\"errorCode\":\"UNAUTHORIZED\",\"message\":\"" + message + "\"}");
    }

    /**
     * 403 Forbidden 응답
     */
    private void sendForbiddenResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"retMsg\":\"FAIL\",\"errorCode\":\"FORBIDDEN\",\"message\":\"" + message + "\"}");
    }
}
