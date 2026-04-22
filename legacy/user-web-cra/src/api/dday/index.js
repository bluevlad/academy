/* eslint-disable prettier/prettier */
import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// D-Day 관리 API
// =====================================================

// 카테고리 목록 조회
export const fetchDdayCategoryList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/dday/getCategoryList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching dday category list:", error);
    return { categoryList: [] };
  }
};

// D-Day 목록 조회
export const fetchDdayList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/dday/getDdayList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching dday list:", error);
    return { list: [], totalCount: 0 };
  }
};

// D-Day 상세 조회
export const fetchDdayDetail = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/dday/getDdayDetail`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching dday detail:", error);
    return { detail: {}, categoryList: [] };
  }
};

// D-Day 등록
export const insertDday = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/dday/insertDday`)
      .type('form')
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting dday:", error);
    throw error;
  }
};

// D-Day 수정
export const updateDday = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/dday/updateDday`)
      .type('form')
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating dday:", error);
    throw error;
  }
};

// D-Day 삭제
export const deleteDday = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/dday/deleteDday`)
      .type('form')
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting dday:", error);
    throw error;
  }
};
