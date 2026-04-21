package com.academy.admin;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.admin.service.AdminCodeService;
import com.academy.admin.service.AdminCodeVO;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 공통코드 관리 API Controller
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            최초 생성
 * </pre>
 */
@Tag(name = "AdminCode", description = "공통코드 관리 API")
@RestController
@RequestMapping("/api/admin/code")
public class AdminCodeApi extends CORSFilter {

    private final AdminCodeService adminCodeService;

    public AdminCodeApi(AdminCodeService adminCodeService) {
        this.adminCodeService = adminCodeService;
    }

    @Operation(summary = "공통코드 목록 조회", description = "페이징 처리된 공통코드 목록을 조회합니다.")
    @GetMapping(value = "/getCodeList")
    public JSONObject getCodeList(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getPageUnit());
        paginationInfo.setPageSize(vo.getPageSize());

        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("codeList", adminCodeService.selectCodeList(vo));

        int totCnt = adminCodeService.selectCodeListCount(vo);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공통코드 상세 조회", description = "공통코드 상세 정보를 조회합니다.")
    @GetMapping(value = "/getCodeDetail")
    public JSONObject getCodeDetail(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("codeDetail", adminCodeService.selectCodeDetail(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공통코드 등록", description = "새로운 공통코드를 등록합니다.")
    @PostMapping(value = "/insertCode")
    public JSONObject insertCode(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminCodeService.insertCode(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공통코드 수정", description = "공통코드 정보를 수정합니다.")
    @PostMapping(value = "/updateCode")
    public JSONObject updateCode(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminCodeService.updateCode(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공통코드 삭제", description = "공통코드를 삭제합니다.")
    @PostMapping(value = "/deleteCode")
    public JSONObject deleteCode(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminCodeService.deleteCode(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "공통코드 일괄 삭제", description = "선택된 공통코드를 일괄 삭제합니다.")
    @PostMapping(value = "/deleteCodeBatch")
    public JSONObject deleteCodeBatch(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminCodeService.deleteCodeBatch(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "설정코드 트리 조회", description = "설정코드 트리 구조를 조회합니다.")
    @GetMapping(value = "/getPassCodeTree")
    public JSONObject getPassCodeTree() throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("codeList", adminCodeService.selectPassCodeTree());
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "설정코드 상세 조회", description = "설정코드 상세 정보를 조회합니다.")
    @GetMapping(value = "/getPassCodeDetail")
    public JSONObject getPassCodeDetail(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("codeDetail", adminCodeService.selectPassCodeDetail(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "설정코드 등록", description = "새로운 설정코드를 등록합니다.")
    @PostMapping(value = "/insertPassCode")
    public JSONObject insertPassCode(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = adminCodeService.insertPassCode(vo);
            jsonObject.put("isInsert", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "설정코드 수정", description = "설정코드 정보를 수정합니다.")
    @PostMapping(value = "/updatePassCode")
    public JSONObject updatePassCode(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminCodeService.updatePassCode(vo);
            jsonObject.put("isUpdate", "1");
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "설정코드 삭제", description = "설정코드를 삭제합니다.")
    @PostMapping(value = "/deletePassCode")
    public JSONObject deletePassCode(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = adminCodeService.deletePassCode(vo);
            jsonObject.put("isDelete", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "설정코드 MAX ID 조회", description = "설정코드의 다음 ID를 조회합니다.")
    @GetMapping(value = "/getPassMaxCodeId")
    public JSONObject getPassMaxCodeId(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("resultMap", adminCodeService.selectPassMaxCodeId(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "BA CONFIG 코드 목록 조회", description = "BA CONFIG 코드 목록을 조회합니다.")
    @GetMapping(value = "/getBaConfigCodeList")
    public JSONObject getBaConfigCodeList(@ModelAttribute("AdminCodeVO") AdminCodeVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("codeList", adminCodeService.selectBaConfigCodeList(vo));
        return new JSONObject(jsonObject);
    }
}
