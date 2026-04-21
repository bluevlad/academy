import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDInput from "components/MDInput";
import MDButton from "components/MDButton";

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
                  {leccode ? "학원 강의 수정" : "학원 강의 등록"}
                </MDTypography>
              </MDBox>
              <MDBox pt={4} pb={3} px={3}>
                <Grid container spacing={3}>
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="강좌명"
                      name="subjectTitle"
                      value={formValues.subjectTitle}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <MDInput
                      label="강의설명"
                      name="subjectDesc"
                      value={formValues.subjectDesc}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="직종 코드"
                      name="categoryCd"
                      value={formValues.categoryCd}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="학습형태 코드"
                      name="learningCd"
                      value={formValues.learningCd}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="과목 코드"
                      name="subjectSjtCd"
                      value={formValues.subjectSjtCd}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="강사 ID/코드"
                      name="subjectTeacher"
                      value={formValues.subjectTeacher}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="정가"
                      name="subjectPrice"
                      type="number"
                      value={formValues.subjectPrice}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="판매가"
                      name="subjectRealPrice"
                      type="number"
                      value={formValues.subjectRealPrice}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="수강인원"
                      name="subjectMemberCnt"
                      type="number"
                      value={formValues.subjectMemberCnt}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <MDInput
                      label="사용여부 (Y/N)"
                      name="subjectIsuse"
                      value={formValues.subjectIsuse}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                </Grid>
                <MDBox mt={4} display="flex" justifyContent="flex-end">
                  <MDButton variant="gradient" color="info" onClick={handleSubmit}>
                    저장
                  </MDButton>
                  <MDButton
                    variant="outlined"
                    color="secondary"
                    onClick={() => navigate("/lecture/offline")}
                    sx={{ ml: 2 }}
                  >
                    취소
                  </MDButton>
                </MDBox>
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default OfflineLectureDetail;
