package com.academy.gosi;

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
import com.academy.gosi.service.GosiService;
import com.academy.gosi.service.GosiVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 고시 관리 API
 */
@Tag(name = "Gosi", description = "고시 관리 API")
@RestController
@RequestMapping("/api/gosi")
public class GosiApi extends CORSFilter {

    private final GosiService gosiService;

    @Autowired
    public GosiApi(GosiService gosiService) {
        this.gosiService = gosiService;
    }

    // === 고시 마스터 ===
    @Operation(summary = "고시 목록 조회", description = "고시 마스터 목록을 조회합니다.")
    @GetMapping(value = "/getGosiList")
    public JSONObject getGosiList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<GosiVO> list = gosiService.getGosiList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 샘플 사용자 관리 ===
    @Operation(summary = "샘플 사용자 목록 조회", description = "샘플 사용자 목록을 조회합니다.")
    @GetMapping(value = "/getSampleUserList")
    public JSONObject getSampleUserList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = gosiService.getSampleUserListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<GosiVO> list = gosiService.getSampleUserList(vo);

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

    @Operation(summary = "샘플 사용자 상세 조회", description = "샘플 사용자 상세 정보를 조회합니다.")
    @GetMapping(value = "/getSampleUserDetail")
    public JSONObject getSampleUserDetail(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            GosiVO detail = gosiService.getSampleUserDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "샘플 사용자 등록", description = "샘플 사용자를 등록합니다.")
    @PostMapping(value = "/insertSampleUser")
    public JSONObject insertSampleUser(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = gosiService.insertSampleUser(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "샘플 사용자 수정", description = "샘플 사용자 정보를 수정합니다.")
    @PostMapping(value = "/updateSampleUser")
    public JSONObject updateSampleUser(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = gosiService.updateSampleUser(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "샘플 사용자 삭제", description = "샘플 사용자를 삭제합니다.")
    @PostMapping(value = "/deleteSampleUser")
    public JSONObject deleteSampleUser(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = gosiService.deleteSampleUser(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 고시 지역 마스터 ===
    @Operation(summary = "고시 지역 목록 조회", description = "고시 지역 마스터 목록을 조회합니다.")
    @GetMapping(value = "/getGosiAreaMstList")
    public JSONObject getGosiAreaMstList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<GosiVO> list = gosiService.getGosiAreaMstList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "고시 지역 상세 조회", description = "고시 지역 마스터 상세 정보를 조회합니다.")
    @GetMapping(value = "/getGosiAreaMstDetail")
    public JSONObject getGosiAreaMstDetail(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            GosiVO detail = gosiService.getGosiAreaMstDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "고시 지역 수정", description = "고시 지역 마스터 정보를 수정합니다.")
    @PostMapping(value = "/updateGosiAreaMst")
    public JSONObject updateGosiAreaMst(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = gosiService.updateGosiAreaMst(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === VOD 관리 ===
    @Operation(summary = "VOD 과목 목록 조회", description = "VOD 과목 목록을 조회합니다.")
    @GetMapping(value = "/getVodSubjectList")
    public JSONObject getVodSubjectList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<GosiVO> list = gosiService.getVodSubjectList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "VOD 상세 조회", description = "VOD 상세 정보를 조회합니다.")
    @GetMapping(value = "/getVodDetail")
    public JSONObject getVodDetail(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            GosiVO detail = gosiService.getVodDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "VOD 수정", description = "VOD 정보를 수정합니다.")
    @PostMapping(value = "/updateGosiVod")
    public JSONObject updateGosiVod(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = gosiService.updateGosiVod(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 고시 통계 ===
    @Operation(summary = "고시 통계 목록 조회", description = "고시 통계 목록을 조회합니다.")
    @GetMapping(value = "/getGosiStatMstList")
    public JSONObject getGosiStatMstList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<GosiVO> list = gosiService.getGosiStatMstList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "고시 결과 생성", description = "고시 결과를 생성합니다.")
    @PostMapping(value = "/makeGosiResult")
    public JSONObject makeGosiResult(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            gosiService.makeGosiResult(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "고시 표준 생성", description = "고시 표준을 생성합니다.")
    @PostMapping(value = "/makeGosiStandard")
    public JSONObject makeGosiStandard(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            gosiService.makeGosiStandard(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "고시 통계 마스터 생성", description = "고시 통계 마스터를 생성합니다.")
    @PostMapping(value = "/makeGosiStatMst")
    public JSONObject makeGosiStatMst(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            gosiService.makeGosiStatMst(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "고시 조정 마스터 생성", description = "고시 조정 마스터를 생성합니다.")
    @PostMapping(value = "/makeGosiAdjustMst")
    public JSONObject makeGosiAdjustMst(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            gosiService.makeGosiAdjustMst(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 고시 과목 ===
    @Operation(summary = "고시 과목 목록 조회", description = "고시 과목 목록을 조회합니다.")
    @GetMapping(value = "/getGosiSubjectList")
    public JSONObject getGosiSubjectList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<GosiVO> list = gosiService.getGosiSubjectList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 이벤트 결과 ===
    @Operation(summary = "이벤트 결과 목록 조회", description = "이벤트 결과 목록을 조회합니다.")
    @GetMapping(value = "/getEventResultList")
    public JSONObject getEventResultList(@ModelAttribute GosiVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = gosiService.getEventResultListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<GosiVO> list = gosiService.getEventResultList(vo);

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
}
