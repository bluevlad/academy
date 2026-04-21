package com.academy.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LectureReplyMapper {
    List<HashMap<String, Object>> selectLectureReplyList(HashMap<String, Object> params);
    int selectLectureReplyListCount(HashMap<String, Object> params);
    HashMap<String, Object> selectLectureReplyDetail(HashMap<String, Object> params);
    List<HashMap<String, Object>> selectLectureReplyDetailComments(HashMap<String, Object> params);
    int selectLectureReplyDetailCommentCount(HashMap<String, Object> params);
    int deleteLectureReply(HashMap<String, Object> params);
}
