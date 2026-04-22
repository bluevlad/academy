import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getLectureYearList } from "api/manage/lectureYear";

function LectureYearList() {
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "연도", accessor: "yearDesc", width: "10%" },
    { Header: "강의명", accessor: "lectureNm", width: "30%" },
    { Header: "카테고리", accessor: "categoryNm", width: "15%" },
    { Header: "강사명", accessor: "teacherNm", width: "10%" },
    { Header: "수강인원", accessor: "studentCnt", align: "right" },
    { Header: "매출금액", accessor: "saleAmt", align: "right" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getLectureYearList({ pageIndex: 1, pageSize: 100 });
      if (response && response.data) {
        const formattedRows = response.data.map((item, index) => ({
          no: index + 1,
          yearDesc: item.yearDesc || item.lectureYear,
          lectureNm: item.lectureNm,
          categoryNm: item.categoryNm,
          teacherNm: item.teacherNm,
          studentCnt: item.studentCnt ? item.studentCnt.toLocaleString() : "0",
          saleAmt: item.saleAmt ? item.saleAmt.toLocaleString() : "0",
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch lecture year list", error);
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
                  Lecture Year List
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

export default LectureYearList;
