import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Board Customer On (Online Customer Voice) API
// =====================================================

// Get Board Customer On List
export const getBoardCustomerOnList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/customerOn/getBoardCustomerOnList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching board customer on list:", error);
    throw error;
  }
};

// Get Board Customer On Detail
export const getBoardCustomerOnDetail = async (boardCustomerOnSeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/board/customerOn/getBoardCustomerOnDetail`)
      .query({ boardCustomerOnSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching board customer on detail:", error);
    throw error;
  }
};

// Insert Board Customer On
export const insertBoardCustomerOn = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/customerOn/insertBoardCustomerOn`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board customer on:", error);
    throw error;
  }
};

// Update Board Customer On
export const updateBoardCustomerOn = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/customerOn/updateBoardCustomerOn`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating board customer on:", error);
    throw error;
  }
};

// Delete Board Customer On
export const deleteBoardCustomerOn = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/customerOn/deleteBoardCustomerOn`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting board customer on:", error);
    throw error;
  }
};

// Insert Board Customer On Reply
export const insertBoardCustomerOnReply = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/board/customerOn/insertBoardCustomerOnReply`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting board customer on reply:", error);
    throw error;
  }
};
