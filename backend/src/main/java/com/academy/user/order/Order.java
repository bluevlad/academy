package com.academy.user.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Order(
    String orderId,
    String userId,
    String status,
    BigDecimal totalAmount,
    BigDecimal discountAmount,
    BigDecimal mileageUsed,
    LocalDateTime createdAt,
    LocalDateTime paidAt,
    LocalDateTime canceledAt,
    List<OrderItem> items
) {
    public record OrderItem(
        String orderItemId,
        String orderId,
        String mstCode,
        int quantity,
        BigDecimal unitPrice
    ) {}

    public static final class Status {
        public static final String PENDING   = "PENDING";
        public static final String PAID      = "PAID";
        public static final String CANCELED  = "CANCELED";
        public static final String REFUNDED  = "REFUNDED";
        private Status() {}
    }
}
