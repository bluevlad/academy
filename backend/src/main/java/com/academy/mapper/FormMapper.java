package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.lecture.service.FormVO;

/**
 * Form Mapper Interface
 * lectureFormSQL.xml 매퍼 파일과 연동
 */
@Mapper
public interface FormMapper {

    /**
     * 학습형태 목록 조회
     */
    List<HashMap<String, String>> formList(FormVO formVO);

    /**
     * 학습형태 목록 카운트
     */
    int formListCount(FormVO formVO);

    /**
     * 학습형태 코드 생성
     */
    String formGetCode();

    /**
     * 학습형태 등록
     */
    void formInsert(FormVO formVO);

    /**
     * 학습형태 상세 조회
     */
    List<HashMap<String, String>> formView(FormVO formVO);

    /**
     * 학습형태 수정
     */
    void formUpdate(FormVO formVO);

    /**
     * 학습형태 삭제
     */
    void formDelete(FormVO formVO);

    /**
     * 학습형태 중복 체크
     */
    int formCheck(FormVO formVO);

    /**
     * 코드 목록 조회
     */
    List<HashMap<String, String>> getCodeList(FormVO formVO);
}