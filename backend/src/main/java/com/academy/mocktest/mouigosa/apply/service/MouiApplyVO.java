package com.academy.mocktest.mouigosa.apply.service;

import com.academy.common.CommonVO;

/**
 * 모의고사 신청 관리 VO
 */
public class MouiApplyVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 신청 정보
    private int applySeq;               // 신청 시퀀스
    private int examSeq;                // 시험 시퀀스
    private String examNm;              // 시험명
    private String examDate;            // 시험일

    // 회원 정보
    private String userId;              // 회원 ID
    private String userNm;              // 회원명
    private String phone;               // 연락처
    private String email;               // 이메일

    // 신청 상태
    private String applyStatus;         // 신청상태 (APPLY:신청, CANCEL:취소, ATTEND:응시, ABSENT:불참)
    private String applyStatusNm;       // 신청상태명
    private String applyDate;           // 신청일
    private String cancelDate;          // 취소일
    private String cancelReason;        // 취소 사유

    // 결제 정보
    private int payAmt;                 // 결제금액
    private String payStatus;           // 결제상태
    private String payDate;             // 결제일

    // 시험 결과
    private int score;                  // 점수
    private int ranking;                // 순위
    private String passYn;              // 합격여부
    private String resultDate;          // 성적 등록일

    // 검색 조건
    private String searchExamSeq;
    private String searchUserId;
    private String searchUserNm;
    private String searchApplyStatus;
    private String searchStartDate;
    private String searchEndDate;

    // 등록/수정
    private String regId;
    private String regDate;
    private String modId;
    private String modDate;

    // Getters and Setters
    public int getApplySeq() { return applySeq; }
    public void setApplySeq(int applySeq) { this.applySeq = applySeq; }
    public int getExamSeq() { return examSeq; }
    public void setExamSeq(int examSeq) { this.examSeq = examSeq; }
    public String getExamNm() { return examNm; }
    public void setExamNm(String examNm) { this.examNm = examNm; }
    public String getExamDate() { return examDate; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserNm() { return userNm; }
    public void setUserNm(String userNm) { this.userNm = userNm; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getApplyStatus() { return applyStatus; }
    public void setApplyStatus(String applyStatus) { this.applyStatus = applyStatus; }
    public String getApplyStatusNm() { return applyStatusNm; }
    public void setApplyStatusNm(String applyStatusNm) { this.applyStatusNm = applyStatusNm; }
    public String getApplyDate() { return applyDate; }
    public void setApplyDate(String applyDate) { this.applyDate = applyDate; }
    public String getCancelDate() { return cancelDate; }
    public void setCancelDate(String cancelDate) { this.cancelDate = cancelDate; }
    public String getCancelReason() { return cancelReason; }
    public void setCancelReason(String cancelReason) { this.cancelReason = cancelReason; }
    public int getPayAmt() { return payAmt; }
    public void setPayAmt(int payAmt) { this.payAmt = payAmt; }
    public String getPayStatus() { return payStatus; }
    public void setPayStatus(String payStatus) { this.payStatus = payStatus; }
    public String getPayDate() { return payDate; }
    public void setPayDate(String payDate) { this.payDate = payDate; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }
    public String getPassYn() { return passYn; }
    public void setPassYn(String passYn) { this.passYn = passYn; }
    public String getResultDate() { return resultDate; }
    public void setResultDate(String resultDate) { this.resultDate = resultDate; }
    public String getSearchExamSeq() { return searchExamSeq; }
    public void setSearchExamSeq(String searchExamSeq) { this.searchExamSeq = searchExamSeq; }
    public String getSearchUserId() { return searchUserId; }
    public void setSearchUserId(String searchUserId) { this.searchUserId = searchUserId; }
    public String getSearchUserNm() { return searchUserNm; }
    public void setSearchUserNm(String searchUserNm) { this.searchUserNm = searchUserNm; }
    public String getSearchApplyStatus() { return searchApplyStatus; }
    public void setSearchApplyStatus(String searchApplyStatus) { this.searchApplyStatus = searchApplyStatus; }
    public String getSearchStartDate() { return searchStartDate; }
    public void setSearchStartDate(String searchStartDate) { this.searchStartDate = searchStartDate; }
    public String getSearchEndDate() { return searchEndDate; }
    public void setSearchEndDate(String searchEndDate) { this.searchEndDate = searchEndDate; }
    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
    public String getModId() { return modId; }
    public void setModId(String modId) { this.modId = modId; }
    public String getModDate() { return modDate; }
    public void setModDate(String modDate) { this.modDate = modDate; }
}
