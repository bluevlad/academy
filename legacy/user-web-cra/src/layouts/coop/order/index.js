/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";

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
import coopOrderData from "layouts/coop/order/data/coopOrderData";

// API
import { fetchCoopOrderList, fetchCoopPayDetailList } from "api/coop";

function CoopOrderList() {
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    SEARCHTEXT: "",
    pageIndex: 1,
    pageUnit: 20
  });
  const [loading, setLoading] = useState(false);

  // Payment Detail Modal
  const [openDetail, setOpenDetail] = useState(false);
  const [currentOrder, setCurrentOrder] = useState(null);
  const [payDetails, setPayDetails] = useState([]);

  const loadData = async () => {
    setLoading(true);
    const result = await fetchCoopOrderList(searchParams);
    if (result && result.coopOrderList) {
      setTableData(coopOrderData(result.coopOrderList, handleOpenDetail));
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

  const handleOpenDetail = async (item) => {
    setCurrentOrder(item);
    const result = await fetchCoopPayDetailList({ ORDERNO: item.ORDERNO });
    if (result && result.coopPayDetailList) {
      setPayDetails(result.coopPayDetailList);
    } else {
      setPayDetails([]);
    }
    setOpenDetail(true);
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
              >
                <MDTypography variant="h6" color="white">
                  제휴사 주문 관리
                </MDTypography>
              </MDBox>

              <MDBox p={3}>
                <MDBox display="flex" mb={2}>
                  <MDInput
                    label="검색어 (주문자, 주문번호)"
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

      {/* Payment Detail Dialog */}
      <Dialog open={openDetail} onClose={() => setOpenDetail(false)} maxWidth="sm" fullWidth>
        <DialogTitle>결제 상세 - {currentOrder?.ORDERNO}</DialogTitle>
        <DialogContent>
          <List>
            {payDetails.map((detail, index) => (
              <ListItem key={index} divider>
                <ListItemText
                  primary={detail.PRODUCT_NAME || "상품명 없음"}
                  secondary={`금액: ${Number(detail.PRICE).toLocaleString()}원 | 상태: ${detail.STATUS_NM}`}
                />
              </ListItem>
            ))}
            {payDetails.length === 0 && <MDTypography variant="button">상세 내역이 없습니다.</MDTypography>}
          </List>
        </DialogContent>
        <DialogActions>
          <MDButton color="secondary" onClick={() => setOpenDetail(false)}>닫기</MDButton>
        </DialogActions>
      </Dialog>

      <Footer />
    </DashboardLayout>
  );
}

export default CoopOrderList;
