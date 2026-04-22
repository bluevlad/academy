package com.academy.user.enrollment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Enrollment(
    String enrollmentId,
    String userId,
    String mstCode,
    String orderId,
    String status,
    LocalDate periodStart,
    LocalDate periodEnd,
    int manualProgress,
    LocalDateTime createdAt
) {
    public static final class Status {
        public static final String ACTIVE = "ACTIVE";
        public static final String CANCELED = "CANCELED";
        public static final String EXPIRED = "EXPIRED";
        private Status() {}
    }
}
