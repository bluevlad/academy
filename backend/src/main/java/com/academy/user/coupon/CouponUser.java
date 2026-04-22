package com.academy.user.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponUser(
    String couponUserId,
    String userId,
    String couponId,
    String name,
    String discountType,
    BigDecimal discountValue,
    LocalDateTime issuedAt,
    LocalDateTime usedAt,
    LocalDateTime validTo
) {}
