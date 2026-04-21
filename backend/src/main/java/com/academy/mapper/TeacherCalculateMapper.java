package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.manage.teacherCalculate.service.TeacherCalculateVO;

/**
 * 강사 정산 관리 Mapper
 */
@Mapper
public interface TeacherCalculateMapper {

    /**
     * 강사 정산 목록 조회
     */
    List<TeacherCalculateVO> selectTeacherCalculateList(TeacherCalculateVO vo);

    /**
     * 강사 정산 목록 카운트
     */
    int selectTeacherCalculateListCount(TeacherCalculateVO vo);

    /**
     * 강사 정산 상세 조회
     */
    TeacherCalculateVO selectTeacherCalculateDetail(TeacherCalculateVO vo);

    /**
     * 강사별 정산 요약 조회
     */
    List<TeacherCalculateVO> selectTeacherCalculateSummaryList(TeacherCalculateVO vo);

    /**
     * 월별 정산 목록 조회
     */
    List<TeacherCalculateVO> selectMonthlyCalculateList(TeacherCalculateVO vo);

    /**
     * 강사별 강의 매출 목록 조회
     */
    List<TeacherCalculateVO> selectTeacherLectureSaleList(TeacherCalculateVO vo);

    /**
     * 정산 통계 조회
     */
    TeacherCalculateVO selectCalculateStats(TeacherCalculateVO vo);

    /**
     * 강사 목록 조회
     */
    List<TeacherCalculateVO> selectTeacherList(TeacherCalculateVO vo);

    /**
     * 강사 정산 등록
     */
    int insertTeacherCalculate(TeacherCalculateVO vo);

    /**
     * 강사 정산 수정
     */
    int updateTeacherCalculate(TeacherCalculateVO vo);

    /**
     * 강사 정산 삭제
     */
    int deleteTeacherCalculate(TeacherCalculateVO vo);

    /**
     * 정산 상태 변경
     */
    int updateCalculateStatus(TeacherCalculateVO vo);

    /**
     * 지급 처리
     */
    int updatePayment(TeacherCalculateVO vo);

    /**
     * 월별 정산 일괄 생성
     */
    int insertMonthlyCalculateBatch(TeacherCalculateVO vo);
}
