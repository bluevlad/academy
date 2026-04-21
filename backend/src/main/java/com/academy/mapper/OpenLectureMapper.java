package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.LectureVO;
import com.academy.lecture.service.OpenLectureVO;

/**
 * OpenLecture Mapper Interface
 * lectureOffSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface OpenLectureMapper {

    /**
     * 오픈강의 목록 조회
     */
    List<HashMap<String, String>> openlectureList(LectureVO lectureVO);

    /**
     * 오픈강의 목록 카운트
     */
    int openlectureListCount(LectureVO lectureVO);

    /**
     * 교재 목록 조회
     */
    List<HashMap<String, String>> bookList(LectureVO lectureVO);

    /**
     * 교재 목록 카운트
     */
    int bookListCount(LectureVO lectureVO);

    /**
     * 브릿지 강의코드 시퀀스 조회
     */
    List<HashMap<String, String>> getBridgeLeccodeSeq(LectureVO lectureVO);

    /**
     * 종강 강의코드 시퀀스 조회
     */
    List<HashMap<String, String>> getJongLeccodeSeq(LectureVO lectureVO);

    /**
     * 오픈강의 코드 조회
     */
    List<HashMap<String, String>> getopenLeccode(LectureVO lectureVO);

    /**
     * 브릿지 강의코드 조회
     */
    List<HashMap<String, String>> getBridgeLeccode(LectureVO lectureVO);

    /**
     * 브릿지 강의코드
     */
    List<HashMap<String, String>> BridgeLeccode(LectureVO lectureVO);

    /**
     * 오픈강의 등록
     */
    void openlectureInsert(LectureVO lectureVO);

    /**
     * 오픈강의 브릿지 등록
     */
    void openlectureBridgeInsert(LectureVO lectureVO);

    /**
     * 오픈강의 교재 등록
     */
    void openlectureBookInsert(LectureVO lectureVO);

    /**
     * 오픈강의 교재 등록2
     */
    void openlectureBookInsert2(LectureVO lectureVO);

    /**
     * 오픈강의 뷰 목록
     */
    List<HashMap<String, String>> openlectureViewList(LectureVO lectureVO);

    /**
     * 오픈강의 조회
     */
    List<HashMap<String, String>> openlectureView(LectureVO lectureVO);

    /**
     * 오픈강의 뷰 교재 목록
     */
    List<HashMap<String, String>> openlectureViewBookList(LectureVO lectureVO);

    /**
     * 오픈강의 교재 삭제
     */
    void openlectureBookDelete(OpenLectureVO openLectureVO);

    /**
     * 오픈강의 수정
     */
    void openlectureUpdate(LectureVO lectureVO);

    /**
     * 오픈강의 사용여부 수정
     */
    void openlectureIsUseUpdate(LectureVO lectureVO);

    /**
     * 강의 On/Off 수정
     */
    void Modify_Lecture_On_Off(LectureVO lectureVO);

    /**
     * 오픈강의 삭제
     */
    void openlectureDelete(OpenLectureVO openLectureVO);

    /**
     * 오픈강의 브릿지 삭제
     */
    void openlectureBridgeDelete(OpenLectureVO openLectureVO);

    /**
     * 강의 이동 수정
     */
    void lecMovUpdate(LectureVO lectureVO);

    /**
     * 오픈강의 순서 목록
     */
    List<HashMap<String, String>> openlectureSeqList(LectureVO lectureVO);

    /**
     * 오픈강의 순서 수정
     */
    void openlectureSeqUpdate(LectureVO lectureVO);

    /**
     * 오픈강의 종강 뷰 목록
     */
    List<HashMap<String, String>> openlectureViewJongList(LectureVO lectureVO);

    /**
     * 오픈강의 종강 목록
     */
    List<HashMap<String, String>> openlectureJongList(LectureVO lectureVO);

    /**
     * 오픈강의 종강 목록 카운트
     */
    int openlectureJongListCount(LectureVO lectureVO);

    /**
     * 오픈강의 종강 조회
     */
    List<HashMap<String, String>> openlectureJongView(LectureVO lectureVO);

    /**
     * 오픈강의 종강 과목 목록
     */
    List<HashMap<String, String>> openlectureJongSubjectList(LectureVO lectureVO);

    /**
     * 오픈강의 종강 과목 목록 카운트
     */
    int openlectureJongSubjectListCount(LectureVO lectureVO);

    /**
     * 오픈강의 종강 등록
     */
    void openlectureLecJongInsert(LectureVO lectureVO);

    /**
     * 오픈강의 선택 종강 번호 등록
     */
    void openlectureChoiceJongNoInsert(LectureVO lectureVO);

    /**
     * 오픈강의 뷰 강의코드 목록
     */
    List<HashMap<String, String>> openlectureViewLeccodeList(LectureVO lectureVO);

    /**
     * 오픈강의 종강 삭제
     */
    void openlectureLecJongDelete(LectureVO lectureVO);

    /**
     * 오픈강의 선택 종강 번호 삭제
     */
    void openlectureChoiceJongNoDelete(LectureVO lectureVO);

    /**
     * 오픈강의 결제 목록
     */
    List<HashMap<String, String>> openlecturePayList(LectureVO lectureVO);

    /**
     * 오픈강의 종강 결제 목록
     */
    List<HashMap<String, String>> openlectureJongPayList(LectureVO lectureVO);

    /**
     * 오픈강의 데이터 메모 뷰 목록
     */
    List<HashMap<String, String>> openlectureDataMemoViewList(LectureVO lectureVO);

    /**
     * 오픈강의 데이터 뷰 목록
     */
    List<HashMap<String, String>> openlectureDataViewList(LectureVO lectureVO);

    /**
     * 오픈강의 데이터 동영상 뷰 목록
     */
    List<HashMap<String, String>> openlectureDataMovieViewList(LectureVO lectureVO);

    /**
     * 오픈강의 데이터 동영상 목록
     */
    List<HashMap<String, String>> openlectureDataMovieList(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 등록
     */
    void openlectureMovieInsert(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 삭제
     */
    void openlectureMovieDelete(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 수정
     */
    void openlectureMovieUpdate(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 파일 삭제
     */
    void openlectureMovieFileDelete(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 메모 등록
     */
    void openlectureMovieMemoInsert(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 메모 수정
     */
    void openlectureMovieMemoUpdate(LectureVO lectureVO);

    /**
     * 오픈강의 동영상 메모 삭제
     */
    void openlectureMovieMemoDelete(LectureVO lectureVO);

    /**
     * 오픈강의 삭제 체크
     */
    int openlectureDeleteCheck(LectureVO lectureVO);

    /**
     * 재생 정보
     */
    List<HashMap<String, String>> playinfo(LectureVO lectureVO);

    /**
     * 무료 관리자 동영상 조회
     */
    List<HashMap<String, String>> getCbMovie4_free_admin(LectureVO lectureVO);

    /**
     * 무료 관리자 동영상 카운트
     */
    int getCbMovie4_free_admin_count(LectureVO lectureVO);

    /**
     * 오픈강의 온라인 상세 조회
     */
    HashMap<String, String> openlectureOnDetailS(LectureVO lectureVO);

    /**
     * PMP 다운로드 로그 등록
     */
    void insertPmpDownLog(LectureVO lectureVO);

    /**
     * 오픈강의 WMV
     */
    List<HashMap<String, String>> openlectureWMV(LectureVO lectureVO);

    /**
     * 오픈강의 다운로드 카운트
     */
    List<HashMap<String, String>> openlectureDown_Count(LectureVO lectureVO);
}