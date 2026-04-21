package com.academy.admin.service;


import com.academy.common.CommonVO;

/**
 * 권한별 메뉴 VO 클래스 - TB_SG_SITE_MENU 테이블
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
public class AdminSiteMenuVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 권한 ID (SITE_ID) */
    private String siteId;
    /** 메뉴 ID (MENU_ID) */
    private String menuId;

    // Getters and Setters
    public String getSiteId() {
        return siteId;
    }
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
