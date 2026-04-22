/* eslint-disable prettier/prettier */
import apiClient from "shared/api/client";

// =====================================================
// D-Day 관리 API
// =====================================================

// 카테고리 목록 조회
export const fetchDdayCategoryList = async (params) => {
  try {
    const response = await apiClient.get("/dday/getCategoryList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching dday category list:", error);
    return { categoryList: [] };
  }
};

// D-Day 목록 조회
export const fetchDdayList = async (params) => {
  try {
    const response = await apiClient.get("/dday/getDdayList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching dday list:", error);
    return { list: [], totalCount: 0 };
  }
};

// D-Day 상세 조회
export const fetchDdayDetail = async (params) => {
  try {
    const response = await apiClient.get("/dday/getDdayDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching dday detail:", error);
    return { detail: {}, categoryList: [] };
  }
};

// D-Day 등록
export const insertDday = async (data) => {
  try {
    const response = await apiClient.post("/dday/insertDday", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error inserting dday:", error);
    throw error;
  }
};

// D-Day 수정
export const updateDday = async (data) => {
  try {
    const response = await apiClient.post("/dday/updateDday", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error updating dday:", error);
    throw error;
  }
};

// D-Day 삭제
export const deleteDday = async (data) => {
  try {
    const response = await apiClient.post("/dday/deleteDday", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error deleting dday:", error);
    throw error;
  }
};
