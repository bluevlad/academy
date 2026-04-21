package com.academy.mocktest.mouigosa.exam.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.MouiExamMapper;

@Service
public class MouiExamService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final MouiExamMapper mouiExamMapper;

    @Autowired
    public MouiExamService(MouiExamMapper mouiExamMapper) {
        this.mouiExamMapper = mouiExamMapper;
    }

    public List<MouiExamVO> getMouiExamList(MouiExamVO vo) { return mouiExamMapper.selectMouiExamList(vo); }
    public int getMouiExamListCount(MouiExamVO vo) { return mouiExamMapper.selectMouiExamListCount(vo); }
    public MouiExamVO getMouiExamDetail(MouiExamVO vo) { return mouiExamMapper.selectMouiExamDetail(vo); }
    @Transactional
    public int insertMouiExam(MouiExamVO vo) { return mouiExamMapper.insertMouiExam(vo); }
    @Transactional
    public int updateMouiExam(MouiExamVO vo) { return mouiExamMapper.updateMouiExam(vo); }
    @Transactional
    public int deleteMouiExam(MouiExamVO vo) { return mouiExamMapper.deleteMouiExam(vo); }
    @Transactional
    public int updateExamStatus(MouiExamVO vo) { return mouiExamMapper.updateExamStatus(vo); }
    public List<MouiExamVO> getExamYearList(MouiExamVO vo) { return mouiExamMapper.selectExamYearList(vo); }
}
