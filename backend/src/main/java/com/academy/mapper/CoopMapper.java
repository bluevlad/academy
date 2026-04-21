package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.coop.service.CoopBoardVO;
import com.academy.coop.service.CoopIpVO;
import com.academy.coop.service.CoopOrderVO;
import com.academy.coop.service.CoopVO;

/**
 * 제휴사 관리에 관한 데이터 접근 클래스를 정의한다.
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
public interface CoopMapper {

    // =====================================================
    // 제휴사 마스터 (COOP_MST) 관련
    // =====================================================

    /**
     * 제휴사 목록 조회
     * @param coopVO 검색조건
     * @return List 제휴사 목록정보
     */
    List<JSONObject> selectCoopList(CoopVO coopVO);

    /**
     * 제휴사 목록 건수 조회
     * @param coopVO 검색조건
     * @return int 제휴사 목록 건수
     */
    int selectCoopListCount(CoopVO coopVO);

    /**
     * 제휴사 상세 조회
     * @param coopVO 검색조건
     * @return JSONObject 제휴사 상세정보
     */
    JSONObject selectCoopDetail(CoopVO coopVO);

    /**
     * 제휴사 등록
     * @param coopVO 제휴사정보
     */
    void insertCoop(CoopVO coopVO);

    /**
     * 제휴사 수정
     * @param coopVO 제휴사정보
     */
    void updateCoop(CoopVO coopVO);

    /**
     * 제휴사 삭제
     * @param coopVO 제휴사정보
     */
    void deleteCoop(CoopVO coopVO);

    // =====================================================
    // 제휴사 IP (COOP_USE_IP) 관련
    // =====================================================

    /**
     * 제휴사 IP 목록 조회
     * @param coopIpVO 검색조건
     * @return List 제휴사 IP 목록
     */
    List<JSONObject> selectCoopIpList(CoopIpVO coopIpVO);

    /**
     * 제휴사 IP 등록
     * @param coopIpVO 제휴사 IP 정보
     */
    void insertCoopIp(CoopIpVO coopIpVO);

    /**
     * 제휴사 IP 삭제
     * @param coopIpVO 제휴사 IP 정보
     */
    void deleteCoopIp(CoopIpVO coopIpVO);

    // =====================================================
    // 제휴사 게시판 (TB_BOARD_MEMBERSHIP) 관련
    // =====================================================

    /**
     * 제휴사 게시판 목록 조회
     * @param coopBoardVO 검색조건
     * @return List 제휴사 게시판 목록
     */
    List<JSONObject> selectCoopBoardList(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 목록 건수 조회
     * @param coopBoardVO 검색조건
     * @return int 제휴사 게시판 목록 건수
     */
    int selectCoopBoardListCount(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 상세 조회
     * @param coopBoardVO 검색조건
     * @return JSONObject 제휴사 게시판 상세
     */
    JSONObject selectCoopBoardDetail(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 등록
     * @param coopBoardVO 제휴사 게시판 정보
     */
    void insertCoopBoard(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 수정
     * @param coopBoardVO 제휴사 게시판 정보
     */
    void updateCoopBoard(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 삭제
     * @param coopBoardVO 제휴사 게시판 정보
     */
    void deleteCoopBoard(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 정렬순서 수정
     * @param coopBoardVO 제휴사 게시판 정보
     */
    void updateCoopBoardRank(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 게시판 파일 삭제 (파일 경로 초기화)
     * @param coopBoardVO 제휴사 게시판 정보
     */
    void updateCoopBoardDeleteFile(CoopBoardVO coopBoardVO);

    /**
     * 제휴사 코드 목록 조회
     * @param coopBoardVO 검색조건
     * @return List 코드 목록
     */
    List<JSONObject> selectCoopCodeList(CoopBoardVO coopBoardVO);

    // =====================================================
    // 제휴사 주문 관련
    // =====================================================

    /**
     * 제휴사 주문 목록 조회
     * @param coopOrderVO 검색조건
     * @return List 제휴사 주문 목록
     */
    List<JSONObject> selectCoopOrderList(CoopOrderVO coopOrderVO);

    /**
     * 제휴사 주문 목록 건수 조회
     * @param coopOrderVO 검색조건
     * @return int 제휴사 주문 목록 건수
     */
    int selectCoopOrderListCount(CoopOrderVO coopOrderVO);

    /**
     * 제휴사 결제 상세 목록 조회
     * @param coopOrderVO 검색조건
     * @return List 결제 상세 목록
     */
    List<JSONObject> selectCoopPayDetailList(CoopOrderVO coopOrderVO);
}
