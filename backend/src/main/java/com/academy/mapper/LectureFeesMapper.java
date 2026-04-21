package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.mocktest.lectureFees.service.LectureFeesVO;

/**
 * 모의고사 수강료 관리 Mapper
 */
@Mapper
public interface LectureFeesMapper {

    List<LectureFeesVO> selectLectureFeesList(LectureFeesVO vo);

    int selectLectureFeesListCount(LectureFeesVO vo);

    LectureFeesVO selectLectureFeesDetail(LectureFeesVO vo);

    int insertLectureFees(LectureFeesVO vo);

    int updateLectureFees(LectureFeesVO vo);

    int deleteLectureFees(LectureFeesVO vo);

    List<LectureFeesVO> selectPackageFeesList(LectureFeesVO vo);

    List<LectureFeesVO> selectExamYearList(LectureFeesVO vo);
}
