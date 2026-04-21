package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.board.service.BoardFileVO;
import com.academy.board.service.BoardVO;

/**
 * 미응답 게시판에 관한 데이터 접근 클래스를 정의한다.
 * @author system
 * @since 2025.12.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.10         system          최초 생성
 * </pre>
 */
@Mapper
public interface BoardNotAnswerMapper {

    // =====================================================
    // 미응답 게시판 목록 관련
    // =====================================================

    /**
     * 미응답 게시판 목록 조회
     * @param boardVO 검색조건
     * @return List 미응답 게시판 목록정보
     */
    List<JSONObject> selectBoardNotAnswerList(BoardVO boardVO);

    /**
     * 미응답 게시판 목록 건수 조회
     * @param boardVO 검색조건
     * @return int 미응답 게시판 목록 건수
     */
    int selectBoardNotAnswerListCount(BoardVO boardVO);

    // =====================================================
    // 게시판 카테고리 관련
    // =====================================================

    /**
     * 직급 코드 목록 조회
     * @param boardVO 검색조건
     * @return List 직급 코드 목록
     */
    List<JSONObject> selectRankCodeList(BoardVO boardVO);

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
    List<JSONObject> selectBoardCodeList(BoardVO boardVO);

    /**
     * 게시판 카테고리 정보 삭제
     * @param boardVO 게시판 정보
     */
    void deleteBoardCatInfo(BoardVO boardVO);

    // =====================================================
    // 게시판 상세/CRUD 관련
    // =====================================================

    /**
     * 게시판 상세 조회 (미응답용)
     * @param boardVO 검색조건
     * @return JSONObject 게시판 상세정보
     */
    JSONObject getBoardDetail(BoardVO boardVO);

    /**
     * 원본글 상세 조회 (답변글에서 참조)
     * @param boardVO 검색조건
     * @return JSONObject 원본글 상세정보
     */
    JSONObject getBoardDetailOrigin(BoardVO boardVO);

    /**
     * 답변 게시물 등록
     * @param boardVO 게시판 정보
     */
    void insertBoardReply(BoardVO boardVO);

    /**
     * 게시판 수정
     * @param boardVO 게시판 정보
     */
    void updateBoard(BoardVO boardVO);

    /**
     * 조회수 증가
     * @param boardVO 게시판 정보
     */
    void updateBoardHits(BoardVO boardVO);

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
    List<JSONObject> selectReplyData(BoardVO boardVO);

    /**
     * 게시판 삭제
     * @param boardVO 게시판 정보
     */
    void deleteBoard(BoardVO boardVO);

    // =====================================================
    // 첨부파일 관련
    // =====================================================

    /**
     * 게시판 첨부파일 목록 조회
     * @param boardFileVO 검색조건
     * @return List 게시판 첨부파일 목록
     */
    List<JSONObject> selectBoardFileList(BoardFileVO boardFileVO);

    /**
     * 게시판 이미지 첨부파일 목록 조회
     * @param boardFileVO 검색조건
     * @return List 게시판 이미지 첨부파일 목록
     */
    List<JSONObject> selectBoardFileImgList(BoardFileVO boardFileVO);

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

}
