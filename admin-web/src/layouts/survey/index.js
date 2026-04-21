import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Tab from "@mui/material/Tab";
import Tabs from "@mui/material/Tabs";
import { useNavigate } from "react-router-dom";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getBankList, getSetList, getSurveyList } from "api/survey";

function SurveyList() {
  const [tabValue, setTabValue] = useState(0);
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadData();
  }, [tabValue]);

  const loadData = async () => {
    setLoading(true);
    try {
      if (tabValue === 0) {
        // Survey List (Actual Surveys)
        const result = await getSurveyList({});
        if (result && result.data) {
          setTableData({
            columns: [
              { Header: "No", accessor: "SURVEY_NO", align: "center" },
              { Header: "제목", accessor: "SURVEY_TITLE", align: "left" },
              { Header: "기간", accessor: "PERIOD", align: "center", Cell: ({ row }) => `${row.original.START_DT} ~ ${row.original.END_DT}` },
              { Header: "상태", accessor: "USE_YN", align: "center" },
            ],
            rows: result.data || [],
          });
        }
      } else if (tabValue === 1) {
        // Survey Set
        const result = await getSetList({});
        if (result && result.data) {
          setTableData({
            columns: [
              { Header: "No", accessor: "SET_NO", align: "center" },
              { Header: "세트명", accessor: "SET_TITLE", align: "left" },
              { Header: "등록일", accessor: "REG_DT", align: "center" },
            ],
            rows: result.data || [],
          });
        }
      } else if (tabValue === 2) {
        // Question Bank
        const result = await getBankList({});
        if (result && result.data) {
          setTableData({
            columns: [
              { Header: "No", accessor: "BANK_NO", align: "center" },
              { Header: "질문", accessor: "QUESTION", align: "left" },
              { Header: "유형", accessor: "TYPE", align: "center" },
            ],
            rows: result.data || [],
          });
        }
      }
    } catch (error) {
      console.error(error);
    }
    setLoading(false);
  };

  const handleTabChange = (event, newValue) => {
    setTabValue(newValue);
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
                  설문조사 관리
                </Typography>
              </Box>

              <Box p={2}>
                <Tabs value={tabValue} onChange={handleTabChange} centered>
                  <Tab label="설문 목록" />
                  <Tab label="설문 세트" />
                  <Tab label="문항 은행" />
                </Tabs>
              </Box>

              <Box p={3}>
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

export default SurveyList;
