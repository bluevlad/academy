package com.academy.productorder;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.productorder.service.ProductOrderService;
import com.academy.productorder.service.ProductOrderVO;

/**
 * ProductOrder API Controller
 * 상품 주문 관리 RESTful API
 */
@RestController
@RequestMapping("/api/productorder")
public class ProductOrderApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private ProductOrderService productOrderService;

    @Autowired
    public ProductOrderApi(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    /**
     * 온라인 주문 목록 조회
     */
    @GetMapping("/list")
    public JSONObject list(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.getOrderListDB(vo);
        int listCount = productOrderService.getOrderListCount(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 0원 주문 목록 조회
     */
    @GetMapping("/list0")
    public JSONObject list0(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.getOrderListDB_0(vo);
        int listCount = productOrderService.getOrderListCount_0(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 무료 강의 주문 목록 조회
     */
    @GetMapping("/listFreelec")
    public JSONObject listFreelec(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.getOrderListDB_freelec(vo);
        int listCount = productOrderService.getOrderListCount_freelec(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 목록 조회
     */
    @GetMapping("/offList")
    public JSONObject offList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.getOffOrderListDB(vo);
        int listCount = productOrderService.getOffOrderListCount(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 주문 상세 조회
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, Object>> approvalsList = productOrderService.getTblApprovalsViewList(vo);
        List<HashMap<String, Object>> ordersList = productOrderService.getTblOrdersViewList(vo);
        List<HashMap<String, Object>> orderMgntList = productOrderService.getTblOrderMgntNoPopViewList(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("approvalsList", approvalsList);
        result.put("ordersList", ordersList);
        result.put("orderMgntList", orderMgntList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 주문 상태 코드 목록
     */
    @GetMapping("/statusCodeList")
    public JSONObject statusCodeList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, Object>> statusList = productOrderService.getOrderStatuscodeList(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("statusList", statusList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 결제 수단 목록
     */
    @GetMapping("/paymentList")
    public JSONObject paymentList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, Object>> paymentList = productOrderService.getPaymentList(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("paymentList", paymentList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 온라인 주문 엑셀 다운로드
     */
    @GetMapping("/excelList")
    public JSONObject excelList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, String>> list = productOrderService.getOrderExcelListDB(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 엑셀 다운로드
     */
    @GetMapping("/offExcelList")
    public JSONObject offExcelList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, String>> list = productOrderService.getOffOrderExcelListDB(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 결제 수단 변경
     */
    @PutMapping("/updatePayKind")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updatePayKind(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.setPayKindUpdate(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "결제 수단이 변경되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 입금 상태 업데이트
     */
    @PutMapping("/updateDepositStatus")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateDepositStatus(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.updateDepositStatus1(vo);
        productOrderService.updateDepositStatus2(vo);
        productOrderService.updateDepositStatus3(vo);
        productOrderService.updateDepositStatus4(vo);
        productOrderService.updateDepositStatus5(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "입금 상태가 업데이트되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 진도율 업데이트
     */
    @PutMapping("/updateStudyPer")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateStudyPer(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.updateStudy_Per(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "진도율이 업데이트되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 등록
     */
    @PostMapping("/insertOffOrder")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertOffOrder(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.insertOffOrders(vo);
        productOrderService.insertOffApprovals(vo);
        productOrderService.insertOffOrderMgntNo(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "오프라인 주문이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 수정
     */
    @PutMapping("/updateOffOrder")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateOffOrder(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.updateOffOrders(vo);
        productOrderService.updateOffApprovals(vo);
        productOrderService.updateOffMgntNo(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "오프라인 주문이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 삭제
     */
    @DeleteMapping("/deleteOffOrder")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteOffOrder(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.deleteOffOrderMgntNo(vo);
        productOrderService.deleteOffMylecture(vo);
        productOrderService.deleteOffOrders(vo);
        productOrderService.deleteOffApprovals(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "오프라인 주문이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 환불 처리
     */
    @PostMapping("/insertRefund")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertRefund(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.updateOffRefund(vo);
        productOrderService.insertRefundOffOrderMgntNo(vo);
        productOrderService.updateOffMylecture(vo);
        productOrderService.insertOffRefund(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "환불이 처리되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 환불 취소
     */
    @DeleteMapping("/deleteRefund")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteRefund(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.deleteOffCancelOrderMgntNo(vo);
        productOrderService.deleteOffCancelRefund(vo);
        productOrderService.updateOffCancelMylecture(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "환불이 취소되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 정보 조회
     */
    @GetMapping("/memberView")
    public JSONObject memberView(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, Object>> memberInfo = productOrderService.getTmMember_View(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("memberInfo", memberInfo);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 포인트 히스토리
     */
    @GetMapping("/pointHistory")
    public JSONObject pointHistory(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, String>> list = productOrderService.getTmPointHistory(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 쿠폰 목록
     */
    @GetMapping("/memberCouponList")
    public JSONObject memberCouponList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.getTm_mycoupon_list_admin(vo);
        int listCount = productOrderService.getTm_mycoupon_listCount_admin(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 강의 목록 (온라인)
     */
    @GetMapping("/memberClassList")
    public JSONObject memberClassList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.Tm_Class_List(vo);
        int listCount = productOrderService.getTmClassListCount(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 강의 목록 (오프라인)
     */
    @GetMapping("/memberOffClassList")
    public JSONObject memberOffClassList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        List<HashMap<String, Object>> list = productOrderService.Off_Class_List(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * CS 게시판 목록
     */
    @GetMapping("/csBoardList")
    public JSONObject csBoardList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.Cs_board_list(vo);
        int listCount = productOrderService.getCsBoardListCount(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * CS 게시판 등록
     */
    @PostMapping("/insertCsBoard")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertCsBoard(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.insertBoardCs(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "CS 게시판에 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 메모 목록
     */
    @GetMapping("/memoList")
    public JSONObject memoList(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        /* 페이징 */
        int currentPage = vo.getCurrentPage();
        int pageRow = vo.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, Object>> list = productOrderService.getMemoList(vo);
        int listCount = productOrderService.getMemoListCount(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 메모 수정
     */
    @PutMapping("/updateMemo")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateMemo(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.updateMemo(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "메모가 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 발급
     */
    @PostMapping("/insertCoupon")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertCoupon(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.insertTmCoupon(vo);
        productOrderService.insertMyCoupon(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "쿠폰이 발급되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 삭제
     */
    @DeleteMapping("/deleteCoupon")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteCoupon(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.Coupon_Del(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "쿠폰이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 내 강의실 강의 등록
     */
    @PostMapping("/insertMyLecture")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertMyLecture(@ModelAttribute ProductOrderVO vo, HttpServletRequest request) throws Exception {
        setParam(vo, request);

        productOrderService.insertMyLecture(vo);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "내 강의실에 강의가 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 파라미터 SETTING
     */
    private void setParam(ProductOrderVO vo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if (loginInfo != null) {
                vo.setRegId(loginInfo.get("USER_ID"));
                vo.setUpdId(loginInfo.get("USER_ID"));
                vo.setManagerId(loginInfo.get("USER_ID"));
            }
        }

        // 페이징 파라미터
        String currentPageStr = request.getParameter("currentPage");
        String pageRowStr = request.getParameter("pageRow");
        vo.setCurrentPage(currentPageStr != null && !currentPageStr.isEmpty() ? Integer.parseInt(currentPageStr) : 1);
        vo.setPageRow(pageRowStr != null && !pageRowStr.isEmpty() ? Integer.parseInt(pageRowStr) : pageUnit);

        // 공통 검색 파라미터
        vo.setSearchText(CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        vo.setSearchType(CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        vo.setSchtype(CommonUtil.isNull(request.getParameter("schtype"), ""));
        vo.setKeyword(CommonUtil.isNull(request.getParameter("keyword"), ""));
        vo.setSearchkey(CommonUtil.isNull(request.getParameter("searchkey"), ""));
        vo.setSearchtype(CommonUtil.isNull(request.getParameter("searchtype"), ""));

        // 날짜 관련 파라미터
        vo.setSdate(CommonUtil.isNull(request.getParameter("sdate"), ""));
        vo.setEdate(CommonUtil.isNull(request.getParameter("edate"), ""));
        vo.setSearchDateType(CommonUtil.isNull(request.getParameter("search_date_type"), ""));

        // 주문 관련 파라미터
        vo.setOrderstatuscode(CommonUtil.isNull(request.getParameter("orderstatuscode"), ""));
        vo.setCodeNo(CommonUtil.isNull(request.getParameter("code_no"), ""));
        vo.setSysCd(CommonUtil.isNull(request.getParameter("sys_cd"), ""));
        vo.setSortfield(CommonUtil.isNull(request.getParameter("sortfield"), ""));
        vo.setSort(CommonUtil.isNull(request.getParameter("sort"), ""));

        // 기타 파라미터
        vo.setOrderno(CommonUtil.isNull(request.getParameter("ORDER_NO"), ""));
        vo.setApprovalNo(CommonUtil.isNull(request.getParameter("APPROVAL_NO"), ""));
        vo.setUserId(CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        vo.setMemberId(CommonUtil.isNull(request.getParameter("MEMBER_ID"), ""));
        vo.setLecCd(CommonUtil.isNull(request.getParameter("LEC_CD"), ""));
        vo.setCourseCd(CommonUtil.isNull(request.getParameter("COURSE_CD"), ""));
        vo.setCcode(CommonUtil.isNull(request.getParameter("CCODE"), ""));

        String priceStr = CommonUtil.isNull(request.getParameter("PRICE"), "");
        if (!priceStr.isEmpty()) {
            vo.setPrice(Integer.parseInt(priceStr));
        }

        String pointStr = CommonUtil.isNull(request.getParameter("POINT"), "");
        if (!pointStr.isEmpty()) {
            vo.setPoint(Integer.parseInt(pointStr));
        }

        vo.setPayKind(CommonUtil.isNull(request.getParameter("PAY_KIND"), ""));
        vo.setDepositStatus(CommonUtil.isNull(request.getParameter("DEPOSIT_STATUS"), ""));

        String refundPriceStr = CommonUtil.isNull(request.getParameter("REFUND_PRICE"), "");
        if (!refundPriceStr.isEmpty()) {
            vo.setRefundPrice(Integer.parseInt(refundPriceStr));
        }

        vo.setRefundReason(CommonUtil.isNull(request.getParameter("REFUND_REASON"), ""));
    }
}
