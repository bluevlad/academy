package com.academy.counsel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.counsel.service.CounselRequestVO;
import com.academy.counsel.service.CounselScheduleVO;
import com.academy.counsel.service.CounselService;
import com.academy.counsel.service.PresentRequestVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 상담 관리 API Controller
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            상담 관리 신규 생성
 * </pre>
 */
@Tag(name = "Counsel", description = "상담 관리 API")
@RestController
@RequestMapping("/api/counsel")
public class CounselApi extends CORSFilter {

    private final CounselService counselService;

    public CounselApi(CounselService counselService) {
        this.counselService = counselService;
    }

    // =====================================================
    // 상담 일정 (COUNSEL_SCH) API
    // =====================================================

    /**
     * 상담 일정 일자 목록 조회
     */
    @Operation(summary = "상담 일정 일자 목록 조회", description = "상담 운영 일정 목록을 조회합니다.")
    @GetMapping(value = "/getScheduleDayList")
    public JSONObject getScheduleDayList(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO,
                                          @RequestParam(value = "searchCategory", required = false) String searchCategory) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // 카테고리 목록 처리
        if (searchCategory != null && searchCategory.length() > 1) {
            List<String> cateList = new ArrayList<String>();
            String[] cats = searchCategory.split(",");
            for (String cat : cats) {
                if (cat != null && cat.length() > 0) {
                    cateList.add(cat);
                }
            }
            counselScheduleVO.setCateList(cateList);
        }

        jsonObject.put("scheduleDayList", counselService.selectScheduleDayList(counselScheduleVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 일정 상세 목록 조회
     */
    @Operation(summary = "상담 일정 상세 목록 조회", description = "특정 일자의 상담 일정 상세를 조회합니다.")
    @GetMapping(value = "/getScheduleList")
    public JSONObject getScheduleList(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("scheduleList", counselService.selectScheduleList(counselScheduleVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 일정 테이블 조회
     */
    @Operation(summary = "상담 일정 테이블 조회", description = "상담 일정 테이블을 조회합니다.")
    @GetMapping(value = "/getScheduleTable")
    public JSONObject getScheduleTable(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("scheduleTable", counselService.selectScheduleTable(counselScheduleVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 시간 테이블 조회
     */
    @Operation(summary = "상담 시간 테이블 조회", description = "상담 시간 테이블을 조회합니다.")
    @GetMapping(value = "/getTimeTable")
    public JSONObject getTimeTable(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("timeTable", counselService.selectTimeTable(counselScheduleVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 일정 등록
     */
    @Operation(summary = "상담 일정 등록", description = "새로운 상담 일정을 등록합니다.")
    @PostMapping(value = "/insertSchedule")
    public JSONObject insertSchedule(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            // 중복 체크
            int count = 0;
            String catCds = counselScheduleVO.getCatCds();
            if (catCds != null && catCds.length() > 1) {
                String[] cats = catCds.split(",");
                for (String cat : cats) {
                    if (cat != null && cat.length() > 0) {
                        counselScheduleVO.setCatCd(cat);
                        count += counselService.selectScheduleCount(counselScheduleVO);
                    }
                }
            } else {
                count = counselService.selectScheduleCount(counselScheduleVO);
            }

            if (count > 0) {
                jsonObject.put("retMsg", "DUPLICATE");
                jsonObject.put("msg", "이미 등록된 일정이 있습니다.");
            } else {
                counselService.insertScheduleBatch(counselScheduleVO);
                jsonObject.put("retMsg", "OK");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 일정 수정
     */
    @Operation(summary = "상담 일정 수정", description = "상담 일정을 수정합니다.")
    @PostMapping(value = "/updateSchedule")
    public JSONObject updateSchedule(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            counselService.updateSchedule(counselScheduleVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 일정 삭제
     */
    @Operation(summary = "상담 일정 삭제", description = "상담 일정을 삭제합니다.")
    @PostMapping(value = "/deleteSchedule")
    public JSONObject deleteSchedule(@ModelAttribute("CounselScheduleVO") CounselScheduleVO counselScheduleVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        try {
            counselService.deleteScheduleBatch(counselScheduleVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 상담 신청 (COUNSEL_RST) API
    // =====================================================

    /**
     * 상담 신청 목록 조회
     */
    @Operation(summary = "상담 신청 목록 조회", description = "페이징 처리된 상담 신청 목록을 조회합니다.")
    @GetMapping(value = "/getCounselRequestList")
    public JSONObject getCounselRequestList(@ModelAttribute("CounselRequestVO") CounselRequestVO counselRequestVO,
                                             @RequestParam(value = "searchCategoryS", required = false) String searchCategoryS) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(counselRequestVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(counselRequestVO.getPageUnit());
        paginationInfo.setPageSize(counselRequestVO.getPageSize());

        counselRequestVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        counselRequestVO.setLastIndex(paginationInfo.getLastRecordIndex());
        counselRequestVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 카테고리 목록 처리
        if (searchCategoryS != null && searchCategoryS.length() > 1) {
            List<String> cateList = new ArrayList<String>();
            String[] cats = searchCategoryS.split(",");
            for (String cat : cats) {
                if (cat != null && cat.length() > 0) {
                    cateList.add(cat);
                }
            }
            counselRequestVO.setCateList(cateList);
        }

        jsonObject.put("counselRequestList", counselService.selectCounselRequestList(counselRequestVO));

        int totCnt = counselService.selectCounselRequestListCount(counselRequestVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 상담 예약 현황 조회
     */
    @Operation(summary = "상담 예약 현황 조회", description = "특정 일자의 상담 예약 현황을 조회합니다.")
    @GetMapping(value = "/getCounselReqList")
    public JSONObject getCounselReqList(@ModelAttribute("CounselRequestVO") CounselRequestVO counselRequestVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("counselReqList", counselService.selectCounselReqList(counselRequestVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    /**
     * 개인 상담 신청 내역 조회
     */
    @Operation(summary = "개인 상담 신청 내역 조회", description = "개인별 상담 신청 내역을 조회합니다.")
    @GetMapping(value = "/getCounselUserReq")
    public JSONObject getCounselUserReq(@ModelAttribute("CounselRequestVO") CounselRequestVO counselRequestVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        jsonObject.put("counselUserReq", counselService.selectCounselUserReq(counselRequestVO));

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }

    // =====================================================
    // 설명회 신청 (TB_PRESENT) API
    // =====================================================

    /**
     * 설명회 신청 목록 조회
     */
    @Operation(summary = "설명회 신청 목록 조회", description = "페이징 처리된 설명회 신청 목록을 조회합니다.")
    @GetMapping(value = "/getPresentReqList")
    public JSONObject getPresentReqList(@ModelAttribute("PresentRequestVO") PresentRequestVO presentRequestVO) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<String, Object>();

        // paging
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(presentRequestVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(presentRequestVO.getPageUnit());
        paginationInfo.setPageSize(presentRequestVO.getPageSize());

        presentRequestVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        presentRequestVO.setLastIndex(paginationInfo.getLastRecordIndex());
        presentRequestVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 코드 목록 조회
        jsonObject.put("codeList", counselService.selectPresentCodeList(presentRequestVO));

        jsonObject.put("presentReqList", counselService.selectPresentReqList(presentRequestVO));

        int totCnt = counselService.selectPresentReqListCount(presentRequestVO);
        paginationInfo.setTotalRecordCount(totCnt);
        jsonObject.put("paginationInfo", paginationInfo);

        JSONObject jObject = new JSONObject(jsonObject);

        return jObject;
    }
}
