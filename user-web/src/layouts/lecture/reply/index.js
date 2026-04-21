import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
import { getLectureReplyList } from "api/lecture/lectureReply";

function LectureReplyList() {
  const navigate = useNavigate();
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "과목", accessor: "subjectNm", width: "10%" },
    { Header: "강사", accessor: "teacherNm", width: "10%" },
    { Header: "강좌명", accessor: "subjectTitle", width: "40%" },
    { Header: "작성자", accessor: "userName", width: "10%" },
    { Header: "등록일", accessor: "regDt", width: "10%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getLectureReplyList({ currentPage: 1, pageRow: 100 });
      if (response && response.list) {
        const formattedRows = response.list.map((item, index) => ({
          no: index + 1,
          subjectNm: item.SUBJECT_NM || "-",
          teacherNm: item.TEACHER_NM || "-",
          subjectTitle: (
            <MDTypography
              component={Link}
              to={`/lecture/reply/detail?leccode=${item.LECCODE}`}
              variant="caption"
              color="text"
              fontWeight="medium"
            >
              {item.SUBJECT_TITLE || "-"}
            </MDTypography>
          ),
          userName: item.USER_NM || "-",
          regDt: item.REG_DT || "-",
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch lecture reply list", error);
    }
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox pt={6} pb={3}>
        <Grid container spacing={6}>
          <Grid item xs={12}>
            <Card>
              <MDBox
                mx={2}
                mt={-3}
                py={3}
                px={2}
                variant="gradient"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <MDTypography variant="h6" color="white">
                  Lecture Reply List
                </MDTypography>
              </MDBox>
              <MDBox pt={3}>
                <DataTable
                  table={{ columns, rows }}
                  isSorted={false}
                  entriesPerPage={false}
                  showTotalEntries={false}
                  noEndBorder
                />
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default LectureReplyList;
