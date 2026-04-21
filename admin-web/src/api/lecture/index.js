import apiClient from "shared/api/client";

// ===========================================
// Online Lecture API (단과 강의 관리)
// ===========================================

// 단과 강의 목록 조회
export const getLectureList = async (params) => {
  try {
    const response = await apiClient.get("/lecture/list", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture list:", error);
    throw error;
  }
};

// 강의 상세 조회
export const getLectureView = async (params) => {
  try {
    const response = await apiClient.get("/lecture/view", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture view:", error);
    throw error;
  }
};

// 단과 강의 등록
export const insertLecture = async (data) => {
  try {
    const response = await apiClient.post("/lecture/save", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting lecture:", error);
    throw error;
  }
};

// 단과 강의 수정
export const updateLecture = async (data) => {
  try {
    const response = await apiClient.put("/lecture/update", data);
    return response.data;
  } catch (error) {
    console.error("Error updating lecture:", error);
    throw error;
  }
};

// 강의 삭제
export const deleteLecture = async (data) => {
  try {
    const response = await apiClient.delete("/lecture/delete", { data });
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture:", error);
    throw error;
  }
};

// 강의 다중 삭제
export const deleteLectureList = async (data) => {
  try {
    const response = await apiClient.delete("/lecture/listDelete", { data });
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture list:", error);
    throw error;
  }
};

// 강의 개설여부 수정
export const updateOnOffStatus = async (data) => {
  try {
    const response = await apiClient.put("/lecture/onOffStatus", data);
    return response.data;
  } catch (error) {
    console.error("Error updating on/off status:", error);
    throw error;
  }
};

// 교재 목록 조회
export const getBookList = async (params) => {
  try {
    const response = await apiClient.get("/lecture/bookList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching book list:", error);
    throw error;
  }
};

// 교재 상세 조회
export const getBookView = async (params) => {
  try {
    const response = await apiClient.get("/lecture/bookView", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching book view:", error);
    throw error;
  }
};

// 쿠폰 목록 조회
export const getCouponList = async (params) => {
  try {
    const response = await apiClient.get("/lecture/couponList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coupon list:", error);
    throw error;
  }
};

// 모바일 쿠폰 목록 조회
export const getMoCouponList = async (params) => {
  try {
    const response = await apiClient.get("/lecture/moCouponList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching mobile coupon list:", error);
    throw error;
  }
};
