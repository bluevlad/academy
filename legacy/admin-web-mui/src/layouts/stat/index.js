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
import { getTeacherList, getSearchKeywordList, getUserBuyStat } from "api/stat";

function StatList() {
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
        // Teacher List
        const result = await getTeacherList({});
        if (result && result.data) {
          setTableData({
            columns: [
              { Header: "ID", accessor: "USER_ID", align: "left" },
              { Header: "이름", accessor: "USER_NM", align: "center" },
              { Header: "과목수", accessor: "SUBJECT_CNT", align: "center" },
            ],
            rows: result.data || [],
          });
        }
      } else if (tabValue === 1) {
        // User Buy Stat (Placeholder columns based on guess)
        const result = await getUserBuyStat({});
        if (result && result.data) {
          // NOTE: Ensure column names match API response
          setTableData({
            columns: [
              { Header: "상품명", accessor: "PRODUCT_NM", align: "left" },
              { Header: "판매수", accessor: "SALE_CNT", align: "center" },
            ],
            rows: result.data || [],
          });
        }
      } else if (tabValue === 2) {
        // Search Keyword
        const result = await getSearchKeywordList({});
        if (result && result.data) {
          setTableData({
            columns: [
              { Header: "검색어", accessor: "KEYWORD", align: "left" },
              { Header: "검색횟수", accessor: "HIT_CNT", align: "center" },
              { Header: "날짜", accessor: "REG_DT", align: "center" },
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
                  통계 관리
                </Typography>
              </Box>

              <Box p={2}>
                <Tabs value={tabValue} onChange={handleTabChange} centered>
                  <Tab label="강사 통계" />
                  <Tab label="회원 구매 통계" />
                  <Tab label="검색어 통계" />
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

export default StatList;
