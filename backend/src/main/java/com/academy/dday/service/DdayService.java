package com.academy.dday.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.DdayMapper;

/**
 * D-Day 관리 Service 클래스
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
@Service
public class DdayService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final DdayMapper ddayMapper;

    @Autowired
    public DdayService(DdayMapper ddayMapper) {
        this.ddayMapper = ddayMapper;
    }

    /**
     * 카테고리 목록 조회
     * @param ddayVO 검색조건
     * @return List 카테고리 목록
     */
    public List<JSONObject> selectCategoryList(DdayVO ddayVO) {
        return ddayMapper.selectCategoryList(ddayVO);
    }

    /**
     * D-Day 목록 조회
     * @param ddayVO 검색조건
     * @return List D-Day 목록
     */
    public List<JSONObject> selectDdayList(DdayVO ddayVO) {
        return ddayMapper.selectDdayList(ddayVO);
    }

    /**
     * D-Day 목록 건수 조회
     * @param ddayVO 검색조건
     * @return int D-Day 목록 건수
     */
    public int selectDdayListCount(DdayVO ddayVO) {
        return ddayMapper.selectDdayListCount(ddayVO);
    }

    /**
     * D-Day 상세 조회
     * @param ddayVO 검색조건
     * @return JSONObject D-Day 상세 정보
     */
    public JSONObject selectDdayDetail(DdayVO ddayVO) {
        return ddayMapper.selectDdayDetail(ddayVO);
    }

    /**
     * D-Day 등록
     * @param ddayVO D-Day 정보
     */
    public void insertDday(DdayVO ddayVO) {
        ddayMapper.insertDday(ddayVO);
    }

    /**
     * D-Day 수정
     * @param ddayVO D-Day 정보
     */
    public void updateDday(DdayVO ddayVO) {
        ddayMapper.updateDday(ddayVO);
    }

    /**
     * D-Day 삭제
     * @param ddayVO D-Day 정보
     */
    public void deleteDday(DdayVO ddayVO) {
        ddayMapper.deleteDday(ddayVO);
    }
}
