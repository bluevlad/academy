import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Board Notice API
// =====================================================

// Get Board Notice List
export const getBoardNoticeList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/notice/getBoardNoticeList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board notice list:", error);
    throw error;
  }
};

// Get Board Notice Detail
export const getBoardNoticeDetail = async (boardNoticeSeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/notice/getBoardNoticeDetail`)
      .query({ boardNoticeSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board notice detail:", error);
    throw error;
  }
};

// Insert Board Notice
export const insertBoardNotice = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/notice/insertBoardNotice`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board notice:", error);
    throw error;
  }
};

// Update Board Notice
export const updateBoardNotice = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/notice/updateBoardNotice`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board notice:", error);
    throw error;
  }
};

// Delete Board Notice
export const deleteBoardNotice = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/notice/deleteBoardNotice`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board notice:", error);
    throw error;
  }
};
