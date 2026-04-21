package com.academy.lectureOff.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.LectureOffMapper;

/**
 * 오프라인 강의 관리 서비스
 */
@Service
public class LectureOffService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LectureOffMapper lectureOffMapper;

    @Autowired
    public LectureOffService(LectureOffMapper lectureOffMapper) {
        this.lectureOffMapper = lectureOffMapper;
    }

    // === 단과 강의 관리 ===
    public List<LectureOffVO> getLectureList(LectureOffVO vo) {
        return lectureOffMapper.selectLectureList(vo);
    }

    public int getLectureListCount(LectureOffVO vo) {
        return lectureOffMapper.selectLectureListCount(vo);
    }

    public LectureOffVO getLectureDetail(LectureOffVO vo) {
        return lectureOffMapper.selectLectureDetail(vo);
    }

    @Transactional
    public int insertLecture(LectureOffVO vo) {
        return lectureOffMapper.insertLecture(vo);
    }

    @Transactional
    public int updateLecture(LectureOffVO vo) {
        return lectureOffMapper.updateLecture(vo);
    }

    @Transactional
    public int deleteLecture(LectureOffVO vo) {
        // 관련 데이터 삭제
        lectureOffMapper.deleteLectureBridgeByLeccode(vo);
        lectureOffMapper.deleteLectureBookByLeccode(vo);
        lectureOffMapper.deleteLectureDateByLeccode(vo);
        // 강의 삭제
        return lectureOffMapper.deleteLecture(vo);
    }

    // === 강의 브릿지 관리 ===
    public List<LectureOffVO> getLectureBridgeList(LectureOffVO vo) {
        return lectureOffMapper.selectLectureBridgeList(vo);
    }

    @Transactional
    public int insertLectureBridge(LectureOffVO vo) {
        return lectureOffMapper.insertLectureBridge(vo);
    }

    @Transactional
    public int deleteLectureBridge(LectureOffVO vo) {
        return lectureOffMapper.deleteLectureBridge(vo);
    }

    @Transactional
    public void saveLectureBridgeList(LectureOffVO vo, List<LectureOffVO> bridgeList) {
        // 기존 브릿지 삭제
        lectureOffMapper.deleteLectureBridgeByLeccode(vo);
        // 새 브릿지 등록
        for (LectureOffVO bridge : bridgeList) {
            bridge.setLeccode(vo.getLeccode());
            lectureOffMapper.insertLectureBridge(bridge);
        }
    }

    // === 강의 교재 관리 ===
    public List<LectureOffVO> getLectureBookList(LectureOffVO vo) {
        return lectureOffMapper.selectLectureBookList(vo);
    }

    public List<LectureOffVO> getBookSearchList(LectureOffVO vo) {
        return lectureOffMapper.selectBookSearchList(vo);
    }

    @Transactional
    public int insertLectureBook(LectureOffVO vo) {
        return lectureOffMapper.insertLectureBook(vo);
    }

    @Transactional
    public int deleteLectureBook(LectureOffVO vo) {
        return lectureOffMapper.deleteLectureBook(vo);
    }

    @Transactional
    public void saveLectureBookList(LectureOffVO vo, List<LectureOffVO> bookList) {
        // 기존 교재 삭제
        lectureOffMapper.deleteLectureBookByLeccode(vo);
        // 새 교재 등록
        for (LectureOffVO book : bookList) {
            book.setLeccode(vo.getLeccode());
            lectureOffMapper.insertLectureBook(book);
        }
    }

    // === 강의 일정 관리 ===
    public List<LectureOffVO> getLectureDateList(LectureOffVO vo) {
        return lectureOffMapper.selectLectureDateList(vo);
    }

    @Transactional
    public int insertLectureDate(LectureOffVO vo) {
        return lectureOffMapper.insertLectureDate(vo);
    }

    @Transactional
    public int deleteLectureDate(LectureOffVO vo) {
        return lectureOffMapper.deleteLectureDate(vo);
    }

    @Transactional
    public void saveLectureDateList(LectureOffVO vo, List<LectureOffVO> dateList) {
        // 기존 일정 삭제
        lectureOffMapper.deleteLectureDateByLeccode(vo);
        // 새 일정 등록
        for (LectureOffVO date : dateList) {
            date.setLeccode(vo.getLeccode());
            lectureOffMapper.insertLectureDate(date);
        }
    }

    // === 종합반 강의 관리 ===
    public List<LectureOffVO> getJongLectureList(LectureOffVO vo) {
        return lectureOffMapper.selectJongLectureList(vo);
    }

    public int getJongLectureListCount(LectureOffVO vo) {
        return lectureOffMapper.selectJongLectureListCount(vo);
    }

    public LectureOffVO getJongLectureDetail(LectureOffVO vo) {
        return lectureOffMapper.selectJongLectureDetail(vo);
    }

    @Transactional
    public int insertJongLecture(LectureOffVO vo) {
        return lectureOffMapper.insertJongLecture(vo);
    }

    @Transactional
    public int updateJongLecture(LectureOffVO vo) {
        return lectureOffMapper.updateJongLecture(vo);
    }

    @Transactional
    public int deleteJongLecture(LectureOffVO vo) {
        // 관련 구성 데이터 삭제
        lectureOffMapper.deleteJongLectureDetailByJongseq(vo);
        // 종합반 삭제
        return lectureOffMapper.deleteJongLecture(vo);
    }

    // === 종합반 강의 구성 관리 ===
    public List<LectureOffVO> getJongLectureDetailList(LectureOffVO vo) {
        return lectureOffMapper.selectJongLectureDetailList(vo);
    }

    @Transactional
    public int insertJongLectureDetail(LectureOffVO vo) {
        return lectureOffMapper.insertJongLectureDetail(vo);
    }

    @Transactional
    public int deleteJongLectureDetail(LectureOffVO vo) {
        return lectureOffMapper.deleteJongLectureDetail(vo);
    }

    @Transactional
    public void saveJongLectureDetailList(LectureOffVO vo, List<LectureOffVO> detailList) {
        // 기존 구성 삭제
        lectureOffMapper.deleteJongLectureDetailByJongseq(vo);
        // 새 구성 등록
        int sort = 1;
        for (LectureOffVO detail : detailList) {
            detail.setJongseq(vo.getJongseq());
            detail.setSort(sort++);
            lectureOffMapper.insertJongLectureDetail(detail);
        }
    }

    // === 선택형 종합반 관리 ===
    public List<LectureOffVO> getChoiceJongList(LectureOffVO vo) {
        return lectureOffMapper.selectChoiceJongList(vo);
    }

    @Transactional
    public int insertChoiceJong(LectureOffVO vo) {
        return lectureOffMapper.insertChoiceJong(vo);
    }

    @Transactional
    public int deleteChoiceJong(LectureOffVO vo) {
        return lectureOffMapper.deleteChoiceJong(vo);
    }

    // === 카테고리/과목/강사 조회 ===
    public List<LectureOffVO> getCategoryList(LectureOffVO vo) {
        return lectureOffMapper.selectCategoryList(vo);
    }

    public List<LectureOffVO> getSubjectList(LectureOffVO vo) {
        return lectureOffMapper.selectSubjectList(vo);
    }

    public List<LectureOffVO> getTeacherList(LectureOffVO vo) {
        return lectureOffMapper.selectTeacherList(vo);
    }

    // === 강의코드 생성 ===
    public String getNextLeccode(LectureOffVO vo) {
        return lectureOffMapper.selectNextLeccode(vo);
    }

    public int getNextJongseq() {
        return lectureOffMapper.selectNextJongseq();
    }
}
