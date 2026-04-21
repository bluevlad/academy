package com.academy.board.service;


import com.academy.common.CommonVO;

/**
 * 게시판 코멘트 VO 클래스 (TB_BOARD_COMMENT 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            게시판 코멘트 등록
 * </pre>
 */
public class BoardCommentVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 코멘트 SEQ (Primary Key) */
    private String seq;

    /** 게시판관리 SEQ */
    private String cmmtBoardMngSeq;

    /** 게시판 SEQ */
    private String cmmtBoardSeq;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 삭제용 SEQ */
    private String deleteSeq;

    /** 등록자명 (조회용) */
    private String regNm;

    // Getters and Setters
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getCmmtBoardMngSeq() {
        return cmmtBoardMngSeq;
    }

    public void setCmmtBoardMngSeq(String cmmtBoardMngSeq) {
        this.cmmtBoardMngSeq = cmmtBoardMngSeq;
    }

    public String getCmmtBoardSeq() {
        return cmmtBoardSeq;
    }

    public void setCmmtBoardSeq(String cmmtBoardSeq) {
        this.cmmtBoardSeq = cmmtBoardSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeleteSeq() {
        return deleteSeq;
    }

    public void setDeleteSeq(String deleteSeq) {
        this.deleteSeq = deleteSeq;
    }

    public String getRegNm() {
        return regNm;
    }

    public void setRegNm(String regNm) {
        this.regNm = regNm;
    }
}
