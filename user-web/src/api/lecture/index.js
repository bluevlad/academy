import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Online Lecture API (단과 강의 관리)
// ===========================================

// 단과 강의 목록 조회
export const getLectureList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lecture/list`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture list:", error);
    throw error;
  }
};

// 강의 상세 조회
export const getLectureView = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lecture/view`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture view:", error);
    throw error;
  }
};

// 단과 강의 등록
export const insertLecture = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lecture/save`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting lecture:", error);
    throw error;
  }
};

// 단과 강의 수정
export const updateLecture = async (data) => {
  try {
    const response = await superagent.put(`${BASE_API}/lecture/update`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating lecture:", error);
    throw error;
  }
};

// 강의 삭제
export const deleteLecture = async (data) => {
  try {
    const response = await superagent.del(`${BASE_API}/lecture/delete`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture:", error);
    throw error;
  }
};

// 강의 다중 삭제
export const deleteLectureList = async (data) => {
  try {
    const response = await superagent.del(`${BASE_API}/lecture/listDelete`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture list:", error);
    throw error;
  }
};

// 강의 개설여부 수정
export const updateOnOffStatus = async (data) => {
  try {
    const response = await superagent.put(`${BASE_API}/lecture/onOffStatus`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating on/off status:", error);
    throw error;
  }
};

// 교재 목록 조회
export const getBookList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lecture/bookList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching book list:", error);
    throw error;
  }
};

// 교재 상세 조회
export const getBookView = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lecture/bookView`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching book view:", error);
    throw error;
  }
};

// 쿠폰 목록 조회
export const getCouponList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lecture/couponList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coupon list:", error);
    throw error;
  }
};

// 모바일 쿠폰 목록 조회
export const getMoCouponList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lecture/moCouponList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching mobile coupon list:", error);
    throw error;
  }
};
