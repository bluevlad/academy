package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.manage.lectureYear.service.LectureYearVO;

/**
 * 강의 연도별 관리 Mapper
 */
@Mapper
public interface LectureYearMapper {

    /**
     * 연도별 강의 목록 조회
     */
    List<LectureYearVO> selectLectureYearList(LectureYearVO vo);

    /**
     * 연도별 강의 목록 카운트
     */
    int selectLectureYearListCount(LectureYearVO vo);

    /**
     * 연도별 강의 상세 조회
     */
    LectureYearVO selectLectureYearDetail(LectureYearVO vo);

    /**
     * 연도 목록 조회
     */
    List<LectureYearVO> selectYearList(LectureYearVO vo);

    /**
     * 연도별 강의 통계 조회
     */
    LectureYearVO selectLectureYearStats(LectureYearVO vo);

    /**
     * 연도별 카테고리 강의 통계 조회
     */
    List<LectureYearVO> selectCategoryYearStatsList(LectureYearVO vo);

    /**
     * 연도별 강사 강의 통계 조회
     */
    List<LectureYearVO> selectTeacherYearStatsList(LectureYearVO vo);

    /**
     * 연도별 매출 통계 조회
     */
    List<LectureYearVO> selectYearlySaleStatsList(LectureYearVO vo);

    /**
     * 연도 정보 등록
     */
    int insertLectureYear(LectureYearVO vo);

    /**
     * 연도 정보 수정
     */
    int updateLectureYear(LectureYearVO vo);

    /**
     * 연도 정보 삭제
     */
    int deleteLectureYear(LectureYearVO vo);

    /**
     * 강의-연도 매핑 등록
     */
    int insertLectureYearMapping(LectureYearVO vo);

    /**
     * 강의-연도 매핑 삭제
     */
    int deleteLectureYearMapping(LectureYearVO vo);
}
