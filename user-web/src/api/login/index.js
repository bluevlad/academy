import superagent from "superagent";
import { BASE_API } from "../../constants/index";

export const login = async (credentials) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/login/login`)
      .type("form")
      .send(credentials);
    return response.body;
  } catch (error) {
    if (error.response && error.response.body) {
      throw error.response.body;
    }
    throw error;
  }
};

export const register = async (userData) => {
  try {
    const response = await superagent.post(`${BASE_API}/auth/sign-up`).send(userData);
    return response.body;
  } catch (error) {
    if (error.response && error.response.body) {
      throw error.response.body;
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

    const response = await superagent
      .post(`${BASE_API}/auth/profile`)
      .set("Authorization", `Bearer ${useToken}`)
      .send({ userId });
    return response.body;
  } catch (error) {
    if (error.response && error.response.body) {
      throw error.response.body;
    }
    throw error;
  }
};
