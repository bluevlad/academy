import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// =====================================================
// Book Comment (Review) API
// =====================================================

// 도서 댓글/후기 목록 조회
export const getBookCmmntList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/book/cmmnt/getBookCmmntList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching book comment list:", error);
    throw error;
  }
};

// 도서 댓글/후기 상세 조회
export const getBookCmmntDetail = async (bookCmmntSeq) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/book/cmmnt/getBookCmmntDetail`)
      .query({ bookCmmntSeq });
    return response.body;
  } catch (error) {
    console.error("Error fetching book comment detail:", error);
    throw error;
  }
};

// 도서 댓글/후기 등록
export const insertBookCmmnt = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/book/cmmnt/insertBookCmmnt`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting book comment:", error);
    throw error;
  }
};

// 도서 댓글/후기 수정
export const updateBookCmmnt = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/book/cmmnt/updateBookCmmnt`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating book comment:", error);
    throw error;
  }
};

// 도서 댓글/후기 삭제
export const deleteBookCmmnt = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/book/cmmnt/deleteBookCmmnt`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting book comment:", error);
    throw error;
  }
};
