import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import { useNavigate } from "react-router-dom";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";

import TextField from "@mui/material/TextField";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getNoteList } from "api/note";

function NoteList() {
  const navigate = useNavigate();
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    searchNoteContent: "",
    pageIndex: 1,
    pageUnit: 10,
  });
  const [loading, setLoading] = useState(false);

  const formatTableData = (data) => {
    return {
      columns: [
        { Header: "No", accessor: "noteNo", align: "center" },
        { Header: "내용", accessor: "noteContent", align: "left" },
        { Header: "받는사람", accessor: "recvId", align: "center" },
        { Header: "읽음확인", accessor: "readYn", align: "center" },
        { Header: "보낸날짜", accessor: "sendDt", align: "center" },
      ],
      rows:
        data?.map((item) => ({
          noteNo: item.NOTE_NO,
          noteContent: (
            <Typography
              component="a"
              href="#"
              variant="caption"
              color="text"
              fontWeight="medium"
              onClick={(e) => {
                e.preventDefault();
                navigate(`/note/detail?noteNo=${item.NOTE_NO}`);
              }}
              sx={{ cursor: "pointer" }}
            >
              {item.NOTE_CONTENT?.length > 20
                ? item.NOTE_CONTENT.substring(0, 20) + "..."
                : item.NOTE_CONTENT}
            </Typography>
          ),
          recvId: (
            <Typography variant="caption" color="text" fontWeight="medium">
              {item.RECV_ID}
            </Typography>
          ),
          readYn: (
            <Typography variant="caption" color="text" fontWeight="medium">
              {item.READ_YN === "Y" ? "읽음" : "안읽음"}
            </Typography>
          ),
          sendDt: (
            <Typography variant="caption" color="text" fontWeight="medium">
              {item.SEND_DT}
            </Typography>
          ),
        })) || [],
    };
  };

  const loadData = async () => {
    setLoading(true);
    try {
      const result = await getNoteList(searchParams);
      if (result && result.data) {
        setTableData(formatTableData(result.data));
      }
    } catch (error) {
      console.error(error);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, [searchParams.pageIndex]);

  const handleSearch = () => {
    setSearchParams((prev) => ({ ...prev, pageIndex: 1 }));
    loadData();
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
                display="flex"
                justifyContent="space-between"
                alignItems="center"
              >
                <Typography variant="h6" color="white">
                  쪽지 관리
                </Typography>
                <Button
                  variant="contained"
                  color="dark"
                  onClick={() => navigate("/note/detail")}
                >
                  <Icon sx={{ fontWeight: "bold" }}>add</Icon>
                  &nbsp;보내기
                </Button>
              </Box>

              <Box p={3}>
                <Grid container spacing={2} mb={2} alignItems="center">
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="내용 검색"
                      value={searchParams.searchNoteContent}
                      onChange={(e) =>
                        setSearchParams((prev) => ({
                          ...prev,
                          searchNoteContent: e.target.value,
                        }))
                      }
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={2}>
                    <Button variant="contained" color="info" onClick={handleSearch} fullWidth>
                      검색
                    </Button>
                  </Grid>
                </Grid>

                <DataTable
                  table={tableData}
                  isSorted={false}
                  entriesPerPage={false}
                  showTotalEntries={false}
                  noEndBorder
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

export default NoteList;
