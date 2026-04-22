package com.academy.shared.security;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * JwtTokenProvider 단위 테스트 — Spring context·Redis 없이 독립 검증.
 */
class JwtTokenProviderTest {

    private final JwtTokenProvider provider = new JwtTokenProvider(
        "test-secret-must-be-at-least-32-bytes-long-xxx",
        Duration.ofMinutes(30),
        Duration.ofDays(14)
    );

    @Test
    void access_token_round_trip() {
        String token = provider.createAccessToken("u-42", Role.USER, Audience.USER);

        JwtTokenProvider.ParsedToken parsed = provider.parse(token);

        assertThat(parsed.userId()).isEqualTo("u-42");
        assertThat(parsed.role()).isEqualTo(Role.USER);
        assertThat(parsed.audience()).isEqualTo(Audience.USER);
        assertThat(parsed.isAccess()).isTrue();
        assertThat(parsed.isRefresh()).isFalse();
    }

    @Test
    void refresh_token_carries_jti_and_ttl() {
        JwtTokenProvider.IssuedRefreshToken issued =
            provider.createRefreshToken("admin", Role.ADMIN, Audience.ADMIN);

        assertThat(issued.jti()).isNotBlank();
        assertThat(issued.ttl()).isEqualTo(Duration.ofDays(14));

        JwtTokenProvider.ParsedToken parsed = provider.parse(issued.token());
        assertThat(parsed.isRefresh()).isTrue();
        assertThat(parsed.jti()).isEqualTo(issued.jti());
        assertThat(parsed.role()).isEqualTo(Role.ADMIN);
        assertThat(parsed.audience()).isEqualTo(Audience.ADMIN);
    }

    @Test
    void invalid_token_throws() {
        assertThatThrownBy(() -> provider.parse("not.a.jwt"))
            .isInstanceOf(JwtTokenProvider.InvalidTokenException.class);
    }

    @Test
    void short_secret_rejected() {
        assertThatThrownBy(() ->
            new JwtTokenProvider("too-short", Duration.ofMinutes(30), Duration.ofDays(14))
        ).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("32 바이트");
    }

    @Test
    void token_signed_by_different_secret_fails() {
        JwtTokenProvider other = new JwtTokenProvider(
            "OTHER-secret-also-at-least-32-bytes-in-length",
            Duration.ofMinutes(30),
            Duration.ofDays(14)
        );
        String tokenFromOther = other.createAccessToken("u", Role.USER, Audience.USER);

        assertThatThrownBy(() -> provider.parse(tokenFromOther))
            .isInstanceOf(JwtTokenProvider.InvalidTokenException.class);
    }
}
