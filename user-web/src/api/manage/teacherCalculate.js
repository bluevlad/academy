/* eslint-disable prettier/prettier */
import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Teacher Calculate API (강사 정산 관리)
// ===========================================

// 강사 정산 목록 조회
export const getTeacherCalculateList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getTeacherCalculateList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher calculate list:", error);
    throw error;
  }
};

// 강사 정산 상세 조회
export const getTeacherCalculateDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getTeacherCalculateDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher calculate detail:", error);
    throw error;
  }
};

// 강사별 정산 요약 조회
export const getTeacherCalculateSummaryList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getTeacherCalculateSummaryList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher calculate summary list:", error);
    throw error;
  }
};

// 월별 정산 목록 조회
export const getMonthlyCalculateList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getMonthlyCalculateList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching monthly calculate list:", error);
    throw error;
  }
};

// 강사별 강의 매출 목록 조회
export const getTeacherLectureSaleList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getTeacherLectureSaleList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher lecture sale list:", error);
    throw error;
  }
};

// 정산 통계 조회
export const getCalculateStats = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getCalculateStats`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching calculate stats:", error);
    throw error;
  }
};

// 강사 목록 조회
export const getTeacherList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/manage/teacherCalculate/getTeacherList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher list:", error);
    throw error;
  }
};

// 강사 정산 등록
export const insertTeacherCalculate = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/teacherCalculate/insertTeacherCalculate`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting teacher calculate:", error);
    throw error;
  }
};

// 강사 정산 수정
export const updateTeacherCalculate = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/teacherCalculate/updateTeacherCalculate`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating teacher calculate:", error);
    throw error;
  }
};

// 강사 정산 삭제
export const deleteTeacherCalculate = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/teacherCalculate/deleteTeacherCalculate`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting teacher calculate:", error);
    throw error;
  }
};

// 정산 상태 변경
export const updateCalculateStatus = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/teacherCalculate/updateCalculateStatus`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating calculate status:", error);
    throw error;
  }
};

// 지급 처리
export const updatePayment = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/teacherCalculate/updatePayment`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating payment:", error);
    throw error;
  }
};

// 월별 정산 일괄 생성
export const insertMonthlyCalculateBatch = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/manage/teacherCalculate/insertMonthlyCalculateBatch`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting monthly calculate batch:", error);
    throw error;
  }
};
