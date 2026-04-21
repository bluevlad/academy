package com.academy.mocktest.stats.total;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.stats.total.service.TotalStatsService;
import com.academy.mocktest.stats.total.service.TotalStatsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TotalStats", description = "전체 성적 통계 API")
@RestController
@RequestMapping("/api/mocktest/stats/total")
public class TotalStatsApi extends CORSFilter {

    private final TotalStatsService totalStatsService;

    @Autowired
    public TotalStatsApi(TotalStatsService totalStatsService) {
        this.totalStatsService = totalStatsService;
    }

    @Operation(summary = "전체 성적 통계 목록 조회")
    @GetMapping("/getTotalStatsList")
    public JSONObject getTotalStatsList(@ModelAttribute TotalStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = totalStatsService.getTotalStatsListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", totalStatsService.getTotalStatsList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "전체 성적 통계 상세 조회")
    @GetMapping("/getTotalStatsDetail")
    public JSONObject getTotalStatsDetail(@ModelAttribute TotalStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", totalStatsService.getTotalStatsDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "과목별 전체 통계 조회")
    @GetMapping("/getTotalSubjectStats")
    public JSONObject getTotalSubjectStats(@ModelAttribute TotalStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", totalStatsService.getTotalSubjectStats(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "응시자 현황 조회")
    @GetMapping("/getOffererCnt")
    public JSONObject getOffererCnt(@ModelAttribute TotalStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", totalStatsService.getOffererCnt(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
