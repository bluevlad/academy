package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.lecture.service.CategoryVO;

/**
 * Category Mapper Interface
 * ExamMapper 패턴 적용 - ArrayList<JSONObject> 및 VO 사용
 */
@Mapper
public interface CategoryMapper {

    ArrayList<JSONObject> getSeriesCateTree();

    JSONObject getDetail(CategoryVO categoryVO);

    void updateProcess(CategoryVO categoryVO);

    int deleteProcess(CategoryVO categoryVO);

    int idCheck(CategoryVO categoryVO);

    int insertProcess(CategoryVO categoryVO);

    JSONObject getMaxOrdr(CategoryVO categoryVO);
}