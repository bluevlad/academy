package com.academy.shared.auth.dto;

import java.time.Instant;

public record MeResponse(
    String userId,
    String role,
    String audience,
    Instant expiresAt
) {}
