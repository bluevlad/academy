package com.academy.box.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.BoxMapper;

/**
 * 사물함 관리 Service 클래스
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            사물함 관리 신규 생성
 * </pre>
 */
@Service
public class BoxService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoxMapper boxMapper;

    public BoxService(BoxMapper boxMapper) {
        this.boxMapper = boxMapper;
    }

    // =====================================================
    // 사물함 마스터 (TB_OFF_BOX) 관련
    // =====================================================

    /**
     * 사물함 목록 조회
     * @param boxVO 검색조건
     * @return List 사물함 목록
     */
    public List<JSONObject> selectBoxList(BoxVO boxVO) {
        return boxMapper.selectBoxList(boxVO);
    }

    /**
     * 사물함 목록 건수 조회
     * @param boxVO 검색조건
     * @return int 사물함 목록 건수
     */
    public int selectBoxListCount(BoxVO boxVO) {
        return boxMapper.selectBoxListCount(boxVO);
    }

    /**
     * 사물함 상세 조회
     * @param boxVO 검색조건
     * @return JSONObject 사물함 상세정보
     */
    public JSONObject selectBoxDetail(BoxVO boxVO) {
        return boxMapper.selectBoxDetail(boxVO);
    }

    /**
     * 사물함 사용중 수 조회
     * @param boxVO 검색조건
     * @return int 사용중 사물함 수
     */
    public int selectBoxNumYCount(BoxVO boxVO) {
        return boxMapper.selectBoxNumYCount(boxVO);
    }

    /**
     * 사물함 등록
     * @param boxVO 사물함정보
     */
    public void insertBox(BoxVO boxVO) {
        boxMapper.insertBox(boxVO);
    }

    /**
     * 사물함 수정
     * @param boxVO 사물함정보
     */
    public void updateBox(BoxVO boxVO) {
        boxMapper.updateBox(boxVO);
    }

    /**
     * 사물함 수정 (사물함 개수, 시작/종료번호 제외)
     * @param boxVO 사물함정보
     */
    public void updateBox2(BoxVO boxVO) {
        boxMapper.updateBox2(boxVO);
    }

    /**
     * 사물함 삭제
     * @param boxVO 사물함정보
     */
    public void deleteBox(BoxVO boxVO) {
        boxMapper.deleteBox(boxVO);
    }

    /**
     * 사물함 관련 전체 삭제 (사물함 + 세부정보)
     * @param boxVO 사물함정보
     */
    public void deleteBoxAll(BoxVO boxVO) {
        // 세부정보 먼저 삭제
        boxMapper.deleteBoxNumByBoxCd(boxVO);
        // 사물함 삭제
        boxMapper.deleteBox(boxVO);
    }

    // =====================================================
    // 사물함 세부정보 (TB_OFF_BOX_NUM) 관련
    // =====================================================

    /**
     * 사물함 세부정보 목록 조회
     * @param boxNumVO 검색조건
     * @return List 사물함 세부정보 목록
     */
    public List<JSONObject> selectBoxNumList(BoxNumVO boxNumVO) {
        return boxMapper.selectBoxNumList(boxNumVO);
    }

    /**
     * 사물함 세부정보 목록 건수 조회
     * @param boxNumVO 검색조건
     * @return int 사물함 세부정보 목록 건수
     */
    public int selectBoxNumListCount(BoxNumVO boxNumVO) {
        return boxMapper.selectBoxNumListCount(boxNumVO);
    }

    /**
     * 사물함 세부정보 상세 조회
     * @param boxNumVO 검색조건
     * @return JSONObject 사물함 세부정보 상세
     */
    public JSONObject selectBoxNumDetail(BoxNumVO boxNumVO) {
        return boxMapper.selectBoxNumDetail(boxNumVO);
    }

    /**
     * 사물함 세부정보 등록
     * @param boxNumVO 사물함 세부정보
     */
    public void insertBoxNum(BoxNumVO boxNumVO) {
        boxMapper.insertBoxNum(boxNumVO);
    }

    /**
     * 사물함 세부정보 수정
     * @param boxNumVO 사물함 세부정보
     */
    public void updateBoxNum(BoxNumVO boxNumVO) {
        boxMapper.updateBoxNum(boxNumVO);
    }

    /**
     * 사물함 플래그(사용상태) 수정
     * @param boxNumVO 사물함 세부정보
     */
    public void updateBoxNumFlag(BoxNumVO boxNumVO) {
        boxMapper.updateBoxNumFlag(boxNumVO);
    }

    /**
     * 사물함 세부정보 초기화
     * @param boxNumVO 사물함 세부정보
     */
    public void updateBoxNumReset(BoxNumVO boxNumVO) {
        boxMapper.updateBoxNumReset(boxNumVO);
    }

    /**
     * 사물함 세부정보 삭제
     * @param boxNumVO 사물함 세부정보
     */
    public void deleteBoxNum(BoxNumVO boxNumVO) {
        boxMapper.deleteBoxNum(boxNumVO);
    }

    /**
     * 사물함 세부정보 일괄 삭제 (사물함코드 기준)
     * @param boxVO 사물함 정보
     */
    public void deleteBoxNumByBoxCd(BoxVO boxVO) {
        boxMapper.deleteBoxNumByBoxCd(boxVO);
    }
}
