package com.academy.manage.categorySale;

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
import com.academy.manage.categorySale.service.CategorySaleService;
import com.academy.manage.categorySale.service.CategorySaleVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 카테고리별 매출 관리 API
 */
@Tag(name = "CategorySale", description = "카테고리별 매출 관리 API")
@RestController
@RequestMapping("/api/manage/categorySale")
public class CategorySaleApi extends CORSFilter {

    private final CategorySaleService categorySaleService;

    @Autowired
    public CategorySaleApi(CategorySaleService categorySaleService) {
        this.categorySaleService = categorySaleService;
    }

    @Operation(summary = "카테고리별 매출 목록 조회", description = "카테고리별 매출 목록을 조회합니다.")
    @GetMapping(value = "/getCategorySaleList")
    public JSONObject getCategorySaleList(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = categorySaleService.getCategorySaleListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<CategorySaleVO> list = categorySaleService.getCategorySaleList(vo);

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

    @Operation(summary = "카테고리별 매출 상세 조회", description = "카테고리별 매출 상세 정보를 조회합니다.")
    @GetMapping(value = "/getCategorySaleDetail")
    public JSONObject getCategorySaleDetail(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            CategorySaleVO detail = categorySaleService.getCategorySaleDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "카테고리별 매출 통계 조회", description = "카테고리별 매출 통계를 조회합니다.")
    @GetMapping(value = "/getCategorySaleStats")
    public JSONObject getCategorySaleStats(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            CategorySaleVO stats = categorySaleService.getCategorySaleStats(vo);
            jsonObject.put("data", stats);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "일별 매출 목록 조회", description = "일별 매출 목록을 조회합니다.")
    @GetMapping(value = "/getDailySaleList")
    public JSONObject getDailySaleList(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<CategorySaleVO> list = categorySaleService.getDailySaleList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "월별 매출 목록 조회", description = "월별 매출 목록을 조회합니다.")
    @GetMapping(value = "/getMonthlySaleList")
    public JSONObject getMonthlySaleList(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<CategorySaleVO> list = categorySaleService.getMonthlySaleList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "년별 매출 목록 조회", description = "년별 매출 목록을 조회합니다.")
    @GetMapping(value = "/getYearlySaleList")
    public JSONObject getYearlySaleList(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<CategorySaleVO> list = categorySaleService.getYearlySaleList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "카테고리 목록 조회", description = "카테고리 목록을 조회합니다.")
    @GetMapping(value = "/getCategoryList")
    public JSONObject getCategoryList(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<CategorySaleVO> list = categorySaleService.getCategoryList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "카테고리별 매출 비율 조회", description = "카테고리별 매출 비율을 조회합니다.")
    @GetMapping(value = "/getCategorySaleRateList")
    public JSONObject getCategorySaleRateList(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<CategorySaleVO> list = categorySaleService.getCategorySaleRateList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "매출 데이터 등록", description = "매출 데이터를 등록합니다.")
    @PostMapping(value = "/insertCategorySale")
    public JSONObject insertCategorySale(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = categorySaleService.insertCategorySale(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "매출 데이터 수정", description = "매출 데이터를 수정합니다.")
    @PostMapping(value = "/updateCategorySale")
    public JSONObject updateCategorySale(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = categorySaleService.updateCategorySale(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "매출 데이터 삭제", description = "매출 데이터를 삭제합니다.")
    @PostMapping(value = "/deleteCategorySale")
    public JSONObject deleteCategorySale(@ModelAttribute CategorySaleVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = categorySaleService.deleteCategorySale(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
