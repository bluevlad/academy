package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Subject Value Object
 * TB_SUBJECT_INFO 테이블 매핑 VO
 */
public class SubjectVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Subject specific fields
    private String sjtCd;           // SJT_CD - 과목 코드
    private String sjtNm;           // SJT_NM - 과목 이름
    private String pSjtCd;          // P_SJT_CD - 부모 과목 코드
    private Integer sjtDepth;       // SJT_DEPTH - 과목 깊이
    private Integer sjtOrdr;        // SJT_ORDR - 과목 순서

    // Additional fields for API
    private String subjectCd;       // SUBJECT_CD - 강의 과목 코드
    private String subjectNm;       // SUBJECT_NM - 강의 과목명
    private String onoffDiv;        // ONOFF_DIV - 온오프 구분
    private String categoryCode;    // CATEGORY_CODE - 카테고리 코드
    private String[] categoryCodes; // CATEGORY_CODE[] - 카테고리 코드 배열
    // gubun inherited from CommonVO
    // isUse, regDt, regId, updDt, updId inherited from CommonVO

    // Display fields
    private String isUseNm;         // ISUSENM - 사용여부명
    private String pSjtNm;          // P_SJT_NM - 부모 과목명

    public String getSjtCd() {
        return sjtCd;
    }

    public void setSjtCd(String sjtCd) {
        this.sjtCd = sjtCd;
    }

    public String getSjtNm() {
        return sjtNm;
    }

    public void setSjtNm(String sjtNm) {
        this.sjtNm = sjtNm;
    }

    public String getpSjtCd() {
        return pSjtCd;
    }

    public void setpSjtCd(String pSjtCd) {
        this.pSjtCd = pSjtCd;
    }

    public Integer getSjtDepth() {
        return sjtDepth;
    }

    public void setSjtDepth(Integer sjtDepth) {
        this.sjtDepth = sjtDepth;
    }

    public Integer getSjtOrdr() {
        return sjtOrdr;
    }

    public void setSjtOrdr(Integer sjtOrdr) {
        this.sjtOrdr = sjtOrdr;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getpSjtNm() {
        return pSjtNm;
    }

    public void setpSjtNm(String pSjtNm) {
        this.pSjtNm = pSjtNm;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectNm() {
        return subjectNm;
    }

    public void setSubjectNm(String subjectNm) {
        this.subjectNm = subjectNm;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String[] getCategoryCodes() {
        return categoryCodes;
    }

    public void setCategoryCodes(String[] categoryCodes) {
        this.categoryCodes = categoryCodes;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}