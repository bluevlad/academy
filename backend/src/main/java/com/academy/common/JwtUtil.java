package com.academy.common;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MySecretKeyForJwtTokenMySecretKeyForJwtToken"; // 최소 256비트
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    private static final String CLAIM_USER_ROLE = "userRole";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // JWT 토큰 생성 (기존 호환성 유지)
    public String generateToken(String username) {
        return generateToken(username, null);
    }

    // JWT 토큰 생성 (userRole 포함)
    public String generateToken(String username, String userRole) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(username) // 사용자 식별 값 (보통 username이나 userId)
                .setIssuedAt(new Date()) // 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)); // 만료 시간

        if (userRole != null && !userRole.isEmpty()) {
            builder.claim(CLAIM_USER_ROLE, userRole);
        }

        return builder.signWith(getSigningKey(), SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    // JWT 토큰에서 사용자명 추출
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // JWT 토큰에서 userRole 추출
    public String extractUserRole(String token) {
        Claims claims = getClaims(token);
        return claims.get(CLAIM_USER_ROLE, String.class);
    }

    // JWT 토큰에서 Claims 추출
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 토큰 검증 및 ADMIN 권한 확인
    public boolean validateAdminToken(String token) {
        try {
            if (!validateToken(token)) {
                return false;
            }
            String userRole = extractUserRole(token);
            return "ADMIN".equals(userRole);
        } catch (Exception e) {
            return false;
        }
    }
}