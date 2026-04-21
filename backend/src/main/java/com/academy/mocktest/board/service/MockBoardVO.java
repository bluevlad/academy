package com.academy.mocktest.board.service;

import com.academy.common.CommonVO;

/**
 * 모의고사 게시판 VO
 */
public class MockBoardVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 게시글 정보
    private int boardSeq;               // 게시글 시퀀스
    private String boardType;           // 게시판 타입 (NOTICE:공지, QNA:Q&A, FAQ:FAQ)
    private String boardTypeNm;         // 게시판 타입명
    private String title;               // 제목
    private String content;             // 내용
    private int viewCnt;                // 조회수
    private String isTop;               // 상단고정 여부 (Y/N)
    private String isuse;               // 사용여부 (Y/N)

    // 모의고사 관련
    private int examSeq;                // 시험 시퀀스
    private String examNm;              // 시험명
    private String examDate;            // 시험일자

    // 카테고리
    private String categoryCd;          // 카테고리 코드
    private String categoryNm;          // 카테고리명

    // 첨부파일
    private String fileId;              // 파일 ID
    private String fileName;            // 파일명
    private String filePath;            // 파일 경로
    private int fileSize;               // 파일 크기

    // 작성자 정보
    private String writerId;            // 작성자 ID
    private String writerNm;            // 작성자명

    // 답변 정보 (Q&A)
    private String answerContent;       // 답변 내용
    private String answerYn;            // 답변 여부 (Y/N)
    private String answerId;            // 답변자 ID
    private String answerNm;            // 답변자명
    private String answerDate;          // 답변일

    // 검색 조건
    private String searchBoardType;     // 검색 게시판 타입
    private String searchTitle;         // 검색 제목
    private String searchContent;       // 검색 내용
    private String searchWriter;        // 검색 작성자
    private String searchStartDate;     // 검색 시작일
    private String searchEndDate;       // 검색 종료일

    // 등록/수정 정보
    private String regId;               // 등록자 ID
    private String regDate;             // 등록일
    private String modId;               // 수정자 ID
    private String modDate;             // 수정일

    // Getters and Setters
    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardTypeNm() {
        return boardTypeNm;
    }

    public void setBoardTypeNm(String boardTypeNm) {
        this.boardTypeNm = boardTypeNm;
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

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public int getExamSeq() {
        return examSeq;
    }

    public void setExamSeq(int examSeq) {
        this.examSeq = examSeq;
    }

    public String getExamNm() {
        return examNm;
    }

    public void setExamNm(String examNm) {
        this.examNm = examNm;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getWriterNm() {
        return writerNm;
    }

    public void setWriterNm(String writerNm) {
        this.writerNm = writerNm;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerYn() {
        return answerYn;
    }

    public void setAnswerYn(String answerYn) {
        this.answerYn = answerYn;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerNm() {
        return answerNm;
    }

    public void setAnswerNm(String answerNm) {
        this.answerNm = answerNm;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public String getSearchBoardType() {
        return searchBoardType;
    }

    public void setSearchBoardType(String searchBoardType) {
        this.searchBoardType = searchBoardType;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getSearchWriter() {
        return searchWriter;
    }

    public void setSearchWriter(String searchWriter) {
        this.searchWriter = searchWriter;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
