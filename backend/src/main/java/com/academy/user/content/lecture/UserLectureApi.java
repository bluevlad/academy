package com.academy.user.content.lecture;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import com.academy.shared.common.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 수강생용 강의 조회 (Sprint 2-2).
 *
 * <p>인증 필요 — {@code /api/user/**} prefix 로 UserJwtAuthenticationFilter + hasRole(USER).
 */
@RestController
@RequestMapping("/api/user/lecture")
@Tag(name = "User Content - Lecture", description = "강의 목록·상세·검색")
@SecurityRequirement(name = "bearer-jwt")
public class UserLectureApi {

    private final UserLectureService service;

    public UserLectureApi(UserLectureService service) {
        this.service = service;
    }

    @Operation(summary = "강의 목록", description = "keyword/subjectCd/teacherId 로 필터. page/size=20 기본")
    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<LectureSummary>>> list(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String subjectCd,
        @RequestParam(required = false) String teacherId,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(ApiResponse.ok(service.list(keyword, subjectCd, teacherId, page, size)));
    }

    @Operation(summary = "강의 상세 + 챕터 목록")
    @GetMapping("/{mstCode}")
    public ResponseEntity<ApiResponse<LectureDetail>> detail(@PathVariable String mstCode) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.detail(mstCode)));
        } catch (UserLectureService.LectureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("LECTURE_404", e.getMessage())));
        }
    }
}
