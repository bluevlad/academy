package com.academy.banner.service;


import com.academy.common.CommonVO;

/**
 * 배너 마스터 VO 클래스 (TB_BANNER 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            배너 관리 신규 생성
 * </pre>
 */
public class BannerVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 배너 코드 (Primary Key) */
    private String bannerCd;

    /** 배너명 */
    private String bannerNm;

    /** 배너 개수 */
    private int bannerCount;

    /** 배너 가격 */
    private int bannerPrice;

    /** 보증금 */
    private int deposit;

    /** 행 개수 */
    private int rowCount;

    /** 행 번호 */
    private int rowNum;

    /** 시작 번호 */
    private int startNum;

    /** 종료 번호 */
    private int endNum;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String onoffDiv;

    /** 화면 구분 (M: 메인홈, H: 모바일홈, S: 서브페이지) */
    private String screenGubun;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 배너 번호 */
    private String bannerNo;

    /** 배너 타입 (I: 이미지, L: 강의, B: 게시판, P: 사람, T: 모의고사) */
    private String bannerTyp;

    /** 게시 시작일 */
    private String openStartdate;

    /** 게시 종료일 */
    private String openEnddate;

    /** 조회수 */
    private int viewCount;

    /** 사용중 배너아이템 수 (조회용) */
    private int yCnt;

    /** 미사용 배너아이템 수 (조회용) */
    private int nCnt;

    /** 검색 조건 - 메뉴타입 */
    private String menuType;

    /** 검색 조건 - 검색어 */
    private String searchText;

    /** 검색 조건 - 카테고리 */
    private String searchCategory;

    /** 검색 조건 - 배너번호 */
    private String searchBannerNo;

    // Getters and Setters
    public String getBannerCd() {
        return bannerCd;
    }

    public void setBannerCd(String bannerCd) {
        this.bannerCd = bannerCd;
    }

    public String getBannerNm() {
        return bannerNm;
    }

    public void setBannerNm(String bannerNm) {
        this.bannerNm = bannerNm;
    }

    public int getBannerCount() {
        return bannerCount;
    }

    public void setBannerCount(int bannerCount) {
        this.bannerCount = bannerCount;
    }

    public int getBannerPrice() {
        return bannerPrice;
    }

    public void setBannerPrice(int bannerPrice) {
        this.bannerPrice = bannerPrice;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getScreenGubun() {
        return screenGubun;
    }

    public void setScreenGubun(String screenGubun) {
        this.screenGubun = screenGubun;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getBannerNo() {
        return bannerNo;
    }

    public void setBannerNo(String bannerNo) {
        this.bannerNo = bannerNo;
    }

    public String getBannerTyp() {
        return bannerTyp;
    }

    public void setBannerTyp(String bannerTyp) {
        this.bannerTyp = bannerTyp;
    }

    public String getOpenStartdate() {
        return openStartdate;
    }

    public void setOpenStartdate(String openStartdate) {
        this.openStartdate = openStartdate;
    }

    public String getOpenEnddate() {
        return openEnddate;
    }

    public void setOpenEnddate(String openEnddate) {
        this.openEnddate = openEnddate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getyCnt() {
        return yCnt;
    }

    public void setyCnt(int yCnt) {
        this.yCnt = yCnt;
    }

    public int getnCnt() {
        return nCnt;
    }

    public void setnCnt(int nCnt) {
        this.nCnt = nCnt;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchBannerNo() {
        return searchBannerNo;
    }

    public void setSearchBannerNo(String searchBannerNo) {
        this.searchBannerNo = searchBannerNo;
    }
}
