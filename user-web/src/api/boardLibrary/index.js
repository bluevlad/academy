import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Board Library (Data Room) API
// =====================================================

// Get Board Library List
export const getBoardLibraryList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/library/getBoardLibraryList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board library list:", error);
    throw error;
  }
};

// Get Board Library Detail
export const getBoardLibraryDetail = async (boardLibrarySeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/library/getBoardLibraryDetail`)
      .query({ boardLibrarySeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board library detail:", error);
    throw error;
  }
};

// Insert Board Library
export const insertBoardLibrary = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/library/insertBoardLibrary`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board library:", error);
    throw error;
  }
};

// Update Board Library
export const updateBoardLibrary = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/library/updateBoardLibrary`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board library:", error);
    throw error;
  }
};

// Delete Board Library
export const deleteBoardLibrary = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/library/deleteBoardLibrary`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board library:", error);
    throw error;
  }
};
