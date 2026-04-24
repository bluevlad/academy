package com.academy.inquiry.dto;

/**
 * 문의 검색·페이징 요청. 모든 필드 optional.
 * category 는 actual_category 또는 predicted_category 중 우선순위로 매칭.
 */
public record InquirySearchRequest(
    String category,         // ACADEMIC | ORDER | SYSTEM | OTHER
    String resolutionState,  // OPEN | ANSWERED | RESOLVED | CLOSED
    String keyword,          // title/body LIKE
    String assignedTo,
    Integer page,            // 1-based
    Integer size
) {
    public int pageOrDefault() {
        return (page == null || page < 1) ? 1 : page;
    }

    public int sizeOrDefault() {
        return (size == null || size < 1 || size > 200) ? 20 : size;
    }

    public int offset() {
        return (pageOrDefault() - 1) * sizeOrDefault();
    }
}
