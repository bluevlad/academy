package com.academy.board.service;


import com.academy.common.CommonVO;

/**
 * 게시판 첨부파일 VO 클래스 (TB_BOARD_FILE 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            게시판 첨부파일 등록
 * </pre>
 */
public class BoardFileVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 게시판관리 SEQ */
    private String boardMngSeq;

    /** 게시판 SEQ */
    private String boardSeq;

    /** 파일 번호 */
    private int fileNo;

    /** 파일명 (원본) */
    private String fileName;

    /** 파일 경로 */
    private String filePath;

    /** 부모 게시글 SEQ (답변 첨부파일용) */
    private String parentBoardSeq;

    // Getters and Setters
    public String getBoardMngSeq() {
        return boardMngSeq;
    }

    public void setBoardMngSeq(String boardMngSeq) {
        this.boardMngSeq = boardMngSeq;
    }

    public String getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(String boardSeq) {
        this.boardSeq = boardSeq;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getParentBoardSeq() {
        return parentBoardSeq;
    }

    public void setParentBoardSeq(String parentBoardSeq) {
        this.parentBoardSeq = parentBoardSeq;
    }
}
