package com.academy.event.service;


import com.academy.common.CommonVO;

/**
 * 이벤트 결과/참여자 VO 클래스 (TB_EVENT_RESULT 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            이벤트 참여자 관리 신규 생성
 * </pre>
 */
public class EventResultVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 이벤트 번호 */
    private String eventNo;

    /** 옵션 번호 */
    private String optionNo;

    /** 사용자 ID */
    private String userId;

    /** 사용자명 */
    private String userName;

    /** 전화번호 */
    private String phoneNo;

    /** 이메일 */
    private String email;

    /** 카테고리 정보 */
    private String categoryInfo;

    /** 이벤트 텍스트 */
    private String eventTxt;

    /** 군무기관명 (제대군인 이벤트용) */
    private String armNm;

    /** 군별 (제대군인 이벤트용) */
    private String armDiv;

    /** 계급 (제대군인 이벤트용) */
    private String armRank;

    /** 군번 (제대군인 이벤트용) */
    private String armNo;

    /** 등록일시 */
    private String regDt;

    /** 검색 - 옵션 번호 */
    private String searchOptionNo;

    /** 결과 시작 번호 */
    private Integer rsltStartNo;

    /** 결과 종료 번호 */
    private Integer rsltEndNo;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String getEventTxt() {
        return eventTxt;
    }

    public void setEventTxt(String eventTxt) {
        this.eventTxt = eventTxt;
    }

    public String getArmNm() {
        return armNm;
    }

    public void setArmNm(String armNm) {
        this.armNm = armNm;
    }

    public String getArmDiv() {
        return armDiv;
    }

    public void setArmDiv(String armDiv) {
        this.armDiv = armDiv;
    }

    public String getArmRank() {
        return armRank;
    }

    public void setArmRank(String armRank) {
        this.armRank = armRank;
    }

    public String getArmNo() {
        return armNo;
    }

    public void setArmNo(String armNo) {
        this.armNo = armNo;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getSearchOptionNo() {
        return searchOptionNo;
    }

    public void setSearchOptionNo(String searchOptionNo) {
        this.searchOptionNo = searchOptionNo;
    }

    public Integer getRsltStartNo() {
        return rsltStartNo;
    }

    public void setRsltStartNo(Integer rsltStartNo) {
        this.rsltStartNo = rsltStartNo;
    }

    public Integer getRsltEndNo() {
        return rsltEndNo;
    }

    public void setRsltEndNo(Integer rsltEndNo) {
        this.rsltEndNo = rsltEndNo;
    }
}
