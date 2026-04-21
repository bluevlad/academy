package com.academy.book.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.BookMapper;

/**
 * Book Service
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 */
@Service
public class BookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 교재 목록 조회
     */
    public List<HashMap<String, String>> bookList(BookVO bookVO) {
        return bookMapper.bookList(bookVO);
    }

    /**
     * 교재 목록 카운트
     */
    public int bookListCount(BookVO bookVO) {
        return bookMapper.bookListCount(bookVO);
    }

    /**
     * 학습형태 목록 조회
     */
    public List<HashMap<String, String>> getLearningFormList(BookVO bookVO) {
        return bookMapper.getLearningFormList(bookVO);
    }

    /**
     * 과목(강사) 목록 조회
     */
    public List<HashMap<String, String>> getCaSubjectTeacherList(BookVO bookVO) {
        return bookMapper.getCaSubjectTeacherList(bookVO);
    }

    /**
     * 교재 SEQ 조회
     */
    public int getCaBookSeq(BookVO bookVO) {
        return bookMapper.getCaBookSeq(bookVO);
    }

    /**
     * 교재 RSC_ID 생성
     */
    public String getCaBookRscId(BookVO bookVO) {
        return bookMapper.getCaBookRscId(bookVO);
    }

    /**
     * 교재 등록
     */
    public void bookInsert(BookVO bookVO) {
        bookMapper.bookInsert(bookVO);
    }

    /**
     * 교재 상세 목록 조회 (관련 도서)
     */
    public List<HashMap<String, String>> bookViewList(BookVO bookVO) {
        return bookMapper.bookViewList(bookVO);
    }

    /**
     * 교재 상세 조회
     */
    public List<HashMap<String, String>> bookView(BookVO bookVO) {
        return bookMapper.bookView(bookVO);
    }

    /**
     * 교재 사용 여부 확인
     */
    public int bookUseCheck(BookVO bookVO) {
        return bookMapper.bookUseCheck(bookVO);
    }

    /**
     * 교재 수정
     */
    public void bookUpdate(BookVO bookVO) {
        bookMapper.bookUpdate(bookVO);
    }

    /**
     * 교재 삭제
     */
    public void bookDelete(BookVO bookVO) {
        bookMapper.bookDelete(bookVO);
    }

    /**
     * 교재 판매 목록 조회
     */
    public List<HashMap<String, String>> bookSellList(BookVO bookVO) {
        return bookMapper.bookSellList(bookVO);
    }

    /**
     * 교재 판매 목록 카운트
     */
    public int bookSellListCount(BookVO bookVO) {
        return bookMapper.bookSellListCount(bookVO);
    }

    /**
     * 교재 판매 목록 엑셀 다운로드용
     */
    public List<HashMap<String, String>> bookSellListExcel(BookVO bookVO) {
        return bookMapper.bookSellListExcel(bookVO);
    }
}