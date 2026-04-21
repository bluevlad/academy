package com.academy.manage.teacherCalculate.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.TeacherCalculateMapper;

/**
 * 강사 정산 관리 서비스
 */
@Service
public class TeacherCalculateService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final TeacherCalculateMapper teacherCalculateMapper;

    @Autowired
    public TeacherCalculateService(TeacherCalculateMapper teacherCalculateMapper) {
        this.teacherCalculateMapper = teacherCalculateMapper;
    }

    /**
     * 강사 정산 목록 조회
     */
    public List<TeacherCalculateVO> getTeacherCalculateList(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectTeacherCalculateList(vo);
    }

    /**
     * 강사 정산 목록 카운트
     */
    public int getTeacherCalculateListCount(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectTeacherCalculateListCount(vo);
    }

    /**
     * 강사 정산 상세 조회
     */
    public TeacherCalculateVO getTeacherCalculateDetail(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectTeacherCalculateDetail(vo);
    }

    /**
     * 강사별 정산 요약 조회
     */
    public List<TeacherCalculateVO> getTeacherCalculateSummaryList(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectTeacherCalculateSummaryList(vo);
    }

    /**
     * 월별 정산 목록 조회
     */
    public List<TeacherCalculateVO> getMonthlyCalculateList(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectMonthlyCalculateList(vo);
    }

    /**
     * 강사별 강의 매출 목록 조회
     */
    public List<TeacherCalculateVO> getTeacherLectureSaleList(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectTeacherLectureSaleList(vo);
    }

    /**
     * 정산 통계 조회
     */
    public TeacherCalculateVO getCalculateStats(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectCalculateStats(vo);
    }

    /**
     * 강사 목록 조회
     */
    public List<TeacherCalculateVO> getTeacherList(TeacherCalculateVO vo) {
        return teacherCalculateMapper.selectTeacherList(vo);
    }

    /**
     * 강사 정산 등록
     */
    @Transactional
    public int insertTeacherCalculate(TeacherCalculateVO vo) {
        return teacherCalculateMapper.insertTeacherCalculate(vo);
    }

    /**
     * 강사 정산 수정
     */
    @Transactional
    public int updateTeacherCalculate(TeacherCalculateVO vo) {
        return teacherCalculateMapper.updateTeacherCalculate(vo);
    }

    /**
     * 강사 정산 삭제
     */
    @Transactional
    public int deleteTeacherCalculate(TeacherCalculateVO vo) {
        return teacherCalculateMapper.deleteTeacherCalculate(vo);
    }

    /**
     * 정산 상태 변경
     */
    @Transactional
    public int updateCalculateStatus(TeacherCalculateVO vo) {
        return teacherCalculateMapper.updateCalculateStatus(vo);
    }

    /**
     * 지급 처리
     */
    @Transactional
    public int updatePayment(TeacherCalculateVO vo) {
        return teacherCalculateMapper.updatePayment(vo);
    }

    /**
     * 월별 정산 일괄 생성
     */
    @Transactional
    public int insertMonthlyCalculateBatch(TeacherCalculateVO vo) {
        return teacherCalculateMapper.insertMonthlyCalculateBatch(vo);
    }
}
