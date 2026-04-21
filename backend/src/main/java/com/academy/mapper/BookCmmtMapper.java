package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.bookCmmt.service.BookCmmtVO;

/**
 * BookCmmt Mapper Interface
 * bookCmmnt.xml 매퍼 파일과 연동
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 */
@Mapper
public interface BookCmmtMapper {

    /**
     * 교재 구매 후기 목록 조회
     */
    List<HashMap<String, Object>> bookCmmtList(BookCmmtVO bookCmmtVO);

    /**
     * 교재 구매 후기 목록 카운트
     */
    int bookCmmtListCount(BookCmmtVO bookCmmtVO);

    /**
     * 교재 구매 후기 상세 목록 조회
     */
    List<HashMap<String, Object>> bookCmmtDetailList(BookCmmtVO bookCmmtVO);

    /**
     * 교재 구매 후기 상세 목록 카운트
     */
    int bookCmmtDetailListCount(BookCmmtVO bookCmmtVO);

    /**
     * 교재 구매 후기 삭제
     */
    void cmmtDelete(BookCmmtVO bookCmmtVO);
}
