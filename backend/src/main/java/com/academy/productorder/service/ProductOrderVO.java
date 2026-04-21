package com.academy.productorder.service;

import java.util.Date;

import com.academy.common.CommonVO;

/**
 * ProductOrder Value Object
 * TB_ORDERS, TB_OFF_ORDERS 테이블 매핑 VO
 */
public class ProductOrderVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // 페이징 및 검색 관련 (int 타입 - CommonVO의 String 타입과 별도 사용)
    private int startNoInt;
    private int endNoInt;
    private String schtype;
    private String keyword;
    private String searchkey;
    private String searchtype;
    private String sdate;
    private String edate;
    private String searchDateType;

    // 주문 상태 및 코드 관련
    private String orderstatuscode;
    private String codeNo;
    private String sysCd;
    private String sortfield;
    private String sort;

    // 사용자 및 관리자 정보
    private String managerId;
    private String memberId;
    private String approvalNo;
    private String courseCd;
    private String lecCd;
    private String payKind;
    private String depositStatus;
    private String prefix;

    // 공통 주문 정보
    private String orderno;
    private String orderType;
    private String userId;
    private String userNm;
    private String phoneNo;
    private String telNo;
    private String email;
    private String zipCode;
    private String address1;
    private String address2;
    private Date regDtDate;
    private Date updDtDate;

    // 결제 정보
    private Integer price;
    private Integer addprice;
    private String paycode;
    private String paycodeName;
    private String acctnocode;
    private String acctnoName;
    private String payname;
    private Integer point;
    private Integer refundPoint;
    private String returnvalue;
    private Integer reprice;
    private String tid;
    private String statuscode;
    private String isconfirm;
    private Date confirmDate;

    // 배송 정보
    private String sendno;
    private String deliverUsername;
    private String deliverTelno;
    private String deliverCellno;
    private String deliverZipcd;
    private String deliverAddr;
    private String deliverMemo;
    private Date deliverRegdate;
    private Date deliverSenddate;
    private Date senddate;

    // 강의 정보
    private String leccode;
    private String subjectTitle;
    private String mgntno;
    private String packageNo;
    private String lectureNo;
    private Integer cnt;
    private Integer subjectCnt;

    // 쿠폰 정보
    private String ccode;
    private Integer useDelCouponCnt;

    // 환불 정보
    private String isCancel;
    private Date canceldate;
    private String refundReason;
    private Integer refundPrice;

    // 기타
    private String memo;
    private String ptype;
    private String bannerName;
    private String openAdminId;
    private String openAdminNm;
    private String userCancel;
    private String ticketPrintDt;
    private Integer rowcnt;
    private Integer bookSum;

    // 카테고리 정보
    private String categoryCd;
    private String categoryNm;

    // Additional display fields
    private String orderTypeNm;
    private String statusCodeNm;
    private String priceSts;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getRegDtDate() {
        return regDtDate;
    }

    public void setRegDtDate(Date regDtDate) {
        this.regDtDate = regDtDate;
    }

    public Date getUpdDtDate() {
        return updDtDate;
    }

    public void setUpdDtDate(Date updDtDate) {
        this.updDtDate = updDtDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAddprice() {
        return addprice;
    }

    public void setAddprice(Integer addprice) {
        this.addprice = addprice;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getPaycodeName() {
        return paycodeName;
    }

    public void setPaycodeName(String paycodeName) {
        this.paycodeName = paycodeName;
    }

    public String getAcctnocode() {
        return acctnocode;
    }

    public void setAcctnocode(String acctnocode) {
        this.acctnocode = acctnocode;
    }

    public String getAcctnoName() {
        return acctnoName;
    }

    public void setAcctnoName(String acctnoName) {
        this.acctnoName = acctnoName;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getRefundPoint() {
        return refundPoint;
    }

    public void setRefundPoint(Integer refundPoint) {
        this.refundPoint = refundPoint;
    }

    public String getReturnvalue() {
        return returnvalue;
    }

    public void setReturnvalue(String returnvalue) {
        this.returnvalue = returnvalue;
    }

    public Integer getReprice() {
        return reprice;
    }

    public void setReprice(Integer reprice) {
        this.reprice = reprice;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getIsconfirm() {
        return isconfirm;
    }

    public void setIsconfirm(String isconfirm) {
        this.isconfirm = isconfirm;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getSendno() {
        return sendno;
    }

    public void setSendno(String sendno) {
        this.sendno = sendno;
    }

    public String getDeliverUsername() {
        return deliverUsername;
    }

    public void setDeliverUsername(String deliverUsername) {
        this.deliverUsername = deliverUsername;
    }

    public String getDeliverTelno() {
        return deliverTelno;
    }

    public void setDeliverTelno(String deliverTelno) {
        this.deliverTelno = deliverTelno;
    }

    public String getDeliverCellno() {
        return deliverCellno;
    }

    public void setDeliverCellno(String deliverCellno) {
        this.deliverCellno = deliverCellno;
    }

    public String getDeliverZipcd() {
        return deliverZipcd;
    }

    public void setDeliverZipcd(String deliverZipcd) {
        this.deliverZipcd = deliverZipcd;
    }

    public String getDeliverAddr() {
        return deliverAddr;
    }

    public void setDeliverAddr(String deliverAddr) {
        this.deliverAddr = deliverAddr;
    }

    public String getDeliverMemo() {
        return deliverMemo;
    }

    public void setDeliverMemo(String deliverMemo) {
        this.deliverMemo = deliverMemo;
    }

    public Date getDeliverRegdate() {
        return deliverRegdate;
    }

    public void setDeliverRegdate(Date deliverRegdate) {
        this.deliverRegdate = deliverRegdate;
    }

    public Date getDeliverSenddate() {
        return deliverSenddate;
    }

    public void setDeliverSenddate(Date deliverSenddate) {
        this.deliverSenddate = deliverSenddate;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getMgntno() {
        return mgntno;
    }

    public void setMgntno(String mgntno) {
        this.mgntno = mgntno;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(String lectureNo) {
        this.lectureNo = lectureNo;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getSubjectCnt() {
        return subjectCnt;
    }

    public void setSubjectCnt(Integer subjectCnt) {
        this.subjectCnt = subjectCnt;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public Integer getUseDelCouponCnt() {
        return useDelCouponCnt;
    }

    public void setUseDelCouponCnt(Integer useDelCouponCnt) {
        this.useDelCouponCnt = useDelCouponCnt;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public Date getCanceldate() {
        return canceldate;
    }

    public void setCanceldate(Date canceldate) {
        this.canceldate = canceldate;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Integer getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Integer refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getOpenAdminId() {
        return openAdminId;
    }

    public void setOpenAdminId(String openAdminId) {
        this.openAdminId = openAdminId;
    }

    public String getOpenAdminNm() {
        return openAdminNm;
    }

    public void setOpenAdminNm(String openAdminNm) {
        this.openAdminNm = openAdminNm;
    }

    public String getUserCancel() {
        return userCancel;
    }

    public void setUserCancel(String userCancel) {
        this.userCancel = userCancel;
    }

    public String getTicketPrintDt() {
        return ticketPrintDt;
    }

    public void setTicketPrintDt(String ticketPrintDt) {
        this.ticketPrintDt = ticketPrintDt;
    }

    public Integer getRowcnt() {
        return rowcnt;
    }

    public void setRowcnt(Integer rowcnt) {
        this.rowcnt = rowcnt;
    }

    public Integer getBookSum() {
        return bookSum;
    }

    public void setBookSum(Integer bookSum) {
        this.bookSum = bookSum;
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

    public String getOrderTypeNm() {
        return orderTypeNm;
    }

    public void setOrderTypeNm(String orderTypeNm) {
        this.orderTypeNm = orderTypeNm;
    }

    public String getStatusCodeNm() {
        return statusCodeNm;
    }

    public void setStatusCodeNm(String statusCodeNm) {
        this.statusCodeNm = statusCodeNm;
    }

    public String getPriceSts() {
        return priceSts;
    }

    public void setPriceSts(String priceSts) {
        this.priceSts = priceSts;
    }

    // 페이징 및 검색 관련 getter/setter (int 타입 별도 사용)
    public int getStartNoInt() {
        return startNoInt;
    }

    public void setStartNoInt(int startNoInt) {
        this.startNoInt = startNoInt;
    }

    public int getEndNoInt() {
        return endNoInt;
    }

    public void setEndNoInt(int endNoInt) {
        this.endNoInt = endNoInt;
    }

    public String getSchtype() {
        return schtype;
    }

    public void setSchtype(String schtype) {
        this.schtype = schtype;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getSearchDateType() {
        return searchDateType;
    }

    public void setSearchDateType(String searchDateType) {
        this.searchDateType = searchDateType;
    }

    public String getOrderstatuscode() {
        return orderstatuscode;
    }

    public void setOrderstatuscode(String orderstatuscode) {
        this.orderstatuscode = orderstatuscode;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getSysCd() {
        return sysCd;
    }

    public void setSysCd(String sysCd) {
        this.sysCd = sysCd;
    }

    public String getSortfield() {
        return sortfield;
    }

    public void setSortfield(String sortfield) {
        this.sortfield = sortfield;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getCourseCd() {
        return courseCd;
    }

    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    public String getLecCd() {
        return lecCd;
    }

    public void setLecCd(String lecCd) {
        this.lecCd = lecCd;
    }

    public String getPayKind() {
        return payKind;
    }

    public void setPayKind(String payKind) {
        this.payKind = payKind;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}