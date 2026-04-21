package com.academy.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.book.service.BookService;
import com.academy.book.service.BookVO;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;

/**
 * Book API Controller
 * 교재 관리 RESTful API
 */
@RestController
@RequestMapping("/api/book")
public class BookApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private BookService bookService;
    private TeacherService teacherService;

    @Autowired
    public BookApi(BookService bookService, TeacherService teacherService) {
        this.bookService = bookService;
        this.teacherService = teacherService;
    }

    /**
     * @Method Name : list
     * @작성일 : 2024
     * @Method 설명 : 교재 목록 조회
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/list")
    public JSONObject list(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        /* 페이징 */
        int currentPage = bookVO.getCurrentPage();
        int pageRow = bookVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        bookVO.setStartNo(String.valueOf(startNo));
        bookVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        TeacherVO teacherVO = new TeacherVO();
        List<HashMap<String, String>> kindlist = teacherService.getKindList(teacherVO);
        List<HashMap<String, String>> list = bookService.bookList(bookVO);
        int listCount = bookService.bookListCount(bookVO);

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
     * @작성일 : 2024
     * @Method 설명 : 교재 상세 조회
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        List<HashMap<String, String>> view = bookService.bookView(bookVO);
        List<HashMap<String, String>> viewlist = bookService.bookViewList(bookVO);
        String rdelyn = "Y"; // 현재 선택글 삭제가능여부
        String sdelyn = "Y"; // 관련글 삭제 가능여부

        int checkCnt = bookService.bookUseCheck(bookVO);
        if (checkCnt > 0)
            rdelyn = "N";

        BookVO checkVO = new BookVO();
        for (int j = 0; j < viewlist.size(); j++) {
            checkVO.setRscId(viewlist.get(j).get("RSC_ID"));
            checkCnt = bookService.bookUseCheck(checkVO);
            if (checkCnt > 0)
                sdelyn = "N";
        }

        TeacherVO teacherVO = new TeacherVO();
        List<HashMap<String, String>> kindlist = teacherService.getKindList(teacherVO);
        bookVO.setSearchCodeIsUse("Y");
        List<HashMap<String, String>> formlist = bookService.getLearningFormList(bookVO);
        List<HashMap<String, String>> subjectteacherlist = bookService.getCaSubjectTeacherList(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("kindlist", kindlist);
        result.put("formlist", formlist);
        result.put("subjectteacherlist", subjectteacherlist);
        result.put("view", view);
        result.put("viewlist", viewlist);
        result.put("rdelyn", rdelyn);
        result.put("sdelyn", sdelyn);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : writeData
     * @작성일 : 2024
     * @Method 설명 : 교재 등록을 위한 기본 데이터 조회
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/writeData")
    public JSONObject writeData(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        TeacherVO teacherVO = new TeacherVO();
        List<HashMap<String, String>> kindlist = teacherService.getKindList(teacherVO);
        bookVO.setSearchCodeIsUse("Y");
        List<HashMap<String, String>> formlist = bookService.getLearningFormList(bookVO);
        List<HashMap<String, String>> subjectteacherlist = bookService.getCaSubjectTeacherList(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("kindlist", kindlist);
        result.put("formlist", formlist);
        result.put("subjectteacherlist", subjectteacherlist);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : save
     * @작성일 : 2024
     * @Method 설명 : 교재 등록
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping("/save")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject save(@ModelAttribute("BookVO") BookVO bookVO,
            @RequestParam Map<?, ?> commandMap,
            @RequestParam(value = "SUBJECT_SJT_CD", required = false) String[] SUBJECT_SJT_CD_ARR,
            @RequestParam(value = "CATEGORY_CD", required = false) String[] CATEGORY_CD_ARR,
            @RequestParam(value = "LEARNING_CD", required = false) String[] LEARNING_CD_ARR) throws Exception {

        bookVO.setSeq(bookService.getCaBookSeq(bookVO));
        for (int j = 0; j < CATEGORY_CD_ARR.length; j++) {
            bookVO.setCategoryCd(CATEGORY_CD_ARR[j]);

            for (int k = 0; k < LEARNING_CD_ARR.length; k++) {
                bookVO.setLearningCd(LEARNING_CD_ARR[k]);
                String SUBJECT_SJT_CD = "";

                for (int i = 0; i < SUBJECT_SJT_CD_ARR.length; i++) {
                    if (!"".equals(SUBJECT_SJT_CD)) {
                        SUBJECT_SJT_CD += ",";
                    }
                    SUBJECT_SJT_CD += SUBJECT_SJT_CD_ARR[i];
                }
                bookVO.setSubjectSjtCd(SUBJECT_SJT_CD);
                bookVO.setRscId(bookService.getCaBookRscId(bookVO));
                bookService.bookInsert(bookVO);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재가 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : update
     * @작성일 : 2024
     * @Method 설명 : 교재 수정
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping("/update")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject update(@ModelAttribute("BookVO") BookVO bookVO,
            @RequestParam Map<?, ?> commandMap,
            @RequestParam(value = "SUBJECT_SJT_CD", required = false) String[] SUBJECT_SJT_CD_ARR,
            @RequestParam(value = "UPDATE_FLAG", required = false) String updateFlag) throws Exception {

        bookVO.setUpdateFlag(CommonUtil.isNull(updateFlag, ""));
        String SUBJECT_SJT_CD = "";
        for (int i = 0; i < SUBJECT_SJT_CD_ARR.length; i++) {
            if (!"".equals(SUBJECT_SJT_CD)) {
                SUBJECT_SJT_CD += ",";
            }
            SUBJECT_SJT_CD += SUBJECT_SJT_CD_ARR[i];
        }
        bookVO.setSubjectSjtCd(SUBJECT_SJT_CD);
        bookService.bookUpdate(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재가 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2024
     * @Method 설명 : 교재 삭제
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/delete")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject delete(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        bookService.bookDelete(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재가 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : deleteAll
     * @작성일 : 2024
     * @Method 설명 : 교재 관련도서 전체 삭제
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/deleteAll")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject deleteAll(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        bookVO.setGubn("all");
        bookService.bookDelete(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "관련 도서가 모두 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : sellList
     * @작성일 : 2024
     * @Method 설명 : 교재 판매 목록 조회
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/sellList")
    public JSONObject sellList(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        /* 페이징 */
        int currentPage = bookVO.getCurrentPage();
        int pageRow = bookVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        bookVO.setStartNo(String.valueOf(startNo));
        bookVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = bookService.bookSellList(bookVO);
        int listCount = bookService.bookSellListCount(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : sellListExcel
     * @작성일 : 2024
     * @Method 설명 : 교재 판매 목록 엑셀 데이터
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/sellListExcel")
    public JSONObject sellListExcel(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap)
            throws Exception {

        /* 페이징 */
        int currentPage = bookVO.getCurrentPage();
        int pageRow = bookVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        bookVO.setStartNo(String.valueOf(startNo));
        bookVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = bookService.bookSellListExcel(bookVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

}