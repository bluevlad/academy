package com.academy.coop.service;


import com.academy.common.CommonVO;

/**
 * 제휴사 게시판 VO 클래스 (TB_BOARD_MEMBERSHIP 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            제휴사 게시판 관리 신규 생성
 * </pre>
 */
public class CoopBoardVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 제휴사 게시판 SEQ */
    private Integer coopBoardSeq;

    /** 공개여부 (Y/N) */
    private String openYn;

    /** 작성자명 */
    private String createName;

    /** 제목 */
    private String subject;

    /** 내용 */
    private String content;

    /** 파일 경로 */
    private String filePath;

    /** 파일명 */
    private String fileName;

    /** 실제 파일명 */
    private String realFileName;

    /** 썸네일 파일 경로 */
    private String thumbnailFilePath;

    /** 썸네일 파일명 */
    private String thumbnailFileName;

    /** 썸네일 실제 파일명 */
    private String thumbnailFileRealName;

    /** 상단공지 여부 (Y/N) */
    private String noticeTopYn;

    /** 추천수 */
    private Integer recommend;

    /** 제휴 타입 */
    private String coopType;

    /** 제휴 지역 */
    private String coopArea;

    /** 제휴 카테고리 */
    private String coopCate;

    /** 제휴 지역명 (조회용) */
    private String coopAreaNm;

    /** 조회수 */
    private Integer hits;

    /** 정렬순서 */
    private Integer coopRank;

    /** 검색 제휴 지역 */
    private String searchCoopArea;

    /** 검색 제휴 카테고리 */
    private String searchCoopCate;

    /** 파일 타입 (default/thumb) - 파일 삭제용 */
    private String fileType;

    // Getters and Setters
    public Integer getCoopBoardSeq() {
        return coopBoardSeq;
    }

    public void setCoopBoardSeq(Integer coopBoardSeq) {
        this.coopBoardSeq = coopBoardSeq;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }

    public void setThumbnailFilePath(String thumbnailFilePath) {
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public String getThumbnailFileName() {
        return thumbnailFileName;
    }

    public void setThumbnailFileName(String thumbnailFileName) {
        this.thumbnailFileName = thumbnailFileName;
    }

    public String getThumbnailFileRealName() {
        return thumbnailFileRealName;
    }

    public void setThumbnailFileRealName(String thumbnailFileRealName) {
        this.thumbnailFileRealName = thumbnailFileRealName;
    }

    public String getNoticeTopYn() {
        return noticeTopYn;
    }

    public void setNoticeTopYn(String noticeTopYn) {
        this.noticeTopYn = noticeTopYn;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getCoopType() {
        return coopType;
    }

    public void setCoopType(String coopType) {
        this.coopType = coopType;
    }

    public String getCoopArea() {
        return coopArea;
    }

    public void setCoopArea(String coopArea) {
        this.coopArea = coopArea;
    }

    public String getCoopCate() {
        return coopCate;
    }

    public void setCoopCate(String coopCate) {
        this.coopCate = coopCate;
    }

    public String getCoopAreaNm() {
        return coopAreaNm;
    }

    public void setCoopAreaNm(String coopAreaNm) {
        this.coopAreaNm = coopAreaNm;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCoopRank() {
        return coopRank;
    }

    public void setCoopRank(Integer coopRank) {
        this.coopRank = coopRank;
    }

    public String getSearchCoopArea() {
        return searchCoopArea;
    }

    public void setSearchCoopArea(String searchCoopArea) {
        this.searchCoopArea = searchCoopArea;
    }

    public String getSearchCoopCate() {
        return searchCoopCate;
    }

    public void setSearchCoopCate(String searchCoopCate) {
        this.searchCoopCate = searchCoopCate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
