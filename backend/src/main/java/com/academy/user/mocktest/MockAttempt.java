package com.academy.user.mocktest;

import java.time.LocalDateTime;

public record MockAttempt(
    String attemptId,
    String userId,
    String examId,
    Integer score,
    String status,
    LocalDateTime registeredAt,
    LocalDateTime submittedAt
) {
    public static final class Status {
        public static final String REGISTERED = "REGISTERED";
        public static final String SUBMITTED = "SUBMITTED";
        public static final String SCORED = "SCORED";
        private Status() {}
    }
}
