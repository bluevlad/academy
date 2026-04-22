package com.academy.user.mylecture;

import com.academy.shared.common.ApiResponse;
import com.academy.user.enrollment.EnrollmentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/mylecture")
@Tag(name = "User My-Lecture", description = "내 강의실 (수강권 기반)")
@SecurityRequirement(name = "bearer-jwt")
public class MyLectureApi {

    private final MyLectureMapper viewMapper;
    private final EnrollmentMapper enrollmentMapper;

    public MyLectureApi(MyLectureMapper viewMapper, EnrollmentMapper enrollmentMapper) {
        this.viewMapper = viewMapper;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Operation(summary = "내 활성 수강 강의 목록 (수강권 JOIN TB_TOP_MST)")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MyLectureView>>> list(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(viewMapper.findActiveByUserId(auth.getName())));
    }

    @Operation(summary = "수동 진도율 업데이트 (0~100)")
    @PutMapping("/{enrollmentId}/progress")
    public ResponseEntity<ApiResponse<Void>> updateProgress(
        @PathVariable String enrollmentId,
        @RequestParam @Min(0) @Max(100) int progress
    ) {
        enrollmentMapper.updateProgress(enrollmentId, progress);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
