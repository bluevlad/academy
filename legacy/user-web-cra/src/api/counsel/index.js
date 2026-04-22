/* eslint-disable prettier/prettier */
import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// 상담 일정 (COUNSEL_SCH)
// =====================================================

// 상담 일정 일자 목록 조회
export const fetchScheduleDayList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/counsel/getScheduleDayList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching schedule day list:", error);
    return { scheduleDayList: [], paginationInfo: {} };
  }
};

// 상담 일정 상세 목록 조회 (Specific Day View)
export const fetchScheduleList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/counsel/getScheduleList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching schedule list:", error);
    return { scheduleList: [] };
  }
};

// 상담 일정 테이블 조회
export const fetchScheduleTable = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/counsel/getScheduleTable`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching schedule table:", error);
    return { scheduleTable: [] };
  }
};

// 상담 시간 테이블 조회 (For Write form)
export const fetchTimeTable = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/counsel/getTimeTable`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching time table:", error);
    return { timeTable: [] };
  }
};

// 상담 일정 등록
export const insertSchedule = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/counsel/insertSchedule`)
      .type('form') // Use form-urlencoded as backend likely expects standard post params or @ModelAttribute
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting schedule:", error);
    throw error;
  }
};

// 상담 일정 수정
export const updateSchedule = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/counsel/updateSchedule`)
      .type('form')
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating schedule:", error);
    throw error;
  }
};

// 상담 일정 삭제
export const deleteSchedule = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/counsel/deleteSchedule`)
      .type('form')
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting schedule:", error);
    throw error;
  }
};

// =====================================================
// 상담 신청 (COUNSEL_RST)
// =====================================================

export const fetchCounselRequestList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/counsel/getCounselRequestList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching counsel request list:", error);
    return { counselRequestList: [], paginationInfo: {} };
  }
};

// 상담 예약 현황 조회 (For View)
export const fetchCounselReqList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/counsel/getCounselReqList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching counsel req list:", error);
    return { counselReqList: [] };
  }
};
