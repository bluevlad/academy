package com.academy.coop.service;


import com.academy.common.CommonVO;

/**
 * 제휴사 주문 VO 클래스 (주문 관련 조회용)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            제휴사 주문 관리 신규 생성
 * </pre>
 */
public class CoopOrderVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 제휴사 코드 */
    private String coopCd;

    /** 주문번호 */
    private String orderNo;

    /** 주문 상태 코드 */
    private String orderStatusCode;

    /** 결제 코드 */
    private String payCode;

    /** 상품 구분 */
    private String prodGbn;

    /** 검색 시작일 */
    private String searchStartDate;

    /** 검색 종료일 */
    private String searchEndDate;

    /** 검색일자 타입 */
    private String searchDateType;

    /** 검색 키워드 */
    private String searchKey;

    // ========== 주문자 정보 ==========
    /** 주문자 ID */
    private String ordersUserId;

    /** 주문자명 */
    private String ordersUserName;

    /** 주문자 전화번호 */
    private String ordersTelNo;

    /** 주문자 휴대폰번호 */
    private String ordersCellNo;

    /** 주문자 우편번호 */
    private String ordersZipCd;

    /** 주문자 주소 */
    private String ordersAddr;

    /** 주문자 이메일 */
    private String ordersEmail;

    /** 주문 등록일 */
    private String ordersRegDate;

    // ========== 배송 정보 ==========
    /** 배송 송장번호 */
    private String deliversSendNo;

    /** 배송 수취인명 */
    private String deliversUserName;

    /** 배송 전화번호 */
    private String deliversTelNo;

    /** 배송 휴대폰번호 */
    private String deliversCellNo;

    /** 배송 우편번호 */
    private String deliversZipCd;

    /** 배송 주소 */
    private String deliversAddr;

    /** 배송 메모 */
    private String deliversMemo;

    /** 배송 등록일 */
    private String deliversRegDate;

    /** 배송일 */
    private String deliversSendDate;

    // ========== 결제 정보 ==========
    /** 결제 금액 */
    private Integer approvalsPrice;

    /** 추가 금액 */
    private Integer approvalsAddPrice;

    /** 결제 코드 */
    private String approvalsPayCode;

    /** 계좌번호 코드 */
    private String approvalsAcctNoCode;

    /** 결제자명 */
    private String approvalsPayName;

    /** 사용 포인트 */
    private Integer approvalsPoint;

    /** 환불 포인트 */
    private Integer refundPoint;

    /** 반환값 */
    private String approvalsReturnValue;

    /** 결제 등록일 */
    private String approvalsRegDate;

    /** 재결제 금액 */
    private Integer approvalsRePrice;

    /** 결제 코드명 */
    private String approvalsPayCodeName;

    /** 계좌번호명 */
    private String approvalsAcctNoName;

    // ========== 기타 정보 ==========
    /** 오픈 관리자 ID */
    private String openAdminId;

    /** 행 수 */
    private Integer rowCnt;

    /** 배송일 */
    private String sendDate;

    /** 단일 포인트 */
    private String danPoint;

    // Getters and Setters
    public String getCoopCd() {
        return coopCd;
    }

    public void setCoopCd(String coopCd) {
        this.coopCd = coopCd;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(String orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getProdGbn() {
        return prodGbn;
    }

    public void setProdGbn(String prodGbn) {
        this.prodGbn = prodGbn;
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

    public String getSearchDateType() {
        return searchDateType;
    }

    public void setSearchDateType(String searchDateType) {
        this.searchDateType = searchDateType;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getOrdersUserId() {
        return ordersUserId;
    }

    public void setOrdersUserId(String ordersUserId) {
        this.ordersUserId = ordersUserId;
    }

    public String getOrdersUserName() {
        return ordersUserName;
    }

    public void setOrdersUserName(String ordersUserName) {
        this.ordersUserName = ordersUserName;
    }

    public String getOrdersTelNo() {
        return ordersTelNo;
    }

    public void setOrdersTelNo(String ordersTelNo) {
        this.ordersTelNo = ordersTelNo;
    }

    public String getOrdersCellNo() {
        return ordersCellNo;
    }

    public void setOrdersCellNo(String ordersCellNo) {
        this.ordersCellNo = ordersCellNo;
    }

    public String getOrdersZipCd() {
        return ordersZipCd;
    }

    public void setOrdersZipCd(String ordersZipCd) {
        this.ordersZipCd = ordersZipCd;
    }

    public String getOrdersAddr() {
        return ordersAddr;
    }

    public void setOrdersAddr(String ordersAddr) {
        this.ordersAddr = ordersAddr;
    }

    public String getOrdersEmail() {
        return ordersEmail;
    }

    public void setOrdersEmail(String ordersEmail) {
        this.ordersEmail = ordersEmail;
    }

    public String getOrdersRegDate() {
        return ordersRegDate;
    }

    public void setOrdersRegDate(String ordersRegDate) {
        this.ordersRegDate = ordersRegDate;
    }

    public String getDeliversSendNo() {
        return deliversSendNo;
    }

    public void setDeliversSendNo(String deliversSendNo) {
        this.deliversSendNo = deliversSendNo;
    }

    public String getDeliversUserName() {
        return deliversUserName;
    }

    public void setDeliversUserName(String deliversUserName) {
        this.deliversUserName = deliversUserName;
    }

    public String getDeliversTelNo() {
        return deliversTelNo;
    }

    public void setDeliversTelNo(String deliversTelNo) {
        this.deliversTelNo = deliversTelNo;
    }

    public String getDeliversCellNo() {
        return deliversCellNo;
    }

    public void setDeliversCellNo(String deliversCellNo) {
        this.deliversCellNo = deliversCellNo;
    }

    public String getDeliversZipCd() {
        return deliversZipCd;
    }

    public void setDeliversZipCd(String deliversZipCd) {
        this.deliversZipCd = deliversZipCd;
    }

    public String getDeliversAddr() {
        return deliversAddr;
    }

    public void setDeliversAddr(String deliversAddr) {
        this.deliversAddr = deliversAddr;
    }

    public String getDeliversMemo() {
        return deliversMemo;
    }

    public void setDeliversMemo(String deliversMemo) {
        this.deliversMemo = deliversMemo;
    }

    public String getDeliversRegDate() {
        return deliversRegDate;
    }

    public void setDeliversRegDate(String deliversRegDate) {
        this.deliversRegDate = deliversRegDate;
    }

    public String getDeliversSendDate() {
        return deliversSendDate;
    }

    public void setDeliversSendDate(String deliversSendDate) {
        this.deliversSendDate = deliversSendDate;
    }

    public Integer getApprovalsPrice() {
        return approvalsPrice;
    }

    public void setApprovalsPrice(Integer approvalsPrice) {
        this.approvalsPrice = approvalsPrice;
    }

    public Integer getApprovalsAddPrice() {
        return approvalsAddPrice;
    }

    public void setApprovalsAddPrice(Integer approvalsAddPrice) {
        this.approvalsAddPrice = approvalsAddPrice;
    }

    public String getApprovalsPayCode() {
        return approvalsPayCode;
    }

    public void setApprovalsPayCode(String approvalsPayCode) {
        this.approvalsPayCode = approvalsPayCode;
    }

    public String getApprovalsAcctNoCode() {
        return approvalsAcctNoCode;
    }

    public void setApprovalsAcctNoCode(String approvalsAcctNoCode) {
        this.approvalsAcctNoCode = approvalsAcctNoCode;
    }

    public String getApprovalsPayName() {
        return approvalsPayName;
    }

    public void setApprovalsPayName(String approvalsPayName) {
        this.approvalsPayName = approvalsPayName;
    }

    public Integer getApprovalsPoint() {
        return approvalsPoint;
    }

    public void setApprovalsPoint(Integer approvalsPoint) {
        this.approvalsPoint = approvalsPoint;
    }

    public Integer getRefundPoint() {
        return refundPoint;
    }

    public void setRefundPoint(Integer refundPoint) {
        this.refundPoint = refundPoint;
    }

    public String getApprovalsReturnValue() {
        return approvalsReturnValue;
    }

    public void setApprovalsReturnValue(String approvalsReturnValue) {
        this.approvalsReturnValue = approvalsReturnValue;
    }

    public String getApprovalsRegDate() {
        return approvalsRegDate;
    }

    public void setApprovalsRegDate(String approvalsRegDate) {
        this.approvalsRegDate = approvalsRegDate;
    }

    public Integer getApprovalsRePrice() {
        return approvalsRePrice;
    }

    public void setApprovalsRePrice(Integer approvalsRePrice) {
        this.approvalsRePrice = approvalsRePrice;
    }

    public String getApprovalsPayCodeName() {
        return approvalsPayCodeName;
    }

    public void setApprovalsPayCodeName(String approvalsPayCodeName) {
        this.approvalsPayCodeName = approvalsPayCodeName;
    }

    public String getApprovalsAcctNoName() {
        return approvalsAcctNoName;
    }

    public void setApprovalsAcctNoName(String approvalsAcctNoName) {
        this.approvalsAcctNoName = approvalsAcctNoName;
    }

    public String getOpenAdminId() {
        return openAdminId;
    }

    public void setOpenAdminId(String openAdminId) {
        this.openAdminId = openAdminId;
    }

    public Integer getRowCnt() {
        return rowCnt;
    }

    public void setRowCnt(Integer rowCnt) {
        this.rowCnt = rowCnt;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getDanPoint() {
        return danPoint;
    }

    public void setDanPoint(String danPoint) {
        this.danPoint = danPoint;
    }
}
