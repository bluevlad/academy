import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Product Order API (상품 주문 관리)
// ===========================================

// 온라인 주문 목록 조회
export const getProductOrderList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/list`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching product order list:", error);
    throw error;
  }
};

// 0원 주문 목록 조회
export const getZeroProductOrderList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/list0`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching zero product order list:", error);
    throw error;
  }
};

// 무료 강의 주문 목록 조회
export const getFreeLecProductOrderList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/listFreelec`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching free lec product order list:", error);
    throw error;
  }
};

// 오프라인 주문 목록 조회
export const getOffProductOrderList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/offList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching off product order list:", error);
    throw error;
  }
};

// 주문 상세 조회
export const getProductOrderView = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/view`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching product order view:", error);
    throw error;
  }
};

// 주문 상태 코드 목록
export const getStatusCodeList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/statusCodeList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching status code list:", error);
    throw error;
  }
};

// 결제 수단 목록
export const getPaymentList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/paymentList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching payment list:", error);
    throw error;
  }
};

// 결제 수단 변경
export const updatePayKind = async (data) => {
  try {
    const response = await superagent.put(`${BASE_API}/productorder/updatePayKind`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating pay kind:", error);
    throw error;
  }
};

// 입금 상태 업데이트
export const updateDepositStatus = async (data) => {
  try {
    const response = await superagent
      .put(`${BASE_API}/productorder/updateDepositStatus`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating deposit status:", error);
    throw error;
  }
};

// 진도율 업데이트
export const updateStudyPer = async (data) => {
  try {
    const response = await superagent.put(`${BASE_API}/productorder/updateStudyPer`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating study per:", error);
    throw error;
  }
};

// 오프라인 주문 등록
export const insertOffOrder = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/productorder/insertOffOrder`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting off order:", error);
    throw error;
  }
};

// 오프라인 주문 수정
export const updateOffOrder = async (data) => {
  try {
    const response = await superagent.put(`${BASE_API}/productorder/updateOffOrder`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating off order:", error);
    throw error;
  }
};

// 오프라인 주문 삭제
export const deleteOffOrder = async (data) => {
  try {
    const response = await superagent.del(`${BASE_API}/productorder/deleteOffOrder`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting off order:", error);
    throw error;
  }
};

// 환불 처리
export const insertRefund = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/productorder/insertRefund`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting refund:", error);
    throw error;
  }
};

// 환불 취소
export const deleteRefund = async (data) => {
  try {
    const response = await superagent.del(`${BASE_API}/productorder/deleteRefund`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting refund:", error);
    throw error;
  }
};

// 회원 정보 조회
export const getMemberView = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/memberView`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching member view:", error);
    throw error;
  }
};

// 회원 포인트 히스토리
export const getPointHistory = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/pointHistory`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching point history:", error);
    throw error;
  }
};

// 회원 쿠폰 목록
export const getMemberCouponList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/productorder/memberCouponList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching member coupon list:", error);
    throw error;
  }
};

// 회원 강의 목록 (온라인)
export const getMemberClassList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/productorder/memberClassList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching member class list:", error);
    throw error;
  }
};

// 회원 강의 목록 (오프라인)
export const getMemberOffClassList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/productorder/memberOffClassList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching member off class list:", error);
    throw error;
  }
};
