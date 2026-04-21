package com.academy.lecture.service;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.CategoryMapper;

/**
 * Category Service
 * Series-Category 관리 서비스 (CategoryMapper 사용)
 */
@Service
public class CategoryService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 트리 리스트 조회
     */
    public ArrayList<JSONObject> getSeriesCateTree() {
        return categoryMapper.getSeriesCateTree();
    }

    /**
     * 상세 조회
     */
    public JSONObject getDetail(CategoryVO categoryVO) {
        return categoryMapper.getDetail(categoryVO);
    }

    /**
     * 수정 프로세스
     */
    public void updateProcess(CategoryVO categoryVO) {
        categoryMapper.updateProcess(categoryVO);
    }

    /**
     * 삭제 프로세스
     */
    public int deleteProcess(CategoryVO categoryVO) {
        return categoryMapper.deleteProcess(categoryVO);
    }

    /**
     * 등록 프로세스
     */
    public int insertProcess(CategoryVO categoryVO) {
        return categoryMapper.insertProcess(categoryVO);
    }

    /**
     * ID 중복체크
     */
    public int idCheck(CategoryVO categoryVO) {
        return categoryMapper.idCheck(categoryVO);
    }

    /**
     * 최대 순서 조회
     */
    public JSONObject getMaxOrdr(CategoryVO categoryVO) {
        return categoryMapper.getMaxOrdr(categoryVO);
    }
}