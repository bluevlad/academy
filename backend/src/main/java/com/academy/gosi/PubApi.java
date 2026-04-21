package com.academy.gosi;

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
import com.academy.gosi.service.PubService;
import com.academy.gosi.service.PubVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 공지/게시판 관리 API
 */
@Tag(name = "Pub", description = "공지/게시판 관리 API")
@RestController
@RequestMapping("/api/pub")
public class PubApi extends CORSFilter {

    private final PubService pubService;

    @Autowired
    public PubApi(PubService pubService) {
        this.pubService = pubService;
    }

    // === 공지사항 관리 ===
    @Operation(summary = "공지사항 목록 조회", description = "공지사항 목록을 조회합니다.")
    @GetMapping(value = "/getPubList")
    public JSONObject getPubList(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = pubService.getPubListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<PubVO> list = pubService.getPubList(vo);

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

    @Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 정보를 조회합니다.")
    @GetMapping(value = "/getPubDetail")
    public JSONObject getPubDetail(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            // 조회수 증가
            pubService.updatePubHitCnt(vo);
            // 상세 정보 조회
            PubVO detail = pubService.getPubDetail(vo);
            // 첨부파일 목록 조회
            List<PubVO> fileList = pubService.getPubFileList(vo);

            jsonObject.put("data", detail);
            jsonObject.put("fileList", fileList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록합니다.")
    @PostMapping(value = "/insertPub")
    public JSONObject insertPub(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = pubService.insertPub(vo);
            jsonObject.put("data", result);
            jsonObject.put("pubNo", vo.getPubNo());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정합니다.")
    @PostMapping(value = "/updatePub")
    public JSONObject updatePub(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = pubService.updatePub(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    @PostMapping(value = "/deletePub")
    public JSONObject deletePub(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = pubService.deletePub(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 첨부파일 관리 ===
    @Operation(summary = "첨부파일 목록 조회", description = "공지사항의 첨부파일 목록을 조회합니다.")
    @GetMapping(value = "/getPubFileList")
    public JSONObject getPubFileList(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<PubVO> list = pubService.getPubFileList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "첨부파일 등록", description = "공지사항에 첨부파일을 등록합니다.")
    @PostMapping(value = "/insertPubFile")
    public JSONObject insertPubFile(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = pubService.insertPubFile(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "첨부파일 삭제", description = "공지사항의 첨부파일을 삭제합니다.")
    @PostMapping(value = "/deletePubFile")
    public JSONObject deletePubFile(@ModelAttribute PubVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = pubService.deletePubFile(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
