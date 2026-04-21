package com.academy.lecture;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.lecture.service.KindService;
import com.academy.lecture.service.KindVO;

@RestController
@RequestMapping("/api/kind")
public class KindApi extends CORSFilter {

	@Value("${pageUnit:10}")
	private int pageUnit;

	private KindService kindservice;

	@Autowired
	public KindApi(KindService kindservice) {
		this.kindservice = kindservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2025.11
	 * @Method 설명 : 카테고리 목록 조회
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/list")
	public JSONObject list(@ModelAttribute KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		/* 페이징 */
		int currentPage = kindVO.getCurrentPage();
		int pageRow = kindVO.getPageRow();
		if(pageRow == 0) pageRow = pageUnit;
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		kindVO.setStartNo(String.valueOf(startNo));
		kindVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		kindVO.setSeqUpdate("N");
		List<HashMap<String, String>> list = kindservice.kindList(kindVO);
		int listCount = kindservice.kindListCount(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getSeqUpdateList
	 * @작성일 : 2025.11
	 * @Method 설명 : 순서 업데이트용 전체 목록 조회
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/seqUpdateList")
	public JSONObject getSeqUpdateList(@ModelAttribute KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		kindVO.setStartNo("0");
		kindVO.setEndNo("1000");
		kindVO.setSeqUpdate("Y");
		List<HashMap<String, String>> list = kindservice.kindList(kindVO);
		int listCount = kindservice.kindListCount(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : seqUpdate
	 * @작성일 : 2025.11
	 * @Method 설명 : 직종 순번 수정
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/seqUpdate")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject seqUpdate(@RequestBody KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		String[] NUM = request.getParameterValues("Num");
		String[] SEQARR = request.getParameterValues("SEQ");
		String[] CODE = request.getParameterValues("CODE_NO");
		String[] CODE_NM = request.getParameterValues("CODE_NM");
		if(SEQARR != null){
			for(int i=0; i<SEQARR.length; i++){
				KindVO updateVO = new KindVO();
				updateVO.setNum(NUM[i]);
				updateVO.setSeq(SEQARR[i]);
				updateVO.setCode(CODE[i]);
				updateVO.setCodeNm(CODE_NM[i]);
				setSessionInfo(updateVO, request);

				kindservice.SeqUpdate(updateVO);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "순서가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : codeCheck
	 * @작성일 : 2025.11
	 * @Method 설명 : 코드 중복 체크
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/codeCheck")
	public JSONObject codeCheck(@ModelAttribute KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		int listCount = kindservice.kindCheck(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("available", listCount == 0);
		result.put("message", listCount > 0 ? "이미 사용중인 코드입니다" : "사용 가능한 코드입니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2025.11
	 * @Method 설명 : 카테고리 등록
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@RequestBody KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		kindservice.kindInsert(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "카테고리가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2025.11
	 * @Method 설명 : 카테고리 상세 조회
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		List<HashMap<String, String>> view = kindservice.kindView(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("view", view);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2025.11
	 * @Method 설명 : 카테고리 수정
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@RequestBody KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		kindservice.kindUpdate(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "카테고리가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2025.11
	 * @Method 설명 : 카테고리 삭제
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		kindservice.kindDelete(kindVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "카테고리가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2025.11
	 * @Method 설명 : 카테고리 다중 삭제
	 * @param kindVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/listDelete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject listDelete(@RequestBody KindVO kindVO, HttpServletRequest request) throws Exception {
		setSessionInfo(kindVO, request);

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		if(DEL_ARR != null){
			for(int i=0; i<DEL_ARR.length; i++){
				KindVO deleteVO = new KindVO();
				deleteVO.setCode(DEL_ARR[i]);
				setSessionInfo(deleteVO, request);
				kindservice.kindDelete(deleteVO);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 카테고리가 삭제되었습니다" : "삭제된 카테고리가 없습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : setSessionInfo
	 * @작성일 : 2025.11
	 * @Method 설명 : 세션 정보 설정
	 * @param kindVO
	 * @param request
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setSessionInfo(KindVO kindVO, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
			if(loginInfo != null) {
				kindVO.setRegId(loginInfo.get("USER_ID"));
				kindVO.setUpdId(loginInfo.get("USER_ID"));
			}
		}
	}

}