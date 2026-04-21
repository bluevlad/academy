package com.academy.gosi.service;

import com.academy.common.CommonVO;

/**
 * 공지/게시판 관리 VO
 * - 공지사항 관리 (TB_PUB)
 * - 첨부파일 관리
 */
public class PubVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // === 공지사항 (TB_PUB) ===
    private Integer pubNo;          // 게시글 번호 (PK)
    private String pubType;         // 게시판 타입
    private String catCd;           // 카테고리 코드
    private String title;           // 제목
    private String content;         // 내용
    private String writeId;         // 작성자 ID
    private String writeNm;         // 작성자명
    private Integer hitCnt;         // 조회수
    private String noticeYn;        // 공지여부 (Y/N)
    private String useYn;           // 사용여부 (Y/N)
    private String regDate;         // 등록일
    private String modDate;         // 수정일

    // === 첨부파일 ===
    private Integer fileNo;         // 파일 번호
    private String fileName;        // 파일명
    private String filePath;        // 파일 경로
    private String fileOrgNm;       // 원본 파일명
    private Long fileSize;          // 파일 크기
    private String fileExt;         // 파일 확장자

    // === 검색 조건 ===
    private String searchPubType;   // 게시판 타입 검색
    private String searchCatCd;     // 카테고리 코드 검색
    private String searchKeyword;   // 키워드 검색 (제목/내용)
    private String searchTitle;     // 제목 검색
    private String searchWriteNm;   // 작성자명 검색
    private String searchStartDate; // 검색 시작일
    private String searchEndDate;   // 검색 종료일
    private String searchNoticeYn;  // 공지여부 검색
    private String searchUseYn;     // 사용여부 검색

    // === Getters and Setters ===

    public Integer getPubNo() {
        return pubNo;
    }

    public void setPubNo(Integer pubNo) {
        this.pubNo = pubNo;
    }

    public String getPubType() {
        return pubType;
    }

    public void setPubType(String pubType) {
        this.pubType = pubType;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
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

    public String getWriteId() {
        return writeId;
    }

    public void setWriteId(String writeId) {
        this.writeId = writeId;
    }

    public String getWriteNm() {
        return writeNm;
    }

    public void setWriteNm(String writeNm) {
        this.writeNm = writeNm;
    }

    public Integer getHitCnt() {
        return hitCnt;
    }

    public void setHitCnt(Integer hitCnt) {
        this.hitCnt = hitCnt;
    }

    public String getNoticeYn() {
        return noticeYn;
    }

    public void setNoticeYn(String noticeYn) {
        this.noticeYn = noticeYn;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
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

    public String getFileOrgNm() {
        return fileOrgNm;
    }

    public void setFileOrgNm(String fileOrgNm) {
        this.fileOrgNm = fileOrgNm;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getSearchPubType() {
        return searchPubType;
    }

    public void setSearchPubType(String searchPubType) {
        this.searchPubType = searchPubType;
    }

    public String getSearchCatCd() {
        return searchCatCd;
    }

    public void setSearchCatCd(String searchCatCd) {
        this.searchCatCd = searchCatCd;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchWriteNm() {
        return searchWriteNm;
    }

    public void setSearchWriteNm(String searchWriteNm) {
        this.searchWriteNm = searchWriteNm;
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

    public String getSearchNoticeYn() {
        return searchNoticeYn;
    }

    public void setSearchNoticeYn(String searchNoticeYn) {
        this.searchNoticeYn = searchNoticeYn;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }
}
