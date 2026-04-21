package com.academy.mocktest.mouigosa.apply.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.MouiApplyMapper;

@Service
public class MouiApplyService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final MouiApplyMapper mouiApplyMapper;

    @Autowired
    public MouiApplyService(MouiApplyMapper mouiApplyMapper) {
        this.mouiApplyMapper = mouiApplyMapper;
    }

    public List<MouiApplyVO> getMouiApplyList(MouiApplyVO vo) { return mouiApplyMapper.selectMouiApplyList(vo); }
    public int getMouiApplyListCount(MouiApplyVO vo) { return mouiApplyMapper.selectMouiApplyListCount(vo); }
    public MouiApplyVO getMouiApplyDetail(MouiApplyVO vo) { return mouiApplyMapper.selectMouiApplyDetail(vo); }
    @Transactional
    public int insertMouiApply(MouiApplyVO vo) { return mouiApplyMapper.insertMouiApply(vo); }
    @Transactional
    public int updateMouiApply(MouiApplyVO vo) { return mouiApplyMapper.updateMouiApply(vo); }
    @Transactional
    public int deleteMouiApply(MouiApplyVO vo) { return mouiApplyMapper.deleteMouiApply(vo); }
    @Transactional
    public int updateApplyStatus(MouiApplyVO vo) { return mouiApplyMapper.updateApplyStatus(vo); }
    @Transactional
    public int updateExamResult(MouiApplyVO vo) { return mouiApplyMapper.updateExamResult(vo); }
}
