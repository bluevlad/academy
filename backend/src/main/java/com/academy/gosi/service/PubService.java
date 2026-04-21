package com.academy.gosi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.PubMapper;

/**
 * 공지/게시판 관리 서비스
 */
@Service
public class PubService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PubMapper pubMapper;

    @Autowired
    public PubService(PubMapper pubMapper) {
        this.pubMapper = pubMapper;
    }

    // === 공지사항 관리 ===
    public List<PubVO> getPubList(PubVO vo) {
        return pubMapper.selectPubList(vo);
    }

    public int getPubListCount(PubVO vo) {
        return pubMapper.selectPubListCount(vo);
    }

    public PubVO getPubDetail(PubVO vo) {
        return pubMapper.selectPubDetail(vo);
    }

    public int getMaxPubNo(PubVO vo) {
        return pubMapper.selectMaxPubNo(vo);
    }

    @Transactional
    public int insertPub(PubVO vo) {
        return pubMapper.insertPub(vo);
    }

    @Transactional
    public int updatePub(PubVO vo) {
        return pubMapper.updatePub(vo);
    }

    @Transactional
    public int deletePub(PubVO vo) {
        // 첨부파일 먼저 삭제
        pubMapper.deletePubFileByPubNo(vo);
        // 게시글 삭제
        return pubMapper.deletePub(vo);
    }

    @Transactional
    public int updatePubHitCnt(PubVO vo) {
        return pubMapper.updatePubHitCnt(vo);
    }

    // === 첨부파일 관리 ===
    public List<PubVO> getPubFileList(PubVO vo) {
        return pubMapper.selectPubFileList(vo);
    }

    @Transactional
    public int insertPubFile(PubVO vo) {
        return pubMapper.insertPubFile(vo);
    }

    @Transactional
    public int deletePubFile(PubVO vo) {
        return pubMapper.deletePubFile(vo);
    }
}
