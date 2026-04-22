import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getCategorySaleList } from "api/manage/categorySale";

function CategorySaleList() {
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "카테고리명", accessor: "categoryNm", width: "20%" },
    { Header: "매출건수", accessor: "saleCnt", align: "right" },
    { Header: "매출금액", accessor: "saleAmt", align: "right" },
    { Header: "환불건수", accessor: "refundCnt", align: "right" },
    { Header: "환불금액", accessor: "refundAmt", align: "right" },
    { Header: "순매출건수", accessor: "netSaleCnt", align: "right" },
    { Header: "순매출금액", accessor: "netSaleAmt", align: "right" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getCategorySaleList({ pageIndex: 1, pageSize: 100 });
      if (response && response.data) {
        const formattedRows = response.data.map((item, index) => ({
          no: index + 1,
          categoryNm: item.categoryNm,
          saleCnt: item.saleCnt ? item.saleCnt.toLocaleString() : "0",
          saleAmt: item.saleAmt ? item.saleAmt.toLocaleString() : "0",
          refundCnt: item.refundCnt ? item.refundCnt.toLocaleString() : "0",
          refundAmt: item.refundAmt ? item.refundAmt.toLocaleString() : "0",
          netSaleCnt: item.netSaleCnt ? item.netSaleCnt.toLocaleString() : "0",
          netSaleAmt: item.netSaleAmt ? item.netSaleAmt.toLocaleString() : "0",
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch category sale list", error);
    }
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
                  Category Sale List
                </MDTypography>
              </MDBox>
              <MDBox pt={3}>
                <DataTable
                  table={{ columns, rows }}
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

export default CategorySaleList;
