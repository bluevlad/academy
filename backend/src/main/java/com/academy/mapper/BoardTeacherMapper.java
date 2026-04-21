package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.board.service.BoardVO;

/**
 * 강사용 게시판에 관한 데이터 접근 클래스를 정의한다.
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
public interface BoardTeacherMapper {

    /**
     * 강사용 게시판 목록 조회
     * @param boardVO 검색조건
     * @return List 강사용 게시판 목록정보
     */
    List<JSONObject> selectBoardTeacherList(BoardVO boardVO);

    /**
     * 강사용 게시판 목록 건수 조회
     * @param boardVO 검색조건
     * @return int 강사용 게시판 목록 건수
     */
    int selectBoardTeacherListCount(BoardVO boardVO);

    /**
     * 강사용 게시판 상세 조회
     * @param boardVO 검색조건
     * @return JSONObject 강사용 게시판 상세정보
     */
    JSONObject getBoardTeacherDetail(BoardVO boardVO);

    /**
     * 강사용 게시판 등록
     * @param boardVO 게시판 정보
     */
    void insertBoardTeacher(BoardVO boardVO);

    /**
     * 강사용 게시판 수정
     * @param boardVO 게시판 정보
     */
    void updateBoardTeacher(BoardVO boardVO);

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

}
