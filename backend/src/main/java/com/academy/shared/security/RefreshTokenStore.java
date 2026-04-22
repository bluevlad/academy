package com.academy.shared.security;

import java.time.Duration;

/**
 * Refresh 토큰 저장소 (ADR-002). key = {@code refresh:{aud}:{userId}:{jti}} 로 관리.
 *
 * <p>서버에서 선택적 무효화 가능한 점이 session-only 방식 대비 장점.
 * 테스트에서는 {@link InMemoryRefreshTokenStore} 사용, 운영은 {@link RedisRefreshTokenStore}.
 */
public interface RefreshTokenStore {

    /** jti 단위 저장. TTL 경과 후 자동 삭제. */
    void save(Audience audience, String userId, String jti, Duration ttl);

    /** 해당 jti 가 유효(존재) 한지 확인. */
    boolean exists(Audience audience, String userId, String jti);

    /** 단일 refresh 폐기 (회전·로그아웃 시). */
    void delete(Audience audience, String userId, String jti);

    /** 해당 유저의 모든 refresh 폐기 (비밀번호 변경·강제 로그아웃 시). */
    void deleteAll(Audience audience, String userId);
}
