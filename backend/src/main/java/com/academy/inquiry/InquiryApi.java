package com.academy.inquiry;

import com.academy.inquiry.dto.AnswerRequest;
import com.academy.inquiry.dto.InquiryDto;
import com.academy.inquiry.dto.InquirySearchRequest;
import com.academy.inquiry.dto.ReassignRequest;
import com.academy.inquiry.service.InquiryAgentClient;
import com.academy.inquiry.service.InquiryService;
import com.academy.shared.common.ApiResponse;
import com.academy.shared.common.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자 콘솔용 CS 문의 응대 API. (CS_INQUIRY_AI_PLAN Phase C)
 *
 * <p>ROLE_ADMIN 보호 — SecurityConfig 가 {@code /api/**} 전역 hasRole(ADMIN) 적용.
 * academy-agent (로컬 Ollama) HTTP 로 분류·유사추천·피드백.
 */
@RestController
@RequestMapping("/api/inquiries")
@Tag(name = "Inquiries", description = "CS 1:1 문의 운영자 콘솔")
public class InquiryApi {

    private final InquiryService service;

    public InquiryApi(InquiryService service) {
        this.service = service;
    }

    @Operation(summary = "문의 목록 (검색·페이징)")
    @GetMapping
    public ApiResponse<PagedResponse<InquiryDto>> list(InquirySearchRequest req) {
        return ApiResponse.ok(service.list(req));
    }

    @Operation(summary = "문의 상세")
    @GetMapping("/{csSeq}")
    public ResponseEntity<ApiResponse<InquiryDto>> detail(@PathVariable long csSeq) {
        InquiryDto d = service.detail(csSeq);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail("INQUIRY_404", "문의를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(ApiResponse.ok(d));
    }

    @Operation(summary = "AI 분류 재실행 (agent 호출)")
    @PostMapping("/{csSeq}/classify")
    public ResponseEntity<ApiResponse<InquiryDto>> classifyNow(@PathVariable long csSeq) {
        try {
            InquiryDto d = service.classifyNow(csSeq);
            if (d == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.fail("INQUIRY_404", "문의를 찾을 수 없습니다."));
            }
            return ResponseEntity.ok(ApiResponse.ok(d));
        } catch (InquiryAgentClient.AgentException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.fail("AGENT_UNAVAILABLE", e.getMessage()));
        }
    }

    @Operation(summary = "답변 저장")
    @PostMapping("/{csSeq}/answer")
    public ResponseEntity<ApiResponse<InquiryDto>> answer(
        @PathVariable long csSeq,
        @Valid @RequestBody AnswerRequest req,
        Authentication auth
    ) {
        String userId = auth != null ? String.valueOf(auth.getPrincipal()) : "system";
        InquiryDto d = service.answer(csSeq, req, userId);
        return ResponseEntity.ok(ApiResponse.ok(d));
    }

    @Operation(summary = "담당자·카테고리 재배정")
    @PostMapping("/{csSeq}/reassign")
    public ResponseEntity<ApiResponse<InquiryDto>> reassign(
        @PathVariable long csSeq,
        @Valid @RequestBody ReassignRequest req,
        Authentication auth
    ) {
        String userId = auth != null ? String.valueOf(auth.getPrincipal()) : "system";
        InquiryDto d = service.reassign(csSeq, req, userId);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail("INQUIRY_404", "문의를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(ApiResponse.ok(d));
    }

    @Operation(summary = "월간 통계 (카테고리 트렌드·AI 정확도·미해결 top)")
    @GetMapping("/stats")
    public ApiResponse<com.academy.inquiry.dto.MonthlyStatsResponse> stats(
        @RequestParam(name = "ym") String yearMonth
    ) {
        return ApiResponse.ok(service.monthlyStats(yearMonth));
    }

    @Operation(summary = "유사 문의 추천 (agent 호출)")
    @GetMapping("/{csSeq}/related")
    public ResponseEntity<ApiResponse<InquiryAgentClient.SuggestResponse>> related(@PathVariable long csSeq) {
        try {
            InquiryAgentClient.SuggestResponse r = service.related(csSeq);
            if (r == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.fail("INQUIRY_404", "문의를 찾을 수 없습니다."));
            }
            return ResponseEntity.ok(ApiResponse.ok(r));
        } catch (InquiryAgentClient.AgentException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.fail("AGENT_UNAVAILABLE", e.getMessage()));
        }
    }
}
