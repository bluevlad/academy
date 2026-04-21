import { useNavigate } from "react-router-dom";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// Data (Mock)
const columns = [
  { Header: "강의명", accessor: "title", width: "45%", align: "left" },
  { Header: "강사", accessor: "instructor", align: "center" },
  { Header: "진도율", accessor: "progress", align: "center" },
  { Header: "학습하기", accessor: "action", align: "center" },
];

const rows = (navigate) => [
  {
    title: (
      <MDBox display="flex" alignItems="center" lineHeight={1}>
        <MDTypography display="block" variant="button" fontWeight="medium">
          리액트 기초 마스터하기
        </MDTypography>
      </MDBox>
    ),
    instructor: (
      <MDTypography component="a" href="#" variant="caption" color="text" fontWeight="medium">
        홍길동
      </MDTypography>
    ),
    progress: (
      <MDTypography component="a" href="#" variant="caption" color="text" fontWeight="medium">
        20%
      </MDTypography>
    ),
    action: (
      <MDButton
        variant="text"
        color="info"
        onClick={() => navigate("/my-classroom/watch?leccode=1")}
      >
        학습하기
      </MDButton>
    ),
  },
  {
    title: (
      <MDBox display="flex" alignItems="center" lineHeight={1}>
        <MDTypography display="block" variant="button" fontWeight="medium">
          자바 스프링 부트 실전
        </MDTypography>
      </MDBox>
    ),
    instructor: (
      <MDTypography component="a" href="#" variant="caption" color="text" fontWeight="medium">
        김철수
      </MDTypography>
    ),
    progress: (
      <MDTypography component="a" href="#" variant="caption" color="text" fontWeight="medium">
        0%
      </MDTypography>
    ),
    action: (
      <MDButton
        variant="text"
        color="info"
        onClick={() => navigate("/my-classroom/watch?leccode=2")}
      >
        학습하기
      </MDButton>
    ),
  },
];

function MyClassroom() {
  const navigate = useNavigate();

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
                  내 강의실
                </MDTypography>
              </MDBox>
              <MDBox pt={3}>
                <DataTable
                  table={{ columns, rows: rows(navigate) }}
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

export default MyClassroom;
