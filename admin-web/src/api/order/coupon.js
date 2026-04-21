import apiClient from "shared/api/client";

// ===========================================
// Coupon API (쿠폰 관리)
// ===========================================

// 쿠폰 목록 조회
export const getCouponList = async (params) => {
  try {
    const response = await apiClient.get("/coupon/list", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coupon list:", error);
    throw error;
  }
};

// 쿠폰 상세 조회
export const getCouponView = async (params) => {
  try {
    const response = await apiClient.get("/coupon/view", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coupon view:", error);
    throw error;
  }
};

// 쿠폰 등록 기본 데이터
export const getCouponWriteData = async (params) => {
  try {
    const response = await apiClient.get("/coupon/writeData", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coupon write data:", error);
    throw error;
  }
};

// 쿠폰 등록
export const insertCoupon = async (data) => {
  try {
    const response = await apiClient.post("/coupon/insert", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting coupon:", error);
    throw error;
  }
};

// 쿠폰 수정
export const updateCoupon = async (data) => {
  try {
    const response = await apiClient.put("/coupon/update", data);
    return response.data;
  } catch (error) {
    console.error("Error updating coupon:", error);
    throw error;
  }
};

// 쿠폰 발급 수강생 리스트
export const getCouponUserList = async (params) => {
  try {
    const response = await apiClient.get("/coupon/userList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coupon user list:", error);
    throw error;
  }
};

// 제휴사 수강권/쿠폰 리스트
export const getCoopLectureList = async (params) => {
  try {
    const response = await apiClient.get("/coupon/coopLectureList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop lecture list:", error);
    throw error;
  }
};

// 제휴사 쿠폰 발급 리스트
export const getCoopCouponList = async (params) => {
  try {
    const response = await apiClient.get("/coupon/coopCouponList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop coupon list:", error);
    throw error;
  }
};

// 제휴사 쿠폰 등록
export const insertCoopCoupon = async (data) => {
  try {
    const response = await apiClient.post("/coupon/insertCoopCoupon", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting coop coupon:", error);
    throw error;
  }
};

// 제휴사 쿠폰 삭제
export const deleteCoopCoupon = async (data) => {
  try {
    const response = await apiClient.delete("/coupon/deleteCoopCoupon", { data });
    return response.data;
  } catch (error) {
    console.error("Error deleting coop coupon:", error);
    throw error;
  }
};

// 공무원 쿠폰 사용 현황
export const getCouponOrderList = async (params) => {
  try {
    const response = await apiClient.get("/coupon/couponOrderList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coupon order list:", error);
    throw error;
  }
};
