import apiClient from "shared/api/client";

// 권한 목록 조회
export const getAuthList = async (params) => {
  try {
    const response = await apiClient.get("/admin/auth/getAuthList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching auth list:", error);
    throw error;
  }
};

// 권한 상세 조회
export const getAuthDetail = async (siteId) => {
  try {
    const response = await apiClient.get("/admin/auth/getAuthDetail", { params: { siteId } });
    return response.data;
  } catch (error) {
    console.error("Error fetching auth detail:", error);
    throw error;
  }
};

// 권한 등록
export const insertAuth = async (data) => {
  try {
    const response = await apiClient.post("/admin/auth/insertAuth", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting auth:", error);
    throw error;
  }
};

// 권한 수정
export const updateAuth = async (data) => {
  try {
    const response = await apiClient.post("/admin/auth/updateAuth", data);
    return response.data;
  } catch (error) {
    console.error("Error updating auth:", error);
    throw error;
  }
};

// 권한 삭제
export const deleteAuth = async (siteId) => {
  try {
    const response = await apiClient.post("/admin/auth/deleteAuth", { siteId });
    return response.data;
  } catch (error) {
    console.error("Error deleting auth:", error);
    throw error;
  }
};

// 권한별 메뉴 목록 조회 (온라인)
export const getOnlineMenuList = async (siteId) => {
  try {
    const response = await apiClient.get("/admin/auth/getOnlineMenuList", {
      params: { siteId },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching online menu list:", error);
    throw error;
  }
};
