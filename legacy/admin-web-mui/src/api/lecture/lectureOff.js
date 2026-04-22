import apiClient from "shared/api/client";

// ===========================================
// Offline Lecture API (오프라인 강의 관리)
// ===========================================

// 단과 강의 목록 조회
export const getLectureOffList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getLectureList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching offline lecture list:", error);
    throw error;
  }
};

// 단과 강의 상세 조회
export const getLectureOffDetail = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getLectureDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching offline lecture detail:", error);
    throw error;
  }
};

// 단과 강의 등록
export const insertLectureOff = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting offline lecture:", error);
    throw error;
  }
};

// 단과 강의 수정
export const updateLectureOff = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/updateLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error updating offline lecture:", error);
    throw error;
  }
};

// 단과 강의 삭제
export const deleteLectureOff = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting offline lecture:", error);
    throw error;
  }
};

// 강의 브릿지 목록 조회
export const getLectureBridgeList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getLectureBridgeList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture bridge list:", error);
    throw error;
  }
};

// 강의 브릿지 등록
export const insertLectureBridge = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertLectureBridge", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting lecture bridge:", error);
    throw error;
  }
};

// 강의 브릿지 삭제
export const deleteLectureBridge = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteLectureBridge", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture bridge:", error);
    throw error;
  }
};

// 강의 교재 목록 조회
export const getLectureBookList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getLectureBookList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture book list:", error);
    throw error;
  }
};

// 교재 검색 목록 조회
export const getBookSearchList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getBookSearchList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching book search list:", error);
    throw error;
  }
};

// 강의 교재 등록
export const insertLectureBook = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertLectureBook", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting lecture book:", error);
    throw error;
  }
};

// 강의 교재 삭제
export const deleteLectureBook = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteLectureBook", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture book:", error);
    throw error;
  }
};

// 강의 일정 목록 조회
export const getLectureDateList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getLectureDateList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture date list:", error);
    throw error;
  }
};

// 강의 일정 등록
export const insertLectureDate = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertLectureDate", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting lecture date:", error);
    throw error;
  }
};

// 강의 일정 삭제
export const deleteLectureDate = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteLectureDate", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting lecture date:", error);
    throw error;
  }
};

// 종합반 강의 목록 조회
export const getJongLectureList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getJongLectureList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching jong lecture list:", error);
    throw error;
  }
};

// 종합반 강의 상세 조회
export const getJongLectureDetail = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getJongLectureDetail", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching jong lecture detail:", error);
    throw error;
  }
};

// 종합반 강의 등록
export const insertJongLecture = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertJongLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting jong lecture:", error);
    throw error;
  }
};

// 종합반 강의 수정
export const updateJongLecture = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/updateJongLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error updating jong lecture:", error);
    throw error;
  }
};

// 종합반 강의 삭제
export const deleteJongLecture = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteJongLecture", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting jong lecture:", error);
    throw error;
  }
};

// 종합반 강의 구성 목록 조회
export const getJongLectureDetailList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getJongLectureDetailList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching jong lecture detail list:", error);
    throw error;
  }
};

// 종합반 강의 구성 등록
export const insertJongLectureDetail = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertJongLectureDetail", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting jong lecture detail:", error);
    throw error;
  }
};

// 종합반 강의 구성 삭제
export const deleteJongLectureDetail = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteJongLectureDetail", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting jong lecture detail:", error);
    throw error;
  }
};

// 선택형 종합반 목록 조회
export const getChoiceJongList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getChoiceJongList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching choice jong list:", error);
    throw error;
  }
};

// 선택형 종합반 등록
export const insertChoiceJong = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/insertChoiceJong", data);
    return response.data;
  } catch (error) {
    console.error("Error inserting choice jong:", error);
    throw error;
  }
};

// 선택형 종합반 삭제
export const deleteChoiceJong = async (data) => {
  try {
    const response = await apiClient.post("/lectureOff/deleteChoiceJong", data);
    return response.data;
  } catch (error) {
    console.error("Error deleting choice jong:", error);
    throw error;
  }
};

// 카테고리 목록 조회
export const getCategoryList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getCategoryList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching category list:", error);
    throw error;
  }
};

// 과목 목록 조회
export const getSubjectList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getSubjectList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching subject list:", error);
    throw error;
  }
};

// 강사 목록 조회
export const getTeacherList = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getTeacherList", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching teacher list:", error);
    throw error;
  }
};

// 다음 강의코드 조회
export const getNextLeccode = async (params) => {
  try {
    const response = await apiClient.get("/lectureOff/getNextLeccode", { params });
    return response.data;
  } catch (error) {
    console.error("Error fetching next leccode:", error);
    throw error;
  }
};

// 다음 종합반 SEQ 조회
export const getNextJongseq = async () => {
  try {
    const response = await apiClient.get("/lectureOff/getNextJongseq");
    return response.data;
  } catch (error) {
    console.error("Error fetching next jongseq:", error);
    throw error;
  }
};
