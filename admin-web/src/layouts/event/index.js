import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
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
            <Typography
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
            </Typography>
          ),
          period: (
            <Typography variant="caption" color="text" fontWeight="medium">
              {item.START_DT && item.END_DT ? `${item.START_DT} ~ ${item.END_DT}` : "기간 미설정"}
            </Typography>
          ),
          useYn: (
            <Typography variant="caption" color="text" fontWeight="medium">
              {item.USE_YN === "Y" ? "사용" : "미사용"}
            </Typography>
          ),
          regDt: (
            <Typography variant="caption" color="text" fontWeight="medium">
              {item.REG_DT}
            </Typography>
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
                  이벤트 관리
                </Typography>
                <Button variant="contained" color="dark" onClick={() => navigate("/event/detail")}>
                  <Icon sx={{ fontWeight: "bold" }}>add</Icon>
                  &nbsp;등록
                </Button>
              </Box>

              <Box p={3}>
                <Grid container spacing={2} mb={2} alignItems="center">
                  <Grid item xs={12} md={6}>
                    <TextField
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

export default EventList;
