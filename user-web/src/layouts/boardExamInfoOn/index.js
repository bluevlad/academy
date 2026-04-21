import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getBoardExamInfoOnList } from "api/boardExamInfoOn";
import { createPaginationParams } from "utils/commonUtils";

function BoardExamInfoOn() {
  const [boardList, setBoardList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  useEffect(() => {
    loadBoardData();
  }, [currentPage]);

  const loadBoardData = async () => {
    setLoading(true);
    try {
      const params = createPaginationParams({ pageIndex: currentPage });
      const data = await getBoardExamInfoOnList(params);

      setBoardList(data.boardList || []);
      setPaginationInfo(data.paginationInfo || null);
      if (data.paginationInfo) {
        setTotalPages(
          Math.ceil(
            data.paginationInfo.totalRecordCount / data.paginationInfo.recordCountPerPage
          ) || 1
        );
      }
    } catch (error) {
      console.error("Failed to load board exam info on data:", error);
      setBoardList([]);
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const columns = [
    { Header: "No", accessor: "boardSeq", width: "10%", align: "center" },
    { Header: "제목", accessor: "subject", width: "40%", align: "left" },
    { Header: "공지", accessor: "noticeYn", width: "10%", align: "center" },
    { Header: "작성자", accessor: "regId", align: "center" },
    { Header: "작성일", accessor: "regDt", align: "center" },
    { Header: "조회수", accessor: "hits", align: "center" },
    { Header: "Action", accessor: "action", align: "center" },
  ];

  const rows = boardList.map((board) => ({
    boardSeq: (
      <MDTypography variant="caption" color="text" fontWeight="medium">
        {board.boardSeq}
      </MDTypography>
    ),
    subject: (
      <MDTypography variant="caption" color="text" fontWeight="medium">
        {board.subject}
      </MDTypography>
    ),
    noticeYn: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {board.noticeTopYn === "Y" ? "공지" : "일반"}
      </MDTypography>
    ),
    regId: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {board.regId}
      </MDTypography>
    ),
    regDt: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {board.regDt ? new Date(board.regDt).toLocaleDateString("ko-KR") : "-"}
      </MDTypography>
    ),
    hits: (
      <MDTypography variant="caption" color="text" fontWeight="medium">
        {board.hits}
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
                  시험 공고 (On)
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

export default BoardExamInfoOn;
