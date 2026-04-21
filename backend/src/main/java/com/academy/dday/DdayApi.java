package com.academy.dday;

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
import com.academy.dday.service.DdayService;
import com.academy.dday.service.DdayVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * D-Day 관리 API Controller
 * @author system
 * @since 2025.12.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.11         system          신규 생성
 * </pre>
 */
@Tag(name = "D-Day 관리", description = "D-Day 관리 API")
@RestController
@RequestMapping("/api/dday")
public class DdayApi extends CORSFilter {

    private final DdayService ddayService;

    @Autowired
    public DdayApi(DdayService ddayService) {
        this.ddayService = ddayService;
    }

    /**
     * 카테고리 목록 조회
     * @param ddayVO 검색조건
     * @return JSONObject 카테고리 목록
     */
    @Operation(summary = "카테고리 목록 조회", description = "D-Day 카테고리 목록을 조회한다.")
    @GetMapping(value = "/getCategoryList")
    public JSONObject getCategoryList(@ModelAttribute DdayVO ddayVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            List<JSONObject> categoryList = ddayService.selectCategoryList(ddayVO);

            jsonObject.put("categoryList", categoryList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * D-Day 목록 조회
     * @param ddayVO 검색조건
     * @return JSONObject D-Day 목록
     */
    @Operation(summary = "D-Day 목록 조회", description = "D-Day 목록을 조회한다.")
    @GetMapping(value = "/getDdayList")
    public JSONObject getDdayList(@ModelAttribute DdayVO ddayVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            // 페이징 처리
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(ddayVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(ddayVO.getPageUnit());
            paginationInfo.setPageSize(ddayVO.getPageSize());

            ddayVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            ddayVO.setLastIndex(paginationInfo.getLastRecordIndex());
            ddayVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            // 목록 조회
            List<JSONObject> list = ddayService.selectDdayList(ddayVO);
            int totalCount = ddayService.selectDdayListCount(ddayVO);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
            jsonObject.put("currentPage", ddayVO.getPageIndex());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * D-Day 상세 조회
     * @param ddayVO 검색조건
     * @return JSONObject D-Day 상세 정보
     */
    @Operation(summary = "D-Day 상세 조회", description = "D-Day 상세 정보를 조회한다.")
    @GetMapping(value = "/getDdayDetail")
    public JSONObject getDdayDetail(@ModelAttribute DdayVO ddayVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            JSONObject detail = ddayService.selectDdayDetail(ddayVO);
            List<JSONObject> categoryList = ddayService.selectCategoryList(ddayVO);

            jsonObject.put("detail", detail);
            jsonObject.put("categoryList", categoryList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * D-Day 등록
     * @param ddayVO D-Day 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "D-Day 등록", description = "D-Day를 등록한다.")
    @PostMapping(value = "/insertDday")
    public JSONObject insertDday(@ModelAttribute DdayVO ddayVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            ddayService.insertDday(ddayVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * D-Day 수정
     * @param ddayVO D-Day 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "D-Day 수정", description = "D-Day를 수정한다.")
    @PostMapping(value = "/updateDday")
    public JSONObject updateDday(@ModelAttribute DdayVO ddayVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            ddayService.updateDday(ddayVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * D-Day 삭제
     * @param ddayVO D-Day 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "D-Day 삭제", description = "D-Day를 삭제한다.")
    @PostMapping(value = "/deleteDday")
    public JSONObject deleteDday(@ModelAttribute DdayVO ddayVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            ddayService.deleteDday(ddayVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
