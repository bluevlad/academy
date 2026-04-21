package com.academy.stat;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.stat.service.SalesStatService;
import com.academy.stat.service.SalesStatVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SalesStat", description = "매출 통계 API")
@RestController
@RequestMapping("/api/stat")
public class SalesStatApi extends CORSFilter {

    private final SalesStatService salesStatService;

    @Autowired
    public SalesStatApi(SalesStatService salesStatService) {
        this.salesStatService = salesStatService;
    }

    @Operation(summary = "강사 목록 조회")
    @GetMapping("/teacher/getTeacherList")
    public JSONObject getTeacherList(@ModelAttribute SalesStatVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", salesStatService.getTeacherList(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "강사별 과목 목록 조회")
    @GetMapping("/teacher/getTeacherSubjectList")
    public JSONObject getTeacherSubjectList(@ModelAttribute SalesStatVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", salesStatService.getTeacherSubjectList(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "강사 상세 조회")
    @GetMapping("/teacher/getTeacherDetail")
    public JSONObject getTeacherDetail(@ModelAttribute SalesStatVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", salesStatService.getTeacherOne(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "강사 매출 통계 조회")
    @GetMapping("/teacher/getTeacherSalesStat")
    public JSONObject getTeacherSalesStat(@ModelAttribute SalesStatVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", salesStatService.getTeacherSalesStatList(vo));
            json.put("view", salesStatService.getTeacherOne(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "회원 구매 통계 조회")
    @GetMapping("/sales/getUserBuyStat")
    public JSONObject getUserBuyStat(@ModelAttribute SalesStatVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", salesStatService.getUserBuyStat(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "검색어 통계 목록 조회")
    @GetMapping("/search/getSearchKeywordList")
    public JSONObject getSearchKeywordList(@ModelAttribute SalesStatVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = salesStatService.getSearchKeywordCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", salesStatService.getSearchKeywordList(vo));
            json.put("totalCount", totalCount);
            json.put("srcSum", salesStatService.getSearchKeywordSum(vo));
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
