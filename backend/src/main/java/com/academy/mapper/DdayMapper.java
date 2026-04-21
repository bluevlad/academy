package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.dday.service.DdayVO;

/**
 * D-Day 관리에 관한 데이터 접근 클래스를 정의한다.
 * @author system
 * @since 2025.12.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.11         system          신규 생성
 * </pre>
 */
@Mapper
public interface DdayMapper {

    /**
     * 카테고리 목록 조회
     * @param ddayVO 검색조건
     * @return List 카테고리 목록
     */
    List<JSONObject> selectCategoryList(DdayVO ddayVO);

    /**
     * D-Day 목록 조회
     * @param ddayVO 검색조건
     * @return List D-Day 목록
     */
    List<JSONObject> selectDdayList(DdayVO ddayVO);

    /**
     * D-Day 목록 건수 조회
     * @param ddayVO 검색조건
     * @return int D-Day 목록 건수
     */
    int selectDdayListCount(DdayVO ddayVO);

    /**
     * D-Day 상세 조회
     * @param ddayVO 검색조건
     * @return JSONObject D-Day 상세 정보
     */
    JSONObject selectDdayDetail(DdayVO ddayVO);

    /**
     * D-Day 등록
     * @param ddayVO D-Day 정보
     */
    void insertDday(DdayVO ddayVO);

    /**
     * D-Day 수정
     * @param ddayVO D-Day 정보
     */
    void updateDday(DdayVO ddayVO);

    /**
     * D-Day 삭제
     * @param ddayVO D-Day 정보
     */
    void deleteDday(DdayVO ddayVO);
}
