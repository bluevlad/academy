package com.academy.box;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.box.service.BoxNumVO;
import com.academy.box.service.BoxService;
import com.academy.box.service.BoxVO;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 사물함 관리 API Controller
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            사물함 관리 신규 생성
 * </pre>
 */
@Tag(name = "Box", description = "사물함 관리 API")
@RestController
@RequestMapping("/api/box")
public class BoxApi extends CORSFilter {

    private final BoxService boxService;

    public BoxApi(BoxService boxService) {
        this.boxService = boxService;
    }

    // =====================================================
    // 사물함 마스터 (TB_OFF_BOX) API
    // =====================================================

    /**
     * 사물함 목록 조회
     */
    @Operation(summary = "사물함 목록 조회", description = "페이징 처리된 사물함 목록을 조회합니다.")
    @GetMapping(value = "/getBoxList")
    public JSONObject getBoxList(@ModelAttribute("BoxVO") BoxVO boxVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boxVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boxVO.getPageUnit());
        paginationInfo.setPageSize(boxVO.getPageSize());

        boxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boxVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("boxList", boxService.selectBoxList(boxVO));

        int totCnt = boxService.selectBoxListCount(boxVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 상세 조회
     */
    @Operation(summary = "사물함 상세 조회", description = "사물함 상세 정보를 조회합니다.")
    @GetMapping(value = "/getBoxDetail")
    public JSONObject getBoxDetail(@ModelAttribute("BoxVO") BoxVO boxVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boxDetail", boxService.selectBoxDetail(boxVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 등록
     */
    @Operation(summary = "사물함 등록", description = "새로운 사물함을 등록합니다.")
    @PostMapping(value = "/insertBox")
    public JSONObject insertBox(@ModelAttribute("BoxVO") BoxVO boxVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boxService.insertBox(boxVO);

            // 사물함 세부정보를 개수만큼 생성
            int boxCount = boxVO.getBoxCount();
            int startNum = boxVO.getStartNum();
            int endNum = boxVO.getEndNum();

            if (boxCount > 0) {
                for (int i = startNum; i <= endNum; i++) {
                    BoxNumVO numVO = new BoxNumVO();
                    numVO.setBoxCd(boxVO.getBoxCd());
                    numVO.setBoxNum(i);
                    numVO.setUserId("");
                    numVO.setBoxFlag("N");
                    numVO.setRegId(boxVO.getRegId());
                    boxService.insertBoxNum(numVO);
                }
            }

            jsonObject.put("retMsg", "OK");
            jsonObject.put("boxCd", boxVO.getBoxCd());
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 수정
     */
    @Operation(summary = "사물함 수정", description = "사물함 정보를 수정합니다.")
    @PostMapping(value = "/updateBox")
    public JSONObject updateBox(@ModelAttribute("BoxVO") BoxVO boxVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        String msgStr = "";

        try {
            int yCnt = boxService.selectBoxNumYCount(boxVO);
            if (yCnt > 0) {
                // 사용중인 사물함이 있으므로 기본 정보만 수정
                boxService.updateBox2(boxVO);
                msgStr = "사용중인 사물함이 있어 기본 정보만 변경되었습니다.";
            } else {
                // 사물함 정보 수정 후 세부정보 삭제 후 새로 생성
                boxService.updateBox(boxVO);
                boxService.deleteBoxNumByBoxCd(boxVO);

                // 사물함 세부정보를 개수만큼 생성
                int boxCount = boxVO.getBoxCount();
                int startNum = boxVO.getStartNum();
                int endNum = boxVO.getEndNum();

                if (boxCount > 0) {
                    for (int i = startNum; i <= endNum; i++) {
                        BoxNumVO numVO = new BoxNumVO();
                        numVO.setBoxCd(boxVO.getBoxCd());
                        numVO.setBoxNum(i);
                        numVO.setUserId("");
                        numVO.setBoxFlag("N");
                        numVO.setRegId(boxVO.getUpdId());
                        boxService.insertBoxNum(numVO);
                    }
                }
                msgStr = "사물함 정보가 변경되었습니다.";
            }

            jsonObject.put("retMsg", "OK");
            jsonObject.put("msg", msgStr);
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 삭제
     */
    @Operation(summary = "사물함 삭제", description = "사물함을 삭제합니다. (연결된 세부정보도 함께 삭제)")
    @PostMapping(value = "/deleteBox")
    public JSONObject deleteBox(@ModelAttribute("BoxVO") BoxVO boxVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();
        String msgStr = "";

        try {
            if (boxVO.getBoxCd() == null || "".equals(boxVO.getBoxCd())) {
                msgStr = "삭제할 사물함 코드가 잘못되어 삭제할 수 없습니다.";
                jsonObject.put("retMsg", "FAIL");
            } else {
                int yCnt = boxService.selectBoxNumYCount(boxVO);
                if (yCnt > 0) {
                    msgStr = "삭제할 사물함에 사용중인 것이 있어 삭제할 수 없습니다.";
                    jsonObject.put("retMsg", "FAIL");
                } else {
                    boxService.deleteBoxAll(boxVO);
                    msgStr = "사물함 정보가 정상적으로 삭제되었습니다.";
                    jsonObject.put("retMsg", "OK");
                }
            }
            jsonObject.put("msg", msgStr);
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 사물함 세부정보 (TB_OFF_BOX_NUM) API
    // =====================================================

    /**
     * 사물함 세부정보 목록 조회
     */
    @Operation(summary = "사물함 세부정보 목록 조회", description = "페이징 처리된 사물함 세부정보 목록을 조회합니다.")
    @GetMapping(value = "/getBoxNumList")
    public JSONObject getBoxNumList(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(boxNumVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boxNumVO.getPageUnit());
        paginationInfo.setPageSize(boxNumVO.getPageSize());

        boxNumVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boxNumVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boxNumVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 부모 사물함 정보도 함께 조회
        BoxVO parentBoxVO = new BoxVO();
        parentBoxVO.setBoxCd(boxNumVO.getBoxCd());
        jsonObject.put("parentBox", boxService.selectBoxDetail(parentBoxVO));

        jsonObject.put("boxNumList", boxService.selectBoxNumList(boxNumVO));

        int totCnt = boxService.selectBoxNumListCount(boxNumVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 세부정보 상세 조회
     */
    @Operation(summary = "사물함 세부정보 상세 조회", description = "사물함 세부정보 상세를 조회합니다.")
    @GetMapping(value = "/getBoxNumDetail")
    public JSONObject getBoxNumDetail(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("boxNumDetail", boxService.selectBoxNumDetail(boxNumVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 세부정보 등록
     */
    @Operation(summary = "사물함 세부정보 등록", description = "새로운 사물함 세부정보를 등록합니다.")
    @PostMapping(value = "/insertBoxNum")
    public JSONObject insertBoxNum(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boxService.insertBoxNum(boxNumVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 세부정보 수정
     */
    @Operation(summary = "사물함 세부정보 수정", description = "사물함 세부정보를 수정합니다.")
    @PostMapping(value = "/updateBoxNum")
    public JSONObject updateBoxNum(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boxService.updateBoxNum(boxNumVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 플래그(사용상태) 수정
     */
    @Operation(summary = "사물함 플래그 수정", description = "사물함 사용상태를 수정합니다.")
    @PostMapping(value = "/updateBoxNumFlag")
    public JSONObject updateBoxNumFlag(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boxService.updateBoxNumFlag(boxNumVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 세부정보 초기화
     */
    @Operation(summary = "사물함 세부정보 초기화", description = "사물함 세부정보를 초기화합니다.")
    @PostMapping(value = "/updateBoxNumReset")
    public JSONObject updateBoxNumReset(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boxService.updateBoxNumReset(boxNumVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 사물함 세부정보 삭제
     */
    @Operation(summary = "사물함 세부정보 삭제", description = "사물함 세부정보를 삭제합니다.")
    @PostMapping(value = "/deleteBoxNum")
    public JSONObject deleteBoxNum(@ModelAttribute("BoxNumVO") BoxNumVO boxNumVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            boxService.deleteBoxNum(boxNumVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }
}
