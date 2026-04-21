package com.academy.note.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academy.mapper.NoteMapper;

@Service
public class NoteService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<NoteVO> getNoteList(NoteVO vo) { return noteMapper.selectNoteList(vo); }
    public int getNoteListCount(NoteVO vo) { return noteMapper.selectNoteListCount(vo); }
    public NoteVO getNoteDetail(NoteVO vo) { return noteMapper.selectNoteDetail(vo); }
    @Transactional
    public int insertNote(NoteVO vo) { return noteMapper.insertNote(vo); }
    @Transactional
    public int updateNote(NoteVO vo) { return noteMapper.updateNote(vo); }
    @Transactional
    public int deleteNote(NoteVO vo) { return noteMapper.deleteNote(vo); }
    @Transactional
    public int updateNoteReadYn(NoteVO vo) { return noteMapper.updateNoteReadYn(vo); }
}
