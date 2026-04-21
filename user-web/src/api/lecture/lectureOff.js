import superagent from "superagent";
import { BASE_API } from "../../constants/index";

// ===========================================
// Offline Lecture API (오프라인 강의 관리)
// ===========================================

// 단과 강의 목록 조회
export const getLectureOffList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getLectureList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching offline lecture list:", error);
    throw error;
  }
};

// 단과 강의 상세 조회
export const getLectureOffDetail = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getLectureDetail`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching offline lecture detail:", error);
    throw error;
  }
};

// 단과 강의 등록
export const insertLectureOff = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/insertLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting offline lecture:", error);
    throw error;
  }
};

// 단과 강의 수정
export const updateLectureOff = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/updateLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating offline lecture:", error);
    throw error;
  }
};

// 단과 강의 삭제
export const deleteLectureOff = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/deleteLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting offline lecture:", error);
    throw error;
  }
};

// 강의 브릿지 목록 조회
export const getLectureBridgeList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/lectureOff/getLectureBridgeList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture bridge list:", error);
    throw error;
  }
};

// 강의 브릿지 등록
export const insertLectureBridge = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/insertLectureBridge`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting lecture bridge:", error);
    throw error;
  }
};

// 강의 브릿지 삭제
export const deleteLectureBridge = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/deleteLectureBridge`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture bridge:", error);
    throw error;
  }
};

// 강의 교재 목록 조회
export const getLectureBookList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/lectureOff/getLectureBookList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture book list:", error);
    throw error;
  }
};

// 교재 검색 목록 조회
export const getBookSearchList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getBookSearchList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching book search list:", error);
    throw error;
  }
};

// 강의 교재 등록
export const insertLectureBook = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/insertLectureBook`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting lecture book:", error);
    throw error;
  }
};

// 강의 교재 삭제
export const deleteLectureBook = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/deleteLectureBook`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture book:", error);
    throw error;
  }
};

// 강의 일정 목록 조회
export const getLectureDateList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/lectureOff/getLectureDateList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching lecture date list:", error);
    throw error;
  }
};

// 강의 일정 등록
export const insertLectureDate = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/insertLectureDate`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting lecture date:", error);
    throw error;
  }
};

// 강의 일정 삭제
export const deleteLectureDate = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/deleteLectureDate`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting lecture date:", error);
    throw error;
  }
};

// 종합반 강의 목록 조회
export const getJongLectureList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/lectureOff/getJongLectureList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching jong lecture list:", error);
    throw error;
  }
};

// 종합반 강의 상세 조회
export const getJongLectureDetail = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/lectureOff/getJongLectureDetail`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching jong lecture detail:", error);
    throw error;
  }
};

// 종합반 강의 등록
export const insertJongLecture = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/insertJongLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting jong lecture:", error);
    throw error;
  }
};

// 종합반 강의 수정
export const updateJongLecture = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/updateJongLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error updating jong lecture:", error);
    throw error;
  }
};

// 종합반 강의 삭제
export const deleteJongLecture = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/deleteJongLecture`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting jong lecture:", error);
    throw error;
  }
};

// 종합반 강의 구성 목록 조회
export const getJongLectureDetailList = async (params) => {
  try {
    const response = await superagent
      .get(`${BASE_API}/lectureOff/getJongLectureDetailList`)
      .query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching jong lecture detail list:", error);
    throw error;
  }
};

// 종합반 강의 구성 등록
export const insertJongLectureDetail = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/lectureOff/insertJongLectureDetail`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting jong lecture detail:", error);
    throw error;
  }
};

// 종합반 강의 구성 삭제
export const deleteJongLectureDetail = async (data) => {
  try {
    const response = await superagent
      .post(`${BASE_API}/lectureOff/deleteJongLectureDetail`)
      .send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting jong lecture detail:", error);
    throw error;
  }
};

// 선택형 종합반 목록 조회
export const getChoiceJongList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getChoiceJongList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching choice jong list:", error);
    throw error;
  }
};

// 선택형 종합반 등록
export const insertChoiceJong = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/insertChoiceJong`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error inserting choice jong:", error);
    throw error;
  }
};

// 선택형 종합반 삭제
export const deleteChoiceJong = async (data) => {
  try {
    const response = await superagent.post(`${BASE_API}/lectureOff/deleteChoiceJong`).send(data);
    return response.body;
  } catch (error) {
    console.error("Error deleting choice jong:", error);
    throw error;
  }
};

// 카테고리 목록 조회
export const getCategoryList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getCategoryList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching category list:", error);
    throw error;
  }
};

// 과목 목록 조회
export const getSubjectList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getSubjectList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching subject list:", error);
    throw error;
  }
};

// 강사 목록 조회
export const getTeacherList = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getTeacherList`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching teacher list:", error);
    throw error;
  }
};

// 다음 강의코드 조회
export const getNextLeccode = async (params) => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getNextLeccode`).query(params);
    return response.body;
  } catch (error) {
    console.error("Error fetching next leccode:", error);
    throw error;
  }
};

// 다음 종합반 SEQ 조회
export const getNextJongseq = async () => {
  try {
    const response = await superagent.get(`${BASE_API}/lectureOff/getNextJongseq`);
    return response.body;
  } catch (error) {
    console.error("Error fetching next jongseq:", error);
    throw error;
  }
};
