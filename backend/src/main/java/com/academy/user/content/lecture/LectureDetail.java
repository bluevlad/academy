package com.academy.user.content.lecture;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 강의 상세 — 요약 + 챕터 목록 (동영상 플레이어 제외, Sprint 2-2).
 */
public record LectureDetail(
    String mstCode,
    String subjectTitle,
    String subjectCd,
    String subjectNm,
    String teacherId,
    String teacherNm,
    String learningCd,
    String subjectOption,
    LocalDateTime regDt,
    List<Chapter> chapters
) {
    /** 연결 강의 (TB_MST_BRIDGE) 단위를 "챕터" 로 노출. 실 컬럼은 브리지 단위 제목·순서. */
    public record Chapter(
        String bridgeMstCode,
        String title,
        int orderNo
    ) {}
}
