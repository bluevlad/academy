package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.SeriesVO;

/**
 * Series Mapper Interface
 * lectureSeriesSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface SeriesMapper {

    /**
     * 시리즈 목록 조회
     */
    List<HashMap<String, String>> seriesList(SeriesVO seriesVO);

    /**
     * 시리즈 목록 카운트
     */
    int seriesListCount(SeriesVO seriesVO);

    /**
     * 시리즈 등록
     */
    int seriesInsert(SeriesVO seriesVO);

    /**
     * 시리즈 상세 조회
     */
    List<HashMap<String, String>> seriesView(SeriesVO seriesVO);

    /**
     * 시리즈 수정
     */
    void seriesUpdate(SeriesVO seriesVO);

    /**
     * 시리즈 삭제
     */
    int seriesDelete(SeriesVO seriesVO);

    /**
     * 시리즈 중복 체크
     */
    int seriesCheck(SeriesVO seriesVO);

    /**
     * 카테고리 삭제
     */
    void categoryDelete(SeriesVO seriesVO);
}