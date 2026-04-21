package com.academy.lecture.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.mapper.LectureReplyMapper;

@Service
public class LectureReplyService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LectureReplyMapper lectureReplyMapper;

    @Autowired
    public LectureReplyService(LectureReplyMapper lectureReplyMapper) {
        this.lectureReplyMapper = lectureReplyMapper;
    }

    public List<HashMap<String, Object>> selectLectureReplyList(HashMap<String, Object> params) {
        return lectureReplyMapper.selectLectureReplyList(params);
    }

    public int selectLectureReplyListCount(HashMap<String, Object> params) {
        return lectureReplyMapper.selectLectureReplyListCount(params);
    }

    public HashMap<String, Object> selectLectureReplyDetail(HashMap<String, Object> params) {
        return lectureReplyMapper.selectLectureReplyDetail(params);
    }

    public List<HashMap<String, Object>> selectLectureReplyDetailComments(HashMap<String, Object> params) {
        return lectureReplyMapper.selectLectureReplyDetailComments(params);
    }

    public int selectLectureReplyDetailCommentCount(HashMap<String, Object> params) {
        return lectureReplyMapper.selectLectureReplyDetailCommentCount(params);
    }

    public int deleteLectureReply(HashMap<String, Object> params) {
        return lectureReplyMapper.deleteLectureReply(params);
    }
}
