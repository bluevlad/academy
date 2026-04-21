package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Form (Learning Form) Value Object
 * TB_LEARNING_FORM_INFO 테이블 매핑 VO
 */
public class FormVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Form specific fields
    private String lecDiv;      // LEC_DIV - 강좌 구분
    private String code;        // CODE - 코드
    private String name;        // NAME - 이름
    private String seq;         // SEQ - 순서
    private String pcode;       // PCODE - 부모 코드
    private String onOffDiv;    // ONOFFDIV - ON/OFF 구분
    // isUse inherited from CommonVO (ISUSE)
    // regDt, regId, updDt, updId inherited from CommonVO

    // Display fields
    private String lecDivNm;    // LEC_DIVNM - 강좌 구분명
    private String isUseNm;     // ISUSENM - 사용여부명

    // Search fields
    private String searchPcode;      // SEARCHPCODE - 검색용 부모 코드
    // searchCodeIsUse inherited from CommonVO

    public String getLecDiv() {
        return lecDiv;
    }

    public void setLecDiv(String lecDiv) {
        this.lecDiv = lecDiv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecDivNm() {
        return lecDivNm;
    }

    public void setLecDivNm(String lecDivNm) {
        this.lecDivNm = lecDivNm;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getOnOffDiv() {
        return onOffDiv;
    }

    public void setOnOffDiv(String onOffDiv) {
        this.onOffDiv = onOffDiv;
    }

    public String getSearchPcode() {
        return searchPcode;
    }

    public void setSearchPcode(String searchPcode) {
        this.searchPcode = searchPcode;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}