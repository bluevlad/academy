import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Lecture Reply API (강의 후기 관리)
// ===========================================

// 강의 후기 목록 조회
export const getLectureReplyList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureReply/list`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture reply list:", error);
    throw error;
  }
};

// 강의 후기 상세 조회
export const getLectureReplyDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureReply/detail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture reply detail:", error);
    throw error;
  }
};

// 강의 후기 삭제
export const deleteLectureReply = async (data) => {
  try {
    const response = await superagent.del(`${BASE_API}/lectureReply/delete`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture reply:", error);
    throw error;
  }
};
