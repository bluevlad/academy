package com.academy.event.service;


import com.academy.common.CommonVO;

/**
 * 강의 이벤트 VO 클래스 (TB_LECTURE_EVENT_INFO, TB_LECTURE_EVENT_LIST 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            강의 이벤트 관리 신규 생성
 * </pre>
 */
public class LecEventVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 이벤트 번호 */
    private String eventNo;

    /** 시작일 */
    private String startDate;

    /** 종료일 */
    private String endDate;

    /** 제목 */
    private String title;

    /** 내용 */
    private String contents;

    /** 등록자 ID */
    private String regId;

    /** 등록일시 */
    private String regDt;

    /** 강좌 코드 */
    private String leccode;

    /** 과목 제목 (조회용) */
    private String subjectTitle;

    /** 카테고리 코드 (조회용) */
    private String categoryCd;

    /** 카테고리명 (조회용) */
    private String categoryNm;

    /** 사용 여부 (조회용) */
    private String subjectIsuse;

    /** 강좌 타입 선택 */
    private String lecTypeChoice;

    /** 검색 - 직렬 */
    private String searchKind;

    /** 검색 - 학습형태 */
    private String searchForm;

    /** 검색 - 텍스트 */
    private String searchText;

    /** 검색 - 사용 여부 */
    private String searchIsuse;

    // Getters and Setters
    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
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

    public String getSubjectIsuse() {
        return subjectIsuse;
    }

    public void setSubjectIsuse(String subjectIsuse) {
        this.subjectIsuse = subjectIsuse;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getSearchKind() {
        return searchKind;
    }

    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchIsuse() {
        return searchIsuse;
    }

    public void setSearchIsuse(String searchIsuse) {
        this.searchIsuse = searchIsuse;
    }
}
