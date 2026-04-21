package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.manage.categorySale.service.CategorySaleVO;

/**
 * 카테고리별 매출 관리 Mapper
 */
@Mapper
public interface CategorySaleMapper {

    /**
     * 카테고리별 매출 목록 조회
     */
    List<CategorySaleVO> selectCategorySaleList(CategorySaleVO vo);

    /**
     * 카테고리별 매출 목록 카운트
     */
    int selectCategorySaleListCount(CategorySaleVO vo);

    /**
     * 카테고리별 매출 상세 조회
     */
    CategorySaleVO selectCategorySaleDetail(CategorySaleVO vo);

    /**
     * 카테고리별 매출 통계 조회
     */
    CategorySaleVO selectCategorySaleStats(CategorySaleVO vo);

    /**
     * 일별 매출 목록 조회
     */
    List<CategorySaleVO> selectDailySaleList(CategorySaleVO vo);

    /**
     * 월별 매출 목록 조회
     */
    List<CategorySaleVO> selectMonthlySaleList(CategorySaleVO vo);

    /**
     * 년별 매출 목록 조회
     */
    List<CategorySaleVO> selectYearlySaleList(CategorySaleVO vo);

    /**
     * 카테고리 목록 조회
     */
    List<CategorySaleVO> selectCategoryList(CategorySaleVO vo);

    /**
     * 카테고리별 매출 비율 조회
     */
    List<CategorySaleVO> selectCategorySaleRateList(CategorySaleVO vo);

    /**
     * 매출 데이터 등록
     */
    int insertCategorySale(CategorySaleVO vo);

    /**
     * 매출 데이터 수정
     */
    int updateCategorySale(CategorySaleVO vo);

    /**
     * 매출 데이터 삭제
     */
    int deleteCategorySale(CategorySaleVO vo);
}
