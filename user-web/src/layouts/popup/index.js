import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
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

// API
import { getPopupList } from "api/popup";

function PopupList() {
  const navigate = useNavigate();
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    searchPopupTitle: "",
    pageIndex: 1,
    pageUnit: 10,
  });
  const [loading, setLoading] = useState(false);

  const formatTableData = (data) => {
    return {
      columns: [
        { Header: "No", accessor: "popNo", align: "center" },
        { Header: "제목", accessor: "popTitle", align: "left" },
        { Header: "기간", accessor: "period", align: "center" },
        { Header: "상태", accessor: "useYn", align: "center" },
        { Header: "조회수", accessor: "hit", align: "center" },
      ],
      rows:
        data?.map((item) => ({
          popNo: item.POP_NO,
          popTitle: (
            <MDTypography
              component="a"
              href="#"
              variant="caption"
              color="text"
              fontWeight="medium"
              onClick={(e) => {
                e.preventDefault();
                navigate(`/popup/detail?popNo=${item.POP_NO}`);
              }}
              sx={{ cursor: "pointer" }}
            >
              {item.POP_TITLE}
            </MDTypography>
          ),
          period: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.START_DATE} ~ {item.END_DATE}
            </MDTypography>
          ),
          useYn: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.USE_YN === "Y" ? "사용" : "미사용"}
            </MDTypography>
          ),
          hit: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.HIT}
            </MDTypography>
          ),
        })) || [],
    };
  };

  const loadData = async () => {
    setLoading(true);
    try {
      const result = await getPopupList(searchParams);
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
                  팝업 관리
                </MDTypography>
                <MDButton variant="gradient" color="dark" onClick={() => navigate("/popup/detail")}>
                  <Icon sx={{ fontWeight: "bold" }}>add</Icon>
                  &nbsp;등록
                </MDButton>
              </MDBox>

              <MDBox p={3}>
                <Grid container spacing={2} mb={2} alignItems="center">
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="팝업 제목"
                      value={searchParams.searchPopupTitle}
                      onChange={(e) =>
                        setSearchParams((prev) => ({
                          ...prev,
                          searchPopupTitle: e.target.value,
                        }))
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

export default PopupList;
