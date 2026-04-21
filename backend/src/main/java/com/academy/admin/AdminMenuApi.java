package com.academy.admin;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.admin.service.AdminMenuService;
import com.academy.admin.service.AdminMenuVO;
import com.academy.common.CORSFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 관리자 메뉴 관리 API Controller
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
@Tag(name = "AdminMenu", description = "관리자 메뉴 관리 API")
@RestController
@RequestMapping("/api/admin/menu")
public class AdminMenuApi extends CORSFilter {

    private final AdminMenuService adminMenuService;

    public AdminMenuApi(AdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

    @Operation(summary = "관리자 메뉴 트리 조회", description = "관리자 메뉴 트리 구조를 조회합니다.")
    @GetMapping(value = "/getMenuTree")
    public JSONObject getMenuTree() throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuList", adminMenuService.selectMenuTree());
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 메뉴 트리 조회", description = "사용자 메뉴 트리 구조를 조회합니다.")
    @GetMapping(value = "/getPassMenuTree")
    public JSONObject getPassMenuTree() throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuList", adminMenuService.selectPassMenuTree());
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "관리자 메뉴 상세 조회", description = "관리자 메뉴 상세 정보를 조회합니다.")
    @GetMapping(value = "/getMenuDetail")
    public JSONObject getMenuDetail(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuDetail", adminMenuService.selectMenuDetail(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 메뉴 상세 조회", description = "사용자 메뉴 상세 정보를 조회합니다.")
    @GetMapping(value = "/getPassMenuDetail")
    public JSONObject getPassMenuDetail(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("menuDetail", adminMenuService.selectPassMenuDetail(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "관리자 메뉴 등록", description = "새로운 관리자 메뉴를 등록합니다.")
    @PostMapping(value = "/insertMenu")
    public JSONObject insertMenu(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = adminMenuService.insertMenu(vo);
            jsonObject.put("isInsert", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 메뉴 등록", description = "새로운 사용자 메뉴를 등록합니다.")
    @PostMapping(value = "/insertPassMenu")
    public JSONObject insertPassMenu(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = adminMenuService.insertPassMenu(vo);
            jsonObject.put("isInsert", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "관리자 메뉴 수정", description = "관리자 메뉴 정보를 수정합니다.")
    @PostMapping(value = "/updateMenu")
    public JSONObject updateMenu(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminMenuService.updateMenu(vo);
            jsonObject.put("isUpdate", "1");
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 메뉴 수정", description = "사용자 메뉴 정보를 수정합니다.")
    @PostMapping(value = "/updatePassMenu")
    public JSONObject updatePassMenu(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            adminMenuService.updatePassMenu(vo);
            jsonObject.put("isUpdate", "1");
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "관리자 메뉴 삭제", description = "관리자 메뉴를 삭제합니다.")
    @PostMapping(value = "/deleteMenu")
    public JSONObject deleteMenu(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = adminMenuService.deleteMenu(vo);
            jsonObject.put("isDelete", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 메뉴 삭제", description = "사용자 메뉴를 삭제합니다.")
    @PostMapping(value = "/deletePassMenu")
    public JSONObject deletePassMenu(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = adminMenuService.deletePassMenu(vo);
            jsonObject.put("isDelete", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "메뉴 ID 중복 체크", description = "메뉴 ID 중복 여부를 확인합니다.")
    @GetMapping(value = "/checkMenuIdDuplicate")
    public JSONObject checkMenuIdDuplicate(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        int count = adminMenuService.checkMenuIdDuplicate(vo);
        jsonObject.put("idCount", count);
        if (count > 0) {
            jsonObject.put("message", "중복된 메뉴ID입니다. 다른 메뉴ID를 사용하여 등록해주십시요");
        } else {
            jsonObject.put("message", "등록가능한 메뉴ID입니다");
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "관리자 메뉴 MAX ID 조회", description = "관리자 메뉴의 다음 ID를 조회합니다.")
    @GetMapping(value = "/getMaxMenuId")
    public JSONObject getMaxMenuId(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("resultMap", adminMenuService.selectMaxMenuId(vo));
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 메뉴 MAX ID 조회", description = "사용자 메뉴의 다음 ID를 조회합니다.")
    @GetMapping(value = "/getPassMaxMenuId")
    public JSONObject getPassMaxMenuId(@ModelAttribute("AdminMenuVO") AdminMenuVO vo) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("resultMap", adminMenuService.selectPassMaxMenuId(vo));
        return new JSONObject(jsonObject);
    }
}
