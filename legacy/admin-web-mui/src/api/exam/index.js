import apiClient from "shared/api/client";

export const fetchExamData = async (page = 1) => {
  const userId = localStorage.getItem("userId");
  try {
    const response = await apiClient.get("/exam/getExamList", {
      params: { curPage: page, userId },
    });

    return {
      examList: response.data.examList.map((exam) => ({
        exam_id: exam.exam_id,
        exam_nm: exam.exam_nm,
        exam_year: exam.exam_year,
        exam_round: exam.exam_round,
        exam_open: exam.exam_open,
        exam_end: exam.exam_end,
        exam_period: exam.exam_period,
        exam_time: exam.exam_time,
        isUse: exam.isUse,
        use_flag: exam.use_flag,
        set_id: exam.set_id,
        reg_dt: exam.reg_dt,
        reg_id: exam.reg_id,
        upd_dt: exam.upd_dt,
        upd_id: exam.upd_id,
        user_id: exam.user_id,
      })),
      paginationInfo: {
        currentPageNo: response.data.paginationInfo.currentPageNo,
        recordCountPerPage: parseInt(response.data.paginationInfo.recordCountPerPage),
        pageSize: parseInt(response.data.paginationInfo.pageSize),
        totalRecordCount: parseInt(response.data.paginationInfo.totalRecordCount),
        totalPageCount: parseInt(response.data.paginationInfo.totalPageCount) || 1, // 기본값 설정
        firstPageNoOnPageList: parseInt(response.data.paginationInfo.firstPageNoOnPageList),
        lastPageNoOnPageList: parseInt(response.data.paginationInfo.lastPageNoOnPageList),
        firstRecordIndex: parseInt(response.data.paginationInfo.firstRecordIndex),
        lastRecordIndex: parseInt(response.data.paginationInfo.lastRecordIndex),
        lastPageNo: parseInt(response.data.paginationInfo.lastPageNo),
        firstPageNo: parseInt(response.data.paginationInfo.firstPageNo),
      },
    };
  } catch (error) {
    console.error("Error fetching exam data:", error);
    return { examList: [], paginationInfo: { totalPageCount: 1 } }; // 기본값 반환
  }
};

export const fetchExamDetailData = async () => {
  try {
    const url = new URL(window.location.href);
    const param = new URLSearchParams(url.search);
    const id = param.get("id");
    const response = await apiClient.get("/exam/getExamView", {
      params: { examId: id },
    });

    return {
      queList: response.data.QueList.map((que) => ({
        que_id: que.que_id,
        que_title: que.que_title,
        que_desc: que.que_desc,
        pass_ans: que.pass_ans,
        que_range: que.que_range,
        que_level: que.que_level,
        que_count: que.que_count,
        que_type: que.que_type,
        passAns: que.pass_ans,
        ans_desc: que.ans_desc,
        ans_view1: que.ans_view1,
        ans_view2: que.ans_view2,
        ans_view3: que.ans_view3,
        ans_view4: que.ans_view4,
        ans_view5: que.ans_view5,
      })),
      examDetail: {
        exam_id: response.data.examDetail.exam_id,
        exam_nm: response.data.examDetail.exam_nm,
        exam_year: response.data.examDetail.exam_year,
        exam_round: response.data.examDetail.exam_round,
        exam_open: response.data.examDetail.exam_open,
        exam_end: response.data.examDetail.exam_end,
        exam_period: response.data.examDetail.exam_period,
        exam_time: response.data.examDetail.exam_time,
        isUse: response.data.examDetail.isUse,
        use_flag: response.data.examDetail.use_flag,
        set_id: response.data.examDetail.set_id,
        reg_dt: response.data.examDetail.reg_dt,
        reg_id: response.data.examDetail.reg_id,
        upd_dt: response.data.examDetail.upd_dt,
        upd_id: response.data.examDetail.upd_id,
      },
    };
  } catch (error) {
    console.error("Error fetching exam data:", error);
    return { queList: [], examDetail: {} }; // 기본값 반환
  }
};

export const fetchExamResultlData = async () => {
  try {
    const url = new URL(window.location.href);
    const param = new URLSearchParams(url.search);
    const id = param.get("id");
    const userId = localStorage.getItem("userId");
    const response = await apiClient.get("/exam/getExamEdit", {
      params: { examId: id, userId },
    });

    return {
      queList: response.data.QueList.map((que) => ({
        que_id: que.que_id,
        que_title: que.que_title,
        que_desc: que.que_desc,
        pass_ans: que.pass_ans,
        que_range: que.que_range,
        que_level: que.que_level,
        que_count: que.que_count,
        que_type: que.que_type,
        passAns: que.pass_ans,
        ans_desc: que.ans_desc,
        ans_view1: que.ans_view1,
        ans_view2: que.ans_view2,
        ans_view3: que.ans_view3,
        ans_view4: que.ans_view4,
        ans_view5: que.ans_view5,
        answer: que.answer,
        currect_yn: que.currect_yn,
      })),
      examDetail: {
        exam_id: response.data.examDetail.exam_id,
        exam_nm: response.data.examDetail.exam_nm,
        exam_year: response.data.examDetail.exam_year,
        exam_round: response.data.examDetail.exam_round,
        exam_open: response.data.examDetail.exam_open,
        exam_end: response.data.examDetail.exam_end,
        exam_period: response.data.examDetail.exam_period,
        exam_time: response.data.examDetail.exam_time,
        isUse: response.data.examDetail.isUse,
        use_flag: response.data.examDetail.use_flag,
        set_id: response.data.examDetail.set_id,
        reg_dt: response.data.examDetail.reg_dt,
        reg_id: response.data.examDetail.reg_id,
        upd_dt: response.data.examDetail.upd_dt,
        upd_id: response.data.examDetail.upd_id,
      },
    };
  } catch (error) {
    console.error("Error fetching exam data:", error);
    return { queList: [], examDetail: {} }; // 기본값 반환
  }
};
