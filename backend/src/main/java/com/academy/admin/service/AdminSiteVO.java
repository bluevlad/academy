package com.academy.admin.service;


import com.academy.common.CommonVO;

/**
 * 권한(사이트) VO 클래스 - TB_SG_SITE 테이블
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            최초 생성
 * </pre>
 */
public class AdminSiteVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 권한 ID (SITE_ID) */
    private String siteId;
    /** 권한명 (SITE_NM) */
    private String siteNm;
    /** 온라인/오프라인 구분 (ONOFF_DIV) - A:전체, O:온라인, F:오프라인, T:모의고사 */
    private String onoffDiv;
    /** 온라인/오프라인 구분명 */
    private String onoffDivNm;

    /** 상세 조회용 권한 ID */
    private String detailSiteId;
    /** 삭제할 권한 ID 목록 */
    private String deleteIds;
    /** 메뉴 온오프 구분 (ON/OFF/TEST) */
    private String menuOnoff;
    /** 메뉴 ID (권한별 메뉴 등록용) */
    private String menuId;

    // Getters and Setters
    public String getSiteId() {
        return siteId;
    }
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteNm() {
        return siteNm;
    }
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }
    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getOnoffDivNm() {
        return onoffDivNm;
    }
    public void setOnoffDivNm(String onoffDivNm) {
        this.onoffDivNm = onoffDivNm;
    }

    public String getDetailSiteId() {
        return detailSiteId;
    }
    public void setDetailSiteId(String detailSiteId) {
        this.detailSiteId = detailSiteId;
    }

    public String getDeleteIds() {
        return deleteIds;
    }
    public void setDeleteIds(String deleteIds) {
        this.deleteIds = deleteIds;
    }

    public String getMenuOnoff() {
        return menuOnoff;
    }
    public void setMenuOnoff(String menuOnoff) {
        this.menuOnoff = menuOnoff;
    }

    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
