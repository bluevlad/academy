import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
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

// Data
import ddayListData from "layouts/dday/data/ddayListData";

// API
import { fetchDdayList, fetchDdayCategoryList } from "api/dday";
import { createPaginationParams } from "utils/commonUtils";

function DdayList() {
  const navigate = useNavigate();
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    searchDdayName: "",
    searchCategory: "",
    pageIndex: 1,
    pageUnit: 10,
  });
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    // Load categories on mount
    const loadCategories = async () => {
      const result = await fetchDdayCategoryList({});
      if (result && result.categoryList) {
        setCategories(result.categoryList);
      }
    };
    loadCategories();
  }, []);

  const loadData = async () => {
    setLoading(true);
    const params = createPaginationParams(searchParams);
    const result = await fetchDdayList(params);
    if (result && result.list) {
      setTableData(ddayListData(result.list));
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
                  D-Day 관리
                </Typography>
                <Button variant="contained" color="dark" onClick={() => navigate("/dday/detail")}>
                  등록
                </Button>
              </Box>

              <Box p={3}>
                <Grid container spacing={2} mb={2} alignItems="center">
                  <Grid item xs={12} md={3}>
                    <FormControl fullWidth>
                      <InputLabel>직종</InputLabel>
                      <Select
                        value={searchParams.searchCategory}
                        label="직종"
                        onChange={(e) =>
                          setSearchParams((prev) => ({ ...prev, searchCategory: e.target.value }))
                        }
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="">전체</MenuItem>
                        {categories.map((cat) => (
                          <MenuItem key={cat.CODE} value={cat.CODE}>
                            {cat.NAME}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12} md={5}>
                    <TextField
                      label="D-day 설명"
                      value={searchParams.searchDdayName}
                      onChange={(e) =>
                        setSearchParams((prev) => ({ ...prev, searchDdayName: e.target.value }))
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

export default DdayList;
