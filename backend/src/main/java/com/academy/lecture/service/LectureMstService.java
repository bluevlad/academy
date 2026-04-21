package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.LectureMstMapper;

/**
 * LectureMst Service
 * 강의 마스터 관리 서비스 (직접 구현)
 */
@Service(value="lecturemstservice")
public class LectureMstService {

    @Autowired
    private LectureMstMapper lectureMstMapper;

    /**
     * 강의 마스터 목록 조회
     */
    public List<HashMap<String, String>> lecturemstList(LectureVO lectureVO){
        return lectureMstMapper.lecturemstList(lectureVO);
    }

    /**
     * 강의 마스터 목록 개수 조회
     */
    public int lecturemstListCount(LectureVO lectureVO){
        return lectureMstMapper.lecturemstListCount(lectureVO);
    }

    /**
     * Bridge Mstcode 시퀀스 조회
     */
    public List<HashMap<String, String>> getBridgeMstcodeSeq(LectureVO lectureVO){
        return lectureMstMapper.getBridgeMstcodeSeq(lectureVO);
    }

    /**
     * Bridge Mstcode 조회
     */
    public List<HashMap<String, String>> getBridgeMstcode(LectureVO lectureVO){
        return lectureMstMapper.getBridgeMstcode(lectureVO);
    }

    /**
     * Mstcode 조회
     */
    public List<HashMap<String, String>> getMstcode(LectureVO lectureVO){
        return lectureMstMapper.getMstcode(lectureVO);
    }

    /**
     * 강의 마스터 등록
     */
    public void lecturemstInsert(LectureVO lectureVO){
        lectureMstMapper.lecturemstInsert(lectureVO);
    }

    /**
     * 강의 Bridge 등록
     */
    public void lectureBridgeInsert(LectureVO lectureVO){
        lectureMstMapper.lectureBridgeInsert(lectureVO);
    }

    /**
     * 강의 교재 등록2
     */
    public void lectureBookInsert2(LectureVO lectureVO){
        lectureMstMapper.lectureBookInsert2(lectureVO);
    }

    /**
     * 강의 데이터 메모 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureDataMemoViewList(LectureVO lectureVO){
        return lectureMstMapper.lectureDataMemoViewList(lectureVO);
    }

    /**
     * 강의 데이터 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureDataViewList(LectureVO lectureVO){
        return lectureMstMapper.lectureDataViewList(lectureVO);
    }

    /**
     * WMV 강의 조회
     */
    public List<HashMap<String, String>> lectureWMV(LectureVO lectureVO){
        return lectureMstMapper.lectureWMV(lectureVO);
    }

    /**
     * 강의 다운로드 횟수 조회
     */
    public List<HashMap<String, String>> lectureDown_Count(LectureVO lectureVO){
        return lectureMstMapper.lectureDown_Count(lectureVO);
    }

    /**
     * 강의 삭제 체크
     */
    public int lectureDeleteCheck(LectureVO lectureVO){
        return lectureMstMapper.lectureDeleteCheck(lectureVO);
    }

    /**
     * 강의 상세 조회
     */
    public List<HashMap<String, String>> lectureView(LectureVO lectureVO){
        return lectureMstMapper.lectureView(lectureVO);
    }

    /**
     * 강의 상세 목록 조회
     */
    public List<HashMap<String, String>> lectureViewList(LectureVO lectureVO){
        return lectureMstMapper.lectureViewList(lectureVO);
    }

    /**
     * 강의 교재 목록 조회
     */
    public List<HashMap<String, String>> lectureViewBookList(LectureVO lectureVO){
        return lectureMstMapper.lectureViewBookList(lectureVO);
    }

    /**
     * 강의 교재 삭제
     */
    public void lectureBookDelete(LectureVO lectureVO){
        lectureMstMapper.lectureBookDelete(lectureVO);
    }

    /**
     * 강의 마스터 수정
     */
    public void lecturemstUpdate(LectureVO lectureVO){
        lectureMstMapper.lecturemstUpdate(lectureVO);
    }

    /**
     * 강의 교재 등록
     */
    public void lectureBookInsert(LectureVO lectureVO){
        lectureMstMapper.lectureBookInsert(lectureVO);
    }

    /**
     * 강의 삭제
     */
    public void lectureDelete(LectureVO lectureVO){
        lectureMstMapper.lectureDelete(lectureVO);
    }

    /**
     * 강의 Bridge 삭제
     */
    public void lectureBridgeDelete(LectureVO lectureVO){
        lectureMstMapper.lectureBridgeDelete(lectureVO);
    }
}