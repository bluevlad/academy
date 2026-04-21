package com.academy.bookCmmt.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.BookCmmtMapper;

/**
 * BookCmmt Service
 * 교재구매후기 게시판 서비스
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 */
@Service
public class BookCmmtService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BookCmmtMapper bookCmmtMapper;

    @Autowired
    public BookCmmtService(BookCmmtMapper bookCmmtMapper) {
        this.bookCmmtMapper = bookCmmtMapper;
    }

    /**
     * 교재 구매 후기 목록 조회
     */
    public List<HashMap<String, Object>> bookCmmtList(BookCmmtVO bookCmmtVO) {
        return bookCmmtMapper.bookCmmtList(bookCmmtVO);
    }

    /**
     * 교재 구매 후기 목록 카운트
     */
    public int bookCmmtListCount(BookCmmtVO bookCmmtVO) {
        return bookCmmtMapper.bookCmmtListCount(bookCmmtVO);
    }

    /**
     * 교재 구매 후기 상세 목록 조회
     */
    public List<HashMap<String, Object>> bookCmmtDetailList(BookCmmtVO bookCmmtVO) {
        return bookCmmtMapper.bookCmmtDetailList(bookCmmtVO);
    }

    /**
     * 교재 구매 후기 상세 목록 카운트
     */
    public int bookCmmtDetailListCount(BookCmmtVO bookCmmtVO) {
        return bookCmmtMapper.bookCmmtDetailListCount(bookCmmtVO);
    }

    /**
     * 교재 구매 후기 삭제
     */
    public void cmmtDelete(BookCmmtVO bookCmmtVO) {
        bookCmmtMapper.cmmtDelete(bookCmmtVO);
    }
}
