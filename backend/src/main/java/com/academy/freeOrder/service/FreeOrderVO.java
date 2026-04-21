package com.academy.freeOrder.service;


import com.academy.common.CommonVO;

/**
 * 무료 수강신청 VO 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            무료 수강신청 관리 신규 생성
 * </pre>
 */
public class FreeOrderVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // =====================================================
    // 수강신청 기본 정보
    // =====================================================

    /** 주문번호 */
    private String orderno;

    /** 사용자 ID */
    private String userid;

    /** 사용자 ID (복수) */
    private String userid2;

    /** 사용자명 */
    private String userNm;

    /** 생년월일 */
    private String birthDay;

    /** 전화번호 */
    private String telNo;

    /** 휴대폰 */
    private String phoneNo;

    /** 이메일 */
    private String email;

    /** 우편번호 */
    private String zipCode;

    /** 주소1 */
    private String address1;

    /** 주소2 */
    private String address2;

    // =====================================================
    // 강의 정보
    // =====================================================

    /** 강의코드 */
    private String leccode;

    /** 강의코드 (이전) */
    private String leccode2;

    /** 패키지번호 */
    private String packageNo;

    /** 관리번호 */
    private String mgntno;

    /** 강의제목 */
    private String subjectTitle;

    /** 강의가격 */
    private String subjectPrice;

    /** 수강기간 */
    private String subjectPeriod;

    /** 할인율 */
    private String subjectDiscount;

    /** 강의수 */
    private String subjectMovie;

    /** 시작일 */
    private String startDate;

    /** 종료일 */
    private String endDate;

    /** 상태코드 */
    private String statuscode;

    /** WMV 체크 */
    private String wmvCheck;

    /** 메모 */
    private String memo;

    /** 강의타입 선택 */
    private String lecTypeChoice;

    // =====================================================
    // 카테고리/학습형태/과목 정보
    // =====================================================

    /** 카테고리 코드 */
    private String catCd;

    /** 카테고리명 */
    private String catNm;

    /** 학습형태 코드 */
    private String menuId;

    /** 학습형태명 */
    private String menuNm;

    /** 과목 코드 */
    private String subjectSjtCd;

    /** 과목명 */
    private String subjectNm;

    /** 강사 ID */
    private String subjectTeacher;

    // =====================================================
    // 검색 조건
    // =====================================================

    /** 검색 키워드 */
    private String keyword;

    /** 검색 카테고리 */
    private String sCatCd;

    /** 검색 과목 코드 */
    private String sSjtCd;

    /** 검색 학습형태 */
    private String sMenuId;

    /** 검색 타입 */
    private String searchType;

    /** 검색 키워드 */
    private String searchKeyword;

    /** 명령 */
    private String cmd;

    /** 명령 카운트 */
    private String cmdcnt;

    // =====================================================
    // 결제 정보
    // =====================================================

    /** 가격 */
    private String price;

    /** 추가가격 */
    private String addPrice;

    /** 결제코드 */
    private String payCode;

    /** 결제코드명 */
    private String payCodeName;

    /** 계좌번호 코드명 */
    private String acctNoName;

    /** 가상계좌은행 */
    private String vcdBank;

    /** 결제자명 */
    private String payName;

    /** 포인트 */
    private String point;

    /** 가상계좌번호 */
    private String vacct;

    /** TID */
    private String tid;

    // =====================================================
    // 관리자 정보
    // =====================================================

    /** 관리자 ID */
    private String userId;

    /** 사용자 IP */
    private String userIp;

    /** 채번 카운트 타입 */
    private String ordernoCount;

    // Getters and Setters
    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid2() {
        return userid2;
    }

    public void setUserid2(String userid2) {
        this.userid2 = userid2;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getLeccode2() {
        return leccode2;
    }

    public void setLeccode2(String leccode2) {
        this.leccode2 = leccode2;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getMgntno() {
        return mgntno;
    }

    public void setMgntno(String mgntno) {
        this.mgntno = mgntno;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(String subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public String getSubjectPeriod() {
        return subjectPeriod;
    }

    public void setSubjectPeriod(String subjectPeriod) {
        this.subjectPeriod = subjectPeriod;
    }

    public String getSubjectDiscount() {
        return subjectDiscount;
    }

    public void setSubjectDiscount(String subjectDiscount) {
        this.subjectDiscount = subjectDiscount;
    }

    public String getSubjectMovie() {
        return subjectMovie;
    }

    public void setSubjectMovie(String subjectMovie) {
        this.subjectMovie = subjectMovie;
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

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getWmvCheck() {
        return wmvCheck;
    }

    public void setWmvCheck(String wmvCheck) {
        this.wmvCheck = wmvCheck;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public String getCatNm() {
        return catNm;
    }

    public void setCatNm(String catNm) {
        this.catNm = catNm;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getsCatCd() {
        return sCatCd;
    }

    public void setsCatCd(String sCatCd) {
        this.sCatCd = sCatCd;
    }

    public String getsSjtCd() {
        return sSjtCd;
    }

    public void setsSjtCd(String sSjtCd) {
        this.sSjtCd = sSjtCd;
    }

    public String getsMenuId() {
        return sMenuId;
    }

    public void setsMenuId(String sMenuId) {
        this.sMenuId = sMenuId;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCmdcnt() {
        return cmdcnt;
    }

    public void setCmdcnt(String cmdcnt) {
        this.cmdcnt = cmdcnt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(String addPrice) {
        this.addPrice = addPrice;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayCodeName() {
        return payCodeName;
    }

    public void setPayCodeName(String payCodeName) {
        this.payCodeName = payCodeName;
    }

    public String getAcctNoName() {
        return acctNoName;
    }

    public void setAcctNoName(String acctNoName) {
        this.acctNoName = acctNoName;
    }

    public String getVcdBank() {
        return vcdBank;
    }

    public void setVcdBank(String vcdBank) {
        this.vcdBank = vcdBank;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getVacct() {
        return vacct;
    }

    public void setVacct(String vacct) {
        this.vacct = vacct;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getOrdernoCount() {
        return ordernoCount;
    }

    public void setOrdernoCount(String ordernoCount) {
        this.ordernoCount = ordernoCount;
    }
}
