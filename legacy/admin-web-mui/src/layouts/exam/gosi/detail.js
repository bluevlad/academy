import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import {
  getSampleUserDetail,
  insertSampleUser,
  updateSampleUser,
  deleteSampleUser,
} from "api/exam/gosi";

function GosiDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const rstNo = queryParams.get("rstNo");

  const [formValues, setFormValues] = useState({
    userNm: "",
    userAge: "",
    studyWait: "",
    studyType: "1", // Default value
    addPoint: "0",
    examStat: "",
    userSex: "M",
    isuse: "Y",
  });

  useEffect(() => {
    if (rstNo) {
      fetchData(rstNo);
    }
  }, [rstNo]);

  const fetchData = async (id) => {
    try {
      const response = await getSampleUserDetail({ rstNo: id });
      if (response && response.data) {
        setFormValues(response.data);
      }
    } catch (error) {
      console.error("Failed to fetch detail", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    try {
      if (rstNo) {
        await updateSampleUser({ ...formValues, rstNo });
        alert("수정되었습니다.");
      } else {
        await insertSampleUser(formValues);
        alert("등록되었습니다.");
        navigate("/exam/gosi");
      }
    } catch (error) {
      console.error("Operation failed", error);
      alert("작업에 실패했습니다.");
    }
  };

  const handleDelete = async () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteSampleUser({ rstNo });
        alert("삭제되었습니다.");
        navigate("/exam/gosi");
      } catch (error) {
        alert("삭제 실패");
      }
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
                  {rstNo ? "샘플 사용자 수정" : "샘플 사용자 등록"}
                </Typography>
              </Box>
              <Box pt={4} pb={3} px={3}>
                <Grid container spacing={3}>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="이름"
                      name="userNm"
                      value={formValues.userNm}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="나이"
                      name="userAge"
                      value={formValues.userAge}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="학습기간"
                      name="studyWait"
                      value={formValues.studyWait}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      select
                      label="유형"
                      name="studyType"
                      value={formValues.studyType || "1"}
                      onChange={handleChange}
                      fullWidth
                    >
                      <MenuItem value="1">유형 1</MenuItem>
                      <MenuItem value="2">유형 2</MenuItem>
                    </TextField>
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="가산점"
                      name="addPoint"
                      value={formValues.addPoint}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      select
                      label="성별"
                      name="userSex"
                      value={formValues.userSex || "M"}
                      onChange={handleChange}
                      fullWidth
                    >
                      <MenuItem value="M">남성</MenuItem>
                      <MenuItem value="F">여성</MenuItem>
                    </TextField>
                  </Grid>
                  <Grid item xs={12}>
                    <Box display="flex" justifyContent="flex-end">
                      <Button variant="contained" color="info" onClick={handleSubmit}>
                        {rstNo ? "수정" : "등록"}
                      </Button>
                      {rstNo && (
                        <Button
                          variant="contained"
                          color="error"
                          onClick={handleDelete}
                          sx={{ ml: 1 }}
                        >
                          삭제
                        </Button>
                      )}
                      <Button
                        variant="outlined"
                        color="secondary"
                        onClick={() => navigate("/exam/gosi")}
                        sx={{ ml: 1 }}
                      >
                        취소
                      </Button>
                    </Box>
                  </Grid>
                </Grid>
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default GosiDetail;
