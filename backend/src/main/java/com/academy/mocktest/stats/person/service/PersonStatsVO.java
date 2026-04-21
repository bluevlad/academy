package com.academy.mocktest.stats.person.service;

import com.academy.common.CommonVO;

/**
 * 개인별 성적 통계 VO
 */
public class PersonStatsVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private int statsSeq;
    private String identyId;
    private String mockCode;
    private String mockName;
    private String examYear;
    private int examRound;
    private String userId;
    private String userNm;
    private String phone;
    private String classCd;
    private String classNm;
    private String subjectCd;
    private String subjectNm;
    private int originScore;
    private int adjustScore;
    private int ranking;
    private int totalCnt;
    private double avgScore;
    private double topScore;
    private String examType;
    private String examTypeNm;
    private String searchExamYear;
    private String searchExamRound;
    private String searchUserNm;
    private String searchMockCode;

    public int getStatsSeq() { return statsSeq; }
    public void setStatsSeq(int statsSeq) { this.statsSeq = statsSeq; }
    public String getIdentyId() { return identyId; }
    public void setIdentyId(String identyId) { this.identyId = identyId; }
    public String getMockCode() { return mockCode; }
    public void setMockCode(String mockCode) { this.mockCode = mockCode; }
    public String getMockName() { return mockName; }
    public void setMockName(String mockName) { this.mockName = mockName; }
    public String getExamYear() { return examYear; }
    public void setExamYear(String examYear) { this.examYear = examYear; }
    public int getExamRound() { return examRound; }
    public void setExamRound(int examRound) { this.examRound = examRound; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserNm() { return userNm; }
    public void setUserNm(String userNm) { this.userNm = userNm; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getClassCd() { return classCd; }
    public void setClassCd(String classCd) { this.classCd = classCd; }
    public String getClassNm() { return classNm; }
    public void setClassNm(String classNm) { this.classNm = classNm; }
    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }
    public String getSubjectNm() { return subjectNm; }
    public void setSubjectNm(String subjectNm) { this.subjectNm = subjectNm; }
    public int getOriginScore() { return originScore; }
    public void setOriginScore(int originScore) { this.originScore = originScore; }
    public int getAdjustScore() { return adjustScore; }
    public void setAdjustScore(int adjustScore) { this.adjustScore = adjustScore; }
    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }
    public int getTotalCnt() { return totalCnt; }
    public void setTotalCnt(int totalCnt) { this.totalCnt = totalCnt; }
    public double getAvgScore() { return avgScore; }
    public void setAvgScore(double avgScore) { this.avgScore = avgScore; }
    public double getTopScore() { return topScore; }
    public void setTopScore(double topScore) { this.topScore = topScore; }
    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }
    public String getExamTypeNm() { return examTypeNm; }
    public void setExamTypeNm(String examTypeNm) { this.examTypeNm = examTypeNm; }
    public String getSearchExamYear() { return searchExamYear; }
    public void setSearchExamYear(String searchExamYear) { this.searchExamYear = searchExamYear; }
    public String getSearchExamRound() { return searchExamRound; }
    public void setSearchExamRound(String searchExamRound) { this.searchExamRound = searchExamRound; }
    public String getSearchUserNm() { return searchUserNm; }
    public void setSearchUserNm(String searchUserNm) { this.searchUserNm = searchUserNm; }
    public String getSearchMockCode() { return searchMockCode; }
    public void setSearchMockCode(String searchMockCode) { this.searchMockCode = searchMockCode; }
}
