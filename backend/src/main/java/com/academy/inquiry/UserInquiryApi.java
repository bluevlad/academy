package com.academy.inquiry;

import com.academy.inquiry.dto.InquiryCreateRequest;
import com.academy.inquiry.dto.InquiryDto;
import com.academy.inquiry.dto.SuggestRequest;
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
 * 학생(USER) 전용 1:1 문의 작성·조회. Phase D.
 *
 * <p>SecurityConfig 가 {@code /api/user/**} 을 hasRole(USER) 로 보호.
 * 본인 것만 조회 가능 (selectDetail owner check).
 */
@RestController
@RequestMapping("/api/user/inquiries")
@Tag(name = "User Inquiries", description = "학생 1:1 문의 작성·조회")
public class UserInquiryApi {

    private final InquiryService service;

    public UserInquiryApi(InquiryService service) {
        this.service = service;
    }

    @Operation(summary = "문의 신규 등록 (자동 AI 분류 async 트리거)")
    @PostMapping
    public ResponseEntity<ApiResponse<InquiryDto>> create(
        @Valid @RequestBody InquiryCreateRequest req,
        Authentication auth
    ) {
        String userId = String.valueOf(auth.getPrincipal());
        InquiryDto d = service.createByUser(userId, userId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(d));
    }

    @Operation(summary = "내 문의 목록")
    @GetMapping
    public ApiResponse<PagedResponse<InquiryDto>> myList(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "20") int size,
        Authentication auth
    ) {
        String userId = String.valueOf(auth.getPrincipal());
        return ApiResponse.ok(service.myList(userId, page, size));
    }

    @Operation(summary = "내 문의 상세")
    @GetMapping("/{csSeq}")
    public ResponseEntity<ApiResponse<InquiryDto>> myDetail(
        @PathVariable long csSeq,
        Authentication auth
    ) {
        String userId = String.valueOf(auth.getPrincipal());
        InquiryDto d = service.myDetail(userId, csSeq);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail("INQUIRY_404", "문의를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(ApiResponse.ok(d));
    }

    @Operation(summary = "작성 중 유사문의 추천 (debounced agent 호출)")
    @PostMapping("/suggest-related")
    public ResponseEntity<ApiResponse<InquiryAgentClient.SuggestResponse>> suggest(
        @Valid @RequestBody SuggestRequest req
    ) {
        try {
            var r = service.suggestForDraft(req.draftBody(), req.topK());
            return ResponseEntity.ok(ApiResponse.ok(r));
        } catch (InquiryAgentClient.AgentException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.fail("AGENT_UNAVAILABLE", e.getMessage()));
        }
    }
}
