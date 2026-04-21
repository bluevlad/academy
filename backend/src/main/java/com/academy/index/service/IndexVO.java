package com.academy.index.service;

import com.academy.common.CommonVO;

/**
 * 인덱스/메뉴 관리 VO
 * - 메뉴 관리 (TB_SG_MENU_MST, TB_SG_SITE_MENU)
 * - 로그인 이력 관리
 */
public class IndexVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    // === 메뉴 정보 (TB_SG_MENU_MST) ===
    private String menuId;          // 메뉴 ID
    private String menuNm;          // 메뉴명
    private String pMenuId;         // 상위 메뉴 ID
    private String menuUrl;         // 메뉴 URL
    private Integer menuSeq;        // 메뉴 순서
    private String target;          // 타겟 (_self, _blank 등)
    private String isuse;           // 사용여부 (Y/N)
    private Integer menuLevel;      // 메뉴 레벨 (트리 깊이)

    // === 사이트 메뉴 (TB_SG_SITE_MENU) ===
    private String siteId;          // 사이트 ID (권한 역할)

    // === 사용자 정보 ===
    private String userId;          // 사용자 ID
    private String userNm;          // 사용자명
    private String adminRole;       // 관리자 권한
    private String onoffDiv;        // 온/오프라인 구분 (A/O/F/T)

    // === 로그인 이력 ===
    private String loginIp;         // 로그인 IP
    private String loginDate;       // 로그인 일시
    private Integer logCnt;         // 로그 조회 개수

    // === 검색/필터 조건 ===
    private String menuType;        // 메뉴 타입 (OM_ROOT, FM_ROOT, TM_ROOT)
    private String topMenuId;       // 탑 메뉴 ID
    private String searchKeyword;   // 키워드 검색

    // === Getters and Setters ===

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(String pMenuId) {
        this.pMenuId = pMenuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuSeq() {
        return menuSeq;
    }

    public void setMenuSeq(Integer menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLogCnt() {
        return logCnt;
    }

    public void setLogCnt(Integer logCnt) {
        this.logCnt = logCnt;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getTopMenuId() {
        return topMenuId;
    }

    public void setTopMenuId(String topMenuId) {
        this.topMenuId = topMenuId;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
