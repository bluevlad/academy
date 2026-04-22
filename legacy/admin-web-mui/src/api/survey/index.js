import apiClient from "shared/api/client";

// =====================================================
// 설문조사 관리 API
// =====================================================

// ========== 설문 문항 (Bank) ==========

// 설문 문항 목록 조회
export const getBankList = async (params) => {
  try {
    const response = await apiClient.get("/survey/bank/getBankList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching bank list:", error);
    throw error;
  }
};

// 설문 문항 상세 조회
export const getBankDetail = async (params) => {
  try {
    const response = await apiClient.get("/survey/bank/getBankDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching bank detail:", error);
    throw error;
  }
};

// 설문 문항 등록
export const insertBank = async (data) => {
  try {
    const response = await apiClient.post("/survey/bank/insertBank", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting bank:", error);
    throw error;
  }
};

// 설문 문항 수정
export const updateBank = async (data) => {
  try {
    const response = await apiClient.post("/survey/bank/updateBank", data);
    return response.data;
  } catch (error) {
    console.error("Error updating bank:", error);
    throw error;
  }
};

// 설문 문항 삭제
export const deleteBank = async (data) => {
  try {
    const response = await apiClient.post("/survey/bank/deleteBank", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting bank:", error);
    throw error;
  }
};

// ========== 설문 세트 (Set) ==========

// 설문 세트 목록 조회
export const getSetList = async (params) => {
  try {
    const response = await apiClient.get("/survey/set/getSetList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching set list:", error);
    throw error;
  }
};

// 설문 세트 상세 조회
export const getSetDetail = async (params) => {
  try {
    const response = await apiClient.get("/survey/set/getSetDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching set detail:", error);
    throw error;
  }
};

// 설문 세트 등록
export const insertSet = async (data) => {
  try {
    const response = await apiClient.post("/survey/set/insertSet", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting set:", error);
    throw error;
  }
};

// 설문 세트 수정
export const updateSet = async (data) => {
  try {
    const response = await apiClient.post("/survey/set/updateSet", data);
    return response.data;
  } catch (error) {
    console.error("Error updating set:", error);
    throw error;
  }
};

// 설문 세트 삭제
export const deleteSet = async (data) => {
  try {
    const response = await apiClient.post("/survey/set/deleteSet", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting set:", error);
    throw error;
  }
};

// ========== 설문 세트 항목 ==========

// 설문 세트 항목 추가
export const insertSetItem = async (data) => {
  try {
    const response = await apiClient.post("/survey/set/insertSetItem", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting set item:", error);
    throw error;
  }
};

// 설문 세트 항목 순서 수정
export const updateSetItem = async (data) => {
  try {
    const response = await apiClient.post("/survey/set/updateSetItem", data);
    return response.data;
  } catch (error) {
    console.error("Error updating set item:", error);
    throw error;
  }
};

// 설문 세트 항목 삭제
export const deleteSetItem = async (data) => {
  try {
    const response = await apiClient.post("/survey/set/deleteSetItem", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting set item:", error);
    throw error;
  }
};

// ========== 설문 (Survey) ==========

// 설문 목록 조회
export const getSurveyList = async (params) => {
  try {
    const response = await apiClient.get("/survey/getSurveyList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching survey list:", error);
    throw error;
  }
};

// 설문 상세 조회
export const getSurveyDetail = async (params) => {
  try {
    const response = await apiClient.get("/survey/getSurveyDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching survey detail:", error);
    throw error;
  }
};

// 설문 등록
export const insertSurvey = async (data) => {
  try {
    const response = await apiClient.post("/survey/insertSurvey", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting survey:", error);
    throw error;
  }
};

// 설문 수정
export const updateSurvey = async (data) => {
  try {
    const response = await apiClient.post("/survey/updateSurvey", data);
    return response.data;
  } catch (error) {
    console.error("Error updating survey:", error);
    throw error;
  }
};

// 설문 삭제
export const deleteSurvey = async (data) => {
  try {
    const response = await apiClient.post("/survey/deleteSurvey", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting survey:", error);
    throw error;
  }
};

// ========== 설문 결과 ==========

// 설문 응답자 목록 조회
export const getAnswerList = async (params) => {
  try {
    const response = await apiClient.get("/survey/getAnswerList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching answer list:", error);
    throw error;
  }
};
