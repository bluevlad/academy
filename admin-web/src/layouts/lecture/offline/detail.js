import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

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
import { getLectureOffDetail, insertLectureOff, updateLectureOff } from "api/lecture/lectureOff";

function OfflineLectureDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const leccode = queryParams.get("leccode");
  const bridgeLeccode = queryParams.get("bridgeLeccode"); // Might be needed

  const [formValues, setFormValues] = useState({
    categoryCd: "",
    learningCd: "",
    subjectTitle: "",
    subjectTeacher: "",
    subjectDesc: "",
    subjectPrice: "0",
    subjectRealPrice: "0",
    subjectSjtCd: "",
    subjectIsuse: "Y",
    subjectMemberCnt: "0",
    // Add more fields specifically for offline if needed
  });

  useEffect(() => {
    if (leccode) {
      fetchData(leccode);
    }
  }, [leccode]);

  const fetchData = async (code) => {
    try {
      const response = await getLectureOffDetail({ leccode: code });
      const data = response.view || response.dt || response;
      if (data) {
        setFormValues({
          categoryCd: data.categoryCd || "",
          learningCd: data.learningCd || "",
          subjectTitle: data.subjectTitle || "",
          subjectTeacher: data.subjectTeacher || "",
          subjectDesc: data.subjectDesc || "",
          subjectPrice: data.subjectPrice || "0",
          subjectRealPrice: data.subjectRealPrice || "0",
          subjectSjtCd: data.subjectSjtCd || "",
          subjectIsuse: data.subjectIsuse || "Y",
          subjectMemberCnt: data.subjectMemberCnt || "0",
        });
      }
    } catch (error) {
      console.error("Failed to fetch offline lecture details", error);
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
        await updateLectureOff({ ...formValues, leccode });
        alert("수정되었습니다.");
      } else {
        await insertLectureOff(formValues);
        alert("등록되었습니다.");
      }
      navigate("/lecture/offline");
    } catch (error) {
      console.error("Failed to save offline lecture", error);
      alert("저장에 실패했습니다.");
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
                  {leccode ? "학원 강의 수정" : "학원 강의 등록"}
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
                      label="직종 코드"
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
                      label="정가"
                      name="subjectPrice"
                      type="number"
                      value={formValues.subjectPrice}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="판매가"
                      name="subjectRealPrice"
                      type="number"
                      value={formValues.subjectRealPrice}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="수강인원"
                      name="subjectMemberCnt"
                      type="number"
                      value={formValues.subjectMemberCnt}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="사용여부 (Y/N)"
                      name="subjectIsuse"
                      value={formValues.subjectIsuse}
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
                    onClick={() => navigate("/lecture/offline")}
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

export default OfflineLectureDetail;
