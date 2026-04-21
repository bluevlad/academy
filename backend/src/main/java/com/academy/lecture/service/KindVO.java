package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Kind (Category) Value Object
 * TB_CATEGORY_INFO 테이블 매핑 VO
 */
public class KindVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Kind specific fields
    private String catCd;       // CAT_CD - 카테고리 코드
    private String catDiv;      // CAT_DIV - 카테고리 구분
    private String catNm;       // CAT_NM - 카테고리 이름
    private String pCatCd;      // P_CAT_CD - 부모 카테고리 코드
    private Integer catDepth;   // CAT_DEPTH - 카테고리 깊이
    private Integer catOrdr;    // CAT_ORDR - 카테고리 순서
    // isUse, regDt, regId, updDt, updId inherited from CommonVO

    // API parameter aliases
    private String code;        // CODE - alias for catCd
    private String name;        // NAME - alias for catNm
    private String seq;         // SEQ - alias for catOrdr
    private String num;         // NUM - number for sequence update
    private String codeNm;      // CODE_NM - code name for sequence update
    private String onOffDiv;    // ONOFFDIV - ON/OFF 구분
    private String seqUpdate;   // Seq_Update - 순서 업데이트 플래그

    // Display fields
    private String catDivNm;    // CAT_DIVNM - 카테고리 구분명
    private String isUseNm;     // ISUSENM - 사용여부명
    private String pCatNm;      // P_CAT_NM - 부모 카테고리명

    public String getCatCd() {
        return catCd;
    }

    public void setCatCd(String catCd) {
        this.catCd = catCd;
    }

    public String getCatDiv() {
        return catDiv;
    }

    public void setCatDiv(String catDiv) {
        this.catDiv = catDiv;
    }

    public String getCatNm() {
        return catNm;
    }

    public void setCatNm(String catNm) {
        this.catNm = catNm;
    }

    public String getpCatCd() {
        return pCatCd;
    }

    public void setpCatCd(String pCatCd) {
        this.pCatCd = pCatCd;
    }

    public Integer getCatDepth() {
        return catDepth;
    }

    public void setCatDepth(Integer catDepth) {
        this.catDepth = catDepth;
    }

    public Integer getCatOrdr() {
        return catOrdr;
    }

    public void setCatOrdr(Integer catOrdr) {
        this.catOrdr = catOrdr;
    }

    public String getCatDivNm() {
        return catDivNm;
    }

    public void setCatDivNm(String catDivNm) {
        this.catDivNm = catDivNm;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getpCatNm() {
        return pCatNm;
    }

    public void setpCatNm(String pCatNm) {
        this.pCatNm = pCatNm;
    }

    // API parameter alias getters/setters
    public String getCode() {
        return code != null ? code : catCd;
    }

    public void setCode(String code) {
        this.code = code;
        this.catCd = code;  // Sync with catCd
    }

    public String getName() {
        return name != null ? name : catNm;
    }

    public void setName(String name) {
        this.name = name;
        this.catNm = name;  // Sync with catNm
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getOnOffDiv() {
        return onOffDiv;
    }

    public void setOnOffDiv(String onOffDiv) {
        this.onOffDiv = onOffDiv;
    }

    public String getSeqUpdate() {
        return seqUpdate;
    }

    public void setSeqUpdate(String seqUpdate) {
        this.seqUpdate = seqUpdate;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}