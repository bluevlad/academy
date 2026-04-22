package com.academy.user.mocktest;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/mocktest")
@Tag(name = "User Mock Exam", description = "모의고사 신청·응시·성적")
@SecurityRequirement(name = "bearer-jwt")
public class MockTestApi {

    private final MockTestService service;

    public MockTestApi(MockTestService service) {
        this.service = service;
    }

    @Operation(summary = "공개된 모의고사 목록")
    @GetMapping("/exams")
    public ResponseEntity<ApiResponse<List<MockExam>>> openExams() {
        return ResponseEntity.ok(ApiResponse.ok(service.openExams()));
    }

    @Operation(summary = "내 응시 이력")
    @GetMapping("/attempts")
    public ResponseEntity<ApiResponse<List<MockAttempt>>> myAttempts(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(service.myAttempts(auth.getName())));
    }

    @Operation(summary = "모의고사 신청 (멱등)")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MockAttempt>> register(Authentication auth, @RequestBody RegisterRequest req) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.register(auth.getName(), req.examId())));
        } catch (MockTestService.ExamNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("MOCK_404", e.getMessage())));
        }
    }

    @Operation(summary = "응시 제출 — answerSheet + 자동 채점 score(옵션)")
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<MockAttempt>> submit(Authentication auth, @RequestBody SubmitRequest req) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(
                service.submit(auth.getName(), req.examId(), req.answerSheet(), req.score())
            ));
        } catch (MockTestService.ExamNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("MOCK_404", e.getMessage())));
        }
    }

    public record RegisterRequest(@NotBlank String examId) {}
    public record SubmitRequest(@NotBlank String examId, String answerSheet, Integer score) {}
}
