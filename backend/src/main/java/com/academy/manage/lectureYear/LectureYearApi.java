package com.academy.manage.lectureYear;

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
import com.academy.manage.lectureYear.service.LectureYearService;
import com.academy.manage.lectureYear.service.LectureYearVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 강의 연도별 관리 API
 */
@Tag(name = "LectureYear", description = "강의 연도별 관리 API")
@RestController
@RequestMapping("/api/manage/lectureYear")
public class LectureYearApi extends CORSFilter {

    private final LectureYearService lectureYearService;

    @Autowired
    public LectureYearApi(LectureYearService lectureYearService) {
        this.lectureYearService = lectureYearService;
    }

    @Operation(summary = "연도별 강의 목록 조회", description = "연도별 강의 목록을 조회합니다.")
    @GetMapping(value = "/getLectureYearList")
    public JSONObject getLectureYearList(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = lectureYearService.getLectureYearListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<LectureYearVO> list = lectureYearService.getLectureYearList(vo);

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

    @Operation(summary = "연도별 강의 상세 조회", description = "연도별 강의 상세 정보를 조회합니다.")
    @GetMapping(value = "/getLectureYearDetail")
    public JSONObject getLectureYearDetail(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            LectureYearVO detail = lectureYearService.getLectureYearDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도 목록 조회", description = "연도 목록을 조회합니다.")
    @GetMapping(value = "/getYearList")
    public JSONObject getYearList(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureYearVO> list = lectureYearService.getYearList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도별 강의 통계 조회", description = "연도별 강의 통계를 조회합니다.")
    @GetMapping(value = "/getLectureYearStats")
    public JSONObject getLectureYearStats(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            LectureYearVO stats = lectureYearService.getLectureYearStats(vo);
            jsonObject.put("data", stats);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도별 카테고리 강의 통계 조회", description = "연도별 카테고리 강의 통계를 조회합니다.")
    @GetMapping(value = "/getCategoryYearStatsList")
    public JSONObject getCategoryYearStatsList(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureYearVO> list = lectureYearService.getCategoryYearStatsList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도별 강사 강의 통계 조회", description = "연도별 강사 강의 통계를 조회합니다.")
    @GetMapping(value = "/getTeacherYearStatsList")
    public JSONObject getTeacherYearStatsList(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureYearVO> list = lectureYearService.getTeacherYearStatsList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도별 매출 통계 조회", description = "연도별 매출 통계를 조회합니다.")
    @GetMapping(value = "/getYearlySaleStatsList")
    public JSONObject getYearlySaleStatsList(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureYearVO> list = lectureYearService.getYearlySaleStatsList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도 정보 등록", description = "연도 정보를 등록합니다.")
    @PostMapping(value = "/insertLectureYear")
    public JSONObject insertLectureYear(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureYearService.insertLectureYear(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도 정보 수정", description = "연도 정보를 수정합니다.")
    @PostMapping(value = "/updateLectureYear")
    public JSONObject updateLectureYear(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureYearService.updateLectureYear(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "연도 정보 삭제", description = "연도 정보를 삭제합니다.")
    @PostMapping(value = "/deleteLectureYear")
    public JSONObject deleteLectureYear(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureYearService.deleteLectureYear(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의-연도 매핑 등록", description = "강의-연도 매핑을 등록합니다.")
    @PostMapping(value = "/insertLectureYearMapping")
    public JSONObject insertLectureYearMapping(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureYearService.insertLectureYearMapping(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의-연도 매핑 삭제", description = "강의-연도 매핑을 삭제합니다.")
    @PostMapping(value = "/deleteLectureYearMapping")
    public JSONObject deleteLectureYearMapping(@ModelAttribute LectureYearVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureYearService.deleteLectureYearMapping(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
