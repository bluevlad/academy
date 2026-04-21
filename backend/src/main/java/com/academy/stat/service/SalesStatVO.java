package com.academy.stat.service;

import com.academy.common.CommonVO;

/**
 * 매출 통계 VO
 */
public class SalesStatVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 강사 정보
    private String userId;
    private String userNm;
    private String regDate;
    private String isuse;
    private String profileSummary;
    private String bookLogSummary;
    private String pic1;
    private String subjectCd;
    private String subjectNm;
    private String subjectTeacher;

    // 매출 통계
    private String sYear;
    private String sMonth;
    private String ym;
    private String sType;
    private int onSaleCnt;
    private int onRefundCnt;
    private long onSaleSum;
    private long onRefundSum;
    private int ofSaleCnt;
    private int ofRefundCnt;
    private long ofSaleSum;
    private long ofRefundSum;

    // 회원 구매 통계
    private int userCnt;
    private int onOrdCnt;
    private int ofOrdCnt;
    private double onOrdAvr;
    private double ofOrdAvr;
    private long onOrdSum;
    private long ofOrdSum;
    private long onOrdPrice;
    private long ofOrdPrice;
    private long onOrdAllPrice;
    private long ofOrdAllPrice;
    private double fstOnOrdDay;
    private double fstOfOrdDay;
    private double sndOnOrdDay;
    private double sndOfOrdDay;

    // 검색어 통계
    private int idx;
    private String searchKeyword;
    private int searchCnt;
    private int serchClick;
    private int srcSum;

    // 검색 조건
    private String searchStartDate;
    private String searchEndDate;
    private String prfId;
    private String sdate;
    private String edate;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserNm() { return userNm; }
    public void setUserNm(String userNm) { this.userNm = userNm; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
    public String getIsuse() { return isuse; }
    public void setIsuse(String isuse) { this.isuse = isuse; }
    public String getProfileSummary() { return profileSummary; }
    public void setProfileSummary(String profileSummary) { this.profileSummary = profileSummary; }
    public String getBookLogSummary() { return bookLogSummary; }
    public void setBookLogSummary(String bookLogSummary) { this.bookLogSummary = bookLogSummary; }
    public String getPic1() { return pic1; }
    public void setPic1(String pic1) { this.pic1 = pic1; }
    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }
    public String getSubjectNm() { return subjectNm; }
    public void setSubjectNm(String subjectNm) { this.subjectNm = subjectNm; }
    public String getSubjectTeacher() { return subjectTeacher; }
    public void setSubjectTeacher(String subjectTeacher) { this.subjectTeacher = subjectTeacher; }
    public String getSYear() { return sYear; }
    public void setSYear(String sYear) { this.sYear = sYear; }
    public String getSMonth() { return sMonth; }
    public void setSMonth(String sMonth) { this.sMonth = sMonth; }
    public String getYm() { return ym; }
    public void setYm(String ym) { this.ym = ym; }
    public String getSType() { return sType; }
    public void setSType(String sType) { this.sType = sType; }
    public int getOnSaleCnt() { return onSaleCnt; }
    public void setOnSaleCnt(int onSaleCnt) { this.onSaleCnt = onSaleCnt; }
    public int getOnRefundCnt() { return onRefundCnt; }
    public void setOnRefundCnt(int onRefundCnt) { this.onRefundCnt = onRefundCnt; }
    public long getOnSaleSum() { return onSaleSum; }
    public void setOnSaleSum(long onSaleSum) { this.onSaleSum = onSaleSum; }
    public long getOnRefundSum() { return onRefundSum; }
    public void setOnRefundSum(long onRefundSum) { this.onRefundSum = onRefundSum; }
    public int getOfSaleCnt() { return ofSaleCnt; }
    public void setOfSaleCnt(int ofSaleCnt) { this.ofSaleCnt = ofSaleCnt; }
    public int getOfRefundCnt() { return ofRefundCnt; }
    public void setOfRefundCnt(int ofRefundCnt) { this.ofRefundCnt = ofRefundCnt; }
    public long getOfSaleSum() { return ofSaleSum; }
    public void setOfSaleSum(long ofSaleSum) { this.ofSaleSum = ofSaleSum; }
    public long getOfRefundSum() { return ofRefundSum; }
    public void setOfRefundSum(long ofRefundSum) { this.ofRefundSum = ofRefundSum; }
    public int getUserCnt() { return userCnt; }
    public void setUserCnt(int userCnt) { this.userCnt = userCnt; }
    public int getOnOrdCnt() { return onOrdCnt; }
    public void setOnOrdCnt(int onOrdCnt) { this.onOrdCnt = onOrdCnt; }
    public int getOfOrdCnt() { return ofOrdCnt; }
    public void setOfOrdCnt(int ofOrdCnt) { this.ofOrdCnt = ofOrdCnt; }
    public double getOnOrdAvr() { return onOrdAvr; }
    public void setOnOrdAvr(double onOrdAvr) { this.onOrdAvr = onOrdAvr; }
    public double getOfOrdAvr() { return ofOrdAvr; }
    public void setOfOrdAvr(double ofOrdAvr) { this.ofOrdAvr = ofOrdAvr; }
    public long getOnOrdSum() { return onOrdSum; }
    public void setOnOrdSum(long onOrdSum) { this.onOrdSum = onOrdSum; }
    public long getOfOrdSum() { return ofOrdSum; }
    public void setOfOrdSum(long ofOrdSum) { this.ofOrdSum = ofOrdSum; }
    public long getOnOrdPrice() { return onOrdPrice; }
    public void setOnOrdPrice(long onOrdPrice) { this.onOrdPrice = onOrdPrice; }
    public long getOfOrdPrice() { return ofOrdPrice; }
    public void setOfOrdPrice(long ofOrdPrice) { this.ofOrdPrice = ofOrdPrice; }
    public long getOnOrdAllPrice() { return onOrdAllPrice; }
    public void setOnOrdAllPrice(long onOrdAllPrice) { this.onOrdAllPrice = onOrdAllPrice; }
    public long getOfOrdAllPrice() { return ofOrdAllPrice; }
    public void setOfOrdAllPrice(long ofOrdAllPrice) { this.ofOrdAllPrice = ofOrdAllPrice; }
    public double getFstOnOrdDay() { return fstOnOrdDay; }
    public void setFstOnOrdDay(double fstOnOrdDay) { this.fstOnOrdDay = fstOnOrdDay; }
    public double getFstOfOrdDay() { return fstOfOrdDay; }
    public void setFstOfOrdDay(double fstOfOrdDay) { this.fstOfOrdDay = fstOfOrdDay; }
    public double getSndOnOrdDay() { return sndOnOrdDay; }
    public void setSndOnOrdDay(double sndOnOrdDay) { this.sndOnOrdDay = sndOnOrdDay; }
    public double getSndOfOrdDay() { return sndOfOrdDay; }
    public void setSndOfOrdDay(double sndOfOrdDay) { this.sndOfOrdDay = sndOfOrdDay; }
    public int getIdx() { return idx; }
    public void setIdx(int idx) { this.idx = idx; }
    public String getSearchKeyword() { return searchKeyword; }
    public void setSearchKeyword(String searchKeyword) { this.searchKeyword = searchKeyword; }
    public int getSearchCnt() { return searchCnt; }
    public void setSearchCnt(int searchCnt) { this.searchCnt = searchCnt; }
    public int getSerchClick() { return serchClick; }
    public void setSerchClick(int serchClick) { this.serchClick = serchClick; }
    public int getSrcSum() { return srcSum; }
    public void setSrcSum(int srcSum) { this.srcSum = srcSum; }
    public String getSearchStartDate() { return searchStartDate; }
    public void setSearchStartDate(String searchStartDate) { this.searchStartDate = searchStartDate; }
    public String getSearchEndDate() { return searchEndDate; }
    public void setSearchEndDate(String searchEndDate) { this.searchEndDate = searchEndDate; }
    public String getPrfId() { return prfId; }
    public void setPrfId(String prfId) { this.prfId = prfId; }
    public String getSdate() { return sdate; }
    public void setSdate(String sdate) { this.sdate = sdate; }
    public String getEdate() { return edate; }
    public void setEdate(String edate) { this.edate = edate; }
}
