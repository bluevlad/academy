package com.academy.shared.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

/**
 * 표준 응답 envelope (ADR-003).
 *
 * <p>{@code retMsg} 는 admin-web 구 포맷 후방 호환용 — Sprint 5 까지 유지.
 * {@code success} / {@code error} 가 신규 정식 필드.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    boolean success,
    T data,
    ApiError error,
    String retMsg,
    Instant timestamp
) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, "OK", Instant.now());
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(true, null, null, "OK", Instant.now());
    }

    public static <T> ApiResponse<T> fail(ApiError error) {
        return new ApiResponse<>(false, null, error, "FAIL", Instant.now());
    }

    public static <T> ApiResponse<T> fail(String code, String message) {
        return fail(new ApiError(code, message, null));
    }
}
