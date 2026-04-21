package com.academy.mocktest.stats.person;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.stats.person.service.PersonStatsService;
import com.academy.mocktest.stats.person.service.PersonStatsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PersonStats", description = "개인별 성적 통계 API")
@RestController
@RequestMapping("/api/mocktest/stats/person")
public class PersonStatsApi extends CORSFilter {

    private final PersonStatsService personStatsService;

    @Autowired
    public PersonStatsApi(PersonStatsService personStatsService) {
        this.personStatsService = personStatsService;
    }

    @Operation(summary = "개인별 성적 목록 조회")
    @GetMapping("/getPersonStatsList")
    public JSONObject getPersonStatsList(@ModelAttribute PersonStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = personStatsService.getPersonStatsListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", personStatsService.getPersonStatsList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "개인 성적 상세 조회")
    @GetMapping("/getPersonStatsDetail")
    public JSONObject getPersonStatsDetail(@ModelAttribute PersonStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", personStatsService.getPersonStatsDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "개인 과목별 성적 조회")
    @GetMapping("/getPersonSubjectStats")
    public JSONObject getPersonSubjectStats(@ModelAttribute PersonStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", personStatsService.getPersonSubjectStats(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "개인 전체 성적 통계 조회")
    @GetMapping("/getPersonTotalStats")
    public JSONObject getPersonTotalStats(@ModelAttribute PersonStatsVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", personStatsService.getPersonTotalStats(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
