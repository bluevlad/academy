package com.academy.admin.service;

import com.academy.common.CommonVO;

/**
 * 공통코드 VO 클래스 - TB_BA_CONFIG_CD 테이블
 * 
 * @author system
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            최초 생성
 *      </pre>
 */
public class AdminCodeVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 시스템 코드 (SYS_CD) */
    private String sysCd;
    /** 시스템명 (SYS_NM) */
    private String sysNm;
    /** 코드 번호 (CODE_NO) */
    private Integer codeNo;
    /** 코드 CD (CODE_CD) */
    private String codeCd;
    /** 코드명 (CODE_NM) */
    private String codeNm;
    /** 코드 값 (CODE_VAL) */
    private String codeVal;
    /** 코드 설명 (CODE_INFO) */
    private String codeInfo;
    /** 코드 순서 (CODE_SEQ) */
    private Integer codeSeq;
    /** 레벨 (계층 구조용) */
    private Integer level;

    /** 상세 조회용 코드 번호 */
    private Integer detailCodeNo;
    /** 삭제할 코드 번호 목록 */
    private String deleteIds;

    // Getters and Setters
    public String getSysCd() {
        return sysCd;
    }

    public void setSysCd(String sysCd) {
        this.sysCd = sysCd;
    }

    public String getSysNm() {
        return sysNm;
    }

    public void setSysNm(String sysNm) {
        this.sysNm = sysNm;
    }

    public Integer getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(Integer codeNo) {
        this.codeNo = codeNo;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getCodeVal() {
        return codeVal;
    }

    public void setCodeVal(String codeVal) {
        this.codeVal = codeVal;
    }

    public String getCodeInfo() {
        return codeInfo;
    }

    public void setCodeInfo(String codeInfo) {
        this.codeInfo = codeInfo;
    }

    public Integer getCodeSeq() {
        return codeSeq;
    }

    public void setCodeSeq(Integer codeSeq) {
        this.codeSeq = codeSeq;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDetailCodeNo() {
        return detailCodeNo;
    }

    public void setDetailCodeNo(Integer detailCodeNo) {
        this.detailCodeNo = detailCodeNo;
    }

    public String getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(String deleteIds) {
        this.deleteIds = deleteIds;
    }
}
