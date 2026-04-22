/* eslint-disable prettier/prettier */
import apiClient from "shared/api/client";

// =====================================================
// 제휴사 마스터 (COOP_MST)
// =====================================================

// 제휴사 목록 조회
export const fetchCoopList = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop list:", error);
    return { coopList: [], paginationInfo: {} };
  }
};

// 제휴사 상세 조회
export const fetchCoopDetail = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop detail:", error);
    return {};
  }
};

// 제휴사 등록
export const insertCoop = async (data) => {
  try {
    const response = await apiClient.post("/coop/insertCoop", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error inserting coop:", error);
    throw error;
  }
};

// 제휴사 수정
export const updateCoop = async (data) => {
  try {
    const response = await apiClient.post("/coop/updateCoop", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error updating coop:", error);
    throw error;
  }
};

// 제휴사 삭제
export const deleteCoop = async (data) => {
  try {
    const response = await apiClient.post("/coop/deleteCoop", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error deleting coop:", error);
    throw error;
  }
};

// =====================================================
// 제휴사 IP (COOP_USE_IP)
// =====================================================

// 제휴사 IP 목록 조회
export const fetchCoopIpList = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopIpList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop IP list:", error);
    return { coopIpList: [] };
  }
};

// 제휴사 IP 등록
export const insertCoopIp = async (data) => {
  try {
    const response = await apiClient.post("/coop/insertCoopIp", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error inserting coop IP:", error);
    throw error;
  }
};

// 제휴사 IP 삭제
export const deleteCoopIp = async (data) => {
  try {
    const response = await apiClient.post("/coop/deleteCoopIp", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error deleting coop IP:", error);
    throw error;
  }
};

// =====================================================
// 제휴사 게시판 (TB_BOARD_MEMBERSHIP)
// =====================================================

// 제휴사 게시판 목록 조회
export const fetchCoopBoardList = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopBoardList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop board list:", error);
    return { coopBoardList: [], paginationInfo: {} };
  }
};

// 제휴사 게시판 상세 조회
export const fetchCoopBoardDetail = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopBoardDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop board detail:", error);
    return {};
  }
};

// 제휴사 게시판 등록
export const insertCoopBoard = async (data) => {
  try {
    const response = await apiClient.post("/coop/insertCoopBoard", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error inserting coop board:", error);
    throw error;
  }
};

// 제휴사 게시판 수정
export const updateCoopBoard = async (data) => {
  try {
    const response = await apiClient.post("/coop/updateCoopBoard", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error updating coop board:", error);
    throw error;
  }
};

// 제휴사 게시판 삭제
export const deleteCoopBoard = async (data) => {
  try {
    const response = await apiClient.post("/coop/deleteCoopBoard", new URLSearchParams(data));
    return response.data;
  } catch (error) {
    console.error("Error deleting coop board:", error);
    throw error;
  }
};

// =====================================================
// 제휴사 주문
// =====================================================

// 제휴사 주문 목록 조회
export const fetchCoopOrderList = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopOrderList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop order list:", error);
    return { coopOrderList: [], paginationInfo: {} };
  }
};

// 제휴사 결제 상세 조회
export const fetchCoopPayDetailList = async (params) => {
  try {
    const response = await apiClient.get("/coop/getCoopPayDetailList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching coop pay detail:", error);
    return { coopPayDetailList: [] };
  }
};
