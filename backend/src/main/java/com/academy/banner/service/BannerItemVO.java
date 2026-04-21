package com.academy.banner.service;


import com.academy.common.CommonVO;

/**
 * 배너 아이템 VO 클래스 (TB_BANNER_ITEM 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            배너 아이템 관리 신규 생성
 * </pre>
 */
public class BannerItemVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 배너 코드 (FK) */
    private String bannerCd;

    /** 배너 번호 */
    private int bannerNum;

    /** 사용자 ID */
    private String userId;

    /** 사용자명 (조회용) */
    private String userNm;

    /** 배너 플래그 (Y: 사용중, N: 미사용) */
    private String bannerFlag;

    /** 대여 SEQ */
    private int rentSeq;

    /** 노출 순서 */
    private int rolIdx;

    /** 배너 부제목 */
    private String bannerSubtitle;

    /** 배너 설명 */
    private String bannerNote;

    /** 배너 이미지 경로 */
    private String bannerImage;

    /** 배너 썸네일 이미지 경로 */
    private String bannerThumbnailImage;

    /** 배너 링크 URL */
    private String bannerLink;

    /** 배너 링크 타겟 (_blank, _self 등) */
    private String bannerLinkTarget;

    /** 배너 시작일 */
    private String bannerSdt;

    /** 배너 종료일 */
    private String bannerEdt;

    /** 클릭수 */
    private int clickCnt;

    /** 그룹 SEQ (카테고리 일괄 등록용) */
    private String gSeq;

    /** 배너명 (부모에서 가져옴, 조회용) */
    private String bannerNm;

    /** 배너 타입 (부모에서 가져옴, 조회용) */
    private String bannerTyp;

    /** 검색 조건 - 검색어 */
    private String searchText;

    // Getters and Setters
    public String getBannerCd() {
        return bannerCd;
    }

    public void setBannerCd(String bannerCd) {
        this.bannerCd = bannerCd;
    }

    public int getBannerNum() {
        return bannerNum;
    }

    public void setBannerNum(int bannerNum) {
        this.bannerNum = bannerNum;
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

    public String getBannerFlag() {
        return bannerFlag;
    }

    public void setBannerFlag(String bannerFlag) {
        this.bannerFlag = bannerFlag;
    }

    public int getRentSeq() {
        return rentSeq;
    }

    public void setRentSeq(int rentSeq) {
        this.rentSeq = rentSeq;
    }

    public int getRolIdx() {
        return rolIdx;
    }

    public void setRolIdx(int rolIdx) {
        this.rolIdx = rolIdx;
    }

    public String getBannerSubtitle() {
        return bannerSubtitle;
    }

    public void setBannerSubtitle(String bannerSubtitle) {
        this.bannerSubtitle = bannerSubtitle;
    }

    public String getBannerNote() {
        return bannerNote;
    }

    public void setBannerNote(String bannerNote) {
        this.bannerNote = bannerNote;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBannerThumbnailImage() {
        return bannerThumbnailImage;
    }

    public void setBannerThumbnailImage(String bannerThumbnailImage) {
        this.bannerThumbnailImage = bannerThumbnailImage;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }

    public String getBannerLinkTarget() {
        return bannerLinkTarget;
    }

    public void setBannerLinkTarget(String bannerLinkTarget) {
        this.bannerLinkTarget = bannerLinkTarget;
    }

    public String getBannerSdt() {
        return bannerSdt;
    }

    public void setBannerSdt(String bannerSdt) {
        this.bannerSdt = bannerSdt;
    }

    public String getBannerEdt() {
        return bannerEdt;
    }

    public void setBannerEdt(String bannerEdt) {
        this.bannerEdt = bannerEdt;
    }

    public int getClickCnt() {
        return clickCnt;
    }

    public void setClickCnt(int clickCnt) {
        this.clickCnt = clickCnt;
    }

    public String getgSeq() {
        return gSeq;
    }

    public void setgSeq(String gSeq) {
        this.gSeq = gSeq;
    }

    public String getBannerNm() {
        return bannerNm;
    }

    public void setBannerNm(String bannerNm) {
        this.bannerNm = bannerNm;
    }

    public String getBannerTyp() {
        return bannerTyp;
    }

    public void setBannerTyp(String bannerTyp) {
        this.bannerTyp = bannerTyp;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}