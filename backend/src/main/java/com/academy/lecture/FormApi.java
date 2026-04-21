package com.academy.lecture;

import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.academy.lecture.service.FormService;
import com.academy.lecture.service.FormVO;

@RestController
@RequestMapping("/api/form")
public class FormApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

	private FormService formservice;

	@Autowired
	public FormApi(FormService formservice) {
		this.formservice = formservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2025.11
	 * @Method 설명 : 학습형태 목록 조회
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/list")
	public JSONObject list(@ModelAttribute FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

		/* 페이징 */
		int currentPage = formVO.getCurrentPage();
		int pageRow = formVO.getPageRow();
		if(pageRow == 0) pageRow = pageUnit;
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		formVO.setStartNo(String.valueOf(startNo));
		formVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = formservice.formList(formVO);
		int listCount = formservice.formListCount(formVO);

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
	 * @Method 설명 : 학습형태 상세 조회
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

		List<HashMap<String, String>> view = formservice.formView(formVO);

		FormVO codeVO = new FormVO();
		codeVO.setSearchPcode("LEC_FORM");
		codeVO.setSearchCodeIsUse("Y");
		List<HashMap<String, String>> codelist = formservice.getCodeList(codeVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("view", view);
		result.put("codelist", codelist);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : codeCheck
	 * @작성일 : 2025.11
	 * @Method 설명 : 코드 중복 체크
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/codeCheck")
	public JSONObject codeCheck(@ModelAttribute FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

	    int listCount = formservice.formCheck(formVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("available", listCount == 0);
		result.put("message", listCount > 0 ? "이미 사용중인 코드입니다" : "사용 가능한 코드입니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2025.11
	 * @Method 설명 : 학습형태 등록
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@RequestBody FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

		formservice.formInsert(formVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "학습형태가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2025.11
	 * @Method 설명 : 학습형태 수정
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@RequestBody FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

		formservice.formUpdate(formVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "학습형태가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2025.11
	 * @Method 설명 : 학습형태 삭제
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

		formservice.formDelete(formVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "학습형태가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2025.11
	 * @Method 설명 : 학습형태 다중 삭제
	 * @param formVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/listDelete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject listDelete(@RequestBody FormVO formVO, HttpServletRequest request) throws Exception {
		setSessionInfo(formVO, request);

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		if(DEL_ARR != null){
			for(int i=0; i<DEL_ARR.length; i++){
				FormVO deleteVO = new FormVO();
				deleteVO.setCode(DEL_ARR[i]);
				setSessionInfo(deleteVO, request);
				formservice.formDelete(deleteVO);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 학습형태가 삭제되었습니다" : "삭제된 학습형태가 없습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : setSessionInfo
	 * @작성일 : 2025.11
	 * @Method 설명 : 세션 정보 설정
	 * @param formVO
	 * @param request
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setSessionInfo(FormVO formVO, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
			if(loginInfo != null) {
				formVO.setRegId(loginInfo.get("USER_ID"));
				formVO.setUpdId(loginInfo.get("USER_ID"));
			}
		}
	}

}