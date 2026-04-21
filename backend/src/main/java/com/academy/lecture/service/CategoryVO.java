package com.academy.lecture.service;


import com.academy.common.CommonVO;

/**
 * Category Value Object
 * TB_CATEGORY_INFO 테이블 매핑 VO
 */
public class CategoryVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // Category specific fields
    private Integer id;             // ID - 시퀀스 ID
    private String code;            // CODE - 카테고리 코드
    private String name;            // NAME - 카테고리명
    private String pCode;           // P_CODE - 부모 코드
    private Integer ordr;           // ORDR - 순서
    private String useOn;           // USE_ON - 온라인 사용 여부
    private String useOff;          // USE_OFF - 오프라인 사용 여부
    private String srsCd;           // SRS_CD - 시리즈 코드
    private String typeChoice;      // TYPE_CHOICE - 타입 선택 (C, S 등)
    // isUse, regDt, regId, updDt, updId inherited from CommonVO

    // Display fields (트리 구조 및 조회용)
    private Integer level;          // LEVEL - 트리 레벨
    private String isUseNm;         // ISUSENM - 사용여부명
    private String pName;           // P_NAME - 부모 카테고리명

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Integer getOrdr() {
        return ordr;
    }

    public void setOrdr(Integer ordr) {
        this.ordr = ordr;
    }

    public String getUseOn() {
        return useOn;
    }

    public void setUseOn(String useOn) {
        this.useOn = useOn;
    }

    public String getUseOff() {
        return useOff;
    }

    public void setUseOff(String useOff) {
        this.useOff = useOff;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getSrsCd() {
        return srsCd;
    }

    public void setSrsCd(String srsCd) {
        this.srsCd = srsCd;
    }

    public String getTypeChoice() {
        return typeChoice;
    }

    public void setTypeChoice(String typeChoice) {
        this.typeChoice = typeChoice;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
    }
}