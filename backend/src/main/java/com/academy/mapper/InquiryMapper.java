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
}
