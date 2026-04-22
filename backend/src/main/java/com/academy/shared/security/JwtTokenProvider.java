package com.academy.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * JWT 발급·검증 (ADR-002).
 *
 * <p>claims:
 * <ul>
 *   <li>{@code sub}  — userId (문자열)</li>
 *   <li>{@code role} — ADMIN / USER</li>
 *   <li>{@code aud}  — admin / user (필터 shouldNotFilter 분기용)</li>
 *   <li>{@code typ}  — access / refresh</li>
 *   <li>{@code jti}  — refresh 고유 식별자 (Redis store 키에 사용)</li>
 * </ul>
 */
@Component
public class JwtTokenProvider {

    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_TYPE = "typ";
    public static final String CLAIM_AUDIENCE = "aud";
    public static final String TYPE_ACCESS = "access";
    public static final String TYPE_REFRESH = "refresh";

    private final Key signingKey;
    private final Duration accessTtl;
    private final Duration refreshTtl;

    public JwtTokenProvider(
        @Value("${app.jwt.secret:CHANGE_ME_MINIMUM_32_BYTES_FOR_HMAC_SHA256}") String secret,
        @Value("${app.jwt.access-ttl:PT30M}") Duration accessTtl,
        @Value("${app.jwt.refresh-ttl:P14D}") Duration refreshTtl
    ) {
        if (secret == null || secret.getBytes().length < 32) {
            throw new IllegalStateException("app.jwt.secret 은 최소 32 바이트여야 합니다.");
        }
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTtl = accessTtl;
        this.refreshTtl = refreshTtl;
    }

    public String createAccessToken(String userId, Role role, Audience audience) {
        Instant now = Instant.now();
        return Jwts.builder()
            .setSubject(userId)
            .setId(UUID.randomUUID().toString())
            .claim(CLAIM_ROLE, role.name())
            .claim(CLAIM_AUDIENCE, audience.value())
            .claim(CLAIM_TYPE, TYPE_ACCESS)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(accessTtl)))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public IssuedRefreshToken createRefreshToken(String userId, Role role, Audience audience) {
        Instant now = Instant.now();
        String jti = UUID.randomUUID().toString();
        String token = Jwts.builder()
            .setSubject(userId)
            .setId(jti)
            .claim(CLAIM_ROLE, role.name())
            .claim(CLAIM_AUDIENCE, audience.value())
            .claim(CLAIM_TYPE, TYPE_REFRESH)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(refreshTtl)))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact();
        return new IssuedRefreshToken(token, jti, refreshTtl);
    }

    public ParsedToken parse(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
            return new ParsedToken(
                claims.getSubject(),
                Role.valueOf(claims.get(CLAIM_ROLE, String.class)),
                Audience.of(claims.get(CLAIM_AUDIENCE, String.class)),
                claims.get(CLAIM_TYPE, String.class),
                claims.getId(),
                claims.getExpiration().toInstant()
            );
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.", e);
        }
    }

    public Duration refreshTtl() {
        return refreshTtl;
    }

    public Duration accessTtl() {
        return accessTtl;
    }

    public record IssuedRefreshToken(String token, String jti, Duration ttl) {}

    public record ParsedToken(
        String userId,
        Role role,
        Audience audience,
        String type,
        String jti,
        Instant expiresAt
    ) {
        public boolean isAccess() { return TYPE_ACCESS.equals(type); }
        public boolean isRefresh() { return TYPE_REFRESH.equals(type); }
    }

    public static class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
