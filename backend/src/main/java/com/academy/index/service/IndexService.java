package com.academy.index.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.IndexMapper;

/**
 * 인덱스/메뉴 관리 서비스
 */
@Service
public class IndexService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final IndexMapper indexMapper;

    @Autowired
    public IndexService(IndexMapper indexMapper) {
        this.indexMapper = indexMapper;
    }

    // === 메뉴 관리 ===
    public List<IndexVO> getTopMenuList(IndexVO vo) {
        return indexMapper.selectTopMenuList(vo);
    }

    public List<IndexVO> getLeftMenuList(IndexVO vo) {
        return indexMapper.selectLeftMenuList(vo);
    }

    public List<IndexVO> getLeftMenuTree(IndexVO vo) {
        return indexMapper.selectLeftMenuTree(vo);
    }

    // === 메뉴 마스터 관리 ===
    public List<IndexVO> getMenuList(IndexVO vo) {
        return indexMapper.selectMenuList(vo);
    }

    public int getMenuListCount(IndexVO vo) {
        return indexMapper.selectMenuListCount(vo);
    }

    public IndexVO getMenuDetail(IndexVO vo) {
        return indexMapper.selectMenuDetail(vo);
    }

    @Transactional
    public int insertMenu(IndexVO vo) {
        return indexMapper.insertMenu(vo);
    }

    @Transactional
    public int updateMenu(IndexVO vo) {
        return indexMapper.updateMenu(vo);
    }

    @Transactional
    public int deleteMenu(IndexVO vo) {
        return indexMapper.deleteMenu(vo);
    }

    // === 사이트 메뉴 권한 관리 ===
    public List<IndexVO> getSiteMenuList(IndexVO vo) {
        return indexMapper.selectSiteMenuList(vo);
    }

    @Transactional
    public int insertSiteMenu(IndexVO vo) {
        return indexMapper.insertSiteMenu(vo);
    }

    @Transactional
    public int deleteSiteMenu(IndexVO vo) {
        return indexMapper.deleteSiteMenu(vo);
    }
}
