package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.book.service.BookVO;

/**
 * Book Mapper Interface
 * book.xml 매퍼 파일과 연동
 */
@Mapper
public interface BookMapper {

    /**
     * 교재 목록 조회
     */
    List<HashMap<String, String>> bookList(BookVO bookVO);

    /**
     * 교재 목록 카운트
     */
    int bookListCount(BookVO bookVO);

    /**
     * 학습형태 목록 조회
     */
    List<HashMap<String, String>> getLearningFormList(BookVO bookVO);

    /**
     * 과목(강사) 목록 조회
     */
    List<HashMap<String, String>> getCaSubjectTeacherList(BookVO bookVO);

    /**
     * 교재 SEQ 조회
     */
    int getCaBookSeq(BookVO bookVO);

    /**
     * 교재 RSC_ID 생성
     */
    String getCaBookRscId(BookVO bookVO);

    /**
     * 교재 등록
     */
    void bookInsert(BookVO bookVO);

    /**
     * 교재 상세 목록 조회 (관련 도서)
     */
    List<HashMap<String, String>> bookViewList(BookVO bookVO);

    /**
     * 교재 상세 조회
     */
    List<HashMap<String, String>> bookView(BookVO bookVO);

    /**
     * 교재 사용 여부 확인
     */
    int bookUseCheck(BookVO bookVO);

    /**
     * 교재 수정
     */
    void bookUpdate(BookVO bookVO);

    /**
     * 교재 삭제
     */
    void bookDelete(BookVO bookVO);

    /**
     * 교재 판매 목록 조회
     */
    List<HashMap<String, String>> bookSellList(BookVO bookVO);

    /**
     * 교재 판매 목록 카운트
     */
    int bookSellListCount(BookVO bookVO);

    /**
     * 교재 판매 목록 엑셀 다운로드용
     */
    List<HashMap<String, String>> bookSellListExcel(BookVO bookVO);
}