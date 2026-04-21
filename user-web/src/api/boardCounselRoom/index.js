import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// 상담실 게시판 (BoardCounselRoom) API
// =====================================================

// 상담실 게시판 목록 조회
export const getBoardCounselRoomList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/counselRoom/getBoardCounselRoomList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board counsel room list:", error);
    throw error;
  }
};

// 상담실 게시판 상세 조회
export const getBoardCounselRoomDetail = async (boardCounselRoomSeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/counselRoom/getBoardCounselRoomDetail`) // boardDetail in standard might be different? Using explicit name.
      .query({ boardCounselRoomSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board counsel room detail:", error);
    throw error;
  }
};

// 상담실 게시판 등록
export const insertBoardCounselRoom = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/counselRoom/insertBoardCounselRoom`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board counsel room:", error);
    throw error;
  }
};

// 상담실 게시판 수정
export const updateBoardCounselRoom = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/counselRoom/updateBoardCounselRoom`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board counsel room:", error);
    throw error;
  }
};

// 상담실 게시판 삭제
export const deleteBoardCounselRoom = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/counselRoom/deleteBoardCounselRoom`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board counsel room:", error);
    throw error;
  }
};

// 상담실 게시판 답변 등록
export const insertBoardCounselRoomReply = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/counselRoom/insertBoardCounselRoomReply`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board counsel room reply:", error);
    throw error;
  }
};
