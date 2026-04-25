package com.academy.shared.dashboard;

import java.math.BigDecimal;
import java.util.List;

/**
 * academy 서비스 dashboard 의 public 요약 응답.
 *
 * <p>설계 원칙:
 * <ul>
 *   <li>절대값(count, currency) 노출 금지 — {@code valueRatio}, {@code deltaPct} 만</li>
 *   <li>비로그인 visitor 도 호출 가능 (SecurityConfig {@code /api/shared/**} permitAll)</li>
 *   <li>학생/운영자 PII 컬럼은 응답 schema 자체에 포함되지 않음</li>
 * </ul>
 *
 * <p>차후 다른 서비스 도입 시 동일 schema 그대로 사용 가능 (포트폴리오 hub 표준).
 */
public record DashboardSummary(
    Service service,
    List<Metric> metrics,
    Uptime uptime,
    String ymBasis
) {
    public record Service(String id, String name, String tagline) {}

    public record Metric(
        String key,
        String label,
        BigDecimal valueRatio,         // 0.0 ~ 1.0 (또는 null)
        BigDecimal deltaPct,           // 전월 대비 (또는 null)
        String trend,                  // up | down | flat
        boolean sentimentInverted      // true 면 deltaPct 음수가 긍정
    ) {}

    public record Uptime(boolean backend, boolean agent, boolean db) {}
}
