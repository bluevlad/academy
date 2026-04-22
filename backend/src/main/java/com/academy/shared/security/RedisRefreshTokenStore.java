package com.academy.shared.security;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

/**
 * Redis 기반 RefreshTokenStore 구현체 (ADR-002).
 *
 * <p>key 패턴: {@code refresh:{aud}:{userId}:{jti}} → value "1", TTL 14d.
 * {@link #deleteAll} 은 SCAN 기반으로 해당 prefix 전체 제거.
 */
@Component
public class RedisRefreshTokenStore implements RefreshTokenStore {

    private static final String KEY_PREFIX = "refresh:";
    private final StringRedisTemplate redis;

    public RedisRefreshTokenStore(StringRedisTemplate redis) {
        this.redis = redis;
    }

    private String key(Audience audience, String userId, String jti) {
        return KEY_PREFIX + audience.value() + ":" + userId + ":" + jti;
    }

    private String userKeyPattern(Audience audience, String userId) {
        return KEY_PREFIX + audience.value() + ":" + userId + ":*";
    }

    @Override
    public void save(Audience audience, String userId, String jti, Duration ttl) {
        redis.opsForValue().set(key(audience, userId, jti), "1", ttl);
    }

    @Override
    public boolean exists(Audience audience, String userId, String jti) {
        return Boolean.TRUE.equals(redis.hasKey(key(audience, userId, jti)));
    }

    @Override
    public void delete(Audience audience, String userId, String jti) {
        redis.delete(key(audience, userId, jti));
    }

    @Override
    public void deleteAll(Audience audience, String userId) {
        Set<String> keys = redis.keys(userKeyPattern(audience, userId));
        if (keys != null && !keys.isEmpty()) {
            redis.delete(keys);
        }
    }
}
