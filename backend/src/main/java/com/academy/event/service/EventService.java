package com.academy.event.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.EventMapper;

/**
 * 이벤트 관리 Service 클래스
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
@Service
public class EventService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    // =====================================================
    // 이벤트 정보 (TB_EVENT_INFO) 관련
    // =====================================================

    /**
     * 이벤트 목록 조회
     * @param eventVO 검색조건
     * @return List 이벤트 목록
     */
    public List<JSONObject> selectEventList(EventVO eventVO) {
        return eventMapper.selectEventList(eventVO);
    }

    /**
     * 이벤트 목록 건수 조회
     * @param eventVO 검색조건
     * @return int 이벤트 목록 건수
     */
    public int selectEventListCount(EventVO eventVO) {
        return eventMapper.selectEventListCount(eventVO);
    }

    /**
     * 이벤트 상세 조회
     * @param eventVO 검색조건
     * @return JSONObject 이벤트 상세 정보
     */
    public JSONObject selectEventDetail(EventVO eventVO) {
        return eventMapper.selectEventDetail(eventVO);
    }

    /**
     * 이벤트 등록
     * @param eventVO 이벤트 정보
     */
    @Transactional
    public void insertEvent(EventVO eventVO) {
        eventMapper.insertEvent(eventVO);
    }

    /**
     * 이벤트 수정
     * @param eventVO 이벤트 정보
     */
    @Transactional
    public void updateEvent(EventVO eventVO) {
        eventMapper.updateEvent(eventVO);
    }

    /**
     * 이벤트 삭제
     * @param eventVO 이벤트 정보
     */
    @Transactional
    public void deleteEvent(EventVO eventVO) {
        eventMapper.deleteEvent(eventVO);
    }

    // =====================================================
    // 이벤트 파일 (TB_EVENT_FILE) 관련
    // =====================================================

    /**
     * 이벤트 파일 목록 조회
     * @param eventFileVO 검색조건
     * @return List 이벤트 파일 목록
     */
    public List<JSONObject> selectEventFileList(EventFileVO eventFileVO) {
        return eventMapper.selectEventFileList(eventFileVO);
    }

    /**
     * 이벤트 파일 등록
     * @param eventFileVO 이벤트 파일 정보
     */
    @Transactional
    public void insertEventFile(EventFileVO eventFileVO) {
        eventMapper.insertEventFile(eventFileVO);
    }

    /**
     * 이벤트 파일 삭제
     * @param eventFileVO 이벤트 파일 정보
     */
    @Transactional
    public void deleteEventFile(EventFileVO eventFileVO) {
        eventMapper.deleteEventFile(eventFileVO);
    }

    // =====================================================
    // 이벤트 옵션1 (TB_EVENT_OPTION1) 관련
    // =====================================================

    /**
     * 이벤트 옵션1 목록 조회
     * @param eventOptionVO 검색조건
     * @return List 이벤트 옵션1 목록
     */
    public List<JSONObject> selectEventOption1List(EventOptionVO eventOptionVO) {
        return eventMapper.selectEventOption1List(eventOptionVO);
    }

    /**
     * 이벤트 옵션1 등록
     * @param eventOptionVO 이벤트 옵션1 정보
     */
    @Transactional
    public void insertEventOption1(EventOptionVO eventOptionVO) {
        eventMapper.insertEventOption1(eventOptionVO);
    }

    /**
     * 이벤트 옵션1 삭제
     * @param eventOptionVO 이벤트 옵션1 정보
     */
    @Transactional
    public void deleteEventOption1(EventOptionVO eventOptionVO) {
        eventMapper.deleteEventOption1(eventOptionVO);
    }

    // =====================================================
    // 이벤트 옵션2 (TB_EVENT_OPTION2) - 댓글 관련
    // =====================================================

    /**
     * 이벤트 옵션2(댓글) 목록 조회
     * @param eventOptionVO 검색조건
     * @return List 이벤트 옵션2 목록
     */
    public List<JSONObject> selectEventOption2List(EventOptionVO eventOptionVO) {
        return eventMapper.selectEventOption2List(eventOptionVO);
    }

    /**
     * 이벤트 옵션2(댓글) 전체 목록 조회
     * @param eventOptionVO 검색조건
     * @return List 이벤트 옵션2 전체 목록
     */
    public List<JSONObject> selectEventOption2ListAll(EventOptionVO eventOptionVO) {
        return eventMapper.selectEventOption2ListAll(eventOptionVO);
    }

    /**
     * 이벤트 옵션2(댓글) 목록 건수 조회
     * @param eventOptionVO 검색조건
     * @return int 이벤트 옵션2 목록 건수
     */
    public int selectEventOption2ListCount(EventOptionVO eventOptionVO) {
        return eventMapper.selectEventOption2ListCount(eventOptionVO);
    }

    /**
     * 이벤트 옵션2(댓글) 등록
     * @param eventOptionVO 이벤트 옵션2 정보
     */
    @Transactional
    public void insertEventOption2(EventOptionVO eventOptionVO) {
        eventMapper.insertEventOption2(eventOptionVO);
    }

    /**
     * 이벤트 옵션2(댓글) 삭제
     * @param eventOptionVO 이벤트 옵션2 정보
     */
    @Transactional
    public void deleteEventOption2(EventOptionVO eventOptionVO) {
        eventMapper.deleteEventOption2(eventOptionVO);
    }

    // =====================================================
    // 이벤트 옵션3 (TB_EVENT_OPTION3) - SMS 관련
    // =====================================================

    /**
     * 이벤트 옵션3(SMS) 등록
     * @param eventOptionVO 이벤트 옵션3 정보
     */
    @Transactional
    public void insertEventOption3(EventOptionVO eventOptionVO) {
        eventMapper.insertEventOption3(eventOptionVO);
    }

    /**
     * 이벤트 옵션3(SMS) 삭제
     * @param eventOptionVO 이벤트 옵션3 정보
     */
    @Transactional
    public void deleteEventOption3(EventOptionVO eventOptionVO) {
        eventMapper.deleteEventOption3(eventOptionVO);
    }

    // =====================================================
    // 이벤트 옵션4 (TB_EVENT_OPTION4) - 팝업 관련
    // =====================================================

    /**
     * 이벤트 옵션4(팝업) 등록
     * @param eventOptionVO 이벤트 옵션4 정보
     */
    @Transactional
    public void insertEventOption4(EventOptionVO eventOptionVO) {
        eventMapper.insertEventOption4(eventOptionVO);
    }

    /**
     * 이벤트 옵션4(팝업) 삭제
     * @param eventOptionVO 이벤트 옵션4 정보
     */
    @Transactional
    public void deleteEventOption4(EventOptionVO eventOptionVO) {
        eventMapper.deleteEventOption4(eventOptionVO);
    }

    // =====================================================
    // 이벤트 결과/참여자 (TB_EVENT_RESULT) 관련
    // =====================================================

    /**
     * 이벤트 참여자 목록 조회
     * @param eventResultVO 검색조건
     * @return List 이벤트 참여자 목록
     */
    public List<JSONObject> selectEventResultList(EventResultVO eventResultVO) {
        return eventMapper.selectEventResultList(eventResultVO);
    }

    /**
     * 이벤트 참여자 목록 건수 조회
     * @param eventResultVO 검색조건
     * @return int 이벤트 참여자 목록 건수
     */
    public int selectEventResultListCount(EventResultVO eventResultVO) {
        return eventMapper.selectEventResultListCount(eventResultVO);
    }

    // =====================================================
    // 강의 이벤트 (TB_LECTURE_EVENT_INFO) 관련
    // =====================================================

    /**
     * 강의 이벤트 목록 조회
     * @param lecEventVO 검색조건
     * @return List 강의 이벤트 목록
     */
    public List<JSONObject> selectLecEventList(LecEventVO lecEventVO) {
        return eventMapper.selectLecEventList(lecEventVO);
    }

    /**
     * 강의 이벤트 목록 건수 조회
     * @param lecEventVO 검색조건
     * @return int 강의 이벤트 목록 건수
     */
    public int selectLecEventListCount(LecEventVO lecEventVO) {
        return eventMapper.selectLecEventListCount(lecEventVO);
    }

    /**
     * 강의 이벤트 상세 조회
     * @param lecEventVO 검색조건
     * @return JSONObject 강의 이벤트 상세 정보
     */
    public JSONObject selectLecEventDetail(LecEventVO lecEventVO) {
        return eventMapper.selectLecEventDetail(lecEventVO);
    }

    /**
     * 강의 이벤트 등록
     * @param lecEventVO 강의 이벤트 정보
     */
    @Transactional
    public void insertLecEvent(LecEventVO lecEventVO) {
        eventMapper.insertLecEvent(lecEventVO);
    }

    /**
     * 강의 이벤트 수정
     * @param lecEventVO 강의 이벤트 정보
     */
    @Transactional
    public void updateLecEvent(LecEventVO lecEventVO) {
        eventMapper.updateLecEvent(lecEventVO);
    }

    /**
     * 강의 이벤트 삭제
     * @param lecEventVO 강의 이벤트 정보
     */
    @Transactional
    public void deleteLecEvent(LecEventVO lecEventVO) {
        eventMapper.deleteLecEvent(lecEventVO);
    }

    /**
     * 강의 이벤트 강좌 목록 조회
     * @param lecEventVO 검색조건
     * @return List 강의 이벤트 강좌 목록
     */
    public List<JSONObject> selectLecEventLectureList(LecEventVO lecEventVO) {
        return eventMapper.selectLecEventLectureList(lecEventVO);
    }

    /**
     * 강의 이벤트 강좌 등록
     * @param lecEventVO 강의 이벤트 강좌 정보
     */
    @Transactional
    public void insertLecEventLecture(LecEventVO lecEventVO) {
        eventMapper.insertLecEventLecture(lecEventVO);
    }

    /**
     * 강의 이벤트 강좌 삭제
     * @param lecEventVO 강의 이벤트 강좌 정보
     */
    @Transactional
    public void deleteLecEventLecture(LecEventVO lecEventVO) {
        eventMapper.deleteLecEventLecture(lecEventVO);
    }

    /**
     * 강좌 목록 조회 (이벤트용)
     * @param lecEventVO 검색조건
     * @return List 강좌 목록
     */
    public List<JSONObject> selectLectureListForEvent(LecEventVO lecEventVO) {
        return eventMapper.selectLectureListForEvent(lecEventVO);
    }

    /**
     * 강좌 목록 건수 조회 (이벤트용)
     * @param lecEventVO 검색조건
     * @return int 강좌 목록 건수
     */
    public int selectLectureListForEventCount(LecEventVO lecEventVO) {
        return eventMapper.selectLectureListForEventCount(lecEventVO);
    }
}
