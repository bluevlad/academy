import apiClient from "shared/api/client";

// ===========================================
// Free Order API (무료 수강신청 관리)
// ===========================================

// 수강신청 회원 목록 조회
export const getMemberFreeOrderList = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getMemberFreeOrderList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching member free order list:", error);
    throw error;
  }
};

// 강의 마스터 정보 조회
export const getLectureMstInfo = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getLectureMstInfo", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture mst info:", error);
    throw error;
  }
};

// 카테고리 목록 조회
export const getCategoryList = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getCategoryList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category list:", error);
    throw error;
  }
};

// 학습형태 목록 조회
export const getLearningFormList = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getLearningFormList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching learning form list:", error);
    throw error;
  }
};

// 과목 목록 조회
export const getSubjectList = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getSubjectList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching subject list:", error);
    throw error;
  }
};

// 강의선택 팝업 목록 조회
export const getLectureListForFreeOrder = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getLectureListForFreeOrder", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture list for free order:", error);
    throw error;
  }
};

// 수강신청 등록
export const insertFreeOrder = async (data) => {
  try {
    const response = await apiClient.post("/freeOrder/insertFreeOrder", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting free order:", error);
    throw error;
  }
};

// 다중 수강신청 등록
export const insertFreeOrderMultiple = async (data) => {
  try {
    const response = await apiClient.post("/freeOrder/insertFreeOrderMultiple", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting free order multiple:", error);
    throw error;
  }
};

// 수강변경 목록 조회
export const getChangeLectureList = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getChangeLectureList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching change lecture list:", error);
    throw error;
  }
};

// 수강변경 상세 조회
export const getChangeViewDetail = async (params) => {
  try {
    const response = await apiClient.get("/freeOrder/getChangeViewDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching change view detail:", error);
    throw error;
  }
};

// 수강변경 처리
export const updateChangeLecture = async (data) => {
  try {
    const response = await apiClient.post("/freeOrder/updateChangeLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error updating change lecture:", error);
    throw error;
  }
};
