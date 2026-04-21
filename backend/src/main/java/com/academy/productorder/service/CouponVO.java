package com.academy.productorder.service;

import java.util.Date;

/**
 * Coupon Value Object
 * TB_TM_COUPON 테이블 매핑 VO
 */
public class CouponVO {

    private String ccode;
    private String cname;
    private String ccontent;
    private Date regdate;
    private String regtype;
    private Integer regprice;
    private Date expdates;
    private Date expdatee;
    private Integer expday;
    private String tclass;
    private String tclasscat;
    private String pubCouponGubun;
    private String isUse;
    private String regId;
    private Date regDt;
    private String updId;
    private Date updDt;

    // Additional fields for display
    private Integer useCnt;

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getRegtype() {
        return regtype;
    }

    public void setRegtype(String regtype) {
        this.regtype = regtype;
    }

    public Integer getRegprice() {
        return regprice;
    }

    public void setRegprice(Integer regprice) {
        this.regprice = regprice;
    }

    public Date getExpdates() {
        return expdates;
    }

    public void setExpdates(Date expdates) {
        this.expdates = expdates;
    }

    public Date getExpdatee() {
        return expdatee;
    }

    public void setExpdatee(Date expdatee) {
        this.expdatee = expdatee;
    }

    public Integer getExpday() {
        return expday;
    }

    public void setExpday(Integer expday) {
        this.expday = expday;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public String getTclasscat() {
        return tclasscat;
    }

    public void setTclasscat(String tclasscat) {
        this.tclasscat = tclasscat;
    }

    public String getPubCouponGubun() {
        return pubCouponGubun;
    }

    public void setPubCouponGubun(String pubCouponGubun) {
        this.pubCouponGubun = pubCouponGubun;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getRegDt() {
        return regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public Date getUpdDt() {
        return updDt;
    }

    public void setUpdDt(Date updDt) {
        this.updDt = updDt;
    }

    public Integer getUseCnt() {
        return useCnt;
    }

    public void setUseCnt(Integer useCnt) {
        this.useCnt = useCnt;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}