package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.OpenLectureMapper;

/**
 * OpenLecture Service
 * 오픈강의(Off) 관리 서비스 (직접 구현)
 */
@Service(value="openlectureservice")
public class OpenLectureService {

    @Autowired
    private OpenLectureMapper openLectureMapper;

    public List<HashMap<String, String>> openlectureList(LectureVO lectureVO){
        return openLectureMapper.openlectureList(lectureVO);
    }

    public int openlectureListCount(LectureVO lectureVO){
        return openLectureMapper.openlectureListCount(lectureVO);
    }

    public List<HashMap<String, String>> bookList(LectureVO lectureVO){
        return openLectureMapper.bookList(lectureVO);
    }

    public int bookListCount(LectureVO lectureVO){
        return openLectureMapper.bookListCount(lectureVO);
    }

    public List<HashMap<String, String>> getBridgeLeccodeSeq(LectureVO lectureVO){
        return openLectureMapper.getBridgeLeccodeSeq(lectureVO);
    }

    public List<HashMap<String, String>> getJongLeccodeSeq(LectureVO lectureVO){
        return openLectureMapper.getJongLeccodeSeq(lectureVO);
    }

    public List<HashMap<String, String>> getopenLeccode(LectureVO lectureVO){
        return openLectureMapper.getopenLeccode(lectureVO);
    }

    public List<HashMap<String, String>> getBridgeLeccode(LectureVO lectureVO){
        return openLectureMapper.getBridgeLeccode(lectureVO);
    }

    public List<HashMap<String, String>> BridgeLeccode(LectureVO lectureVO){
        return openLectureMapper.BridgeLeccode(lectureVO);
    }

    public void openlectureInsert(LectureVO lectureVO){
        openLectureMapper.openlectureInsert(lectureVO);
    }

    public void openlectureBridgeInsert(LectureVO lectureVO){
        openLectureMapper.openlectureBridgeInsert(lectureVO);
    }

    public void openlectureBookInsert2(LectureVO lectureVO){
        openLectureMapper.openlectureBookInsert2(lectureVO);
    }

    public void openlectureBookInsert(LectureVO lectureVO){
        openLectureMapper.openlectureBookInsert(lectureVO);
    }

