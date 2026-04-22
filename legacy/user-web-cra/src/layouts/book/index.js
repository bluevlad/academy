import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import Rating from "@mui/material/Rating"; // Added Rating for books

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import { getBookList } from "api/book";
import { createPaginationParams } from "utils/commonUtils";

function BookList() {
  const [bookList, setBookList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  useEffect(() => {
    loadBookData();
  }, [currentPage]);

  const loadBookData = async () => {
    setLoading(true);
    try {
      const params = createPaginationParams({ pageIndex: currentPage });
      const data = await getBookList(params);

      setBookList(data.bookList || []);
      setPaginationInfo(data.paginationInfo || null);
      if (data.paginationInfo) {
        setTotalPages(
          Math.ceil(
            data.paginationInfo.totalRecordCount / data.paginationInfo.recordCountPerPage
          ) || 1
        );
      }
    } catch (error) {
      console.error("Failed to load book data:", error);
      setBookList([]);
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const columns = [
    { Header: "No", accessor: "bookSeq", width: "7%", align: "center" },
    { Header: "카테고리", accessor: "category", width: "10%", align: "center" },
    { Header: "도서명", accessor: "bookName", width: "30%", align: "left" },
    { Header: "저자", accessor: "author", width: "10%", align: "center" },
    { Header: "출판사", accessor: "publisher", width: "10%", align: "center" },
    { Header: "가격", accessor: "price", width: "10%", align: "right" },
    { Header: "평점", accessor: "rating", width: "10%", align: "center" },
    { Header: "등록일", accessor: "regDt", align: "center" },
    { Header: "Action", accessor: "action", align: "center" },
  ];

  const rows = bookList.map((book) => ({
    bookSeq: (
      <MDTypography variant="caption" color="text" fontWeight="medium">
        {book.bookSeq}
      </MDTypography>
    ),
    category: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {book.categoryName || "-"}
      </MDTypography>
    ),
    bookName: (
      <MDBox display="flex" flexDirection="column">
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {book.bookName}
        </MDTypography>
        {book.isbn && (
          <MDTypography variant="caption" color="secondary">
            ISBN: {book.isbn}
          </MDTypography>
        )}
      </MDBox>
    ),
    author: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {book.author}
      </MDTypography>
    ),
    publisher: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {book.publisher}
      </MDTypography>
    ),
    price: (
      <MDTypography variant="caption" color="text" fontWeight="medium">
        {book.price ? `${book.price.toLocaleString()}원` : "0원"}
      </MDTypography>
    ),
    rating: <Rating value={book.scope || 0} readOnly size="small" />,
    regDt: (
      <MDTypography variant="caption" color="text" fontWeight="regular">
        {book.regDt ? new Date(book.regDt).toLocaleDateString("ko-KR") : "-"}
      </MDTypography>
    ),
    action: (
      <MDTypography component="a" href="#" color="text">
        <Icon>more_vert</Icon>
      </MDTypography>
    ),
  }));

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
                  도서 관리
                </MDTypography>
                <MDButton variant="outlined" color="white" size="small">
                  도서 등록
                </MDButton>
              </MDBox>
              <MDBox pt={3}>
                {loading ? (
                  <MDBox p={3} textAlign="center">
                    <MDTypography variant="caption">로딩 중...</MDTypography>
                  </MDBox>
                ) : (
                  <>
                    <DataTable
                      table={{ columns, rows }}
                      isSorted={false}
                      entriesPerPage={false}
                      showTotalEntries={false}
                      noEndBorder
                    />
                    {paginationInfo && (
                      <MDBox
                        p={3}
                        display="flex"
                        justifyContent="space-between"
                        alignItems="center"
                      >
                        <MDBox>
                          <MDTypography variant="caption" color="text">
                            전체 {paginationInfo.totalRecordCount}건 중{" "}
                            {paginationInfo.firstRecordIndex + 1} -{" "}
                            {Math.min(
                              paginationInfo.lastRecordIndex + 1,
                              paginationInfo.totalRecordCount
                            )}
                            건 표시{" "}
                          </MDTypography>
                        </MDBox>
                        <Stack spacing={2}>
                          <Pagination
                            count={totalPages}
                            page={currentPage}
                            onChange={handlePageChange}
                            color="primary"
                            shape="rounded"
                            showFirstButton
                            showLastButton
                          />
                        </Stack>
                      </MDBox>
                    )}
                  </>
                )}
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default BookList;
