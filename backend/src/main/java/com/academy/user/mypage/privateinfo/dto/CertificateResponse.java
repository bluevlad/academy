package com.academy.user.mypage.privateinfo.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * 수강확인증 — MVP Sprint 1-4 에서는 기본 필드만 (JSON). PDF 생성은 Sprint 5 mylecture 통합 시 추가.
 * P0 canonical "수강확인증 출력" (variants 12).
 */
public record CertificateResponse(
    String userId,
    String userNm,
    String email,
    LocalDate issuedDate,
    List<EnrollmentSummary> enrollments,
    String note
) {
    public record EnrollmentSummary(
        String lectureId,
        String lectureNm,
        String period,
        String status
    ) {}
}
