package com.academy.mocktest.mouigosa.apply;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.mouigosa.apply.service.MouiApplyService;
import com.academy.mocktest.mouigosa.apply.service.MouiApplyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MouiApply", description = "모의고사 신청 관리 API")
@RestController
@RequestMapping("/api/mocktest/mouigosa/apply")
public class MouiApplyApi extends CORSFilter {

    private final MouiApplyService mouiApplyService;

    @Autowired
    public MouiApplyApi(MouiApplyService mouiApplyService) {
        this.mouiApplyService = mouiApplyService;
    }

    @Operation(summary = "신청 목록 조회")
    @GetMapping("/getMouiApplyList")
    public JSONObject getMouiApplyList(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = mouiApplyService.getMouiApplyListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", mouiApplyService.getMouiApplyList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "신청 상세 조회")
    @GetMapping("/getMouiApplyDetail")
    public JSONObject getMouiApplyDetail(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiApplyService.getMouiApplyDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "신청 등록")
    @PostMapping("/insertMouiApply")
    public JSONObject insertMouiApply(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiApplyService.insertMouiApply(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "신청 수정")
    @PostMapping("/updateMouiApply")
    public JSONObject updateMouiApply(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiApplyService.updateMouiApply(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "신청 삭제")
    @PostMapping("/deleteMouiApply")
    public JSONObject deleteMouiApply(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiApplyService.deleteMouiApply(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "신청 상태 변경")
    @PostMapping("/updateApplyStatus")
    public JSONObject updateApplyStatus(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiApplyService.updateApplyStatus(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 결과 등록")
    @PostMapping("/updateExamResult")
    public JSONObject updateExamResult(@ModelAttribute MouiApplyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiApplyService.updateExamResult(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
