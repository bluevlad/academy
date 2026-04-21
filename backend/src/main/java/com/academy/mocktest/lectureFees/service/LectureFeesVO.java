package com.academy.mocktest.lectureFees.service;

import com.academy.common.CommonVO;

/**
 * 모의고사 수강료 관리 VO
 */
public class LectureFeesVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 수강료 정보
    private int feeSeq;                 // 수강료 시퀀스
    private String feeType;             // 수강료 타입 (SINGLE:단과, PACKAGE:패키지)
    private String feeTypeNm;           // 수강료 타입명
    private String feeNm;               // 수강료명
    private int price;                  // 정가
    private int salePrice;              // 판매가
    private int discountRate;           // 할인율
    private int discountAmt;            // 할인금액

    // 시험 정보
    private int examSeq;                // 시험 시퀀스
    private String examNm;              // 시험명
    private String examDate;            // 시험일자
    private String examYear;            // 시험년도

    // 카테고리
    private String categoryCd;          // 카테고리 코드
    private String categoryNm;          // 카테고리명
    private String subjectCd;           // 과목 코드
    private String subjectNm;           // 과목명

    // 수강 기간
    private String startDate;           // 시작일
    private String endDate;             // 종료일
    private int limitDay;               // 수강기한(일)

    // 상태 정보
    private String isuse;               // 사용여부 (Y/N)
    private String saleYn;              // 판매여부 (Y/N)
    private int sortNo;                 // 정렬순서

    // 패키지 구성
    private int packageCnt;             // 패키지 포함 과목 수
    private String includedSubjects;    // 포함 과목 목록

    // 검색 조건
    private String searchFeeType;       // 검색 수강료 타입
    private String searchExamYear;      // 검색 시험년도
    private String searchCategoryCd;    // 검색 카테고리
    private String searchSaleYn;        // 검색 판매여부

    // 등록/수정 정보
    private String regId;
    private String regDate;
    private String modId;
    private String modDate;

    // Getters and Setters
    public int getFeeSeq() {
        return feeSeq;
    }

    public void setFeeSeq(int feeSeq) {
        this.feeSeq = feeSeq;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeTypeNm() {
        return feeTypeNm;
    }

    public void setFeeTypeNm(String feeTypeNm) {
        this.feeTypeNm = feeTypeNm;
    }

    public String getFeeNm() {
        return feeNm;
    }

    public void setFeeNm(String feeNm) {
        this.feeNm = feeNm;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(int discountAmt) {
        this.discountAmt = discountAmt;
    }

    public int getExamSeq() {
        return examSeq;
    }

    public void setExamSeq(int examSeq) {
        this.examSeq = examSeq;
    }

    public String getExamNm() {
        return examNm;
    }

    public void setExamNm(String examNm) {
        this.examNm = examNm;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamYear() {
        return examYear;
    }

    public void setExamYear(String examYear) {
        this.examYear = examYear;
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

    public int getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(int limitDay) {
        this.limitDay = limitDay;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getSaleYn() {
        return saleYn;
    }

    public void setSaleYn(String saleYn) {
        this.saleYn = saleYn;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public int getPackageCnt() {
        return packageCnt;
    }

    public void setPackageCnt(int packageCnt) {
        this.packageCnt = packageCnt;
    }

    public String getIncludedSubjects() {
        return includedSubjects;
    }

    public void setIncludedSubjects(String includedSubjects) {
        this.includedSubjects = includedSubjects;
    }

    public String getSearchFeeType() {
        return searchFeeType;
    }

    public void setSearchFeeType(String searchFeeType) {
        this.searchFeeType = searchFeeType;
    }

    public String getSearchExamYear() {
        return searchExamYear;
    }

    public void setSearchExamYear(String searchExamYear) {
        this.searchExamYear = searchExamYear;
    }

    public String getSearchCategoryCd() {
        return searchCategoryCd;
    }

    public void setSearchCategoryCd(String searchCategoryCd) {
        this.searchCategoryCd = searchCategoryCd;
    }

    public String getSearchSaleYn() {
        return searchSaleYn;
    }

    public void setSearchSaleYn(String searchSaleYn) {
        this.searchSaleYn = searchSaleYn;
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
