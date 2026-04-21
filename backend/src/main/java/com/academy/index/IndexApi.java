package com.academy.index;

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
import com.academy.index.service.IndexService;
import com.academy.index.service.IndexVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 인덱스/메뉴 관리 API
 */
@Tag(name = "Index", description = "인덱스/메뉴 관리 API")
@RestController
@RequestMapping("/api/index")
public class IndexApi extends CORSFilter {

    private final IndexService indexService;

    @Autowired
    public IndexApi(IndexService indexService) {
        this.indexService = indexService;
    }

    // === 메뉴 조회 ===
    @Operation(summary = "TOP 메뉴 목록 조회", description = "사용자 권한에 따른 TOP 메뉴 목록을 조회합니다.")
    @GetMapping(value = "/getTopMenuList")
    public JSONObject getTopMenuList(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<IndexVO> list = indexService.getTopMenuList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "LEFT 메뉴 목록 조회", description = "TOP 메뉴에 따른 LEFT 메뉴 목록을 조회합니다.")
    @GetMapping(value = "/getLeftMenuList")
    public JSONObject getLeftMenuList(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<IndexVO> list = indexService.getLeftMenuList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "LEFT 메뉴 트리 조회", description = "TOP 메뉴에 따른 LEFT 메뉴 트리를 조회합니다.")
    @GetMapping(value = "/getLeftMenuTree")
    public JSONObject getLeftMenuTree(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<IndexVO> list = indexService.getLeftMenuTree(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 메뉴 마스터 관리 ===
    @Operation(summary = "메뉴 목록 조회", description = "메뉴 마스터 목록을 조회합니다.")
    @GetMapping(value = "/getMenuList")
    public JSONObject getMenuList(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = indexService.getMenuListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<IndexVO> list = indexService.getMenuList(vo);

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

    @Operation(summary = "메뉴 상세 조회", description = "메뉴 상세 정보를 조회합니다.")
    @GetMapping(value = "/getMenuDetail")
    public JSONObject getMenuDetail(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            IndexVO detail = indexService.getMenuDetail(vo);
            jsonObject.put("data", detail);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "메뉴 등록", description = "메뉴를 등록합니다.")
    @PostMapping(value = "/insertMenu")
    public JSONObject insertMenu(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = indexService.insertMenu(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "메뉴 수정", description = "메뉴 정보를 수정합니다.")
    @PostMapping(value = "/updateMenu")
    public JSONObject updateMenu(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = indexService.updateMenu(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "메뉴 삭제", description = "메뉴를 삭제합니다.")
    @PostMapping(value = "/deleteMenu")
    public JSONObject deleteMenu(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = indexService.deleteMenu(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // === 사이트 메뉴 권한 관리 ===
    @Operation(summary = "사이트 메뉴 권한 목록 조회", description = "사이트별 메뉴 권한 목록을 조회합니다.")
    @GetMapping(value = "/getSiteMenuList")
    public JSONObject getSiteMenuList(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<IndexVO> list = indexService.getSiteMenuList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사이트 메뉴 권한 등록", description = "사이트에 메뉴 권한을 등록합니다.")
    @PostMapping(value = "/insertSiteMenu")
    public JSONObject insertSiteMenu(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = indexService.insertSiteMenu(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사이트 메뉴 권한 삭제", description = "사이트의 메뉴 권한을 삭제합니다.")
    @PostMapping(value = "/deleteSiteMenu")
    public JSONObject deleteSiteMenu(@ModelAttribute IndexVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = indexService.deleteSiteMenu(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
