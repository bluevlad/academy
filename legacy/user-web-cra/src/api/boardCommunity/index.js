import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Board Community API
// =====================================================

// Get Board Community List
export const getBoardCmmntyList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/cmmnty/getBoardCmmntyList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board community list:", error);
    throw error;
  }
};

// Get Board Community Detail
export const getBoardCmmntyDetail = async (boardCmmntySeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/cmmnty/getBoardCmmntyDetail`)
      .query({ boardCmmntySeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board community detail:", error);
    throw error;
  }
};

// Insert Board Community
export const insertBoardCmmnty = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/cmmnty/insertBoardCmmnty`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board community:", error);
    throw error;
  }
};

// Update Board Community
export const updateBoardCmmnty = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/cmmnty/updateBoardCmmnty`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board community:", error);
    throw error;
  }
};

// Delete Board Community
export const deleteBoardCmmnty = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/cmmnty/deleteBoardCmmnty`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board community:", error);
    throw error;
  }
};
