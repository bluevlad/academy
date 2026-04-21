package com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.FormMapper;

/**
 * Form Service
 * 강좌 형태 관리 서비스 (직접 구현)
 */
@Service("formservice")
public class FormService {

    @Autowired
    private FormMapper formMapper;

    /**
     * Form 목록 조회
     */
    public List<HashMap<String, String>> formList(FormVO formVO) {
        return formMapper.formList(formVO);
    }

    /**
     * Form 목록 개수 조회
     */
    public int formListCount(FormVO formVO) {
        return formMapper.formListCount(formVO);
    }

    /**
     * Form 코드 생성
     */
    public String formGetCode() {
        return formMapper.formGetCode();
    }

    /**
     * Form 등록
     */
    public void formInsert(FormVO formVO) {
        formMapper.formInsert(formVO);
    }

    /**
     * Form 상세 조회
     */
    public List<HashMap<String, String>> formView(FormVO formVO) {
        return formMapper.formView(formVO);
    }

    /**
     * Form 수정
     */
    public void formUpdate(FormVO formVO) {
        formMapper.formUpdate(formVO);
    }

    /**
     * Form 삭제
     */
    public void formDelete(FormVO formVO) {
        formMapper.formDelete(formVO);
    }

    /**
     * Form 코드 중복 체크
     */
    public int formCheck(FormVO formVO) {
        return formMapper.formCheck(formVO);
    }

    /**
     * 코드 리스트 조회
     */
    public List<HashMap<String, String>> getCodeList(FormVO formVO) {
        return formMapper.getCodeList(formVO);
    }
}