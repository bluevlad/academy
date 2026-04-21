package com.academy.board.service;


import com.academy.common.CommonVO;

/**
 *
 * 게시판 VO 클래스
 * @author rainend
 * @version 1.0
 * @see
 ** <pre>
 * << 개정이력(Modification Information) >>
 *
 *   	  수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.11		 rainend		 게시판 등록
 * </pre>
 */
public class BoardVO extends CommonVO {

	private static final long serialVersionUID = 638950577710720796L;

	/* Identification */
	/** 게시판관리SEQ (BOARD_MNG_SEQ) */
	private String boardMngSeq;
	/** 게시판SEQ (BOARD_SEQ) */
	private String boardSeq;
	/** 부모 게시글SEQ (PARENT_BOARD_SEQ) */
	private String parentBoardSeq;

	/* Content */
	/** 제목 (SUBJECT) - underlying field is boardTitle */
	private String subject;
	/** 내용 (CONTENT) - underlying field is boardMemo */
	private String boardMemo;
	/** 답변(ANSWER) - BLOB */
	private byte[] answer;

	/* Metadata / flags */
	private String openYn; // OPEN_YN
	private String noticeTopYn; // NOTICE_TOP_YN
	// isUse inherited from CommonVO
	private String recommend; // RECOMMEND
	private Long hits; // HITS

	/* Files */
	private String filePath;
	private String fileName;
	private String realFileName;
	private String thumbnailFilePath;
	private String thumbnailFileName;
	private String thumbnailFileRealName;

	/* Audit - inherited from CommonVO: regDt, regId, updDt, updId */
	private String createName; // CREATENAME

	/* Other */
	private Long boardSeq3;
	private String examType;
	private String examArea;
	private String examCate;
	private String examSub;
	private String categoryCode;
	private String profId;
	private String boardType;
	private String mockcode;
	private String diviceType;

	/* Search / Filter */
	/** 검색 타입 (SEARCHKIND: SEARCHSUBJECT, SEARCHNAME, SEARCHCONTENT) */
	private String searchKind;
	/** 검색어 (SEARCHTEXT) */
	private String searchText;
	/** 검색 카테고리 (SEARCHCATEGORY) */
	private String searchCategory;
	/** 검색 온/오프라인 구분 (SEARCHONOFFDIV: O, F, T) */
	private String searchOnoffDiv;
	/** 검색 교수 ID (SEARCHPRFID) */
	private String searchPrfId;
	/** 온/오프라인 구분 (ONOFF_DIV: O=온라인, F=오프라인, T=강사용) */
	private String onoffDiv;

	/* Reply status */
	/** 답변 상태 (BOARD_REPLY: N=답변대기, Y=답변완료, C=처리중(CS), A=처리중(운영)) */
	private String boardReply;
	/** 메인 노출 여부 (MAIN_YN) */
	private String mainYn;

	/* 조회용 필드 */
	/** 첨부파일명 (조회용) */
	private String attachFileName;
	/** 게시판명 (조회용) */
	private String boardName;
	/** 답변 상태명 (조회용) */
	private String boardReplyNm;
	/** 교수명 (조회용) */
	private String profNm;
	/** 작성자명 (조회용) */
	private String userNm;
	/** 코드명 (조회용) */
	private String codeName;

	/* ---------------------------------- */
	/* Aliases / compatibility methods */
	/** boardId alias (used by mapper as parameter) */
	public String getBoardId() { return this.boardSeq; }
	public void setBoardId(String boardId) { this.boardSeq = boardId; }

	/** subject alias for SUBJECT column (maps to boardTitle) */
	public String getSubject() { return this.subject; }
	public void setSubject(String subject) { this.subject = subject; }

	/** content alias for CONTENT column (maps to boardMemo) */
	public String getContent() { return this.boardMemo; }
	public void setContent(String content) { this.boardMemo = content; }

	/* Standard getters/setters (boardSeq/boardMngSeq and JVM bean style) */
	public String getBoardMngSeq() { return boardMngSeq; }
	public void setBoardMngSeq(String boardMngSeq) { this.boardMngSeq = boardMngSeq; }

	public String getBoardSeq() { return boardSeq; }
	public void setBoardSeq(String boardSeq) { this.boardSeq = boardSeq; }

	public String getParentBoardSeq() { return parentBoardSeq; }
	public void setParentBoardSeq(String parentBoardSeq) { this.parentBoardSeq = parentBoardSeq; }

	public String getBoardMemo() { return boardMemo; }
	public void setBoardMemo(String boardMemo) { this.boardMemo = boardMemo; }

	public byte[] getAnswer() { return answer; }
	public void setAnswer(byte[] answer) { this.answer = answer; }

