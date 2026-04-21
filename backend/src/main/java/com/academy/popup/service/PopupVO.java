package com.academy.popup.service;

import com.academy.common.CommonVO;

/**
 * 팝업 관리 VO
 */
public class PopupVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private int popupNo;
    private String onoffDiv;
    private String categoryCd;
    private String categoryNm;
    private String title;
    private String popupImg;
    private String popupImgNm;
    private String thumbnail;
    private String thumbnailNm;
    private String linkAddr;
    private String linkTarget;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String openYn;
    private String openYnNm;
    private int hit;
    private String regId;
    private String regDate;
    private String modId;
    private String modDate;
    private String searchKind;
    private String searchText;
    private String searchOnoffDiv;

    public int getPopupNo() { return popupNo; }
    public void setPopupNo(int popupNo) { this.popupNo = popupNo; }
    public String getOnoffDiv() { return onoffDiv; }
    public void setOnoffDiv(String onoffDiv) { this.onoffDiv = onoffDiv; }
    public String getCategoryCd() { return categoryCd; }
    public void setCategoryCd(String categoryCd) { this.categoryCd = categoryCd; }
    public String getCategoryNm() { return categoryNm; }
    public void setCategoryNm(String categoryNm) { this.categoryNm = categoryNm; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPopupImg() { return popupImg; }
    public void setPopupImg(String popupImg) { this.popupImg = popupImg; }
    public String getPopupImgNm() { return popupImgNm; }
    public void setPopupImgNm(String popupImgNm) { this.popupImgNm = popupImgNm; }
    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
    public String getThumbnailNm() { return thumbnailNm; }
    public void setThumbnailNm(String thumbnailNm) { this.thumbnailNm = thumbnailNm; }
    public String getLinkAddr() { return linkAddr; }
    public void setLinkAddr(String linkAddr) { this.linkAddr = linkAddr; }
    public String getLinkTarget() { return linkTarget; }
    public void setLinkTarget(String linkTarget) { this.linkTarget = linkTarget; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public String getOpenYn() { return openYn; }
    public void setOpenYn(String openYn) { this.openYn = openYn; }
    public String getOpenYnNm() { return openYnNm; }
    public void setOpenYnNm(String openYnNm) { this.openYnNm = openYnNm; }
    public int getHit() { return hit; }
    public void setHit(int hit) { this.hit = hit; }
    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
    public String getModId() { return modId; }
    public void setModId(String modId) { this.modId = modId; }
    public String getModDate() { return modDate; }
    public void setModDate(String modDate) { this.modDate = modDate; }
    public String getSearchKind() { return searchKind; }
    public void setSearchKind(String searchKind) { this.searchKind = searchKind; }
    public String getSearchText() { return searchText; }
    public void setSearchText(String searchText) { this.searchText = searchText; }
    public String getSearchOnoffDiv() { return searchOnoffDiv; }
    public void setSearchOnoffDiv(String searchOnoffDiv) { this.searchOnoffDiv = searchOnoffDiv; }
}
