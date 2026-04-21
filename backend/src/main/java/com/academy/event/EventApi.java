package com.academy.event;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.event.service.EventFileVO;
import com.academy.event.service.EventOptionVO;
import com.academy.event.service.EventResultVO;
import com.academy.event.service.EventService;
import com.academy.event.service.EventVO;
import com.academy.event.service.LecEventVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 이벤트 관리 API Controller
 * @author system
 * @since 2025.12.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.11         system          신규 생성
 * </pre>
 */
@Tag(name = "이벤트 관리", description = "이벤트 관리 API")
@RestController
@RequestMapping("/api/event")
public class EventApi extends CORSFilter {

    private final EventService eventService;

    @Autowired
    public EventApi(EventService eventService) {
        this.eventService = eventService;
    }

    // =====================================================
    // 이벤트 정보 (TB_EVENT_INFO) 관련
    // =====================================================

    /**
     * 이벤트 목록 조회
     * @param eventVO 검색조건
     * @return JSONObject 이벤트 목록
     */
    @Operation(summary = "이벤트 목록 조회", description = "이벤트 목록을 조회한다.")
    @GetMapping(value = "/getEventList")
    public JSONObject getEventList(@ModelAttribute EventVO eventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(eventVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(eventVO.getPageUnit());
            paginationInfo.setPageSize(eventVO.getPageSize());

            eventVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            eventVO.setLastIndex(paginationInfo.getLastRecordIndex());
            eventVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = eventService.selectEventList(eventVO);
            int totalCount = eventService.selectEventListCount(eventVO);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
            jsonObject.put("currentPage", eventVO.getPageIndex());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 상세 조회
     * @param eventVO 검색조건
     * @return JSONObject 이벤트 상세 정보
     */
    @Operation(summary = "이벤트 상세 조회", description = "이벤트 상세 정보를 조회한다.")
    @GetMapping(value = "/getEventDetail")
    public JSONObject getEventDetail(@ModelAttribute EventVO eventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            JSONObject detail = eventService.selectEventDetail(eventVO);

            EventFileVO fileVO = new EventFileVO();
            fileVO.setEventNo(eventVO.getEventNo());
            List<JSONObject> fileList = eventService.selectEventFileList(fileVO);

            EventOptionVO optionVO = new EventOptionVO();
            optionVO.setEventNo(eventVO.getEventNo());
            List<JSONObject> option1List = eventService.selectEventOption1List(optionVO);

            jsonObject.put("detail", detail);
            jsonObject.put("fileList", fileList);
            jsonObject.put("option1List", option1List);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 등록
     * @param eventVO 이벤트 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "이벤트 등록", description = "이벤트를 등록한다.")
    @PostMapping(value = "/insertEvent")
    public JSONObject insertEvent(@ModelAttribute EventVO eventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.insertEvent(eventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 수정
     * @param eventVO 이벤트 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "이벤트 수정", description = "이벤트를 수정한다.")
    @PostMapping(value = "/updateEvent")
    public JSONObject updateEvent(@ModelAttribute EventVO eventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.updateEvent(eventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 삭제
     * @param eventVO 이벤트 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제한다.")
    @PostMapping(value = "/deleteEvent")
    public JSONObject deleteEvent(@ModelAttribute EventVO eventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.deleteEvent(eventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    // =====================================================
    // 이벤트 옵션2 (TB_EVENT_OPTION2) - 댓글 관련
    // =====================================================

    /**
     * 이벤트 댓글 목록 조회
     * @param eventOptionVO 검색조건
     * @return JSONObject 이벤트 댓글 목록
     */
    @Operation(summary = "이벤트 댓글 목록 조회", description = "이벤트 댓글 목록을 조회한다.")
    @GetMapping(value = "/getEventCommentList")
    public JSONObject getEventCommentList(@ModelAttribute EventOptionVO eventOptionVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(eventOptionVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(eventOptionVO.getPageUnit());
            paginationInfo.setPageSize(eventOptionVO.getPageSize());

            eventOptionVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            eventOptionVO.setLastIndex(paginationInfo.getLastRecordIndex());
            eventOptionVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = eventService.selectEventOption2List(eventOptionVO);
            int totalCount = eventService.selectEventOption2ListCount(eventOptionVO);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
            jsonObject.put("currentPage", eventOptionVO.getPageIndex());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 댓글 등록
     * @param eventOptionVO 이벤트 댓글 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "이벤트 댓글 등록", description = "이벤트 댓글을 등록한다.")
    @PostMapping(value = "/insertEventComment")
    public JSONObject insertEventComment(@ModelAttribute EventOptionVO eventOptionVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.insertEventOption2(eventOptionVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 댓글 삭제
     * @param eventOptionVO 이벤트 댓글 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "이벤트 댓글 삭제", description = "이벤트 댓글을 삭제한다.")
    @PostMapping(value = "/deleteEventComment")
    public JSONObject deleteEventComment(@ModelAttribute EventOptionVO eventOptionVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.deleteEventOption2(eventOptionVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    // =====================================================
    // 이벤트 결과/참여자 (TB_EVENT_RESULT) 관련
    // =====================================================

    /**
     * 이벤트 참여자 목록 조회
     * @param eventResultVO 검색조건
     * @return JSONObject 이벤트 참여자 목록
     */
    @Operation(summary = "이벤트 참여자 목록 조회", description = "이벤트 참여자 목록을 조회한다.")
    @GetMapping(value = "/getEventResultList")
    public JSONObject getEventResultList(@ModelAttribute EventResultVO eventResultVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(eventResultVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(eventResultVO.getPageUnit());
            paginationInfo.setPageSize(eventResultVO.getPageSize());

            eventResultVO.setRsltStartNo(paginationInfo.getFirstRecordIndex());
            eventResultVO.setRsltEndNo(paginationInfo.getLastRecordIndex());

            List<JSONObject> list = eventService.selectEventResultList(eventResultVO);
            int totalCount = eventService.selectEventResultListCount(eventResultVO);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
            jsonObject.put("currentPage", eventResultVO.getPageIndex());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    // =====================================================
    // 강의 이벤트 (TB_LECTURE_EVENT_INFO) 관련
    // =====================================================

    /**
     * 강의 이벤트 목록 조회
     * @param lecEventVO 검색조건
     * @return JSONObject 강의 이벤트 목록
     */
    @Operation(summary = "강의 이벤트 목록 조회", description = "강의 이벤트 목록을 조회한다.")
    @GetMapping(value = "/getLecEventList")
    public JSONObject getLecEventList(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(lecEventVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(lecEventVO.getPageUnit());
            paginationInfo.setPageSize(lecEventVO.getPageSize());

            lecEventVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            lecEventVO.setLastIndex(paginationInfo.getLastRecordIndex());
            lecEventVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = eventService.selectLecEventList(lecEventVO);
            int totalCount = eventService.selectLecEventListCount(lecEventVO);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
            jsonObject.put("currentPage", lecEventVO.getPageIndex());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 이벤트 상세 조회
     * @param lecEventVO 검색조건
     * @return JSONObject 강의 이벤트 상세 정보
     */
    @Operation(summary = "강의 이벤트 상세 조회", description = "강의 이벤트 상세 정보를 조회한다.")
    @GetMapping(value = "/getLecEventDetail")
    public JSONObject getLecEventDetail(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            JSONObject detail = eventService.selectLecEventDetail(lecEventVO);
            List<JSONObject> lectureList = eventService.selectLecEventLectureList(lecEventVO);

            jsonObject.put("detail", detail);
            jsonObject.put("lectureList", lectureList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 이벤트 등록
     * @param lecEventVO 강의 이벤트 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "강의 이벤트 등록", description = "강의 이벤트를 등록한다.")
    @PostMapping(value = "/insertLecEvent")
    public JSONObject insertLecEvent(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.insertLecEvent(lecEventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 이벤트 수정
     * @param lecEventVO 강의 이벤트 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "강의 이벤트 수정", description = "강의 이벤트를 수정한다.")
    @PostMapping(value = "/updateLecEvent")
    public JSONObject updateLecEvent(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.updateLecEvent(lecEventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 이벤트 삭제
     * @param lecEventVO 강의 이벤트 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "강의 이벤트 삭제", description = "강의 이벤트를 삭제한다.")
    @PostMapping(value = "/deleteLecEvent")
    public JSONObject deleteLecEvent(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.deleteLecEvent(lecEventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 이벤트 강좌 등록
     * @param lecEventVO 강의 이벤트 강좌 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "강의 이벤트 강좌 등록", description = "강의 이벤트 강좌를 등록한다.")
    @PostMapping(value = "/insertLecEventLecture")
    public JSONObject insertLecEventLecture(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.insertLecEventLecture(lecEventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 이벤트 강좌 삭제
     * @param lecEventVO 강의 이벤트 강좌 정보
     * @return JSONObject 처리 결과
     */
    @Operation(summary = "강의 이벤트 강좌 삭제", description = "강의 이벤트 강좌를 삭제한다.")
    @PostMapping(value = "/deleteLecEventLecture")
    public JSONObject deleteLecEventLecture(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            eventService.deleteLecEventLecture(lecEventVO);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 강좌 목록 조회 (이벤트용)
     * @param lecEventVO 검색조건
     * @return JSONObject 강좌 목록
     */
    @Operation(summary = "강좌 목록 조회 (이벤트용)", description = "이벤트에 등록할 강좌 목록을 조회한다.")
    @GetMapping(value = "/getLectureListForEvent")
    public JSONObject getLectureListForEvent(@ModelAttribute LecEventVO lecEventVO) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(lecEventVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(lecEventVO.getPageUnit());
            paginationInfo.setPageSize(lecEventVO.getPageSize());

            lecEventVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            lecEventVO.setLastIndex(paginationInfo.getLastRecordIndex());
            lecEventVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<JSONObject> list = eventService.selectLectureListForEvent(lecEventVO);
            int totalCount = eventService.selectLectureListForEventCount(lecEventVO);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
            jsonObject.put("currentPage", lecEventVO.getPageIndex());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("errMsg", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
