package com.academy.shared.auth;

import java.util.Optional;

/**
 * 사용자(수강생) 인증에 필요한 최소 정보를 조회하는 port.
 *
 * <p>shared 는 user 모듈의 구체 매퍼를 알지 않아야 한다(ArchUnit 경계).
 * 구현체는 {@code com.academy.user.login} 에 둔다.
 */
public interface UserCredentialsLookup {

    Optional<UserCredentials> findByUserId(String userId);

    /**
     * @param userId       로그인 식별자
     * @param passwordHash BCrypt 해시
     * @param userRole     USER_ROLE 원값 (ADMIN / USER 등)
     * @param active       IS_USE='Y' 활성 여부
     */
    record UserCredentials(String userId, String passwordHash, String userRole, boolean active) {}
}
