package com.academy.lecture;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.lecture.service.LectureReplyService;

@RestController
@RequestMapping("/api/lectureReply")
public class LectureReplyApi extends CORSFilter {

    private static final int PAGE_ROW = 100;

    private final LectureReplyService lectureReplyService;

    @Autowired
    public LectureReplyApi(LectureReplyService lectureReplyService) {
        this.lectureReplyService = lectureReplyService;
    }

    @GetMapping("/list")
    public JSONObject list(@RequestParam(defaultValue = "1") int currentPage,
                           @RequestParam(defaultValue = "100") int pageRow,
                           @RequestParam(defaultValue = "") String SEARCHTYPE,
                           @RequestParam(defaultValue = "") String SEARCHKEYWORD) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("SEARCHTYPE", SEARCHTYPE);
            params.put("SEARCHKEYWORD", SEARCHKEYWORD);
            int actualPageRow = pageRow > 0 ? pageRow : PAGE_ROW;
            params.put("pageRow", actualPageRow);
            params.put("startNo", (currentPage - 1) * actualPageRow);

            List<HashMap<String, Object>> list = lectureReplyService.selectLectureReplyList(params);
            int totalCount = lectureReplyService.selectLectureReplyListCount(params);

            result.put("data", list);
            result.put("totalCount", totalCount);
            result.put("retMsg", "OK");
        } catch (Exception e) {
            result.put("retMsg", "FAIL");
            result.put("errMsg", e.getMessage());
        }
        return new JSONObject(result);
    }

    @GetMapping("/detail")
    public JSONObject detail(@RequestParam String DETAIL_LECCODE,
                             @RequestParam(defaultValue = "1") int currentPage,
                             @RequestParam(defaultValue = "100") int pageRow) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("DETAIL_LECCODE", DETAIL_LECCODE);
            int actualPageRow = pageRow > 0 ? pageRow : PAGE_ROW;
            params.put("pageRow", actualPageRow);
            params.put("startNo", (currentPage - 1) * actualPageRow);

            HashMap<String, Object> lectureInfo = lectureReplyService.selectLectureReplyDetail(params);
            List<HashMap<String, Object>> comments = lectureReplyService.selectLectureReplyDetailComments(params);
            int commentCount = lectureReplyService.selectLectureReplyDetailCommentCount(params);

            result.put("lectureInfo", lectureInfo);
            result.put("data", comments);
            result.put("totalCount", commentCount);
            result.put("retMsg", "OK");
        } catch (Exception e) {
            result.put("retMsg", "FAIL");
            result.put("errMsg", e.getMessage());
        }
        return new JSONObject(result);
    }

    @DeleteMapping("/delete")
    public JSONObject delete(@RequestParam String LECCODE, @RequestParam String SEQ) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("LECCODE", LECCODE);
            params.put("SEQ", SEQ);

            lectureReplyService.deleteLectureReply(params);
            result.put("retMsg", "OK");
        } catch (Exception e) {
            result.put("retMsg", "FAIL");
            result.put("errMsg", e.getMessage());
        }
        return new JSONObject(result);
    }
}
