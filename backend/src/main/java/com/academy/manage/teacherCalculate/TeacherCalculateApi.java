package com.academy.manage.teacherCalculate;

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
import com.academy.manage.teacherCalculate.service.TeacherCalculateService;
import com.academy.manage.teacherCalculate.service.TeacherCalculateVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 강사 정산 관리 API
 */
@Tag(name = "TeacherCalculate", description = "강사 정산 관리 API")
@RestController
@RequestMapping("/api/manage/teacherCalculate")
public class TeacherCalculateApi extends CORSFilter {

    private final TeacherCalculateService teacherCalculateService;

    @Autowired
    public TeacherCalculateApi(TeacherCalculateService teacherCalculateService) {
        this.teacherCalculateService = teacherCalculateService;
    }

    @Operation(summary = "강사 정산 목록 조회", description = "강사 정산 목록을 조회합니다.")
    @GetMapping(value = "/getTeacherCalculateList")
    public JSONObject getTeacherCalculateList(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = teacherCalculateService.getTeacherCalculateListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<TeacherCalculateVO> list = teacherCalculateService.getTeacherCalculateList(vo);

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

    @Operation(summary = "강사 정산 상세 조회", description = "강사 정산 상세 정보를 조회합니다.")
    @GetMapping(value = "/getTeacherCalculateDetail")
    public JSONObject getTeacherCalculateDetail(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            TeacherCalculateVO detail = teacherCalculateService.getTeacherCalculateDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사별 정산 요약 조회", description = "강사별 정산 요약을 조회합니다.")
    @GetMapping(value = "/getTeacherCalculateSummaryList")
    public JSONObject getTeacherCalculateSummaryList(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<TeacherCalculateVO> list = teacherCalculateService.getTeacherCalculateSummaryList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "월별 정산 목록 조회", description = "월별 정산 목록을 조회합니다.")
    @GetMapping(value = "/getMonthlyCalculateList")
    public JSONObject getMonthlyCalculateList(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<TeacherCalculateVO> list = teacherCalculateService.getMonthlyCalculateList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사별 강의 매출 목록 조회", description = "강사별 강의 매출 목록을 조회합니다.")
    @GetMapping(value = "/getTeacherLectureSaleList")
    public JSONObject getTeacherLectureSaleList(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<TeacherCalculateVO> list = teacherCalculateService.getTeacherLectureSaleList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "정산 통계 조회", description = "정산 통계를 조회합니다.")
    @GetMapping(value = "/getCalculateStats")
    public JSONObject getCalculateStats(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            TeacherCalculateVO stats = teacherCalculateService.getCalculateStats(vo);
            jsonObject.put("data", stats);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사 목록 조회", description = "강사 목록을 조회합니다.")
    @GetMapping(value = "/getTeacherList")
    public JSONObject getTeacherList(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<TeacherCalculateVO> list = teacherCalculateService.getTeacherList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사 정산 등록", description = "강사 정산을 등록합니다.")
    @PostMapping(value = "/insertTeacherCalculate")
    public JSONObject insertTeacherCalculate(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = teacherCalculateService.insertTeacherCalculate(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사 정산 수정", description = "강사 정산을 수정합니다.")
    @PostMapping(value = "/updateTeacherCalculate")
    public JSONObject updateTeacherCalculate(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = teacherCalculateService.updateTeacherCalculate(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사 정산 삭제", description = "강사 정산을 삭제합니다.")
    @PostMapping(value = "/deleteTeacherCalculate")
    public JSONObject deleteTeacherCalculate(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = teacherCalculateService.deleteTeacherCalculate(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "정산 상태 변경", description = "정산 상태를 변경합니다.")
    @PostMapping(value = "/updateCalculateStatus")
    public JSONObject updateCalculateStatus(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = teacherCalculateService.updateCalculateStatus(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "지급 처리", description = "강사 정산 지급을 처리합니다.")
    @PostMapping(value = "/updatePayment")
    public JSONObject updatePayment(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = teacherCalculateService.updatePayment(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "월별 정산 일괄 생성", description = "월별 정산을 일괄 생성합니다.")
    @PostMapping(value = "/insertMonthlyCalculateBatch")
    public JSONObject insertMonthlyCalculateBatch(@ModelAttribute TeacherCalculateVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = teacherCalculateService.insertMonthlyCalculateBatch(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
