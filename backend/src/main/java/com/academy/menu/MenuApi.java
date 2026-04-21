package com.academy.menu;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.menu.service.MenuService;
import com.academy.menu.service.MenuVO;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Menu", description = "메뉴 관리 API")
@RestController
@RequestMapping("/api/menu")
public class MenuApi extends CORSFilter {

    private MenuService menuService;

    public MenuApi(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "/getMenuTree")
    public JSONObject getMenuTree(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("menuTree", menuService.getMenuTree(menuVO));
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @GetMapping(value = "/getpassMenuTree")
    public JSONObject getpassMenuTree(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("menuTree", menuService.getpassMenuTree(menuVO));
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @GetMapping(value = "/getDetailMenu")
    public JSONObject getDetailMenu(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("menu", menuService.getDetailMenu(menuVO));
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @GetMapping(value = "/getpassDetailMenu")
    public JSONObject getpassDetailMenu(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        jsonObject.put("menu", menuService.getpassDetailMenu(menuVO));
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @PutMapping(value = "/menuUpdateProcess")
    public JSONObject menuUpdateProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.menuUpdateProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @PutMapping(value = "/sgMenuUpdateProcess")
    public JSONObject sgMenuUpdateProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.sgMenuUpdateProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @PutMapping(value = "/passmenuUpdateProcess")
    public JSONObject passmenuUpdateProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.passmenuUpdateProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @DeleteMapping(value = "/menuDeleteProcess")
    public JSONObject menuDeleteProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.menuDeleteProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @DeleteMapping(value = "/passmenuDeleteProcess")
    public JSONObject passmenuDeleteProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.passmenuDeleteProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @GetMapping(value = "/menuIdCheck")
    public JSONObject menuIdCheck(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int count = menuService.menuIdCheck(menuVO);
        jsonObject.put("count", count);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @GetMapping(value = "/getMaxMenuId")
    public JSONObject getMaxMenuId(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        HashMap<String, Object> maxMenuId = menuService.getMaxMenuId(menuVO);
        jsonObject.put("maxMenuId", maxMenuId);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @GetMapping(value = "/getpassMaxMenuId")
    public JSONObject getpassMaxMenuId(@ModelAttribute MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        HashMap<String, Object> maxMenuId = menuService.getpassMaxMenuId(menuVO);
        jsonObject.put("maxMenuId", maxMenuId);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @PostMapping(value = "/menuInsertProcess")
    public JSONObject menuInsertProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.menuInsertProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

    @PostMapping(value = "/passmenuInsertProcess")
    public JSONObject passmenuInsertProcess(@RequestBody MenuVO menuVO) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        int result = menuService.passmenuInsertProcess(menuVO);
        jsonObject.put("result", result);
        JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
    }

}
