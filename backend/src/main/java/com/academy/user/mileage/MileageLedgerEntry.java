package com.academy.user.mileage;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MileageLedgerEntry(
    long ledgerId,
    String userId,
    BigDecimal delta,
    String reason,
    String orderId,
    BigDecimal balanceAfter,
    LocalDateTime createdAt
) {}
