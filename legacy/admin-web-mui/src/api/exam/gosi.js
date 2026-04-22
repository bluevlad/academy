import apiClient from "shared/api/client";

// const BASE_API = "http://115.68.220.203:8080/api/gosi"; // Deprecated

export const getSampleUserList = async (params) => {
  try {
    const response = await apiClient.get("/gosi/getSampleUserList", { params });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getSampleUserDetail = async (params) => {
  try {
    const response = await apiClient.get("/gosi/getSampleUserDetail", { params });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const insertSampleUser = async (data) => {
  try {
    const response = await apiClient.post("/gosi/insertSampleUser", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const updateSampleUser = async (data) => {
  try {
    const response = await apiClient.post("/gosi/updateSampleUser", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteSampleUser = async (data) => {
  try {
    const response = await apiClient.post("/gosi/deleteSampleUser", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    throw error;
  }
};
