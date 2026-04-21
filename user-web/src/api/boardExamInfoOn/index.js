import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Board Exam Info On API
// =====================================================

// Get Board Exam Info On List
export const getBoardExamInfoOnList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/examInfoOn/getBoardExamInfoOnList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board exam info on list:", error);
    throw error;
  }
};

// Get Board Exam Info On Detail
export const getBoardExamInfoOnDetail = async (boardExamInfoOnSeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/examInfoOn/getBoardExamInfoOnDetail`)
      .query({ boardExamInfoOnSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board exam info on detail:", error);
    throw error;
  }
};

// Insert Board Exam Info On
export const insertBoardExamInfoOn = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/examInfoOn/insertBoardExamInfoOn`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board exam info on:", error);
    throw error;
  }
};

// Update Board Exam Info On
export const updateBoardExamInfoOn = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/examInfoOn/updateBoardExamInfoOn`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board exam info on:", error);
    throw error;
  }
};

// Delete Board Exam Info On
export const deleteBoardExamInfoOn = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/examInfoOn/deleteBoardExamInfoOn`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board exam info on:", error);
    throw error;
  }
};
