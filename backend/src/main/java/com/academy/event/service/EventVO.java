package com.academy.event.service;


import com.academy.common.CommonVO;

/**
 * 이벤트 VO 클래스 (TB_EVENT_INFO 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            이벤트 관리 신규 생성
 * </pre>
 */
public class EventVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 이벤트 번호 */
    private String eventNo;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인, L: 강의) */
    private String onoffDiv;

    /** 카테고리 코드 */
    private String categoryCode;

    /** 카테고리명 (조회용) */
    private String categoryName;

    /** 공지 구분 */
    private String noticeGubun;

    /** 공개 여부 */
    private String openYn;

    /** 회원 구분 */
    private String memberGubun;

    /** 조회수 */
    private Integer hit;

    /** 시작일 */
    private String startDate;

    /** 시작시간 */
    private String startTime;

    /** 종료일 */
    private String endDate;

    /** 종료시간 */
    private String endTime;

    /** 상태 (조회용) */
    private String status;

    /** 제목 */
    private String title;

    /** 내용 텍스트 */
    private String contentsText;

    /** 내용 이미지 */
    private String contentsImg;

    /** 목록 썸네일 */
    private String listThumbnail;

    /** 이슈 썸네일 */
    private String issueThumbnail;

    /** 옵션1 사용여부 */
    private String option1Yn;

    /** 옵션2 사용여부 */
    private String option2Yn;

    /** 옵션3 사용여부 */
    private String option3Yn;

    /** 옵션4 사용여부 */
    private String option4Yn;

    /** 옵션2 숨김여부 */
    private String option2Hidden;

    /** 옵션2 이벤트여부 */
    private String option2Event;

    /** 옵션2 팝업여부 */
    private String option2Popup;

    /** 첨부파일 수 (조회용) */
    private Integer attachFile;

    /** 옵션2 댓글 수 (조회용) */
    private Integer option2Cnt;

    /** 참여 인원 수 (조회용) */
    private Integer peopleCnt;

    /** SMS 메시지박스 */
    private String smsMessageBox;

    /** SMS 번호 */
    private String smsNum;

    /** 팝업 제목 */
    private String popupTitle;

    /** 팝업 링크 */
    private String popupLink;

    /** 등록자 ID */
    private String regId;

    /** 등록일시 */
    private String regDt;

    /** 수정자 ID */
    private String updId;

    /** 수정일시 */
    private String updDt;

    /** 검색 - 카테고리 */
    private String searchCategory;

    /** 메뉴 타입 */
    private String menuType;

    // Getters and Setters
    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNoticeGubun() {
        return noticeGubun;
    }

    public void setNoticeGubun(String noticeGubun) {
        this.noticeGubun = noticeGubun;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getMemberGubun() {
        return memberGubun;
    }

    public void setMemberGubun(String memberGubun) {
        this.memberGubun = memberGubun;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentsText() {
        return contentsText;
    }

    public void setContentsText(String contentsText) {
        this.contentsText = contentsText;
    }

    public String getContentsImg() {
        return contentsImg;
    }

    public void setContentsImg(String contentsImg) {
        this.contentsImg = contentsImg;
    }

    public String getListThumbnail() {
        return listThumbnail;
    }

    public void setListThumbnail(String listThumbnail) {
        this.listThumbnail = listThumbnail;
    }

    public String getIssueThumbnail() {
        return issueThumbnail;
    }

    public void setIssueThumbnail(String issueThumbnail) {
        this.issueThumbnail = issueThumbnail;
    }

    public String getOption1Yn() {
        return option1Yn;
    }

    public void setOption1Yn(String option1Yn) {
        this.option1Yn = option1Yn;
    }

    public String getOption2Yn() {
        return option2Yn;
    }

    public void setOption2Yn(String option2Yn) {
        this.option2Yn = option2Yn;
    }

    public String getOption3Yn() {
        return option3Yn;
    }

    public void setOption3Yn(String option3Yn) {
        this.option3Yn = option3Yn;
    }

    public String getOption4Yn() {
        return option4Yn;
    }

    public void setOption4Yn(String option4Yn) {
        this.option4Yn = option4Yn;
    }

    public String getOption2Hidden() {
        return option2Hidden;
    }

    public void setOption2Hidden(String option2Hidden) {
        this.option2Hidden = option2Hidden;
    }

    public String getOption2Event() {
        return option2Event;
    }

    public void setOption2Event(String option2Event) {
        this.option2Event = option2Event;
    }

    public String getOption2Popup() {
        return option2Popup;
    }

    public void setOption2Popup(String option2Popup) {
        this.option2Popup = option2Popup;
    }

    public Integer getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(Integer attachFile) {
        this.attachFile = attachFile;
    }

    public Integer getOption2Cnt() {
        return option2Cnt;
    }

    public void setOption2Cnt(Integer option2Cnt) {
        this.option2Cnt = option2Cnt;
    }

    public Integer getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(Integer peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public String getSmsMessageBox() {
        return smsMessageBox;
    }

    public void setSmsMessageBox(String smsMessageBox) {
        this.smsMessageBox = smsMessageBox;
    }

    public String getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(String smsNum) {
        this.smsNum = smsNum;
    }

    public String getPopupTitle() {
        return popupTitle;
    }

    public void setPopupTitle(String popupTitle) {
        this.popupTitle = popupTitle;
    }

    public String getPopupLink() {
        return popupLink;
    }

    public void setPopupLink(String popupLink) {
        this.popupLink = popupLink;
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

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUpdDt() {
        return updDt;
    }

    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}
