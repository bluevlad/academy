package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.mocktest.board.service.MockBoardVO;

/**
 * 모의고사 게시판 Mapper
 */
@Mapper
public interface MockBoardMapper {

    List<MockBoardVO> selectMockBoardList(MockBoardVO vo);

    int selectMockBoardListCount(MockBoardVO vo);

    MockBoardVO selectMockBoardDetail(MockBoardVO vo);

    int insertMockBoard(MockBoardVO vo);

    int updateMockBoard(MockBoardVO vo);

    int deleteMockBoard(MockBoardVO vo);

    int updateViewCnt(MockBoardVO vo);

    int updateAnswer(MockBoardVO vo);

    List<MockBoardVO> selectNoticeTopList(MockBoardVO vo);
}
