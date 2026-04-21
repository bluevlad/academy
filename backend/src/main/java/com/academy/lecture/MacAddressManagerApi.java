package com.academy.lecture;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.lecture.service.MacAddressManagerService;
import com.academy.lecture.service.MacAddressManagerVO;

@RestController
@RequestMapping("/api/macaddressmanager")
public class MacAddressManagerApi extends CORSFilter {

    private MacAddressManagerService macaddressmanagerservice;

    @Autowired
    public MacAddressManagerApi(MacAddressManagerService macaddressmanagerservice) {
        this.macaddressmanagerservice = macaddressmanagerservice;
    }

    /**
     * @Method Name : list
     * @작성일 : 2025.11
     * @Method 설명 : MAC 주소 관리 목록 조회
     * @param macAddressManagerVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/list")
    public JSONObject list(@ModelAttribute MacAddressManagerVO macAddressManagerVO, HttpServletRequest request) throws Exception {
        setSessionInfo(macAddressManagerVO, request);

        /* 페이징 */
        int currentPage = macAddressManagerVO.getCurrentPage();
        int pageRow = macAddressManagerVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        macAddressManagerVO.setStartNo(String.valueOf(startNo));
        macAddressManagerVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = macaddressmanagerservice.macaddressmanagerList(macAddressManagerVO);
        int listCount = macaddressmanagerservice.macaddressmanagerListCount(macAddressManagerVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : view
     * @작성일 : 2025.11
     * @Method 설명 : MAC 주소 상세 조회
     * @param macAddressManagerVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/view")
    public JSONObject view(@ModelAttribute MacAddressManagerVO macAddressManagerVO, HttpServletRequest request) throws Exception {
        setSessionInfo(macAddressManagerVO, request);

        List<HashMap<String, String>> view = macaddressmanagerservice.macaddressView(macAddressManagerVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : deviceList
     * @작성일 : 2025.11
     * @Method 설명 : 디바이스 목록 조회
     * @param macAddressManagerVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/deviceList")
    public JSONObject deviceList(@ModelAttribute MacAddressManagerVO macAddressManagerVO, HttpServletRequest request) throws Exception {
        setSessionInfo(macAddressManagerVO, request);

        List<HashMap<String, String>> list = macaddressmanagerservice.devicelist(macAddressManagerVO);
        List<HashMap<String, String>> view = macaddressmanagerservice.macaddressView(macAddressManagerVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("view", view);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : updateMacAddress
     * @작성일 : 2025.11
     * @Method 설명 : MAC 주소 업데이트
     * @param macAddressManagerVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/update")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject updateMacAddress(@RequestBody MacAddressManagerVO macAddressManagerVO, HttpServletRequest request) throws Exception {
        setSessionInfo(macAddressManagerVO, request);

        macaddressmanagerservice.macaddressmanagerUpdate(macAddressManagerVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "MAC 주소가 업데이트되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : resetMobile
     * @작성일 : 2025.11
     * @Method 설명 : 모바일 초기화
     * @param macAddressManagerVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/resetMobile")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject resetMobile(@RequestBody MacAddressManagerVO macAddressManagerVO, HttpServletRequest request) throws Exception {
        setSessionInfo(macAddressManagerVO, request);

        macaddressmanagerservice.macaddressmanagerUpdate1(macAddressManagerVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "모바일이 초기화되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : setSessionInfo
     * @작성일 : 2025.11
     * @Method 설명 : 세션 정보 설정
     * @param macAddressManagerVO
     * @param request
     * @return void
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void setSessionInfo(MacAddressManagerVO macAddressManagerVO, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if(loginInfo != null) {
                macAddressManagerVO.setRegId(loginInfo.get("USER_ID"));
                macAddressManagerVO.setUpdId(loginInfo.get("USER_ID"));
            }
        }
    }

}