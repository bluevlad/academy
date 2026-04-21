package com.academy.coop.service;


import com.academy.common.CommonVO;

/**
 * 제휴사 마스터 VO 클래스 (COOP_MST 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            제휴사 관리 신규 생성
 * </pre>
 */
public class CoopVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 제휴사 코드 */
    private String coopCd;

    /** 제휴사명 */
    private String coopNm;

    /** 할인율 */
    private Integer discountPer;

    /** 제휴사 설명 */
    private String coopDesc;

    /** IP 수 (조회용) */
    private int ipCnt;

    // Getters and Setters
    public String getCoopCd() {
        return coopCd;
    }

    public void setCoopCd(String coopCd) {
        this.coopCd = coopCd;
    }

    public String getCoopNm() {
        return coopNm;
    }

    public void setCoopNm(String coopNm) {
        this.coopNm = coopNm;
    }

    public Integer getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Integer discountPer) {
        this.discountPer = discountPer;
    }

    public String getCoopDesc() {
        return coopDesc;
    }

    public void setCoopDesc(String coopDesc) {
        this.coopDesc = coopDesc;
    }

    public int getIpCnt() {
        return ipCnt;
    }

    public void setIpCnt(int ipCnt) {
        this.ipCnt = ipCnt;
    }
}
