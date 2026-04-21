import { useState, useEffect, useMemo } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Chip from "@mui/material/Chip";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";


// Layout
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// Shared Components
import { ServerDataTable } from "shared/components/DataTable";

// API
import { getBoardList } from "api/board";

function Board() {
  const [boardList, setBoardList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [paginationInfo, setPaginationInfo] = useState({});

  useEffect(() => {
    loadBoardData();
  }, [currentPage, pageSize]);

  const loadBoardData = async () => {
    setLoading(true);
    try {
      const params = {
        currentPageNo: currentPage,
        recordCountPerPage: pageSize,
      };
      const data = await getBoardList(params);
      setBoardList(data.boardList || []);
      setPaginationInfo(data.paginationInfo || {});
    } catch (error) {
      console.error("Failed to load board data:", error);
    } finally {
      setLoading(false);
    }
  };

  // TanStack Table 컬럼 정의
  const columns = useMemo(
    () => [
      { accessorKey: "boardMngSeq", header: "게시판구분", size: 100 },
      { accessorKey: "boardSeq", header: "게시판번호", size: 100 },
      { accessorKey: "subject", header: "게시판제목", size: 200 },
      {
        accessorKey: "noticeTopYn",
        header: "공지여부",
        size: 80,
        cell: ({ getValue }) => (
          <Chip
            label={getValue() === "Y" ? "공지" : "일반"}
            size="small"
            color={getValue() === "Y" ? "warning" : "default"}
            variant="outlined"
          />
        ),
      },
      {
        accessorKey: "openYn",
        header: "공개여부",
        size: 80,
        cell: ({ getValue }) => (getValue() === "Y" ? "공개" : "비공개"),
      },
      {
        accessorKey: "isUse",
        header: "사용여부",
        size: 80,
        cell: ({ getValue }) => (
          <Chip
            label={getValue() === "Y" ? "사용" : "미사용"}
            size="small"
            color={getValue() === "Y" ? "success" : "default"}
            variant="outlined"
          />
        ),
      },
      { accessorKey: "hits", header: "열람수", size: 70 },
      {
        accessorKey: "regDt",
        header: "등록일",
        size: 100,
        cell: ({ getValue }) =>
          getValue() ? new Date(getValue()).toLocaleDateString("ko-KR") : "-",
      },
      { accessorKey: "regId", header: "등록자", size: 90 },
      {
        id: "action",
        header: "",
        size: 50,
        cell: () => (
          <Icon sx={{ cursor: "pointer", color: "text.secondary" }}>more_vert</Icon>
        ),
      },
    ],
    []
  );

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
                  게시판 목록
                </Typography>
              </Box>
              <Box p={2}>
                <ServerDataTable
                  columns={columns}
                  data={boardList}
                  pagination={paginationInfo}
                  onPageChange={(page) => setCurrentPage(page)}
                  onPageSizeChange={(size) => {
                    setPageSize(size);
                    setCurrentPage(1);
                  }}
                  loading={loading}
                  canSearch={false}
                />
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default Board;
