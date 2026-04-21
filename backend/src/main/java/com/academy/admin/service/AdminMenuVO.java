package com.academy.admin.service;


import com.academy.common.CommonVO;

/**
 * 관리자 메뉴 VO 클래스 - TB_SG_MENU_MST 테이블
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
public class AdminMenuVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 온라인/오프라인 구분 (ONOFF_DIV) */
    private String onoffDiv;
    /** 메뉴 ID (MENU_ID) */
    private String menuId;
    /** 메뉴명 (MENU_NM) */
    private String menuNm;
    /** 메뉴 순서 (MENU_SEQ) */
    private String menuSeq;
    /** 메뉴 URL (MENU_URL) */
    private String menuUrl;
    /** 부모 메뉴 ID (P_MENUID) */
    private String pMenuId;
    /** 타겟 (TARGET) */
    private String target;
    /** 메뉴 설명 (MENU_INFO) */
    private String menuInfo;

    /** 상위 부모 메뉴 재귀 목록 */
    private String upPMenuIdRecur;
    /** 하위 자식 메뉴 재귀 목록 */
    private String downPMenuIdRecur;
    /** 레벨 */
    private Integer level;

    /** 사용자 메뉴 추가 필드 (TB_SG_MENU_MST2) */
    /** 코드 (CODE) */
    private String code;
    /** 상단 이미지 URL (TOP_IMG_URL) */
    private String topImgUrl;
    /** 좌측 이미지 URL (LEFT_IMG_URL) */
    private String leftImgUrl;
    /** 타이틀 이미지 URL (TITL_IMG_URL) */
    private String titlImgUrl;
    /** 서브타이틀 이미지 URL (SUBTITL_IMG_URL) */
    private String subtitlImgUrl;

    // Getters and Setters
    public String getOnoffDiv() {
        return onoffDiv;
    }
    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

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

    public String getMenuSeq() {
        return menuSeq;
    }
    public void setMenuSeq(String menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getpMenuId() {
        return pMenuId;
    }
    public void setpMenuId(String pMenuId) {
        this.pMenuId = pMenuId;
    }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public String getMenuInfo() {
        return menuInfo;
    }
    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo;
    }

    public String getUpPMenuIdRecur() {
        return upPMenuIdRecur;
    }
    public void setUpPMenuIdRecur(String upPMenuIdRecur) {
        this.upPMenuIdRecur = upPMenuIdRecur;
    }

    public String getDownPMenuIdRecur() {
        return downPMenuIdRecur;
    }
    public void setDownPMenuIdRecur(String downPMenuIdRecur) {
        this.downPMenuIdRecur = downPMenuIdRecur;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getTopImgUrl() {
        return topImgUrl;
    }
    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    public String getLeftImgUrl() {
        return leftImgUrl;
    }
    public void setLeftImgUrl(String leftImgUrl) {
        this.leftImgUrl = leftImgUrl;
    }

    public String getTitlImgUrl() {
        return titlImgUrl;
    }
    public void setTitlImgUrl(String titlImgUrl) {
        this.titlImgUrl = titlImgUrl;
    }

    public String getSubtitlImgUrl() {
        return subtitlImgUrl;
    }
    public void setSubtitlImgUrl(String subtitlImgUrl) {
        this.subtitlImgUrl = subtitlImgUrl;
    }
}
