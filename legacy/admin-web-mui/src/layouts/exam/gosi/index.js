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
import { getSampleUserList } from "api/exam/gosi";
import { createPaginationParams } from "utils/commonUtils";

function GosiList() {
  const navigate = useNavigate();
  const [columns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "이름", accessor: "userNm", width: "15%" },
    { Header: "나이", accessor: "userAge", width: "10%" },
    { Header: "학습기간", accessor: "studyWait", width: "15%" },
    { Header: "유형", accessor: "studyType", width: "15%" },
    { Header: "가산점", accessor: "addPoint", width: "10%" },
    { Header: "등록일", accessor: "regDt", width: "20%" },
  ]);
  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const params = createPaginationParams({ pageIndex: 1 });
      const response = await getSampleUserList(params);
      if (response && response.data) {
        setRows(
          response.data.map((item, index) => ({
            no: index + 1,
            userNm: (
              <Typography
                component={Link}
                to={`/exam/gosi/detail?rstNo=${item.rstNo}`}
                variant="caption"
                color="text"
                fontWeight="medium"
              >
                {item.userNm}
              </Typography>
            ),
            userAge: item.userAge,
            studyWait: item.studyWait,
            studyType: item.studyType,
            addPoint: item.addPoint,
            regDt: item.inputDate || item.regDt || "-", // VO says inputDate
          }))
        );
      }
    } catch (error) {
      console.error("Failed to fetch gosi list", error);
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
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <Typography variant="h6" color="white">
                  샘플 사용자 목록 (Gosi)
                </Typography>
                <Button
                  variant="contained"
                  color="white"
                  onClick={() => navigate("/exam/gosi/write")}
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

export default GosiList;
