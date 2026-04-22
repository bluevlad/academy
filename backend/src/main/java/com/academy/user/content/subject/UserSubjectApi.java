package com.academy.user.content.subject;

import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/subject")
@Tag(name = "User Content - Subject", description = "활성 과목 목록 (강의 필터용)")
@SecurityRequirement(name = "bearer-jwt")
public class UserSubjectApi {

    private final UserSubjectMapper mapper;

    public UserSubjectApi(UserSubjectMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "활성 과목 목록 — ISUSE='Y' 필터")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectView>>> list(
        @RequestParam(required = false) String keyword
    ) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findActiveList(keyword)));
    }
}
