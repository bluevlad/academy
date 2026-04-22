/* eslint-disable prettier/prettier */
import apiClient from "shared/api/client";

// ===========================================
// Teacher Calculate API (강사 정산 관리)
// ===========================================

// 강사 정산 목록 조회
export const getTeacherCalculateList = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getTeacherCalculateList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher calculate list:", error);
    throw error;
  }
};

// 강사 정산 상세 조회
export const getTeacherCalculateDetail = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getTeacherCalculateDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher calculate detail:", error);
    throw error;
  }
};

// 강사별 정산 요약 조회
export const getTeacherCalculateSummaryList = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getTeacherCalculateSummaryList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher calculate summary list:", error);
    throw error;
  }
};

// 월별 정산 목록 조회
export const getMonthlyCalculateList = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getMonthlyCalculateList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching monthly calculate list:", error);
    throw error;
  }
};

// 강사별 강의 매출 목록 조회
export const getTeacherLectureSaleList = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getTeacherLectureSaleList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher lecture sale list:", error);
    throw error;
  }
};

// 정산 통계 조회
export const getCalculateStats = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getCalculateStats", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching calculate stats:", error);
    throw error;
  }
};

// 강사 목록 조회
export const getTeacherList = async (params) => {
  try {
    const response = await apiClient.get("/manage/teacherCalculate/getTeacherList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher list:", error);
    throw error;
  }
};

// 강사 정산 등록
export const insertTeacherCalculate = async (data) => {
  try {
    const response = await apiClient.post("/manage/teacherCalculate/insertTeacherCalculate", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting teacher calculate:", error);
    throw error;
  }
};

// 강사 정산 수정
export const updateTeacherCalculate = async (data) => {
  try {
    const response = await apiClient.post("/manage/teacherCalculate/updateTeacherCalculate", data);
    return response.data;
  } catch (error) {
    console.error("Error updating teacher calculate:", error);
    throw error;
  }
};

// 강사 정산 삭제
export const deleteTeacherCalculate = async (data) => {
  try {
    const response = await apiClient.post("/manage/teacherCalculate/deleteTeacherCalculate", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting teacher calculate:", error);
    throw error;
  }
};

// 정산 상태 변경
export const updateCalculateStatus = async (data) => {
  try {
    const response = await apiClient.post("/manage/teacherCalculate/updateCalculateStatus", data);
    return response.data;
  } catch (error) {
    console.error("Error updating calculate status:", error);
    throw error;
  }
};

// 지급 처리
export const updatePayment = async (data) => {
  try {
    const response = await apiClient.post("/manage/teacherCalculate/updatePayment", data);
    return response.data;
  } catch (error) {
    console.error("Error updating payment:", error);
    throw error;
  }
};

// 월별 정산 일괄 생성
export const insertMonthlyCalculateBatch = async (data) => {
  try {
    const response = await apiClient.post("/manage/teacherCalculate/insertMonthlyCalculateBatch", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting monthly calculate batch:", error);
    throw error;
  }
};
