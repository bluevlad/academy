import { useState, useEffect } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
import { getMemberFreeOrderList } from "api/order/freeOrder";

function FreeOrderList() {
  const [columns, setColumns] = useState([
    { Header: "No", accessor: "no", width: "5%" },
    { Header: "이름", accessor: "userNm", width: "10%" },
    { Header: "아이디", accessor: "userId", width: "10%" },
    { Header: "생년월일", accessor: "birthDay", width: "10%" },
    { Header: "전화번호", accessor: "telNo", width: "15%" },
    { Header: "휴대폰", accessor: "phoneNo", width: "15%" },
    { Header: "이메일", accessor: "email", width: "20%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getMemberFreeOrderList({ pageIndex: 1, pageSize: 100 });
      if (response && response.list) {
        const formattedRows = response.list.map((item, index) => ({
          no: index + 1,
          userNm: item.USER_NM,
          userId: item.USER_ID,
          birthDay: item.BIRTH_DAY,
          telNo: item.TEL_NO,
          phoneNo: item.PHONE_NO,
          email: item.EMAIL,
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch free order list", error);
    }
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
              >
                <Typography variant="h6" color="white">
                  Free Order Member List
                </Typography>
              </Box>
              <Box pt={3}>
                <DataTable
                  table={{ columns, rows }}
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

export default FreeOrderList;
