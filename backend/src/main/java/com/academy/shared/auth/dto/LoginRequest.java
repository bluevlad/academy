package com.academy.shared.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * {@code POST /api/auth/login} 요청 (ADR-002).
 *
 * @param userId   관리자·수강생 공통 식별자 (email 또는 userId)
 * @param password 평문 비밀번호
 * @param audience {@code admin} / {@code user}
 */
public record LoginRequest(
    @NotBlank String userId,
    @NotBlank String password,
    @NotBlank String audience
) {}
