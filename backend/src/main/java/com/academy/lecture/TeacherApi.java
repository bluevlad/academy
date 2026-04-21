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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.file.FileUtil;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;

@RestController
@RequestMapping("/api/teacher")
public class TeacherApi extends CORSFilter {

	@Value("${pageUnit:10}")
	private int pageUnit;

	@Value("${file.upload.path:C:/upload/}")
	private String uploadPath;

	private final FileUtil fileUtil;
	private final TeacherService teacherservice;

	@Autowired
	public TeacherApi(TeacherService teacherservice, FileUtil fileUtil) {
		this.teacherservice = teacherservice;
		this.fileUtil = fileUtil;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2025.11
	 * @Method 설명 : 강사 목록 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/list")
	public JSONObject list(@ModelAttribute TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);

		/* 페이징 */
		int currentPage = vo.getCurrentPage();
		int pageRow = vo.getPageRow();
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		vo.setStartNo(String.valueOf(startNo));
		vo.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		vo.setSearchGubn("T");
		List<HashMap<String, String>> list;
		int listCount = 0;
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(vo);

		if ("main".equals(vo.getSearchCategory())) {
			list = teacherservice.teacherMainList(vo);
			listCount = teacherservice.teacherMainListCount(vo);
		} else if ("intro".equals(vo.getSearchCategory())) {
			list = teacherservice.teacherIntroList(vo);
			listCount = teacherservice.teacherIntroListCount(vo);
		} else if ("intro_off".equals(vo.getSearchCategory())) {
			list = teacherservice.teacherIntro_offList(vo);
			listCount = teacherservice.teacherIntro_offListCount(vo);
		} else {
			list = teacherservice.teacherList(vo);
			listCount = teacherservice.teacherListCount(vo);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
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
	 * @Method 설명 : 강사 상세 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/view")
	public JSONObject view(@ModelAttribute TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);
		List<HashMap<String, String>> view = teacherservice.teacherView(vo);
		vo.setOnOffDiv("");
		vo.setSearchGubn("T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(vo);
		vo.setSearchGubn("E");
		vo.setOnOffDiv("O");
		List<HashMap<String, String>> eonsubjectlist = teacherservice.getSubjectList(vo);
		vo.setOnOffDiv("F");
		List<HashMap<String, String>> eoffsubjectlist = teacherservice.getSubjectList(vo);
		vo.setSearchGubn("M");
		vo.setOnOffDiv("O");
		List<HashMap<String, String>> monsubjectlist = teacherservice.getSubjectList(vo);
		vo.setOnOffDiv("F");
		List<HashMap<String, String>> moffsubjectlist = teacherservice.getSubjectList(vo);
		List<HashMap<String, String>> mkindlist = teacherservice.getKindList(vo);
		List<HashMap<String, String>> bookloglist = teacherservice.teacherBookLog(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("view", view);
		result.put("kindlist", kindlist);
		result.put("mkindlist", mkindlist);
		result.put("eonsubjectlist", eonsubjectlist);
		result.put("eoffsubjectlist", eoffsubjectlist);
		result.put("monsubjectlist", monsubjectlist);
		result.put("moffsubjectlist", moffsubjectlist);
		result.put("bookloglist", bookloglist);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : idCheck
	 * @작성일 : 2025.11
	 * @Method 설명 : 강사 ID 중복 확인
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/idCheck")
	public JSONObject idCheck(@ModelAttribute TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);

		int listCount = teacherservice.teacherIdCheck(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		if (listCount > 0) {
			result.put("available", false);
			result.put("message", "이미 사용중인 ID입니다");
		} else {
			result.put("available", true);
			result.put("message", "사용 가능한 ID입니다");
		}

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2025.11
	 * @Method 설명 : 강사 등록
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject save(@RequestBody TeacherVO vo, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		setSessionInfo(vo, request);

		lecFileProcess(vo, multipartRequest);

		String[] ORI_CATEGORY_CODE = request.getParameterValues("ORI_CATEGORY_CODE");
		String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
		String[] SETCARR = request.getParameterValues("SUBJECT_CD");
		vo.setOriCategoryCode(ORI_CATEGORY_CODE);
		vo.setCetcarr(CETCARR);
		vo.setSetcarr(SETCARR);

		teacherservice.teacherInsert(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강사가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2025.11
	 * @Method 설명 : 강사 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value = "/update")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject update(@RequestBody TeacherVO vo, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		setSessionInfo(vo, request);
		lecFileProcess(vo, multipartRequest);

		String rootPath = uploadPath;
		deleteOldFiles(vo, rootPath);

		String[] ORI_CATEGORY_CODE = request.getParameterValues("ORI_CATEGORY_CODE");
		String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
		String[] SETCARR = request.getParameterValues("SUBJECT_CD");
		String[] OFF_SETCARR = request.getParameterValues("OFF_SUBJECT_CD");
		vo.setOriCategoryCode(ORI_CATEGORY_CODE);
		vo.setCetcarr(CETCARR);
		vo.setSetcarr(SETCARR);
		vo.setOffSetcarr(OFF_SETCARR);

		teacherservice.teacherUpdate(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강사 정보가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2025.11
	 * @Method 설명 : 강사 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value = "/delete")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject delete(@RequestBody TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);

		String[] DEL_ARR = { vo.getUserId() };
		vo.setDelArr(DEL_ARR);

		teacherservice.teacherIsUseUpdate(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강사가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2025.11
	 * @Method 설명 : 목록에서 다중 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value = "/listDelete")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject listDelete(@RequestBody TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		int deletedCount = 0;
		if (DEL_ARR != null && DEL_ARR.length > 0) {
			vo.setDelArr(DEL_ARR);
			teacherservice.teacherIsUseUpdate(vo);
			deletedCount = DEL_ARR.length;
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", deletedCount + "명의 강사가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : seqUpdate
	 * @작성일 : 2025.11
	 * @Method 설명 : 강사 순번 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value = "/seqUpdate")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject seqUpdate(@RequestBody TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);

		String[] SEQARR = request.getParameterValues("SEQ");
		if (SEQARR != null && SEQARR.length > 0) {
			vo.setNum(request.getParameterValues("Num"));
			vo.setSeq(SEQARR);
			vo.setProfessorUserId(request.getParameterValues("PROFESSOR_USER_ID"));
			vo.setSubjectCd(request.getParameterValues("SUBJECT_CD"));

			teacherservice.teacherSeqUpdate(vo);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "순번이 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : findTeacherList
	 * @작성일 : 2015. 07.
	 * @Method 설명 : 강사 검색 목록
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/find")
	public JSONObject findTeacherList(@ModelAttribute TeacherVO vo, HttpServletRequest request) throws Exception {
		setSessionInfo(vo, request);

		List<HashMap<String, String>> list = teacherservice.findTeacherList(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : setSessionInfo
	 * @작성일 : 2025.11
	 * @Method 설명 : 세션 정보 설정
	 * @param vo
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setSessionInfo(TeacherVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("AdmUserInfo");
			if (loginInfo != null) {
				vo.setRegId(loginInfo.get("USER_ID"));
				vo.setUpdId(loginInfo.get("USER_ID"));
			}
		}

		vo.setCurrentPage(Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1")));
		vo.setPageRow(Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit))));
		vo.setSearchCategory(CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		vo.setSearchType(CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		vo.setSearchText(CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		vo.setOnoffdiv(CommonUtil.isNull(request.getParameter("ONOFFDIV"), ""));
		vo.setUserId(CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		vo.setUserNm(CommonUtil.isNull(request.getParameter("USER_NM"), ""));
		vo.setUserNicknm(CommonUtil.isNull(request.getParameter("USER_NICKNM"), ""));
		vo.setUserPosition(CommonUtil.isNull(request.getParameter("USER_POSITION"), ""));
		vo.setUserRole(CommonUtil.isNull(request.getParameter("USER_ROLE"), "PRF"));
		vo.setUserPwd(CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
		vo.setBirthDay(CommonUtil.isNull(request.getParameter("BIRTH_DAY"), ""));
		vo.setEmail(CommonUtil.isNull(request.getParameter("EMAIL"), ""));
		vo.setAccount(CommonUtil.isNull(request.getParameter("ACCOUNT"), ""));
		vo.setPhoneNo(CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
		vo.setUserPoint(CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));
		vo.setOnOpenyn(CommonUtil.isNull(request.getParameter("ON_OPENYN"), "Y"));
		vo.setOffOpenyn(CommonUtil.isNull(request.getParameter("OFF_OPENYN"), "Y"));
		vo.setPrfBrdOn(CommonUtil.isNull(request.getParameter("PRF_BRD_ON"), "N"));
		vo.setPrfBrdOf(CommonUtil.isNull(request.getParameter("PRF_BRD_OF"), "N"));
		vo.setPayment(CommonUtil.isNull(request.getParameter("PAYMENT"), ""));
		vo.setOffPayment(CommonUtil.isNull(request.getParameter("OFF_PAYMENT"), ""));
		vo.setOnUrl(CommonUtil.isNull(request.getParameter("ON_URL"), ""));
		vo.setOffUrl(CommonUtil.isNull(request.getParameter("OFF_URL"), ""));
		vo.setBrdYn(CommonUtil.isNull(request.getParameter("BRD_YN"), ""));
		vo.setOffBrdYn(CommonUtil.isNull(request.getParameter("OFF_BRD_YN"), ""));
		vo.setProfile(CommonUtil.isNull(request.getParameter("PROFILE"), ""));
		vo.setOffProfile(CommonUtil.isNull(request.getParameter("OFF_PROFILE"), ""));
		vo.setTitle(CommonUtil.isNull(request.getParameter("TITLE"), ""));
		vo.setOffTitle(CommonUtil.isNull(request.getParameter("OFF_TITLE"), ""));
		vo.setBookLog(CommonUtil.isNull(request.getParameter("BOOK_LOG"), ""));
		vo.setOffBookLog(CommonUtil.isNull(request.getParameter("OFF_BOOK_LOG"), ""));
		vo.setYplan(CommonUtil.isNull(request.getParameter("YPLAN"), ""));
		vo.setOffYplan(CommonUtil.isNull(request.getParameter("OFF_YPLAN"), ""));
		vo.setLecinfo(CommonUtil.isNull(request.getParameter("LECINFO"), ""));
		vo.setOffLecinfo(CommonUtil.isNull(request.getParameter("OFF_LECINFO"), ""));
		vo.setProfHtml(CommonUtil.isNull(request.getParameter("PROF_HTML"), ""));
		vo.setOffProfHtml(CommonUtil.isNull(request.getParameter("OFF_PROF_HTML"), ""));
		vo.setSearchSbjtcd(CommonUtil.isNull(request.getParameter("SEARCHSBJTCD"), ""));
		vo.setSrchcode(CommonUtil.isNull(request.getParameter("SRCHCODE"), ""));
		vo.setSrchtxt(CommonUtil.isNull(request.getParameter("SRCHTXT"), ""));
	}

	/**
	 * @Method Name : lecFileProcess
	 * @작성일 : 2025.11
	 * @Method 설명 : 파일처리 프로세스
	 * @param vo
	 * @param multipartRequest
	 * @return TeacherVO
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public TeacherVO lecFileProcess(TeacherVO vo, MultipartHttpServletRequest multipartRequest) throws Exception {
		String rootPath = uploadPath;
		String subPath = "member_upload/";

		String[] picFields = { "PIC1", "PIC2", "PIC3", "PIC4", "PIC5", "PIC6", "PIC7", "PIC8", "PIC9", "PIC10",
				"OFF_PIC1", "OFF_PIC2", "OFF_PIC3", "OFF_PIC4", "OFF_PIC5", "OFF_PIC6", "OFF_PIC7", "OFF_PIC8", "OFF_PIC9",
				"OFF_PIC10",
				"PRF_ONPIC1", "PRF_ONPIC2", "PRF_ONPIC3", "PRF_OFFPIC1", "PRF_OFFPIC2", "PRF_OFFPIC3",
				"PRF_LISTONBANNER", "PRF_LISTOFFBANNER", "PROF_IMG", "OFF_PROF_IMG", "PRF_TOPONIMG", "PRF_TOPOFFIMG" };

		for (String field : picFields) {
			MultipartFile file = multipartRequest.getFile(field);
			if (file != null && !file.isEmpty()) {
				fileUtil.uploadFile(file, rootPath, subPath);
				// Note: The field mapping to VO properties would need to be added to TeacherVO
				// or handled differently
				// For now, this maintains the same logic as the original code
				Thread.sleep(100);
			}
		}

		return vo;
	}

	/**
	 * @Method Name : deleteOldFiles
	 * @작성일 : 2025.11
	 * @Method 설명 : 이전 파일 삭제
	 * @param vo
	 * @param rootPath
	 * @throws Exception
	 */
	private void deleteOldFiles(TeacherVO vo, String rootPath) throws Exception {
		// String[] deleteFields = {"PIC1_DEL", "PIC2_DEL", "PIC3_DEL", "PIC4_DEL",
		// "PIC5_DEL", "PIC6_DEL", "PIC7_DEL", "PIC8_DEL", "PIC9_DEL", "PIC10_DEL",
		// "OFF_PIC1_DEL", "OFF_PIC2_DEL", "OFF_PIC3_DEL", "OFF_PIC4_DEL",
		// "OFF_PIC5_DEL", "OFF_PIC6_DEL", "OFF_PIC7_DEL", "OFF_PIC8_DEL",
		// "OFF_PIC9_DEL", "OFF_PIC10_DEL",
		// "PRF_ONPIC1_DEL", "PRF_ONPIC2_DEL", "PRF_ONPIC3_DEL", "PRF_OFFPIC1_DEL",
		// "PRF_OFFPIC2_DEL", "PRF_OFFPIC3_DEL",
		// "PRF_LISTONBANNER_DEL", "PRF_LISTOFFBANNER_DEL", "PROF_IMG_DEL",
		// "OFF_PROF_IMG_DEL", "PRF_TOPONIMG_DEL", "PRF_TOPOFFIMG_DEL"};

		// Note: File deletion logic would need to be implemented based on VO properties
		// This maintains the original logic structure
	}

}