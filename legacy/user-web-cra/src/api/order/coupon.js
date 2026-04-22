import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Coupon API (쿠폰 관리)
// ===========================================

// 쿠폰 목록 조회
export const getCouponList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/list`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coupon list:", error);
    throw error;
  }
};

// 쿠폰 상세 조회
export const getCouponView = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/view`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coupon view:", error);
    throw error;
  }
};

// 쿠폰 등록 기본 데이터
export const getCouponWriteData = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/writeData`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coupon write data:", error);
    throw error;
  }
};

// 쿠폰 등록
export const insertCoupon = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/coupon/insert`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting coupon:", error);
    throw error;
  }
};

// 쿠폰 수정
export const updateCoupon = async (data) => {
  try {
    const response = await superagent.put(`${BASE_API}/coupon/update`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating coupon:", error);
    throw error;
  }
};

// 쿠폰 발급 수강생 리스트
export const getCouponUserList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/userList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coupon user list:", error);
    throw error;
  }
};

// 제휴사 수강권/쿠폰 리스트
export const getCoopLectureList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/coopLectureList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coop lecture list:", error);
    throw error;
  }
};

// 제휴사 쿠폰 발급 리스트
export const getCoopCouponList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/coopCouponList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coop coupon list:", error);
    throw error;
  }
};

// 제휴사 쿠폰 등록
export const insertCoopCoupon = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/coupon/insertCoopCoupon`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting coop coupon:", error);
    throw error;
  }
};

// 제휴사 쿠폰 삭제
export const deleteCoopCoupon = async (data) => {
  try {
    const response = await superagent.del(`${BASE_API}/coupon/deleteCoopCoupon`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting coop coupon:", error);
    throw error;
  }
};

// 공무원 쿠폰 사용 현황
export const getCouponOrderList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/coupon/couponOrderList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching coupon order list:", error);
    throw error;
  }
};
