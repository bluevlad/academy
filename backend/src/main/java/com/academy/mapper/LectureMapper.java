package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.LectureVO;

/**
 * Lecture Mapper Interface
 * lectureLectureSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface LectureMapper {

    // 강의 목록
    List<HashMap<String, String>> lectureList(LectureVO lectureVO);
    List<HashMap<String, String>> oldFreeToNewFree(LectureVO lectureVO);
    List<HashMap<String, String>> oldBogangFreeToNewBogangFree(LectureVO lectureVO);
    int lectureListCount(LectureVO lectureVO);

    // 교재 목록
    List<HashMap<String, String>> bookList(LectureVO lectureVO);
    int bookListCount(LectureVO lectureVO);
    List<HashMap<String, String>> bookView(LectureVO lectureVO);

    // 강의 코드 관련
    List<HashMap<String, String>> getBridgeLeccodeSeq(LectureVO lectureVO);
    List<HashMap<String, String>> getJongLeccodeSeq(LectureVO lectureVO);
    List<HashMap<String, String>> getLeccode(LectureVO lectureVO);
    List<HashMap<String, String>> getBridgeLeccode(LectureVO lectureVO);
    List<HashMap<String, String>> BridgeLeccode(LectureVO lectureVO);
    String getRleccode(LectureVO lectureVO);

    // 강의 등록
    void lectureInsert(LectureVO lectureVO);
    void lectureBridgeInsert(LectureVO lectureVO);
    void lectureBookInsert(LectureVO lectureVO);
    void lectureBookInsert2(LectureVO lectureVO);
    void oldFreeToNewFreeInsert(LectureVO lectureVO);
    void oldBogangFreeToNewFreeBogangInsert(LectureVO lectureVO);
    void oldTbmovieToNewTbmovieInsert(LectureVO lectureVO);

    // 강의 조회
    List<HashMap<String, String>> lectureViewList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureView(LectureVO lectureVO);
    List<HashMap<String, String>> lectureViewBookList(LectureVO lectureVO);
    HashMap<String, String> lectureOnDetailS(LectureVO lectureVO);

    // 강의 수정/삭제
    void lectureUpdate(LectureVO lectureVO);
    void lectureIsUseUpdate(LectureVO lectureVO);
    void Modify_Lecture_On_Off(LectureVO lectureVO);
    void lectureDelete(LectureVO lectureVO);
    void lectureBridgeDelete(LectureVO lectureVO);
    void lectureBookDelete(LectureVO lectureVO);
    void lecMovUpdate(LectureVO lectureVO);
    int lectureDeleteCheck(LectureVO lectureVO);

    // 강의 순서
    List<HashMap<String, String>> lectureSeqList(LectureVO lectureVO);
    void lectureSeqUpdate(LectureVO lectureVO);

    // 종강 관련
    List<HashMap<String, String>> lectureViewJongList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureJongList(LectureVO lectureVO);
    int lectureJongListCount(LectureVO lectureVO);
    List<HashMap<String, String>> lectureYearList(LectureVO lectureVO);
    int lectureYearListCount(LectureVO lectureVO);
    List<HashMap<String, String>> lectureJongView(LectureVO lectureVO);
    List<HashMap<String, String>> lectureJongSubjectList(LectureVO lectureVO);
    int lectureJongSubjectListCount(LectureVO lectureVO);
    void lectureLecJongInsert(LectureVO lectureVO);
    void lectureChoiceJongNoInsert(LectureVO lectureVO);
    List<HashMap<String, String>> lectureViewLeccodeList(LectureVO lectureVO);
    void lectureLecJongDelete(LectureVO lectureVO);
    void lectureChoiceJongNoDelete(LectureVO lectureVO);

    // 결제 관련
    List<HashMap<String, String>> lecturePayList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureJongPayList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureFreePassPayList(LectureVO lectureVO);
    List<HashMap<String, String>> YearIngList(LectureVO lectureVO);
    List<HashMap<String, String>> MyYearIngList(LectureVO lectureVO);

    // 강의 데이터
    List<HashMap<String, String>> lectureDataMemoViewList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureDataViewList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureMobileList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureDataMovieViewList(LectureVO lectureVO);
    List<HashMap<String, String>> lectureDataMovieList(LectureVO lectureVO);

    // 동영상 관련
    void lectureMovieInsert(LectureVO lectureVO);
    void lectureMovieDelete(LectureVO lectureVO);
    void lectureMovieUpdate(LectureVO lectureVO);
    void lectureMovieFileDelete(LectureVO lectureVO);
    void lectureMovieMemoInsert(LectureVO lectureVO);
    void lectureMovieMemoUpdate(LectureVO lectureVO);
    void lectureMovieMemoDelete(LectureVO lectureVO);

    // 재생 정보
    List<HashMap<String, String>> playinfo(LectureVO lectureVO);
    List<HashMap<String, String>> getCbMovie4_free_admin(LectureVO lectureVO);
    int getCbMovie4_free_admin_count(LectureVO lectureVO);
    void insertPmpDownLog(LectureVO lectureVO);
    List<HashMap<String, String>> lectureWMV(LectureVO lectureVO);
    List<HashMap<String, String>> lectureDown_Count(LectureVO lectureVO);

    // Legacy methods for complex logic (Object parameter)
    List<HashMap<String, String>> oldFreeToNewFree(Object params);
    List<HashMap<String, String>> oldBogangFreeToNewBogangFree(Object params);
    List<HashMap<String, String>> getBridgeLeccodeSeq(Object params);
    List<HashMap<String, String>> getBridgeLeccode(Object params);
    List<HashMap<String, String>> getLeccode(Object params);
    String getRleccode(Object params);
    void oldFreeToNewFreeInsert(Object params);
    void oldBogangFreeToNewFreeBogangInsert(Object params);
    void lectureBridgeInsert(Object params);
    void oldTbmovieToNewTbmovieInsert(Object params);
}