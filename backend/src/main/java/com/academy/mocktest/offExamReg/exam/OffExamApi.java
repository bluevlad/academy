package com.academy.mocktest.offExamReg.exam;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.offExamReg.exam.service.OffExamService;
import com.academy.mocktest.offExamReg.exam.service.OffExamVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "OffExam", description = "오프라인 시험 관리 API")
@RestController
@RequestMapping("/api/mocktest/offExamReg/exam")
public class OffExamApi extends CORSFilter {

    private final OffExamService offExamService;

    @Autowired
    public OffExamApi(OffExamService offExamService) {
        this.offExamService = offExamService;
    }

    @Operation(summary = "시험 목록 조회")
    @GetMapping("/getOffExamList")
    public JSONObject getOffExamList(@ModelAttribute OffExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = offExamService.getOffExamListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", offExamService.getOffExamList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 상세 조회")
    @GetMapping("/getOffExamDetail")
    public JSONObject getOffExamDetail(@ModelAttribute OffExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamService.getOffExamDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 등록")
    @PostMapping("/insertOffExam")
    public JSONObject insertOffExam(@ModelAttribute OffExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamService.insertOffExam(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 수정")
    @PostMapping("/updateOffExam")
    public JSONObject updateOffExam(@ModelAttribute OffExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamService.updateOffExam(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 삭제")
    @PostMapping("/deleteOffExam")
    public JSONObject deleteOffExam(@ModelAttribute OffExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamService.deleteOffExam(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 상태 변경")
    @PostMapping("/updateOffExamStatus")
    public JSONObject updateOffExamStatus(@ModelAttribute OffExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", offExamService.updateOffExamStatus(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
