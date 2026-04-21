package com.academy.lectureOff.service;

import com.academy.common.CommonVO;

/**
 * 오프라인 강의 관리 VO
 * - 단과 강의 (TB_OFF_LEC_MST)
 * - 강의 브릿지 (TB_OFF_LEC_BRIDGE)
 * - 종합반 강의 (TB_OFF_LEC_JONG)
 * - 강의 일정 (TB_OFF_LECTURE_DATE)
 * - 강의 교재 (TB_OFF_PLUS_CA_BOOK)
 */
public class LectureOffVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // === 강의 마스터 (TB_OFF_LEC_MST) ===
    private Integer seq;                // 순번
    private String leccode;             // 강의 코드
    private String categoryCd;          // 직종 코드
    private String categoryNm;          // 직종명
    private String learningCd;          // 학습형태 코드
    private String learningNm;          // 학습형태명
    private String subjectType;         // 과목 타입
    private String subjectGubun;        // 과목 구분
    private String subjectMemberCnt;    // 수강인원
    private String subjectTeacher;      // 강사 ID
    private String subjectTeacherNm;    // 강사명
    private String subjectTeacherPayment; // 강사 수당
    private String subjectSjtCd;        // 과목 코드
    private String subjectNm;           // 과목명
    private String subjectTitle;        // 강의 제목
    private String subjectDesc;         // 강의 설명
    private String subjectKeyword;      // 키워드
    private String subjectOpenDate;     // 개강일
    private String subjectEndDate;      // 종강일
    private String lecSchedule;         // 강의 스케줄
    private String week1;               // 월요일
    private String week2;               // 화요일
    private String week3;               // 수요일
    private String week4;               // 목요일
    private String week5;               // 금요일
    private String week6;               // 토요일
    private String week7;               // 일요일
    private String subjectDiscount;     // 할인율
    private Integer subjectPrice;       // 정가
    private Integer subjectRealPrice;   // 실제가
    private String subjectSumnail;      // 썸네일
    private String subjectIsuse;        // 사용여부 (Y/N)
    private String lecTypeChoice;       // 강의타입 (D:단과, J:종합반, N:선택형종합반)
    private String lecGubun;            // 강의 구분
    private String recGubun;            // 녹화 구분
    private String plan;                // 강의 계획
    private Integer lecCount;           // 강의 횟수
    private String lecProcess;          // 진행 상태
    private String regDt;               // 등록일
    private String regId;               // 등록자
    private String updDt;               // 수정일
    private String updId;               // 수정자

    // === 강의 브릿지 (TB_OFF_LEC_BRIDGE) ===
    private String bridgeLeccode;       // 브릿지 강의코드
    private Integer bseq;               // 브릿지 순번

    // === 종합반 (TB_OFF_LEC_JONG) ===
    private Integer jongseq;            // 종합반 순번
    private String mstLeccode;          // 마스터 강의코드
    private String gubun;               // 구분 (1:필수, 2:선택)
    private Integer sort;               // 정렬순서

    // === 강의 일정 (TB_OFF_LECTURE_DATE) ===
    private Integer num;                // 차시
    private String lecDate;             // 강의일

    // === 강의 교재 (TB_OFF_PLUS_CA_BOOK) ===
    private Integer idx;                // 인덱스
    private String rscId;               // 교재 ID
    private String bookNm;              // 교재명
    private String flag;                // 교재 구분 (J:주교재, B:부교재, S:수강생교재)
    private String subjectBookMemo;     // 교재 메모

    // === 수강자 정보 ===
    private String userId;              // 사용자 ID
    private String userNm;              // 사용자명
    private String phoneNo;             // 전화번호
    private String orderno;             // 주문번호
    private Integer realprice;          // 실결제금액
    private String paycode;             // 결제코드
    private String payNm;               // 결제명
    private Integer offerercnt;         // 수강인원

    // === 선택형 종합반 ===
    private Integer no;                 // 선택번호

    // === 검색 조건 ===
    private String searchKind;          // 직종 검색
    private String searchForm;          // 학습형태 검색
    private String searchYear;          // 연도 검색
    private String searchOpenDate;      // 개강일 시작
    private String searchEndDate;       // 개강일 종료
    private String searchSubjectIsuse;  // 사용여부 검색
    private String searchLecProcess;    // 진행상태 검색
    private String searchType;          // 검색 타입
    private String searchText;          // 검색어
    private String searchPayYn;         // 유/무료 검색
    private String searchOpenPage;      // 페이지 타입
    private String searchPayType;       // 결제타입 검색
    private String prefix;              // 코드 접두어
    private String updateFlag;          // 수정 플래그 (ALL:전체)
    private String bridgeLec;           // 브릿지 강의코드
    private String bridgeLecs;          // 브릿지 강의코드 목록

    // === Getters and Setters ===

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
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

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getLearningNm() {
        return learningNm;
    }

    public void setLearningNm(String learningNm) {
        this.learningNm = learningNm;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectGubun() {
        return subjectGubun;
    }

    public void setSubjectGubun(String subjectGubun) {
        this.subjectGubun = subjectGubun;
    }

    public String getSubjectMemberCnt() {
        return subjectMemberCnt;
    }

    public void setSubjectMemberCnt(String subjectMemberCnt) {
        this.subjectMemberCnt = subjectMemberCnt;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectTeacherNm() {
        return subjectTeacherNm;
    }

    public void setSubjectTeacherNm(String subjectTeacherNm) {
        this.subjectTeacherNm = subjectTeacherNm;
    }

    public String getSubjectTeacherPayment() {
        return subjectTeacherPayment;
    }

    public void setSubjectTeacherPayment(String subjectTeacherPayment) {
        this.subjectTeacherPayment = subjectTeacherPayment;
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

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectKeyword() {
        return subjectKeyword;
    }

    public void setSubjectKeyword(String subjectKeyword) {
        this.subjectKeyword = subjectKeyword;
    }

    public String getSubjectOpenDate() {
        return subjectOpenDate;
    }

    public void setSubjectOpenDate(String subjectOpenDate) {
        this.subjectOpenDate = subjectOpenDate;
    }

    public String getSubjectEndDate() {
        return subjectEndDate;
    }

    public void setSubjectEndDate(String subjectEndDate) {
        this.subjectEndDate = subjectEndDate;
    }

    public String getLecSchedule() {
        return lecSchedule;
    }

    public void setLecSchedule(String lecSchedule) {
        this.lecSchedule = lecSchedule;
    }

    public String getWeek1() {
        return week1;
    }

    public void setWeek1(String week1) {
        this.week1 = week1;
    }

    public String getWeek2() {
        return week2;
    }

    public void setWeek2(String week2) {
        this.week2 = week2;
    }

    public String getWeek3() {
        return week3;
    }

    public void setWeek3(String week3) {
        this.week3 = week3;
    }

    public String getWeek4() {
        return week4;
    }

    public void setWeek4(String week4) {
        this.week4 = week4;
    }

    public String getWeek5() {
        return week5;
    }

    public void setWeek5(String week5) {
        this.week5 = week5;
    }

    public String getWeek6() {
        return week6;
    }

    public void setWeek6(String week6) {
        this.week6 = week6;
    }

    public String getWeek7() {
        return week7;
    }

    public void setWeek7(String week7) {
        this.week7 = week7;
    }

    public String getSubjectDiscount() {
        return subjectDiscount;
    }

    public void setSubjectDiscount(String subjectDiscount) {
        this.subjectDiscount = subjectDiscount;
    }

    public Integer getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(Integer subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public Integer getSubjectRealPrice() {
        return subjectRealPrice;
    }

    public void setSubjectRealPrice(Integer subjectRealPrice) {
        this.subjectRealPrice = subjectRealPrice;
    }

    public String getSubjectSumnail() {
        return subjectSumnail;
    }

    public void setSubjectSumnail(String subjectSumnail) {
        this.subjectSumnail = subjectSumnail;
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

    public String getLecGubun() {
        return lecGubun;
    }

    public void setLecGubun(String lecGubun) {
        this.lecGubun = lecGubun;
    }

    public String getRecGubun() {
        return recGubun;
    }

    public void setRecGubun(String recGubun) {
        this.recGubun = recGubun;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Integer getLecCount() {
        return lecCount;
    }

    public void setLecCount(Integer lecCount) {
        this.lecCount = lecCount;
    }

    public String getLecProcess() {
        return lecProcess;
    }

    public void setLecProcess(String lecProcess) {
        this.lecProcess = lecProcess;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getUpdDt() {
        return updDt;
    }

    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getBridgeLeccode() {
        return bridgeLeccode;
    }

    public void setBridgeLeccode(String bridgeLeccode) {
        this.bridgeLeccode = bridgeLeccode;
    }

    public Integer getBseq() {
        return bseq;
    }

    public void setBseq(Integer bseq) {
        this.bseq = bseq;
    }

    public Integer getJongseq() {
        return jongseq;
    }

    public void setJongseq(Integer jongseq) {
        this.jongseq = jongseq;
    }

    public String getMstLeccode() {
        return mstLeccode;
    }

    public void setMstLeccode(String mstLeccode) {
        this.mstLeccode = mstLeccode;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLecDate() {
        return lecDate;
    }

    public void setLecDate(String lecDate) {
        this.lecDate = lecDate;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getBookNm() {
        return bookNm;
    }

    public void setBookNm(String bookNm) {
        this.bookNm = bookNm;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSubjectBookMemo() {
        return subjectBookMemo;
    }

    public void setSubjectBookMemo(String subjectBookMemo) {
        this.subjectBookMemo = subjectBookMemo;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Integer getRealprice() {
        return realprice;
    }

    public void setRealprice(Integer realprice) {
        this.realprice = realprice;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getPayNm() {
        return payNm;
    }

    public void setPayNm(String payNm) {
        this.payNm = payNm;
    }

    public Integer getOfferercnt() {
        return offerercnt;
    }

    public void setOfferercnt(Integer offerercnt) {
        this.offerercnt = offerercnt;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
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

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchOpenDate() {
        return searchOpenDate;
    }

    public void setSearchOpenDate(String searchOpenDate) {
        this.searchOpenDate = searchOpenDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getSearchSubjectIsuse() {
        return searchSubjectIsuse;
    }

    public void setSearchSubjectIsuse(String searchSubjectIsuse) {
        this.searchSubjectIsuse = searchSubjectIsuse;
    }

    public String getSearchLecProcess() {
        return searchLecProcess;
    }

    public void setSearchLecProcess(String searchLecProcess) {
        this.searchLecProcess = searchLecProcess;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchPayYn() {
        return searchPayYn;
    }

    public void setSearchPayYn(String searchPayYn) {
        this.searchPayYn = searchPayYn;
    }

    public String getSearchOpenPage() {
        return searchOpenPage;
    }

    public void setSearchOpenPage(String searchOpenPage) {
        this.searchOpenPage = searchOpenPage;
    }

    public String getSearchPayType() {
        return searchPayType;
    }

    public void setSearchPayType(String searchPayType) {
        this.searchPayType = searchPayType;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getBridgeLec() {
        return bridgeLec;
    }

    public void setBridgeLec(String bridgeLec) {
        this.bridgeLec = bridgeLec;
    }

    public String getBridgeLecs() {
        return bridgeLecs;
    }

    public void setBridgeLecs(String bridgeLecs) {
        this.bridgeLecs = bridgeLecs;
    }
}
