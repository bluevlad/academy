package com.academy.inquiry.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * 담당자·카테고리 재배정. is_ai_error=true 시 학습 셋 포함 (routing_log 기록).
 */
public record ReassignRequest(
    @NotBlank String toCategory,
    @NotBlank String toUser,
    String reason,
    boolean isAiError
) {}
