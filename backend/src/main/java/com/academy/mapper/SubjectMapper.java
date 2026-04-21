package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.SubjectVO;

/**
 * Subject Mapper Interface
 * lectureSubjectSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface SubjectMapper {

    /**
     * 과목 목록 조회
     */
    List<HashMap<String, String>> subjectList(SubjectVO subjectVO);

    /**
     * 과목 목록 카운트
     */
    int subjectListCount(SubjectVO subjectVO);

    /**
     * 과목 코드 생성
     */
    String subjectGetCode(SubjectVO subjectVO);

    /**
     * 과목 등록
     */
    void subjectInsert(SubjectVO subjectVO);

    /**
     * 과목 상세 조회
     */
    List<HashMap<String, String>> subjectView(SubjectVO subjectVO);

    /**
     * 과목 수정
     */
    void subjectUpdate(SubjectVO subjectVO);

    /**
     * 과목 삭제
     */
    void subjectDelete(SubjectVO subjectVO);

    /**
     * 과목 중복 체크
     */
    int subjectCheck(SubjectVO subjectVO);

    /**
     * 과목 카테고리 등록
     */
    void subjectCategoryInsert(SubjectVO subjectVO);

    /**
     * 과목 카테고리 삭제
     */
    void subjectCategoryDelete(SubjectVO subjectVO);

    /**
     * 과목 카테고리 삭제 (카테고리별)
     */
    void subjectCategoryDeleteByCat(SubjectVO subjectVO);

    /**
     * 과목 카테고리 순서 등록
     */
    void subjectCategoryOrderInsert(SubjectVO subjectVO);

    /**
     * 과목 카테고리 순서 개수 체크
     */
    int chkSubjectCategoryOrderCnt(SubjectVO subjectVO);

    /**
     * 과목 카테고리 개수 체크
     */
    int chkSubjectCategoryCnt(SubjectVO subjectVO);

    /**
     * 과목 카테고리 순서 인덱스 조회
     */
    String getSubjectCategoryOrderIdx(SubjectVO subjectVO);

    /**
     * 과목 카테고리 순서 삭제 (On/Off별)
     */
    void subjectCategoryOrderDeleteByOnoff(SubjectVO subjectVO);

    /**
     * 과목 카테고리 순서 삭제
     */
    void subjectCategoryOrderDelete(SubjectVO subjectVO);

    /**
     * 과목 카테고리 상세 조회
     */
    List<HashMap<String, String>> subjectCategoryView(SubjectVO subjectVO);

    /**
     * 과목 카테고리 목록 조회
     */
    List<HashMap<String, String>> findSubjectCategoryList(SubjectVO subjectVO);

    /**
     * 메인 카테고리 과목 순서 등록
     */
    void main_category_subject_order_Insert(SubjectVO subjectVO);
}