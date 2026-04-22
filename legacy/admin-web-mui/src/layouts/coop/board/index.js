/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";

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

// Data
import coopBoardData from "layouts/coop/board/data/coopBoardData";

// API
import { fetchCoopBoardList } from "api/coop";
import { useNavigate } from "react-router-dom";

function CoopBoardList() {
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    SEARCHTEXT: "",
    pageIndex: 1,
    pageUnit: 10
  });
  const [loading, setLoading] = useState(false);

  // Filters logic if API returns code lists here, or we can hardcode if static. 
  // API returns codeAreaList, codeHsptList in getCoopBoardList result.
  // For simplicity, I'll stick to text search for now, or use the codes if I extract them.

  const navigate = useNavigate();

  const loadData = async () => {
    setLoading(true);
    const result = await fetchCoopBoardList(searchParams);
    if (result && result.coopBoardList) {
      setTableData(coopBoardData(result.coopBoardList));
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, [searchParams.pageIndex]);

  const handleSearch = () => {
    setSearchParams(prev => ({ ...prev, pageIndex: 1 }));
    loadData();
  };

  const handleCreate = () => {
    navigate("/coop/board/write");
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
                  제휴사 게시판
                </Typography>
                <Button variant="contained" color="dark" onClick={handleCreate}>
                  글쓰기
                </Button>
              </Box>

              <Box p={3}>
                <Box display="flex" mb={2}>
                  <TextField
                    label="검색어"
                    value={searchParams.SEARCHTEXT}
                    onChange={(e) => setSearchParams(prev => ({ ...prev, SEARCHTEXT: e.target.value }))}
                    sx={{ marginRight: 2 }}
                  />
                  <Button variant="contained" color="info" onClick={handleSearch}>
                    검색
                  </Button>
                </Box>

                {loading ? (
                  <Typography>Loading...</Typography>
                ) : (
                  <DataTable
                    table={tableData}
                    isSorted={false}
                    entriesPerPage={false}
                    showTotalEntries={false}
                    noEndBorder
                  />
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

export default CoopBoardList;
