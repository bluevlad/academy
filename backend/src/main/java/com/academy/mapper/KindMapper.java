package com.academy.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.CategoryVO;
import com.academy.lecture.service.KindVO;
import org.json.simple.JSONObject;

/**
 * Kind Mapper Interface
 * lectureKindSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface KindMapper {

    /**
     * Kind 목록 조회 (간단)
     */
    List<HashMap<String, String>> getKindList(KindVO kindVO);

    /**
     * Kind 목록 조회
     */
    List<HashMap<String, String>> kindList(KindVO kindVO);

    /**
     * Kind 목록 카운트
     */
    int kindListCount(KindVO kindVO);

    /**
     * Kind 등록
     */
    void kindInsert(KindVO kindVO);

    /**
     * Kind 상세 조회
     */
    List<HashMap<String, String>> kindView(KindVO kindVO);

    /**
     * Kind 수정
     */
    void kindUpdate(KindVO kindVO);

    /**
     * Kind 삭제
     */
    void kindDelete(KindVO kindVO);

    /**
     * Kind 중복 체크
     */
    int kindCheck(KindVO kindVO);

    /**
     * Kind 코드 조회
     */
    List<HashMap<String, String>> selectKindCode();

    /**
     * 순서 업데이트
     */
    void SeqUpdate(KindVO kindVO);

    /**
     * 시리즈 카테고리 트리 조회
     */
    ArrayList<JSONObject> getSeriesCateTree();

    /**
     * 최대 순서 조회
     */
    HashMap<String, Object> getMaxOrdr(CategoryVO categoryVO);
}