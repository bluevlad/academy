package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.counsel.service.CounselRequestVO;
import com.academy.counsel.service.CounselScheduleVO;
import com.academy.counsel.service.PresentRequestVO;

/**
 * 상담 관리에 관한 데이터 접근 클래스를 정의한다.
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
public interface CounselMapper {

    // =====================================================
    // 상담 일정 (COUNSEL_SCH) 관련
    // =====================================================

    /**
     * 상담 일정 일자 목록 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 일정 일자 목록
     */
    List<JSONObject> selectScheduleDayList(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 일정 상세 목록 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 일정 상세 목록
     */
    List<JSONObject> selectScheduleList(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 일정 테이블 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 일정 테이블
     */
    List<JSONObject> selectScheduleTable(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 시간 테이블 조회
     * @param counselScheduleVO 검색조건
     * @return List 상담 시간 테이블
     */
    List<JSONObject> selectTimeTable(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 일정 중복 건수 조회
     * @param counselScheduleVO 검색조건
     * @return int 중복 건수
     */
    int selectScheduleCount(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 일정 등록
     * @param counselScheduleVO 상담 일정 정보
     */
    void insertSchedule(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 일정 수정
     * @param counselScheduleVO 상담 일정 정보
     */
    void updateSchedule(CounselScheduleVO counselScheduleVO);

    /**
     * 상담 일정 삭제
     * @param counselScheduleVO 상담 일정 정보
     */
    void deleteSchedule(CounselScheduleVO counselScheduleVO);

    // =====================================================
    // 상담 신청 (COUNSEL_RST) 관련
    // =====================================================

    /**
     * 상담 신청 목록 조회
     * @param counselRequestVO 검색조건
     * @return List 상담 신청 목록
     */
    List<JSONObject> selectCounselRequestList(CounselRequestVO counselRequestVO);

    /**
     * 상담 신청 목록 건수 조회
     * @param counselRequestVO 검색조건
     * @return int 상담 신청 목록 건수
     */
    int selectCounselRequestListCount(CounselRequestVO counselRequestVO);

    /**
     * 상담 신청 명단 조회 (예약 현황)
     * @param counselRequestVO 검색조건
     * @return List 상담 신청 명단
     */
    List<JSONObject> selectCounselReqList(CounselRequestVO counselRequestVO);

    /**
     * 개인 상담 신청 내역 조회
     * @param counselRequestVO 검색조건
     * @return List 개인 상담 신청 내역
     */
    List<JSONObject> selectCounselUserReq(CounselRequestVO counselRequestVO);

    // =====================================================
    // 설명회 신청 (TB_PRESENT) 관련
    // =====================================================

    /**
     * 설명회 신청 목록 조회
     * @param presentRequestVO 검색조건
     * @return List 설명회 신청 목록
     */
    List<JSONObject> selectPresentReqList(PresentRequestVO presentRequestVO);

    /**
     * 설명회 신청 목록 건수 조회
     * @param presentRequestVO 검색조건
     * @return int 설명회 신청 목록 건수
     */
    int selectPresentReqListCount(PresentRequestVO presentRequestVO);

    /**
     * 설명회 코드 목록 조회
     * @param presentRequestVO 검색조건
     * @return List 설명회 코드 목록
     */
    List<JSONObject> selectPresentCodeList(PresentRequestVO presentRequestVO);
}
