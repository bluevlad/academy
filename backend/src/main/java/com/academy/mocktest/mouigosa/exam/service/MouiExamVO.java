package com.academy.mocktest.mouigosa.exam.service;

import com.academy.common.CommonVO;

/**
 * 모의고사 시험 관리 VO
 */
public class MouiExamVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 시험 정보
    private int examSeq;                // 시험 시퀀스
    private String examCd;              // 시험 코드
    private String examNm;              // 시험명
    private String examType;            // 시험 타입 (ONLINE:온라인, OFFLINE:오프라인)
    private String examTypeNm;          // 시험 타입명
    private String examYear;            // 시험 연도
    private int examRound;              // 시험 회차

    // 일정
    private String examDate;            // 시험일
    private String examStartTime;       // 시험 시작시간
    private String examEndTime;         // 시험 종료시간
    private int examMinute;             // 시험 시간(분)
    private String regStartDate;        // 접수 시작일
    private String regEndDate;          // 접수 종료일
    private String resultDate;          // 성적 발표일

    // 카테고리
    private String categoryCd;          // 카테고리 코드
    private String categoryNm;          // 카테고리명
    private String subjectCd;           // 과목 코드
    private String subjectNm;           // 과목명

    // 문제 정보
    private int questionCnt;            // 문항 수
    private int totalScore;             // 총점
    private int passScore;              // 합격 점수

    // 장소
    private String examPlace;           // 시험 장소
    private String examAddr;            // 시험 주소
    private int maxApplyCnt;            // 최대 신청 인원

    // 상태
    private String isuse;               // 사용여부
    private String status;              // 상태 (READY:준비, OPEN:접수중, CLOSE:마감, FINISH:완료)
    private String statusNm;            // 상태명

    // 신청 현황
    private int applyCnt;               // 신청 인원
    private int attendCnt;              // 응시 인원

    // 검색 조건
    private String searchExamYear;
    private String searchExamType;
    private String searchStatus;
    private String searchCategoryCd;

    // 등록/수정
    private String regId;
    private String regDate;
    private String modId;
    private String modDate;

    // Getters and Setters
    public int getExamSeq() { return examSeq; }
    public void setExamSeq(int examSeq) { this.examSeq = examSeq; }
    public String getExamCd() { return examCd; }
    public void setExamCd(String examCd) { this.examCd = examCd; }
    public String getExamNm() { return examNm; }
    public void setExamNm(String examNm) { this.examNm = examNm; }
    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }
    public String getExamTypeNm() { return examTypeNm; }
    public void setExamTypeNm(String examTypeNm) { this.examTypeNm = examTypeNm; }
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
    public String getResultDate() { return resultDate; }
    public void setResultDate(String resultDate) { this.resultDate = resultDate; }
    public String getCategoryCd() { return categoryCd; }
    public void setCategoryCd(String categoryCd) { this.categoryCd = categoryCd; }
    public String getCategoryNm() { return categoryNm; }
    public void setCategoryNm(String categoryNm) { this.categoryNm = categoryNm; }
    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }
    public String getSubjectNm() { return subjectNm; }
    public void setSubjectNm(String subjectNm) { this.subjectNm = subjectNm; }
    public int getQuestionCnt() { return questionCnt; }
    public void setQuestionCnt(int questionCnt) { this.questionCnt = questionCnt; }
    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
    public int getPassScore() { return passScore; }
    public void setPassScore(int passScore) { this.passScore = passScore; }
    public String getExamPlace() { return examPlace; }
    public void setExamPlace(String examPlace) { this.examPlace = examPlace; }
    public String getExamAddr() { return examAddr; }
    public void setExamAddr(String examAddr) { this.examAddr = examAddr; }
    public int getMaxApplyCnt() { return maxApplyCnt; }
    public void setMaxApplyCnt(int maxApplyCnt) { this.maxApplyCnt = maxApplyCnt; }
    public String getIsuse() { return isuse; }
    public void setIsuse(String isuse) { this.isuse = isuse; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusNm() { return statusNm; }
    public void setStatusNm(String statusNm) { this.statusNm = statusNm; }
    public int getApplyCnt() { return applyCnt; }
    public void setApplyCnt(int applyCnt) { this.applyCnt = applyCnt; }
    public int getAttendCnt() { return attendCnt; }
    public void setAttendCnt(int attendCnt) { this.attendCnt = attendCnt; }
    public String getSearchExamYear() { return searchExamYear; }
    public void setSearchExamYear(String searchExamYear) { this.searchExamYear = searchExamYear; }
    public String getSearchExamType() { return searchExamType; }
    public void setSearchExamType(String searchExamType) { this.searchExamType = searchExamType; }
    public String getSearchStatus() { return searchStatus; }
    public void setSearchStatus(String searchStatus) { this.searchStatus = searchStatus; }
    public String getSearchCategoryCd() { return searchCategoryCd; }
    public void setSearchCategoryCd(String searchCategoryCd) { this.searchCategoryCd = searchCategoryCd; }
    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
    public String getModId() { return modId; }
    public void setModId(String modId) { this.modId = modId; }
    public String getModDate() { return modDate; }
    public void setModDate(String modDate) { this.modDate = modDate; }
}
