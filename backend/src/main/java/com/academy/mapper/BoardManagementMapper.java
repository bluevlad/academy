package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.board.service.BoardMngVO;
import com.academy.board.service.BoardVO;

/**
 * 게시판 관리에 관한 데이터 접근 클래스를 정의한다.
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
public interface BoardManagementMapper {

    /**
     * 게시판 관리 목록 조회
     * @param boardMngVO 검색조건
     * @return List 게시판 관리 목록정보
     */
    List<JSONObject> selectBoardMngList(BoardMngVO boardMngVO);

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
    List<JSONObject> selectBoardTypeList();

    /**
     * 게시판 관리 상세 조회
     * @param boardMngVO 검색조건
     * @return JSONObject 게시판 관리 상세정보
     */
    JSONObject selectBoardMngDetail(BoardMngVO boardMngVO);

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
     * @return JSONObject 게시판 종류 정보
     */
    JSONObject selectBoardKind(BoardVO boardVO);

}
