package com.academy.event.service;


import com.academy.common.CommonVO;

/**
 * 이벤트 옵션 VO 클래스 (TB_EVENT_OPTION1~4 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            이벤트 옵션 관리 신규 생성
 * </pre>
 */
public class EventOptionVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 이벤트 번호 */
    private String eventNo;

    /** 옵션 번호 */
    private String optionNo;

    /** 옵션명 */
    private String optionName;

    /** 인원 구분 */
    private String peopleGubun;

    /** 인원 수 */
    private Integer peopleCnt;

    /** 다중선택 여부 */
    private String multiSelectYn;

    /** 번호 (옵션2용) */
    private Integer no;

    /** 사용자 ID */
    private String userId;

    /** 사용자명 */
    private String userNm;

    /** 텍스트 */
    private String txt;

    /** SMS 메시지박스 (옵션3용) */
    private String smsMessageBox;

    /** SMS 번호 (옵션3용) */
    private String smsNum;

    /** 팝업 제목 (옵션4용) */
    private String popupTitle;

    /** 팝업 링크 (옵션4용) */
    private String popupLink;

    /** 등록자 ID */
    private String regId;

    /** 등록일시 */
    private String regDt;

    /** 수정자 ID */
    private String updId;

    /** 수정일시 */
    private String updDt;

    // Getters and Setters
    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getPeopleGubun() {
        return peopleGubun;
    }

    public void setPeopleGubun(String peopleGubun) {
        this.peopleGubun = peopleGubun;
    }

    public Integer getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(Integer peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public String getMultiSelectYn() {
        return multiSelectYn;
    }

    public void setMultiSelectYn(String multiSelectYn) {
        this.multiSelectYn = multiSelectYn;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
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
}
