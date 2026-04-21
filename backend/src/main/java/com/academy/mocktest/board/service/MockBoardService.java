package com.academy.mocktest.board.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.mapper.MockBoardMapper;

/**
 * 모의고사 게시판 서비스
 */
@Service
public class MockBoardService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MockBoardMapper mockBoardMapper;

    @Autowired
    public MockBoardService(MockBoardMapper mockBoardMapper) {
        this.mockBoardMapper = mockBoardMapper;
    }

    public List<MockBoardVO> getMockBoardList(MockBoardVO vo) {
        return mockBoardMapper.selectMockBoardList(vo);
    }

    public int getMockBoardListCount(MockBoardVO vo) {
        return mockBoardMapper.selectMockBoardListCount(vo);
    }

    public MockBoardVO getMockBoardDetail(MockBoardVO vo) {
        mockBoardMapper.updateViewCnt(vo);
        return mockBoardMapper.selectMockBoardDetail(vo);
    }

    @Transactional
    public int insertMockBoard(MockBoardVO vo) {
        return mockBoardMapper.insertMockBoard(vo);
    }

    @Transactional
    public int updateMockBoard(MockBoardVO vo) {
        return mockBoardMapper.updateMockBoard(vo);
    }

    @Transactional
    public int deleteMockBoard(MockBoardVO vo) {
        return mockBoardMapper.deleteMockBoard(vo);
    }

    @Transactional
    public int updateAnswer(MockBoardVO vo) {
        return mockBoardMapper.updateAnswer(vo);
    }

    public List<MockBoardVO> getNoticeTopList(MockBoardVO vo) {
        return mockBoardMapper.selectNoticeTopList(vo);
    }
}
