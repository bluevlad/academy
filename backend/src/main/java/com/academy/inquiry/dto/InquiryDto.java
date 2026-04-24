package com.academy.inquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 문의 목록·상세 공통 응답 record.
 *
 * <p>{@code bodyPreview} 는 목록에서만 사용 (본문 HTML 일부 텍스트만).
 * {@code body} 는 상세 조회에서만 채워짐.
 */
public record InquiryDto(
    long csSeq,
    String inquiryUserId,
    String inquiryName,
    String inquiryTitle,
    String bodyPreview,
    String body,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime inquiryDate,
    String predictedCategory,
    BigDecimal predictedConfidence,
    String classifiedByModel,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime classifiedAt,
    String actualCategory,
    String assignedTo,
    int rerouteCount,
    String answerBody,
    String answeredBy,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime answeredAt,
    String resolutionState,
    Integer userSatisfaction
) {}
