package com.academy.popup;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.popup.service.PopupService;
import com.academy.popup.service.PopupVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Popup", description = "팝업 관리 API")
@RestController
@RequestMapping("/api/popup")
public class PopupApi extends CORSFilter {

    private final PopupService popupService;

    @Autowired
    public PopupApi(PopupService popupService) {
        this.popupService = popupService;
    }

    @Operation(summary = "팝업 목록 조회")
    @GetMapping("/getPopupList")
    public JSONObject getPopupList(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = popupService.getPopupListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", popupService.getPopupList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "팝업 상세 조회")
    @GetMapping("/getPopupDetail")
    public JSONObject getPopupDetail(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", popupService.getPopupDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "팝업 등록")
    @PostMapping("/insertPopup")
    public JSONObject insertPopup(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", popupService.insertPopup(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "팝업 수정")
    @PostMapping("/updatePopup")
    public JSONObject updatePopup(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", popupService.updatePopup(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "팝업 삭제")
    @PostMapping("/deletePopup")
    public JSONObject deletePopup(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", popupService.deletePopup(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "팝업 공개여부 변경")
    @PostMapping("/updatePopupOpenYn")
    public JSONObject updatePopupOpenYn(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", popupService.updatePopupOpenYn(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "팝업 조회수 증가")
    @PostMapping("/updatePopupHit")
    public JSONObject updatePopupHit(@ModelAttribute PopupVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", popupService.updatePopupHit(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
