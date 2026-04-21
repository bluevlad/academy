import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// 팝업 관리 API
// =====================================================

// 팝업 목록 조회
export const getPopupList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/popup/getPopupList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching popup list:", error);
    throw error;
  }
};

// 팝업 상세 조회
export const getPopupDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/popup/getPopupDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching popup detail:", error);
    throw error;
  }
};

// 팝업 등록
export const insertPopup = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/popup/insertPopup`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting popup:", error);
    throw error;
  }
};

// 팝업 수정
export const updatePopup = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/popup/updatePopup`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating popup:", error);
    throw error;
  }
};

// 팝업 삭제
export const deletePopup = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/popup/deletePopup`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting popup:", error);
    throw error;
  }
};

// 팝업 공개여부 변경
export const updatePopupOpenYn = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/popup/updatePopupOpenYn`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating popup open status:", error);
    throw error;
  }
};

// 팝업 조회수 증가
export const updatePopupHit = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/popup/updatePopupHit`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating popup hit:", error);
    throw error;
  }
};
