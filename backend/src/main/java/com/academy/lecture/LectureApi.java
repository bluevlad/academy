package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.academy.book.service.BookVO;
import com.academy.productorder.service.ProductOrderVO;

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
import com.academy.common.service.CmmUseService;
import com.academy.book.service.BookService;
import com.academy.lecture.service.LectureService;
import com.academy.lecture.service.LectureVO;
import com.academy.lecture.service.SubjectService;
import com.academy.lecture.service.SubjectVO;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;
import com.academy.productorder.service.ProductOrderService;

@RestController
@RequestMapping("/api/lecture")
public class LectureApi extends CORSFilter {

	@Value("${pageUnit:10}")
	private int pageUnit;

	private BookService bookservice;
	private LectureService lectureservice;
	private TeacherService teacherservice;
	private CmmUseService cmmUseService;
	@SuppressWarnings("unused")
	private SubjectService subjectService;
	private ProductOrderService productOrderService;

	@Autowired
	public LectureApi(BookService bookservice, LectureService lectureservice,
			TeacherService teacherservice, CmmUseService cmmUseService,
			SubjectService subjectService, ProductOrderService productOrderService) {
		this.bookservice = bookservice;
		this.lectureservice = lectureservice;
		this.teacherservice = teacherservice;
		this.cmmUseService = cmmUseService;
		this.subjectService = subjectService;
		this.productOrderService = productOrderService;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2025.11
	 * @Method 설명 : 단과 강의 목록 조회
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/list")
	public JSONObject list(@ModelAttribute LectureVO lectureVO) throws Exception {

		/* 페이징 */
		int currentPage = lectureVO.getCurrentPage();
		int pageRow = lectureVO.getPageRow();
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		lectureVO.setStartNo(String.valueOf(startNo));
		lectureVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		TeacherVO teacherVO = new TeacherVO();
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");

		BookVO bookVO = new BookVO();

		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(bookVO);

		List<HashMap<String, String>> list = lectureservice.lectureList(lectureVO);
		int listCount = lectureservice.lectureListCount(lectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
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
	 * @Method 설명 : 강의 상세 조회
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/view")
	public JSONObject view(@ModelAttribute LectureVO lectureVO) throws Exception {

		List<HashMap<String, String>> view = lectureservice.lectureView(lectureVO);
		List<HashMap<String, String>> viewlist = lectureservice.lectureViewList(lectureVO);
		List<HashMap<String, String>> viewbooklist = lectureservice.lectureViewBookList(lectureVO);

		TeacherVO teacherVO = new TeacherVO();
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");

		BookVO bookVO = new BookVO();
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(bookVO);

		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(bookVO);
		int lectureOrderCount = lectureservice.lectureDeleteCheck(lectureVO);

		Map<String, String> vo = new HashMap<String, String>();
		vo.put("SYS_CD", "ICON_GUBUN");
		List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("lectureOrderCount", lectureOrderCount);
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
		result.put("subjectteacherlist", subjectteacherlist);
		result.put("view", view);
		result.put("viewlist", viewlist);
		result.put("viewbooklist", viewbooklist);
		result.put("ICON_GUBUNs", ICON_GUBUNs);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : bookList
	 * @작성일 : 2025.11
	 * @Method 설명 : 교재 목록 조회
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/bookList")
	public JSONObject bookList(@ModelAttribute LectureVO lectureVO) throws Exception {

		/* 페이징 */
		int currentPage = lectureVO.getCurrentPage();
		int pageRow = lectureVO.getPageRow();
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		lectureVO.setStartNo(String.valueOf(startNo));
		lectureVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		TeacherVO teacherVO = new TeacherVO();
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");

		BookVO bookVO = new BookVO();

		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(bookVO);

		List<HashMap<String, String>> list = lectureservice.bookList(lectureVO);
		int listCount = lectureservice.bookListCount(lectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : bookView
	 * @작성일 : 2025.11
	 * @Method 설명 : 교재 상세 조회
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/bookView")
	public JSONObject bookView(@ModelAttribute LectureVO lectureVO) throws Exception {

		List<HashMap<String, String>> view = lectureservice.bookView(lectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("view", view);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getCouponList
	 * @작성일 : 2025.11
	 * @Method 설명 : 쿠폰 목록 조회
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/couponList")
	public JSONObject getCouponList(@ModelAttribute LectureVO lectureVO) throws Exception {

		/* 페이징 */
		int currentPage = lectureVO.getCurrentPage();
		int pageRow = 4;
		lectureVO.setPageRow(4);
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		lectureVO.setStartNo(String.valueOf(startNo));
		lectureVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		ProductOrderVO productOrderVO = new ProductOrderVO();

		List<HashMap<String, Object>> list = productOrderService.getTmCouponList(productOrderVO);
		int listCount = productOrderService.getTmCouponCount(productOrderVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getMoCouponList
	 * @작성일 : 2025.11
	 * @Method 설명 : 모바일 쿠폰 목록 조회
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/moCouponList")
	public JSONObject getMoCouponList(@ModelAttribute LectureVO lectureVO) throws Exception {

		/* 페이징 */
		int currentPage = lectureVO.getCurrentPage();
		int pageRow = 4;
		lectureVO.setPageRow(4);
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		lectureVO.setStartNo(String.valueOf(startNo));
		lectureVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		ProductOrderVO productOrderVO = new ProductOrderVO();

		List<HashMap<String, Object>> list = productOrderService.getTmMoCouponList(productOrderVO);
		int listCount = productOrderService.getTmMoCouponCount(productOrderVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : modifyLectureOnOff
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 개설여부 수정
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value = "/onOffStatus")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject modifyLectureOnOff(@RequestBody LectureVO lectureVO) throws Exception {

		String flag = lectureVO.getFlag() != null ? lectureVO.getFlag() : "";
		String flag2 = lectureVO.getFlag2() != null ? lectureVO.getFlag2() : "";

		if (flag.equals("ON")) {
			lectureVO.setFlag("Y");
		} else {
			lectureVO.setFlag("N");
		}

		if (flag2.equals("list")) { // 단과 강의관리 리스트에서 개설여부 수정시
			List<HashMap<String, String>> BridgeLeccode = lectureservice.BridgeLeccode(lectureVO);

			if (BridgeLeccode.size() > 0) {
				for (int i = 0; i < BridgeLeccode.size(); i++) {
					String GET_CODE = String.valueOf(BridgeLeccode.get(i)).substring(9, 19);
					lectureVO.setGetCode(GET_CODE);
					lectureservice.Modify_Lecture_On_Off(lectureVO);
				}
			}
		} else if (flag2.equals("jlist") || flag2.equals("ylist")) { // 종합반,패키지,연회원패키지 강의관리 리스트에서 개설여부 수정시
			lectureservice.Modify_Lecture_On_Off(lectureVO);
		} else { // 단과 강의관리 상세페이지에서 개설여부 수정시
			lectureservice.Modify_Lecture_On_Off(lectureVO);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("flag", flag);
		result.put("message", "개설여부가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2025.11
	 * @Method 설명 : 단과 강의 등록
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject save(@RequestBody LectureVO lectureVO) throws Exception {

		Calendar cal = Calendar.getInstance();
		String[] CATEGORY_CD_ARR = lectureVO.getCategoryCdArr();
		String[] LEARNING_CD_ARR = lectureVO.getLearningCdArr();
		String[] JU_RSC_ID_ARR = lectureVO.getJuRscIdArr();
		String[] BU_RSC_ID_ARR = lectureVO.getBuRscIdArr();
		String[] SU_RSC_ID_ARR = lectureVO.getSuRscIdArr();
		String BRIDGE_LECCODE = "";
		String LECCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeLeccodeSeqList = lectureservice.getBridgeLeccodeSeq(lectureVO);
		if (getBridgeLeccodeSeqList.size() > 0) {
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			lectureVO.setSeq(Integer.parseInt(getBridgeLeccodeSeqList.get(0).get("SEQ")));
		} else {
			SEQ = "1";
			lectureVO.setSeq(1);
		}

		String prefix = "R" + cal.get(Calendar.YEAR);
		lectureVO.setPrefix(prefix);
		List<HashMap<String, String>> getBridgeLeccodeList = lectureservice.getBridgeLeccode(lectureVO);

		if (getBridgeLeccodeList.size() > 0)
			BRIDGE_LECCODE = prefix + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
		else
			BRIDGE_LECCODE = prefix + "00001";
		lectureVO.setBridgeLeccode(BRIDGE_LECCODE.replace(" ", ""));

		prefix = lectureVO.getLecTypeChoice() + cal.get(Calendar.YEAR);
		lectureVO.setPrefix(prefix);
		for (int i = 0; i < CATEGORY_CD_ARR.length; i++) {
			lectureVO.setCategoryCd(CATEGORY_CD_ARR[i]);

			for (int j = 0; j < LEARNING_CD_ARR.length; j++) {
				lectureVO.setLearningCd(LEARNING_CD_ARR[j]);

				List<HashMap<String, String>> getLeccodeList = lectureservice.getLeccode(lectureVO);
				if (getLeccodeList.size() > 0)
					LECCODE = prefix + getLeccodeList.get(0).get("LECCODE");
				else
					LECCODE = prefix + "00001";
				lectureVO.setLeccode(LECCODE.replace(" ", ""));

				lectureservice.lectureInsert(lectureVO);
				lectureVO.setSeq(Integer.parseInt(SEQ));
				lectureservice.lectureBridgeInsert(lectureVO);

				if (JU_RSC_ID_ARR != null) {
					for (int k = 0; k < JU_RSC_ID_ARR.length; k++) {
						lectureVO.setRscId(JU_RSC_ID_ARR[k]);
						lectureVO.setFlag("J");
						lectureservice.lectureBookInsert2(lectureVO);
					}
				}
				if (BU_RSC_ID_ARR != null) {
					for (int k = 0; k < BU_RSC_ID_ARR.length; k++) {
						lectureVO.setRscId(BU_RSC_ID_ARR[k]);
						lectureVO.setFlag("B");
						lectureservice.lectureBookInsert2(lectureVO);
					}
				}
				if (SU_RSC_ID_ARR != null) {
					for (int k = 0; k < SU_RSC_ID_ARR.length; k++) {
						lectureVO.setRscId(SU_RSC_ID_ARR[k]);
						lectureVO.setFlag("S");
						lectureservice.lectureBookInsert2(lectureVO);
					}
				}
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2025.11
	 * @Method 설명 : 단과 강의 수정
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value = "/update")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject update(@RequestBody LectureVO lectureVO) throws Exception {

		String SUBJECT_DESC = lectureVO.getSubjectDesc();
		if (SUBJECT_DESC != null) {
			SUBJECT_DESC = SUBJECT_DESC.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");
			lectureVO.setSubjectDesc(SUBJECT_DESC);
		}

		lectureservice.lectureBookDelete(lectureVO);
		lectureservice.lectureUpdate(lectureVO);

		String[] JU_RSC_ID_ARR = lectureVO.getJuRscIdArr();
		String[] BU_RSC_ID_ARR = lectureVO.getBuRscIdArr();
		String[] SU_RSC_ID_ARR = lectureVO.getSuRscIdArr();

		if (JU_RSC_ID_ARR != null) {
			for (int k = 0; k < JU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(JU_RSC_ID_ARR[k]);
				lectureVO.setFlag("J");
				if ("ALL".equals(lectureVO.getUpdateFlag())) {
					lectureservice.lectureBookInsert(lectureVO);
				} else {
					lectureservice.lectureBookInsert2(lectureVO);
				}
			}
		}
		if (BU_RSC_ID_ARR != null) {
			for (int k = 0; k < BU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(BU_RSC_ID_ARR[k]);
				lectureVO.setFlag("B");
				if ("ALL".equals(lectureVO.getUpdateFlag())) {
					lectureservice.lectureBookInsert(lectureVO);
				} else {
					lectureservice.lectureBookInsert2(lectureVO);
				}
			}
		}
		if (SU_RSC_ID_ARR != null) {
			for (int k = 0; k < SU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(SU_RSC_ID_ARR[k]);
				lectureVO.setFlag("S");
				if ("ALL".equals(lectureVO.getUpdateFlag())) {
					lectureservice.lectureBookInsert(lectureVO);
				} else {
					lectureservice.lectureBookInsert2(lectureVO);
				}
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 삭제
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value = "/delete")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject delete(@RequestBody LectureVO lectureVO) throws Exception {

		lectureservice.lectureDelete(lectureVO);
		lectureservice.lectureBridgeDelete(lectureVO);
		lectureservice.lectureBookDelete(lectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 다중 삭제
	 * @param lectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value = "/listDelete")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject listDelete(@RequestBody LectureVO lectureVO) throws Exception {

		String[] DEL_ARR = lectureVO.getDelArr();
		if (DEL_ARR != null) {
			for (int i = 0; i < DEL_ARR.length; i++) {
				String Lcode = DEL_ARR[i].split("/")[0];
				String Bcode = DEL_ARR[i].split("/")[1];

				lectureVO.setLeccode(Lcode);
				lectureVO.setBridgeLeccode(Bcode);
				lectureVO.setBridgeLec(Bcode);

				lectureservice.lectureDelete(lectureVO);
				lectureservice.lectureBridgeDelete(lectureVO);
				lectureservice.lectureBookDelete(lectureVO);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 강의가 삭제되었습니다" : "삭제된 강의가 없습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

}