package com.academy.survey;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.survey.service.SurveyService;
import com.academy.survey.service.SurveyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Survey", description = "설문조사 관리 API")
@RestController
@RequestMapping("/api/survey")
public class SurveyApi extends CORSFilter {

    private final SurveyService surveyService;

    @Autowired
    public SurveyApi(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    // ========== 설문 문항 (Bank) ==========
    @Operation(summary = "설문 문항 목록 조회")
    @GetMapping("/bank/getBankList")
    public JSONObject getBankList(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = surveyService.getBankListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", surveyService.getBankList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 문항 상세 조회")
    @GetMapping("/bank/getBankDetail")
    public JSONObject getBankDetail(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.getBankDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 문항 등록")
    @PostMapping("/bank/insertBank")
    public JSONObject insertBank(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.insertBank(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 문항 수정")
    @PostMapping("/bank/updateBank")
    public JSONObject updateBank(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.updateBank(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 문항 삭제")
    @PostMapping("/bank/deleteBank")
    public JSONObject deleteBank(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.deleteBank(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    // ========== 설문 세트 (Set) ==========
    @Operation(summary = "설문 세트 목록 조회")
    @GetMapping("/set/getSetList")
    public JSONObject getSetList(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = surveyService.getSetListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", surveyService.getSetList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 세트 상세 조회")
    @GetMapping("/set/getSetDetail")
    public JSONObject getSetDetail(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.getSetDetail(vo));
            json.put("items", surveyService.getSetItemList(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 세트 등록")
    @PostMapping("/set/insertSet")
    public JSONObject insertSet(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.insertSet(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 세트 수정")
    @PostMapping("/set/updateSet")
    public JSONObject updateSet(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.updateSet(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 세트 삭제")
    @PostMapping("/set/deleteSet")
    public JSONObject deleteSet(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.deleteSet(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    // ========== 설문 세트 항목 ==========
    @Operation(summary = "설문 세트 항목 추가")
    @PostMapping("/set/insertSetItem")
    public JSONObject insertSetItem(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.insertSetItem(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 세트 항목 순서 수정")
    @PostMapping("/set/updateSetItem")
    public JSONObject updateSetItem(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.updateSetItem(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 세트 항목 삭제")
    @PostMapping("/set/deleteSetItem")
    public JSONObject deleteSetItem(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.deleteSetItem(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    // ========== 설문 (Survey) ==========
    @Operation(summary = "설문 목록 조회")
    @GetMapping("/getSurveyList")
    public JSONObject getSurveyList(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = surveyService.getSurveyListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", surveyService.getSurveyList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 상세 조회")
    @GetMapping("/getSurveyDetail")
    public JSONObject getSurveyDetail(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.getSurveyDetail(vo));
            json.put("items", surveyService.getSetItemList(vo));
            json.put("results", surveyService.getSurveyResultList(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 등록")
    @PostMapping("/insertSurvey")
    public JSONObject insertSurvey(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.insertSurvey(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 수정")
    @PostMapping("/updateSurvey")
    public JSONObject updateSurvey(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.updateSurvey(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "설문 삭제")
    @PostMapping("/deleteSurvey")
    public JSONObject deleteSurvey(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.deleteSurvey(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    // ========== 설문 결과 ==========
    @Operation(summary = "설문 응답자 목록 조회")
    @GetMapping("/getAnswerList")
    public JSONObject getAnswerList(@ModelAttribute SurveyVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", surveyService.getAnswerList(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
