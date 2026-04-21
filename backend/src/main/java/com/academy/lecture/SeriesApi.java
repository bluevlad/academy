package com.academy.lecture;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.lecture.service.KindService;
import com.academy.lecture.service.SeriesService;
import com.academy.lecture.service.SeriesVO;

@RestController
@RequestMapping("/api/series")
public class SeriesApi extends CORSFilter {

    private SeriesService seriesService;
    private KindService kindservice;

    @Autowired
    public SeriesApi(SeriesService seriesService, KindService kindservice) {
        this.seriesService = seriesService;
        this.kindservice = kindservice;
    }

    /**
     * @Method Name : list
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 목록 조회
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/list")
    public JSONObject list(@ModelAttribute SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        /* 페이징 */
        int currentPage = seriesVO.getCurrentPage();
        int pageRow = seriesVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        seriesVO.setStartNo(String.valueOf(startNo));
        seriesVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = seriesService.seriesList(seriesVO);
        int listCount = seriesService.seriesListCount(seriesVO);

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
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 상세 조회
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/view")
    public JSONObject view(@ModelAttribute SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        List<HashMap<String, String>> view = seriesService.seriesView(seriesVO);
        List<HashMap<String, String>> cat_list = kindservice.selectKindCode();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);
        result.put("cat_list", cat_list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : getKindCodeList
     * @작성일 : 2015. 05.
     * @Method 설명 : Kind 코드 목록 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/kindCodes")
    public JSONObject getKindCodeList(HttpServletRequest request) throws Exception {
        List<HashMap<String, String>> list = kindservice.selectKindCode();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("cat_list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : codeCheck
     * @작성일 : 2015. 05.
     * @Method 설명 : 코드 중복 체크
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/codeCheck")
    public JSONObject codeCheck(@ModelAttribute SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        int listCount = seriesService.seriesCheck(seriesVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("available", listCount == 0);
        result.put("message", listCount > 0 ? "이미 사용중인 코드입니다" : "사용 가능한 코드입니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : save
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 등록
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping(value="/save")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject save(@RequestBody SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        String[] CAT_CDs = request.getParameterValues("CAT_CD");
        seriesVO.setCatCds(CAT_CDs);

        seriesService.seriesInsert(seriesVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "직렬이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : update
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 수정
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/update")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject update(@RequestBody SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        String[] CAT_CDs = request.getParameterValues("CAT_CD");
        seriesVO.setCatCds(CAT_CDs);

        seriesService.seriesUpdate(seriesVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "직렬이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬 삭제
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/delete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject delete(@RequestBody SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        seriesService.seriesDelete(seriesVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "직렬이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : listDelete
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬 다중 삭제
     * @param seriesVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/listDelete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject listDelete(@RequestBody SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        setSessionInfo(seriesVO, request);

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        seriesVO.setSrsCds(DEL_ARR);

        seriesService.seriesDelete(seriesVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 직렬이 삭제되었습니다" : "삭제된 직렬이 없습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : setSessionInfo
     * @작성일 : 2015. 05.
     * @Method 설명 : 세션 정보 설정
     * @param seriesVO
     * @param request
     * @return void
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void setSessionInfo(SeriesVO seriesVO, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if(loginInfo != null) {
                seriesVO.setRegId(loginInfo.get("USER_ID"));
                seriesVO.setUpdId(loginInfo.get("USER_ID"));
            }
        }
    }

}