package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.ProductEventMapper;

/**
 * ProductEvent Service
 * 상품 이벤트 관리 서비스 (직접 구현)
 */
@Service(value="ProductEventService")
public class ProductEventService {

    @Autowired
    private ProductEventMapper productEventMapper;

    // FreeOrderMapper 생성 후 아래 메서드들 구현 필요
    // @Autowired
    // private FreeOrderMapper freeOrderMapper;

    /**
     * 이벤트 목록 조회
     */
    public List<HashMap<String, String>> list(ProductEventVO vo){
        return productEventMapper.list(vo);
    }

    /**
     * 이벤트 목록 개수 조회
     */
    public int listCount(ProductEventVO vo){
        return productEventMapper.listCount(vo);
    }

    /**
     * 이벤트 단건 조회
     */
    public HashMap<String, String> getOne(ProductEventVO vo){
        return productEventMapper.getOne(vo);
    }

    /**
     * 이벤트 상품 목록 조회
     */
    public List<HashMap<String, String>> list_prd(ProductEventVO vo){
        return productEventMapper.list_prd(vo);
    }

    /**
     * 이벤트 등록
     */
    public void insert(ProductEventVO vo){
        productEventMapper.insert(vo);
    }

    /**
     * 이벤트 수정
     */
    public void update(ProductEventVO vo){
        productEventMapper.update(vo);
    }

    /**
     * 강의 등록
     */
    public void lec_insert(ProductEventVO vo){
        productEventMapper.lec_insert(vo);
    }

    /**
     * 강의 삭제
     */
    public void lec_delete(ProductEventVO vo){
        productEventMapper.lec_delete(vo);
    }

    // 강의선택 팝업 카테고리 셀렉트박스 리스트
    // FreeOrderMapper 생성 후 구현
    public List<HashMap<String, String>> getCaCatCdList(Map<String, Object> keyName){
        // return freeOrderMapper.getCaCatCdList(keyName);
        throw new UnsupportedOperationException("FreeOrderMapper not implemented yet");
    }

    // 강의선택 팝업 학습형태 셀렉트박스 리스트
    // FreeOrderMapper 생성 후 구현
    public List<HashMap<String, String>> getVwMenuMstTree_lec(Map<String, Object> keyName){
        // return freeOrderMapper.getVwMenuMstTree_lec(keyName);
        throw new UnsupportedOperationException("FreeOrderMapper not implemented yet");
    }

    // 강의선택 팝업 과목 셀렉트박스 리스트
    // FreeOrderMapper 생성 후 구현
    public List<HashMap<String, String>> getCaSubjectCdList(Map<String, Object> keyName){
        // return freeOrderMapper.getCaSubjectCdList(keyName);
        throw new UnsupportedOperationException("FreeOrderMapper not implemented yet");
    }

    // 강의선택 팝업  리스트
    // FreeOrderMapper 생성 후 구현
    public List<HashMap<String, String>> getCbLecMstFreeOrderList(Map<String, Object> keyName){
        // return freeOrderMapper.getCbLecMstFreeOrderList(keyName);
        throw new UnsupportedOperationException("FreeOrderMapper not implemented yet");
    }

    // 강의선택 팝업  카운트
    // FreeOrderMapper 생성 후 구현
    public int getCbLecMstListFreeOrderCount(Map<String, Object> keyName) {
        // return freeOrderMapper.getCbLecMstListFreeOrderCount(keyName);
        throw new UnsupportedOperationException("FreeOrderMapper not implemented yet");
    }
}