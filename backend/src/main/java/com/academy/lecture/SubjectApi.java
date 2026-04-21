package com.academy.lecture;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import com.academy.lecture.service.TeacherVO;
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
import com.academy.lecture.service.SubjectService;
import com.academy.lecture.service.SubjectVO;
import com.academy.lecture.service.TeacherService;

@RestController
@RequestMapping("/api/subject")
public class SubjectApi extends CORSFilter {

    private SubjectService subjectservice;
    private TeacherService teacherservice;

    @Autowired
    public SubjectApi(SubjectService subjectservice, TeacherService teacherservice) {
        this.subjectservice = subjectservice;
        this.teacherservice = teacherservice;
    }

    /**
     * @Method Name : list
     * @작성일 : 2025.11
     * @Method 설명 : 과목 목록 조회
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/list")
    public JSONObject list(@ModelAttribute SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        /* 페이징 */
        int currentPage = subjectVO.getCurrentPage();
        int pageRow = subjectVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        subjectVO.setStartNo(String.valueOf(startNo));
        subjectVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = subjectservice.subjectList(subjectVO);
        int listCount = subjectservice.subjectListCount(subjectVO);

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
     * @Method 설명 : 과목 상세 조회
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/view")
    public JSONObject view(@ModelAttribute SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        List<HashMap<String, String>> view = subjectservice.subjectView(subjectVO);

        TeacherVO teacherVO = new TeacherVO();
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);
        result.put("kindlist", kindlist);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : codeCheck
     * @작성일 : 2025.11
     * @Method 설명 : 과목 코드 중복 체크
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/codeCheck")
    public JSONObject codeCheck(@ModelAttribute SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        if (subjectVO.getSubjectNm() != null) {
            subjectVO.setSubjectNm(URLDecoder.decode(subjectVO.getSubjectNm(), "UTF-8"));
        }
        int listCount = subjectservice.subjectCheck(subjectVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("available", listCount == 0);
        result.put("message", listCount > 0 ? "이미 사용중인 과목명입니다" : "사용 가능한 과목명입니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : save
     * @작성일 : 2025.11
     * @Method 설명 : 과목 등록
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping(value="/save")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject save(@RequestBody SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        subjectservice.subjectInsert(subjectVO);

        String[] CATEGORY_CODE = request.getParameterValues("CATEGORY_CODE");
        if(CATEGORY_CODE != null){
            for(int i=0; i<CATEGORY_CODE.length; i++){
                subjectVO.setCategoryCode(CATEGORY_CODE[i]);
                subjectservice.subjectCategoryInsert(subjectVO);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "과목이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : update
     * @작성일 : 2025.11
     * @Method 설명 : 과목 수정
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/update")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject update(@RequestBody SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        subjectservice.subjectUpdate(subjectVO);
        subjectservice.subjectCategoryDelete(subjectVO);

        String[] CATEGORY_CODE = request.getParameterValues("CATEGORY_CODE");
        if(CATEGORY_CODE != null){
            for(int i=0; i<CATEGORY_CODE.length; i++){
                subjectVO.setCategoryCode(CATEGORY_CODE[i]);
                subjectservice.subjectCategoryInsert(subjectVO);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "과목이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2025.11
     * @Method 설명 : 과목 삭제
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/delete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject delete(@RequestBody SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        subjectservice.subjectDelete(subjectVO);
        subjectservice.subjectCategoryDelete(subjectVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "과목이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : listDelete
     * @작성일 : 2025.11
     * @Method 설명 : 과목 다중 삭제
     * @param subjectVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/listDelete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject listDelete(@RequestBody SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        setSessionInfo(subjectVO, request);

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        if(DEL_ARR != null){
            for(int i=0; i<DEL_ARR.length; i++){
                subjectVO.setSubjectCd(DEL_ARR[i]);
                subjectservice.subjectDelete(subjectVO);
                subjectservice.subjectCategoryDelete(subjectVO);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 과목이 삭제되었습니다" : "삭제된 과목이 없습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : setSessionInfo
     * @작성일 : 2025.11
     * @Method 설명 : 세션 정보 설정
     * @param subjectVO
     * @param request
     * @return void
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void setSessionInfo(SubjectVO subjectVO, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if(loginInfo != null) {
                subjectVO.setRegId(loginInfo.get("USER_ID"));
                subjectVO.setUpdId(loginInfo.get("USER_ID"));
            }
        }
    }

}