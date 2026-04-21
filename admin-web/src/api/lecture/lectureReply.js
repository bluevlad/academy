import apiClient from "shared/api/client";

// ===========================================
// Lecture Reply API (강의 후기 관리)
// ===========================================

// 강의 후기 목록 조회
export const getLectureReplyList = async (params) => {
  try {
    const response = await apiClient.get("/lectureReply/list", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture reply list:", error);
    throw error;
  }
};

// 강의 후기 상세 조회
export const getLectureReplyDetail = async (params) => {
  try {
    const response = await apiClient.get("/lectureReply/detail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture reply detail:", error);
    throw error;
  }
};

// 강의 후기 삭제
export const deleteLectureReply = async (params) => {
  try {
    const response = await apiClient.delete("/lectureReply/delete", { params });
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture reply:", error);
    throw error;
  }
};
