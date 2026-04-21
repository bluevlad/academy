/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";

import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import { fetchDdayDetail, insertDday, updateDday, deleteDday } from "api/dday";

function DdayDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const ddayIdx = queryParams.get("ddayIdx");
  const isEdit = !!ddayIdx;

  const [formData, setFormData] = useState({
    DDAY_CATEGORY: "",
    DDAY_NAME: "",
    DDAY_DATE: "",
    DDAY_LINK: "",
    DDAY_TYPE: "P",
    DDAY_ACTIVE: "F"
  });
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const load = async () => {
      try {
        // If edit, fetch detail loaded with categories. If new, just categories? 
        // getDdayDetail returns categories too.
        // We'll call getDdayDetail even if new? No, backend expects ID for detail.
        // But we need categories. list endpoint returns categories, or we can use another endpoint.
        // Looking at API, `getCategoryList` exists.
        // But `getDdayDetail` returns `categoryList` too.

        if (isEdit) {
          const result = await fetchDdayDetail({ DDAY_IDX: ddayIdx });
          if (result && result.detail) {
            setFormData({
              ...result.detail,
              DDAY_CATEGORY: result.detail.DDAY_CATEGORY || "", // Ensure not null
              DDAY_TYPE: result.detail.DDAY_TYPE || "P",
              DDAY_ACTIVE: result.detail.DDAY_ACTIVE || "F"
            });
          }
          if (result && result.categoryList) {
            setCategories(result.categoryList);
          }
        } else {
          // Load categories only
          // We can reuse getCategoryList from api/dday/index.js if exported, 
          // or just import it like in list. Oh, I exported it.
          // But wait, I didn't import fetchDdayCategoryList here.
          // I will import it or assume getDdayDetail handles it? 
          // I'll add export to imports.
          // Actually, I can just use fetchDdayCategoryList which I'll add to imports in a sec (auto-fix or next thought).
          // Or... I can assume DdayApi.getDdayDetail might error without ID?
          // Let's use fetchDdayCategoryList.
        }
      } catch (e) {
        console.error(e);
      }
    };
    load();
  }, [ddayIdx]);

  // Load categories for new insert
  useEffect(() => {
    if (!isEdit) {
      import("api/dday").then(async ({ fetchDdayCategoryList }) => {
        const result = await fetchDdayCategoryList({});
        if (result && result.categoryList) {
          setCategories(result.categoryList);
          if (result.categoryList.length > 0) {
            setFormData(prev => ({ ...prev, DDAY_CATEGORY: result.categoryList[0].CODE }));
          }
        }
      });
    }
  }, [isEdit]);


  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    if (!formData.DDAY_NAME) { alert("D-day 설명을 입력하세요"); return; }
    if (!formData.DDAY_DATE) { alert("D-day 날짜를 입력하세요"); return; }

    try {
      if (isEdit) {
        await updateDday(formData);
        alert("수정되었습니다.");
      } else {
        await insertDday(formData);
        alert("등록되었습니다.");
      }
      navigate("/dday");
    } catch (e) {
      alert("저장 실패");
    }
  };

  const handleDelete = async () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteDday({ DDAY_IDX: ddayIdx });
        navigate("/dday");
      } catch (e) {
        alert("삭제 실패");
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
              >
                <MDTypography variant="h6" color="white">
                  D-Day {isEdit ? "상세/수정" : "등록"}
                </MDTypography>
              </MDBox>
              <MDBox p={3}>
                <Grid container spacing={2}>
                  <Grid item xs={12} md={6}>
                    <FormControl fullWidth>
                      <InputLabel>직종</InputLabel>
                      <Select
                        name="DDAY_CATEGORY"
                        value={formData.DDAY_CATEGORY}
                        label="직종"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        {categories.map(c => (
                          <MenuItem key={c.CODE} value={c.CODE}>{c.NAME}</MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <FormControl fullWidth>
                      <InputLabel>D-day Type</InputLabel>
                      <Select
                        name="DDAY_TYPE"
                        value={formData.DDAY_TYPE}
                        label="D-day Type"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="P">관리자가 입력해놓은 D-day</MenuItem>
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12}>
                    <MDInput
                      label="D-day 설명"
                      name="DDAY_NAME"
                      value={formData.DDAY_NAME}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="D-day 날짜 (YYYYMMDD)"
                      name="DDAY_DATE"
                      value={formData.DDAY_DATE}
                      onChange={handleChange}
                      fullWidth
                      inputProps={{ maxLength: 8 }}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="D-day Link"
                      name="DDAY_LINK"
                      value={formData.DDAY_LINK || ""}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <FormControl fullWidth>
                      <InputLabel>개인 사용자 활성 여부</InputLabel>
                      <Select
                        name="DDAY_ACTIVE"
                        value={formData.DDAY_ACTIVE}
                        label="개인 사용자 활성 여부"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="F">비활성</MenuItem>
                        <MenuItem value="T">활성</MenuItem>
                      </Select>
                    </FormControl>
                  </Grid>

                  <Grid item xs={12} display="flex" justifyContent="center">
                    <MDButton variant="gradient" color="info" onClick={handleSubmit}>
                      {isEdit ? "수정" : "등록"}
                    </MDButton>
                    {isEdit && (
                      <MDBox ml={1}>
                        <MDButton variant="outlined" color="error" onClick={handleDelete}>
                          삭제
                        </MDButton>
                      </MDBox>
                    )}
                    <MDBox ml={1}>
                      <MDButton variant="outlined" color="secondary" onClick={() => navigate("/dday")}>
                        목록
                      </MDButton>
                    </MDBox>
                  </Grid>
                </Grid>
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default DdayDetail;
