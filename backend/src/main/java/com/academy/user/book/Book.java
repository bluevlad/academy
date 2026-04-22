package com.academy.user.book;

import java.math.BigDecimal;

public record Book(
    String bookId,
    String title,
    String author,
    BigDecimal price,
    int stock,
    boolean isActive
) {}
