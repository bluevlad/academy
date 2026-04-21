package com.academy.productorder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.academy.productorder.service.CouponMngService;

/**
 * CouponMng API Controller
 * 쿠폰 관리 RESTful API
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponMngApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private CouponMngService couponMngService;

    @Autowired
    public CouponMngApi(CouponMngService couponMngService) {
        this.couponMngService = couponMngService;
    }

    /**
     * 쿠폰 목록 조회
     */
    @GetMapping("/list")
    public JSONObject list(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> list = couponMngService.getCouponList(params);
        int listCount = couponMngService.getCouponListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 상세 조회
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        HashMap<String, String> view = couponMngService.getCouponOne(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 등록을 위한 기본 데이터
     */
    @GetMapping("/writeData")
    public JSONObject writeData(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        Calendar month = Calendar.getInstance();
        Date date = month.getTime();
        String day = new SimpleDateFormat("yyMMdd").format(date);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("day", day);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 등록
     */
    @PostMapping("/insert")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject insert(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        couponMngService.insertCoupon(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "쿠폰이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 수정
     */
    @PutMapping("/update")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject update(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        couponMngService.updateCoupon(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "쿠폰이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 발급 수강생 리스트
     */
    @GetMapping("/userList")
    public JSONObject userList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> list = couponMngService.getCouponUserList(params);
        int listCount = couponMngService.getCouponUserListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 제휴사 수강권/쿠폰 리스트
     */
    @GetMapping("/coopLectureList")
    public JSONObject coopLectureList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> coopList = couponMngService.getCoopList(params);
        List<HashMap<String, String>> list = couponMngService.getCoopLectureList(params);
        int listCount = couponMngService.getCoopLectureListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("coop_list", coopList);
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 제휴사 쿠폰 발급 리스트
     */
    @GetMapping("/coopCouponList")
    public JSONObject coopCouponList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> list = couponMngService.getCoopCouponList(params);
        int listCount = couponMngService.getCoopCouponListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 제휴사 쿠폰 등록
     */
    @PostMapping("/insertCoopCoupon")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject insertCoopCoupon(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        couponMngService.insertCoopCoupon(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "제휴사 쿠폰이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 제휴사 쿠폰 삭제
     */
    @DeleteMapping("/deleteCoopCoupon")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject deleteCoopCoupon(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        couponMngService.deleteCoopCoupon(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "제휴사 쿠폰이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 공무원 쿠폰 사용 현황
     */
    @GetMapping("/couponOrderList")
    public JSONObject couponOrderList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request)
            throws Exception {
        setParam(params, request);

        Calendar month = Calendar.getInstance();
        Date date = month.getTime();
        String day = new SimpleDateFormat("yyyyMMdd").format(date);

        if (CommonUtil.isNull(params.get("EDATE"), "").equals("")) {
            params.put("EDATE", day);
        }

        List<HashMap<String, String>> list = couponMngService.getCouponOrderList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 파라미터 SETTING
     */
    private void setParam(HashMap<String, String> params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("AdmUserInfo");
            if (loginInfo != null) {
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
            }
        }

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit)));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));

        params.put("CCODE", CommonUtil.isNull(request.getParameter("CCODE"), ""));
        params.put("CNAME", CommonUtil.isNull(request.getParameter("CNAME"), ""));
        params.put("CCONTENT", CommonUtil.isNull(request.getParameter("CCONTENT"), ""));
        params.put("REGTYPE", CommonUtil.isNull(request.getParameter("REGTYPE"), ""));
        params.put("REGPRICE", CommonUtil.isNull(request.getParameter("REGPRICE"), ""));
        params.put("EXPDATES", CommonUtil.isNull(request.getParameter("EXPDATES"), ""));
        params.put("EXPDATEE", CommonUtil.isNull(request.getParameter("EXPDATEE"), ""));
        params.put("EXPDAY", CommonUtil.isNull(request.getParameter("EXPDAY"), ""));
        params.put("TCLASS", CommonUtil.isNull(request.getParameter("TCLASS"), ""));
        params.put("TCLASSCAT", CommonUtil.isNull(request.getParameter("TCLASSCAT"), ""));
        params.put("PUB_COUPON_GUBUN", CommonUtil.isNull(request.getParameter("PUB_COUPON_GUBUN"), ""));
        params.put("TERM", CommonUtil.isNull(request.getParameter("TERM"), ""));
        params.put("DAN_JONG", CommonUtil.isNull(request.getParameter("DAN_JONG"), ""));
        params.put("DAN_MENU", CommonUtil.isNull(request.getParameter("DAN_MENU"), ""));
        params.put("SUBJECT", CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
        params.put("TEACHER", CommonUtil.isNull(request.getParameter("TEACHER"), ""));
        params.put("PRICE", CommonUtil.isNull(request.getParameter("PRICE"), ""));
        params.put("ADD_FLAG", CommonUtil.isNull(request.getParameter("ADD_FLAG"), ""));
        params.put("IS_USE", CommonUtil.isNull(request.getParameter("IS_USE"), ""));
        params.put("PTYPE", CommonUtil.isNull(request.getParameter("PTYPE"), ""));
        params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
        params.put("RTYPE", CommonUtil.isNull(request.getParameter("RTYPE"), ""));
        params.put("CTYPE", CommonUtil.isNull(request.getParameter("CTYPE"), ""));
        params.put("COOP_CD", CommonUtil.isNull(request.getParameter("COOP_CD"), ""));
        params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE"), ""));
        params.put("COUPON_NM", CommonUtil.isNull(request.getParameter("COUPON_NM"), ""));
        params.put("COUPON_CNT", CommonUtil.isNull(request.getParameter("COUPON_CNT"), ""));
        params.put("ST_DT", CommonUtil.isNull(request.getParameter("ST_DT"), ""));
        params.put("ED_DT", CommonUtil.isNull(request.getParameter("ED_DT"), ""));
        params.put("S_COOP_CD", CommonUtil.isNull(request.getParameter("S_COOP_CD"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
    }
}