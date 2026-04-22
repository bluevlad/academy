package com.academy.user.signup.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * {@code POST /api/auth/signup} 요청 (Sprint 1-3).
 *
 * @param userId   영문·숫자 4~20자
 * @param password 8자 이상 (BCrypt 해시 저장, ADR-002)
 * @param userNm   실명 (1~50자)
 * @param email    이메일 (선택)
 */
public record SignupRequest(
    @NotBlank @Pattern(regexp = "^[A-Za-z0-9._-]{4,20}$",
        message = "userId 는 영문·숫자·._- 4~20자") String userId,
    @NotBlank @Size(min = 8, max = 100, message = "비밀번호는 8자 이상") String password,
    @NotBlank @Size(max = 50) String userNm,
    @Email String email
) {}
