package com.academy.shared.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * {@code id_admin} MyBatis mapper. namespace 는 FQN 준수 (CLAUDE.md 원칙).
 */
@Mapper
public interface AdminMapper {

    Optional<AdminVO> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    int insert(AdminVO admin);

    int updateLastLoginAt(@Param("adminId") String adminId);

    int existsByUsername(@Param("username") String username);

    /**
     * bootstrap 시 환경설정의 패스워드가 바뀌었으면 동기화용.
     * email / role / enabled 도 함께 정렬.
     */
    int updateAccount(
        @Param("username") String username,
        @Param("passwordHash") String passwordHash,
        @Param("email") String email,
        @Param("role") String role,
        @Param("enabled") boolean enabled
    );
}
