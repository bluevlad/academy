package com.academy.lectureOff;

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
import com.academy.lectureOff.service.LectureOffService;
import com.academy.lectureOff.service.LectureOffVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 오프라인 강의 관리 API
 */
@Tag(name = "LectureOff", description = "오프라인 강의 관리 API")
@RestController
@RequestMapping("/api/lectureOff")
public class LectureOffApi extends CORSFilter {

    private final LectureOffService lectureOffService;

    @Autowired
    public LectureOffApi(LectureOffService lectureOffService) {
        this.lectureOffService = lectureOffService;
    }

    // =============================================
    // 단과 강의 관리
    // =============================================
    @Operation(summary = "단과 강의 목록 조회", description = "오프라인 단과 강의 목록을 조회합니다.")
    @GetMapping(value = "/getLectureList")
    public JSONObject getLectureList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = lectureOffService.getLectureListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<LectureOffVO> list = lectureOffService.getLectureList(vo);

            jsonObject.put("data", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "단과 강의 상세 조회", description = "단과 강의 상세 정보를 조회합니다.")
    @GetMapping(value = "/getLectureDetail")
    public JSONObject getLectureDetail(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            LectureOffVO detail = lectureOffService.getLectureDetail(vo);
            List<LectureOffVO> bridgeList = lectureOffService.getLectureBridgeList(vo);
            List<LectureOffVO> bookList = lectureOffService.getLectureBookList(vo);
            List<LectureOffVO> dateList = lectureOffService.getLectureDateList(vo);

            jsonObject.put("data", detail);
            jsonObject.put("bridgeList", bridgeList);
            jsonObject.put("bookList", bookList);
            jsonObject.put("dateList", dateList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "단과 강의 등록", description = "오프라인 단과 강의를 등록합니다.")
    @PostMapping(value = "/insertLecture")
    public JSONObject insertLecture(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertLecture(vo);
            jsonObject.put("data", result);
            jsonObject.put("leccode", vo.getLeccode());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "단과 강의 수정", description = "오프라인 단과 강의를 수정합니다.")
    @PostMapping(value = "/updateLecture")
    public JSONObject updateLecture(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.updateLecture(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "단과 강의 삭제", description = "오프라인 단과 강의를 삭제합니다.")
    @PostMapping(value = "/deleteLecture")
    public JSONObject deleteLecture(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteLecture(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 강의 브릿지 관리
    // =============================================
    @Operation(summary = "강의 브릿지 목록 조회", description = "강의 브릿지 목록을 조회합니다.")
    @GetMapping(value = "/getLectureBridgeList")
    public JSONObject getLectureBridgeList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getLectureBridgeList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 브릿지 등록", description = "강의 브릿지를 등록합니다.")
    @PostMapping(value = "/insertLectureBridge")
    public JSONObject insertLectureBridge(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertLectureBridge(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 브릿지 삭제", description = "강의 브릿지를 삭제합니다.")
    @PostMapping(value = "/deleteLectureBridge")
    public JSONObject deleteLectureBridge(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteLectureBridge(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 강의 교재 관리
    // =============================================
    @Operation(summary = "강의 교재 목록 조회", description = "강의 교재 목록을 조회합니다.")
    @GetMapping(value = "/getLectureBookList")
    public JSONObject getLectureBookList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getLectureBookList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "교재 검색 목록 조회", description = "등록 가능한 교재를 검색합니다.")
    @GetMapping(value = "/getBookSearchList")
    public JSONObject getBookSearchList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getBookSearchList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 교재 등록", description = "강의 교재를 등록합니다.")
    @PostMapping(value = "/insertLectureBook")
    public JSONObject insertLectureBook(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertLectureBook(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 교재 삭제", description = "강의 교재를 삭제합니다.")
    @PostMapping(value = "/deleteLectureBook")
    public JSONObject deleteLectureBook(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteLectureBook(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 강의 일정 관리
    // =============================================
    @Operation(summary = "강의 일정 목록 조회", description = "강의 일정 목록을 조회합니다.")
    @GetMapping(value = "/getLectureDateList")
    public JSONObject getLectureDateList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getLectureDateList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 일정 등록", description = "강의 일정을 등록합니다.")
    @PostMapping(value = "/insertLectureDate")
    public JSONObject insertLectureDate(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertLectureDate(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 일정 삭제", description = "강의 일정을 삭제합니다.")
    @PostMapping(value = "/deleteLectureDate")
    public JSONObject deleteLectureDate(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteLectureDate(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 종합반 강의 관리
    // =============================================
    @Operation(summary = "종합반 강의 목록 조회", description = "종합반 강의 목록을 조회합니다.")
    @GetMapping(value = "/getJongLectureList")
    public JSONObject getJongLectureList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int totalCount = lectureOffService.getJongLectureListCount(vo);

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vo.getPageIndex());
            paginationInfo.setRecordCountPerPage(vo.getPageUnit());
            paginationInfo.setPageSize(vo.getPageSize());
            paginationInfo.setTotalRecordCount(totalCount);

            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vo.setLastIndex(paginationInfo.getLastRecordIndex());
            vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            List<LectureOffVO> list = lectureOffService.getJongLectureList(vo);

            jsonObject.put("data", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "종합반 강의 상세 조회", description = "종합반 강의 상세 정보를 조회합니다.")
    @GetMapping(value = "/getJongLectureDetail")
    public JSONObject getJongLectureDetail(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            LectureOffVO detail = lectureOffService.getJongLectureDetail(vo);
            List<LectureOffVO> detailList = lectureOffService.getJongLectureDetailList(vo);

            jsonObject.put("data", detail);
            jsonObject.put("detailList", detailList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "종합반 강의 등록", description = "종합반 강의를 등록합니다.")
    @PostMapping(value = "/insertJongLecture")
    public JSONObject insertJongLecture(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertJongLecture(vo);
            jsonObject.put("data", result);
            jsonObject.put("jongseq", vo.getJongseq());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "종합반 강의 수정", description = "종합반 강의를 수정합니다.")
    @PostMapping(value = "/updateJongLecture")
    public JSONObject updateJongLecture(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.updateJongLecture(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "종합반 강의 삭제", description = "종합반 강의를 삭제합니다.")
    @PostMapping(value = "/deleteJongLecture")
    public JSONObject deleteJongLecture(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteJongLecture(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 종합반 강의 구성 관리
    // =============================================
    @Operation(summary = "종합반 강의 구성 목록 조회", description = "종합반에 포함된 단과 강의 목록을 조회합니다.")
    @GetMapping(value = "/getJongLectureDetailList")
    public JSONObject getJongLectureDetailList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getJongLectureDetailList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "종합반 강의 구성 등록", description = "종합반에 단과 강의를 추가합니다.")
    @PostMapping(value = "/insertJongLectureDetail")
    public JSONObject insertJongLectureDetail(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertJongLectureDetail(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "종합반 강의 구성 삭제", description = "종합반에서 단과 강의를 삭제합니다.")
    @PostMapping(value = "/deleteJongLectureDetail")
    public JSONObject deleteJongLectureDetail(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteJongLectureDetail(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 선택형 종합반 관리
    // =============================================
    @Operation(summary = "선택형 종합반 목록 조회", description = "선택형 종합반 목록을 조회합니다.")
    @GetMapping(value = "/getChoiceJongList")
    public JSONObject getChoiceJongList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getChoiceJongList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "선택형 종합반 등록", description = "선택형 종합반을 등록합니다.")
    @PostMapping(value = "/insertChoiceJong")
    public JSONObject insertChoiceJong(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.insertChoiceJong(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "선택형 종합반 삭제", description = "선택형 종합반을 삭제합니다.")
    @PostMapping(value = "/deleteChoiceJong")
    public JSONObject deleteChoiceJong(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int result = lectureOffService.deleteChoiceJong(vo);
            jsonObject.put("data", result);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 카테고리/과목/강사 조회
    // =============================================
    @Operation(summary = "카테고리 목록 조회", description = "카테고리 목록을 조회합니다.")
    @GetMapping(value = "/getCategoryList")
    public JSONObject getCategoryList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getCategoryList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "과목 목록 조회", description = "과목 목록을 조회합니다.")
    @GetMapping(value = "/getSubjectList")
    public JSONObject getSubjectList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getSubjectList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강사 목록 조회", description = "강사 목록을 조회합니다.")
    @GetMapping(value = "/getTeacherList")
    public JSONObject getTeacherList(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            List<LectureOffVO> list = lectureOffService.getTeacherList(vo);
            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    // =============================================
    // 강의코드 생성
    // =============================================
    @Operation(summary = "다음 강의코드 조회", description = "새로운 강의코드를 생성합니다.")
    @GetMapping(value = "/getNextLeccode")
    public JSONObject getNextLeccode(@ModelAttribute LectureOffVO vo) {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            String nextLeccode = lectureOffService.getNextLeccode(vo);
            jsonObject.put("data", nextLeccode);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }

    @Operation(summary = "다음 종합반 SEQ 조회", description = "새로운 종합반 SEQ를 생성합니다.")
    @GetMapping(value = "/getNextJongseq")
    public JSONObject getNextJongseq() {
        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            int nextJongseq = lectureOffService.getNextJongseq();
            jsonObject.put("data", nextJongseq);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }
        return new JSONObject(jsonObject);
    }
}
