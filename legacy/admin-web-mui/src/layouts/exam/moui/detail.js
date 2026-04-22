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
import { getMouiExamDetail, insertMouiExam, updateMouiExam, deleteMouiExam } from "api/exam/moui";

function MouiDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const examSeq = queryParams.get("examSeq");

  const [formValues, setFormValues] = useState({
    examNm: "",
    examType: "ONLINE",
    examYear: new Date().getFullYear().toString(),
    examRound: "1",
    examDate: "",
    status: "READY",
    examStartTime: "",
    examEndTime: "",
    examMinute: "0",
    regStartDate: "",
    regEndDate: "",
    isuse: "Y",
  });

  useEffect(() => {
    if (examSeq) {
      fetchData(examSeq);
    }
  }, [examSeq]);

  const fetchData = async (seq) => {
    try {
      const response = await getMouiExamDetail({ examSeq: seq });
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
      if (examSeq) {
        await updateMouiExam({ ...formValues, examSeq });
        alert("수정되었습니다.");
      } else {
        await insertMouiExam(formValues);
        alert("등록되었습니다.");
        navigate("/exam/moui");
      }
    } catch (error) {
      console.error("Operation failed", error);
      alert("작업에 실패했습니다.");
    }
  };

  const handleDelete = async () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteMouiExam({ examSeq });
        alert("삭제되었습니다.");
        navigate("/exam/moui");
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
                bgColor="success"
                borderRadius="lg"
                coloredShadow="success"
              >
                <Typography variant="h6" color="white">
                  {examSeq ? "모의고사 수정" : "모의고사 등록"}
                </Typography>
              </Box>
              <Box pt={4} pb={3} px={3}>
                <Grid container spacing={3}>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="시험명"
                      name="examNm"
                      value={formValues.examNm}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      select
                      label="시험 유형"
                      name="examType"
                      value={formValues.examType || "ONLINE"}
                      onChange={handleChange}
                      fullWidth
                    >
                      <MenuItem value="ONLINE">온라인</MenuItem>
                      <MenuItem value="OFFLINE">오프라인</MenuItem>
                    </TextField>
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <TextField
                      label="연도"
                      name="examYear"
                      value={formValues.examYear}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <TextField
                      label="회차"
                      name="examRound"
                      value={formValues.examRound}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      select
                      label="상태"
                      name="status"
                      value={formValues.status || "READY"}
                      onChange={handleChange}
                      fullWidth
                    >
                      <MenuItem value="READY">준비</MenuItem>
                      <MenuItem value="OPEN">접수중</MenuItem>
                      <MenuItem value="CLOSE">마감</MenuItem>
                      <MenuItem value="FINISH">종료</MenuItem>
                    </TextField>
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="시험일자"
                      name="examDate"
                      value={formValues.examDate}
                      onChange={handleChange}
                      placeholder="YYYY-MM-DD"
                      fullWidth
                      InputLabelProps={{ shrink: true }}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="접수 시작일"
                      name="regStartDate"
                      value={formValues.regStartDate}
                      onChange={handleChange}
                      placeholder="YYYY-MM-DD"
                      fullWidth
                      InputLabelProps={{ shrink: true }}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="접수 종료일"
                      name="regEndDate"
                      value={formValues.regEndDate}
                      onChange={handleChange}
                      placeholder="YYYY-MM-DD"
                      fullWidth
                      InputLabelProps={{ shrink: true }}
                    />
                  </Grid>

                  <Grid item xs={12}>
                    <Box display="flex" justifyContent="flex-end">
                      <Button variant="contained" color="info" onClick={handleSubmit}>
                        {examSeq ? "수정" : "등록"}
                      </Button>
                      {examSeq && (
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
                        onClick={() => navigate("/exam/moui")}
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

export default MouiDetail;
