package com.academy.inquiry.service;

import com.academy.inquiry.dto.AnswerRequest;
import com.academy.inquiry.dto.InquiryCreateRequest;
import com.academy.inquiry.dto.InquiryDto;
import com.academy.inquiry.dto.InquirySearchRequest;
import com.academy.inquiry.dto.MonthlyStatsResponse;
import com.academy.inquiry.dto.ReassignRequest;
import com.academy.mapper.InquiryMapper;
import com.academy.shared.common.PagedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class InquiryService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(InquiryService.class);

    private static final String SOURCE_NEW = "N";

    private final InquiryMapper mapper;
    private final InquiryAgentClient agent;

    public InquiryService(InquiryMapper mapper, InquiryAgentClient agent) {
        this.mapper = mapper;
        this.agent = agent;
    }

    @Transactional(readOnly = true)
    public PagedResponse<InquiryDto> list(InquirySearchRequest req) {
        List<InquiryDto> items = mapper.selectList(req);
        long total = mapper.selectCount(req);
        return PagedResponse.of(items, req.pageOrDefault(), req.sizeOrDefault(), total);
    }

    @Transactional(readOnly = true)
    public InquiryDto detail(String csSeq) {
        return mapper.selectDetail(csSeq);
    }

    /**
     * agent 호출 → 분류 결과 저장. legacy 항목은 read-only 라 NotSupported 반환.
     */
    @Transactional
    public InquiryDto classifyNow(String csSeq) throws InquiryAgentClient.AgentException {
        InquiryDto current = mapper.selectDetail(csSeq);
        if (current == null) return null;

        long inquiryId = requireNewInquiryId(current);

        InquiryAgentClient.ClassifyResponse r = agent.classify(current.inquiryTitle(), current.body());

        LocalDateTime now = LocalDateTime.now();
        mapper.updateClassification(inquiryId, r.category(), r.confidence(), r.model(), now);
        mapper.insertAnalysisLog(inquiryId, r.model(), "v1", r.category(), r.confidence(),
            r.latency_ms(), truncate(r.reasoning(), 2000));

        return mapper.selectDetail(csSeq);
    }

    @Transactional
    public InquiryDto answer(String csSeq, AnswerRequest req, String answeredBy) {
        InquiryDto current = mapper.selectDetail(csSeq);
        if (current == null) return null;

        long inquiryId = requireNewInquiryId(current);

        String state = (req.resolutionState() == null || req.resolutionState().isBlank())
            ? "ANSWERED" : req.resolutionState();
        mapper.updateAnswer(inquiryId, req.answerBody(), answeredBy, state, LocalDateTime.now());
        return mapper.selectDetail(csSeq);
    }

    @Transactional
    public InquiryDto reassign(String csSeq, ReassignRequest req, String changedBy) {
        InquiryDto current = mapper.selectDetail(csSeq);
        if (current == null) return null;

        long inquiryId = requireNewInquiryId(current);

        String fromCategory = current.actualCategory() != null
            ? current.actualCategory() : current.predictedCategory();
        String fromUser = current.assignedTo();

        mapper.updateReassign(inquiryId, req.toCategory(), req.toUser());
        mapper.insertRoutingLog(
            inquiryId, fromCategory, req.toCategory(), fromUser, req.toUser(),
            req.reason(), changedBy, req.isAiError() ? "Y" : "N"
        );

        try {
            agent.recordFeedback(inquiryId, fromCategory, req.toCategory(),
                req.toUser(), changedBy, req.reason(), req.isAiError());
        } catch (Exception e) {
            log.warn("agent 피드백 실패 (무시): {}", e.getMessage());
        }

        return mapper.selectDetail(csSeq);
    }

    public InquiryAgentClient.SuggestResponse related(String csSeq) throws InquiryAgentClient.AgentException {
        InquiryDto d = mapper.selectDetail(csSeq);
        if (d == null) return null;
        return agent.suggestRelated(d.body(), 3);
    }

    // ==================================================================
    // Phase D — 사용자 작성·본인 목록·유사추천·통계·async 분류
    // ==================================================================

    /** 사용자 신규 문의 등록 후 async 분류 트리거. */
    @Transactional
    public InquiryDto createByUser(String userId, String userName, InquiryCreateRequest req) {
        String name = req.inquiryName() != null && !req.inquiryName().isBlank()
            ? req.inquiryName() : userName;

        mapper.insertInquiry(userId, name, req.title(), req.body());

        InquiryDto created = mapper.selectMyList(userId, 1, 0).stream()
            .findFirst().orElse(null);

        if (created != null) {
            try {
                long inquiryId = Long.parseLong(created.csSeq());
                classifyAsync(inquiryId);
            } catch (NumberFormatException e) {
                log.warn("createByUser 후 csSeq 파싱 실패: {}", created.csSeq());
            }
        }
        return created;
    }

    /** 비동기 분류 — 사용자 응답 블로킹 방지. 실패 시 로그만. */
    @Async
    public void classifyAsync(long inquiryId) {
        try {
            classifyNow(String.valueOf(inquiryId));
        } catch (Exception e) {
            log.warn("async classify 실패 inquiry_id={}: {}", inquiryId, e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public PagedResponse<InquiryDto> myList(String userId, int page, int size) {
        int p = Math.max(1, page);
        int s = Math.max(1, Math.min(100, size));
        int offset = (p - 1) * s;
        List<InquiryDto> items = mapper.selectMyList(userId, s, offset);
        long total = mapper.selectMyCount(userId);
        return PagedResponse.of(items, p, s, total);
    }

    /** 본인 신규 문의 상세. 소유자 아니면 null. */
    @Transactional(readOnly = true)
    public InquiryDto myDetail(String userId, long inquiryId) {
        InquiryDto d = mapper.selectDetail(String.valueOf(inquiryId));
        if (d == null || !SOURCE_NEW.equals(d.source()) || !userId.equals(d.inquiryUserId())) {
            return null;
        }
        return d;
    }

    /** 문의 작성 중 유사문의 추천 (agent 호출). 로그인 사용자 전용. */
    public InquiryAgentClient.SuggestResponse suggestForDraft(String draftBody, Integer topK)
        throws InquiryAgentClient.AgentException {
        return agent.suggestRelated(draftBody, topK == null ? 3 : topK);
    }

    /** 운영자 대시보드 월간 통계. */
    @Transactional(readOnly = true)
    public MonthlyStatsResponse monthlyStats(String ym) {
        List<Map<String, Object>> curr = mapper.selectMonthlyStats(ym);

        YearMonth y = YearMonth.parse(ym);
        String prevYm = y.minusMonths(1).toString();
        Map<String, Long> prev = new HashMap<>();
        for (Map<String, Object> r : mapper.selectMonthlyStats(prevYm)) {
            prev.put(String.valueOf(r.get("category")), toLong(r.get("totalCount")));
        }

        List<MonthlyStatsResponse.CategoryStat> cats = new ArrayList<>();
        long total = 0;
        long resolved = 0;
        for (Map<String, Object> r : curr) {
            String cat = String.valueOf(r.get("category"));
            long cnt = toLong(r.get("totalCount"));
            long res = toLong(r.get("resolvedCount"));
            BigDecimal sat = toDecimal(r.get("avgSatisfaction"));
            long prevCnt = prev.getOrDefault(cat, 0L);
            BigDecimal delta = null;
            boolean decreasing = false;
            if (prevCnt > 0) {
                delta = BigDecimal.valueOf(cnt - prevCnt)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(prevCnt), 2, RoundingMode.HALF_UP);
                decreasing = delta.signum() < 0;
            }
            cats.add(new MonthlyStatsResponse.CategoryStat(cat, cnt, res, prevCnt, delta, decreasing, sat));
            total += cnt;
            resolved += res;
        }

        Map<String, Object> routing = mapper.selectRoutingStats(ym);
        long totalLog = routing == null ? 0 : toLong(routing.get("totalLog"));
        long aiErr = routing == null ? 0 : toLong(routing.get("aiErrorCount"));
        BigDecimal aiAccuracy = totalLog > 0
            ? BigDecimal.valueOf(totalLog - aiErr).divide(BigDecimal.valueOf(totalLog), 4, RoundingMode.HALF_UP)
            : null;
        BigDecimal resRate = total > 0
            ? BigDecimal.valueOf(resolved).divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP)
            : null;

        List<InquiryDto> unresolvedTop = mapper.selectUnresolvedTop(5);

        return new MonthlyStatsResponse(
            ym, cats, total, resolved, resRate,
            aiAccuracy, aiErr, totalLog, unresolvedTop
        );
    }

    /**
     * legacy('L') 항목은 운영 액션(분류·답변·재배정) 불가. 신규('N') 항목의 csSeq 를
     * BIGINT inquiry_id 로 파싱하여 반환.
     */
    private static long requireNewInquiryId(InquiryDto d) {
        if (!SOURCE_NEW.equals(d.source())) {
            throw new IllegalStateException("legacy 문의는 운영 액션 적용 불가: csSeq=" + d.csSeq());
        }
        try {
            return Long.parseLong(d.csSeq());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("신규 문의 csSeq 파싱 실패: " + d.csSeq(), e);
        }
    }

    private static long toLong(Object o) {
        if (o == null) return 0;
        if (o instanceof Number n) return n.longValue();
        try { return Long.parseLong(o.toString()); } catch (Exception e) { return 0; }
    }

    private static BigDecimal toDecimal(Object o) {
        if (o == null) return null;
        if (o instanceof BigDecimal b) return b;
        if (o instanceof Number n) return BigDecimal.valueOf(n.doubleValue());
        try { return new BigDecimal(o.toString()); } catch (Exception e) { return null; }
    }

    private static String truncate(String s, int max) {
        if (s == null) return null;
        return s.length() > max ? s.substring(0, max) : s;
    }
}