    public List<HashMap<String, String>> openlectureViewList(LectureVO lectureVO){
        return openLectureMapper.openlectureViewList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureView(LectureVO lectureVO){
        return openLectureMapper.openlectureView(lectureVO);
    }

    public List<HashMap<String, String>> openlectureViewBookList(LectureVO lectureVO){
        return openLectureMapper.openlectureViewBookList(lectureVO);
    }

    public void openlectureBookDelete(OpenLectureVO openLectureVO){
        openLectureMapper.openlectureBookDelete(openLectureVO);
    }

    public void openlectureUpdate(LectureVO lectureVO){
        openLectureMapper.openlectureUpdate(lectureVO);
    }

    public void openlectureIsUseUpdate(LectureVO lectureVO){
        openLectureMapper.openlectureIsUseUpdate(lectureVO);
    }

    public void openlectureDelete(OpenLectureVO openLectureVO){
        openLectureMapper.openlectureDelete(openLectureVO);
    }

    public void openlectureBridgeDelete(OpenLectureVO openLectureVO){
        openLectureMapper.openlectureBridgeDelete(openLectureVO);
    }

    public void lecMovUpdate(LectureVO lectureVO){
        openLectureMapper.lecMovUpdate(lectureVO);
    }

    public List<HashMap<String, String>> openlectureSeqList(LectureVO lectureVO){
        return openLectureMapper.openlectureSeqList(lectureVO);
    }

    public void openlectureSeqUpdate(LectureVO lectureVO){
        openLectureMapper.openlectureSeqUpdate(lectureVO);
    }

    public void Modify_Lecture_On_Off(LectureVO lectureVO){
        openLectureMapper.Modify_Lecture_On_Off(lectureVO);
    }

    public List<HashMap<String, String>> openlectureViewJongList(LectureVO lectureVO){
        return openLectureMapper.openlectureViewJongList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureJongList(LectureVO lectureVO){
        return openLectureMapper.openlectureJongList(lectureVO);
    }

    public int openlectureJongListCount(LectureVO lectureVO){
        return openLectureMapper.openlectureJongListCount(lectureVO);
    }

    public List<HashMap<String, String>> openlectureJongView(LectureVO lectureVO){
        return openLectureMapper.openlectureJongView(lectureVO);
    }

    public List<HashMap<String, String>> openlectureJongSubjectList(LectureVO lectureVO){
        return openLectureMapper.openlectureJongSubjectList(lectureVO);
    }

    public int openlectureJongSubjectListCount(LectureVO lectureVO){
        return openLectureMapper.openlectureJongSubjectListCount(lectureVO);
    }

    public void openlectureLecJongInsert(LectureVO lectureVO){
        openLectureMapper.openlectureLecJongInsert(lectureVO);
    }

    public void openlectureChoiceJongNoInsert(LectureVO lectureVO){
        openLectureMapper.openlectureChoiceJongNoInsert(lectureVO);
    }

    public List<HashMap<String, String>> openlectureViewLeccodeList(LectureVO lectureVO){
        return openLectureMapper.openlectureViewLeccodeList(lectureVO);
    }

    public void openlectureLecJongDelete(LectureVO lectureVO){
        openLectureMapper.openlectureLecJongDelete(lectureVO);
    }

    public void openlectureChoiceJongNoDelete(LectureVO lectureVO){
        openLectureMapper.openlectureChoiceJongNoDelete(lectureVO);
    }

    public List<HashMap<String, String>> openlecturePayList(LectureVO lectureVO){
        return openLectureMapper.openlecturePayList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureJongPayList(LectureVO lectureVO){
        return openLectureMapper.openlectureJongPayList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureDataMemoViewList(LectureVO lectureVO){
        return openLectureMapper.openlectureDataMemoViewList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureDataViewList(LectureVO lectureVO){
        return openLectureMapper.openlectureDataViewList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureDataMovieViewList(LectureVO lectureVO){
        return openLectureMapper.openlectureDataMovieViewList(lectureVO);
    }

    public List<HashMap<String, String>> openlectureDataMovieList(LectureVO lectureVO){
        return openLectureMapper.openlectureDataMovieList(lectureVO);
    }

    public void openlectureMovieInsert(LectureVO lectureVO){
        openLectureMapper.openlectureMovieInsert(lectureVO);
    }

    public void openlectureMovieDelete(LectureVO lectureVO){
        openLectureMapper.openlectureMovieDelete(lectureVO);
    }

    public void openlectureMovieUpdate(LectureVO lectureVO){
        openLectureMapper.openlectureMovieUpdate(lectureVO);
    }

    public void openlectureMovieFileDelete(LectureVO lectureVO){
        openLectureMapper.openlectureMovieFileDelete(lectureVO);
    }

    public void openlectureMovieMemoInsert(LectureVO lectureVO){
        openLectureMapper.openlectureMovieMemoInsert(lectureVO);
    }

    public void openlectureMovieMemoUpdate(LectureVO lectureVO){
        openLectureMapper.openlectureMovieMemoUpdate(lectureVO);
    }

    public void openlectureMovieMemoDelete(LectureVO lectureVO){
        openLectureMapper.openlectureMovieMemoDelete(lectureVO);
    }

    public int openlectureDeleteCheck(LectureVO lectureVO){
        return openLectureMapper.openlectureDeleteCheck(lectureVO);
    }

    public List<HashMap<String, String>> playinfo(LectureVO lectureVO){
        return openLectureMapper.playinfo(lectureVO);
    }

    public List<HashMap<String, String>> getCbMovie4_free_admin(LectureVO lectureVO){
        return openLectureMapper.getCbMovie4_free_admin(lectureVO);
    }

    public int getCbMovie4_free_admin_count(LectureVO lectureVO){
        return openLectureMapper.getCbMovie4_free_admin_count(lectureVO);
    }

    public HashMap<String, String> openlectureOnDetailS(LectureVO lectureVO) {
        return openLectureMapper.openlectureOnDetailS(lectureVO);
    }

    public void insertPmpDownLog(LectureVO lectureVO){
        openLectureMapper.insertPmpDownLog(lectureVO);
    }

    public List<HashMap<String, String>> openlectureWMV(LectureVO lectureVO){
        return openLectureMapper.openlectureWMV(lectureVO);
    }

    public List<HashMap<String, String>> openlectureDown_Count(LectureVO lectureVO){
        return openLectureMapper.openlectureDown_Count(lectureVO);
    }
}