package com.academy.mocktest.stats.total.service;

import com.academy.common.CommonVO;

/**
 * 전체 성적 통계 VO
 */
public class TotalStatsVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private int statsSeq;
    private String mockCode;
    private String mockName;
    private String examYear;
    private int examRound;
    private String classCd;
    private String classNm;
    private String subjectCd;
    private String subjectNm;
    private int totalCnt;
    private double avgScore;
    private double maxScore;
    private double minScore;
    private double topAvgScore;
    private int onlineCnt;
    private int offlineCnt;
    private int onlinePayCnt;
    private int offlinePayCnt;
    private String examTypeOn;
    private String examTypeOff;
    private String searchExamYear;
    private String searchExamRound;
    private String searchMockCode;
    private String searchClassCd;

    public int getStatsSeq() { return statsSeq; }
    public void setStatsSeq(int statsSeq) { this.statsSeq = statsSeq; }
    public String getMockCode() { return mockCode; }
    public void setMockCode(String mockCode) { this.mockCode = mockCode; }
    public String getMockName() { return mockName; }
    public void setMockName(String mockName) { this.mockName = mockName; }
    public String getExamYear() { return examYear; }
    public void setExamYear(String examYear) { this.examYear = examYear; }
    public int getExamRound() { return examRound; }
    public void setExamRound(int examRound) { this.examRound = examRound; }
    public String getClassCd() { return classCd; }
    public void setClassCd(String classCd) { this.classCd = classCd; }
    public String getClassNm() { return classNm; }
    public void setClassNm(String classNm) { this.classNm = classNm; }
    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }
    public String getSubjectNm() { return subjectNm; }
    public void setSubjectNm(String subjectNm) { this.subjectNm = subjectNm; }
    public int getTotalCnt() { return totalCnt; }
    public void setTotalCnt(int totalCnt) { this.totalCnt = totalCnt; }
    public double getAvgScore() { return avgScore; }
    public void setAvgScore(double avgScore) { this.avgScore = avgScore; }
    public double getMaxScore() { return maxScore; }
    public void setMaxScore(double maxScore) { this.maxScore = maxScore; }
    public double getMinScore() { return minScore; }
    public void setMinScore(double minScore) { this.minScore = minScore; }
    public double getTopAvgScore() { return topAvgScore; }
    public void setTopAvgScore(double topAvgScore) { this.topAvgScore = topAvgScore; }
    public int getOnlineCnt() { return onlineCnt; }
    public void setOnlineCnt(int onlineCnt) { this.onlineCnt = onlineCnt; }
    public int getOfflineCnt() { return offlineCnt; }
    public void setOfflineCnt(int offlineCnt) { this.offlineCnt = offlineCnt; }
    public int getOnlinePayCnt() { return onlinePayCnt; }
    public void setOnlinePayCnt(int onlinePayCnt) { this.onlinePayCnt = onlinePayCnt; }
    public int getOfflinePayCnt() { return offlinePayCnt; }
    public void setOfflinePayCnt(int offlinePayCnt) { this.offlinePayCnt = offlinePayCnt; }
    public String getExamTypeOn() { return examTypeOn; }
    public void setExamTypeOn(String examTypeOn) { this.examTypeOn = examTypeOn; }
    public String getExamTypeOff() { return examTypeOff; }
    public void setExamTypeOff(String examTypeOff) { this.examTypeOff = examTypeOff; }
    public String getSearchExamYear() { return searchExamYear; }
    public void setSearchExamYear(String searchExamYear) { this.searchExamYear = searchExamYear; }
    public String getSearchExamRound() { return searchExamRound; }
    public void setSearchExamRound(String searchExamRound) { this.searchExamRound = searchExamRound; }
    public String getSearchMockCode() { return searchMockCode; }
    public void setSearchMockCode(String searchMockCode) { this.searchMockCode = searchMockCode; }
    public String getSearchClassCd() { return searchClassCd; }
    public void setSearchClassCd(String searchClassCd) { this.searchClassCd = searchClassCd; }
}
