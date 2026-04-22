import apiClient from "shared/api/client";

// =====================================================
// 게시판 관리 (TB_BOARD_MNG) API
// =====================================================

// 게시판 관리 목록 조회
export const getBoardMngList = async (params) => {
  try {
    const response = await apiClient.get("/board/getBoardMngList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching board mng list:", error);
    throw error;
  }
};

// 게시판 관리 상세 조회
export const getBoardMngDetail = async (boardMngSeq) => {
  try {
    const response = await apiClient.get("/board/getBoardMngDetail", {
      params: { boardMngSeq },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching board mng detail:", error);
    throw error;
  }
};

// 게시판 관리 등록
export const insertBoardMng = async (data) => {
  try {
    const response = await apiClient.post("/board/insertBoardMng", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting board mng:", error);
    throw error;
  }
};

// 게시판 관리 수정
export const updateBoardMng = async (data) => {
  try {
    const response = await apiClient.post("/board/updateBoardMng", data);
    return response.data;
  } catch (error) {
    console.error("Error updating board mng:", error);
    throw error;
  }
};

// 게시판 관리 삭제
export const deleteBoardMng = async (boardMngSeq) => {
  try {
    const response = await apiClient.post("/board/deleteBoardMng", { boardMngSeq });
    return response.data;
  } catch (error) {
    console.error("Error deleting board mng:", error);
    throw error;
  }
};

// 게시판 타입 목록 조회 (공지, 일반 등)
export const getBoardTypeList = async () => {
  try {
    const response = await apiClient.get("/board/getBoardTypeList");
    return response.data;
  } catch (error) {
    console.error("Error fetching board type list:", error);
    throw error;
  }
};

// =====================================================
// 게시판 (TB_BOARD) API
// =====================================================

// 게시판 목록 조회 (All)
export const getBoardList = async (params) => {
  try {
    const response = await apiClient.get("/board/getBoardList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching board list:", error);
    throw error;
  }
};

// 게시판 상세 조회
export const getBoardDetail = async (params) => {
  try {
    const response = await apiClient.get("/board/getBoardDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching board detail:", error);
    throw error;
  }
};

// 게시물 등록
export const insertBoard = async (data) => {
  try {
    const response = await apiClient.post("/board/insertBoard", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting board:", error);
    throw error;
  }
};

// 게시물 수정
export const updateBoard = async (data) => {
  try {
    const response = await apiClient.post("/board/updateBoard", data);
    return response.data;
  } catch (error) {
    console.error("Error updating board:", error);
    throw error;
  }
};

// 게시물 삭제
export const deleteBoard = async (data) => {
  try {
    const response = await apiClient.post("/board/deleteBoard", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting board:", error);
    throw error;
  }
};

// 답변 게시물 등록
export const insertBoardReply = async (data) => {
  try {
    const response = await apiClient.post("/board/insertBoardReply", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting board reply:", error);
    throw error;
  }
};

// =====================================================
// 게시판 카테고리 / 뷰 관련 (Assuming Board Code/Category is View Management)
// =====================================================

// 게시판 카테고리 정보 조회
export const getBoardCodeList = async (params) => {
  try {
    const response = await apiClient.get("/board/getBoardCodeList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching board code list:", error);
    throw error;
  }
};

// 게시판 카테고리 등록
export const insertBoardCatInfo = async (data) => {
  try {
    const response = await apiClient.post("/board/insertBoardCatInfo", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting board cat info:", error);
    throw error;
  }
};

// 게시판 카테고리 삭제
export const deleteBoardCatInfo = async (data) => {
  try {
    const response = await apiClient.post("/board/deleteBoardCatInfo", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting board cat info:", error);
    throw error;
  }
};

// 직급 코드 목록 조회
export const getRankCodeList = async (params) => {
  try {
    const response = await apiClient.get("/board/getRankCodeList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching rank code list:", error);
    throw error;
  }
};
