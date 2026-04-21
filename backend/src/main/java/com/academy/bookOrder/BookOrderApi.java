package com.academy.bookOrder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.bookOrder.service.BookOrderService;
import com.academy.bookOrder.service.BookOrderVO;

/**
 * BookOrder API Controller
 * 교재 배송/매출 관리 RESTful API
 */
@RestController
@RequestMapping("/api/bookOrder")
public class BookOrderApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BookOrderService bookOrderService;

    @Autowired
    public BookOrderApi(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    // =====================================================
    // 교재배송 리스트 (택배)
    // =====================================================

    /**
     * 교재배송 리스트 (택배) 조회
     */
    @GetMapping("/juList")
    public JSONObject juList(@ModelAttribute BookOrderVO vo) throws Exception {
        setDefaultDateRange(vo);
        setPagination(vo);

        List<HashMap<String, Object>> list;
        int listCount;

        // 사이트 타입에 따라 분기 (C: 경찰)
        if ("C".equals(vo.getSType())) {
            list = bookOrderService.getJuOrderListDB_COP(vo);
            listCount = bookOrderService.getJuOrderListCount_COP(vo);
        } else {
            list = bookOrderService.getJuOrderListDB(vo);
            listCount = bookOrderService.getJuOrderListCount(vo);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / vo.getPageUnit()));
        result.put("currentPage", vo.getCurrentPage());

        return new JSONObject(result);
    }

    /**
     * 교재배송 2번째 리스트 (택배/직접수령)
     */
    @GetMapping("/juListSecond")
    public JSONObject juListSecond(@ModelAttribute BookOrderVO vo) throws Exception {
        List<HashMap<String, Object>> list;

        if ("C".equals(vo.getSType())) {
            list = bookOrderService.getTblJuOrderMgntListDB_COP(vo);
        } else {
            list = bookOrderService.getTblJuOrderMgntListDB(vo);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);

        return new JSONObject(result);
    }

    /**
     * 엑셀 리스트
     */
    @GetMapping("/juListExcel")
    public JSONObject juListExcel(@ModelAttribute BookOrderVO vo) throws Exception {
        setDefaultDateRange(vo);

        List<HashMap<String, Object>> list;

        if ("C".equals(vo.getSType())) {
            list = bookOrderService.getJuOrderExelListDB_COP(vo);
        } else {
            list = bookOrderService.getJuOrderExelListDB(vo);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);

        return new JSONObject(result);
    }

    // =====================================================
    // 전체상품주문관리
    // =====================================================

    /**
     * 전체상품주문관리 주문번호 상세
     */
    @GetMapping("/orderDetail")
    public JSONObject orderDetail(@ModelAttribute BookOrderVO vo) throws Exception {
        List<HashMap<String, Object>> approvalsList = bookOrderService.getTblApprovalsViewList(vo);
        int deliversCount = bookOrderService.getTblDeliversViewListCount(vo);
        List<HashMap<String, Object>> deliversList;
        List<HashMap<String, Object>> ordersList = bookOrderService.getTblOrdersViewList(vo);
        List<HashMap<String, Object>> lecMstList;

        if ("C".equals(vo.getSType())) {
            deliversList = bookOrderService.getTblDeliversViewList_COP(vo);
            lecMstList = bookOrderService.getLecMstViewList_COP(vo);
        } else {
            deliversList = bookOrderService.getTblDeliversViewList(vo);
            lecMstList = bookOrderService.getLecMstViewList(vo);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("approvalsList", approvalsList);
        result.put("deliversCount", deliversCount);
        result.put("deliversList", deliversList);
        result.put("ordersList", ordersList);
        result.put("lecMstList", lecMstList);

        return new JSONObject(result);
    }

    /**
     * 배송정보 수정
     */
    @PutMapping("/updateDelivers")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject updateDelivers(@ModelAttribute BookOrderVO vo) throws Exception {
        int count = bookOrderService.updateDelivers(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", count > 0 ? "success" : "fail");
        result.put("message", count > 0 ? "배송정보가 수정되었습니다" : "배송정보 수정에 실패했습니다");

        return new JSONObject(result);
    }

    /**
     * 승인정보 수정
     */
    @PutMapping("/updateApprovals")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject updateApprovals(@ModelAttribute BookOrderVO vo) throws Exception {
        int count = bookOrderService.updateApprovals(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", count > 0 ? "success" : "fail");
        result.put("message", count > 0 ? "승인정보가 수정되었습니다" : "승인정보 수정에 실패했습니다");

        return new JSONObject(result);
    }

    /**
     * WMV/PMP 구분 수정
     */
    @PutMapping("/updateDeliversWmv")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject updateDeliversWmv(@ModelAttribute BookOrderVO vo) throws Exception {
        int count = bookOrderService.updateDeliversWmv(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", count > 0 ? "success" : "fail");
        result.put("message", count > 0 ? "상품구분이 수정되었습니다" : "상품구분 수정에 실패했습니다");

        return new JSONObject(result);
    }

    /**
     * 금액 수정
     */
    @PutMapping("/updateMoney")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject updateMoney(@ModelAttribute BookOrderVO vo) throws Exception {
        bookOrderService.updateMoney1(vo);

        int totalPrice = bookOrderService.getMoneySum(vo);
        vo.setTotalPrice(totalPrice);
        bookOrderService.updateMoney2(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("message", "금액이 수정되었습니다");
        result.put("totalPrice", totalPrice);

        return new JSONObject(result);
    }

    /**
     * 환불 삭제
     */
    @DeleteMapping("/refundDelete")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject refundDelete(@ModelAttribute BookOrderVO vo) throws Exception {
        int count = bookOrderService.refundMoneyDelete(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", count > 0 ? "success" : "fail");
        result.put("message", count > 0 ? "환불이 삭제되었습니다" : "환불 삭제에 실패했습니다");

        return new JSONObject(result);
    }

    /**
     * 송장저장
     */
    @PostMapping("/saveSendNo")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject saveSendNo(@ModelAttribute BookOrderVO vo) throws Exception {
        if ("C".equals(vo.getSType())) {
            bookOrderService.updateSendNo1_COP(vo);
            bookOrderService.updateSendNo2_COP(vo);
        } else {
            bookOrderService.updateSendNo1(vo);
            bookOrderService.updateSendNo2(vo);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("message", "송장번호가 저장되었습니다");

        return new JSONObject(result);
    }

    // =====================================================
    // 교재배송관리 팝업
    // =====================================================

    /**
     * 교재배송관리 팝업 리스트
     */
    @GetMapping("/popPrintView")
    public JSONObject popPrintView(@ModelAttribute BookOrderVO vo) throws Exception {
        List<HashMap<String, Object>> list = bookOrderService.getPopPrintView(vo);
        List<HashMap<String, Object>> infoList = bookOrderService.getPopPrintInfoView(vo);
        List<HashMap<String, Object>> orderInfoList = bookOrderService.getPopPrintOrderInfoView(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("infoList", infoList);
        result.put("orderInfoList", orderInfoList);

        return new JSONObject(result);
    }

    // =====================================================
    // 교재배송 리스트 (직접수령)
    // =====================================================

    /**
     * 교재배송 리스트 (직접수령) 조회
     */
    @GetMapping("/jicList")
    public JSONObject jicList(@ModelAttribute BookOrderVO vo) throws Exception {
        setDefaultDateRange(vo);
        setPagination(vo);

        List<HashMap<String, Object>> list = bookOrderService.getJicOrderListDB(vo);
        int listCount = bookOrderService.getJicOrderListCount(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / vo.getPageUnit()));
        result.put("currentPage", vo.getCurrentPage());

        return new JSONObject(result);
    }

    /**
     * 수령완료 처리
     */
    @PostMapping("/completeReceive")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject completeReceive(@ModelAttribute BookOrderVO vo) throws Exception {
        bookOrderService.updateSendNo3(vo);
        bookOrderService.updateSendNo4(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("message", "수령완료 처리되었습니다");

        return new JSONObject(result);
    }

    // =====================================================
    // 교재매출내역
    // =====================================================

    /**
     * 교재매출내역 리스트 조회
     */
    @GetMapping("/tolList")
    public JSONObject tolList(@ModelAttribute BookOrderVO vo) throws Exception {
        setDefaultDateRange(vo);
        setPagination(vo);

        List<HashMap<String, Object>> list = bookOrderService.getTolOrderListDB(vo);
        int listCount = bookOrderService.getTolOrderListCount(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / vo.getPageUnit()));
        result.put("currentPage", vo.getCurrentPage());

        return new JSONObject(result);
    }

    /**
     * 교재매출내역 2번째 리스트
     */
    @GetMapping("/tolListSecond")
    public JSONObject tolListSecond(@ModelAttribute BookOrderVO vo) throws Exception {
        List<HashMap<String, Object>> list = bookOrderService.getPutLecMstDB(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);

        return new JSONObject(result);
    }

    /**
     * 교재매출내역 엑셀 리스트
     */
    @GetMapping("/tolListExcel")
    public JSONObject tolListExcel(@ModelAttribute BookOrderVO vo) throws Exception {
        setDefaultDateRange(vo);

        List<HashMap<String, Object>> list = bookOrderService.getTolOrderExelListDB(vo);

        HashMap<String, Object> result = new HashMap<>();
        result.put("list", list);

        return new JSONObject(result);
    }

    // =====================================================
    // 유틸리티 메서드
    // =====================================================

    /**
     * 기본 날짜 범위 설정 (6개월 전 ~ 오늘)
     */
    private void setDefaultDateRange(BookOrderVO vo) {
        if (vo.getSdate().isEmpty()) {
            Calendar month6 = Calendar.getInstance();
            month6.add(Calendar.MONTH, -6);
            Date date6 = month6.getTime();
            String sdate = new SimpleDateFormat("yyyyMMdd").format(date6);
            vo.setSdate(sdate);
        }

        if (vo.getEdate().isEmpty()) {
            TimeZone tz = new SimpleTimeZone(9 * 60 * 60 * 1000, "KST");
            TimeZone.setDefault(tz);
            Date d1 = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            String edate = sdf1.format(d1);
            vo.setEdate(edate);
        }

        // URL 디코딩
        try {
            if (vo.getSearchkey() != null && !vo.getSearchkey().isEmpty()) {
                vo.setSearchkey(URLDecoder.decode(vo.getSearchkey(), StandardCharsets.UTF_8.name()));
            }
        } catch (Exception e) {
            // 디코딩 실패 시 원본 유지
        }
    }

    /**
     * 페이징 설정
     */
    private void setPagination(BookOrderVO vo) {
        int currentPage = vo.getCurrentPage() > 0 ? vo.getCurrentPage() : 1;
        int pageRow = vo.getPageUnit() > 0 ? vo.getPageUnit() : pageUnit;

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        vo.setCurrentPage(currentPage);
        vo.setPageUnit(pageRow);
        vo.setStartNo(String.valueOf(startNo));
        vo.setEndNo(String.valueOf(endNo));
    }
}
