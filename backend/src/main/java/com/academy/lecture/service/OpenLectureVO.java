package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * OpenLecture Value Object
 * TB_OPEN_LECTURE 테이블 매핑 VO
 */
public class OpenLectureVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // OpenLecture specific fields
    private String openleccode;     // OPENLECCODE - 공개강의 코드
    private String categoryCd;      // CATEGORY_CD - 카테고리 코드
    private String openbunru;       // OPENBUNRU - 공개 분류
    private String openTitle;       // OPEN_TITLE - 제목
    private String openSjtCd;       // OPEN_SJT_CD - 과목 코드
    private String openTeacher;     // OPEN_TEACHER - 강사
    private String openMemo;        // OPEN_MEMO - 메모
    private String openDesc;        // OPEN_DESC - 설명
    private String openHimoviePath; // OPEN_HIMOVIE_PATH - 고화질 동영상 경로
    private String openNormalmoviePath; // OPEN_NOMALMOVIE_PATH - 일반화질 동영상 경로
    private String openFile;        // OPEN_FILE - 파일
    private String openPassword;    // OPEN_PASSWORD - 비밀번호
    private Integer openPoint;      // OPEN_POINT - 포인트
    private Integer openHit;        // OPEN_HIT - 조회수

    // Display / Search fields (searchType, searchText, searchKind inherited from CommonVO)
    private String categoryNm;      // CATEGORY_NM - 카테고리명
    private String subjectNm;       // SUBJECT_NM - 과목명
    private String teacherNm;       // TEACHER_NM - 강사명
    private String searchForm;      // SEARCHFORM - 검색 형태
    private String searchYear;      // SEARCHYEAR - 검색 연도
    private String searchPayyn;     // SEARCHPAYYN - 검색 결제여부
    private String searchPaytype;   // SEARCHPAYTYPE - 검색 결제타입
    private String searchOpenpage;  // SEARCHOPENPAGE - 검색 공개페이지
    private String openIsuse;       // OPEN_ISUSE - 공개강의 사용여부
    private String openFileDel;     // OPEN_FILE_DEL - 공개강의 파일 삭제
    private String openFileDelnm;   // OPEN_FILE_DELNM - 공개강의 파일 삭제명
    private String prefix;          // PREFIX - 접두사
    private String seq;             // SEQ - 순서

    // Getters and Setters
    public String getOpenleccode() {
        return openleccode;
    }

    public void setOpenleccode(String openleccode) {
        this.openleccode = openleccode;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getOpenbunru() {
        return openbunru;
    }

    public void setOpenbunru(String openbunru) {
        this.openbunru = openbunru;
    }

    public String getOpenTitle() {
        return openTitle;
    }

    public void setOpenTitle(String openTitle) {
        this.openTitle = openTitle;
    }

    public String getOpenSjtCd() {
        return openSjtCd;
    }

    public void setOpenSjtCd(String openSjtCd) {
        this.openSjtCd = openSjtCd;
    }

    public String getOpenTeacher() {
        return openTeacher;
    }

    public void setOpenTeacher(String openTeacher) {
        this.openTeacher = openTeacher;
    }

    public String getOpenMemo() {
        return openMemo;
    }

    public void setOpenMemo(String openMemo) {
        this.openMemo = openMemo;
    }

    public String getOpenDesc() {
        return openDesc;
    }

    public void setOpenDesc(String openDesc) {
        this.openDesc = openDesc;
    }

    public String getOpenHimoviePath() {
        return openHimoviePath;
    }

    public void setOpenHimoviePath(String openHimoviePath) {
        this.openHimoviePath = openHimoviePath;
    }

    public String getOpenNormalmoviePath() {
        return openNormalmoviePath;
    }

    public void setOpenNormalmoviePath(String openNormalmoviePath) {
        this.openNormalmoviePath = openNormalmoviePath;
    }

    public String getOpenFile() {
        return openFile;
    }

    public void setOpenFile(String openFile) {
        this.openFile = openFile;
    }

    public String getOpenPassword() {
        return openPassword;
    }

    public void setOpenPassword(String openPassword) {
        this.openPassword = openPassword;
    }

    public Integer getOpenPoint() {
        return openPoint;
    }

    public void setOpenPoint(Integer openPoint) {
        this.openPoint = openPoint;
    }

    public Integer getOpenHit() {
        return openHit;
    }

    public void setOpenHit(Integer openHit) {
        this.openHit = openHit;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchPayyn() {
        return searchPayyn;
    }

    public void setSearchPayyn(String searchPayyn) {
        this.searchPayyn = searchPayyn;
    }

    public String getSearchPaytype() {
        return searchPaytype;
    }

    public void setSearchPaytype(String searchPaytype) {
        this.searchPaytype = searchPaytype;
    }

    public String getSearchOpenpage() {
        return searchOpenpage;
    }

    public void setSearchOpenpage(String searchOpenpage) {
        this.searchOpenpage = searchOpenpage;
    }

    public String getOpenIsuse() {
        return openIsuse;
    }

    public void setOpenIsuse(String openIsuse) {
        this.openIsuse = openIsuse;
    }

    public String getOpenFileDel() {
        return openFileDel;
    }

    public void setOpenFileDel(String openFileDel) {
        this.openFileDel = openFileDel;
    }

    public String getOpenFileDelnm() {
        return openFileDelnm;
    }

    public void setOpenFileDelnm(String openFileDelnm) {
        this.openFileDelnm = openFileDelnm;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}