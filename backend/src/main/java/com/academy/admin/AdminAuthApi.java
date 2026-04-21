package com.academy.admin;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.admin.service.AdminAuthService;
import com.academy.admin.service.AdminSiteVO;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 권한 관리 API Controller
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
@Tag(name = "AdminAuth", description = "권한 관리 API")
@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthApi extends CORSFilter {

    private final AdminAuthService adminAuthService;

    public AdminAuthApi(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @Operation(summary = "권한 목록 조회", description = "페이징 처리된 권한 목록을 조회합니다.")
    @GetMapping(value = "/getAuthList")
    public JSONObject getAuthList(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getPageUnit());
        paginationInfo.setPageSize(vo.getPageSize());

        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("authList", adminAuthService.selectAuthList(vo));

        int totCnt = adminAuthService.selectAuthListCount(vo);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한 상세 조회", description = "권한 상세 정보를 조회합니다.")
    @GetMapping(value = "/getAuthDetail")
    public JSONObject getAuthDetail(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("authDetail", adminAuthService.selectAuthDetail(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한 등록", description = "새로운 권한을 등록합니다.")
    @PostMapping(value = "/insertAuth")
    public JSONObject insertAuth(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminAuthService.insertAuth(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한 수정", description = "권한 정보를 수정합니다.")
    @PostMapping(value = "/updateAuth")
    public JSONObject updateAuth(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminAuthService.updateAuth(vo);
            adminAuthService.updateAuthMenuSiteId(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한 삭제", description = "권한을 삭제합니다.")
    @PostMapping(value = "/deleteAuth")
    public JSONObject deleteAuth(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminAuthService.deleteAuth(vo);
            adminAuthService.deleteSiteIdAuthMenu(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한 일괄 삭제", description = "선택된 권한을 일괄 삭제합니다.")
    @PostMapping(value = "/deleteAuthBatch")
    public JSONObject deleteAuthBatch(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminAuthService.deleteAuthBatch(vo);
            adminAuthService.deleteSiteIdAuthMenuBatch(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 메뉴 목록 조회", description = "권한별 온라인 메뉴 목록을 조회합니다.")
    @GetMapping(value = "/getOnlineMenuList")
    public JSONObject getOnlineMenuList(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuList", adminAuthService.selectOnlineMenuList(vo));
        jsonObject.put("authMenuList", adminAuthService.selectAuthMenuList(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 메뉴 목록 조회", description = "권한별 오프라인 메뉴 목록을 조회합니다.")
    @GetMapping(value = "/getOfflineMenuList")
    public JSONObject getOfflineMenuList(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuList", adminAuthService.selectOfflineMenuList(vo));
        jsonObject.put("authMenuList", adminAuthService.selectAuthMenuList(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "모의고사 메뉴 목록 조회", description = "권한별 모의고사 메뉴 목록을 조회합니다.")
    @GetMapping(value = "/getTestMenuList")
    public JSONObject getTestMenuList(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuList", adminAuthService.selectTestMenuList(vo));
        jsonObject.put("authMenuList", adminAuthService.selectAuthMenuList(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한별 메뉴 등록", description = "권한에 메뉴를 등록합니다.")
    @PostMapping(value = "/insertAuthMenu")
    public JSONObject insertAuthMenu(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            // 기존 메뉴 삭제 후 등록
            adminAuthService.deleteAuthMenu(vo);
            adminAuthService.insertAuthMenu(vo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "권한별 등록된 메뉴 목록 조회", description = "권한에 등록된 메뉴 목록을 조회합니다.")
    @GetMapping(value = "/getAuthMenuList")
    public JSONObject getAuthMenuList(@ModelAttribute("AdminSiteVO") AdminSiteVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("authMenuList", adminAuthService.selectAuthMenuList(vo));
        return new JSONObject(jsonObject);
    }
}
