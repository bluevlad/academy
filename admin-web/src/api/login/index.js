import apiClient from "shared/api/client";

export const login = async (credentials) => {
  try {
    const response = await apiClient.post("/auth/sign-in", credentials, {
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
    });
    return response.data;
  } catch (error) {
    if (error.response && error.response.data) {
      throw error.response.data;
    }
    throw error;
  }
};

export const register = async (userData) => {
  try {
    const response = await apiClient.post("/auth/sign-up", userData);
    return response.data;
  } catch (error) {
    if (error.response && error.response.data) {
      throw error.response.data;
    }
    throw error;
  }
};

export const getProfile = async (userId, token = null) => {
  try {
    const useToken = token || sessionStorage.getItem("token") || localStorage.getItem("token");

    if (!useToken) {
      throw new Error("No token found");
    }

    const response = await apiClient.post("/auth/profile", { userId });
    return response.data;
  } catch (error) {
    if (error.response && error.response.data) {
      throw error.response.data;
    }
    throw error;
  }
};
