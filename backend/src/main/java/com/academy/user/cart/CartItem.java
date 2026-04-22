package com.academy.user.cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CartItem(
    String cartItemId,
    String userId,
    String mstCode,
    int quantity,
    BigDecimal priceSnapshot,
    LocalDateTime addedAt
) {}
