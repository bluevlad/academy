package com.academy.mapper;

import com.academy.inquiry.dto.InquiryDto;
import com.academy.inquiry.dto.InquirySearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDto> selectList(@Param("req") InquirySearchRequest req);

    long selectCount(@Param("req") InquirySearchRequest req);

    InquiryDto selectDetail(@Param("csSeq") long csSeq);

    int updateClassification(
        @Param("csSeq") long csSeq,
        @Param("category") String category,
        @Param("confidence") BigDecimal confidence,
        @Param("model") String model,
        @Param("classifiedAt") LocalDateTime classifiedAt
    );

    int updateAnswer(
        @Param("csSeq") long csSeq,
        @Param("body") String answerBody,
        @Param("by") String answeredBy,
        @Param("state") String resolutionState,
        @Param("answeredAt") LocalDateTime answeredAt
    );

    int updateReassign(
        @Param("csSeq") long csSeq,
        @Param("toCategory") String toCategory,
        @Param("toUser") String toUser
    );

    int insertRoutingLog(
        @Param("csSeq") long csSeq,
        @Param("fromCategory") String fromCategory,
        @Param("toCategory") String toCategory,
        @Param("fromUser") String fromUser,
        @Param("toUser") String toUser,
        @Param("reason") String reason,
        @Param("changedBy") String changedBy,
        @Param("isAiError") String isAiError
    );

    int insertAnalysisLog(
        @Param("csSeq") long csSeq,
        @Param("modelName") String modelName,
        @Param("promptTemplate") String promptTemplate,
        @Param("parsedCategory") String parsedCategory,
        @Param("confidence") BigDecimal confidence,
        @Param("latencyMs") Integer latencyMs,
        @Param("rawOutput") String rawOutput
    );

    /** 사용자 문의 신규 등록. generated cs_seq 반환 위해 VO 대신 Long 사용시 useGeneratedKeys 필요. */
    long insertInquiry(
        @Param("userId") String userId,
        @Param("name") String name,
        @Param("title") String title,
        @Param("body") String body
    );

    /** 본인이 등록한 문의 목록 (inquiry_user_id = userId). */
    List<InquiryDto> selectMyList(
        @Param("userId") String userId,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    long selectMyCount(@Param("userId") String userId);

    /** 특정 YYYY-MM 의 카테고리별 집계. COALESCE(actual, predicted). */
    List<java.util.Map<String, Object>> selectMonthlyStats(@Param("ym") String ym);

    /** AI 오분류 건수 / 전체 재배정 건수 — 정확도 지표. */
    java.util.Map<String, Object> selectRoutingStats(@Param("ym") String ym);

    /** 미해결 문의 top-N (접수 오래된 순). */
    List<InquiryDto> selectUnresolvedTop(@Param("limit") int limit);
}
