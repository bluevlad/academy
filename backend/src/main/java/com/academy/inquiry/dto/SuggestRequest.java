package com.academy.inquiry.dto;

import jakarta.validation.constraints.NotBlank;

public record SuggestRequest(
    @NotBlank String draftBody,
    Integer topK
) {}
