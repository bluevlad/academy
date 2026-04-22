import { useState, useEffect } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
import { getProductOrderList } from "api/order/productOrder";

function ProductOrderList() {
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "주문번호", accessor: "orderno", width: "10%" },
    { Header: "이름", accessor: "userNm", width: "10%" },
    { Header: "상품명", accessor: "subjectTitle", width: "30%" },
    { Header: "금액", accessor: "price", align: "right" },
    { Header: "결제수단", accessor: "payCodeName", width: "10%" },
    { Header: "상태", accessor: "statusCodeNm", width: "10%" },
    { Header: "주문일", accessor: "regDt", width: "10%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getProductOrderList({ currentPage: 1, pageRow: 100 });
      if (response && response.list) {
        const formattedRows = response.list.map((item, index) => ({
          no: index + 1,
          orderno: item.ORDERNO,
          userNm: `${item.USER_NM}(${item.USER_ID})`,
          subjectTitle: item.SUBJECT_TITLE,
          price: item.PRICE ? item.PRICE.toLocaleString() : "0",
          payCodeName: item.PAYCODE_NAME,
          statusCodeNm: item.STATUSCODE_NM,
          regDt: item.REG_DT,
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch product order list", error);
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
                  Product Order List
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

export default ProductOrderList;
