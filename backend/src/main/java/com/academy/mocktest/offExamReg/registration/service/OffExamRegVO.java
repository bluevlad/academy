package com.academy.mocktest.offExamReg.registration.service;

import com.academy.common.CommonVO;

/**
 * 오프라인 시험 접수 관리 VO
 */
public class OffExamRegVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private int regSeq;
    private int examSeq;
    private String examNm;
    private String examDate;
    private String userId;
    private String userNm;
    private String phone;
    private String email;
    private String regStatus;
    private String regStatusNm;
    private String regDate;
    private String cancelDate;
    private String cancelReason;
    private int payAmt;
    private String payStatus;
    private String payDate;
    private String attendYn;
    private int score;
    private int ranking;
    private String passYn;
    private String searchExamSeq;
    private String searchUserNm;
    private String searchRegStatus;
    private String modId;
    private String modDate;

    public int getRegSeq() { return regSeq; }
    public void setRegSeq(int regSeq) { this.regSeq = regSeq; }
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
    public String getRegStatus() { return regStatus; }
    public void setRegStatus(String regStatus) { this.regStatus = regStatus; }
    public String getRegStatusNm() { return regStatusNm; }
    public void setRegStatusNm(String regStatusNm) { this.regStatusNm = regStatusNm; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
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
    public String getAttendYn() { return attendYn; }
    public void setAttendYn(String attendYn) { this.attendYn = attendYn; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }
    public String getPassYn() { return passYn; }
    public void setPassYn(String passYn) { this.passYn = passYn; }
    public String getSearchExamSeq() { return searchExamSeq; }
    public void setSearchExamSeq(String searchExamSeq) { this.searchExamSeq = searchExamSeq; }
    public String getSearchUserNm() { return searchUserNm; }
    public void setSearchUserNm(String searchUserNm) { this.searchUserNm = searchUserNm; }
    public String getSearchRegStatus() { return searchRegStatus; }
    public void setSearchRegStatus(String searchRegStatus) { this.searchRegStatus = searchRegStatus; }
    public String getModId() { return modId; }
    public void setModId(String modId) { this.modId = modId; }
    public String getModDate() { return modDate; }
    public void setModDate(String modDate) { this.modDate = modDate; }
}
