package com.academy.shared.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * 오류 페이로드 (ADR-003). code 는 도메인별 prefix 권장 (예: AUTH_001, VALIDATION_001).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
    String code,
    String message,
    Map<String, Object> details
) {

    public static ApiError of(String code, String message) {
        return new ApiError(code, message, null);
    }
}
