package com.academy.board.service;


import com.academy.common.CommonVO;

/**
 * 게시판 관리 VO 클래스 (TB_BOARD_MNG 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            게시판 관리 등록
 * </pre>
 */
public class BoardMngVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 게시판관리 SEQ (Primary Key) */
    private String boardMngSeq;

    /** 게시판명 */
    private String boardMngName;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인, T: 강사용) */
    private String onoffDiv;

    /** 온/오프라인 구분명 (조회용) */
    private String onoffDivNm;

    /** 게시판 타입 (BOARD: 일반게시판, NOTICE: 공지사항, FAQ: FAQ, TCC: 강사상담) */
    private String boardMngType;

    /** 게시판 타입명 (조회용) */
    private String boardMngTypeNm;

    /** 첨부파일 사용 여부 */
    private String attachFileYn;

    /** 공개 여부 (Y: 로그인, N: 비로그인) */
    private String openYn;

    /** 공개 여부명 (조회용) */
    private String openYnNm;

    /** 답변 사용 여부 */
    private String replyYn;

    /** 사용여부명 (조회용) */
    private String isUseNm;

    /** 게시물 수 (조회용) */
    private int cnt;

    /** 오늘 등록 게시물 수 (조회용) */
    private int today;

    /* 검색 조건 */
    /** 검색 타입 (BOARD_MNG_SEQ, BOARD_MNG_NAME) */
    private String searchType;

    /** 검색 키워드 */
    private String searchKeyword;

    /** 검색 온/오프라인 구분 */
    private String searchOnoffDiv;

    /** 상세 조회용 SEQ */
    private String detailBoardMngSeq;

    /** 일괄 삭제용 ID 목록 */
    private String deleteIds;

    /** 첨부파일 사용 여부명 (조회용) */
    private String attachFileYnNm;

    /** 답변 사용 여부명 (조회용) */
    private String replyYnNm;

    // Getters and Setters
    public String getBoardMngSeq() {
        return boardMngSeq;
    }

    public void setBoardMngSeq(String boardMngSeq) {
        this.boardMngSeq = boardMngSeq;
    }

    public String getBoardMngName() {
        return boardMngName;
    }

    public void setBoardMngName(String boardMngName) {
        this.boardMngName = boardMngName;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getOnoffDivNm() {
        return onoffDivNm;
    }

    public void setOnoffDivNm(String onoffDivNm) {
        this.onoffDivNm = onoffDivNm;
    }

    public String getBoardMngType() {
        return boardMngType;
    }

    public void setBoardMngType(String boardMngType) {
        this.boardMngType = boardMngType;
    }

    public String getBoardMngTypeNm() {
        return boardMngTypeNm;
    }

    public void setBoardMngTypeNm(String boardMngTypeNm) {
        this.boardMngTypeNm = boardMngTypeNm;
    }

    public String getAttachFileYn() {
        return attachFileYn;
    }

    public void setAttachFileYn(String attachFileYn) {
        this.attachFileYn = attachFileYn;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getOpenYnNm() {
        return openYnNm;
    }

    public void setOpenYnNm(String openYnNm) {
        this.openYnNm = openYnNm;
    }

    public String getReplyYn() {
        return replyYn;
    }

    public void setReplyYn(String replyYn) {
        this.replyYn = replyYn;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getToday() {
        return today;
    }

    public void setToday(int today) {
        this.today = today;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchOnoffDiv() {
        return searchOnoffDiv;
    }

    public void setSearchOnoffDiv(String searchOnoffDiv) {
        this.searchOnoffDiv = searchOnoffDiv;
    }

    public String getDetailBoardMngSeq() {
        return detailBoardMngSeq;
    }

    public void setDetailBoardMngSeq(String detailBoardMngSeq) {
        this.detailBoardMngSeq = detailBoardMngSeq;
    }

    public String getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(String deleteIds) {
        this.deleteIds = deleteIds;
    }

    public String getAttachFileYnNm() {
        return attachFileYnNm;
    }

    public void setAttachFileYnNm(String attachFileYnNm) {
        this.attachFileYnNm = attachFileYnNm;
    }

    public String getReplyYnNm() {
        return replyYnNm;
    }

    public void setReplyYnNm(String replyYnNm) {
        this.replyYnNm = replyYnNm;
    }
}
