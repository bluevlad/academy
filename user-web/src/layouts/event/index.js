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
import { getEventList } from "api/event";
import { createPaginationParams } from "utils/commonUtils";

function EventList() {
  const navigate = useNavigate();
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    searchEventTitle: "",
    pageIndex: 1,
    pageUnit: 10,
  });
  const [loading, setLoading] = useState(false);

  const formatTableData = (data) => {
    return {
      columns: [
        { Header: "No", accessor: "eventNo", align: "center" },
        { Header: "제목", accessor: "eventTitle", align: "left" },
        { Header: "기간", accessor: "period", align: "center" },
        { Header: "상태", accessor: "useYn", align: "center" },
        { Header: "등록일", accessor: "regDt", align: "center" },
      ],
      rows:
        data?.map((item) => ({
          eventNo: item.EVENT_NO,
          eventTitle: (
            <MDTypography
              component="a"
              href="#"
              variant="caption"
              color="text"
              fontWeight="medium"
              onClick={(e) => {
                e.preventDefault();
                navigate(`/event/detail?eventNo=${item.EVENT_NO}`);
              }}
              sx={{ cursor: "pointer" }}
            >
              {item.EVENT_TITLE}
            </MDTypography>
          ),
          period: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.START_DT && item.END_DT ? `${item.START_DT} ~ ${item.END_DT}` : "기간 미설정"}
            </MDTypography>
          ),
          useYn: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.USE_YN === "Y" ? "사용" : "미사용"}
            </MDTypography>
          ),
          regDt: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.REG_DT}
            </MDTypography>
          ),
        })) || [],
    };
  };

  const loadData = async () => {
    setLoading(true);
    try {
      const params = createPaginationParams(searchParams);
      const result = await getEventList(params);
      if (result && result.list) {
        setTableData(formatTableData(result.list));
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
                  이벤트 관리
                </MDTypography>
                <MDButton variant="gradient" color="dark" onClick={() => navigate("/event/detail")}>
                  <Icon sx={{ fontWeight: "bold" }}>add</Icon>
                  &nbsp;등록
                </MDButton>
              </MDBox>

              <MDBox p={3}>
                <Grid container spacing={2} mb={2} alignItems="center">
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="이벤트 제목"
                      value={searchParams.searchEventTitle}
                      onChange={(e) =>
                        setSearchParams((prev) => ({
                          ...prev,
                          searchEventTitle: e.target.value,
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

export default EventList;
