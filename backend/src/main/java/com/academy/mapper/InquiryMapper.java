package com.academy.mapper;

import com.academy.inquiry.dto.InquiryDto;
import com.academy.inquiry.dto.InquirySearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 1:1 문의 매퍼. v_inquiry (legacy + 신규 통합) 조회와 tb_inquiry 직접 UPDATE 분리.
 *
 * <ul>
 *   <li>운영자 list/detail/통계/추천 → v_inquiry (csSeq 는 String, source 포함)</li>
 *   <li>분류/답변/재배정 UPDATE → tb_inquiry 직접 (inquiryId BIGINT) — legacy 는 read-only</li>
 *   <li>사용자 신규 작성·본인 목록 → tb_inquiry 직접</li>
 * </ul>
 */
@Mapper
public interface InquiryMapper {

    // ==============================================================
    // 운영자 콘솔 조회 (v_inquiry — legacy + 신규 통합)
    // ==============================================================

    List<InquiryDto> selectList(@Param("req") InquirySearchRequest req);

    long selectCount(@Param("req") InquirySearchRequest req);

    /** csSeq = view 의 통합 PK (legacy 'CSBOARD_001' or 신규 '12345'). */
    InquiryDto selectDetail(@Param("csSeq") String csSeq);

    // ==============================================================
    // 운영자 액션 — tb_inquiry 직접 (legacy 적용 불가)
    // ==============================================================

    int updateClassification(
        @Param("inquiryId") long inquiryId,
        @Param("category") String category,
        @Param("confidence") BigDecimal confidence,
        @Param("model") String model,
        @Param("classifiedAt") LocalDateTime classifiedAt
    );

    int updateAnswer(
        @Param("inquiryId") long inquiryId,
        @Param("body") String answerBody,
        @Param("by") String answeredBy,
        @Param("state") String resolutionState,
        @Param("answeredAt") LocalDateTime answeredAt
    );

    int updateReassign(
        @Param("inquiryId") long inquiryId,
        @Param("toCategory") String toCategory,
        @Param("toUser") String toUser
    );

    int insertRoutingLog(
        @Param("inquiryId") long inquiryId,
        @Param("fromCategory") String fromCategory,
        @Param("toCategory") String toCategory,
        @Param("fromUser") String fromUser,
        @Param("toUser") String toUser,
        @Param("reason") String reason,
        @Param("changedBy") String changedBy,
        @Param("isAiError") String isAiError
    );

    int insertAnalysisLog(
        @Param("inquiryId") long inquiryId,
        @Param("modelName") String modelName,
        @Param("promptTemplate") String promptTemplate,
        @Param("parsedCategory") String parsedCategory,
        @Param("confidence") BigDecimal confidence,
        @Param("latencyMs") Integer latencyMs,
        @Param("rawOutput") String rawOutput
    );

    // ==============================================================
    // 사용자 신규 작성·본인 목록 — tb_inquiry 직접
    // ==============================================================

    /** 사용자 문의 신규 등록. int = affected rows. Service 에서 selectMyList 로 최신 행 재조회. */
    int insertInquiry(
        @Param("userId") String userId,
        @Param("name") String name,
        @Param("title") String title,
        @Param("body") String body
    );

    /** 본인이 등록한 신규 문의 목록 (legacy 미포함). */
    List<InquiryDto> selectMyList(
        @Param("userId") String userId,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    long selectMyCount(@Param("userId") String userId);

    // ==============================================================
    // 통계·추천 — v_inquiry
    // ==============================================================

    /** 특정 YYYY-MM 의 카테고리별 집계. COALESCE(actual, predicted). legacy + 신규 통합. */
    List<java.util.Map<String, Object>> selectMonthlyStats(@Param("ym") String ym);

    /** AI 오분류 건수 / 전체 재배정 건수 — 정확도 지표 (신규 tb_inquiry_routing_log 만). */
    java.util.Map<String, Object> selectRoutingStats(@Param("ym") String ym);

    /** 미해결 문의 top-N (접수 오래된 순). 사용자 유사문의 추천 풀에 활용 — legacy 포함. */
    List<InquiryDto> selectUnresolvedTop(@Param("limit") int limit);
}
