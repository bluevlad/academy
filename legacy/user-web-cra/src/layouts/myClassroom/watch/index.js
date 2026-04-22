import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";

// Layout components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// Custom components
import VideoPlayer from "components/VideoPlayer";

function MyClassroomWatch() {
  const { search } = useLocation();
  const navigate = useNavigate();
  const queryParams = new URLSearchParams(search);
  const leccode = queryParams.get("leccode");

  // Mock Data - In real app, fetch from API based on leccode
  const [lectureData, setLectureData] = useState({
    title: "리액트 기초 마스터하기",
    videoUrl: "https://www.youtube.com/watch?v=ysz5S6PUM-U", // Sample URL
    progress: 0.2, // 20% started
  });

  const handleProgress = (state) => {
    // Save progress to backend
    // console.log("Current progress:", state.played);
  };

  const handleEnded = () => {
    alert("학습을 완료했습니다! 수고하셨습니다.");
    // Update status to 'completed' in backend
  };

  const handleBack = () => {
    navigate("/my-classroom");
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
                  강의 학습실
                </MDTypography>
                <MDButton variant="outlined" color="white" size="small" onClick={handleBack}>
                  목록으로
                </MDButton>
              </MDBox>
              <MDBox pt={4} pb={3} px={3}>
                <Grid container spacing={3}>
                  <Grid item xs={12} lg={8}>
                    <VideoPlayer
                      url={lectureData.videoUrl}
                      title={lectureData.title}
                      initialProgress={lectureData.progress}
                      onProgress={handleProgress}
                      onEnded={handleEnded}
                    />
                  </Grid>
                  <Grid item xs={12} lg={4}>
                    <MDBox mb={3}>
                      <MDTypography variant="h6" fontWeight="medium" gutterBottom>
                        강의 정보
                      </MDTypography>
                      <MDTypography variant="body2" color="text">
                        이 강의는 리액트의 기초부터 심화까지 아우르는 포괄적인 과정을 담고 있습니다.
                      </MDTypography>
                    </MDBox>
                    <MDBox mb={3}>
                      <MDTypography variant="h6" fontWeight="medium" gutterBottom>
                        학습 노트
                      </MDTypography>
                      <MDTypography variant="button" color="text" fontWeight="regular">
                        (학습 노트를 작성하는 기능이 여기에 들어갈 수 있습니다.)
                      </MDTypography>
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

export default MyClassroomWatch;
