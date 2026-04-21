package com.academy.board.service;

import java.io.Serializable;

/**
 * 게시판 타입 VO 클래스 (TB_BA_CONFIG_CD 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.22         system            게시판 타입 VO 생성
 * </pre>
 */
public class BoardTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 코드 CD */
    private String codeCd;

    /** 코드명 */
    private String codeNm;

    // Getters and Setters
    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }
}
