package com.academy.inquiry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InquiryCreateRequest(
    @NotBlank @Size(max = 300) String title,
    @NotBlank String body,
    String inquiryName  // optional override; 기본은 사용자 이름
) {}
