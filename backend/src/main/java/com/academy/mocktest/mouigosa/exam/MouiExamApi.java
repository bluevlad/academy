package com.academy.mocktest.mouigosa.exam;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.mouigosa.exam.service.MouiExamService;
import com.academy.mocktest.mouigosa.exam.service.MouiExamVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MouiExam", description = "모의고사 시험 관리 API")
@RestController
@RequestMapping("/api/mocktest/mouigosa/exam")
public class MouiExamApi extends CORSFilter {

    private final MouiExamService mouiExamService;

    @Autowired
    public MouiExamApi(MouiExamService mouiExamService) {
        this.mouiExamService = mouiExamService;
    }

    @Operation(summary = "시험 목록 조회")
    @GetMapping("/getMouiExamList")
    public JSONObject getMouiExamList(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = mouiExamService.getMouiExamListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", mouiExamService.getMouiExamList(vo));
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
    @GetMapping("/getMouiExamDetail")
    public JSONObject getMouiExamDetail(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiExamService.getMouiExamDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 등록")
    @PostMapping("/insertMouiExam")
    public JSONObject insertMouiExam(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiExamService.insertMouiExam(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 수정")
    @PostMapping("/updateMouiExam")
    public JSONObject updateMouiExam(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiExamService.updateMouiExam(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 삭제")
    @PostMapping("/deleteMouiExam")
    public JSONObject deleteMouiExam(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiExamService.deleteMouiExam(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험 상태 변경")
    @PostMapping("/updateExamStatus")
    public JSONObject updateExamStatus(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiExamService.updateExamStatus(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "시험년도 목록")
    @GetMapping("/getExamYearList")
    public JSONObject getExamYearList(@ModelAttribute MouiExamVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", mouiExamService.getExamYearList(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
