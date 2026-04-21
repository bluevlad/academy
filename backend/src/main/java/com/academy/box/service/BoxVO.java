package com.academy.box.service;


import com.academy.common.CommonVO;

/**
 * 사물함 마스터 VO 클래스 (TB_OFF_BOX 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            사물함 관리 신규 생성
 * </pre>
 */
public class BoxVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 사물함 코드 (Primary Key) */
    private String boxCd;

    /** 사물함명 */
    private String boxNm;

    /** 사물함 개수 */
    private int boxCount;

    /** 사물함 가격 */
    private int boxPrice;

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

    /** 사용중 사물함 수 (조회용) */
    private int yCnt;

    /** 미사용 사물함 수 (조회용) */
    private int nCnt;

    /** 검색 조건 - 검색어 */
    private String searchText;

    /** 검색 조건 - 검색타입 */
    private String searchType;

    // Getters and Setters
    public String getBoxCd() {
        return boxCd;
    }

    public void setBoxCd(String boxCd) {
        this.boxCd = boxCd;
    }

    public String getBoxNm() {
        return boxNm;
    }

    public void setBoxNm(String boxNm) {
        this.boxNm = boxNm;
    }

    public int getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(int boxCount) {
        this.boxCount = boxCount;
    }

    public int getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(int boxPrice) {
        this.boxPrice = boxPrice;
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

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
