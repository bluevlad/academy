package com.academy.user.content.lecture;

import java.time.LocalDateTime;

/**
 * 강의 목록 노출용 요약 DTO (Sprint 2-2).
 */
public record LectureSummary(
    String mstCode,
    String subjectTitle,
    String subjectCd,
    String subjectNm,
    String teacherId,
    String teacherNm,
    String learningCd,
    LocalDateTime regDt
) {}
