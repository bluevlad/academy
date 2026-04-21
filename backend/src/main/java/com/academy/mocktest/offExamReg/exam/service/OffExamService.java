package com.academy.mocktest.offExamReg.exam.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.OffExamMapper;

@Service
public class OffExamService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final OffExamMapper offExamMapper;

    @Autowired
    public OffExamService(OffExamMapper offExamMapper) {
        this.offExamMapper = offExamMapper;
    }

    public List<OffExamVO> getOffExamList(OffExamVO vo) { return offExamMapper.selectOffExamList(vo); }
    public int getOffExamListCount(OffExamVO vo) { return offExamMapper.selectOffExamListCount(vo); }
    public OffExamVO getOffExamDetail(OffExamVO vo) { return offExamMapper.selectOffExamDetail(vo); }
    @Transactional
    public int insertOffExam(OffExamVO vo) { return offExamMapper.insertOffExam(vo); }
    @Transactional
    public int updateOffExam(OffExamVO vo) { return offExamMapper.updateOffExam(vo); }
    @Transactional
    public int deleteOffExam(OffExamVO vo) { return offExamMapper.deleteOffExam(vo); }
    @Transactional
    public int updateOffExamStatus(OffExamVO vo) { return offExamMapper.updateOffExamStatus(vo); }
}
