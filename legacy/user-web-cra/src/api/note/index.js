import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// 쪽지 관리 API
// =====================================================

// 쪽지 목록 조회
export const getNoteList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/note/getNoteList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching note list:", error);
    throw error;
  }
};

// 쪽지 상세 조회
export const getNoteDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/note/getNoteDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching note detail:", error);
    throw error;
  }
};

// 쪽지 등록
export const insertNote = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/note/insertNote`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting note:", error);
    throw error;
  }
};

// 쪽지 수정
export const updateNote = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/note/updateNote`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating note:", error);
    throw error;
  }
};

// 쪽지 삭제
export const deleteNote = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/note/deleteNote`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting note:", error);
    throw error;
  }
};

// 쪽지 읽음 처리
export const updateNoteReadYn = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/note/updateNoteReadYn`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating note read status:", error);
    throw error;
  }
};
