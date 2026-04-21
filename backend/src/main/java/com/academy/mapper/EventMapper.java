package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.event.service.EventFileVO;
import com.academy.event.service.EventOptionVO;
import com.academy.event.service.EventResultVO;
import com.academy.event.service.EventVO;
import com.academy.event.service.LecEventVO;

/**
 * 이벤트 관리에 관한 데이터 접근 클래스를 정의한다.
 * @author system
 * @since 2025.12.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.11         system          신규 생성
 * </pre>
 */
@Mapper
public interface EventMapper {

    // =====================================================
    // 이벤트 정보 (TB_EVENT_INFO) 관련
    // =====================================================

    /**
     * 이벤트 목록 조회
     * @param eventVO 검색조건
     * @return List 이벤트 목록
     */
    List<JSONObject> selectEventList(EventVO eventVO);

    /**
     * 이벤트 목록 건수 조회
     * @param eventVO 검색조건
     * @return int 이벤트 목록 건수
     */
    int selectEventListCount(EventVO eventVO);

    /**
     * 이벤트 상세 조회
     * @param eventVO 검색조건
     * @return JSONObject 이벤트 상세 정보
     */
    JSONObject selectEventDetail(EventVO eventVO);

    /**
     * 이벤트 등록
     * @param eventVO 이벤트 정보
     */
    void insertEvent(EventVO eventVO);

    /**
     * 이벤트 수정
     * @param eventVO 이벤트 정보
     */
    void updateEvent(EventVO eventVO);

    /**
     * 이벤트 삭제
     * @param eventVO 이벤트 정보
     */
    void deleteEvent(EventVO eventVO);

    // =====================================================
    // 이벤트 파일 (TB_EVENT_FILE) 관련
    // =====================================================

    /**
     * 이벤트 파일 목록 조회
     * @param eventFileVO 검색조건
     * @return List 이벤트 파일 목록
     */
    List<JSONObject> selectEventFileList(EventFileVO eventFileVO);

    /**
     * 이벤트 파일 등록
     * @param eventFileVO 이벤트 파일 정보
     */
    void insertEventFile(EventFileVO eventFileVO);

    /**
     * 이벤트 파일 삭제
     * @param eventFileVO 이벤트 파일 정보
     */
    void deleteEventFile(EventFileVO eventFileVO);

    // =====================================================
    // 이벤트 옵션1 (TB_EVENT_OPTION1) 관련
    // =====================================================

    /**
     * 이벤트 옵션1 목록 조회
     * @param eventOptionVO 검색조건
     * @return List 이벤트 옵션1 목록
     */
    List<JSONObject> selectEventOption1List(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션1 등록
     * @param eventOptionVO 이벤트 옵션1 정보
     */
    void insertEventOption1(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션1 삭제
     * @param eventOptionVO 이벤트 옵션1 정보
     */
    void deleteEventOption1(EventOptionVO eventOptionVO);

    // =====================================================
    // 이벤트 옵션2 (TB_EVENT_OPTION2) - 댓글 관련
    // =====================================================

    /**
     * 이벤트 옵션2(댓글) 목록 조회
     * @param eventOptionVO 검색조건
     * @return List 이벤트 옵션2 목록
     */
    List<JSONObject> selectEventOption2List(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션2(댓글) 전체 목록 조회
     * @param eventOptionVO 검색조건
     * @return List 이벤트 옵션2 전체 목록
     */
    List<JSONObject> selectEventOption2ListAll(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션2(댓글) 목록 건수 조회
     * @param eventOptionVO 검색조건
     * @return int 이벤트 옵션2 목록 건수
     */
    int selectEventOption2ListCount(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션2(댓글) 등록
     * @param eventOptionVO 이벤트 옵션2 정보
     */
    void insertEventOption2(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션2(댓글) 삭제
     * @param eventOptionVO 이벤트 옵션2 정보
     */
    void deleteEventOption2(EventOptionVO eventOptionVO);

    // =====================================================
    // 이벤트 옵션3 (TB_EVENT_OPTION3) - SMS 관련
    // =====================================================

    /**
     * 이벤트 옵션3(SMS) 등록
     * @param eventOptionVO 이벤트 옵션3 정보
     */
    void insertEventOption3(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션3(SMS) 삭제
     * @param eventOptionVO 이벤트 옵션3 정보
     */
    void deleteEventOption3(EventOptionVO eventOptionVO);

    // =====================================================
    // 이벤트 옵션4 (TB_EVENT_OPTION4) - 팝업 관련
    // =====================================================

    /**
     * 이벤트 옵션4(팝업) 등록
     * @param eventOptionVO 이벤트 옵션4 정보
     */
    void insertEventOption4(EventOptionVO eventOptionVO);

    /**
     * 이벤트 옵션4(팝업) 삭제
     * @param eventOptionVO 이벤트 옵션4 정보
     */
    void deleteEventOption4(EventOptionVO eventOptionVO);

    // =====================================================
    // 이벤트 결과/참여자 (TB_EVENT_RESULT) 관련
    // =====================================================

    /**
     * 이벤트 참여자 목록 조회
     * @param eventResultVO 검색조건
     * @return List 이벤트 참여자 목록
     */
    List<JSONObject> selectEventResultList(EventResultVO eventResultVO);

    /**
     * 이벤트 참여자 목록 건수 조회
     * @param eventResultVO 검색조건
     * @return int 이벤트 참여자 목록 건수
     */
    int selectEventResultListCount(EventResultVO eventResultVO);

    // =====================================================
    // 강의 이벤트 (TB_LECTURE_EVENT_INFO) 관련
    // =====================================================

    /**
     * 강의 이벤트 목록 조회
     * @param lecEventVO 검색조건
     * @return List 강의 이벤트 목록
     */
    List<JSONObject> selectLecEventList(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 목록 건수 조회
     * @param lecEventVO 검색조건
     * @return int 강의 이벤트 목록 건수
     */
    int selectLecEventListCount(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 상세 조회
     * @param lecEventVO 검색조건
     * @return JSONObject 강의 이벤트 상세 정보
     */
    JSONObject selectLecEventDetail(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 등록
     * @param lecEventVO 강의 이벤트 정보
     */
    void insertLecEvent(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 수정
     * @param lecEventVO 강의 이벤트 정보
     */
    void updateLecEvent(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 삭제
     * @param lecEventVO 강의 이벤트 정보
     */
    void deleteLecEvent(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 강좌 목록 조회
     * @param lecEventVO 검색조건
     * @return List 강의 이벤트 강좌 목록
     */
    List<JSONObject> selectLecEventLectureList(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 강좌 등록
     * @param lecEventVO 강의 이벤트 강좌 정보
     */
    void insertLecEventLecture(LecEventVO lecEventVO);

    /**
     * 강의 이벤트 강좌 삭제
     * @param lecEventVO 강의 이벤트 강좌 정보
     */
    void deleteLecEventLecture(LecEventVO lecEventVO);

    /**
     * 강좌 목록 조회 (이벤트용)
     * @param lecEventVO 검색조건
     * @return List 강좌 목록
     */
    List<JSONObject> selectLectureListForEvent(LecEventVO lecEventVO);

    /**
     * 강좌 목록 건수 조회 (이벤트용)
     * @param lecEventVO 검색조건
     * @return int 강좌 목록 건수
     */
    int selectLectureListForEventCount(LecEventVO lecEventVO);
}
