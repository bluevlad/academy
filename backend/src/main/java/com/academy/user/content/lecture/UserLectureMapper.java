package com.academy.user.content.lecture;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 수강생 노출용 강의 mapper (Sprint 2-2).
 *
 * <p>기존 admin Oracle 레거시 쿼리를 재사용하지 않고 MariaDB 표준 문법으로 신규 작성.
 */
@Mapper
public interface UserLectureMapper {

    List<LectureSummary> findOpenList(
        @Param("keyword") String keyword,
        @Param("subjectCd") String subjectCd,
        @Param("teacherId") String teacherId,
        @Param("offset") int offset,
        @Param("size") int size
    );

    long countOpenList(
        @Param("keyword") String keyword,
        @Param("subjectCd") String subjectCd,
        @Param("teacherId") String teacherId
    );

    Optional<LectureDetail> findDetailByMstCode(@Param("mstCode") String mstCode);

    List<LectureDetail.Chapter> findChaptersByMstCode(@Param("mstCode") String mstCode);
}
