package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
import com.academy.book.service.BookService;
import com.academy.lecture.service.LectureMstService;
import com.academy.lecture.service.LectureVO;
import com.academy.lecture.service.SubjectVO;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;
import com.academy.book.service.BookVO;

@RestController
@RequestMapping("/api/lecturemst")
public class LectureMstApi extends CORSFilter {

	private final BookService bookservice;
	private final LectureMstService lecturemstservice;
	private final TeacherService teacherservice;

	@Autowired
	public LectureMstApi(BookService bookservice, LectureMstService lecturemstservice,
			TeacherService teacherservice) {
		this.bookservice = bookservice;
		this.lecturemstservice = lecturemstservice;
		this.teacherservice = teacherservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 목록 조회
	 * @param LectureVO
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
		BookVO bookVO = new BookVO();
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(bookVO);

		List<HashMap<String, String>> list = lecturemstservice.lecturemstList(lectureVO);
		int listCount = lecturemstservice.lecturemstListCount(lectureVO);

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
	 * @Method Name : getSubjectTeacherList
	 * @작성일 : 2025.11
	 * @Method 설명 : 과목(강사) 검색 목록
	 * @param LectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/subjectTeacherList")
	public JSONObject getSubjectTeacherList(@ModelAttribute LectureVO lectureVO) throws Exception {

		BookVO bookVO = new BookVO();

		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(bookVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("subjectteacherlist", subjectteacherlist);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 상세 조회
	 * @param LectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/view")
	public JSONObject view(@ModelAttribute LectureVO lectureVO) throws Exception {

		BookVO bookVO = new BookVO();

		List<HashMap<String, String>> view = lecturemstservice.lectureView(lectureVO);
		List<HashMap<String, String>> viewbooklist = lecturemstservice.lectureViewBookList(lectureVO);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(bookVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("subjectteacherlist", subjectteacherlist);
		result.put("view", view);
		result.put("viewbooklist", viewbooklist);
		result.put("rdelyn", "Y");
		result.put("sdelyn", "Y");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getDataViewList
	 * @작성일 : 2025.11
	 * @Method 설명 : 데이터 뷰 리스트 조회
	 * @param LectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value = "/dataViewList")
	public JSONObject getDataViewList(@ModelAttribute LectureVO lectureVO) throws Exception {

		List<HashMap<String, String>> memolist = lecturemstservice.lectureDataMemoViewList(lectureVO);
		List<HashMap<String, String>> list = lecturemstservice.lectureDataViewList(lectureVO);
		List<HashMap<String, String>> wmv = lecturemstservice.lectureWMV(lectureVO);
		List<HashMap<String, String>> down_count = lecturemstservice.lectureDown_Count(lectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("memolist", memolist);
		result.put("list", list);
		result.put("wmv", wmv);
		result.put("down_count", down_count);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 등록
	 * @param LectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject save(@RequestBody LectureVO lectureVO) throws Exception {

		Calendar cal = Calendar.getInstance();
		// String[] CATEGORY_CD_ARR = lectureVO.getCategoryCdArr();
		// String[] LEARNING_CD_ARR = lectureVO.getLearningCdArr();
		String[] JU_RSC_ID_ARR = lectureVO.getJuRscIdArr();
		String[] BU_RSC_ID_ARR = lectureVO.getBuRscIdArr();
		String[] SU_RSC_ID_ARR = lectureVO.getSuRscIdArr();
		String BRIDGE_MSTCODE = "";
		String MSTCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeMstcodeSeqList = lecturemstservice.getBridgeMstcodeSeq(lectureVO);
		if (getBridgeMstcodeSeqList.size() > 0) {
			SEQ = getBridgeMstcodeSeqList.get(0).get("SEQ");
			lectureVO.setJseq(getBridgeMstcodeSeqList.get(0).get("SEQ"));
		} else {
			SEQ = "1";
			lectureVO.setJseq("1");
		}

		String prefix = "R" + cal.get(Calendar.YEAR);
		lectureVO.setPrefix(prefix);
		List<HashMap<String, String>> getBridgeMstcodeList = lecturemstservice.getBridgeMstcode(lectureVO);

		if (getBridgeMstcodeList.size() > 0)
			BRIDGE_MSTCODE = prefix + getBridgeMstcodeList.get(0).get("BRIDGE_MSTCODE");
		else
			BRIDGE_MSTCODE = prefix + "00001";
		lectureVO.setBridgeLeccode(BRIDGE_MSTCODE.replace(" ", ""));

		prefix = "M" + cal.get(Calendar.YEAR);
		lectureVO.setPrefix(prefix);

		List<HashMap<String, String>> getLeccodeList = lecturemstservice.getMstcode(lectureVO);
		if (getLeccodeList.size() > 0)
			MSTCODE = prefix + getLeccodeList.get(0).get("MSTCODE");
		else
			MSTCODE = prefix + "00001";
		lectureVO.setMstcode(MSTCODE.replace(" ", ""));

		lecturemstservice.lecturemstInsert(lectureVO);
		lectureVO.setJseq(SEQ);
		lecturemstservice.lectureBridgeInsert(lectureVO);

		if (JU_RSC_ID_ARR != null) {
			for (int k = 0; k < JU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(JU_RSC_ID_ARR[k]);
				lectureVO.setFlag("J");
				lecturemstservice.lectureBookInsert2(lectureVO);
			}
		}
		if (BU_RSC_ID_ARR != null) {
			for (int k = 0; k < BU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(BU_RSC_ID_ARR[k]);
				lectureVO.setFlag("B");
				lecturemstservice.lectureBookInsert2(lectureVO);
			}
		}
		if (SU_RSC_ID_ARR != null) {
			for (int k = 0; k < SU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(SU_RSC_ID_ARR[k]);
				lectureVO.setFlag("S");
				lecturemstservice.lectureBookInsert2(lectureVO);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 등록되었습니다");
		result.put("MSTCODE", MSTCODE);
		result.put("BRIDGE_MSTCODE", BRIDGE_MSTCODE);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 수정
	 * @param LectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value = "/update")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject update(@RequestBody LectureVO lectureVO) throws Exception {

		lecturemstservice.lectureBookDelete(lectureVO);
		lecturemstservice.lecturemstUpdate(lectureVO);

		String[] JU_RSC_ID_ARR = lectureVO.getJuRscIdArr();
		String[] BU_RSC_ID_ARR = lectureVO.getBuRscIdArr();
		String[] SU_RSC_ID_ARR = lectureVO.getSuRscIdArr();

		if (JU_RSC_ID_ARR != null) {
			for (int k = 0; k < JU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(JU_RSC_ID_ARR[k]);
				lectureVO.setFlag("J");
				if ("ALL".equals(lectureVO.getUpdateFlag())) {
					lecturemstservice.lectureBookInsert(lectureVO);
				} else {
					lecturemstservice.lectureBookInsert2(lectureVO);
				}
			}
		}
		if (BU_RSC_ID_ARR != null) {
			for (int k = 0; k < BU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(BU_RSC_ID_ARR[k]);
				lectureVO.setFlag("B");
				if ("ALL".equals(lectureVO.getUpdateFlag())) {
					lecturemstservice.lectureBookInsert(lectureVO);
				} else {
					lecturemstservice.lectureBookInsert2(lectureVO);
				}
			}
		}
		if (SU_RSC_ID_ARR != null) {
			for (int k = 0; k < SU_RSC_ID_ARR.length; k++) {
				lectureVO.setRscId(SU_RSC_ID_ARR[k]);
				lectureVO.setFlag("S");
				if ("ALL".equals(lectureVO.getUpdateFlag())) {
					lecturemstservice.lectureBookInsert(lectureVO);
				} else {
					lecturemstservice.lectureBookInsert2(lectureVO);
				}
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 삭제
	 * @param LectureVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value = "/delete")
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public JSONObject delete(@RequestBody LectureVO lectureVO) throws Exception {

		lecturemstservice.lectureDelete(lectureVO);
		lecturemstservice.lectureBridgeDelete(lectureVO);
		lecturemstservice.lectureBookDelete(lectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

}