package com.academy.lecture.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.SubjectMapper;

/**
 * Subject Service
 * 과목 관리 서비스 (직접 구현)
 */
@Service(value="subjectservice")
public class SubjectService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * Subject 목록 조회
     */
    public List<HashMap<String, String>> subjectList(SubjectVO subjectVO){
        return subjectMapper.subjectList(subjectVO);
    }

    /**
     * Subject 목록 개수 조회
     */
    public int subjectListCount(SubjectVO subjectVO){
        return subjectMapper.subjectListCount(subjectVO);
    }

    /**
     * Subject 코드 생성
     */
    public String subjectGetCode(SubjectVO subjectVO){
        return subjectMapper.subjectGetCode(subjectVO);
    }

    /**
     * Subject 등록
     */
    public void subjectInsert(SubjectVO subjectVO){
        subjectMapper.subjectInsert(subjectVO);
    }

    /**
     * Subject 상세 조회
     */
    public List<HashMap<String, String>> subjectView(SubjectVO subjectVO){
        return subjectMapper.subjectView(subjectVO);
    }

    /**
     * Subject 수정
     */
    public void subjectUpdate(SubjectVO subjectVO){
        subjectMapper.subjectUpdate(subjectVO);
    }

    /**
     * Subject 삭제
     */
    public void subjectDelete(SubjectVO subjectVO){
        subjectMapper.subjectDelete(subjectVO);
    }

    /**
     * Subject 코드 중복 체크
     */
    public int subjectCheck(SubjectVO subjectVO){
        return subjectMapper.subjectCheck(subjectVO);
    }

    /**
     * Subject Category 등록
     */
    public void subjectCategoryInsert(SubjectVO subjectVO){
        subjectMapper.subjectCategoryInsert(subjectVO);
    }

    /**
     * Subject Category 삭제
     */
    public void subjectCategoryDelete(SubjectVO subjectVO){
        subjectMapper.subjectCategoryDelete(subjectVO);
    }

    /**
     * Subject Category 삭제 (카테고리 기준)
     */
    public void subjectCategoryDeleteByCat(SubjectVO subjectVO){
        subjectMapper.subjectCategoryDeleteByCat(subjectVO);
    }

    /**
     * Subject Category Order 등록
     */
    public void subjectCategoryOrderInsert(SubjectVO subjectVO){
        subjectMapper.subjectCategoryOrderInsert(subjectVO);
    }

    /**
     * Subject Category Order 개수 확인
     */
    public int chkSubjectCategoryOrderCnt(SubjectVO subjectVO){
        return subjectMapper.chkSubjectCategoryOrderCnt(subjectVO);
    }

    /**
     * Subject Category 개수 확인
     */
    public int chkSubjectCategoryCnt(SubjectVO subjectVO){
        return subjectMapper.chkSubjectCategoryCnt(subjectVO);
    }

    /**
     * Subject Category Order Index 조회
     */
    public String getSubjectCategoryOrderIdx(SubjectVO subjectVO){
        return subjectMapper.getSubjectCategoryOrderIdx(subjectVO);
    }

    /**
     * Subject Category Order 삭제 (온오프 기준)
     */
    public void subjectCategoryOrderDeleteByOnoff(SubjectVO subjectVO){
        subjectMapper.subjectCategoryOrderDeleteByOnoff(subjectVO);
    }

    /**
     * Subject Category Order 삭제
     */
    public void subjectCategoryOrderDelete(SubjectVO subjectVO){
        subjectMapper.subjectCategoryOrderDelete(subjectVO);
    }

    /**
     * Subject Category 상세 조회
     */
    public List<HashMap<String, String>> subjectCategoryView(SubjectVO subjectVO){
        return subjectMapper.subjectCategoryView(subjectVO);
    }

    /**
     * Subject Category 리스트 검색
     */
    public List<HashMap<String, String>> findSubjectCategoryList(SubjectVO subjectVO){
        return subjectMapper.findSubjectCategoryList(subjectVO);
    }

    /**
     * Main Category Subject Order 등록
     */
    public void main_category_subject_order_Insert(SubjectVO subjectVO){
        subjectMapper.main_category_subject_order_Insert(subjectVO);
    }
}