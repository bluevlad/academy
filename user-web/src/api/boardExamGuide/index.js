import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Board Exam Guide API
// =====================================================

// Get Board Exam Guide List
export const getBoardExamGuideList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/examGuide/getBoardExamGuideList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board exam guide list:", error);
    throw error;
  }
};

// Get Board Exam Guide Detail
export const getBoardExamGuideDetail = async (boardExamGuideSeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/examGuide/getBoardExamGuideDetail`)
      .query({ boardExamGuideSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board exam guide detail:", error);
    throw error;
  }
};

// Insert Board Exam Guide
export const insertBoardExamGuide = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/examGuide/insertBoardExamGuide`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board exam guide:", error);
    throw error;
  }
};

// Update Board Exam Guide
export const updateBoardExamGuide = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/examGuide/updateBoardExamGuide`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board exam guide:", error);
    throw error;
  }
};

// Delete Board Exam Guide
export const deleteBoardExamGuide = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/examGuide/deleteBoardExamGuide`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board exam guide:", error);
    throw error;
  }
};
