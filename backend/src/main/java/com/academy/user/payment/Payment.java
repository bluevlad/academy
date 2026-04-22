package com.academy.user.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(
    String paymentId,
    String orderId,
    String method,
    String status,
    String pgTxnId,
    BigDecimal amount,
    LocalDateTime createdAt,
    LocalDateTime approvedAt
) {
    public static final class Status {
        public static final String PENDING = "PENDING";
        public static final String APPROVED = "APPROVED";
        public static final String CANCELED = "CANCELED";
        public static final String FAILED = "FAILED";
        private Status() {}
    }
}
