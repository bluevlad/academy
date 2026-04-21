package com.academy.mocktest.board;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.board.service.MockBoardService;
import com.academy.mocktest.board.service.MockBoardVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 모의고사 게시판 API
 */
@Tag(name = "MockBoard", description = "모의고사 게시판 API")
@RestController
@RequestMapping("/api/mocktest/board")
public class MockBoardApi extends CORSFilter {

    private final MockBoardService mockBoardService;

    @Autowired
    public MockBoardApi(MockBoardService mockBoardService) {
        this.mockBoardService = mockBoardService;
    }

    @Operation(summary = "게시글 목록 조회", description = "모의고사 게시판 목록을 조회합니다.")
    @GetMapping(value = "/getMockBoardList")
    public JSONObject getMockBoardList(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = mockBoardService.getMockBoardListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<MockBoardVO> list = mockBoardService.getMockBoardList(vo);

            jsonObject.put("data", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 정보를 조회합니다.")
    @GetMapping(value = "/getMockBoardDetail")
    public JSONObject getMockBoardDetail(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            MockBoardVO detail = mockBoardService.getMockBoardDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "게시글 등록", description = "게시글을 등록합니다.")
    @PostMapping(value = "/insertMockBoard")
    public JSONObject insertMockBoard(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = mockBoardService.insertMockBoard(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PostMapping(value = "/updateMockBoard")
    public JSONObject updateMockBoard(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = mockBoardService.updateMockBoard(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @PostMapping(value = "/deleteMockBoard")
    public JSONObject deleteMockBoard(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = mockBoardService.deleteMockBoard(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "답변 등록/수정", description = "Q&A 답변을 등록/수정합니다.")
    @PostMapping(value = "/updateAnswer")
    public JSONObject updateAnswer(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = mockBoardService.updateAnswer(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공지사항 상단 목록", description = "상단 고정 공지사항 목록을 조회합니다.")
    @GetMapping(value = "/getNoticeTopList")
    public JSONObject getNoticeTopList(@ModelAttribute MockBoardVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<MockBoardVO> list = mockBoardService.getNoticeTopList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
