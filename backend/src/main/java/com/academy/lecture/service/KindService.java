package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.KindMapper;

/**
 * Kind Service
 * 카테고리 관리 서비스 (직접 구현)
 */
@Service("kindservice")
public class KindService {

    @Autowired
    private KindMapper kindMapper;

    /**
     * Kind 리스트 조회 (간단 조회용)
     */
    public List<HashMap<String, String>> getKindList(KindVO kindVO) {
        return kindMapper.getKindList(kindVO);
    }

    /**
     * Kind 목록 조회
     */
    public List<HashMap<String, String>> kindList(KindVO kindVO) {
        return kindMapper.kindList(kindVO);
    }

    /**
     * Kind 목록 개수 조회
     */
    public int kindListCount(KindVO kindVO) {
        return kindMapper.kindListCount(kindVO);
    }

    /**
     * Kind 등록
     */
    public void kindInsert(KindVO kindVO) {
        kindMapper.kindInsert(kindVO);
    }

    /**
     * Kind 상세 조회
     */
    public List<HashMap<String, String>> kindView(KindVO kindVO) {
        return kindMapper.kindView(kindVO);
    }

    /**
     * Kind 수정
     */
    public void kindUpdate(KindVO kindVO) {
        kindMapper.kindUpdate(kindVO);
    }

    /**
     * Kind 삭제
     */
    public void kindDelete(KindVO kindVO) {
        kindMapper.kindDelete(kindVO);
    }

    /**
     * Kind 코드 중복 체크
     */
    public int kindCheck(KindVO kindVO) {
        return kindMapper.kindCheck(kindVO);
    }

    /**
     * Kind 코드 전체 조회
     */
    public List<HashMap<String, String>> selectKindCode() {
        return kindMapper.selectKindCode();
    }

    /**
     * Kind 순서 수정
     */
    public void SeqUpdate(KindVO kindVO) {
        kindMapper.SeqUpdate(kindVO);
    }
}