import apiClient from "shared/api/client";

// =====================================================
// 쪽지 관리 API
// =====================================================

// 쪽지 목록 조회
export const getNoteList = async (params) => {
  try {
    const response = await apiClient.get("/note/getNoteList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching note list:", error);
    throw error;
  }
};

// 쪽지 상세 조회
export const getNoteDetail = async (params) => {
  try {
    const response = await apiClient.get("/note/getNoteDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching note detail:", error);
    throw error;
  }
};

// 쪽지 등록
export const insertNote = async (data) => {
  try {
    const response = await apiClient.post("/note/insertNote", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting note:", error);
    throw error;
  }
};

// 쪽지 수정
export const updateNote = async (data) => {
  try {
    const response = await apiClient.post("/note/updateNote", data);
    return response.data;
  } catch (error) {
    console.error("Error updating note:", error);
    throw error;
  }
};

// 쪽지 삭제
export const deleteNote = async (data) => {
  try {
    const response = await apiClient.post("/note/deleteNote", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting note:", error);
    throw error;
  }
};

// 쪽지 읽음 처리
export const updateNoteReadYn = async (data) => {
  try {
    const response = await apiClient.post("/note/updateNoteReadYn", data);
    return response.data;
  } catch (error) {
    console.error("Error updating note read status:", error);
    throw error;
  }
};
