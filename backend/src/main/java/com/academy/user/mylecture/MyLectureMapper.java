package com.academy.user.mylecture;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyLectureMapper {
    List<MyLectureView> findActiveByUserId(@Param("userId") String userId);
}
