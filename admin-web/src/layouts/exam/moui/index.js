import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
// API
import { getMouiExamList } from "api/exam/moui";
import { createPaginationParams } from "utils/commonUtils";

function MouiList() {
  const navigate = useNavigate();
  const [columns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "시험명", accessor: "examNm", width: "25%" },
    { Header: "유형", accessor: "examTypeNm", width: "10%" },
    { Header: "연도", accessor: "examYear", width: "10%" },
    { Header: "회차", accessor: "examRound", width: "10%" },
    { Header: "상태", accessor: "statusNm", width: "10%" },
    { Header: "시험일", accessor: "examDate", width: "15%" },
  ]);
  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const params = createPaginationParams({ pageIndex: 1 });
      const response = await getMouiExamList(params);
      if (response && response.data) {
        setRows(
          response.data.map((item, index) => ({
            no: index + 1,
            examNm: (
              <Typography
                component={Link}
                to={`/exam/moui/detail?examSeq=${item.examSeq}`}
                variant="caption"
                color="text"
                fontWeight="medium"
              >
                {item.examNm}
              </Typography>
            ),
            examTypeNm: item.examTypeNm || item.examType,
            examYear: item.examYear,
            examRound: item.examRound,
            statusNm: item.statusNm || item.status,
            examDate: item.examDate,
          }))
        );
      }
    } catch (error) {
      console.error("Failed to fetch moui list", error);
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
                bgColor="success"
                borderRadius="lg"
                coloredShadow="success"
              >
                <Typography variant="h6" color="white">
                  모의고사 목록 (Moui)
                </Typography>
                <Button
                  variant="contained"
                  color="white"
                  onClick={() => navigate("/exam/moui/write")}
                  style={{ float: "right", marginTop: "-30px", color: "black" }}
                >
                  Create
                </Button>
              </Box>
              <Box pt={3}>
                <DataTable
                  table={{ columns, rows }}
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

export default MouiList;
