package com.academy.counsel.service;


import com.academy.common.CommonVO;

/**
 * 설명회 신청 VO 클래스 (TB_PRESENT 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            설명회 신청 관리 신규 생성
 * </pre>
 */
public class PresentRequestVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 사용자 ID */
    private String userId;

    /** 대학 코드 */
    private String univCd;

    /** 코드명 (조회용) */
    private String codeNm;

    /** 사용자명 */
    private String userNm;

    /** 전화번호 */
    private String phoneNo;

    /** 이메일 */
    private String email;

    /** 코멘트 */
    private String coments;

    /** 검색 코드 */
    private String searchCode;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnivCd() {
        return univCd;
    }

    public void setUnivCd(String univCd) {
        this.univCd = univCd;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }
}