	public String getOpenYn() { return openYn; }
	public void setOpenYn(String openYn) { this.openYn = openYn; }

	public String getNoticeTopYn() { return noticeTopYn; }
	public void setNoticeTopYn(String noticeTopYn) { this.noticeTopYn = noticeTopYn; }

	// isUse getter/setter inherited from CommonVO

	public String getRecommend() { return recommend; }
	public void setRecommend(String recommend) { this.recommend = recommend; }

	public Long getHits() { return hits; }
	public void setHits(Long hits) { this.hits = hits; }

	public String getFilePath() { return filePath; }
	public void setFilePath(String filePath) { this.filePath = filePath; }

	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }

	public String getRealFileName() { return realFileName; }
	public void setRealFileName(String realFileName) { this.realFileName = realFileName; }

	public String getThumbnailFilePath() { return thumbnailFilePath; }
	public void setThumbnailFilePath(String thumbnailFilePath) { this.thumbnailFilePath = thumbnailFilePath; }

	public String getThumbnailFileName() { return thumbnailFileName; }
	public void setThumbnailFileName(String thumbnailFileName) { this.thumbnailFileName = thumbnailFileName; }

	public String getThumbnailFileRealName() { return thumbnailFileRealName; }
	public void setThumbnailFileRealName(String thumbnailFileRealName) { this.thumbnailFileRealName = thumbnailFileRealName; }

	// regDt, regId, updDt, updId getter/setter inherited from CommonVO

	public String getCreateName() { return createName; }
	public void setCreateName(String createName) { this.createName = createName; }

	public Long getBoardSeq3() { return boardSeq3; }
	public void setBoardSeq3(Long boardSeq3) { this.boardSeq3 = boardSeq3; }

	public String getExamType() { return examType; }
	public void setExamType(String examType) { this.examType = examType; }

	public String getExamArea() { return examArea; }
	public void setExamArea(String examArea) { this.examArea = examArea; }

	public String getExamCate() { return examCate; }
	public void setExamCate(String examCate) { this.examCate = examCate; }

	public String getExamSub() { return examSub; }
	public void setExamSub(String examSub) { this.examSub = examSub; }

	public String getCategoryCode() { return categoryCode; }
	public void setCategoryCode(String categoryCode) { this.categoryCode = categoryCode; }

	public String getProfId() { return profId; }
	public void setProfId(String profId) { this.profId = profId; }

	public String getBoardType() { return boardType; }
	public void setBoardType(String boardType) { this.boardType = boardType; }

	public String getMockcode() { return mockcode; }
	public void setMockcode(String mockcode) { this.mockcode = mockcode; }

	public String getDiviceType() { return diviceType; }
	public void setDiviceType(String diviceType) { this.diviceType = diviceType; }

	public String getSearchKind() { return searchKind; }
	public void setSearchKind(String searchKind) { this.searchKind = searchKind; }

	public String getSearchText() { return searchText; }
	public void setSearchText(String searchText) { this.searchText = searchText; }

	public String getSearchCategory() { return searchCategory; }
	public void setSearchCategory(String searchCategory) { this.searchCategory = searchCategory; }

	public String getSearchOnoffDiv() { return searchOnoffDiv; }
	public void setSearchOnoffDiv(String searchOnoffDiv) { this.searchOnoffDiv = searchOnoffDiv; }

	public String getSearchPrfId() { return searchPrfId; }
	public void setSearchPrfId(String searchPrfId) { this.searchPrfId = searchPrfId; }

	public String getOnoffDiv() { return onoffDiv; }
	public void setOnoffDiv(String onoffDiv) { this.onoffDiv = onoffDiv; }

	public String getBoardReply() { return boardReply; }
	public void setBoardReply(String boardReply) { this.boardReply = boardReply; }

	public String getMainYn() { return mainYn; }
	public void setMainYn(String mainYn) { this.mainYn = mainYn; }

	public String getAttachFileName() { return attachFileName; }
	public void setAttachFileName(String attachFileName) { this.attachFileName = attachFileName; }

	public String getBoardName() { return boardName; }
	public void setBoardName(String boardName) { this.boardName = boardName; }

	public String getBoardReplyNm() { return boardReplyNm; }
	public void setBoardReplyNm(String boardReplyNm) { this.boardReplyNm = boardReplyNm; }

	public String getProfNm() { return profNm; }
	public void setProfNm(String profNm) { this.profNm = profNm; }

	public String getUserNm() { return userNm; }
	public void setUserNm(String userNm) { this.userNm = userNm; }

	public String getCodeName() { return codeName; }
	public void setCodeName(String codeName) { this.codeName = codeName; }

}