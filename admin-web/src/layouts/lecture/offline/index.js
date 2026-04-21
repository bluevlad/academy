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
import { getLectureOffList } from "api/lecture/lectureOff";

function OfflineLectureList() {
  const navigate = useNavigate();
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "강의코드", accessor: "leccode", width: "10%" },
    { Header: "직종", accessor: "categoryNm", width: "10%" },
    { Header: "학습형태", accessor: "learningNm", width: "10%" },
    { Header: "강사", accessor: "subjectTeacherNm", width: "10%" },
    { Header: "강좌명", accessor: "subjectTitle", width: "35%" },
    { Header: "사용여부", accessor: "subjectIsuse", width: "5%" },
    { Header: "등록일", accessor: "regDt", width: "10%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      // API expects pageIndex, pageUnit, pageSize
      const response = await getLectureOffList({ pageIndex: 1, pageUnit: 100, pageSize: 10 });
      if (response && response.data) {
        const formattedRows = response.data.map((item, index) => ({
          no: index + 1,
          leccode: item.leccode,
          categoryNm: item.categoryNm,
          learningNm: item.learningNm,
          subjectTeacherNm: item.subjectTeacherNm,
          subjectTitle: (
            <Typography
              component={Link}
              to={`/lecture/offline/detail?bridgeLeccode=${item.bridgeLeccode}&leccode=${item.leccode}`} // Offline lectures might use bridgeLeccode or leccode for ID
              variant="caption"
              color="text"
              fontWeight="medium"
            >
              {item.subjectTitle}
            </Typography>
          ),
          subjectIsuse: item.subjectIsuse === 'Y' ? '사용' : '미사용',
          regDt: item.regDt,
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch offline lecture list", error);
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
                  Offline Lecture List
                </Typography>
                <Button
                  variant="contained"
                  color="white"
                  onClick={() => navigate("/lecture/offline/write")}
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
    </DashboardLayout >
  );
}

export default OfflineLectureList;
