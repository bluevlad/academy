package com.academy.mocktest.offExamReg.registration.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.OffExamRegMapper;

@Service
public class OffExamRegService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final OffExamRegMapper offExamRegMapper;

    @Autowired
    public OffExamRegService(OffExamRegMapper offExamRegMapper) {
        this.offExamRegMapper = offExamRegMapper;
    }

    public List<OffExamRegVO> getOffExamRegList(OffExamRegVO vo) { return offExamRegMapper.selectOffExamRegList(vo); }
    public int getOffExamRegListCount(OffExamRegVO vo) { return offExamRegMapper.selectOffExamRegListCount(vo); }
    public OffExamRegVO getOffExamRegDetail(OffExamRegVO vo) { return offExamRegMapper.selectOffExamRegDetail(vo); }
    @Transactional
    public int insertOffExamReg(OffExamRegVO vo) { return offExamRegMapper.insertOffExamReg(vo); }
    @Transactional
    public int updateOffExamReg(OffExamRegVO vo) { return offExamRegMapper.updateOffExamReg(vo); }
    @Transactional
    public int deleteOffExamReg(OffExamRegVO vo) { return offExamRegMapper.deleteOffExamReg(vo); }
    @Transactional
    public int updateOffExamRegStatus(OffExamRegVO vo) { return offExamRegMapper.updateOffExamRegStatus(vo); }
    @Transactional
    public int updateOffExamRegResult(OffExamRegVO vo) { return offExamRegMapper.updateOffExamRegResult(vo); }
}
