/* eslint-disable prettier/prettier */
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

// Data
import counselListData from "layouts/counsel/data/counselListData";

// API
import { fetchScheduleDayList, deleteSchedule } from "api/counsel";

function CounselList() {
  const navigate = useNavigate();
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [searchParams, setSearchParams] = useState({
    SDate: "",
    EDate: "",
    searchCategory: "", // Comma separated codes
    pageRow: 10,
    pageIndex: 1
  });
  const [selectedItems, setSelectedItems] = useState([]);

  // In a real app we'd fetch categories. Hardcoding or leaving empty for now.
  // const [categories, setCategories] = useState([]);

  const loadData = async () => {
    const result = await fetchScheduleDayList(searchParams);
    if (result && result.scheduleDayList) {
      setTableData(counselListData(result.scheduleDayList, handleView, handleEdit, handleSelect));
    }
  };

  useEffect(() => {
    loadData();
  }, [searchParams.pageIndex]);

  const handleSearch = () => {
    setSearchParams(prev => ({ ...prev, pageIndex: 1 }));
    loadData();
  };

  const handleView = (item) => {
    // Navigate to view page
    navigate(`/counsel/view?date=${item.SCH_DAY}&catCd=${item.CAT_CD}`);
  };

  const handleEdit = (item) => {
    // Navigate to write page with params
    navigate(`/counsel/write?date=${item.SCH_DAY}&catCd=${item.CAT_CD}&catNm=${item.CAT_NM}`);
  };

  const handleSelect = (e, item) => {
    const value = `${item.SCH_DAY}#${item.CAT_CD}`;
    if (e.target.checked) {
      setSelectedItems(prev => [...prev, value]);
    } else {
      setSelectedItems(prev => prev.filter(i => i !== value));
    }
  };

  const handleDelete = async () => {
    if (selectedItems.length === 0) {
      alert("삭제하실 상담일정을 선택해주세요");
      return;
    }
    if (window.confirm("선택하신 상담일정을 삭제하시겠습니까?")) {
      // Need to handle multiple deletes. API `deleteSchedule` might verify params.
      // Inspecting CounselApi.java: deleteSchedule takes CounselScheduleVO.
      // CounselService.deleteScheduleBatch(counselScheduleVO)
      // It probably expects 'DEL_ARR' array or similar. 
      // In list.jsp, name='DEL_ARR' and value='SCH_DAY#CAT_CD' is sent.
      // So we should send `DEL_ARR` as parameter.

      // Note: superagent .type('form') with array might need specific handling or repeating keys.
      // Or sending manual query string or object with array.
      // Let's try sending object { DEL_ARR: [val1, val2] }

      try {
        await deleteSchedule({ DEL_ARR: selectedItems });
        alert("상담일정이 삭제되었습니다.");
        setSelectedItems([]);
        loadData();
      } catch (e) {
        alert("상담일정 삭제 실패");
      }
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
                display="flex"
                justifyContent="space-between"
                alignItems="center"
              >
                <MDTypography variant="h6" color="white">
                  상담예약관리
                </MDTypography>
                <MDButton variant="gradient" color="dark" onClick={() => navigate("/counsel/write")}>
                  상담일정등록
                </MDButton>
              </MDBox>

              <MDBox p={3}>
                <Grid container spacing={2} alignItems="center" mb={2}>
                  <Grid item xs={12} md={3}>
                    <MDInput
                      label="시작일 (YYYYMMDD)"
                      value={searchParams.SDate}
                      onChange={(e) => setSearchParams(prev => ({ ...prev, SDate: e.target.value }))}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <MDInput
                      label="종료일 (YYYYMMDD)"
                      value={searchParams.EDate}
                      onChange={(e) => setSearchParams(prev => ({ ...prev, EDate: e.target.value }))}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={2}>
                    <MDButton variant="gradient" color="info" onClick={handleSearch} fullWidth>
                      검색
                    </MDButton>
                  </Grid>
                  <Grid item xs={12} md={2}>
                    <MDButton variant="outlined" color="error" onClick={handleDelete} fullWidth>
                      삭제
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

export default CounselList;
