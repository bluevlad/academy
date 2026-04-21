package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.index.service.IndexVO;

/**
 * 인덱스/메뉴 관리 Mapper
 */
@Mapper
public interface IndexMapper {

    // === 메뉴 관리 ===
    List<IndexVO> selectTopMenuList(IndexVO vo);
    List<IndexVO> selectLeftMenuList(IndexVO vo);
    List<IndexVO> selectLeftMenuTree(IndexVO vo);

    // === 메뉴 마스터 관리 ===
    List<IndexVO> selectMenuList(IndexVO vo);
    int selectMenuListCount(IndexVO vo);
    IndexVO selectMenuDetail(IndexVO vo);
    int insertMenu(IndexVO vo);
    int updateMenu(IndexVO vo);
    int deleteMenu(IndexVO vo);

    // === 사이트 메뉴 권한 관리 ===
    List<IndexVO> selectSiteMenuList(IndexVO vo);
    int insertSiteMenu(IndexVO vo);
    int deleteSiteMenu(IndexVO vo);
}
