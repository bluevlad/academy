package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Series Value Object
 * TB_SERIES_INFO 테이블 매핑 VO
 */
public class SeriesVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Series specific fields
    private String srsCd;       // SRS_CD - 시리즈 코드
    private String srsNm;       // SRS_NM - 시리즈 이름 (SRS_NAME)
    private String srsDiv;      // SRS_DIV - 시리즈 구분
    private String srsYn;       // SRS_YN - 시리즈 사용 여부
    private String srsMemo;     // SRS_MEMO - 시리즈 메모
    // isUse (maps to USE_YN), regDt, regId, updDt, updId inherited from CommonVO

    // Additional fields for API
    private String[] catCds;    // CAT_CD[] - 카테고리 코드 배열
    private String[] srsCds;    // SRS_CD[] - 시리즈 코드 배열 (다중 삭제용)
    private String catCd;       // CAT_CD - 카테고리 코드 (단일)
    private Integer ordr;       // ORDR - 순서

    // Display fields
    private String srsDivNm;    // SRS_DIVNM - 시리즈 구분명
    private String isUseNm;     // ISUSENM - 사용여부명

    public String getSrsCd() {
        return srsCd;
    }

    public void setSrsCd(String srsCd) {
        this.srsCd = srsCd;
    }

    public String getSrsNm() {
        return srsNm;
    }

    public void setSrsNm(String srsNm) {
        this.srsNm = srsNm;
    }

    public String getSrsDiv() {
        return srsDiv;
    }

    public void setSrsDiv(String srsDiv) {
        this.srsDiv = srsDiv;
    }

    public String getSrsDivNm() {
        return srsDivNm;
    }

    public void setSrsDivNm(String srsDivNm) {
        this.srsDivNm = srsDivNm;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getSrsYn() {
        return srsYn;
    }

    public void setSrsYn(String srsYn) {
        this.srsYn = srsYn;
    }

    public String getSrsMemo() {
        return srsMemo;
    }

    public void setSrsMemo(String srsMemo) {
        this.srsMemo = srsMemo;
    }

    public String[] getCatCds() {
        return catCds;
    }

    public void setCatCds(String[] catCds) {
        this.catCds = catCds;
    }

    public String[] getSrsCds() {
        return srsCds;
    }

    public void setSrsCds(String[] srsCds) {
        this.srsCds = srsCds;
    }

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public Integer getOrdr() {
        return ordr;
    }

    public void setOrdr(Integer ordr) {
        this.ordr = ordr;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}