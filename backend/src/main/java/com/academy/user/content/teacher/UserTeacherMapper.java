package com.academy.user.content.teacher;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserTeacherMapper {
    List<TeacherView> findList(@Param("keyword") String keyword);

    Optional<TeacherView> findById(@Param("teacherId") String teacherId);
}
