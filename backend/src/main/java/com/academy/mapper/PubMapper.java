package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.gosi.service.PubVO;

/**
 * 공지/게시판 관리 Mapper
 */
@Mapper
public interface PubMapper {

    // === 공지사항 관리 ===
    List<PubVO> selectPubList(PubVO vo);
    int selectPubListCount(PubVO vo);
    PubVO selectPubDetail(PubVO vo);
    int selectMaxPubNo(PubVO vo);
    int insertPub(PubVO vo);
    int updatePub(PubVO vo);
    int deletePub(PubVO vo);
    int updatePubHitCnt(PubVO vo);

    // === 첨부파일 관리 ===
    List<PubVO> selectPubFileList(PubVO vo);
    int insertPubFile(PubVO vo);
    int deletePubFile(PubVO vo);
    int deletePubFileByPubNo(PubVO vo);
}
