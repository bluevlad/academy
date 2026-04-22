package com.academy.user.content.teacher;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
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

import java.util.List;

@RestController
@RequestMapping("/api/user/teacher")
@Tag(name = "User Content - Teacher", description = "교수진 목록·상세")
@SecurityRequirement(name = "bearer-jwt")
public class UserTeacherApi {

    private final UserTeacherMapper mapper;

    public UserTeacherApi(UserTeacherMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "교수진 목록 — 강의 1건 이상 담당한 회원만 노출")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TeacherView>>> list(
        @RequestParam(required = false) String keyword
    ) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findList(keyword)));
    }

    @Operation(summary = "교수진 상세")
    @GetMapping("/{teacherId}")
    public ResponseEntity<ApiResponse<TeacherView>> detail(@PathVariable String teacherId) {
        return mapper.findById(teacherId)
            .map(v -> ResponseEntity.ok(ApiResponse.ok(v)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("TEACHER_404", "교수를 찾을 수 없습니다: " + teacherId))));
    }
}
