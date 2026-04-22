package com.academy.user.book;

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
@RequestMapping("/api/user/book")
@Tag(name = "User Book", description = "교재 카탈로그")
@SecurityRequirement(name = "bearer-jwt")
public class BookApi {

    private final BookMapper mapper;

    public BookApi(BookMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "활성 교재 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> list(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findActive(keyword)));
    }

    @Operation(summary = "교재 상세")
    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> detail(@PathVariable String bookId) {
        return mapper.findById(bookId)
            .map(b -> ResponseEntity.ok(ApiResponse.ok(b)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("BOOK_404", "교재를 찾을 수 없습니다: " + bookId))));
    }
}
