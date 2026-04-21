package com.academy.gosi.service;

import com.academy.common.CommonVO;

/**
 * 고시 관리 VO
 * - 샘플 사용자 관리 (GOSI_RST_SMP)
 * - 고시 지역 마스터 관리 (GOSI_AREA_MST)
 * - VOD 관리 (GOSI_VOD)
 * - 통계 관리 (GOSI_STAT_MST)
 * - 이벤트 결과 (TB_EVENT_RESULT)
 */
public class GosiVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // === 고시 마스터 (GOSI_MST) ===
    private String gosiCd;          // 고시 코드
    private String gosiNm;          // 고시명
    private String gosiType;        // 고시 타입

    // === 샘플 사용자 (GOSI_RST_SMP) ===
    private String rstNo;           // 결과 번호
    private String userNm;          // 사용자명
    private String userAge;         // 나이
    private String studyWait;       // 학습 대기기간
    private String studyType;       // 학습 유형
    private String addPoint;        // 추가 점수
    private String examStat;        // 시험 상태
    private String area01;          // 지역1
    private String area02;          // 지역2
    private String selSbj01;        // 선택과목1
    private String selSbj02;        // 선택과목2
    private String sbj01;           // 과목1 점수
    private String sbj02;           // 과목2 점수
    private String sbj03;           // 과목3 점수
    private String sbj04;           // 과목4 점수
    private String sbj05;           // 과목5 점수
    private String sbjMo01;         // 과목1 모의점수
    private String sbjMo02;         // 과목2 모의점수
    private String sbjMo03;         // 과목3 모의점수
    private String sbjMo04;         // 과목4 모의점수
    private String sbjMo05;         // 과목5 모의점수
    private String userSex;         // 성별
    private String isuse;           // 사용여부
    private String inputDate;       // 입력일
    private String updateDate;      // 수정일

    // === 고시 지역 마스터 (GOSI_AREA_MST) ===
    private String gosiArea;        // 지역 코드
    private String gosiAreaFullNm;  // 지역 전체명
    private String reqNum;          // 요청 인원
    private String useNum;          // 사용 인원
    private String gosiCmpStat;     // 경쟁률
    private String pass2016;        // 2016년 합격선
    private String pass2017S;       // 2017년 시작 합격선
    private String pass2017E;       // 2017년 종료 합격선

    // === VOD 관리 (GOSI_VOD) ===
    private String prfId;           // 강사 ID
    private String title;           // 제목
    private String vodUrl;          // VOD URL
    private String fileUrl;         // 파일 URL
    private Integer idx;            // 인덱스
    private String tNo;             // T번호

    // === 고시 통계 (GOSI_STAT_MST) ===
    private String statYear;        // 통계 연도
    private String statType;        // 통계 타입
    private String statVal;         // 통계 값

    // === 고시 과목 (GOSI_SBJ_MST) ===
    private String sbjCd;           // 과목 코드
    private String sbjNm;           // 과목명
    private Integer sbjOrd;         // 과목 순서

    // === 이벤트 결과 (TB_EVENT_RESULT) ===
    private String eventCd;         // 이벤트 코드
    private String userId;          // 사용자 ID
    private String userName;        // 사용자명
    private String eventDate;       // 이벤트 일자
    private String eventResult;     // 이벤트 결과
    private String phone;           // 전화번호
    private String email;           // 이메일
    private String regDate;         // 등록일
    private String modDate;         // 수정일

    // === 검색 조건 ===
    private String searchGosiCd;    // 고시코드 검색
    private String searchGosiType;  // 고시타입 검색
    private String searchArea;      // 지역 검색
    private String searchUserNm;    // 사용자명 검색
    private String searchEventCd;   // 이벤트코드 검색
    private String searchStartDate; // 검색 시작일
    private String searchEndDate;   // 검색 종료일
    private String searchKeyword;   // 키워드 검색

    // === Getters and Setters ===

    public String getGosiCd() {
        return gosiCd;
    }

    public void setGosiCd(String gosiCd) {
        this.gosiCd = gosiCd;
    }

    public String getGosiNm() {
        return gosiNm;
    }

    public void setGosiNm(String gosiNm) {
        this.gosiNm = gosiNm;
    }

    public String getGosiType() {
        return gosiType;
    }

    public void setGosiType(String gosiType) {
        this.gosiType = gosiType;
    }

    public String getRstNo() {
        return rstNo;
    }

    public void setRstNo(String rstNo) {
        this.rstNo = rstNo;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getStudyWait() {
        return studyWait;
    }

    public void setStudyWait(String studyWait) {
        this.studyWait = studyWait;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getAddPoint() {
        return addPoint;
    }

    public void setAddPoint(String addPoint) {
        this.addPoint = addPoint;
    }

    public String getExamStat() {
        return examStat;
    }

    public void setExamStat(String examStat) {
        this.examStat = examStat;
    }

    public String getArea01() {
        return area01;
    }

    public void setArea01(String area01) {
        this.area01 = area01;
    }

    public String getArea02() {
        return area02;
    }

    public void setArea02(String area02) {
        this.area02 = area02;
    }

    public String getSelSbj01() {
        return selSbj01;
    }

    public void setSelSbj01(String selSbj01) {
        this.selSbj01 = selSbj01;
    }

    public String getSelSbj02() {
        return selSbj02;
    }

    public void setSelSbj02(String selSbj02) {
        this.selSbj02 = selSbj02;
    }

    public String getSbj01() {
        return sbj01;
    }

    public void setSbj01(String sbj01) {
        this.sbj01 = sbj01;
    }

    public String getSbj02() {
        return sbj02;
    }

    public void setSbj02(String sbj02) {
        this.sbj02 = sbj02;
    }

    public String getSbj03() {
        return sbj03;
    }

    public void setSbj03(String sbj03) {
        this.sbj03 = sbj03;
    }

    public String getSbj04() {
        return sbj04;
    }

    public void setSbj04(String sbj04) {
        this.sbj04 = sbj04;
    }

    public String getSbj05() {
        return sbj05;
    }

    public void setSbj05(String sbj05) {
        this.sbj05 = sbj05;
    }

    public String getSbjMo01() {
        return sbjMo01;
    }

    public void setSbjMo01(String sbjMo01) {
        this.sbjMo01 = sbjMo01;
    }

    public String getSbjMo02() {
        return sbjMo02;
    }

    public void setSbjMo02(String sbjMo02) {
        this.sbjMo02 = sbjMo02;
    }

    public String getSbjMo03() {
        return sbjMo03;
    }

    public void setSbjMo03(String sbjMo03) {
        this.sbjMo03 = sbjMo03;
    }

    public String getSbjMo04() {
        return sbjMo04;
    }

    public void setSbjMo04(String sbjMo04) {
        this.sbjMo04 = sbjMo04;
    }

    public String getSbjMo05() {
        return sbjMo05;
    }

    public void setSbjMo05(String sbjMo05) {
        this.sbjMo05 = sbjMo05;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getGosiArea() {
        return gosiArea;
    }

    public void setGosiArea(String gosiArea) {
        this.gosiArea = gosiArea;
    }

    public String getGosiAreaFullNm() {
        return gosiAreaFullNm;
    }

    public void setGosiAreaFullNm(String gosiAreaFullNm) {
        this.gosiAreaFullNm = gosiAreaFullNm;
    }

    public String getReqNum() {
        return reqNum;
    }

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
    }

    public String getUseNum() {
        return useNum;
    }

    public void setUseNum(String useNum) {
        this.useNum = useNum;
    }

    public String getGosiCmpStat() {
        return gosiCmpStat;
    }

    public void setGosiCmpStat(String gosiCmpStat) {
        this.gosiCmpStat = gosiCmpStat;
    }

    public String getPass2016() {
        return pass2016;
    }

    public void setPass2016(String pass2016) {
        this.pass2016 = pass2016;
    }

    public String getPass2017S() {
        return pass2017S;
    }

    public void setPass2017S(String pass2017S) {
        this.pass2017S = pass2017S;
    }

    public String getPass2017E() {
        return pass2017E;
    }

    public void setPass2017E(String pass2017E) {
        this.pass2017E = pass2017E;
    }

    public String getPrfId() {
        return prfId;
    }

    public void setPrfId(String prfId) {
        this.prfId = prfId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVodUrl() {
        return vodUrl;
    }

    public void setVodUrl(String vodUrl) {
        this.vodUrl = vodUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getTNo() {
        return tNo;
    }

    public void setTNo(String tNo) {
        this.tNo = tNo;
    }

    public String getStatYear() {
        return statYear;
    }

    public void setStatYear(String statYear) {
        this.statYear = statYear;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public String getStatVal() {
        return statVal;
    }

    public void setStatVal(String statVal) {
        this.statVal = statVal;
    }

    public String getSbjCd() {
        return sbjCd;
    }

    public void setSbjCd(String sbjCd) {
        this.sbjCd = sbjCd;
    }

    public String getSbjNm() {
        return sbjNm;
    }

    public void setSbjNm(String sbjNm) {
        this.sbjNm = sbjNm;
    }

    public Integer getSbjOrd() {
        return sbjOrd;
    }

    public void setSbjOrd(Integer sbjOrd) {
        this.sbjOrd = sbjOrd;
    }

    public String getEventCd() {
        return eventCd;
    }

    public void setEventCd(String eventCd) {
        this.eventCd = eventCd;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventResult() {
        return eventResult;
    }

    public void setEventResult(String eventResult) {
        this.eventResult = eventResult;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getSearchGosiCd() {
        return searchGosiCd;
    }

    public void setSearchGosiCd(String searchGosiCd) {
        this.searchGosiCd = searchGosiCd;
    }

    public String getSearchGosiType() {
        return searchGosiType;
    }

    public void setSearchGosiType(String searchGosiType) {
        this.searchGosiType = searchGosiType;
    }

    public String getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(String searchArea) {
        this.searchArea = searchArea;
    }

    public String getSearchUserNm() {
        return searchUserNm;
    }

    public void setSearchUserNm(String searchUserNm) {
        this.searchUserNm = searchUserNm;
    }

    public String getSearchEventCd() {
        return searchEventCd;
    }

    public void setSearchEventCd(String searchEventCd) {
        this.searchEventCd = searchEventCd;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
