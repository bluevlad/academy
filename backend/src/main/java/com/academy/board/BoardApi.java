package com.academy.board;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.board.service.BoardCommentVO;
import com.academy.board.service.BoardFileVO;
import com.academy.board.service.BoardMngVO;
import com.academy.board.service.BoardService;
import com.academy.board.service.BoardVO;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 게시판 관리 API 컨트롤러
 * 
 * @author rainend
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.11            rainend           게시판 등록
 *  2025.12.10         system            게시판 관리 기능 확장 (게시판 관리, FAQ, 강사용, 미응답, 첨부파일, 코멘트)
 *      </pre>
 */
@Tag(name = "Board", description = "게시판 관리 API")
@RestController
@RequestMapping("/api/board")
public class BoardApi extends CORSFilter {

    private final BoardService boardService;

    public BoardApi(BoardService boardService) {
        this.boardService = boardService;
    }

    // =====================================================
    // 게시판 관리 (TB_BOARD_MNG) 관련 API
    // =====================================================

    @Operation(summary = "게시판 관리 목록 조회", description = "페이징 처리된 게시판 관리 목록을 조회합니다.")
    @GetMapping(value = "/getBoardMngList")
    public JSONObject getBoardMngList(@ModelAttribute("BoardMngVO") BoardMngVO boardMngVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boardMngVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardMngVO.getPageUnit());
        paginationInfo.setPageSize(boardMngVO.getPageSize());

        boardMngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardMngVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardMngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("boardMngList", boardService.selectBoardMngList(boardMngVO));

