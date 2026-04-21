package com.academy.board.service;

import java.io.Serializable;

/**
 * 카테고리 정보 VO 클래스 (TB_CATEGORY_INFO 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.22         system            카테고리 정보 VO 생성
 * </pre>
 */
public class CategoryInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 코드 */
    private String code;

    /** 명칭 */
    private String name;

    /** 사용여부 */
    private String isUse;

    /** 사용여부명 (조회용) */
    private String isUseNm;

    /** 등록일시 */
    private String regDt;

    /** 등록자 */
    private String regId;

    /** 수정일시 */
    private String updDt;

    /** 수정자 */
    private String updId;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getIsUseNm() {
        return isUseNm;
    }

    public void setIsUseNm(String isUseNm) {
        this.isUseNm = isUseNm;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getUpdDt() {
        return updDt;
    }

    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }
}
