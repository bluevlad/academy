import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import MenuItem from "@mui/material/MenuItem"; // For Select if I use it

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import TextField from "@mui/material/TextField";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import { getLectureView, insertLecture, updateLecture } from "api/lecture";

function OnlineLectureDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const leccode = queryParams.get("leccode");

  const [formValues, setFormValues] = useState({
    categoryCd: "",
    learningCd: "",
    subjectTitle: "",
    subjectTeacher: "",
    subjectDesc: "",
    subjectPrice: "0",
    subjectPeriod: "0",
    subjectSjtCd: "",
    isUse: "Y",
  });

  useEffect(() => {
    if (leccode) {
      fetchData(leccode);
    }
  }, [leccode]);

  const fetchData = async (code) => {
    try {
      const response = await getLectureView({ leccode: code });
      // Assuming response structure based on typical legacy backend patterns (uppercase keys or nested object)
      // If response.view is the object
      const data = response.view || response.dt || response;
      if (data) {
        setFormValues({
          categoryCd: data.CATEGORY_CD || data.categoryCd || "",
          learningCd: data.LEARNING_CD || data.learningCd || "",
          subjectTitle: data.SUBJECT_TITLE || data.subjectTitle || "",
          subjectTeacher: data.SUBJECT_TEACHER || data.subjectTeacher || "",
          subjectDesc: data.SUBJECT_DESC || data.subjectDesc || "",
          subjectPrice: data.SUBJECT_PRICE || data.subjectPrice || "0",
          subjectPeriod: data.SUBJECT_PERIOD || data.subjectPeriod || "0",
          subjectSjtCd: data.SUBJECT_SJT_CD || data.subjectSjtCd || "",
          isUse: data.ISUSE || data.isUse || "Y",
        });
      }
    } catch (error) {
      console.error("Failed to fetch lecture details", error);
      alert("데이터를 불러오는 중 오류가 발생했습니다.");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    try {
      if (leccode) {
        await updateLecture({ ...formValues, leccode });
        alert("수정되었습니다.");
      } else {
        await insertLecture(formValues);
        alert("등록되었습니다.");
      }
      navigate("/lecture/online");
    } catch (error) {
      console.error("Failed to save lecture", error);
      alert("저장에 실패했습니다.");
    }
  };

  const handleDelete = async () => {
    // Implement delete if needed, or stick to list view deleting
    if (window.confirm("정말 삭제하시겠습니까?")) {
      // await deleteLecture({ leccode });
      // navigate("/lecture/online");
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
                  {leccode ? "단과 강의 수정" : "단과 강의 등록"}
                </Typography>
              </Box>
              <Box pt={4} pb={3} px={3}>
                <Grid container spacing={3}>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="강좌명"
                      name="subjectTitle"
                      value={formValues.subjectTitle}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="강의설명"
                      name="subjectDesc"
                      value={formValues.subjectDesc}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="카테고리 코드"
                      name="categoryCd"
                      value={formValues.categoryCd}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="학습형태 코드"
                      name="learningCd"
                      value={formValues.learningCd}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="과목 코드"
                      name="subjectSjtCd"
                      value={formValues.subjectSjtCd}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="강사 ID/코드"
                      name="subjectTeacher"
                      value={formValues.subjectTeacher}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="가격"
                      name="subjectPrice"
                      type="number"
                      value={formValues.subjectPrice}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="수강기간(일)"
                      name="subjectPeriod"
                      type="number"
                      value={formValues.subjectPeriod}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="사용여부 (Y/N)"
                      name="isUse"
                      value={formValues.isUse}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                </Grid>
                <Box mt={4} display="flex" justifyContent="flex-end">
                  <Button variant="contained" color="info" onClick={handleSubmit}>
                    저장
                  </Button>
                  <Button
                    variant="outlined"
                    color="secondary"
                    onClick={() => navigate("/lecture/online")}
                    sx={{ ml: 2 }}
                  >
                    취소
                  </Button>
                </Box>
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default OnlineLectureDetail;
