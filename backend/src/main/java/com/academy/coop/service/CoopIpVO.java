package com.academy.coop.service;


import com.academy.common.CommonVO;

/**
 * 제휴사 IP VO 클래스 (COOP_USE_IP 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            제휴사 IP 관리 신규 생성
 * </pre>
 */
public class CoopIpVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 제휴사 코드 */
    private String coopCd;

    /** 사용자 IP */
    private String userIp;

    /** 기존 사용자 IP (삭제용) */
    private String oldUserIp;

    // Getters and Setters
    public String getCoopCd() {
        return coopCd;
    }

    public void setCoopCd(String coopCd) {
        this.coopCd = coopCd;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getOldUserIp() {
        return oldUserIp;
    }

    public void setOldUserIp(String oldUserIp) {
        this.oldUserIp = oldUserIp;
    }
}
