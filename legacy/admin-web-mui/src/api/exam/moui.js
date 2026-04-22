import apiClient from "shared/api/client";

export const getMouiExamList = async (params) => {
  try {
    const response = await apiClient.get("/mocktest/mouigosa/exam/getMouiExamList", { params });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getMouiExamDetail = async (params) => {
  try {
    const response = await apiClient.get("/mocktest/mouigosa/exam/getMouiExamDetail", { params });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const insertMouiExam = async (data) => {
  try {
    const response = await apiClient.post("/mocktest/mouigosa/exam/insertMouiExam", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const updateMouiExam = async (data) => {
  try {
    const response = await apiClient.post("/mocktest/mouigosa/exam/updateMouiExam", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteMouiExam = async (data) => {
  try {
    const response = await apiClient.post("/mocktest/mouigosa/exam/deleteMouiExam", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getExamYearList = async (params) => {
  try {
    const response = await apiClient.get("/mocktest/mouigosa/exam/getExamYearList", { params });
    return response.data;
  } catch (error) {
    throw error;
  }
};
