package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.SeriesVO;

/**
 * CategorySeries Mapper Interface
 * lectureCategorySeriesSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface CategorySeriesMapper {

    /**
     * 카테고리 시리즈 등록
     */
    void catSeriesInsert(SeriesVO seriesVO);

    /**
     * 카테고리 시리즈 삭제 (카테고리 코드별)
     */
    void catSeriesDeleteWthCatCd(SeriesVO seriesVO);

    /**
     * 카테고리 시리즈 삭제 (시리즈 코드별)
     */
    void catSeriesDeleteWthSrsCd(SeriesVO seriesVO);
}