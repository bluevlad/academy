// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Badge from "@mui/material/Badge";


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
      <Typography variant="caption" color="text" fontWeight="medium">
        {exam.exam_nm}
      </Typography>
    ),
    exam_year: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {exam.exam_year}
      </Typography>
    ),
    exam_round: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {exam.exam_round}회
      </Typography>
    ),
    exam_period: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {exam.exam_period}
      </Typography>
    ),
    exam_time: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {exam.exam_time}분
      </Typography>
    ),
    isUse: (
      <Box ml={-1}>
        <Badge
          badgeContent={exam.isUse === "Y" ? "사용" : "미사용"}
          color={exam.isUse === "Y" ? "success" : "dark"}
          variant="contained"
          size="sm"
        />
      </Box>
    ),
    use_flag: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {exam.use_flag === "Y" ? "응시가능" : "응시불가"}
      </Typography>
    ),
    reg_dt: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {exam.reg_dt ? new Date(exam.reg_dt).toLocaleDateString("ko-KR") : "-"}
      </Typography>
    ),
    action: (
      <Typography component="a" href="#" color="text">
        <Icon>more_vert</Icon>
      </Typography>
    ),
  }));

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
                  모의고사 목록
                </Typography>
              </Box>
              <Box pt={3}>
                {loading ? (
                  <Box p={3} textAlign="center">
                    <Typography variant="caption">로딩 중...</Typography>
                  </Box>
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
                      <Box
                        p={3}
                        display="flex"
                        justifyContent="space-between"
                        alignItems="center"
                      >
                        <Box>
                          <Typography variant="caption" color="text">
                            전체 {paginationInfo.totalRecordCount}건 중{" "}
                            {paginationInfo.firstRecordIndex + 1} -{" "}
                            {Math.min(
                              paginationInfo.lastRecordIndex + 1,
                              paginationInfo.totalRecordCount
                            )}
                            건 표시{" "}
                          </Typography>
                        </Box>
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
                      </Box>
                    )}
                  </>
                )}
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default Exam;
