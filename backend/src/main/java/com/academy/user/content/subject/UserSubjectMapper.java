package com.academy.user.content.subject;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserSubjectMapper {
    List<SubjectView> findActiveList(@Param("keyword") String keyword);
}
