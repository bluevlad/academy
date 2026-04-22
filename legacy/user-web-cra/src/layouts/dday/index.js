import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import { useNavigate } from "react-router-dom";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";

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
      <MDBox pt={6} pb={3}>
        <Grid container spacing={6}>
          <Grid item xs={12}>
            <Card>
              <MDBox
                mx={2}
                mt={-3}
                py={3}
                px={2}
                variant="gradient"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
                display="flex"
                justifyContent="space-between"
                alignItems="center"
              >
                <MDTypography variant="h6" color="white">
                  D-Day 관리
                </MDTypography>
                <MDButton variant="gradient" color="dark" onClick={() => navigate("/dday/detail")}>
                  등록
                </MDButton>
              </MDBox>

              <MDBox p={3}>
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
                    <MDInput
                      label="D-day 설명"
                      value={searchParams.searchDdayName}
                      onChange={(e) =>
                        setSearchParams((prev) => ({ ...prev, searchDdayName: e.target.value }))
                      }
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={2}>
                    <MDButton variant="gradient" color="info" onClick={handleSearch} fullWidth>
                      검색
                    </MDButton>
                  </Grid>
                </Grid>

                <DataTable
                  table={tableData}
                  isSorted={false}
                  entriesPerPage={false}
                  showTotalEntries={false}
                  noEndBorder
                />
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default DdayList;
