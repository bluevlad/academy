package com.academy.manage.categorySale.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.CategorySaleMapper;

/**
 * 카테고리별 매출 관리 서비스
 */
@Service
public class CategorySaleService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CategorySaleMapper categorySaleMapper;

    @Autowired
    public CategorySaleService(CategorySaleMapper categorySaleMapper) {
        this.categorySaleMapper = categorySaleMapper;
    }

    /**
     * 카테고리별 매출 목록 조회
     */
    public List<CategorySaleVO> getCategorySaleList(CategorySaleVO vo) {
        return categorySaleMapper.selectCategorySaleList(vo);
    }

    /**
     * 카테고리별 매출 목록 카운트
     */
    public int getCategorySaleListCount(CategorySaleVO vo) {
        return categorySaleMapper.selectCategorySaleListCount(vo);
    }

    /**
     * 카테고리별 매출 상세 조회
     */
    public CategorySaleVO getCategorySaleDetail(CategorySaleVO vo) {
        return categorySaleMapper.selectCategorySaleDetail(vo);
    }

    /**
     * 카테고리별 매출 통계 조회
     */
    public CategorySaleVO getCategorySaleStats(CategorySaleVO vo) {
        return categorySaleMapper.selectCategorySaleStats(vo);
    }

    /**
     * 일별 매출 목록 조회
     */
    public List<CategorySaleVO> getDailySaleList(CategorySaleVO vo) {
        return categorySaleMapper.selectDailySaleList(vo);
    }

    /**
     * 월별 매출 목록 조회
     */
    public List<CategorySaleVO> getMonthlySaleList(CategorySaleVO vo) {
        return categorySaleMapper.selectMonthlySaleList(vo);
    }

    /**
     * 년별 매출 목록 조회
     */
    public List<CategorySaleVO> getYearlySaleList(CategorySaleVO vo) {
        return categorySaleMapper.selectYearlySaleList(vo);
    }

    /**
     * 카테고리 목록 조회
     */
    public List<CategorySaleVO> getCategoryList(CategorySaleVO vo) {
        return categorySaleMapper.selectCategoryList(vo);
    }

    /**
     * 카테고리별 매출 비율 조회
     */
    public List<CategorySaleVO> getCategorySaleRateList(CategorySaleVO vo) {
        return categorySaleMapper.selectCategorySaleRateList(vo);
    }

    /**
     * 매출 데이터 등록
     */
    @Transactional
    public int insertCategorySale(CategorySaleVO vo) {
        return categorySaleMapper.insertCategorySale(vo);
    }

    /**
     * 매출 데이터 수정
     */
    @Transactional
    public int updateCategorySale(CategorySaleVO vo) {
        return categorySaleMapper.updateCategorySale(vo);
    }

    /**
     * 매출 데이터 삭제
     */
    @Transactional
    public int deleteCategorySale(CategorySaleVO vo) {
        return categorySaleMapper.deleteCategorySale(vo);
    }
}
