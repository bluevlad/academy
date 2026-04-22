import { useState, useEffect } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";
import { getCouponList } from "api/order/coupon";

function CouponList() {
  const [columns, setColumns] = useState([
    { Header: "쿠폰코드", accessor: "ccode", width: "10%" },
    { Header: "쿠폰명", accessor: "cname", width: "20%" },
    { Header: "발급인원", accessor: "useCnt", align: "center", width: "10%" },
    { Header: "쿠폰타입", accessor: "regtypeNm", width: "10%" },
    { Header: "금액/할인율", accessor: "regpriceNm", width: "10%" },
    { Header: "사용구분", accessor: "addFlag", width: "10%" },
    { Header: "사용기간", accessor: "expday", width: "10%" },
    { Header: "사용만료일", accessor: "expdatee", width: "10%" },
    { Header: "등록일", accessor: "regdate", width: "10%" },
    { Header: "상태", accessor: "isUse", width: "5%" },
  ]);

  const [rows, setRows] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await getCouponList({ currentPage: 1, pageRow: 100 });
      if (response && response.list) {
        const formattedRows = response.list.map((item) => ({
          ccode: item.CCODE,
          cname: item.CNAME,
          useCnt: `${item.USE_CNT} / ${item.CNT}`,
          regtypeNm: item.REGTYPE_NM,
          regpriceNm: item.REGPRICE_NM,
          addFlag: getAddFlagName(item.ADD_FLAG),
          expday: `${item.EXPDAY}일`,
          expdatee: item.EXPDATEE,
          regdate: item.REGDATE,
          isUse: item.IS_USE === 'Y' ? "활성" : "비활성",
        }));
        setRows(formattedRows);
      }
    } catch (error) {
      console.error("Failed to fetch coupon list", error);
    }
  };

  const getAddFlagName = (flag) => {
    switch (flag) {
      case 'O': return '동영상';
      case 'M': return '모의고사';
      case 'L': return '교재';
      case 'F': return '학원';
      default: return '';
    }
  }

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
                  Coupon List
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

export default CouponList;
