package com.academy.inquiry.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 월간 통계 응답. 운영자 대시보드용.
 */
public record MonthlyStatsResponse(
    String yearMonth,
    List<CategoryStat> categories,
    long totalInquiries,
    long resolvedCount,
    BigDecimal resolutionRate,
    BigDecimal aiAccuracyRate,   // 1 - (ai_error_count / total_routing)
    long aiErrorCount,
    long totalRoutingChanges,
    List<InquiryDto> unresolvedTop
) {
    public record CategoryStat(
        String category,
        long totalCount,
        long resolvedCount,
        long prevMonthCount,
        BigDecimal momDeltaPct,
        boolean decreasing,
        BigDecimal avgSatisfaction
    ) {}
}
