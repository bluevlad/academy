/* eslint-disable prettier/prettier */
import apiClient from "shared/api/client";

// ===========================================
// Lecture Year API (강의 연도별 관리)
// ===========================================

// 연도별 강의 목록 조회
export const getLectureYearList = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getLectureYearList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture year list:", error);
    throw error;
  }
};

// 연도별 강의 상세 조회
export const getLectureYearDetail = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getLectureYearDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture year detail:", error);
    throw error;
  }
};

// 연도 목록 조회
export const getYearList = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getYearList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching year list:", error);
    throw error;
  }
};

// 연도별 강의 통계 조회
export const getLectureYearStats = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getLectureYearStats", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture year stats:", error);
    throw error;
  }
};

// 연도별 카테고리 강의 통계 조회
export const getCategoryYearStatsList = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getCategoryYearStatsList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category year stats list:", error);
    throw error;
  }
};

// 연도별 강사 강의 통계 조회
export const getTeacherYearStatsList = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getTeacherYearStatsList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher year stats list:", error);
    throw error;
  }
};

// 연도별 매출 통계 조회
export const getYearlySaleStatsList = async (params) => {
  try {
    const response = await apiClient.get("/manage/lectureYear/getYearlySaleStatsList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching yearly sale stats list:", error);
    throw error;
  }
};

// 연도 정보 등록
export const insertLectureYear = async (data) => {
  try {
    const response = await apiClient.post("/manage/lectureYear/insertLectureYear", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting lecture year:", error);
    throw error;
  }
};

// 연도 정보 수정
export const updateLectureYear = async (data) => {
  try {
    const response = await apiClient.post("/manage/lectureYear/updateLectureYear", data);
    return response.data;
  } catch (error) {
    console.error("Error updating lecture year:", error);
    throw error;
  }
};

// 연도 정보 삭제
export const deleteLectureYear = async (data) => {
  try {
    const response = await apiClient.post("/manage/lectureYear/deleteLectureYear", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture year:", error);
    throw error;
  }
};

// 강의-연도 매핑 등록
export const insertLectureYearMapping = async (data) => {
  try {
    const response = await apiClient.post("/manage/lectureYear/insertLectureYearMapping", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting lecture year mapping:", error);
    throw error;
  }
};

// 강의-연도 매핑 삭제
export const deleteLectureYearMapping = async (data) => {
  try {
    const response = await apiClient.post("/manage/lectureYear/deleteLectureYearMapping", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture year mapping:", error);
    throw error;
  }
};
