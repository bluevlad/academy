/* eslint-disable prettier/prettier */
import apiClient from "shared/api/client";

// ===========================================
// Category Sale API (카테고리별 매출 관리)
// ===========================================

// 카테고리별 매출 목록 조회
export const getCategorySaleList = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getCategorySaleList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category sale list:", error);
    throw error;
  }
};

// 카테고리별 매출 상세 조회
export const getCategorySaleDetail = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getCategorySaleDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category sale detail:", error);
    throw error;
  }
};

// 카테고리별 매출 통계 조회
export const getCategorySaleStats = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getCategorySaleStats", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category sale stats:", error);
    throw error;
  }
};

// 일별 매출 목록 조회
export const getDailySaleList = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getDailySaleList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching daily sale list:", error);
    throw error;
  }
};

// 월별 매출 목록 조회
export const getMonthlySaleList = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getMonthlySaleList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching monthly sale list:", error);
    throw error;
  }
};

// 년별 매출 목록 조회
export const getYearlySaleList = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getYearlySaleList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching yearly sale list:", error);
    throw error;
  }
};

// 카테고리 목록 조회
export const getCategoryList = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getCategoryList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category list:", error);
    throw error;
  }
};

// 카테고리별 매출 비율 조회
export const getCategorySaleRateList = async (params) => {
  try {
    const response = await apiClient.get("/manage/categorySale/getCategorySaleRateList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category sale rate list:", error);
    throw error;
  }
};

// 매출 데이터 등록
export const insertCategorySale = async (data) => {
  try {
    const response = await apiClient.post("/manage/categorySale/insertCategorySale", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting category sale:", error);
    throw error;
  }
};

// 매출 데이터 수정
export const updateCategorySale = async (data) => {
  try {
    const response = await apiClient.post("/manage/categorySale/updateCategorySale", data);
    return response.data;
  } catch (error) {
    console.error("Error updating category sale:", error);
    throw error;
  }
};

// 매출 데이터 삭제
export const deleteCategorySale = async (data) => {
  try {
    const response = await apiClient.post("/manage/categorySale/deleteCategorySale", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting category sale:", error);
    throw error;
  }
};
