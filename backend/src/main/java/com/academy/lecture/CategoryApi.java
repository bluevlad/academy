package com.academy.lecture;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.lecture.service.CategoryService;
import com.academy.lecture.service.CategoryVO;

/**
 * @FileName   : CategoryApi.java
 * @Project    :
 * @Date       : 2025. 11.
 * @Author     : miraenet
 * @변경이력    :
 * @프로그램 설명 : 카테고리 관리 API
 */
@RestController
@RequestMapping("/api/category")
public class CategoryApi extends CORSFilter {

    private CategoryService categoryService;

    @Autowired
    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @Method Name  : getSeriesCateTree
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 트리 조회
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/tree")
    public JSONObject getSeriesCateTree(@ModelAttribute("CategoryVO") CategoryVO categoryVO) throws Exception {
        ArrayList<JSONObject> menuList = categoryService.getSeriesCateTree();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("menuList", menuList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : getDetail
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 상세 조회
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/detail")
    public JSONObject getDetail(@ModelAttribute("CategoryVO") CategoryVO categoryVO) throws Exception {

        JSONObject detail = categoryService.getDetail(categoryVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("detail", detail);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : getMaxOrdr
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 최대 순서 조회
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/maxOrdr")
    public JSONObject getMaxOrdr(@ModelAttribute("CategoryVO") CategoryVO categoryVO) throws Exception {

        JSONObject maxMenuMap = categoryService.getMaxOrdr(categoryVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        if(maxMenuMap == null){
            Integer ordr = categoryVO.getOrdr();
            String ORDR = ordr != null ? ordr.toString() : "0";
            if(ORDR.equals("0")){ //Root
                result.put("ORDR", 1);
            }else{
                result.put("ORDR", (Integer.parseInt(ORDR) + 1)+"");
            }
        } else{
            result.put("ORDR", maxMenuMap.get("ORDR"));
            result.put("P_NAME", maxMenuMap.get("P_NAME"));
        }
        String CODE = CommonUtil.isNull(categoryVO.getCode(), "");
        if(CODE!=null && CODE!=""){
            result.put("P_CODE", CODE);
        }

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : deleteProcess
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 삭제
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/delete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject deleteProcess(@RequestBody CategoryVO categoryVO) throws Exception {

        int isDelete = categoryService.deleteProcess(categoryVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("isDelete", isDelete);
        result.put("VIEWCODE", categoryVO.getCode());

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : idCheck
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : ID 중복 확인
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/idCheck")
    public JSONObject idCheck(@ModelAttribute("CategoryVO") CategoryVO categoryVO) throws Exception {

        int idCount = categoryService.idCheck(categoryVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("idCount", idCount);
        if(idCount  > 0){
            result.put("message", "중복된 메뉴ID입니다. 다른 메뉴ID를 사용하여 등록해주십시요");
        }else{
            result.put("message", "등록가능한 메뉴ID입니다");
        }

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }


    /**
     * @Method Name  : insertProcess
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 등록
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insert")
    public JSONObject insertProcess(@RequestBody CategoryVO categoryVO) throws Exception {

        int isInsert = categoryService.insertProcess(categoryVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("isInsert", isInsert);
        result.put("VIEWCODE", categoryVO.getCode());

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : updateProcess
     * @Date         : 2025. 11.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 수정
     * @param CategoryVO
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/update")
    public JSONObject updateProcess(@RequestBody CategoryVO categoryVO) throws Exception {

        categoryService.updateProcess(categoryVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("isUpdate", 1);
        result.put("VIEWCODE", categoryVO.getCode());

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }
}