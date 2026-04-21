import apiClient from "shared/api/client";

// 교재 목록 조회
export const fetchBookList = async (params) => {
  try {
    const response = await apiClient.get("/book/list", { params });

    return response.data;
  } catch (error) {
    console.error("Error fetching book list:", error);
    return { list: [], totalCount: 0, totalPage: 0, currentPage: 1 };
  }
};

// 교재 상세 조회
export const fetchBookView = async (params) => {
  try {
    const response = await apiClient.get("/book/view", { params });

    return response.data;
  } catch (error) {
    console.error("Error fetching book view:", error);
    return null;
  }
};

// 교재 등록을 위한 기본 데이터 조회
export const fetchBookWriteData = async () => {
  try {
    const response = await apiClient.get("/book/writeData");

    return response.data;
  } catch (error) {
    console.error("Error fetching book write data:", error);
    return { kindlist: [], formlist: [], subjectteacherlist: [] };
  }
};

// 교재 등록
export const saveBook = async (bookData) => {
  try {
    const response = await apiClient.post("/book/save", bookData);

    return response.data;
  } catch (error) {
    console.error("Error saving book:", error);
    throw error;
  }
};

// 교재 수정
export const updateBook = async (bookData) => {
  try {
    const response = await apiClient.put("/book/update", bookData);

    return response.data;
  } catch (error) {
    console.error("Error updating book:", error);
    throw error;
  }
};

// 교재 삭제
export const deleteBook = async (params) => {
  try {
    const response = await apiClient.delete("/book/delete", { params });

    return response.data;
  } catch (error) {
    console.error("Error deleting book:", error);
    throw error;
  }
};

// 교재 관련도서 전체 삭제
export const deleteBookAll = async (params) => {
  try {
    const response = await apiClient.delete("/book/deleteAll", { params });

    return response.data;
  } catch (error) {
    console.error("Error deleting all related books:", error);
    throw error;
  }
};

// 교재 판매 목록 조회
export const fetchBookSellList = async (params) => {
  try {
    const response = await apiClient.get("/book/sellList", { params });

    return response.data;
  } catch (error) {
    console.error("Error fetching book sell list:", error);
    return { list: [], totalCount: 0, totalPage: 0, currentPage: 1 };
  }
};

// 교재 판매 목록 엑셀 데이터
export const fetchBookSellListExcel = async (params) => {
  try {
    const response = await apiClient.get("/book/sellListExcel", { params });

    return response.data;
  } catch (error) {
    console.error("Error fetching book sell list excel:", error);
    return { list: [] };
  }
};
