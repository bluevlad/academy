import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getLectureReplyDetail, deleteLectureReply } from "api/lecture/lectureReply";

function LectureReplyDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const leccode = queryParams.get("leccode");

  const [detail, setDetail] = useState({});
  const [columns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "작성자", accessor: "userNm", width: "15%" },
    { Header: "제목", accessor: "subjectTitle", width: "40%" },
    { Header: "내용", accessor: "content", width: "20%" }, // Added content column just in case
    { Header: "등록일", accessor: "regDt", width: "10%" },
    { Header: "관리", accessor: "action", width: "10%" },
  ]);
  const [rows, setRows] = useState([]);

  useEffect(() => {
    if (leccode) {
      fetchData(leccode);
    }
  }, [leccode]);

  const fetchData = async (code) => {
    try {
      const response = await getLectureReplyDetail({
        DETAIL_LECCODE: code,
        currentPageReply: 1,
        pageRowReply: 100
      });

      if (response) {
        if (response.detail) {
          setDetail(response.detail);
        }
        if (response.replyList) {
          const formattedRows = response.replyList.map((item, index) => ({
            no: index + 1,
            userNm: item.USER_NM || item.userNm || "-",
            subjectTitle: item.SUBJECT_TITLE || item.subjectTitle || "-", // Might be review title
            content: item.CONTENT || item.content || "-", // Assuming content field exist
            regDt: item.REG_DT || item.regDt || "-",
            action: (
              <Button variant="text" color="error" onClick={() => handleDelete(item.SEQ || item.seq)}>
                삭제
              </Button>
            )
          }));
          setRows(formattedRows);
        }
      }
    } catch (error) {
      console.error("Failed to fetch lecture reply details", error);
    }
  };

  const handleDelete = async (seq) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteLectureReply({ DELETE_LECCODE: leccode, DELETE_SEQ: seq });
        alert("삭제되었습니다.");
        fetchData(leccode);
      } catch (error) {
        console.error("Failed to delete reply", error);
        alert("삭제에 실패했습니다.");
      }
    }
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <Box pt={6} pb={3}>
        <Grid container spacing={6}>
          <Grid item xs={12}>
            <Card>
              <Box
                mx={2}
                mt={-3}
                py={3}
                px={2}
                variant="contained"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <Typography variant="h6" color="white">
                  수강 후기 관리 - {detail.SUBJECT_TITLE || detail.subjectTitle || "강의명 로딩중..."}
                </Typography>
              </Box>
              <Box pt={4} pb={3} px={3}>
                <Box mb={3}>
                  <Typography variant="body2">강사: {detail.TEACHER_NM || detail.teacherNm}</Typography>
                  <Typography variant="body2">코드: {detail.LECCODE || detail.leccode}</Typography>
                </Box>
                <DataTable
                  table={{ columns, rows }}
                  isSorted={false}
                  entriesPerPage={false}
                  showTotalEntries={false}
                  noEndBorder
                />
                <Box mt={4} display="flex" justifyContent="flex-end">
                  <Button
                    variant="outlined"
                    color="secondary"
                    onClick={() => navigate("/lecture/reply")}
                  >
                    목록으로
                  </Button>
                </Box>
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default LectureReplyDetail;
