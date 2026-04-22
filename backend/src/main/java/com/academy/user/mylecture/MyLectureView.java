package com.academy.user.mylecture;

import java.time.LocalDate;

/**
 * 내 강의실 — 수강권(en_enrollment) + 강의 메타(TB_TOP_MST/TB_SUBJECT_INFO) JOIN 결과.
 */
public record MyLectureView(
    String enrollmentId,
    String mstCode,
    String subjectTitle,
    String subjectNm,
    String teacherNm,
    LocalDate periodStart,
    LocalDate periodEnd,
    int manualProgress,
    String status
) {}
