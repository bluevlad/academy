import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// FAQ API
// =====================================================

// Get FAQ List
export const getFaqList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/board/faq/getFaqList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching faq list:", error);
    throw error;
  }
};

// Get FAQ Detail
export const getFaqDetail = async (faqSeq) => {
  try {
    const response = await superagent.get(`${BASE_API}/board/faq/getFaqDetail`).query({ faqSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching faq detail:", error);
    throw error;
  }
};

// Insert FAQ
export const insertFaq = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/faq/insertFaq`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting faq:", error);
    throw error;
  }
};

// Update FAQ
export const updateFaq = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/faq/updateFaq`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating faq:", error);
    throw error;
  }
};

// Delete FAQ
export const deleteFaq = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/board/faq/deleteFaq`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting faq:", error);
    throw error;
  }
};
