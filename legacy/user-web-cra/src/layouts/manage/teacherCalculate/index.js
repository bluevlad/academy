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
import { getTeacherCalculateList } from "api/manage/teacherCalculate";

function TeacherCalculateList() {
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "정산월", accessor: "calcMonth", width: "10%" },
    { Header: "강사명", accessor: "teacherNm", width: "10%" },
    { Header: "강의명", accessor: "lectureNm", width: "30%" },
    { Header: "매출금액", accessor: "saleAmt", align: "right" },
    { Header: "정산금액", accessor: "calcAmt", align: "right" },
    { Header: "세금", accessor: "taxAmt", align: "right" },
    { Header: "지급금액", accessor: "paidAmt", align: "right" },
    { Header: "상태", accessor: "calcStatusNm", width: "10%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getTeacherCalculateList({ pageIndex: 1, pageSize: 100 });
      if (response && response.data) {
        const formattedRows = response.data.map((item, index) => ({
          no: index + 1,
          calcMonth: `${item.calcYear}-${item.calcMonth}`,
          teacherNm: item.teacherNm,
          lectureNm: item.lectureNm,
          saleAmt: item.saleAmt ? item.saleAmt.toLocaleString() : "0",
          calcAmt: item.calcAmt ? item.calcAmt.toLocaleString() : "0",
          taxAmt: item.taxAmt ? item.taxAmt.toLocaleString() : "0",
          paidAmt: item.paidAmt ? item.paidAmt.toLocaleString() : "0",
          calcStatusNm: item.calcStatusNm,
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch teacher calculate list", error);
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
                  Teacher Calculate List
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

export default TeacherCalculateList;
