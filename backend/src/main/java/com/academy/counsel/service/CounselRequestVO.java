package com.academy.counsel.service;

import java.util.List;

import com.academy.common.CommonVO;

/**
 * 상담 신청 VO 클래스 (COUNSEL_RST 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            상담 신청 관리 신규 생성
 * </pre>
 */
public class CounselRequestVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 상담일 */
    private String schDay;

    /** 시간 인덱스 */
    private Integer tsIdx;

    /** 사용자 ID */
    private String userId;

    /** 사용자명 */
    private String userNm;

    /** 사용자 카테고리 */
    private String userCategory;

    /** 사용자 카테고리명 (조회용) */
    private String userCategoryNm;

    /** 사용자 기간 (A: 6개월 미만, B: 1년 미만, C: 1년 이상) */
    private String userPeriod;

    /** 사용자 기간명 (조회용) */
    private String userPeriodNm;

    /** 사용자 강의 */
    private String userLec;

    /** 사용자 강의명 (조회용) */
    private String userLecNm;

    /** 사용자 생년월일 */
    private String userBirthday;

    /** 사용자 전화번호 */
    private String userPhone;

    /** 사용자 이메일 */
    private String userEmail;

    /** 사용자 코드1 */
    private String userCode1;

    /** 사용자 코드1명 (조회용) */
    private String userCode1Nm;

    /** 사용자 코드2 */
    private String userCode2;

    /** 사용자 코드2명 (조회용) */
    private String userCode2Nm;

    /** 사용자 과목 */
    private String userSubject;

    /** 사용자 코멘트 */
    private String userComments;

    /** 취소일 */
    private String cancelDate;

    /** 예약 여부 */
    private String reserve;

    /** 시간 설정 (조회용) */
    private String timeSet;

    /** 최대 인원 (조회용) */
    private Integer maxUsr;

    /** 예약 인원 (조회용) */
    private Integer reqCnt;

    /** 예약 타입 (조회용) */
    private String reqType;

    /** 카테고리 코드 */
    private String catCd;

    /** 카테고리 목록 (검색용) */
    private List<String> cateList;

    /** 검색 카테고리 */
    private String searchCategoryS;

    // Getters and Setters
    public String getSchDay() {
        return schDay;
    }

    public void setSchDay(String schDay) {
        this.schDay = schDay;
    }

    public Integer getTsIdx() {
        return tsIdx;
    }

    public void setTsIdx(Integer tsIdx) {
        this.tsIdx = tsIdx;
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

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getUserCategoryNm() {
        return userCategoryNm;
    }

    public void setUserCategoryNm(String userCategoryNm) {
        this.userCategoryNm = userCategoryNm;
    }

    public String getUserPeriod() {
        return userPeriod;
    }

    public void setUserPeriod(String userPeriod) {
        this.userPeriod = userPeriod;
    }

    public String getUserPeriodNm() {
        return userPeriodNm;
    }

    public void setUserPeriodNm(String userPeriodNm) {
        this.userPeriodNm = userPeriodNm;
    }

    public String getUserLec() {
        return userLec;
    }

    public void setUserLec(String userLec) {
        this.userLec = userLec;
    }

    public String getUserLecNm() {
        return userLecNm;
    }

    public void setUserLecNm(String userLecNm) {
        this.userLecNm = userLecNm;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCode1() {
        return userCode1;
    }

    public void setUserCode1(String userCode1) {
        this.userCode1 = userCode1;
    }

    public String getUserCode1Nm() {
        return userCode1Nm;
    }

    public void setUserCode1Nm(String userCode1Nm) {
        this.userCode1Nm = userCode1Nm;
    }

    public String getUserCode2() {
        return userCode2;
    }

    public void setUserCode2(String userCode2) {
        this.userCode2 = userCode2;
    }

    public String getUserCode2Nm() {
        return userCode2Nm;
    }

    public void setUserCode2Nm(String userCode2Nm) {
        this.userCode2Nm = userCode2Nm;
    }

    public String getUserSubject() {
        return userSubject;
    }

    public void setUserSubject(String userSubject) {
        this.userSubject = userSubject;
    }

    public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(String timeSet) {
        this.timeSet = timeSet;
    }

    public Integer getMaxUsr() {
        return maxUsr;
    }

    public void setMaxUsr(Integer maxUsr) {
        this.maxUsr = maxUsr;
    }

    public Integer getReqCnt() {
        return reqCnt;
    }

    public void setReqCnt(Integer reqCnt) {
        this.reqCnt = reqCnt;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public List<String> getCateList() {
        return cateList;
    }

    public void setCateList(List<String> cateList) {
        this.cateList = cateList;
    }

    public String getSearchCategoryS() {
        return searchCategoryS;
    }

    public void setSearchCategoryS(String searchCategoryS) {
        this.searchCategoryS = searchCategoryS;
    }
}
