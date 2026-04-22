/* eslint-disable prettier/prettier */
import apiClient from "shared/api/client";

// 공통코드 목록 조회
export const getCodeList = async (params) => {
  try {
    const response = await apiClient.get("/admin/code/getCodeList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching code list:", error);
    throw error;
  }
};

// 공통코드 등록
export const insertCode = async (data) => {
  try {
    const response = await apiClient.post("/admin/code/insertCode", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting code:", error);
    throw error;
  }
};

// 공통코드 수정
export const updateCode = async (data) => {
  try {
    const response = await apiClient.post("/admin/code/updateCode", data);
    return response.data;
  } catch (error) {
    console.error("Error updating code:", error);
    throw error;
  }
};

// 공통코드 삭제
export const deleteCode = async (codeNo) => {
  try {
    const response = await apiClient.post("/admin/code/deleteCode", { codeNo });
    return response.data;
  } catch (error) {
    console.error("Error deleting code:", error);
    throw error;
  }
};
