package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.academy.board.service.BoardCodeVO;
import com.academy.board.service.BoardCommentVO;
import com.academy.board.service.BoardFileVO;
import com.academy.board.service.BoardKindVO;
import com.academy.board.service.BoardMngVO;
import com.academy.board.service.BoardTypeVO;
import com.academy.board.service.BoardVO;
import com.academy.board.service.CategoryInfoVO;
import com.academy.board.service.ReplyDataVO;

/**
 * 게시판정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.02.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.02.10  		rainend          최초 생성
 *   2025.12.10         system           게시판 관리 기능 확장
 * </pre>
 */
@Mapper
public interface BoardMapper {

    // =====================================================
    // 게시판 관리 (TB_BOARD_MNG) 관련
    // =====================================================

    /**
     * 게시판 관리 목록 조회
     * @param boardMngVO 검색조건
     * @return List 게시판 관리 목록정보
     */
    List<BoardMngVO> selectBoardMngList(BoardMngVO boardMngVO);

    /**
     * 게시판 관리 목록 건수 조회
     * @param boardMngVO 검색조건
     * @return int 게시판 관리 목록 건수
     */
    int selectBoardMngListCount(BoardMngVO boardMngVO);

    /**
     * 게시판 타입 목록 조회
     * @return List 게시판 타입 목록
     */
    List<BoardTypeVO> selectBoardTypeList();

    /**
     * 게시판 관리 상세 조회
     * @param boardMngVO 검색조건
     * @return BoardMngVO 게시판 관리 상세정보
     */
    BoardMngVO selectBoardMngDetail(BoardMngVO boardMngVO);

    /**
     * 게시판 관리 등록
     * @param boardMngVO 게시판 관리 정보
     */
    void insertBoardMng(BoardMngVO boardMngVO);

    /**
     * 게시판 관리 수정
     * @param boardMngVO 게시판 관리 정보
     */
    void updateBoardMng(BoardMngVO boardMngVO);

    /**
     * 게시판 관리 삭제
     * @param boardMngVO 게시판 관리 정보
     */
    void deleteBoardMng(BoardMngVO boardMngVO);

    /**
     * 게시판 관리 일괄 삭제
     * @param boardMngVO 게시판 관리 정보
     */
    void deleteBoardMngBatch(BoardMngVO boardMngVO);

    /**
     * 게시판 종류 정보 조회 (게시물 등록 시)
     * @param boardVO 검색조건
     * @return BoardKindVO 게시판 종류 정보
     */
    BoardKindVO selectBoardKind(BoardVO boardVO);

    // =====================================================
    // 게시판 (TB_BOARD) 관련
    // =====================================================

    /**
     * 게시판 목록 조회
     * @param boardVO 검색조건
     * @return List 게시판 목록정보
     */
    List<BoardVO> selectBoardList(BoardVO boardVO);

    /**
     * 게시판 목록 건수 조회
     * @param boardVO 검색조건
     * @return int 게시판 목록 건수
     */
    int selectBoardListTotCnt(BoardVO boardVO);

    /**
     * 미응답 게시판 목록 조회
     * @param boardVO 검색조건
     * @return List 미응답 게시판 목록정보
     */
    List<BoardVO> selectBoardNotAnswerList(BoardVO boardVO);

    /**
     * 미응답 게시판 목록 건수 조회
     * @param boardVO 검색조건
     * @return int 미응답 게시판 목록 건수
     */
    int selectBoardNotAnswerListCount(BoardVO boardVO);

    /**
     * FAQ 게시판 목록 조회
     * @param boardVO 검색조건
     * @return List FAQ 게시판 목록정보
     */
    List<BoardVO> selectBoardFAQList(BoardVO boardVO);

    /**
     * FAQ 게시판 목록 건수 조회
     * @param boardVO 검색조건
     * @return int FAQ 게시판 목록 건수
     */
    int selectBoardFAQListCount(BoardVO boardVO);

    /**
     * 강사용 게시판 목록 조회
     * @param boardVO 검색조건
     * @return List 강사용 게시판 목록정보
     */
    List<BoardVO> selectBoardTeacherList(BoardVO boardVO);

    /**
     * 강사용 게시판 목록 건수 조회
     * @param boardVO 검색조건
     * @return int 강사용 게시판 목록 건수
     */
    int selectBoardTeacherListCount(BoardVO boardVO);

    /**
     * 게시판 상세 조회
     * @param boardVO 검색조건
     * @return BoardVO 게시판 상세정보
     */
    BoardVO getBoardDetail(BoardVO boardVO);

    /**
     * 원본글 상세 조회 (답변글에서 참조)
     * @param boardVO 검색조건
     * @return BoardVO 원본글 상세정보
     */
    BoardVO getBoardDetailOrigin(BoardVO boardVO);

    /**
     * 게시판 등록
     * @param boardVO 게시판 정보
     */
    void insertBoard(BoardVO boardVO);

