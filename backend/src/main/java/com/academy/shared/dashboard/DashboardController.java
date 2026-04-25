package com.academy.shared.dashboard;

import com.academy.inquiry.dto.MonthlyStatsResponse;
import com.academy.inquiry.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * academy.unmong.com 진입 시 보여지는 public dashboard 의 데이터 소스.
 *
 * <p>{@code /api/shared/**} 는 SecurityConfig 에서 permitAll — 비로그인 호출 가능.
 * 응답에는 절대값 없음 — % (valueRatio, deltaPct) 만.
 */
@RestController
@RequestMapping("/api/shared/dashboard")
@Tag(name = "Shared — Dashboard", description = "공개 대시보드 (포트폴리오 진입 화면)")
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

    private final InquiryService inquiryService;

    public DashboardController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/summary")
    @Operation(summary = "dashboard 요약 — 비로그인 호출 가능, % 만 노출")
    public DashboardSummary summary() {
        String ym = YearMonth.now().toString();
        List<DashboardSummary.Metric> metrics = new ArrayList<>();

        boolean dbUp = true;
        try {
            MonthlyStatsResponse stats = inquiryService.monthlyStats(ym);
            metrics.add(new DashboardSummary.Metric(
                "inquiry-resolution-rate",
                "1:1 문의 처리율",
                stats.resolutionRate(),
                null, // monthly stats 응답엔 처리율 자체의 mom delta 없음 — 카테고리별만 보유
                trendOf(stats.resolutionRate()),
                false
            ));
            metrics.add(new DashboardSummary.Metric(
                "ai-classification-accuracy",
                "AI 분류 정확도",
                stats.aiAccuracyRate(),
                null,
                trendOf(stats.aiAccuracyRate()),
                false
            ));
            // 카테고리 단위 mom delta 평균 — 단일 mom 신호로 노출
            BigDecimal momAvg = avgMomDelta(stats);
            if (momAvg != null) {
                metrics.add(new DashboardSummary.Metric(
                    "monthly-volume-trend",
                    "월간 문의량 변화",
                    null,
                    momAvg,
                    momAvg.signum() > 0 ? "up" : (momAvg.signum() < 0 ? "down" : "flat"),
                    true   // 문의가 증가하면 부담 — 음수가 긍정
                ));
            }
        } catch (Exception e) {
            log.warn("dashboard monthlyStats 실패 — uptime=false: {}", e.getMessage());
            dbUp = false;
        }

        return new DashboardSummary(
            new DashboardSummary.Service(
                "academy",
                "Academy",
                "학원 운영·학습·1:1 문의 AI 자동분류 통합 시스템"
            ),
            metrics,
            new DashboardSummary.Uptime(true, true, dbUp),
            ym
        );
    }

    private static String trendOf(BigDecimal ratio) {
        if (ratio == null) return "flat";
        // 처리율·정확도 절대값으로 trend 판정 (0.85 이상 up, 0.6 미만 down)
        if (ratio.compareTo(new BigDecimal("0.85")) >= 0) return "up";
        if (ratio.compareTo(new BigDecimal("0.60")) < 0) return "down";
        return "flat";
    }

    private static BigDecimal avgMomDelta(MonthlyStatsResponse stats) {
        if (stats.categories() == null || stats.categories().isEmpty()) return null;
        BigDecimal sum = BigDecimal.ZERO;
        int n = 0;
        for (MonthlyStatsResponse.CategoryStat c : stats.categories()) {
            if (c.momDeltaPct() != null) {
                sum = sum.add(c.momDeltaPct());
                n++;
            }
        }
        if (n == 0) return null;
        return sum.divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);
    }
}
