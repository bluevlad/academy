package com.academy.user.mocktest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MockExam(
    String examId,
    String name,
    String subjectCd,
    LocalDate scheduleDate,
    int maxScore,
    boolean isOpen,
    LocalDateTime createdAt
) {}
