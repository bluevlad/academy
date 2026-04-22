import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Badge from "@mui/material/Badge";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";

// API
import { getBoardList } from "api/board";

function BoardAll() {
  const [boardList, setBoardList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  useEffect(() => {
    loadData();
  }, [currentPage]);

  const loadData = async () => {
    setLoading(true);
    try {
      const data = await getBoardList({ pageIndex: currentPage });
      setBoardList(data.boardList || []);
      setPaginationInfo(data.paginationInfo);
      if (data.paginationInfo) {
        setTotalPages(
          Math.ceil(
            data.paginationInfo.totalRecordCount / data.paginationInfo.recordCountPerPage
          ) || 1
        );
      }
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const columns = [
    { Header: "번호", accessor: "boardSeq", align: "center", width: "10%" },
    { Header: "제목", accessor: "subject", align: "left", width: "40%" },
    { Header: "작성자", accessor: "regId", align: "center", width: "15%" },
    { Header: "등록일", accessor: "regDt", align: "center", width: "15%" },
    { Header: "조회수", accessor: "hits", align: "center", width: "10%" },
    { Header: "상태", accessor: "isUse", align: "center", width: "10%" },
  ];

  const rows = boardList.map((item) => ({
    boardSeq: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {item.boardSeq}
      </Typography>
    ),
    subject: (
      <Typography variant="button" color="text" fontWeight="medium">
        {item.subject}
      </Typography>
    ),
    regId: (
      <Typography variant="caption" color="text">
        {item.regId}
      </Typography>
    ),
    regDt: (
      <Typography variant="caption" color="text">
        {item.regDt}
      </Typography>
    ),
    hits: (
      <Typography variant="caption" color="text">
        {item.hits}
      </Typography>
    ),
    isUse: (
      <Badge
        badgeContent={item.isUse === "Y" ? "사용" : "미사용"}
        color={item.isUse === "Y" ? "success" : "secondary"}
        variant="contained"
        size="sm"
      />
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
                bgColor="primary"
                borderRadius="lg"
                coloredShadow="primary"
              >
                <Typography variant="h6" color="white">
                  게시물 전체 조회
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
                            Total {paginationInfo.totalRecordCount} records
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

export default BoardAll;
