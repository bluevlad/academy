package com.academy.box.service;


import com.academy.common.CommonVO;

/**
 * 사물함 세부정보 VO 클래스 (TB_OFF_BOX_NUM 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            사물함 세부정보 관리 신규 생성
 * </pre>
 */
public class BoxNumVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 사물함 코드 (FK) */
    private String boxCd;

    /** 사물함 번호 */
    private int boxNum;

    /** 사용자 ID */
    private String userId;

    /** 사용자명 (조회용) */
    private String userNm;

    /** 사물함 사용 플래그 (Y: 사용중, N: 미사용) */
    private String boxFlag;

    /** 대여 SEQ */
    private Integer rentSeq;

    /** 대여 메모 */
    private String rentMemo;

    /** 사물함명 (부모에서 가져옴, 조회용) */
    private String boxNm;

    /** 검색 조건 - 검색어 */
    private String searchText;

    // Getters and Setters
    public String getBoxCd() {
        return boxCd;
    }

    public void setBoxCd(String boxCd) {
        this.boxCd = boxCd;
    }

    public int getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
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

    public String getBoxFlag() {
        return boxFlag;
    }

    public void setBoxFlag(String boxFlag) {
        this.boxFlag = boxFlag;
    }

    public Integer getRentSeq() {
        return rentSeq;
    }

    public void setRentSeq(Integer rentSeq) {
        this.rentSeq = rentSeq;
    }

    public String getRentMemo() {
        return rentMemo;
    }

    public void setRentMemo(String rentMemo) {
        this.rentMemo = rentMemo;
    }

    public String getBoxNm() {
        return boxNm;
    }

    public void setBoxNm(String boxNm) {
        this.boxNm = boxNm;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
