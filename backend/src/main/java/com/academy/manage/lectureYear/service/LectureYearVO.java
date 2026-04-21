package com.academy.manage.lectureYear.service;

import com.academy.common.CommonVO;

/**
 * 강의 연도별 관리 VO
 */
public class LectureYearVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 연도 정보
    private int yearSeq;                // 연도 시퀀스
    private String lectureYear;         // 강의 연도
    private String yearNm;              // 연도명 (예: 2024년도)
    private String yearDesc;            // 연도 설명

    // 강의 정보
    private String leccode;             // 강의 코드
    private String lectureNm;           // 강의명
    private String categoryCd;          // 카테고리 코드
    private String categoryNm;          // 카테고리명
    private String subjectCd;           // 과목 코드
    private String subjectNm;           // 과목명
    private String teacherKey;          // 강사 키
    private String teacherNm;           // 강사명

    // 연도별 강의 통계
    private int lectureCnt;             // 강의 수
    private int studentCnt;             // 수강생 수
    private int completeCnt;            // 수료생 수
    private double completeRate;        // 수료율

    // 연도별 매출 정보
    private int saleAmt;                // 매출 금액
    private int saleCnt;                // 매출 건수
    private int refundAmt;              // 환불 금액
    private int refundCnt;              // 환불 건수
    private int netSaleAmt;             // 순매출 금액

    // 기간 정보
    private String startDate;           // 시작일
    private String endDate;             // 종료일
    private String openDate;            // 개강일
    private String closeDate;           // 종강일

    // 상태 정보
    private String isuse;               // 사용여부 (Y/N)
    private String status;              // 상태 (OPEN:진행중, CLOSE:종료, READY:준비중)
    private String statusNm;            // 상태명

    // 검색 조건
    private String searchYear;          // 검색 연도
    private String searchCategoryCd;    // 검색 카테고리
    private String searchTeacherKey;    // 검색 강사
    private String searchStatus;        // 검색 상태

    // 등록/수정 정보
    private String regId;               // 등록자 ID
    private String regDate;             // 등록일
    private String modId;               // 수정자 ID
    private String modDate;             // 수정일

    // Getters and Setters
    public int getYearSeq() {
        return yearSeq;
    }

    public void setYearSeq(int yearSeq) {
        this.yearSeq = yearSeq;
    }

    public String getLectureYear() {
        return lectureYear;
    }

    public void setLectureYear(String lectureYear) {
        this.lectureYear = lectureYear;
    }

    public String getYearNm() {
        return yearNm;
    }

    public void setYearNm(String yearNm) {
        this.yearNm = yearNm;
    }

    public String getYearDesc() {
        return yearDesc;
    }

    public void setYearDesc(String yearDesc) {
        this.yearDesc = yearDesc;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getLectureNm() {
        return lectureNm;
    }

    public void setLectureNm(String lectureNm) {
        this.lectureNm = lectureNm;
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

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getTeacherKey() {
        return teacherKey;
    }

    public void setTeacherKey(String teacherKey) {
        this.teacherKey = teacherKey;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public int getLectureCnt() {
        return lectureCnt;
    }

    public void setLectureCnt(int lectureCnt) {
        this.lectureCnt = lectureCnt;
    }

    public int getStudentCnt() {
        return studentCnt;
    }

    public void setStudentCnt(int studentCnt) {
        this.studentCnt = studentCnt;
    }

    public int getCompleteCnt() {
        return completeCnt;
    }

    public void setCompleteCnt(int completeCnt) {
        this.completeCnt = completeCnt;
    }

    public double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(double completeRate) {
        this.completeRate = completeRate;
    }

    public int getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(int saleAmt) {
        this.saleAmt = saleAmt;
    }

    public int getSaleCnt() {
        return saleCnt;
    }

    public void setSaleCnt(int saleCnt) {
        this.saleCnt = saleCnt;
    }

    public int getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(int refundAmt) {
        this.refundAmt = refundAmt;
    }

    public int getRefundCnt() {
        return refundCnt;
    }

    public void setRefundCnt(int refundCnt) {
        this.refundCnt = refundCnt;
    }

    public int getNetSaleAmt() {
        return netSaleAmt;
    }

    public void setNetSaleAmt(int netSaleAmt) {
        this.netSaleAmt = netSaleAmt;
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

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusNm() {
        return statusNm;
    }

    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchCategoryCd() {
        return searchCategoryCd;
    }

    public void setSearchCategoryCd(String searchCategoryCd) {
        this.searchCategoryCd = searchCategoryCd;
    }

    public String getSearchTeacherKey() {
        return searchTeacherKey;
    }

    public void setSearchTeacherKey(String searchTeacherKey) {
        this.searchTeacherKey = searchTeacherKey;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
}
