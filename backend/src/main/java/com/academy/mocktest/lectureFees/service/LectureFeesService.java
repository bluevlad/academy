package com.academy.mocktest.lectureFees.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.LectureFeesMapper;

/**
 * 모의고사 수강료 관리 서비스
 */
@Service
public class LectureFeesService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LectureFeesMapper lectureFeesMapper;

    @Autowired
    public LectureFeesService(LectureFeesMapper lectureFeesMapper) {
        this.lectureFeesMapper = lectureFeesMapper;
    }

    public List<LectureFeesVO> getLectureFeesList(LectureFeesVO vo) {
        return lectureFeesMapper.selectLectureFeesList(vo);
    }

    public int getLectureFeesListCount(LectureFeesVO vo) {
        return lectureFeesMapper.selectLectureFeesListCount(vo);
    }

    public LectureFeesVO getLectureFeesDetail(LectureFeesVO vo) {
        return lectureFeesMapper.selectLectureFeesDetail(vo);
    }

    @Transactional
    public int insertLectureFees(LectureFeesVO vo) {
        return lectureFeesMapper.insertLectureFees(vo);
    }

    @Transactional
    public int updateLectureFees(LectureFeesVO vo) {
        return lectureFeesMapper.updateLectureFees(vo);
    }

    @Transactional
    public int deleteLectureFees(LectureFeesVO vo) {
        return lectureFeesMapper.deleteLectureFees(vo);
    }

    public List<LectureFeesVO> getPackageFeesList(LectureFeesVO vo) {
        return lectureFeesMapper.selectPackageFeesList(vo);
    }

    public List<LectureFeesVO> getExamYearList(LectureFeesVO vo) {
        return lectureFeesMapper.selectExamYearList(vo);
    }
}
