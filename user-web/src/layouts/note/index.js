import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import { useNavigate } from "react-router-dom";

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

// API
import { getNoteList } from "api/note";

function NoteList() {
  const navigate = useNavigate();
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    searchNoteContent: "",
    pageIndex: 1,
    pageUnit: 10,
  });
  const [loading, setLoading] = useState(false);

  const formatTableData = (data) => {
    return {
      columns: [
        { Header: "No", accessor: "noteNo", align: "center" },
        { Header: "내용", accessor: "noteContent", align: "left" },
        { Header: "받는사람", accessor: "recvId", align: "center" },
        { Header: "읽음확인", accessor: "readYn", align: "center" },
        { Header: "보낸날짜", accessor: "sendDt", align: "center" },
      ],
      rows:
        data?.map((item) => ({
          noteNo: item.NOTE_NO,
          noteContent: (
            <MDTypography
              component="a"
              href="#"
              variant="caption"
              color="text"
              fontWeight="medium"
              onClick={(e) => {
                e.preventDefault();
                navigate(`/note/detail?noteNo=${item.NOTE_NO}`);
              }}
              sx={{ cursor: "pointer" }}
            >
              {item.NOTE_CONTENT?.length > 20
                ? item.NOTE_CONTENT.substring(0, 20) + "..."
                : item.NOTE_CONTENT}
            </MDTypography>
          ),
          recvId: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.RECV_ID}
            </MDTypography>
          ),
          readYn: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.READ_YN === "Y" ? "읽음" : "안읽음"}
            </MDTypography>
          ),
          sendDt: (
            <MDTypography variant="caption" color="text" fontWeight="medium">
              {item.SEND_DT}
            </MDTypography>
          ),
        })) || [],
    };
  };

  const loadData = async () => {
    setLoading(true);
    try {
      const result = await getNoteList(searchParams);
      if (result && result.data) {
        setTableData(formatTableData(result.data));
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
                  쪽지 관리
                </MDTypography>
                <MDButton variant="gradient" color="dark" onClick={() => navigate("/note/detail")}>
                  <Icon sx={{ fontWeight: "bold" }}>add</Icon>
                  &nbsp;보내기
                </MDButton>
              </MDBox>

              <MDBox p={3}>
                <Grid container spacing={2} mb={2} alignItems="center">
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="내용 검색"
                      value={searchParams.searchNoteContent}
                      onChange={(e) =>
                        setSearchParams((prev) => ({
                          ...prev,
                          searchNoteContent: e.target.value,
                        }))
                      }
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={2}>
                    <MDButton variant="gradient" color="info" onClick={handleSearch} fullWidth>
                      검색
                    </MDButton>
                  </Grid>
                </Grid>

                <DataTable
                  table={tableData}
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

export default NoteList;
