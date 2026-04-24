package com.academy.inquiry.dto;

import jakarta.validation.constraints.NotBlank;

public record AnswerRequest(
    @NotBlank String answerBody,
    String resolutionState  // 미지정시 ANSWERED
) {}
