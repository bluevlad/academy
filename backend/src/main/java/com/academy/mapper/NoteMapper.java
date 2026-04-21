package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.note.service.NoteVO;

@Mapper
public interface NoteMapper {
    List<NoteVO> selectNoteList(NoteVO vo);
    int selectNoteListCount(NoteVO vo);
    NoteVO selectNoteDetail(NoteVO vo);
    int insertNote(NoteVO vo);
    int updateNote(NoteVO vo);
    int deleteNote(NoteVO vo);
    int updateNoteReadYn(NoteVO vo);
}
