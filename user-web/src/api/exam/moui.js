import superagent from "superagent";

import { BASE_API } from "../../constants/index";

export const getMouiExamList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/getMouiExamList`).query(params);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const getMouiExamDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/getMouiExamDetail`).query(params);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const insertMouiExam = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/insertMouiExam`).type("form").send(data);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const updateMouiExam = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/updateMouiExam`).type("form").send(data);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const deleteMouiExam = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/deleteMouiExam`).type("form").send(data);
    return response.body;
  } catch (error) {
    throw error;
  }
};

export const getExamYearList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/getExamYearList`).query(params);
    return response.body;
  } catch (error) {
    throw error;
  }
};
