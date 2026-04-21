package com.academy.manage.categorySale.service;

import com.academy.common.CommonVO;

/**
 * 카테고리별 매출 관리 VO
 */
public class CategorySaleVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 카테고리 정보
    private String categoryCd;          // 카테고리 코드
    private String categoryNm;          // 카테고리명
    private String parentCd;            // 상위 카테고리 코드
    private String parentNm;            // 상위 카테고리명
    private int categoryLevel;          // 카테고리 레벨

    // 매출 정보
    private int saleSeq;                // 매출 시퀀스
    private String saleDate;            // 매출 일자
    private String saleMonth;           // 매출 월
    private String saleYear;            // 매출 년도
    private int saleAmt;                // 매출 금액
    private int saleCnt;                // 매출 건수
    private int refundAmt;              // 환불 금액
    private int refundCnt;              // 환불 건수
    private int netSaleAmt;             // 순매출 금액
    private int netSaleCnt;             // 순매출 건수

    // 통계 정보
    private double saleRate;            // 매출 비율
    private int totalSaleAmt;           // 총 매출 금액
    private int avgSaleAmt;             // 평균 매출 금액
    private int maxSaleAmt;             // 최대 매출 금액
    private int minSaleAmt;             // 최소 매출 금액

    // 기간별 매출
    private int dailySaleAmt;           // 일별 매출
    private int weeklySaleAmt;          // 주별 매출
    private int monthlySaleAmt;         // 월별 매출
    private int yearlySaleAmt;          // 년별 매출

    // 검색 조건
    private String searchStartDate;     // 검색 시작일
    private String searchEndDate;       // 검색 종료일
    private String searchCategoryCd;    // 검색 카테고리 코드
    private String searchPeriodType;    // 검색 기간 타입 (D:일별, W:주별, M:월별, Y:년별)

    // 등록/수정 정보
    private String regId;               // 등록자 ID
    private String regDate;             // 등록일
    private String modId;               // 수정자 ID
    private String modDate;             // 수정일

    // Getters and Setters
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

    public String getParentCd() {
        return parentCd;
    }

    public void setParentCd(String parentCd) {
        this.parentCd = parentCd;
    }

    public String getParentNm() {
        return parentNm;
    }

    public void setParentNm(String parentNm) {
        this.parentNm = parentNm;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public int getSaleSeq() {
        return saleSeq;
    }

    public void setSaleSeq(int saleSeq) {
        this.saleSeq = saleSeq;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleMonth() {
        return saleMonth;
    }

    public void setSaleMonth(String saleMonth) {
        this.saleMonth = saleMonth;
    }

    public String getSaleYear() {
        return saleYear;
    }

    public void setSaleYear(String saleYear) {
        this.saleYear = saleYear;
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

    public int getNetSaleCnt() {
        return netSaleCnt;
    }

    public void setNetSaleCnt(int netSaleCnt) {
        this.netSaleCnt = netSaleCnt;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public int getTotalSaleAmt() {
        return totalSaleAmt;
    }

    public void setTotalSaleAmt(int totalSaleAmt) {
        this.totalSaleAmt = totalSaleAmt;
    }

    public int getAvgSaleAmt() {
        return avgSaleAmt;
    }

    public void setAvgSaleAmt(int avgSaleAmt) {
        this.avgSaleAmt = avgSaleAmt;
    }

    public int getMaxSaleAmt() {
        return maxSaleAmt;
    }

    public void setMaxSaleAmt(int maxSaleAmt) {
        this.maxSaleAmt = maxSaleAmt;
    }

    public int getMinSaleAmt() {
        return minSaleAmt;
    }

    public void setMinSaleAmt(int minSaleAmt) {
        this.minSaleAmt = minSaleAmt;
    }

    public int getDailySaleAmt() {
        return dailySaleAmt;
    }

    public void setDailySaleAmt(int dailySaleAmt) {
        this.dailySaleAmt = dailySaleAmt;
    }

    public int getWeeklySaleAmt() {
        return weeklySaleAmt;
    }

    public void setWeeklySaleAmt(int weeklySaleAmt) {
        this.weeklySaleAmt = weeklySaleAmt;
    }

    public int getMonthlySaleAmt() {
        return monthlySaleAmt;
    }

    public void setMonthlySaleAmt(int monthlySaleAmt) {
        this.monthlySaleAmt = monthlySaleAmt;
    }

    public int getYearlySaleAmt() {
        return yearlySaleAmt;
    }

    public void setYearlySaleAmt(int yearlySaleAmt) {
        this.yearlySaleAmt = yearlySaleAmt;
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

    public String getSearchCategoryCd() {
        return searchCategoryCd;
    }

    public void setSearchCategoryCd(String searchCategoryCd) {
        this.searchCategoryCd = searchCategoryCd;
    }

    public String getSearchPeriodType() {
        return searchPeriodType;
    }

    public void setSearchPeriodType(String searchPeriodType) {
        this.searchPeriodType = searchPeriodType;
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
