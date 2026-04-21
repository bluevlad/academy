package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.box.service.BoxNumVO;
import com.academy.box.service.BoxVO;

/**
 * 사물함정보에 관한 데이터 접근 클래스를 정의한다.
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
public interface BoxMapper {

    // =====================================================
    // 사물함 마스터 (TB_OFF_BOX) 관련
    // =====================================================

    /**
     * 사물함 목록 조회
     * @param boxVO 검색조건
     * @return List 사물함 목록정보
     */
    List<JSONObject> selectBoxList(BoxVO boxVO);

    /**
     * 사물함 목록 건수 조회
     * @param boxVO 검색조건
     * @return int 사물함 목록 건수
     */
    int selectBoxListCount(BoxVO boxVO);

    /**
     * 사물함 상세 조회
     * @param boxVO 검색조건
     * @return JSONObject 사물함 상세정보
     */
    JSONObject selectBoxDetail(BoxVO boxVO);

    /**
     * 사물함 사용중 수 조회
     * @param boxVO 검색조건
     * @return int 사용중 사물함 수
     */
    int selectBoxNumYCount(BoxVO boxVO);

    /**
     * 사물함 등록
     * @param boxVO 사물함정보
     */
    void insertBox(BoxVO boxVO);

    /**
     * 사물함 수정
     * @param boxVO 사물함정보
     */
    void updateBox(BoxVO boxVO);

    /**
     * 사물함 수정 (사물함 개수, 시작/종료번호 제외)
     * @param boxVO 사물함정보
     */
    void updateBox2(BoxVO boxVO);

    /**
     * 사물함 삭제
     * @param boxVO 사물함정보
     */
    void deleteBox(BoxVO boxVO);

    // =====================================================
    // 사물함 세부정보 (TB_OFF_BOX_NUM) 관련
    // =====================================================

    /**
     * 사물함 세부정보 목록 조회
     * @param boxNumVO 검색조건
     * @return List 사물함 세부정보 목록
     */
    List<JSONObject> selectBoxNumList(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 목록 건수 조회
     * @param boxNumVO 검색조건
     * @return int 사물함 세부정보 목록 건수
     */
    int selectBoxNumListCount(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 상세 조회
     * @param boxNumVO 검색조건
     * @return JSONObject 사물함 세부정보 상세
     */
    JSONObject selectBoxNumDetail(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 등록
     * @param boxNumVO 사물함 세부정보
     */
    void insertBoxNum(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 수정
     * @param boxNumVO 사물함 세부정보
     */
    void updateBoxNum(BoxNumVO boxNumVO);

    /**
     * 사물함 플래그(사용상태) 수정
     * @param boxNumVO 사물함 세부정보
     */
    void updateBoxNumFlag(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 초기화
     * @param boxNumVO 사물함 세부정보
     */
    void updateBoxNumReset(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 삭제
     * @param boxNumVO 사물함 세부정보
     */
    void deleteBoxNum(BoxNumVO boxNumVO);

    /**
     * 사물함 세부정보 일괄 삭제 (사물함코드 기준)
     * @param boxVO 사물함 정보
     */
    void deleteBoxNumByBoxCd(BoxVO boxVO);
}
