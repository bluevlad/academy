import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Free Order API (무료 수강신청 관리)
// ===========================================

// 수강신청 회원 목록 조회
export const getMemberFreeOrderList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/freeOrder/getMemberFreeOrderList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching member free order list:", error);
    throw error;
  }
};

// 강의 마스터 정보 조회
export const getLectureMstInfo = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/freeOrder/getLectureMstInfo`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture mst info:", error);
    throw error;
  }
};

// 카테고리 목록 조회
export const getCategoryList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/freeOrder/getCategoryList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching category list:", error);
    throw error;
  }
};

// 학습형태 목록 조회
export const getLearningFormList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/freeOrder/getLearningFormList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching learning form list:", error);
    throw error;
  }
};

// 과목 목록 조회
export const getSubjectList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/freeOrder/getSubjectList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching subject list:", error);
    throw error;
  }
};

// 강의선택 팝업 목록 조회
export const getLectureListForFreeOrder = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/freeOrder/getLectureListForFreeOrder`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture list for free order:", error);
    throw error;
  }
};

// 수강신청 등록
export const insertFreeOrder = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/freeOrder/insertFreeOrder`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting free order:", error);
    throw error;
  }
};

// 다중 수강신청 등록
export const insertFreeOrderMultiple = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/freeOrder/insertFreeOrderMultiple`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting free order multiple:", error);
    throw error;
  }
};

// 수강변경 목록 조회
export const getChangeLectureList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/freeOrder/getChangeLectureList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching change lecture list:", error);
    throw error;
  }
};

// 수강변경 상세 조회
export const getChangeViewDetail = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/freeOrder/getChangeViewDetail`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching change view detail:", error);
    throw error;
  }
};

// 수강변경 처리
export const updateChangeLecture = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/freeOrder/updateChangeLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating change lecture:", error);
    throw error;
  }
};
