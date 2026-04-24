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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InquiryService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(InquiryService.class);

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
    public InquiryDto detail(long csSeq) {
        return mapper.selectDetail(csSeq);
    }

    /** agent 호출 → 분류 결과 저장. 실패시 예외 전파. */
    @Transactional
    public InquiryDto classifyNow(long csSeq) throws InquiryAgentClient.AgentException {
        InquiryDto current = mapper.selectDetail(csSeq);
        if (current == null) return null;

        InquiryAgentClient.ClassifyResponse r = agent.classify(current.inquiryTitle(), current.body());

        LocalDateTime now = LocalDateTime.now();
        mapper.updateClassification(csSeq, r.category(), r.confidence(), r.model(), now);
        mapper.insertAnalysisLog(csSeq, r.model(), "v1", r.category(), r.confidence(),
            r.latency_ms(), truncate(r.reasoning(), 2000));

        return mapper.selectDetail(csSeq);
    }

    @Transactional
    public InquiryDto answer(long csSeq, AnswerRequest req, String answeredBy) {
        String state = (req.resolutionState() == null || req.resolutionState().isBlank())
            ? "ANSWERED" : req.resolutionState();
        mapper.updateAnswer(csSeq, req.answerBody(), answeredBy, state, LocalDateTime.now());
        return mapper.selectDetail(csSeq);
    }

    @Transactional
    public InquiryDto reassign(long csSeq, ReassignRequest req, String changedBy) {
        InquiryDto current = mapper.selectDetail(csSeq);
        if (current == null) return null;

        String fromCategory = current.actualCategory() != null
            ? current.actualCategory() : current.predictedCategory();
        String fromUser = current.assignedTo();

        mapper.updateReassign(csSeq, req.toCategory(), req.toUser());
        mapper.insertRoutingLog(
            csSeq, fromCategory, req.toCategory(), fromUser, req.toUser(),
            req.reason(), changedBy, req.isAiError() ? "Y" : "N"
        );

        // agent 에도 피드백 (best-effort, 실패해도 진행)
        try {
            agent.recordFeedback(csSeq, fromCategory, req.toCategory(),
                req.toUser(), changedBy, req.reason(), req.isAiError());
        } catch (Exception e) {
            log.warn("agent 피드백 실패 (무시): {}", e.getMessage());
        }

        return mapper.selectDetail(csSeq);
    }

    public InquiryAgentClient.SuggestResponse related(long csSeq) throws InquiryAgentClient.AgentException {
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
        long csSeq = 0;
        // MyBatis useGeneratedKeys — 삽입 후 키 채워짐
        var params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("name", req.inquiryName() != null && !req.inquiryName().isBlank()
            ? req.inquiryName() : userName);
        params.put("title", req.title());
        params.put("body", req.body());

        // 직접 mapper 메서드 호출 — return type 이 insert id
        mapper.insertInquiry(userId,
            req.inquiryName() != null && !req.inquiryName().isBlank()
                ? req.inquiryName() : userName,
            req.title(), req.body());
        // Mapper 의 useGeneratedKeys=true 가 첫 번째 Long 반환을 채움. MyBatis 는 이를
        // selectKey 없이 안전하게 리턴하려면 trick 필요. 간단히 재조회로 대체:
        InquiryDto created = mapper.selectMyList(userId, 1, 0).stream()
            .findFirst().orElse(null);

        if (created != null) {
            classifyAsync(created.csSeq());
        }
        return created;
    }

    /** 비동기 분류 — 사용자 응답 블로킹 방지. 실패 시 로그만. */
    @Async
    public void classifyAsync(long csSeq) {
        try {
            classifyNow(csSeq);
        } catch (Exception e) {
            log.warn("async classify 실패 cs_seq={}: {}", csSeq, e.getMessage());
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

    /** 본인 문의 상세. 소유자 아니면 null. */
    @Transactional(readOnly = true)
    public InquiryDto myDetail(String userId, long csSeq) {
        InquiryDto d = mapper.selectDetail(csSeq);
        if (d == null || !userId.equals(d.inquiryUserId())) return null;
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

    // 시그니처 미사용 — Jackson 직렬화용 더미 필드 아님
    @SuppressWarnings("unused")
    private static BigDecimal _unused;
}
