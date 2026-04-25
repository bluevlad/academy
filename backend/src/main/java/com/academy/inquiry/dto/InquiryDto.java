package com.academy.inquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 문의 목록·상세 공통 응답 record. v_inquiry view 와 1:1 정렬.
 *
 * <p>{@code source} = 'L' (legacy TB_BOARD_CS) | 'N' (new tb_inquiry) — UI 가 답변/재배정
 * 가능 여부 판단에 사용. legacy 는 read-only.
 *
 * <p>{@code csSeq} = legacy 는 'CSBOARD_001' 형식 VARCHAR, 신규는 BIGINT 의 문자열 형식.
 * 양쪽 통일 위해 String 으로 노출. 신규 단독 API (사용자 본인 작성/조회) 는 long
 * inquiry_id 직접 사용 가능.
 *
 * <p>{@code bodyPreview} 는 목록에서만 사용. {@code body} 는 상세 조회에서만 채워짐.
 */
public record InquiryDto(
    String source,
    String csSeq,
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
