package com.academy.coop;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.coop.service.CoopBoardVO;
import com.academy.coop.service.CoopIpVO;
import com.academy.coop.service.CoopOrderVO;
import com.academy.coop.service.CoopService;
import com.academy.coop.service.CoopVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 제휴사 관리 API Controller
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            제휴사 관리 신규 생성
 * </pre>
 */
@Tag(name = "Coop", description = "제휴사 관리 API")
@RestController
@RequestMapping("/api/coop")
public class CoopApi extends CORSFilter {

    private final CoopService coopService;

    public CoopApi(CoopService coopService) {
        this.coopService = coopService;
    }

    // =====================================================
    // 제휴사 마스터 (COOP_MST) API
    // =====================================================

    /**
     * 제휴사 목록 조회
     */
    @Operation(summary = "제휴사 목록 조회", description = "제휴사 목록을 조회합니다.")
    @GetMapping(value = "/getCoopList")
    public JSONObject getCoopList(@ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(coopVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(coopVO.getPageUnit());
        paginationInfo.setPageSize(coopVO.getPageSize());

        coopVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        coopVO.setLastIndex(paginationInfo.getLastRecordIndex());
        coopVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("coopList", coopService.selectCoopList(coopVO));

        int totCnt = coopService.selectCoopListCount(coopVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 상세 조회
     */
    @Operation(summary = "제휴사 상세 조회", description = "제휴사 상세 정보를 조회합니다.")
    @GetMapping(value = "/getCoopDetail")
    public JSONObject getCoopDetail(@ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("coopDetail", coopService.selectCoopDetail(coopVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 등록
     */
    @Operation(summary = "제휴사 등록", description = "새로운 제휴사를 등록합니다.")
    @PostMapping(value = "/insertCoop")
    public JSONObject insertCoop(@ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.insertCoop(coopVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 수정
     */
    @Operation(summary = "제휴사 수정", description = "제휴사 정보를 수정합니다.")
    @PostMapping(value = "/updateCoop")
    public JSONObject updateCoop(@ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.updateCoop(coopVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 삭제
     */
    @Operation(summary = "제휴사 삭제", description = "제휴사를 삭제합니다.")
    @PostMapping(value = "/deleteCoop")
    public JSONObject deleteCoop(@ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.deleteCoop(coopVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 제휴사 IP (COOP_USE_IP) API
    // =====================================================

    /**
     * 제휴사 IP 목록 조회
     */
    @Operation(summary = "제휴사 IP 목록 조회", description = "제휴사 IP 목록을 조회합니다.")
    @GetMapping(value = "/getCoopIpList")
    public JSONObject getCoopIpList(@ModelAttribute("CoopIpVO") CoopIpVO coopIpVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("coopIpList", coopService.selectCoopIpList(coopIpVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 IP 등록
     */
    @Operation(summary = "제휴사 IP 등록", description = "새로운 제휴사 IP를 등록합니다.")
    @PostMapping(value = "/insertCoopIp")
    public JSONObject insertCoopIp(@ModelAttribute("CoopIpVO") CoopIpVO coopIpVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.insertCoopIp(coopIpVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 IP 삭제
     */
    @Operation(summary = "제휴사 IP 삭제", description = "제휴사 IP를 삭제합니다.")
    @PostMapping(value = "/deleteCoopIp")
    public JSONObject deleteCoopIp(@ModelAttribute("CoopIpVO") CoopIpVO coopIpVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.deleteCoopIp(coopIpVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 제휴사 게시판 (TB_BOARD_MEMBERSHIP) API
    // =====================================================

    /**
     * 제휴사 게시판 목록 조회
     */
    @Operation(summary = "제휴사 게시판 목록 조회", description = "페이징 처리된 제휴사 게시판 목록을 조회합니다.")
    @GetMapping(value = "/getCoopBoardList")
    public JSONObject getCoopBoardList(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(coopBoardVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(coopBoardVO.getPageUnit());
        paginationInfo.setPageSize(coopBoardVO.getPageSize());

        coopBoardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        coopBoardVO.setLastIndex(paginationInfo.getLastRecordIndex());
        coopBoardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 코드 목록 조회
        CoopBoardVO areaVO = new CoopBoardVO();
        areaVO.setSearchKeyword("COOP_AREA");
        jsonObject.put("codeAreaList", coopService.selectCoopCodeList(areaVO));

        CoopBoardVO hsptVO = new CoopBoardVO();
        hsptVO.setSearchKeyword("COOP_HSPT");
        jsonObject.put("codeHsptList", coopService.selectCoopCodeList(hsptVO));

        jsonObject.put("coopBoardList", coopService.selectCoopBoardList(coopBoardVO));

        int totCnt = coopService.selectCoopBoardListCount(coopBoardVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 게시판 상세 조회
     */
    @Operation(summary = "제휴사 게시판 상세 조회", description = "제휴사 게시판 상세 정보를 조회합니다.")
    @GetMapping(value = "/getCoopBoardDetail")
    public JSONObject getCoopBoardDetail(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // 코드 목록 조회
        CoopBoardVO areaVO = new CoopBoardVO();
        areaVO.setSearchKeyword("COOP_AREA");
        jsonObject.put("codeAreaList", coopService.selectCoopCodeList(areaVO));

        CoopBoardVO hsptVO = new CoopBoardVO();
        hsptVO.setSearchKeyword("COOP_HSPT");
        jsonObject.put("codeHsptList", coopService.selectCoopCodeList(hsptVO));

        jsonObject.put("coopBoardDetail", coopService.selectCoopBoardDetail(coopBoardVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 게시판 등록
     */
    @Operation(summary = "제휴사 게시판 등록", description = "새로운 제휴사 게시글을 등록합니다.")
    @PostMapping(value = "/insertCoopBoard")
    public JSONObject insertCoopBoard(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.insertCoopBoard(coopBoardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 게시판 수정
     */
    @Operation(summary = "제휴사 게시판 수정", description = "제휴사 게시글을 수정합니다.")
    @PostMapping(value = "/updateCoopBoard")
    public JSONObject updateCoopBoard(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.updateCoopBoard(coopBoardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 게시판 삭제
     */
    @Operation(summary = "제휴사 게시판 삭제", description = "제휴사 게시글을 삭제합니다.")
    @PostMapping(value = "/deleteCoopBoard")
    public JSONObject deleteCoopBoard(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.deleteCoopBoard(coopBoardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 게시판 정렬순서 수정
     */
    @Operation(summary = "제휴사 게시판 정렬순서 수정", description = "제휴사 게시글의 정렬순서를 수정합니다.")
    @PostMapping(value = "/updateCoopBoardRank")
    public JSONObject updateCoopBoardRank(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.updateCoopBoardRank(coopBoardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 게시판 파일 삭제
     */
    @Operation(summary = "제휴사 게시판 파일 삭제", description = "제휴사 게시글의 첨부파일을 삭제합니다.")
    @PostMapping(value = "/deleteCoopBoardFile")
    public JSONObject deleteCoopBoardFile(@ModelAttribute("CoopBoardVO") CoopBoardVO coopBoardVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            coopService.updateCoopBoardDeleteFile(coopBoardVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 제휴사 주문 API
    // =====================================================

    /**
     * 제휴사 주문 목록 조회
     */
    @Operation(summary = "제휴사 주문 목록 조회", description = "페이징 처리된 제휴사 주문 목록을 조회합니다.")
    @GetMapping(value = "/getCoopOrderList")
    public JSONObject getCoopOrderList(@ModelAttribute("CoopOrderVO") CoopOrderVO coopOrderVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(coopOrderVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(coopOrderVO.getPageUnit());
        paginationInfo.setPageSize(coopOrderVO.getPageSize());

        coopOrderVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        coopOrderVO.setLastIndex(paginationInfo.getLastRecordIndex());
        coopOrderVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        jsonObject.put("coopOrderList", coopService.selectCoopOrderList(coopOrderVO));

        int totCnt = coopService.selectCoopOrderListCount(coopOrderVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 제휴사 결제 상세 목록 조회
     */
    @Operation(summary = "제휴사 결제 상세 조회", description = "제휴사 결제 상세 정보를 조회합니다.")
    @GetMapping(value = "/getCoopPayDetailList")
    public JSONObject getCoopPayDetailList(@ModelAttribute("CoopOrderVO") CoopOrderVO coopOrderVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("coopPayDetailList", coopService.selectCoopPayDetailList(coopOrderVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }
}
