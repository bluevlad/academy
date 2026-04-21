package com.academy.menu.service;


import com.academy.common.CommonVO;

public class MenuVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private String onoffDiv;
    private String menuId;
    private String menuNm;
    private String menuSeq;
    private String menuUrl;
    private String pMenuid;
    private String isuse;
    private String target;
    private String menuInfo;
    private String code;
    private String topImgUrl;
    private String leftImgUrl;
    private String titlImgUrl;
    private String subtitlImgUrl;
    // regId, regDt, updId, updDt inherited from CommonVO

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

    public String getpMenuid() {
        return pMenuid;
    }

    public void setpMenuid(String pMenuid) {
        this.pMenuid = pMenuid;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
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

    // regId, regDt, updId, updDt getter/setter inherited from CommonVO

}