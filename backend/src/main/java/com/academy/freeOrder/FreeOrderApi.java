package com.academy.freeOrder;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.freeOrder.service.FreeOrderService;
import com.academy.freeOrder.service.FreeOrderVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 무료 수강신청 관리 API Controller
 * 
 * @author system
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.11         system            무료 수강신청 관리 신규 생성
 *      </pre>
 */
@Tag(name = "FreeOrder", description = "무료 수강신청 관리 API")
@RestController
@RequestMapping("/api/freeOrder")
public class FreeOrderApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final FreeOrderService freeOrderService;

    @Autowired
    public FreeOrderApi(FreeOrderService freeOrderService) {
        this.freeOrderService = freeOrderService;
    }

    // =====================================================
    // 수강신청 관련 API
    // =====================================================

    /**
     * 수강신청 회원 목록 조회
     */
    @Operation(summary = "수강신청 회원 목록 조회", description = "수강신청 가능한 회원 목록을 조회합니다.")
    @GetMapping("/getMemberFreeOrderList")
    public JSONObject getMemberFreeOrderList(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(freeOrderVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(freeOrderVO.getPageUnit() > 0 ? freeOrderVO.getPageUnit() : pageUnit);
            paginationInfo.setPageSize(freeOrderVO.getPageSize());

            freeOrderVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            freeOrderVO.setLastIndex(paginationInfo.getLastRecordIndex());
            freeOrderVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = freeOrderService.getMemberFreeOrderList(freeOrderVO);
            int totalCount = freeOrderService.getMemberFreeOrderListCount(freeOrderVO);

            paginationInfo.setTotalRecordCount(totalCount);

            resultMap.put("list", list);
            resultMap.put("totalCount", totalCount);
            resultMap.put("totalPage", paginationInfo.getTotalPageCount());
            resultMap.put("currentPage", freeOrderVO.getPageIndex());
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 강의 마스터 정보 조회 (수강신청 등록 팝업용)
     */
    @Operation(summary = "강의 마스터 정보 조회", description = "수강신청 등록을 위한 강의 마스터 정보를 조회합니다.")
    @GetMapping("/getLectureMstInfo")
    public JSONObject getLectureMstInfo(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            JSONObject data = freeOrderService.getLectureMstInfo(freeOrderVO);
            resultMap.put("data", data);
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    // =====================================================
    // 강의선택 팝업 관련 API
    // =====================================================

    /**
     * 카테고리 목록 조회
     */
    @Operation(summary = "카테고리 목록 조회", description = "강의선택 팝업용 카테고리 목록을 조회합니다.")
    @GetMapping("/getCategoryList")
    public JSONObject getCategoryList(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<JSONObject> list = freeOrderService.getCategoryList(freeOrderVO);
            resultMap.put("list", list);
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 학습형태 목록 조회
     */
    @Operation(summary = "학습형태 목록 조회", description = "강의선택 팝업용 학습형태 목록을 조회합니다.")
    @GetMapping("/getLearningFormList")
    public JSONObject getLearningFormList(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<JSONObject> list = freeOrderService.getLearningFormList(freeOrderVO);
            resultMap.put("list", list);
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 과목 목록 조회
     */
    @Operation(summary = "과목 목록 조회", description = "강의선택 팝업용 과목 목록을 조회합니다.")
    @GetMapping("/getSubjectList")
    public JSONObject getSubjectList(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<JSONObject> list = freeOrderService.getSubjectList(freeOrderVO);
            resultMap.put("list", list);
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 강의선택 팝업 목록 조회
     */
    @Operation(summary = "강의선택 팝업 목록 조회", description = "수강신청용 강의 목록을 조회합니다.")
    @GetMapping("/getLectureListForFreeOrder")
    public JSONObject getLectureListForFreeOrder(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(freeOrderVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(freeOrderVO.getPageUnit() > 0 ? freeOrderVO.getPageUnit() : pageUnit);
            paginationInfo.setPageSize(freeOrderVO.getPageSize());

            freeOrderVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            freeOrderVO.setLastIndex(paginationInfo.getLastRecordIndex());
            freeOrderVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = freeOrderService.getLectureListForFreeOrder(freeOrderVO);
            int totalCount = freeOrderService.getLectureListForFreeOrderCount(freeOrderVO);

            paginationInfo.setTotalRecordCount(totalCount);

            resultMap.put("list", list);
            resultMap.put("totalCount", totalCount);
            resultMap.put("totalPage", paginationInfo.getTotalPageCount());
            resultMap.put("currentPage", freeOrderVO.getPageIndex());
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    // =====================================================
    // 수강신청 등록 관련 API
    // =====================================================

    /**
     * 수강신청 등록
     */
    @Operation(summary = "수강신청 등록", description = "무료 수강신청을 등록합니다.")
    @PostMapping("/insertFreeOrder")
    public JSONObject insertFreeOrder(@ModelAttribute FreeOrderVO freeOrderVO, HttpServletRequest request) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            // 세션에서 관리자 ID 조회
            @SuppressWarnings("unchecked")
            HashMap<String, String> sessionMap = (HashMap<String, String>) request.getSession()
                    .getAttribute("AdmUserInfo");
            String adminUserId = sessionMap != null ? sessionMap.get("USER_ID") : "";

            if (adminUserId == null || adminUserId.isEmpty()) {
                resultMap.put("retMsg", "FAIL");
                resultMap.put("errMsg", "관리자 로그인이 필요합니다.");
                return new JSONObject(resultMap);
            }

            // 사용자 ID 처리 (쉼표를 슬래시로 변환)
            String userid2 = freeOrderVO.getUserid2();
            if (userid2 != null && !userid2.isEmpty()) {
                userid2 = userid2.replace(',', '/');
            }

            String[] userIds = userid2 != null ? userid2.split("/") : new String[0];

            // 현재 날짜
            TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(tz);
            // String today = sdf.format(new Date());

            String userIp = request.getRemoteAddr();
            String leccode = freeOrderVO.getLeccode();

            for (String userId : userIds) {
                userId = userId.trim();
                if (userId.isEmpty())
                    continue;

                freeOrderVO.setUserid(userId);

                // 사용자 존재여부 확인
                JSONObject memberInfo = freeOrderService.getMemberExists(freeOrderVO);
                if (memberInfo == null)
                    continue;

                // 주문번호 채번
                freeOrderVO.setOrdernoCount("M");
                String orderno = freeOrderService.getNextOrderNo(freeOrderVO);
                freeOrderVO.setOrderno(orderno);
                freeOrderVO.setMgntno(leccode);
                freeOrderVO.setUserId(adminUserId);
                freeOrderVO.setUserIp(userIp);

                // 강의 타입에 따라 등록 처리
                if (leccode != null && leccode.length() > 0) {
                    String lecType = leccode.substring(0, 1);

                    if ("D".equals(lecType)) {
                        // 단과 수강신청
                        freeOrderService.insertFreeOrder(freeOrderVO);
                    } else if ("J".equals(lecType) || "N".equals(lecType) || "P".equals(lecType)) {
                        // 종합반 수강신청
                        freeOrderService.insertFreeOrderPackage(freeOrderVO);
                    } else {
                        // 연회원패키지 등록
                        freeOrderService.insertFreeOrderYearPackage(freeOrderVO);
                    }
                }
            }

            resultMap.put("retMsg", "OK");
            resultMap.put("message", "등록완료");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 다중 수강신청 등록
     */
    @Operation(summary = "다중 수강신청 등록", description = "여러 강좌에 대한 무료 수강신청을 등록합니다.")
    @PostMapping("/insertFreeOrderMultiple")
    public JSONObject insertFreeOrderMultiple(@ModelAttribute FreeOrderVO freeOrderVO, HttpServletRequest request) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            // 세션에서 관리자 ID 조회
            @SuppressWarnings("unchecked")
            HashMap<String, String> sessionMap = (HashMap<String, String>) request.getSession()
                    .getAttribute("AdmUserInfo");
            String adminUserId = sessionMap != null ? sessionMap.get("USER_ID") : "";

            if (adminUserId == null || adminUserId.isEmpty()) {
                resultMap.put("retMsg", "FAIL");
                resultMap.put("errMsg", "관리자 로그인이 필요합니다.");
                return new JSONObject(resultMap);
            }

            // 사용자 ID 및 강의코드 처리
            String userid2 = freeOrderVO.getUserid2();
            if (userid2 != null && !userid2.isEmpty()) {
                userid2 = userid2.replace(',', '/');
            }

            String leccode = freeOrderVO.getLeccode();
            if (leccode != null && leccode.endsWith("/")) {
                leccode = leccode.substring(0, leccode.length() - 1);
            }

            String[] userIds = userid2 != null ? userid2.split("/") : new String[0];
            String[] leccodes = leccode != null ? leccode.split("/") : new String[0];

            String userIp = request.getRemoteAddr();

            for (String userId : userIds) {
                userId = userId.trim();
                if (userId.isEmpty())
                    continue;

                freeOrderVO.setUserid(userId);

                // 사용자 존재여부 확인
                JSONObject memberInfo = freeOrderService.getMemberExists(freeOrderVO);
                if (memberInfo == null)
                    continue;

                for (String lec : leccodes) {
                    lec = lec.trim();
                    if (lec.isEmpty())
                        continue;

                    // 주문번호 채번
                    freeOrderVO.setOrdernoCount("M");
                    String orderno = freeOrderService.getNextOrderNo(freeOrderVO);
                    freeOrderVO.setOrderno(orderno);
                    freeOrderVO.setLeccode(lec);
                    freeOrderVO.setMgntno(lec);
                    freeOrderVO.setUserId(adminUserId);
                    freeOrderVO.setUserIp(userIp);

                    // 강의 타입에 따라 등록 처리
                    String lecType = lec.substring(0, 1);

                    if ("D".equals(lecType)) {
                        freeOrderService.insertFreeOrder(freeOrderVO);
                    } else {
                        freeOrderService.insertFreeOrderPackage(freeOrderVO);
                    }
                }
            }

            resultMap.put("retMsg", "OK");
            resultMap.put("message", "등록완료");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    // =====================================================
    // 수강변경 관련 API
    // =====================================================

    /**
     * 수강변경 목록 조회
     */
    @Operation(summary = "수강변경 목록 조회", description = "수강변경 목록을 조회합니다.")
    @GetMapping("/getChangeLectureList")
    public JSONObject getChangeLectureList(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(freeOrderVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(freeOrderVO.getPageUnit() > 0 ? freeOrderVO.getPageUnit() : pageUnit);
            paginationInfo.setPageSize(freeOrderVO.getPageSize());

            freeOrderVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            freeOrderVO.setLastIndex(paginationInfo.getLastRecordIndex());
            freeOrderVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = freeOrderService.getChangeLectureList(freeOrderVO);
            int totalCount = freeOrderService.getChangeLectureListCount(freeOrderVO);

            paginationInfo.setTotalRecordCount(totalCount);

            resultMap.put("list", list);
            resultMap.put("totalCount", totalCount);
            resultMap.put("totalPage", paginationInfo.getTotalPageCount());
            resultMap.put("currentPage", freeOrderVO.getPageIndex());
            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 수강변경 상세 조회
     */
    @Operation(summary = "수강변경 상세 조회", description = "수강변경 상세 정보를 조회합니다.")
    @GetMapping("/getChangeViewDetail")
    public JSONObject getChangeViewDetail(@ModelAttribute FreeOrderVO freeOrderVO) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            // 결제승인 상세
            JSONObject approvalsDetail = freeOrderService.getApprovalsDetail(freeOrderVO);
            resultMap.put("payInfo", approvalsDetail);

            // 배송정보 조회
            int deliversCount = freeOrderService.getDeliversCount(freeOrderVO);
            if (deliversCount > 0) {
                JSONObject deliversDetail = freeOrderService.getDeliversDetail(freeOrderVO);
                resultMap.put("delInfo", deliversDetail);
            }

            // 주문자 정보
            JSONObject orderInfo = freeOrderService.getChangeViewOrderInfo(freeOrderVO);
            resultMap.put("orderInfo", orderInfo);

            // 강의/도서 목록
            List<JSONObject> lecList = freeOrderService.getChangeViewList(freeOrderVO);
            resultMap.put("lecList", lecList);

            resultMap.put("retMsg", "OK");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }

    /**
     * 수강변경 처리
     */
    @Operation(summary = "수강변경 처리", description = "수강을 변경합니다.")
    @PostMapping("/updateChangeLecture")
    public JSONObject updateChangeLecture(@ModelAttribute FreeOrderVO freeOrderVO, HttpServletRequest request) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            // 세션에서 관리자 ID 조회
            @SuppressWarnings("unchecked")
            HashMap<String, String> sessionMap = (HashMap<String, String>) request.getSession()
                    .getAttribute("AdmUserInfo");
            String adminUserId = sessionMap != null ? sessionMap.get("USER_ID") : "";

            freeOrderVO.setUserId(adminUserId);

            freeOrderService.updateChangeLecture(freeOrderVO);

            resultMap.put("retMsg", "OK");
            resultMap.put("message", "변경완료");
        } catch (Exception e) {
            resultMap.put("retMsg", "FAIL");
            resultMap.put("errMsg", e.getMessage());
        }

        return new JSONObject(resultMap);
    }
}
