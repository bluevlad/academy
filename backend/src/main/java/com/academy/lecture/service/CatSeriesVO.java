package com.academy.lecture.service;

import java.io.Serializable;
import java.util.Date;

/**
 * CategorySeries Value Object
 * TB_CATEGORY_SERIES 테이블 매핑 VO
 * 카테고리와 시리즈의 매핑 테이블 (Bridge/Join Table)
 */
public class CatSeriesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // CategorySeries specific fields
    private Integer seq;        // SEQ - 시퀀스
    private String catCd;       // CAT_CD - 카테고리 코드
    private String srsCd;       // SRS_CD - 시리즈 코드
    private Integer ordr;       // ORDR - 순서
    private Date regDt;         // REG_DT - 등록일
    private String regId;       // REG_ID - 등록자

    // Getters and Setters
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public String getSrsCd() {
        return srsCd;
    }

    public void setSrsCd(String srsCd) {
        this.srsCd = srsCd;
    }

    public Integer getOrdr() {
        return ordr;
    }

    public void setOrdr(Integer ordr) {
        this.ordr = ordr;
    }

    public Date getRegDt() {
        return regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}