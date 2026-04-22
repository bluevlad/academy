import apiClient from "shared/api/client";

// =====================================================
// 팝업 관리 API
// =====================================================

// 팝업 목록 조회
export const getPopupList = async (params) => {
  try {
    const response = await apiClient.get("/popup/getPopupList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching popup list:", error);
    throw error;
  }
};

// 팝업 상세 조회
export const getPopupDetail = async (params) => {
  try {
    const response = await apiClient.get("/popup/getPopupDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching popup detail:", error);
    throw error;
  }
};

// 팝업 등록
export const insertPopup = async (data) => {
  try {
    const response = await apiClient.post("/popup/insertPopup", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting popup:", error);
    throw error;
  }
};

// 팝업 수정
export const updatePopup = async (data) => {
  try {
    const response = await apiClient.post("/popup/updatePopup", data);
    return response.data;
  } catch (error) {
    console.error("Error updating popup:", error);
    throw error;
  }
};

// 팝업 삭제
export const deletePopup = async (data) => {
  try {
    const response = await apiClient.post("/popup/deletePopup", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting popup:", error);
    throw error;
  }
};

// 팝업 공개여부 변경
export const updatePopupOpenYn = async (data) => {
  try {
    const response = await apiClient.post("/popup/updatePopupOpenYn", data);
    return response.data;
  } catch (error) {
    console.error("Error updating popup open status:", error);
    throw error;
  }
};

// 팝업 조회수 증가
export const updatePopupHit = async (data) => {
  try {
    const response = await apiClient.post("/popup/updatePopupHit", data);
    return response.data;
  } catch (error) {
    console.error("Error updating popup hit:", error);
    throw error;
  }
};
