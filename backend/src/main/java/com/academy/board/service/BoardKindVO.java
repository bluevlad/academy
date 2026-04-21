package com.academy.board.service;

import java.io.Serializable;

/**
 * 게시판 종류 정보 VO 클래스 (TB_BOARD_MNG 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.22         system            게시판 종류 VO 생성
 * </pre>
 */
public class BoardKindVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 게시판관리 SEQ */
    private String boardMngSeq;

    /** 게시판명 */
    private String boardMngName;

    /** 온/오프라인 구분 */
    private String onoffDiv;

    /** 게시판 타입 */
    private String boardMngType;

    /** 첨부파일 사용 여부 */
    private String attachFileYn;

    /** 공개 여부 */
    private String openYn;

    /** 답변 사용 여부 */
    private String replyYn;

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

    public String getBoardMngType() {
        return boardMngType;
    }

    public void setBoardMngType(String boardMngType) {
        this.boardMngType = boardMngType;
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

    public String getReplyYn() {
        return replyYn;
    }

    public void setReplyYn(String replyYn) {
        this.replyYn = replyYn;
    }
}
