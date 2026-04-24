package com.academy.inquiry.service;

import com.academy.inquiry.dto.AnswerRequest;
import com.academy.inquiry.dto.InquiryDto;
import com.academy.inquiry.dto.InquirySearchRequest;
import com.academy.inquiry.dto.ReassignRequest;
import com.academy.mapper.InquiryMapper;
import com.academy.shared.common.PagedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    private static String truncate(String s, int max) {
        if (s == null) return null;
        return s.length() > max ? s.substring(0, max) : s;
    }

    // 시그니처 미사용 — Jackson 직렬화용 더미 필드 아님
    @SuppressWarnings("unused")
    private static BigDecimal _unused;
}
