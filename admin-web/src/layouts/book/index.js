import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// Data
import bookListData from "layouts/book/data/bookListData";

// API
import { fetchBookList } from "api/book";
import { useNavigate } from "react-router-dom";
import { createPaginationParams } from "utils/commonUtils";

function BookList() {
  const [tableData, setTableData] = useState(bookListData([]));
  const navigate = useNavigate();

  useEffect(() => {
    const loadData = async () => {
      // Fetch data
      // Using pageRow 100 to show more data for now
      const params = createPaginationParams({ pageIndex: 1, pageRow: 100 });
      const result = await fetchBookList(params);

      if (result && result.list) {
        setTableData(bookListData(result.list));
      }
    };
    loadData();
  }, []);

  const handleCreate = () => {
    navigate("/book/write");
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
                  교재 관리 List
                </Typography>
                <Button variant="contained" color="dark" onClick={handleCreate}>
                  교재 등록
                </Button>
              </Box>
              <Box pt={3}>
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

export default BookList;
