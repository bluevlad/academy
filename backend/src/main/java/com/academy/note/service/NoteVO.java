package com.academy.note.service;

import com.academy.common.CommonVO;

/**
 * 쪽지 관리 VO
 */
public class NoteVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    private int noteId;
    private String fromUserId;
    private String fromUserNm;
    private String sendId;
    private String sendNm;
    private String content;
    private String readYn;
    private String readYnNm;
    private String sendDate;
    private String readDate;
    private String searchKind;
    private String searchText;

    public int getNoteId() { return noteId; }
    public void setNoteId(int noteId) { this.noteId = noteId; }
    public String getFromUserId() { return fromUserId; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
    public String getFromUserNm() { return fromUserNm; }
    public void setFromUserNm(String fromUserNm) { this.fromUserNm = fromUserNm; }
    public String getSendId() { return sendId; }
    public void setSendId(String sendId) { this.sendId = sendId; }
    public String getSendNm() { return sendNm; }
    public void setSendNm(String sendNm) { this.sendNm = sendNm; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getReadYn() { return readYn; }
    public void setReadYn(String readYn) { this.readYn = readYn; }
    public String getReadYnNm() { return readYnNm; }
    public void setReadYnNm(String readYnNm) { this.readYnNm = readYnNm; }
    public String getSendDate() { return sendDate; }
    public void setSendDate(String sendDate) { this.sendDate = sendDate; }
    public String getReadDate() { return readDate; }
    public void setReadDate(String readDate) { this.readDate = readDate; }
    public String getSearchKind() { return searchKind; }
    public void setSearchKind(String searchKind) { this.searchKind = searchKind; }
    public String getSearchText() { return searchText; }
    public void setSearchText(String searchText) { this.searchText = searchText; }
}
