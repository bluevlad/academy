import apiClient from "shared/api/client";

// =====================================================
// 이벤트 관리 API
// =====================================================

// 이벤트 목록 조회
export const getEventList = async (params) => {
  try {
    const response = await apiClient.get("/event/getEventList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching event list:", error);
    throw error;
  }
};

// 이벤트 상세 조회
export const getEventDetail = async (params) => {
  try {
    const response = await apiClient.get("/event/getEventDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching event detail:", error);
    throw error;
  }
};

// 이벤트 등록
export const insertEvent = async (data) => {
  try {
    const response = await apiClient.post("/event/insertEvent", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting event:", error);
    throw error;
  }
};

// 이벤트 수정
export const updateEvent = async (data) => {
  try {
    const response = await apiClient.post("/event/updateEvent", data);
    return response.data;
  } catch (error) {
    console.error("Error updating event:", error);
    throw error;
  }
};

// 이벤트 삭제
export const deleteEvent = async (data) => {
  try {
    const response = await apiClient.post("/event/deleteEvent", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting event:", error);
    throw error;
  }
};

// =====================================================
// 이벤트 댓글 (OPTION2)
// =====================================================

// 이벤트 댓글 목록 조회
export const getEventCommentList = async (params) => {
  try {
    const response = await apiClient.get("/event/getEventCommentList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching event comment list:", error);
    throw error;
  }
};

// 이벤트 댓글 등록
export const insertEventComment = async (data) => {
  try {
    const response = await apiClient.post("/event/insertEventComment", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting event comment:", error);
    throw error;
  }
};

// 이벤트 댓글 삭제
export const deleteEventComment = async (data) => {
  try {
    const response = await apiClient.post("/event/deleteEventComment", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting event comment:", error);
    throw error;
  }
};

// =====================================================
// 이벤트 결과/참여자
// =====================================================

// 이벤트 참여자 목록 조회
export const getEventResultList = async (params) => {
  try {
    const response = await apiClient.get("/event/getEventResultList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching event result list:", error);
    throw error;
  }
};
