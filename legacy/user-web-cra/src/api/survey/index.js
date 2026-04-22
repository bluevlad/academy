import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// 설문조사 관리 API
// =====================================================

// ========== 설문 문항 (Bank) ==========

// 설문 문항 목록 조회
export const getBankList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/bank/getBankList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching bank list:", error);
    throw error;
  }
};

// 설문 문항 상세 조회
export const getBankDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/bank/getBankDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching bank detail:", error);
    throw error;
  }
};

// 설문 문항 등록
export const insertBank = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/bank/insertBank`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting bank:", error);
    throw error;
  }
};

// 설문 문항 수정
export const updateBank = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/bank/updateBank`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating bank:", error);
    throw error;
  }
};

// 설문 문항 삭제
export const deleteBank = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/bank/deleteBank`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting bank:", error);
    throw error;
  }
};

// ========== 설문 세트 (Set) ==========

// 설문 세트 목록 조회
export const getSetList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/set/getSetList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching set list:", error);
    throw error;
  }
};

// 설문 세트 상세 조회
export const getSetDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/set/getSetDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching set detail:", error);
    throw error;
  }
};

// 설문 세트 등록
export const insertSet = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/set/insertSet`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting set:", error);
    throw error;
  }
};

// 설문 세트 수정
export const updateSet = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/set/updateSet`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating set:", error);
    throw error;
  }
};

// 설문 세트 삭제
export const deleteSet = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/set/deleteSet`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting set:", error);
    throw error;
  }
};

// ========== 설문 세트 항목 ==========

// 설문 세트 항목 추가
export const insertSetItem = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/set/insertSetItem`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting set item:", error);
    throw error;
  }
};

// 설문 세트 항목 순서 수정
export const updateSetItem = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/set/updateSetItem`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating set item:", error);
    throw error;
  }
};

// 설문 세트 항목 삭제
export const deleteSetItem = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/set/deleteSetItem`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting set item:", error);
    throw error;
  }
};

// ========== 설문 (Survey) ==========

// 설문 목록 조회
export const getSurveyList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/getSurveyList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching survey list:", error);
    throw error;
  }
};

// 설문 상세 조회
export const getSurveyDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/getSurveyDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching survey detail:", error);
    throw error;
  }
};

// 설문 등록
export const insertSurvey = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/insertSurvey`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting survey:", error);
    throw error;
  }
};

// 설문 수정
export const updateSurvey = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/updateSurvey`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating survey:", error);
    throw error;
  }
};

// 설문 삭제
export const deleteSurvey = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/survey/deleteSurvey`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting survey:", error);
    throw error;
  }
};

// ========== 설문 결과 ==========

// 설문 응답자 목록 조회
export const getAnswerList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/survey/getAnswerList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching answer list:", error);
    throw error;
  }
};
