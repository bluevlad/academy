import superagent from "superagent";

import { BASE_API } from "../../constants/index";

// const BASE_API = "http://115.68.220.203:8080/api/gosi"; // Deprecated

export const getSampleUserList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/getSampleUserList`).query(params);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const getSampleUserDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/getSampleUserDetail`).query(params);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const insertSampleUser = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/insertSampleUser`).type("form").send(data);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const updateSampleUser = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/updateSampleUser`).type("form").send(data);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const deleteSampleUser = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/deleteSampleUser`).type("form").send(data);
    return response.body;
  } catch (error) {
    throw error;
  }
};
