package com.academy.note;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.note.service.NoteService;
import com.academy.note.service.NoteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Note", description = "쪽지 관리 API")
@RestController
@RequestMapping("/api/note")
public class NoteApi extends CORSFilter {

    private final NoteService noteService;

    @Autowired
    public NoteApi(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "쪽지 목록 조회")
    @GetMapping("/getNoteList")
    public JSONObject getNoteList(@ModelAttribute NoteVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            int totalCount = noteService.getNoteListCount(vo);
            PaginationInfo page = new PaginationInfo();
            page.setCurrentPageNo(vo.getPageIndex());
            page.setRecordCountPerPage(vo.getPageUnit());
            page.setPageSize(vo.getPageSize());
            page.setTotalRecordCount(totalCount);
            vo.setFirstIndex(page.getFirstRecordIndex());
            vo.setLastIndex(page.getLastRecordIndex());
            vo.setRecordCountPerPage(page.getRecordCountPerPage());
            json.put("data", noteService.getNoteList(vo));
            json.put("totalCount", totalCount);
            json.put("paginationInfo", page);
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "쪽지 상세 조회")
    @GetMapping("/getNoteDetail")
    public JSONObject getNoteDetail(@ModelAttribute NoteVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", noteService.getNoteDetail(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "쪽지 등록")
    @PostMapping("/insertNote")
    public JSONObject insertNote(@ModelAttribute NoteVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", noteService.insertNote(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "쪽지 수정")
    @PostMapping("/updateNote")
    public JSONObject updateNote(@ModelAttribute NoteVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", noteService.updateNote(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "쪽지 삭제")
    @PostMapping("/deleteNote")
    public JSONObject deleteNote(@ModelAttribute NoteVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", noteService.deleteNote(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }

    @Operation(summary = "쪽지 읽음 처리")
    @PostMapping("/updateNoteReadYn")
    public JSONObject updateNoteReadYn(@ModelAttribute NoteVO vo) {
        HashMap<String, Object> json = new HashMap<>();
        try {
            json.put("data", noteService.updateNoteReadYn(vo));
            json.put("retMsg", "OK");
        } catch (Exception e) {
            json.put("retMsg", "FAIL");
            json.put("error", e.getMessage());
        }
        return new JSONObject(json);
    }
}