    /**
     * FAQ 게시판 등록
     * @param boardVO 게시판 정보
     */
    void insertBoardFAQ(BoardVO boardVO);

    /**
     * 답변 게시판 등록
     * @param boardVO 게시판 정보
     */
    void insertBoardReply(BoardVO boardVO);

    /**
     * 게시판 수정
     * @param boardVO 게시판 정보
     */
    void updateBoard(BoardVO boardVO);

    /**
     * FAQ 게시판 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardFAQ(BoardVO boardVO);

    /**
     * 게시판 삭제
     * @param boardVO 게시판 정보
     */
    void deleteBoard(BoardVO boardVO);

    /**
     * 조회수 증가
     * @param boardVO 게시판 정보
     */
    void updateBoardHits(BoardVO boardVO);

    /**
     * 이슈 여부 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardIssue(BoardVO boardVO);

    /**
     * 추천 여부 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardRecommend(BoardVO boardVO);

    /**
     * 공개 여부 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardOpenYn(BoardVO boardVO);

    /**
     * 메인 노출 여부 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardMainYn(BoardVO boardVO);

    /**
     * 답변 상태 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardReply(BoardVO boardVO);

    /**
     * 답변 존재 여부 확인
     * @param boardVO 검색조건
     * @return int 답변 수
     */
    int selectReplyCount(BoardVO boardVO);

    /**
     * 답변 데이터 조회
     * @param boardVO 검색조건
     * @return List 답변 데이터 목록
     */
    List<ReplyDataVO> selectReplyData(BoardVO boardVO);

    // =====================================================
    // 게시판 카테고리 (TB_BOARD_CATEGORY_INFO) 관련
    // =====================================================

    /**
     * 직급 코드 목록 조회
     * @param boardVO 검색조건
     * @return List 직급 코드 목록
     */
    List<CategoryInfoVO> selectRankCodeList(BoardVO boardVO);

    /**
     * 게시판 카테고리 정보 등록
     * @param boardVO 게시판 정보
     */
    void insertBoardCatInfo(BoardVO boardVO);

    /**
     * 게시판 카테고리 정보 조회
     * @param boardVO 검색조건
     * @return List 게시판 카테고리 정보 목록
     */
    List<BoardCodeVO> selectBoardCodeList(BoardVO boardVO);

    /**
     * 게시판 카테고리 정보 삭제
     * @param boardVO 게시판 정보
     */
    void deleteBoardCatInfo(BoardVO boardVO);

    // =====================================================
    // 게시판 첨부파일 (TB_BOARD_FILE) 관련
    // =====================================================

    /**
     * 게시판 첨부파일 목록 조회
     * @param boardFileVO 검색조건
     * @return List 게시판 첨부파일 목록
     */
    List<BoardFileVO> selectBoardFileList(BoardFileVO boardFileVO);

    /**
     * 게시판 이미지 첨부파일 목록 조회
     * @param boardFileVO 검색조건
     * @return List 게시판 이미지 첨부파일 목록
     */
    List<BoardFileVO> selectBoardFileImgList(BoardFileVO boardFileVO);

    /**
     * 게시판 첨부파일 등록
     * @param boardFileVO 첨부파일 정보
     */
    void insertBoardFile(BoardFileVO boardFileVO);

    /**
     * 답변 게시판 첨부파일 등록
     * @param boardFileVO 첨부파일 정보
     */
    void insertBoardReplyFile(BoardFileVO boardFileVO);

    /**
     * 게시판 첨부파일 삭제 (경로 기준)
     * @param boardFileVO 첨부파일 정보
     */
    void deleteBoardFileByPath(BoardFileVO boardFileVO);

    /**
     * 게시판 첨부파일 삭제 (게시글 기준)
     * @param boardFileVO 첨부파일 정보
     */
    void deleteBoardFileByBoard(BoardFileVO boardFileVO);

    // =====================================================
    // 게시판 코멘트 (TB_BOARD_COMMENT) 관련
    // =====================================================

    /**
     * 게시판 코멘트 목록 조회
     * @param boardCommentVO 검색조건
     * @return List 게시판 코멘트 목록
     */
    List<BoardCommentVO> selectBoardCommentList(BoardCommentVO boardCommentVO);

    /**
     * 게시판 코멘트 목록 건수 조회
     * @param boardCommentVO 검색조건
     * @return int 게시판 코멘트 목록 건수
     */
    int selectBoardCommentListCount(BoardCommentVO boardCommentVO);

    /**
     * 게시판 코멘트 등록
     * @param boardCommentVO 코멘트 정보
     */
    void insertBoardComment(BoardCommentVO boardCommentVO);

    /**
     * 게시판 코멘트 삭제
     * @param boardCommentVO 코멘트 정보
     */
    void deleteBoardComment(BoardCommentVO boardCommentVO);

}
