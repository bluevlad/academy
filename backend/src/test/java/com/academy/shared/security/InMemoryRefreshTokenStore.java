package com.academy.shared.security;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 테스트 전용 in-memory RefreshTokenStore.
 * Redis 없이 {@link JwtTokenProvider}·AuthService 로직을 검증할 때 사용.
 */
public class InMemoryRefreshTokenStore implements RefreshTokenStore {

    private final Map<String, Long> store = new ConcurrentHashMap<>();

    private String key(Audience audience, String userId, String jti) {
        return audience.value() + ":" + userId + ":" + jti;
    }

    @Override
    public void save(Audience audience, String userId, String jti, Duration ttl) {
        store.put(key(audience, userId, jti), System.currentTimeMillis() + ttl.toMillis());
    }

    @Override
    public boolean exists(Audience audience, String userId, String jti) {
        Long expiry = store.get(key(audience, userId, jti));
        if (expiry == null) return false;
        if (System.currentTimeMillis() > expiry) {
            store.remove(key(audience, userId, jti));
            return false;
        }
        return true;
    }

    @Override
    public void delete(Audience audience, String userId, String jti) {
        store.remove(key(audience, userId, jti));
    }

    @Override
    public void deleteAll(Audience audience, String userId) {
        String prefix = audience.value() + ":" + userId + ":";
        store.keySet().removeIf(k -> k.startsWith(prefix));
    }
}
