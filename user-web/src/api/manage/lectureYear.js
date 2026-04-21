/* eslint-disable prettier/prettier */
import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Lecture Year API (강의 연도별 관리)
// ===========================================

// 연도별 강의 목록 조회
export const getLectureYearList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getLectureYearList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture year list:", error);
    throw error;
  }
};

// 연도별 강의 상세 조회
export const getLectureYearDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getLectureYearDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture year detail:", error);
    throw error;
  }
};

// 연도 목록 조회
export const getYearList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getYearList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching year list:", error);
    throw error;
  }
};

// 연도별 강의 통계 조회
export const getLectureYearStats = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getLectureYearStats`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture year stats:", error);
    throw error;
  }
};

// 연도별 카테고리 강의 통계 조회
export const getCategoryYearStatsList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getCategoryYearStatsList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching category year stats list:", error);
    throw error;
  }
};

// 연도별 강사 강의 통계 조회
export const getTeacherYearStatsList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getTeacherYearStatsList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher year stats list:", error);
    throw error;
  }
};

// 연도별 매출 통계 조회
export const getYearlySaleStatsList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/lectureYear/getYearlySaleStatsList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching yearly sale stats list:", error);
    throw error;
  }
};

// 연도 정보 등록
export const insertLectureYear = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/lectureYear/insertLectureYear`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting lecture year:", error);
    throw error;
  }
};

// 연도 정보 수정
export const updateLectureYear = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/lectureYear/updateLectureYear`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating lecture year:", error);
    throw error;
  }
};

// 연도 정보 삭제
export const deleteLectureYear = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/lectureYear/deleteLectureYear`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture year:", error);
    throw error;
  }
};

// 강의-연도 매핑 등록
export const insertLectureYearMapping = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/lectureYear/insertLectureYearMapping`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting lecture year mapping:", error);
    throw error;
  }
};

// 강의-연도 매핑 삭제
export const deleteLectureYearMapping = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/lectureYear/deleteLectureYearMapping`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture year mapping:", error);
    throw error;
  }
};
