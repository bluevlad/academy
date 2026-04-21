package com.academy.manage.teacherCalculate.service;

import com.academy.common.CommonVO;

/**
 * 강사 정산 관리 VO
 */
public class TeacherCalculateVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 정산 정보
    private int calcSeq;                // 정산 시퀀스
    private String calcDate;            // 정산일
    private String calcMonth;           // 정산월
    private String calcYear;            // 정산년도
    private String calcStatus;          // 정산상태 (READY:준비, CALC:정산완료, PAID:지급완료)
    private String calcStatusNm;        // 정산상태명

    // 강사 정보
    private String teacherKey;          // 강사 키
    private String teacherNm;           // 강사명
    private String teacherId;           // 강사 ID
    private String teacherEmail;        // 강사 이메일
    private String teacherPhone;        // 강사 연락처
    private String bankNm;              // 은행명
    private String accountNo;           // 계좌번호
    private String accountHolder;       // 예금주

    // 강의 정보
    private String leccode;             // 강의 코드
    private String lectureNm;           // 강의명
    private String categoryCd;          // 카테고리 코드
    private String categoryNm;          // 카테고리명

    // 매출 정보
    private int saleAmt;                // 매출 금액
    private int saleCnt;                // 매출 건수
    private int refundAmt;              // 환불 금액
    private int refundCnt;              // 환불 건수
    private int netSaleAmt;             // 순매출 금액
    private int netSaleCnt;             // 순매출 건수

    // 정산 금액
    private double calcRate;            // 정산 비율 (%)
    private int calcAmt;                // 정산 금액
    private int taxAmt;                 // 세금 금액
    private int netCalcAmt;             // 순정산 금액 (세금 제외)
    private int paidAmt;                // 지급된 금액
    private int remainAmt;              // 잔여 금액

    // 정산 합계
    private int totalSaleAmt;           // 총 매출
    private int totalCalcAmt;           // 총 정산금액
    private int totalPaidAmt;           // 총 지급금액

    // 검색 조건
    private String searchTeacherKey;    // 검색 강사
    private String searchCalcMonth;     // 검색 정산월
    private String searchCalcYear;      // 검색 정산년도
    private String searchCalcStatus;    // 검색 정산상태
    private String searchStartDate;     // 검색 시작일
    private String searchEndDate;       // 검색 종료일

    // 지급 정보
    private String paidDate;            // 지급일
    private String paidMemo;            // 지급 메모

    // 등록/수정 정보
    private String regId;               // 등록자 ID
    private String regDate;             // 등록일
    private String modId;               // 수정자 ID
    private String modDate;             // 수정일

    // Getters and Setters
    public int getCalcSeq() {
        return calcSeq;
    }

    public void setCalcSeq(int calcSeq) {
        this.calcSeq = calcSeq;
    }

    public String getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(String calcDate) {
        this.calcDate = calcDate;
    }

    public String getCalcMonth() {
        return calcMonth;
    }

    public void setCalcMonth(String calcMonth) {
        this.calcMonth = calcMonth;
    }

    public String getCalcYear() {
        return calcYear;
    }

    public void setCalcYear(String calcYear) {
        this.calcYear = calcYear;
    }

    public String getCalcStatus() {
        return calcStatus;
    }

    public void setCalcStatus(String calcStatus) {
        this.calcStatus = calcStatus;
    }

    public String getCalcStatusNm() {
        return calcStatusNm;
    }

    public void setCalcStatusNm(String calcStatusNm) {
        this.calcStatusNm = calcStatusNm;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getBankNm() {
        return bankNm;
    }

    public void setBankNm(String bankNm) {
        this.bankNm = bankNm;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
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

    public double getCalcRate() {
        return calcRate;
    }

    public void setCalcRate(double calcRate) {
        this.calcRate = calcRate;
    }

    public int getCalcAmt() {
        return calcAmt;
    }

    public void setCalcAmt(int calcAmt) {
        this.calcAmt = calcAmt;
    }

    public int getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(int taxAmt) {
        this.taxAmt = taxAmt;
    }

    public int getNetCalcAmt() {
        return netCalcAmt;
    }

    public void setNetCalcAmt(int netCalcAmt) {
        this.netCalcAmt = netCalcAmt;
    }

    public int getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(int paidAmt) {
        this.paidAmt = paidAmt;
    }

    public int getRemainAmt() {
        return remainAmt;
    }

    public void setRemainAmt(int remainAmt) {
        this.remainAmt = remainAmt;
    }

    public int getTotalSaleAmt() {
        return totalSaleAmt;
    }

    public void setTotalSaleAmt(int totalSaleAmt) {
        this.totalSaleAmt = totalSaleAmt;
    }

    public int getTotalCalcAmt() {
        return totalCalcAmt;
    }

    public void setTotalCalcAmt(int totalCalcAmt) {
        this.totalCalcAmt = totalCalcAmt;
    }

    public int getTotalPaidAmt() {
        return totalPaidAmt;
    }

    public void setTotalPaidAmt(int totalPaidAmt) {
        this.totalPaidAmt = totalPaidAmt;
    }

    public String getSearchTeacherKey() {
        return searchTeacherKey;
    }

    public void setSearchTeacherKey(String searchTeacherKey) {
        this.searchTeacherKey = searchTeacherKey;
    }

    public String getSearchCalcMonth() {
        return searchCalcMonth;
    }

    public void setSearchCalcMonth(String searchCalcMonth) {
        this.searchCalcMonth = searchCalcMonth;
    }

    public String getSearchCalcYear() {
        return searchCalcYear;
    }

    public void setSearchCalcYear(String searchCalcYear) {
        this.searchCalcYear = searchCalcYear;
    }

    public String getSearchCalcStatus() {
        return searchCalcStatus;
    }

    public void setSearchCalcStatus(String searchCalcStatus) {
        this.searchCalcStatus = searchCalcStatus;
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

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getPaidMemo() {
        return paidMemo;
    }

    public void setPaidMemo(String paidMemo) {
        this.paidMemo = paidMemo;
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
