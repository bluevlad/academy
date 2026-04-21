package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.LectureVO;

/**
 * LectureMst Mapper Interface
 * lectureMstSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface LectureMstMapper {

    /**
     * 강의 마스터 목록 조회
     */
    List<HashMap<String, String>> lecturemstList(LectureVO lectureVO);

    /**
     * 강의 마스터 목록 카운트
     */
    int lecturemstListCount(LectureVO lectureVO);

    /**
     * 브릿지 마스터 코드 시퀀스 조회
     */
    List<HashMap<String, String>> getBridgeMstcodeSeq(LectureVO lectureVO);

    /**
     * 브릿지 마스터 코드 조회
     */
    List<HashMap<String, String>> getBridgeMstcode(LectureVO lectureVO);

    /**
     * 마스터 코드 조회
     */
    List<HashMap<String, String>> getMstcode(LectureVO lectureVO);

    /**
     * 강의 마스터 등록
     */
    void lecturemstInsert(LectureVO lectureVO);

    /**
     * 강의 브릿지 등록
     */
    void lectureBridgeInsert(LectureVO lectureVO);

    /**
     * 강의 교재 등록2
     */
    void lectureBookInsert2(LectureVO lectureVO);

    /**
     * 강의 데이터 메모 뷰 목록
     */
    List<HashMap<String, String>> lectureDataMemoViewList(LectureVO lectureVO);

    /**
     * 강의 데이터 뷰 목록
     */
    List<HashMap<String, String>> lectureDataViewList(LectureVO lectureVO);

    /**
     * 강의 WMV
     */
    List<HashMap<String, String>> lectureWMV(LectureVO lectureVO);

    /**
     * 강의 다운로드 카운트
     */
    List<HashMap<String, String>> lectureDown_Count(LectureVO lectureVO);

    /**
     * 강의 삭제 체크
     */
    int lectureDeleteCheck(LectureVO lectureVO);

    /**
     * 강의 조회
     */
    List<HashMap<String, String>> lectureView(LectureVO lectureVO);

    /**
     * 강의 뷰 교재 목록
     */
    List<HashMap<String, String>> lectureViewBookList(LectureVO lectureVO);

    /**
     * 강의 뷰 목록
     */
    List<HashMap<String, String>> lectureViewList(LectureVO lectureVO);

    /**
     * 강의 교재 삭제
     */
    void lectureBookDelete(LectureVO lectureVO);

    /**
     * 강의 마스터 수정
     */
    void lecturemstUpdate(LectureVO lectureVO);

    /**
     * 강의 교재 등록
     */
    void lectureBookInsert(LectureVO lectureVO);

    /**
     * 강의 삭제
     */
    void lectureDelete(LectureVO lectureVO);

    /**
     * 강의 브릿지 삭제
     */
    void lectureBridgeDelete(LectureVO lectureVO);
}