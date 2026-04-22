package com.academy.shared.common;

import java.util.List;

/**
 * ADR-003 의 pagination envelope. {@code data} 필드 안에 들어가는 구조.
 */
public record PagedResponse<T>(
    List<T> items,
    int page,
    int size,
    long totalItems,
    int totalPages
) {
    public static <T> PagedResponse<T> of(List<T> items, int page, int size, long total) {
        int totalPages = size <= 0 ? 0 : (int) ((total + size - 1) / size);
        return new PagedResponse<>(items, page, size, total, totalPages);
    }
}
