// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDBadge from "components/MDBadge";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { fetchExamData } from "api/exam";
import { useState, useEffect } from "react";

function Exam() {
  const [examList, setExamList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  useEffect(() => {
    const loadExamData = async () => {
      setLoading(true);
      try {
        const data = await fetchExamData(currentPage);
        setExamList(data.examList || []);
        setPaginationInfo(data.paginationInfo || null);
        setTotalPages(data.paginationInfo?.totalPageCount || 1);
      } catch (error) {
        console.error("Failed to load exam data:", error);
      } finally {
        setLoading(false);
      }
    };

    loadExamData();
  }, [currentPage]);

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const columns = [
    { Header: "시험명", accessor: "exam_nm", width: "20%", align: "left" },
    { Header: "년도", accessor: "exam_year", align: "center" },
    { Header: "회차", accessor: "exam_round", align: "center" },
    { Header: "접수기간", accessor: "exam_period", width: "20%", align: "center" },
    { Header: "시험시간", accessor: "exam_time", align: "center" },
    { Header: "사용여부", accessor: "isUse", align: "center" },
    { Header: "응시여부", accessor: "use_flag", align: "center" },
    { Header: "등록일", accessor: "reg_dt", align: "center" },
    { Header: "Actions", accessor: "action", align: "center" },
  ];

  const rows = examList.map((exam) => ({
    exam_nm: (
      <MDTypography variant="caption" color="text" fontWeight="medium">
        {exam.exam_nm}
      </MDTypography>
    ),
    exam_year: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {exam.exam_year}
      </MDTypography>
    ),
    exam_round: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {exam.exam_round}회
      </MDTypography>
    ),
    exam_period: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {exam.exam_period}
      </MDTypography>
    ),
    exam_time: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {exam.exam_time}분
      </MDTypography>
    ),
    isUse: (
      <MDBox ml={-1}>
        <MDBadge
          badgeContent={exam.isUse === "Y" ? "사용" : "미사용"}
          color={exam.isUse === "Y" ? "success" : "dark"}
          variant="gradient"
          size="sm"
        />
      </MDBox>
    ),
    use_flag: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {exam.use_flag === "Y" ? "응시가능" : "응시불가"}
      </MDTypography>
    ),
    reg_dt: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {exam.reg_dt ? new Date(exam.reg_dt).toLocaleDateString("ko-KR") : "-"}
      </MDTypography>
    ),
    action: (
      <MDTypography component="a" href="#" color="text">
        <Icon>more_vert</Icon>
      </MDTypography>
    ),
  }));

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
                  모의고사 목록
                </MDTypography>
              </MDBox>
              <MDBox pt={3}>
                {loading ? (
                  <MDBox p={3} textAlign="center">
                    <MDTypography variant="caption">로딩 중...</MDTypography>
                  </MDBox>
                ) : (
                  <>
                    <DataTable
                      table={{ columns, rows }}
                      isSorted={false}
                      entriesPerPage={false}
                      showTotalEntries={false}
                      noEndBorder
                    />
                    {paginationInfo && (
                      <MDBox
                        p={3}
                        display="flex"
                        justifyContent="space-between"
                        alignItems="center"
                      >
                        <MDBox>
                          <MDTypography variant="caption" color="text">
                            전체 {paginationInfo.totalRecordCount}건 중{" "}
                            {paginationInfo.firstRecordIndex + 1} -{" "}
                            {Math.min(
                              paginationInfo.lastRecordIndex + 1,
                              paginationInfo.totalRecordCount
                            )}
                            건 표시{" "}
                          </MDTypography>
                        </MDBox>
                        <Stack spacing={2}>
                          <Pagination
                            count={totalPages}
                            page={currentPage}
                            onChange={handlePageChange}
                            color="primary"
                            shape="rounded"
                            showFirstButton
                            showLastButton
                          />
                        </Stack>
                      </MDBox>
                    )}
                  </>
                )}
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default Exam;
