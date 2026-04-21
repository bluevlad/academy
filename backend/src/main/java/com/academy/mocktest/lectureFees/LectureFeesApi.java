package com.academy.mocktest.lectureFees;

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
import com.academy.mocktest.lectureFees.service.LectureFeesService;
import com.academy.mocktest.lectureFees.service.LectureFeesVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 모의고사 수강료 관리 API
 */
@Tag(name = "LectureFees", description = "모의고사 수강료 관리 API")
@RestController
@RequestMapping("/api/mocktest/lectureFees")
public class LectureFeesApi extends CORSFilter {

    private final LectureFeesService lectureFeesService;

    @Autowired
    public LectureFeesApi(LectureFeesService lectureFeesService) {
        this.lectureFeesService = lectureFeesService;
    }

    @Operation(summary = "수강료 목록 조회", description = "모의고사 수강료 목록을 조회합니다.")
    @GetMapping(value = "/getLectureFeesList")
    public JSONObject getLectureFeesList(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = lectureFeesService.getLectureFeesListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<LectureFeesVO> list = lectureFeesService.getLectureFeesList(vo);

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

    @Operation(summary = "수강료 상세 조회", description = "수강료 상세 정보를 조회합니다.")
    @GetMapping(value = "/getLectureFeesDetail")
    public JSONObject getLectureFeesDetail(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            LectureFeesVO detail = lectureFeesService.getLectureFeesDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "수강료 등록", description = "수강료를 등록합니다.")
    @PostMapping(value = "/insertLectureFees")
    public JSONObject insertLectureFees(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureFeesService.insertLectureFees(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "수강료 수정", description = "수강료를 수정합니다.")
    @PostMapping(value = "/updateLectureFees")
    public JSONObject updateLectureFees(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureFeesService.updateLectureFees(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "수강료 삭제", description = "수강료를 삭제합니다.")
    @PostMapping(value = "/deleteLectureFees")
    public JSONObject deleteLectureFees(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureFeesService.deleteLectureFees(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "패키지 수강료 목록", description = "패키지 수강료 목록을 조회합니다.")
    @GetMapping(value = "/getPackageFeesList")
    public JSONObject getPackageFeesList(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureFeesVO> list = lectureFeesService.getPackageFeesList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "시험년도 목록 조회", description = "시험년도 목록을 조회합니다.")
    @GetMapping(value = "/getExamYearList")
    public JSONObject getExamYearList(@ModelAttribute LectureFeesVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureFeesVO> list = lectureFeesService.getExamYearList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
