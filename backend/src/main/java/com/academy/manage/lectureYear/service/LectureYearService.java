package com.academy.manage.lectureYear.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.LectureYearMapper;

/**
 * 강의 연도별 관리 서비스
 */
@Service
public class LectureYearService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LectureYearMapper lectureYearMapper;

    @Autowired
    public LectureYearService(LectureYearMapper lectureYearMapper) {
        this.lectureYearMapper = lectureYearMapper;
    }

    /**
     * 연도별 강의 목록 조회
     */
    public List<LectureYearVO> getLectureYearList(LectureYearVO vo) {
        return lectureYearMapper.selectLectureYearList(vo);
    }

    /**
     * 연도별 강의 목록 카운트
     */
    public int getLectureYearListCount(LectureYearVO vo) {
        return lectureYearMapper.selectLectureYearListCount(vo);
    }

    /**
     * 연도별 강의 상세 조회
     */
    public LectureYearVO getLectureYearDetail(LectureYearVO vo) {
        return lectureYearMapper.selectLectureYearDetail(vo);
    }

    /**
     * 연도 목록 조회
     */
    public List<LectureYearVO> getYearList(LectureYearVO vo) {
        return lectureYearMapper.selectYearList(vo);
    }

    /**
     * 연도별 강의 통계 조회
     */
    public LectureYearVO getLectureYearStats(LectureYearVO vo) {
        return lectureYearMapper.selectLectureYearStats(vo);
    }

    /**
     * 연도별 카테고리 강의 통계 조회
     */
    public List<LectureYearVO> getCategoryYearStatsList(LectureYearVO vo) {
        return lectureYearMapper.selectCategoryYearStatsList(vo);
    }

    /**
     * 연도별 강사 강의 통계 조회
     */
    public List<LectureYearVO> getTeacherYearStatsList(LectureYearVO vo) {
        return lectureYearMapper.selectTeacherYearStatsList(vo);
    }

    /**
     * 연도별 매출 통계 조회
     */
    public List<LectureYearVO> getYearlySaleStatsList(LectureYearVO vo) {
        return lectureYearMapper.selectYearlySaleStatsList(vo);
    }

    /**
     * 연도 정보 등록
     */
    @Transactional
    public int insertLectureYear(LectureYearVO vo) {
        return lectureYearMapper.insertLectureYear(vo);
    }

    /**
     * 연도 정보 수정
     */
    @Transactional
    public int updateLectureYear(LectureYearVO vo) {
        return lectureYearMapper.updateLectureYear(vo);
    }

    /**
     * 연도 정보 삭제
     */
    @Transactional
    public int deleteLectureYear(LectureYearVO vo) {
        return lectureYearMapper.deleteLectureYear(vo);
    }

    /**
     * 강의-연도 매핑 등록
     */
    @Transactional
    public int insertLectureYearMapping(LectureYearVO vo) {
        return lectureYearMapper.insertLectureYearMapping(vo);
    }

    /**
     * 강의-연도 매핑 삭제
     */
    @Transactional
    public int deleteLectureYearMapping(LectureYearVO vo) {
        return lectureYearMapper.deleteLectureYearMapping(vo);
    }
}
