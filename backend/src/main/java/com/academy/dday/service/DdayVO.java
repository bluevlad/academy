package com.academy.dday.service;


import com.academy.common.CommonVO;

/**
 * D-Day VO 클래스 (TB_DDAY 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            D-Day 관리 신규 생성
 * </pre>
 */
public class DdayVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** D-Day 인덱스 */
    private Integer ddayIdx;

    /** 사용자 ID */
    private String userId;

    /** D-Day 타입 (P: 개인, G: 전체 등) */
    private String ddayType;

    /** D-Day 카테고리 코드 */
    private String ddayCategory;

    /** D-Day 카테고리명 (조회용) */
    private String ddayCategoryNm;

    /** D-Day 이름 */
    private String ddayName;

    /** D-Day 날짜 (YYYYMMDD 형식) */
    private String ddayDate;

    /** D-Day 링크 */
    private String ddayLink;

    /** D-Day 활성화 여부 */
    private String ddayActive;

    /** 등록일시 */
    private String regDate;

    /** 검색 - D-Day 이름 */
    private String searchDdayName;

    /** 검색 - 카테고리 */
    private String searchCategory;

    /** 메뉴 타입 (O: 온라인, F: 오프라인) */
    private String menuType;

    // Getters and Setters
    public Integer getDdayIdx() {
        return ddayIdx;
    }

    public void setDdayIdx(Integer ddayIdx) {
        this.ddayIdx = ddayIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDdayType() {
        return ddayType;
    }

    public void setDdayType(String ddayType) {
        this.ddayType = ddayType;
    }

    public String getDdayCategory() {
        return ddayCategory;
    }

    public void setDdayCategory(String ddayCategory) {
        this.ddayCategory = ddayCategory;
    }

    public String getDdayCategoryNm() {
        return ddayCategoryNm;
    }

    public void setDdayCategoryNm(String ddayCategoryNm) {
        this.ddayCategoryNm = ddayCategoryNm;
    }

    public String getDdayName() {
        return ddayName;
    }

    public void setDdayName(String ddayName) {
        this.ddayName = ddayName;
    }

    public String getDdayDate() {
        return ddayDate;
    }

    public void setDdayDate(String ddayDate) {
        this.ddayDate = ddayDate;
    }

    public String getDdayLink() {
        return ddayLink;
    }

    public void setDdayLink(String ddayLink) {
        this.ddayLink = ddayLink;
    }

    public String getDdayActive() {
        return ddayActive;
    }

    public void setDdayActive(String ddayActive) {
        this.ddayActive = ddayActive;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getSearchDdayName() {
        return searchDdayName;
    }

    public void setSearchDdayName(String searchDdayName) {
        this.searchDdayName = searchDdayName;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}
