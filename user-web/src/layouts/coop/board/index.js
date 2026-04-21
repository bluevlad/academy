/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";

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
                  제휴사 게시판
                </MDTypography>
                <MDButton variant="gradient" color="dark" onClick={handleCreate}>
                  글쓰기
                </MDButton>
              </MDBox>

              <MDBox p={3}>
                <MDBox display="flex" mb={2}>
                  <MDInput
                    label="검색어"
                    value={searchParams.SEARCHTEXT}
                    onChange={(e) => setSearchParams(prev => ({ ...prev, SEARCHTEXT: e.target.value }))}
                    sx={{ marginRight: 2 }}
                  />
                  <MDButton variant="gradient" color="info" onClick={handleSearch}>
                    검색
                  </MDButton>
                </MDBox>

                {loading ? (
                  <MDTypography>Loading...</MDTypography>
                ) : (
                  <DataTable
                    table={tableData}
                    isSorted={false}
                    entriesPerPage={false}
                    showTotalEntries={false}
                    noEndBorder
                  />
                )}
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default CoopBoardList;
