import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
import { getLectureList } from "api/lecture";

function OnlineLectureList() {
  const navigate = useNavigate();
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "강의코드", accessor: "leccode", width: "10%" },
    { Header: "카테고리", accessor: "categoryNm", width: "10%" },
    { Header: "학습형태", accessor: "learningNm", width: "10%" },
    { Header: "강사", accessor: "subjectTeacherNm", width: "10%" },
    { Header: "강좌명", accessor: "subjectTitle", width: "35%" },
    { Header: "상태", accessor: "isUseNm", width: "5%" },
    { Header: "등록일", accessor: "regDt", width: "10%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getLectureList({ currentPage: 1, pageRow: 100 });
      if (response && response.list) {
        const formattedRows = response.list.map((item, index) => ({
          no: index + 1,
          leccode: item.LECCODE,
          categoryNm: item.CATEGORY_NM,
          learningNm: item.LEARNING_NM,
          subjectTeacherNm: item.SUBJECT_TEACHER_NM,
          subjectTitle: (
            <MDTypography
              component={Link}
              to={`/lecture/online/detail?leccode=${item.LECCODE}`}
              variant="caption"
              color="text"
              fontWeight="medium"
            >
              {item.SUBJECT_TITLE}
            </MDTypography>
          ),
          isUseNm: item.ISUSENM,
          regDt: item.REG_DT,
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch lecture list", error);
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
                  Online Lecture List
                </MDTypography>
                <Button
                  variant="contained"
                  color="white"
                  onClick={() => navigate("/lecture/online/write")}
                  style={{ float: "right", marginTop: "-30px", color: "black" }}
                >
                  Create
                </Button>
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

export default OnlineLectureList;
