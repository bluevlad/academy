package com.academy.mocktest.offExamReg.registration;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.offExamReg.registration.service.OffExamRegService;
import com.academy.mocktest.offExamReg.registration.service.OffExamRegVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "OffExamReg", description = "오프라인 시험 접수 관리 API")
@RestController
@RequestMapping("/api/mocktest/offExamReg/registration")
public class OffExamRegApi extends CORSFilter {

    private final OffExamRegService offExamRegService;

    @Autowired
    public OffExamRegApi(OffExamRegService offExamRegService) {
        this.offExamRegService = offExamRegService;
    }

    @Operation(summary = "접수 목록 조회")
    @GetMapping("/getOffExamRegList")
    public JSONObject getOffExamRegList(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = offExamRegService.getOffExamRegListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", offExamRegService.getOffExamRegList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "접수 상세 조회")
    @GetMapping("/getOffExamRegDetail")
    public JSONObject getOffExamRegDetail(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamRegService.getOffExamRegDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "접수 등록")
    @PostMapping("/insertOffExamReg")
    public JSONObject insertOffExamReg(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamRegService.insertOffExamReg(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "접수 수정")
    @PostMapping("/updateOffExamReg")
    public JSONObject updateOffExamReg(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamRegService.updateOffExamReg(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "접수 삭제")
    @PostMapping("/deleteOffExamReg")
    public JSONObject deleteOffExamReg(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamRegService.deleteOffExamReg(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "접수 상태 변경")
    @PostMapping("/updateOffExamRegStatus")
    public JSONObject updateOffExamRegStatus(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamRegService.updateOffExamRegStatus(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "성적/결과 입력")
    @PostMapping("/updateOffExamRegResult")
    public JSONObject updateOffExamRegResult(@ModelAttribute OffExamRegVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamRegService.updateOffExamRegResult(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
