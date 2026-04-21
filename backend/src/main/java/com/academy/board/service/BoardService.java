package com.academy.board.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.academy.mapper.BoardMapper;

/**
 * 게시판 관리 Service 클래스
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.11            rainend           게시판 등록
 *  2025.12.10         system            게시판 관리 기능 확장
 * </pre>
 */
@Service
public class BoardService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    // =====================================================
    // 게시판 관리 (TB_BOARD_MNG) 관련
    // =====================================================

    /**
     * 게시판 관리 목록 조회
     */
    public List<BoardMngVO> selectBoardMngList(BoardMngVO boardMngVO) {
        return boardMapper.selectBoardMngList(boardMngVO);
    }

    /**
     * 게시판 관리 목록 건수 조회
     */
    public int selectBoardMngListCount(BoardMngVO boardMngVO) {
        return boardMapper.selectBoardMngListCount(boardMngVO);
    }

    /**
     * 게시판 타입 목록 조회
     */
    public List<BoardTypeVO> selectBoardTypeList() {
        return boardMapper.selectBoardTypeList();
    }

    /**
     * 게시판 관리 상세 조회
     */
    public BoardMngVO selectBoardMngDetail(BoardMngVO boardMngVO) {
        return boardMapper.selectBoardMngDetail(boardMngVO);
    }

    /**
     * 게시판 관리 등록
     */
    public void insertBoardMng(BoardMngVO boardMngVO) {
        boardMapper.insertBoardMng(boardMngVO);
    }

    /**
     * 게시판 관리 수정
     */
    public void updateBoardMng(BoardMngVO boardMngVO) {
        boardMapper.updateBoardMng(boardMngVO);
    }

    /**
     * 게시판 관리 삭제
     */
    public void deleteBoardMng(BoardMngVO boardMngVO) {
        boardMapper.deleteBoardMng(boardMngVO);
    }

    /**
     * 게시판 관리 일괄 삭제
     */
    public void deleteBoardMngBatch(BoardMngVO boardMngVO) {
        boardMapper.deleteBoardMngBatch(boardMngVO);
    }

    /**
     * 게시판 종류 정보 조회
     */
    public BoardKindVO selectBoardKind(BoardVO boardVO) {
        return boardMapper.selectBoardKind(boardVO);
    }

    // =====================================================
    // 게시판 (TB_BOARD) 관련
    // =====================================================

    /**
     * 게시판 목록 조회
     */
    public List<BoardVO> selectBoardList(BoardVO boardVO) {
        return boardMapper.selectBoardList(boardVO);
    }

    /**
     * 게시판 목록 건수 조회
     */
    public int selectBoardListTotCnt(BoardVO boardVO) {
        return boardMapper.selectBoardListTotCnt(boardVO);
    }

    /**
     * FAQ 게시판 목록 조회
     */
    public List<BoardVO> selectBoardFAQList(BoardVO boardVO) {
        return boardMapper.selectBoardFAQList(boardVO);
    }

    /**
     * FAQ 게시판 목록 건수 조회
     */
    public int selectBoardFAQListCount(BoardVO boardVO) {
        return boardMapper.selectBoardFAQListCount(boardVO);
    }

    /**
     * 강사용 게시판 목록 조회
     */
    public List<BoardVO> selectBoardTeacherList(BoardVO boardVO) {
        return boardMapper.selectBoardTeacherList(boardVO);
    }

    /**
     * 강사용 게시판 목록 건수 조회
     */
    public int selectBoardTeacherListCount(BoardVO boardVO) {
        return boardMapper.selectBoardTeacherListCount(boardVO);
    }

    /**
     * 게시판 상세 조회
     */
    public BoardVO getBoardDetail(BoardVO boardVO) {
        return boardMapper.getBoardDetail(boardVO);
    }

    /**
     * 원본글 상세 조회
     */
    public BoardVO getBoardDetailOrigin(BoardVO boardVO) {
        return boardMapper.getBoardDetailOrigin(boardVO);
    }

    /**
     * 게시판 등록
     */
    public void insertBoard(BoardVO boardVO) {
        boardMapper.insertBoard(boardVO);
    }

    /**
     * FAQ 게시판 등록
     */
    public void insertBoardFAQ(BoardVO boardVO) {
        boardMapper.insertBoardFAQ(boardVO);
    }

    /**
     * 답변 게시판 등록
     */
    public void insertBoardReply(BoardVO boardVO) {
        boardMapper.insertBoardReply(boardVO);
    }

    /**
     * 게시판 수정
     */
    public void updateBoard(BoardVO boardVO) {
        boardMapper.updateBoard(boardVO);
    }

    /**
     * FAQ 게시판 수정
     */
    public void updateBoardFAQ(BoardVO boardVO) {
        boardMapper.updateBoardFAQ(boardVO);
    }

    /**
     * 게시판 삭제
     */
    public void deleteBoard(BoardVO boardVO) {
        // 첨부파일 삭제
        BoardFileVO boardFileVO = new BoardFileVO();
        boardFileVO.setBoardMngSeq(boardVO.getBoardMngSeq());
        boardFileVO.setBoardSeq(boardVO.getBoardSeq());
        boardMapper.deleteBoardFileByBoard(boardFileVO);

        // 카테고리 정보 삭제
        boardMapper.deleteBoardCatInfo(boardVO);

        // 게시글 삭제
        boardMapper.deleteBoard(boardVO);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(BoardVO boardVO) {
        boardMapper.updateBoardHits(boardVO);
    }

    /**
     * 이슈 여부 수정
     */
    public void updateBoardIssue(BoardVO boardVO) {
        boardMapper.updateBoardIssue(boardVO);
    }

    /**
     * 추천 여부 수정
     */
    public void updateBoardRecommend(BoardVO boardVO) {
        boardMapper.updateBoardRecommend(boardVO);
    }

    /**
     * 공개 여부 수정
     */
    public void updateBoardOpenYn(BoardVO boardVO) {
        boardMapper.updateBoardOpenYn(boardVO);
    }

    /**
     * 메인 노출 여부 수정
     */
    public void updateBoardMainYn(BoardVO boardVO) {
        boardMapper.updateBoardMainYn(boardVO);
    }

    /**
     * 답변 상태 수정
     */
    public void updateBoardReply(BoardVO boardVO) {
        boardMapper.updateBoardReply(boardVO);
    }

    /**
     * 답변 존재 여부 확인
     */
    public int selectReplyCount(BoardVO boardVO) {
        return boardMapper.selectReplyCount(boardVO);
    }

    /**
     * 답변 데이터 조회
     */
    public List<ReplyDataVO> selectReplyData(BoardVO boardVO) {
        return boardMapper.selectReplyData(boardVO);
    }

    // =====================================================
    // 게시판 카테고리 (TB_BOARD_CATEGORY_INFO) 관련
    // =====================================================

    /**
     * 직급 코드 목록 조회
     */
    public List<CategoryInfoVO> selectRankCodeList(BoardVO boardVO) {
        return boardMapper.selectRankCodeList(boardVO);
    }

    /**
     * 게시판 카테고리 정보 등록
     */
    public void insertBoardCatInfo(BoardVO boardVO) {
        boardMapper.insertBoardCatInfo(boardVO);
    }

    /**
     * 게시판 카테고리 정보 조회
     */
    public List<BoardCodeVO> selectBoardCodeList(BoardVO boardVO) {
        return boardMapper.selectBoardCodeList(boardVO);
    }

    /**
     * 게시판 카테고리 정보 삭제
     */
    public void deleteBoardCatInfo(BoardVO boardVO) {
        boardMapper.deleteBoardCatInfo(boardVO);
    }

    // =====================================================
    // 게시판 첨부파일 (TB_BOARD_FILE) 관련
    // =====================================================

    /**
     * 게시판 첨부파일 목록 조회
     */
    public List<BoardFileVO> selectBoardFileList(BoardFileVO boardFileVO) {
        return boardMapper.selectBoardFileList(boardFileVO);
    }

    /**
     * 게시판 이미지 첨부파일 목록 조회
     */
    public List<BoardFileVO> selectBoardFileImgList(BoardFileVO boardFileVO) {
        return boardMapper.selectBoardFileImgList(boardFileVO);
    }

    /**
     * 게시판 첨부파일 등록
     */
    public void insertBoardFile(BoardFileVO boardFileVO) {
        boardMapper.insertBoardFile(boardFileVO);
    }

    /**
     * 답변 게시판 첨부파일 등록
     */
    public void insertBoardReplyFile(BoardFileVO boardFileVO) {
        boardMapper.insertBoardReplyFile(boardFileVO);
    }

    /**
     * 게시판 첨부파일 삭제 (경로 기준)
     */
    public void deleteBoardFileByPath(BoardFileVO boardFileVO) {
        boardMapper.deleteBoardFileByPath(boardFileVO);
    }

    /**
     * 게시판 첨부파일 삭제 (게시글 기준)
     */
    public void deleteBoardFileByBoard(BoardFileVO boardFileVO) {
        boardMapper.deleteBoardFileByBoard(boardFileVO);
    }

    // =====================================================
    // 게시판 코멘트 (TB_BOARD_COMMENT) 관련
    // =====================================================

    /**
     * 게시판 코멘트 목록 조회
     */
    public List<BoardCommentVO> selectBoardCommentList(BoardCommentVO boardCommentVO) {
        return boardMapper.selectBoardCommentList(boardCommentVO);
    }

    /**
     * 게시판 코멘트 목록 건수 조회
     */
    public int selectBoardCommentListCount(BoardCommentVO boardCommentVO) {
        return boardMapper.selectBoardCommentListCount(boardCommentVO);
    }

    /**
     * 게시판 코멘트 등록
     */
    public void insertBoardComment(BoardCommentVO boardCommentVO) {
        boardMapper.insertBoardComment(boardCommentVO);
    }

    /**
     * 게시판 코멘트 삭제
     */
    public void deleteBoardComment(BoardCommentVO boardCommentVO) {
        boardMapper.deleteBoardComment(boardCommentVO);
    }

}
