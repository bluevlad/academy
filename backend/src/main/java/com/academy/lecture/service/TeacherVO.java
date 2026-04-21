package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Teacher (MA Member) Value Object
 * TB_MA_MEMBER 테이블 매핑 VO
 */
public class TeacherVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Teacher specific fields
    private Integer maSn;           // MA_SN - 강사 일련번호
    private String userId;          // USER_ID - 사용자 ID
    private String userNm;          // USER_NM - 사용자 이름
    private String userNicknm;      // USER_NICKNM - 사용자 닉네임
    private String userPosition;    // USER_POSITION - 사용자 직책
    private String userRole;        // USER_ROLE - 사용자 역할
    private String userPwd;         // USER_PWD - 비밀번호
    private String birthDay;        // BIRTH_DAY - 생년월일
    private String email;           // EMAIL - 이메일
    private String account;         // ACCOUNT - 계좌
    private String phoneNo;         // PHONE_NO - 전화번호
    private String userPoint;       // USER_POINT - 사용자 포인트
    private String onOpenyn;        // ON_OPENYN - 온라인 공개여부
    private String offOpenyn;       // OFF_OPENYN - 오프라인 공개여부
    private String prfBrdOn;        // PRF_BRD_ON - 온라인 게시판 사용여부
    private String prfBrdOf;        // PRF_BRD_OF - 오프라인 게시판 사용여부
    private String payment;         // PAYMENT - 온라인 급여
    private String offPayment;      // OFF_PAYMENT - 오프라인 급여
    private String onUrl;           // ON_URL - 온라인 URL
    private String offUrl;          // OFF_URL - 오프라인 URL
    private String brdYn;           // BRD_YN - 게시판 사용여부
    private String offBrdYn;        // OFF_BRD_YN - 오프라인 게시판 사용여부
    private String profile;         // PROFILE - 프로필
    private String offProfile;      // OFF_PROFILE - 오프라인 프로필
    private String title;           // TITLE - 제목
    private String offTitle;        // OFF_TITLE - 오프라인 제목
    private String bookLog;         // BOOK_LOG - 교재 로그
    private String offBookLog;      // OFF_BOOK_LOG - 오프라인 교재 로그
    private String yplan;           // YPLAN - 연간 계획
    private String offYplan;        // OFF_YPLAN - 오프라인 연간 계획
    private String lecinfo;         // LECINFO - 강의 정보
    private String offLecinfo;      // OFF_LECINFO - 오프라인 강의 정보
    private String profHtml;        // PROF_HTML - 프로필 HTML
    private String offProfHtml;     // OFF_PROF_HTML - 오프라인 프로필 HTML
    private String searchSbjtcd;    // SEARCHSBJTCD - 검색 과목 코드
    private String srchcode;        // SRCHCODE - 검색 코드
    private String srchtxt;         // SRCHTXT - 검색 텍스트

    private String pic;             // PIC - 사진 (소)
    private String intro;           // INTRO - 소개
    private String lecIntro;        // LEC_INTRO - 강의 소개
    private String orgPic;          // ORG_PIC - 원본 사진
    private String orgIntro;        // ORG_INTRO - 원본 소개
    private String picDetail;       // PIC_DETAIL - 상세 사진
    private String memType;         // MEM_TYPE - 회원 유형
    private Integer orderSeq;       // ORDER_SEQ - 정렬 순서
    private String catCd;           // CAT_CD - 카테고리 코드
    private String onOffDiv;        // ONOFF_DIV - 온오프 구분
    private String onoffdiv;        // ONOFFDIV - 온오프 구분 (alias)
    private String searchCategory;  // SEARCHCATEGORY - 검색 카테고리
    private String[] delArr;        // DEL_ARR - 삭제 배열
    private String[] oriCategoryCode; // ORI_CATEGORY_CODE - 원본 카테고리 코드
    private String[] cetcarr;       // CETCARR - 카테고리 코드 배열
    private String[] setcarr;       // SETCARR - 과목 코드 배열
    private String[] offSetcarr;    // OFF_SETCARR - 오프라인 과목 코드 배열
    private String[] seq;           // SEQ - 순서 배열
    private String[] num;           // NUM - 번호 배열
    private String[] subjectCd;     // SUBJECT_CD - 과목 코드 배열
    private String[] professorUserId; // PROFESSOR_USER_ID - 교수 사용자 ID 배열
    // isUse, regDt, regId, updDt, updId inherited from CommonVO

    // Single value fields for mapper operations
    private String categoryCode;    // CATEGORY_CODE - 카테고리 코드 (단일)
    private String delCategoryCode; // DEL_CATEGORY_CODE - 삭제할 카테고리 코드
    private String subjectCdSingle; // SUBJECT_CD - 과목 코드 (단일)
    private String useOn;           // USE_ON - 온라인 사용 여부
    private String useOff;          // USE_OFF - 오프라인 사용 여부
    private String numSingle;       // NUM - 번호 (단일)
    private String seqSingle;       // SEQ - 순서 (단일)

    // Display fields
    private String isUseNm;         // ISUSENM - 사용여부명
    private String memTypeNm;       // MEM_TYPENM - 회원 유형명

    public Integer getMaSn() {
        return maSn;
    }

    public void setMaSn(Integer maSn) {
        this.maSn = maSn;
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

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLecIntro() {
        return lecIntro;
    }

    public void setLecIntro(String lecIntro) {
        this.lecIntro = lecIntro;
    }

    public String getOrgPic() {
        return orgPic;
    }

    public void setOrgPic(String orgPic) {
        this.orgPic = orgPic;
    }

    public String getOrgIntro() {
        return orgIntro;
    }

    public void setOrgIntro(String orgIntro) {
        this.orgIntro = orgIntro;
    }

    public String getPicDetail() {
        return picDetail;
    }

    public void setPicDetail(String picDetail) {
        this.picDetail = picDetail;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getMemTypeNm() {
        return memTypeNm;
    }

    public void setMemTypeNm(String memTypeNm) {
        this.memTypeNm = memTypeNm;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public String getOnOffDiv() {
        return onOffDiv;
    }

    public void setOnOffDiv(String onOffDiv) {
        this.onOffDiv = onOffDiv;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String[] getDelArr() {
        return delArr;
    }

    public void setDelArr(String[] delArr) {
        this.delArr = delArr;
    }

    public String getUserNicknm() {
        return userNicknm;
    }

    public void setUserNicknm(String userNicknm) {
        this.userNicknm = userNicknm;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public String getOnOpenyn() {
        return onOpenyn;
    }

    public void setOnOpenyn(String onOpenyn) {
        this.onOpenyn = onOpenyn;
    }

    public String getOffOpenyn() {
        return offOpenyn;
    }

    public void setOffOpenyn(String offOpenyn) {
        this.offOpenyn = offOpenyn;
    }

    public String getPrfBrdOn() {
        return prfBrdOn;
    }

    public void setPrfBrdOn(String prfBrdOn) {
        this.prfBrdOn = prfBrdOn;
    }

    public String getPrfBrdOf() {
        return prfBrdOf;
    }

    public void setPrfBrdOf(String prfBrdOf) {
        this.prfBrdOf = prfBrdOf;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOffPayment() {
        return offPayment;
    }

    public void setOffPayment(String offPayment) {
        this.offPayment = offPayment;
    }

    public String getOnUrl() {
        return onUrl;
    }

    public void setOnUrl(String onUrl) {
        this.onUrl = onUrl;
    }

    public String getOffUrl() {
        return offUrl;
    }

    public void setOffUrl(String offUrl) {
        this.offUrl = offUrl;
    }

    public String getBrdYn() {
        return brdYn;
    }

    public void setBrdYn(String brdYn) {
        this.brdYn = brdYn;
    }

    public String getOffBrdYn() {
        return offBrdYn;
    }

    public void setOffBrdYn(String offBrdYn) {
        this.offBrdYn = offBrdYn;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getOffProfile() {
        return offProfile;
    }

    public void setOffProfile(String offProfile) {
        this.offProfile = offProfile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffTitle() {
        return offTitle;
    }

    public void setOffTitle(String offTitle) {
        this.offTitle = offTitle;
    }

    public String getBookLog() {
        return bookLog;
    }

    public void setBookLog(String bookLog) {
        this.bookLog = bookLog;
    }

    public String getOffBookLog() {
        return offBookLog;
    }

    public void setOffBookLog(String offBookLog) {
        this.offBookLog = offBookLog;
    }

    public String getYplan() {
        return yplan;
    }

    public void setYplan(String yplan) {
        this.yplan = yplan;
    }

    public String getOffYplan() {
        return offYplan;
    }

    public void setOffYplan(String offYplan) {
        this.offYplan = offYplan;
    }

    public String getLecinfo() {
        return lecinfo;
    }

    public void setLecinfo(String lecinfo) {
        this.lecinfo = lecinfo;
    }

    public String getOffLecinfo() {
        return offLecinfo;
    }

    public void setOffLecinfo(String offLecinfo) {
        this.offLecinfo = offLecinfo;
    }

    public String getProfHtml() {
        return profHtml;
    }

    public void setProfHtml(String profHtml) {
        this.profHtml = profHtml;
    }

    public String getOffProfHtml() {
        return offProfHtml;
    }

    public void setOffProfHtml(String offProfHtml) {
        this.offProfHtml = offProfHtml;
    }

    public String getSearchSbjtcd() {
        return searchSbjtcd;
    }

    public void setSearchSbjtcd(String searchSbjtcd) {
        this.searchSbjtcd = searchSbjtcd;
    }

    public String getSrchcode() {
        return srchcode;
    }

    public void setSrchcode(String srchcode) {
        this.srchcode = srchcode;
    }

    public String getSrchtxt() {
        return srchtxt;
    }

    public void setSrchtxt(String srchtxt) {
        this.srchtxt = srchtxt;
    }

    public String getOnoffdiv() {
        return onoffdiv;
    }

    public void setOnoffdiv(String onoffdiv) {
        this.onoffdiv = onoffdiv;
    }

    public String[] getOriCategoryCode() {
        return oriCategoryCode;
    }

    public void setOriCategoryCode(String[] oriCategoryCode) {
        this.oriCategoryCode = oriCategoryCode;
    }

    public String[] getCetcarr() {
        return cetcarr;
    }

    public void setCetcarr(String[] cetcarr) {
        this.cetcarr = cetcarr;
    }

    public String[] getSetcarr() {
        return setcarr;
    }

    public void setSetcarr(String[] setcarr) {
        this.setcarr = setcarr;
    }

    public String[] getOffSetcarr() {
        return offSetcarr;
    }

    public void setOffSetcarr(String[] offSetcarr) {
        this.offSetcarr = offSetcarr;
    }

    public String[] getSeq() {
        return seq;
    }

    public void setSeq(String[] seq) {
        this.seq = seq;
    }

    public String[] getNum() {
        return num;
    }

    public void setNum(String[] num) {
        this.num = num;
    }

    public String[] getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String[] subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String[] getProfessorUserId() {
        return professorUserId;
    }

    public void setProfessorUserId(String[] professorUserId) {
        this.professorUserId = professorUserId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getDelCategoryCode() {
        return delCategoryCode;
    }

    public void setDelCategoryCode(String delCategoryCode) {
        this.delCategoryCode = delCategoryCode;
    }

    public String getSubjectCdSingle() {
        return subjectCdSingle;
    }

    public void setSubjectCdSingle(String subjectCdSingle) {
        this.subjectCdSingle = subjectCdSingle;
    }

    public String getUseOn() {
        return useOn;
    }

    public void setUseOn(String useOn) {
        this.useOn = useOn;
    }

    public String getUseOff() {
        return useOff;
    }

    public void setUseOff(String useOff) {
        this.useOff = useOff;
    }

    public String getNumSingle() {
        return numSingle;
    }

    public void setNumSingle(String numSingle) {
        this.numSingle = numSingle;
    }

    public String getSeqSingle() {
        return seqSingle;
    }

    public void setSeqSingle(String seqSingle) {
        this.seqSingle = seqSingle;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}