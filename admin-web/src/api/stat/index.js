import apiClient from "shared/api/client";

// =====================================================
// 매출 통계 API
// =====================================================

// 강사 목록 조회 (통계용)
export const getTeacherList = async (params) => {
  try {
    const response = await apiClient.get("/stat/teacher/getTeacherList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher list:", error);
    throw error;
  }
};

// 강사별 과목 목록 조회
export const getTeacherSubjectList = async (params) => {
  try {
    const response = await apiClient.get("/stat/teacher/getTeacherSubjectList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher subject list:", error);
    throw error;
  }
};

// 강사 상세 조회
export const getTeacherDetail = async (params) => {
  try {
    const response = await apiClient.get("/stat/teacher/getTeacherDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher detail:", error);
    throw error;
  }
};

// 강사 매출 통계 조회
export const getTeacherSalesStat = async (params) => {
  try {
    const response = await apiClient.get("/stat/teacher/getTeacherSalesStat", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher sales stat:", error);
    throw error;
  }
};

// 회원 구매 통계 조회
export const getUserBuyStat = async (params) => {
  try {
    const response = await apiClient.get("/stat/sales/getUserBuyStat", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching user buy stat:", error);
    throw error;
  }
};

// 검색어 통계 목록 조회
export const getSearchKeywordList = async (params) => {
  try {
    const response = await apiClient.get("/stat/search/getSearchKeywordList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching search keyword list:", error);
    throw error;
  }
};
