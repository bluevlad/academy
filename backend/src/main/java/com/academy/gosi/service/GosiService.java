package com.academy.gosi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.GosiMapper;

/**
 * 고시 관리 서비스
 */
@Service
public class GosiService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final GosiMapper gosiMapper;

    @Autowired
    public GosiService(GosiMapper gosiMapper) {
        this.gosiMapper = gosiMapper;
    }

    // === 고시 마스터 ===
    public List<GosiVO> getGosiList(GosiVO vo) {
        return gosiMapper.selectGosiList(vo);
    }

    // === 샘플 사용자 관리 ===
    public List<GosiVO> getSampleUserList(GosiVO vo) {
        return gosiMapper.selectSampleUserList(vo);
    }

    public int getSampleUserListCount(GosiVO vo) {
        return gosiMapper.selectSampleUserListCount(vo);
    }

    public GosiVO getSampleUserDetail(GosiVO vo) {
        return gosiMapper.selectSampleUserDetail(vo);
    }

    @Transactional
    public int insertSampleUser(GosiVO vo) {
        return gosiMapper.insertSampleUser(vo);
    }

    @Transactional
    public int updateSampleUser(GosiVO vo) {
        return gosiMapper.updateSampleUser(vo);
    }

    @Transactional
    public int deleteSampleUser(GosiVO vo) {
        return gosiMapper.deleteSampleUser(vo);
    }

    // === 고시 지역 마스터 ===
    public List<GosiVO> getGosiAreaMstList(GosiVO vo) {
        return gosiMapper.selectGosiAreaMstList(vo);
    }

    public GosiVO getGosiAreaMstDetail(GosiVO vo) {
        return gosiMapper.selectGosiAreaMstDetail(vo);
    }

    @Transactional
    public int updateGosiAreaMst(GosiVO vo) {
        return gosiMapper.updateGosiAreaMst(vo);
    }

    // === VOD 관리 ===
    public List<GosiVO> getVodSubjectList(GosiVO vo) {
        return gosiMapper.selectVodSubjectList(vo);
    }

    public GosiVO getVodDetail(GosiVO vo) {
        return gosiMapper.selectVodDetail(vo);
    }

    @Transactional
    public int updateGosiVod(GosiVO vo) {
        return gosiMapper.updateGosiVod(vo);
    }

    // === 고시 통계 ===
    public List<GosiVO> getGosiStatMstList(GosiVO vo) {
        return gosiMapper.selectGosiStatMstList(vo);
    }

    @Transactional
    public void makeGosiResult(GosiVO vo) {
        gosiMapper.callMakeGosiResult(vo);
    }

    @Transactional
    public void makeGosiStandard(GosiVO vo) {
        gosiMapper.callMakeGosiStandard(vo);
    }

    @Transactional
    public void makeGosiStatMst(GosiVO vo) {
        gosiMapper.callMakeGosiStatMst(vo);
    }

    @Transactional
    public void makeGosiAdjustMst(GosiVO vo) {
        gosiMapper.callMakeGosiAdjustMst(vo);
    }

    // === 고시 과목 ===
    public List<GosiVO> getGosiSubjectList(GosiVO vo) {
        return gosiMapper.selectGosiSubjectList(vo);
    }

    // === 이벤트 결과 ===
    public List<GosiVO> getEventResultList(GosiVO vo) {
        return gosiMapper.selectEventResultList(vo);
    }

    public int getEventResultListCount(GosiVO vo) {
        return gosiMapper.selectEventResultListCount(vo);
    }
}
