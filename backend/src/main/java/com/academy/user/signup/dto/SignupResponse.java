package com.academy.user.signup.dto;

public record SignupResponse(
    String userId,
    String userNm,
    String email
) {}
