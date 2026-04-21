package com.academy.board.service;

import java.io.Serializable;

/**
 * 게시판 카테고리 코드 VO 클래스 (TB_BOARD_CATEGORY_INFO 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.22         system            게시판 카테고리 코드 VO 생성
 * </pre>
 */
public class BoardCodeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 카테고리 코드 */
    private String categoryCode;

    // Getters and Setters
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
