package com.academy.shared.auth.dto;

import java.time.Instant;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    String userId,
    String role,
    String audience,
    Instant accessExpiresAt
) {}
