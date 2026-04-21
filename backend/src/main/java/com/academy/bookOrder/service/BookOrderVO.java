package com.academy.bookOrder.service;


import com.academy.common.CommonVO;

/**
 * BookOrder Value Object
 * 교재 배송/매출 관리 VO
 */
public class BookOrderVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 주문 관련 fields
    private String orderno;             // 주문번호
    private String orderNo;             // 주문번호 (alias)
    private String userId;              // 사용자 ID
    private String userNm;              // 사용자 이름
    private String telNo;               // 전화번호
    private String phoneNo;             // 핸드폰번호
    private String zipCode;             // 우편번호
    private String address1;            // 주소1
    private String address2;            // 주소2
    private String email;               // 이메일

    // 배송 관련 fields
    private String sendNo;              // 송장번호
    private String sendno;              // 송장번호 (alias)
    private String deliverUsername;     // 수령인 이름
    private String dName;               // 수령인 이름 (alias)
    private String dTelno;              // 수령인 전화번호
    private String dCellno;             // 수령인 핸드폰번호
    private String zipcd;               // 우편번호
    private String juso;                // 주소
    private String addr;                // 주소 (alias)
    private String dMemo;               // 배송 메모
    private String dleorder;            // 배송 구분 (DLE100: 택배, DLE110: 직접수령)
    private String sendDate;            // 발송일

    // 결제 관련 fields
    private String payCode;             // 결제코드
    private String payName;             // 결제자 이름
    private String payname;             // 결제자 이름 (alias)
    private String recName;             // 수령인 이름
    private Integer addPrice;           // 추가금액
    private Integer price;              // 가격
    private Integer point;              // 포인트
    private String returnValue;         // 반환값
    private String vacct;               // 가상계좌

    // 상품 관련 fields
    private String mgntNo;              // 관리번호
    private String mgntno;              // 관리번호 (alias)
    private String ptype;               // 상품타입
    private String statusCode;          // 상태코드
    private Integer cnt;                // 수량
    private String wmvPmp;              // WMV/PMP 구분
    private Integer realprice;          // 실제가격
    private String iscancle;            // 취소여부
    private Integer totalPrice;         // 총가격

    // 검색 조건 fields
    private String sdate;               // 시작일
    private String edate;               // 종료일
    private String searchkey;           // 검색어
    private String searchtype;          // 검색타입
    private String sType;               // 사이트 타입 (C: 경찰)
    private String printtype;           // 출력 타입

    // Getters and Setters
    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
        this.orderNo = orderno;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        this.orderno = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
        this.sendno = sendNo;
    }

    public String getSendno() {
        return sendno;
    }

    public void setSendno(String sendno) {
        this.sendno = sendno;
        this.sendNo = sendno;
    }

    public String getDeliverUsername() {
        return deliverUsername;
    }

    public void setDeliverUsername(String deliverUsername) {
        this.deliverUsername = deliverUsername;
    }

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getDTelno() {
        return dTelno;
    }

    public void setDTelno(String dTelno) {
        this.dTelno = dTelno;
    }

    public String getDCellno() {
        return dCellno;
    }

    public void setDCellno(String dCellno) {
        this.dCellno = dCellno;
    }

    public String getZipcd() {
        return zipcd;
    }

    public void setZipcd(String zipcd) {
        this.zipcd = zipcd;
    }

    public String getJuso() {
        return juso;
    }

    public void setJuso(String juso) {
        this.juso = juso;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDMemo() {
        return dMemo;
    }

    public void setDMemo(String dMemo) {
        this.dMemo = dMemo;
    }

    public String getDleorder() {
        return dleorder;
    }

    public void setDleorder(String dleorder) {
        this.dleorder = dleorder;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
        this.payname = payName;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
        this.payName = payname;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public Integer getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Integer addPrice) {
        this.addPrice = addPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getVacct() {
        return vacct;
    }

    public void setVacct(String vacct) {
        this.vacct = vacct;
    }

    public String getMgntNo() {
        return mgntNo;
    }

    public void setMgntNo(String mgntNo) {
        this.mgntNo = mgntNo;
        this.mgntno = mgntNo;
    }

    public String getMgntno() {
        return mgntno;
    }

    public void setMgntno(String mgntno) {
        this.mgntno = mgntno;
        this.mgntNo = mgntno;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getWmvPmp() {
        return wmvPmp;
    }

    public void setWmvPmp(String wmvPmp) {
        this.wmvPmp = wmvPmp;
    }

    public Integer getRealprice() {
        return realprice;
    }

    public void setRealprice(Integer realprice) {
        this.realprice = realprice;
    }

    public String getIscancle() {
        return iscancle;
    }

    public void setIscancle(String iscancle) {
        this.iscancle = iscancle;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getSType() {
        return sType;
    }

    public void setSType(String sType) {
        this.sType = sType;
    }

    public String getPrinttype() {
        return printtype;
    }

    public void setPrinttype(String printtype) {
        this.printtype = printtype;
    }

    @Override
    public String toString() {
        return "BookOrderVO{" +
                "orderno='" + orderno + '\'' +
                ", userId='" + userId + '\'' +
                ", userNm='" + userNm + '\'' +
                ", sdate='" + sdate + '\'' +
                ", edate='" + edate + '\'' +
                '}';
    }
}
