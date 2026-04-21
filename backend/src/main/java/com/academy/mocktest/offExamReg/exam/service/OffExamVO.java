package com.academy.mocktest.offExamReg.exam.service;

import com.academy.common.CommonVO;

/**
 * 오프라인 시험 관리 VO
 */
public class OffExamVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private int examSeq;
    private String examCd;
    private String examNm;
    private String examYear;
    private int examRound;
    private String examDate;
    private String examStartTime;
    private String examEndTime;
    private int examMinute;
    private String regStartDate;
    private String regEndDate;
    private String categoryCd;
    private String categoryNm;
    private String examPlace;
    private String examAddr;
    private int maxApplyCnt;
    private int applyCnt;
    private String status;
    private String statusNm;
    private String isuse;
    private String searchExamYear;
    private String searchStatus;
    private String regId;
    private String regDate;
    private String modId;
    private String modDate;

    public int getExamSeq() { return examSeq; }
    public void setExamSeq(int examSeq) { this.examSeq = examSeq; }
    public String getExamCd() { return examCd; }
    public void setExamCd(String examCd) { this.examCd = examCd; }
    public String getExamNm() { return examNm; }
    public void setExamNm(String examNm) { this.examNm = examNm; }
    public String getExamYear() { return examYear; }
    public void setExamYear(String examYear) { this.examYear = examYear; }
    public int getExamRound() { return examRound; }
    public void setExamRound(int examRound) { this.examRound = examRound; }
    public String getExamDate() { return examDate; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public String getExamStartTime() { return examStartTime; }
    public void setExamStartTime(String examStartTime) { this.examStartTime = examStartTime; }
    public String getExamEndTime() { return examEndTime; }
    public void setExamEndTime(String examEndTime) { this.examEndTime = examEndTime; }
    public int getExamMinute() { return examMinute; }
    public void setExamMinute(int examMinute) { this.examMinute = examMinute; }
    public String getRegStartDate() { return regStartDate; }
    public void setRegStartDate(String regStartDate) { this.regStartDate = regStartDate; }
    public String getRegEndDate() { return regEndDate; }
    public void setRegEndDate(String regEndDate) { this.regEndDate = regEndDate; }
    public String getCategoryCd() { return categoryCd; }
    public void setCategoryCd(String categoryCd) { this.categoryCd = categoryCd; }
    public String getCategoryNm() { return categoryNm; }
    public void setCategoryNm(String categoryNm) { this.categoryNm = categoryNm; }
    public String getExamPlace() { return examPlace; }
    public void setExamPlace(String examPlace) { this.examPlace = examPlace; }
    public String getExamAddr() { return examAddr; }
    public void setExamAddr(String examAddr) { this.examAddr = examAddr; }
    public int getMaxApplyCnt() { return maxApplyCnt; }
    public void setMaxApplyCnt(int maxApplyCnt) { this.maxApplyCnt = maxApplyCnt; }
    public int getApplyCnt() { return applyCnt; }
    public void setApplyCnt(int applyCnt) { this.applyCnt = applyCnt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusNm() { return statusNm; }
    public void setStatusNm(String statusNm) { this.statusNm = statusNm; }
    public String getIsuse() { return isuse; }
    public void setIsuse(String isuse) { this.isuse = isuse; }
    public String getSearchExamYear() { return searchExamYear; }
    public void setSearchExamYear(String searchExamYear) { this.searchExamYear = searchExamYear; }
    public String getSearchStatus() { return searchStatus; }
    public void setSearchStatus(String searchStatus) { this.searchStatus = searchStatus; }
    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
    public String getModId() { return modId; }
    public void setModId(String modId) { this.modId = modId; }
    public String getModDate() { return modDate; }
    public void setModDate(String modDate) { this.modDate = modDate; }
}