        int totCnt = boardService.selectBoardMngListCount(boardMngVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 타입 목록 조회", description = "게시판 타입(공지, 일반 등) 목록을 조회합니다.")
    @GetMapping(value = "/getBoardTypeList")
    public JSONObject getBoardTypeList() throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardTypeList", boardService.selectBoardTypeList());

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 관리 상세 조회", description = "게시판 관리 상세 정보를 조회합니다.")
    @GetMapping(value = "/getBoardMngDetail")
    public JSONObject getBoardMngDetail(@ModelAttribute("BoardMngVO") BoardMngVO boardMngVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardMngDetail", boardService.selectBoardMngDetail(boardMngVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 관리 등록", description = "새로운 게시판을 등록합니다.")
    @PostMapping(value = "/insertBoardMng")
    public JSONObject insertBoardMng(@ModelAttribute("BoardMngVO") BoardMngVO boardMngVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardMng(boardMngVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 관리 수정", description = "게시판 관리 정보를 수정합니다.")
    @PostMapping(value = "/updateBoardMng")
    public JSONObject updateBoardMng(@ModelAttribute("BoardMngVO") BoardMngVO boardMngVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardMng(boardMngVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 관리 삭제", description = "게시판을 삭제합니다.")
    @PostMapping(value = "/deleteBoardMng")
    public JSONObject deleteBoardMng(@ModelAttribute("BoardMngVO") BoardMngVO boardMngVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoardMng(boardMngVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 관리 일괄 삭제", description = "선택된 게시판들을 일괄 삭제합니다.")
    @PostMapping(value = "/deleteBoardMngBatch")
    public JSONObject deleteBoardMngBatch(@ModelAttribute("BoardMngVO") BoardMngVO boardMngVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoardMngBatch(boardMngVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 종류 정보 조회", description = "게시물 등록 시 필요한 게시판 종류 정보를 조회합니다.")
    @GetMapping(value = "/getBoardKind")
    public JSONObject getBoardKind(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardKind", boardService.selectBoardKind(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 게시판 (TB_BOARD) 관련 API
    // =====================================================

    @Operation(summary = "게시판 목록 조회", description = "페이징 처리된 게시판 목록을 조회합니다.")
    @GetMapping(value = "/getBoardList")
    public JSONObject getBoardList(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
        paginationInfo.setPageSize(boardVO.getPageSize());

        boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("boardList", boardService.selectBoardList(boardVO));

        int totCnt = boardService.selectBoardListTotCnt(boardVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "FAQ 게시판 목록 조회", description = "페이징 처리된 FAQ 게시판 목록을 조회합니다.")
    @GetMapping(value = "/getBoardFAQList")
    public JSONObject getBoardFAQList(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
        paginationInfo.setPageSize(boardVO.getPageSize());

        boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("boardFAQList", boardService.selectBoardFAQList(boardVO));

        int totCnt = boardService.selectBoardFAQListCount(boardVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "강사용 게시판 목록 조회", description = "페이징 처리된 강사용 게시판 목록을 조회합니다.")
    @GetMapping(value = "/getBoardTeacherList")
    public JSONObject getBoardTeacherList(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
        paginationInfo.setPageSize(boardVO.getPageSize());

        boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("boardTeacherList", boardService.selectBoardTeacherList(boardVO));

        int totCnt = boardService.selectBoardTeacherListCount(boardVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 상세 조회", description = "게시판 상세 정보를 조회합니다.")
    @GetMapping(value = "/getBoardDetail")
    public JSONObject getBoardDetail(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardDetail", boardService.getBoardDetail(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "원본글 상세 조회", description = "답변글에서 참조하는 원본글 상세 정보를 조회합니다.")
    @GetMapping(value = "/getBoardDetailOrigin")
    public JSONObject getBoardDetailOrigin(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardDetailOrigin", boardService.getBoardDetailOrigin(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시물 등록", description = "새로운 게시물을 등록합니다.")
    @PostMapping(value = "/insertBoard")
    public JSONObject insertBoard(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoard(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "FAQ 게시물 등록", description = "새로운 FAQ 게시물을 등록합니다.")
    @PostMapping(value = "/insertBoardFAQ")
    public JSONObject insertBoardFAQ(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardFAQ(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "답변 게시물 등록", description = "원본글에 대한 답변 게시물을 등록합니다.")
    @PostMapping(value = "/insertBoardReply")
    public JSONObject insertBoardReply(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardReply(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시물 수정", description = "게시물 정보를 수정합니다.")
    @PostMapping(value = "/updateBoard")
    public JSONObject updateBoard(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoard(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "FAQ 게시물 수정", description = "FAQ 게시물 정보를 수정합니다.")
    @PostMapping(value = "/updateBoardFAQ")
    public JSONObject updateBoardFAQ(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardFAQ(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시물 삭제", description = "게시물을 삭제합니다.")
    @PostMapping(value = "/deleteBoard")
    public JSONObject deleteBoard(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoard(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "조회수 증가", description = "게시물 조회수를 1 증가시킵니다.")
    @PostMapping(value = "/updateBoardHits")
    public JSONObject updateBoardHits(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardHits(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "이슈 여부 수정", description = "게시물의 이슈 여부를 수정합니다.")
    @PostMapping(value = "/updateBoardIssue")
    public JSONObject updateBoardIssue(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardIssue(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "추천 여부 수정", description = "게시물의 추천 여부를 수정합니다.")
    @PostMapping(value = "/updateBoardRecommend")
    public JSONObject updateBoardRecommend(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardRecommend(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "공개 여부 수정", description = "게시물의 공개 여부를 수정합니다.")
    @PostMapping(value = "/updateBoardOpenYn")
    public JSONObject updateBoardOpenYn(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardOpenYn(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "메인 노출 여부 수정", description = "게시물의 메인 노출 여부를 수정합니다.")
    @PostMapping(value = "/updateBoardMainYn")
    public JSONObject updateBoardMainYn(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardMainYn(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "답변 상태 수정", description = "게시물의 답변 상태를 수정합니다.")
    @PostMapping(value = "/updateBoardReply")
    public JSONObject updateBoardReply(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.updateBoardReply(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "답변 존재 여부 확인", description = "게시물에 대한 답변 존재 여부를 확인합니다.")
    @GetMapping(value = "/getReplyCount")
    public JSONObject getReplyCount(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("replyCount", boardService.selectReplyCount(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "답변 데이터 조회", description = "게시물에 대한 답변 데이터 목록을 조회합니다.")
    @GetMapping(value = "/getReplyData")
    public JSONObject getReplyData(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("replyData", boardService.selectReplyData(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 게시판 카테고리 (TB_BOARD_CATEGORY_INFO) 관련 API
    // =====================================================

    @Operation(summary = "직급 코드 목록 조회", description = "직급 코드 목록을 조회합니다.")
    @GetMapping(value = "/getRankCodeList")
    public JSONObject getRankCodeList(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("rankCodeList", boardService.selectRankCodeList(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 카테고리 정보 등록", description = "게시판 카테고리 정보를 등록합니다.")
    @PostMapping(value = "/insertBoardCatInfo")
    public JSONObject insertBoardCatInfo(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardCatInfo(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 카테고리 정보 조회", description = "게시판 카테고리 정보 목록을 조회합니다.")
    @GetMapping(value = "/getBoardCodeList")
    public JSONObject getBoardCodeList(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardCodeList", boardService.selectBoardCodeList(boardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 카테고리 정보 삭제", description = "게시판 카테고리 정보를 삭제합니다.")
    @PostMapping(value = "/deleteBoardCatInfo")
    public JSONObject deleteBoardCatInfo(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoardCatInfo(boardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 게시판 첨부파일 (TB_BOARD_FILE) 관련 API
    // =====================================================

    @Operation(summary = "게시판 첨부파일 목록 조회", description = "게시물의 첨부파일 목록을 조회합니다.")
    @GetMapping(value = "/getBoardFileList")
    public JSONObject getBoardFileList(@ModelAttribute("BoardFileVO") BoardFileVO boardFileVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardFileList", boardService.selectBoardFileList(boardFileVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 이미지 첨부파일 목록 조회", description = "게시물의 이미지 첨부파일 목록을 조회합니다.")
    @GetMapping(value = "/getBoardFileImgList")
    public JSONObject getBoardFileImgList(@ModelAttribute("BoardFileVO") BoardFileVO boardFileVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boardFileImgList", boardService.selectBoardFileImgList(boardFileVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 첨부파일 등록", description = "게시물에 첨부파일을 등록합니다.")
    @PostMapping(value = "/insertBoardFile")
    public JSONObject insertBoardFile(@ModelAttribute("BoardFileVO") BoardFileVO boardFileVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardFile(boardFileVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "답변 게시판 첨부파일 등록", description = "답변 게시물에 첨부파일을 등록합니다.")
    @PostMapping(value = "/insertBoardReplyFile")
    public JSONObject insertBoardReplyFile(@ModelAttribute("BoardFileVO") BoardFileVO boardFileVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardReplyFile(boardFileVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 첨부파일 삭제 (경로 기준)", description = "파일 경로를 기준으로 첨부파일을 삭제합니다.")
    @PostMapping(value = "/deleteBoardFileByPath")
    public JSONObject deleteBoardFileByPath(@ModelAttribute("BoardFileVO") BoardFileVO boardFileVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoardFileByPath(boardFileVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 첨부파일 삭제 (게시글 기준)", description = "게시글을 기준으로 모든 첨부파일을 삭제합니다.")
    @PostMapping(value = "/deleteBoardFileByBoard")
    public JSONObject deleteBoardFileByBoard(@ModelAttribute("BoardFileVO") BoardFileVO boardFileVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoardFileByBoard(boardFileVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 게시판 코멘트 (TB_BOARD_COMMENT) 관련 API
    // =====================================================

    @Operation(summary = "게시판 코멘트 목록 조회", description = "페이징 처리된 게시판 코멘트 목록을 조회합니다.")
    @GetMapping(value = "/getBoardCommentList")
    public JSONObject getBoardCommentList(@ModelAttribute("BoardCommentVO") BoardCommentVO boardCommentVO)
            throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boardCommentVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardCommentVO.getPageUnit());
        paginationInfo.setPageSize(boardCommentVO.getPageSize());

        boardCommentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardCommentVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardCommentVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("boardCommentList", boardService.selectBoardCommentList(boardCommentVO));

        int totCnt = boardService.selectBoardCommentListCount(boardCommentVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 코멘트 등록", description = "게시물에 코멘트를 등록합니다.")
    @PostMapping(value = "/insertBoardComment")
    public JSONObject insertBoardComment(@ModelAttribute("BoardCommentVO") BoardCommentVO boardCommentVO)
            throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.insertBoardComment(boardCommentVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    @Operation(summary = "게시판 코멘트 삭제", description = "게시물의 코멘트를 삭제합니다.")
    @PostMapping(value = "/deleteBoardComment")
    public JSONObject deleteBoardComment(@ModelAttribute("BoardCommentVO") BoardCommentVO boardCommentVO)
            throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boardService.deleteBoardComment(boardCommentVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

}
