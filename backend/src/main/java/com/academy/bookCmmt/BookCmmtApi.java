package com.academy.bookCmmt;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.book.service.BookService;
import com.academy.bookCmmt.service.BookCmmtService;
import com.academy.bookCmmt.service.BookCmmtVO;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;

/**
 * BookCmmt API Controller
 * 교재 구매 후기 관리 RESTful API
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 */
@RestController
@RequestMapping("/api/bookCmmt")
public class BookCmmtApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BookCmmtService bookCmmtService;
    private final TeacherService teacherService;
    private final BookService bookService;

    @Autowired
    public BookCmmtApi(BookCmmtService bookCmmtService, TeacherService teacherService, BookService bookService) {
        this.bookCmmtService = bookCmmtService;
        this.teacherService = teacherService;
        this.bookService = bookService;
    }

    /**
     * @Method Name : list
     * @작성일 : 2025.11
     * @Method 설명 : 교재 구매 후기 목록 조회
     * @param bookCmmtVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/list")
    public JSONObject list(@ModelAttribute BookCmmtVO bookCmmtVO, HttpServletRequest request) throws Exception {
        setSessionInfo(bookCmmtVO, request);

        /* 페이징 */
        int currentPage = bookCmmtVO.getCurrentPage();
        int pageRow = bookCmmtVO.getPageRow();
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        bookCmmtVO.setStartNo(String.valueOf(startNo));
        bookCmmtVO.setEndNo(String.valueOf(endNo));
        /* 페이징 */

        TeacherVO teacherVO = new TeacherVO();
        List<HashMap<String, String>> kindlist = teacherService.getKindList(teacherVO);

        List<HashMap<String, Object>> list = bookCmmtService.bookCmmtList(bookCmmtVO);
        int listCount = bookCmmtService.bookCmmtListCount(bookCmmtVO);

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
     * @Method 설명 : 교재 구매 후기 상세 조회
     * @param bookCmmtVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute BookCmmtVO bookCmmtVO, HttpServletRequest request) throws Exception {
        setSessionInfo(bookCmmtVO, request);

        int currentPageReply = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPageReply"), "1"));
        int pageRowReply = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRowReply"), String.valueOf(pageUnit)));

        int startNo = (currentPageReply - 1) * pageRowReply;
        int endNo = startNo + pageRowReply;
        bookCmmtVO.setStartNo(String.valueOf(startNo));
        bookCmmtVO.setEndNo(String.valueOf(endNo));

        bookCmmtVO.setSeq(CommonUtil.isNull(request.getParameter("SEQ"), ""));
        bookCmmtVO.setRscId(CommonUtil.isNull(request.getParameter("RSC_ID"), ""));

        List<HashMap<String, Object>> replyList = bookCmmtService.bookCmmtDetailList(bookCmmtVO);
        int replyListCount = bookCmmtService.bookCmmtDetailListCount(bookCmmtVO);

        // BookVO를 사용하여 상세 정보 조회 (향후 리팩토링 필요)
        com.academy.book.service.BookVO bookVO = new com.academy.book.service.BookVO();
        bookVO.setSeq(Integer.parseInt(bookCmmtVO.getSeq()));
        bookVO.setRscId(bookCmmtVO.getRscId());

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", bookService.bookView(bookVO));
        result.put("viewlist", bookService.bookViewList(bookVO));
        result.put("cmmntList", replyList);
        result.put("totalCount", replyListCount);
        result.put("currentPageReply", currentPageReply);
        result.put("pageRowReply", pageRowReply);
        result.put("totalPage", (int) Math.ceil((double) replyListCount / pageRowReply));

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2025.11
     * @Method 설명 : 교재 구매 후기 삭제
     * @param bookCmmtVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/delete")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject delete(@ModelAttribute BookCmmtVO bookCmmtVO, HttpServletRequest request) throws Exception {
        setSessionInfo(bookCmmtVO, request);

        bookCmmtVO.setDeleteRscId(request.getParameter("DELETE_RSC_ID"));
        bookCmmtVO.setDeleteSeq(request.getParameter("DELETE_SEQ"));

        bookCmmtService.cmmtDelete(bookCmmtVO);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재 구매 후기가 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : setSessionInfo
     * @작성일 : 2025.11
     * @Method 설명 : 세션 정보 설정
     * @param bookCmmtVO
     * @param request
     * @return void
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void setSessionInfo(BookCmmtVO bookCmmtVO, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if(loginInfo != null) {
                bookCmmtVO.setRegId(loginInfo.get("USER_ID"));
                bookCmmtVO.setUpdId(loginInfo.get("USER_ID"));
            }
        }

        // Set pagination parameters (using CommonVO fields)
        bookCmmtVO.setPageIndex(Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1")));
        bookCmmtVO.setPageUnit(Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit))));

        // Set search parameters (using CommonVO fields)
        bookCmmtVO.setSearchKind(CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        bookCmmtVO.setSearchType(CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        bookCmmtVO.setSearchText(CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
    }
}
