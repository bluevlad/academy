package com.academy.coop.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.CoopMapper;

/**
 * 제휴사 관리 Service 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            제휴사 관리 신규 생성
 * </pre>
 */
@Service
public class CoopService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CoopMapper coopMapper;

    public CoopService(CoopMapper coopMapper) {
        this.coopMapper = coopMapper;
    }

    // =====================================================
    // 제휴사 마스터 (COOP_MST) 관련
    // =====================================================

    /**
     * 제휴사 목록 조회
     * @param coopVO 검색조건
     * @return List 제휴사 목록
     */
    public List<JSONObject> selectCoopList(CoopVO coopVO) {
        return coopMapper.selectCoopList(coopVO);
    }

    /**
     * 제휴사 목록 건수 조회
     * @param coopVO 검색조건
     * @return int 제휴사 목록 건수
     */
    public int selectCoopListCount(CoopVO coopVO) {
        return coopMapper.selectCoopListCount(coopVO);
    }

    /**
     * 제휴사 상세 조회
     * @param coopVO 검색조건
     * @return JSONObject 제휴사 상세정보
     */
    public JSONObject selectCoopDetail(CoopVO coopVO) {
        return coopMapper.selectCoopDetail(coopVO);
    }

    /**
     * 제휴사 등록
     * @param coopVO 제휴사정보
     */
    public void insertCoop(CoopVO coopVO) {
        coopMapper.insertCoop(coopVO);
    }

    /**
     * 제휴사 수정
     * @param coopVO 제휴사정보
     */
    public void updateCoop(CoopVO coopVO) {
        coopMapper.updateCoop(coopVO);
    }

    /**
     * 제휴사 삭제
     * @param coopVO 제휴사정보
     */
    public void deleteCoop(CoopVO coopVO) {
        coopMapper.deleteCoop(coopVO);
    }

    // =====================================================
    // 제휴사 IP (COOP_USE_IP) 관련
    // =====================================================

    /**
     * 제휴사 IP 목록 조회
     * @param coopIpVO 검색조건
     * @return List 제휴사 IP 목록
     */
    public List<JSONObject> selectCoopIpList(CoopIpVO coopIpVO) {
        return coopMapper.selectCoopIpList(coopIpVO);
    }

    /**
     * 제휴사 IP 등록
     * @param coopIpVO 제휴사 IP 정보
     */
    public void insertCoopIp(CoopIpVO coopIpVO) {
        coopMapper.insertCoopIp(coopIpVO);
    }

    /**
     * 제휴사 IP 삭제
     * @param coopIpVO 제휴사 IP 정보
     */
    public void deleteCoopIp(CoopIpVO coopIpVO) {
        coopMapper.deleteCoopIp(coopIpVO);
    }

    // =====================================================
    // 제휴사 게시판 (TB_BOARD_MEMBERSHIP) 관련
    // =====================================================

    /**
     * 제휴사 게시판 목록 조회
     * @param coopBoardVO 검색조건
     * @return List 제휴사 게시판 목록
     */
    public List<JSONObject> selectCoopBoardList(CoopBoardVO coopBoardVO) {
        return coopMapper.selectCoopBoardList(coopBoardVO);
    }

    /**
     * 제휴사 게시판 목록 건수 조회
     * @param coopBoardVO 검색조건
     * @return int 제휴사 게시판 목록 건수
     */
    public int selectCoopBoardListCount(CoopBoardVO coopBoardVO) {
        return coopMapper.selectCoopBoardListCount(coopBoardVO);
    }

    /**
     * 제휴사 게시판 상세 조회
     * @param coopBoardVO 검색조건
     * @return JSONObject 제휴사 게시판 상세
     */
    public JSONObject selectCoopBoardDetail(CoopBoardVO coopBoardVO) {
        return coopMapper.selectCoopBoardDetail(coopBoardVO);
    }

    /**
     * 제휴사 게시판 등록
     * @param coopBoardVO 제휴사 게시판 정보
     */
    public void insertCoopBoard(CoopBoardVO coopBoardVO) {
        coopMapper.insertCoopBoard(coopBoardVO);
    }

    /**
     * 제휴사 게시판 수정
     * @param coopBoardVO 제휴사 게시판 정보
     */
    public void updateCoopBoard(CoopBoardVO coopBoardVO) {
        coopMapper.updateCoopBoard(coopBoardVO);
    }

    /**
     * 제휴사 게시판 삭제
     * @param coopBoardVO 제휴사 게시판 정보
     */
    public void deleteCoopBoard(CoopBoardVO coopBoardVO) {
        coopMapper.deleteCoopBoard(coopBoardVO);
    }

    /**
     * 제휴사 게시판 정렬순서 수정
     * @param coopBoardVO 제휴사 게시판 정보
     */
    public void updateCoopBoardRank(CoopBoardVO coopBoardVO) {
        coopMapper.updateCoopBoardRank(coopBoardVO);
    }

    /**
     * 제휴사 게시판 파일 삭제 (파일 경로 초기화)
     * @param coopBoardVO 제휴사 게시판 정보
     */
    public void updateCoopBoardDeleteFile(CoopBoardVO coopBoardVO) {
        coopMapper.updateCoopBoardDeleteFile(coopBoardVO);
    }

    /**
     * 제휴사 코드 목록 조회
     * @param coopBoardVO 검색조건
     * @return List 코드 목록
     */
    public List<JSONObject> selectCoopCodeList(CoopBoardVO coopBoardVO) {
        return coopMapper.selectCoopCodeList(coopBoardVO);
    }

    // =====================================================
    // 제휴사 주문 관련
    // =====================================================

    /**
     * 제휴사 주문 목록 조회
     * @param coopOrderVO 검색조건
     * @return List 제휴사 주문 목록
     */
    public List<JSONObject> selectCoopOrderList(CoopOrderVO coopOrderVO) {
        return coopMapper.selectCoopOrderList(coopOrderVO);
    }

    /**
     * 제휴사 주문 목록 건수 조회
     * @param coopOrderVO 검색조건
     * @return int 제휴사 주문 목록 건수
     */
    public int selectCoopOrderListCount(CoopOrderVO coopOrderVO) {
        return coopMapper.selectCoopOrderListCount(coopOrderVO);
    }

    /**
     * 제휴사 결제 상세 목록 조회
     * @param coopOrderVO 검색조건
     * @return List 결제 상세 목록
     */
    public List<JSONObject> selectCoopPayDetailList(CoopOrderVO coopOrderVO) {
        return coopMapper.selectCoopPayDetailList(coopOrderVO);
    }
}
