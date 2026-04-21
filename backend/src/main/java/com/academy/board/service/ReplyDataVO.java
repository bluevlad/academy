package com.academy.board.service;

import java.io.Serializable;

/**
 * 답변 데이터 VO 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.22         system            답변 데이터 VO 생성
 * </pre>
 */
public class ReplyDataVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 게시판 SEQ */
    private String boardSeq;

    /** 파일 경로 */
    private String filePath;

    // Getters and Setters
    public String getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(String boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
