package com.academy.user.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * {@code acm_member} 로그인 조회 mapper (Sprint 1-2).
 *
 * <p>namespace FQN 원칙 (CLAUDE.md) 준수. 비즈니스 전반의 회원 수정·조회는 기존
 * {@link com.academy.mapper.MemberMapper} 를 당분간 공존 사용.
 */
@Mapper
public interface UserAccountMapper {

    Optional<UserAccountVO> findByUserId(@Param("userId") String userId);

    int existsByUserId(@Param("userId") String userId);

    int existsByEmail(@Param("email") String email);

    int insert(UserAccountVO account);

    int updatePassword(@Param("userId") String userId, @Param("passwordHash") String passwordHash);

    int updateProfile(UserAccountVO account);

    int markWithdrawn(@Param("userId") String userId);
}
