package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lectureOff.service.LectureOffVO;

/**
 * 오프라인 강의 관리 Mapper
 */
@Mapper
public interface LectureOffMapper {

    // === 단과 강의 관리 ===
    /**
     * 단과 강의 목록 조회
     */
    List<LectureOffVO> selectLectureList(LectureOffVO vo);

    /**
     * 단과 강의 목록 카운트
     */
    int selectLectureListCount(LectureOffVO vo);

    /**
     * 단과 강의 상세 조회
     */
    LectureOffVO selectLectureDetail(LectureOffVO vo);

    /**
     * 단과 강의 등록
     */
    int insertLecture(LectureOffVO vo);

    /**
     * 단과 강의 수정
     */
    int updateLecture(LectureOffVO vo);

    /**
     * 단과 강의 삭제
     */
    int deleteLecture(LectureOffVO vo);

    // === 강의 브릿지 관리 ===
    /**
     * 강의 브릿지 목록 조회
     */
    List<LectureOffVO> selectLectureBridgeList(LectureOffVO vo);

    /**
     * 강의 브릿지 등록
     */
    int insertLectureBridge(LectureOffVO vo);

    /**
     * 강의 브릿지 삭제
     */
    int deleteLectureBridge(LectureOffVO vo);

    /**
     * 강의 브릿지 전체 삭제 (강의코드 기준)
     */
    int deleteLectureBridgeByLeccode(LectureOffVO vo);

    // === 강의 교재 관리 ===
    /**
     * 강의 교재 목록 조회
     */
    List<LectureOffVO> selectLectureBookList(LectureOffVO vo);

    /**
     * 교재 검색 목록 조회
     */
    List<LectureOffVO> selectBookSearchList(LectureOffVO vo);

    /**
     * 강의 교재 등록
     */
    int insertLectureBook(LectureOffVO vo);

    /**
     * 강의 교재 삭제
     */
    int deleteLectureBook(LectureOffVO vo);

    /**
     * 강의 교재 전체 삭제 (강의코드 기준)
     */
    int deleteLectureBookByLeccode(LectureOffVO vo);

    // === 강의 일정 관리 ===
    /**
     * 강의 일정 목록 조회
     */
    List<LectureOffVO> selectLectureDateList(LectureOffVO vo);

    /**
     * 강의 일정 등록
     */
    int insertLectureDate(LectureOffVO vo);

    /**
     * 강의 일정 삭제
     */
    int deleteLectureDate(LectureOffVO vo);

    /**
     * 강의 일정 전체 삭제 (강의코드 기준)
     */
    int deleteLectureDateByLeccode(LectureOffVO vo);

    // === 종합반 강의 관리 ===
    /**
     * 종합반 강의 목록 조회
     */
    List<LectureOffVO> selectJongLectureList(LectureOffVO vo);

    /**
     * 종합반 강의 목록 카운트
     */
    int selectJongLectureListCount(LectureOffVO vo);

    /**
     * 종합반 강의 상세 조회
     */
    LectureOffVO selectJongLectureDetail(LectureOffVO vo);

    /**
     * 종합반 강의 등록
     */
    int insertJongLecture(LectureOffVO vo);

    /**
     * 종합반 강의 수정
     */
    int updateJongLecture(LectureOffVO vo);

    /**
     * 종합반 강의 삭제
     */
    int deleteJongLecture(LectureOffVO vo);

    // === 종합반 강의 구성 관리 ===
    /**
     * 종합반 강의 구성 목록 조회 (단과 강의 목록)
     */
    List<LectureOffVO> selectJongLectureDetailList(LectureOffVO vo);

    /**
     * 종합반 강의 구성 등록
     */
    int insertJongLectureDetail(LectureOffVO vo);

    /**
     * 종합반 강의 구성 삭제
     */
    int deleteJongLectureDetail(LectureOffVO vo);

    /**
     * 종합반 강의 구성 전체 삭제 (종합반 SEQ 기준)
     */
    int deleteJongLectureDetailByJongseq(LectureOffVO vo);

    // === 선택형 종합반 관리 ===
    /**
     * 선택형 종합반 목록 조회
     */
    List<LectureOffVO> selectChoiceJongList(LectureOffVO vo);

    /**
     * 선택형 종합반 등록
     */
    int insertChoiceJong(LectureOffVO vo);

    /**
     * 선택형 종합반 삭제
     */
    int deleteChoiceJong(LectureOffVO vo);

    // === 카테고리/과목 조회 ===
    /**
     * 카테고리 목록 조회
     */
    List<LectureOffVO> selectCategoryList(LectureOffVO vo);

    /**
     * 과목 목록 조회
     */
    List<LectureOffVO> selectSubjectList(LectureOffVO vo);

    /**
     * 강사 목록 조회
     */
    List<LectureOffVO> selectTeacherList(LectureOffVO vo);

    // === 강의코드 생성 ===
    /**
     * 다음 강의코드 조회
     */
    String selectNextLeccode(LectureOffVO vo);

    /**
     * 다음 종합반 SEQ 조회
     */
    int selectNextJongseq();
}
