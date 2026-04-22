import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import { useLocation, useNavigate } from "react-router-dom";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";



// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import { getPopupDetail, insertPopup, updatePopup, deletePopup } from "api/popup";

function PopupDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const popNo = queryParams.get("popNo");

  const [form, setForm] = useState({
    popTitle: "",
    popContent: "",
    startDate: "",
    endDate: "",
    useYn: "Y",
    locLeft: "0",
    locTop: "0",
    width: "400",
    height: "300",
  });

  useEffect(() => {
    if (popNo) {
      loadDetail();
    }
  }, [popNo]);

  const loadDetail = async () => {
    try {
      const result = await getPopupDetail({ popNo });
      if (result && result.data) {
        const d = result.data;
        setForm({
          popNo: d.POP_NO,
          popTitle: d.POP_TITLE || "",
          popContent: d.POP_CONTENT || "",
          startDate: d.START_DATE || "",
          endDate: d.END_DATE || "",
          useYn: d.USE_YN || "Y",
          locLeft: d.LOC_LEFT || "0",
          locTop: d.LOC_TOP || "0",
          width: d.WIDTH || "400",
          height: d.HEIGHT || "300",
        });
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    if (!form.popTitle) {
      alert("제목을 입력하세요.");
      return;
    }

    try {
      let result;
      if (popNo) {
        result = await updatePopup(form);
      } else {
        result = await insertPopup(form);
      }

      if (result.retMsg === "OK") {
        alert("저장되었습니다.");
        navigate("/popup");
      } else {
        alert("저장에 실패했습니다: " + result.errMsg);
      }
    } catch (error) {
      console.error(error);
      alert("오류가 발생했습니다.");
    }
  };

  const handleDelete = async () => {
    if (!window.confirm("삭제하시겠습니까?")) return;
    try {
      const result = await deletePopup({ popNo });
      if (result.retMsg === "OK") {
        alert("삭제되었습니다.");
        navigate("/popup");
      } else {
        alert("삭제 실패: " + result.errMsg);
      }
    } catch (error) {
      console.error(error);
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
                display="flex"
                justifyContent="space-between"
                alignItems="center"
              >
                <Typography variant="h6" color="white">
                  {popNo ? "팝업 수정" : "팝업 등록"}
                </Typography>
              </Box>
              <Box p={3}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <TextField
                      label="제목"
                      name="popTitle"
                      value={form.popTitle}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <TextField
                      type="date"
                      label="시작일"
                      name="startDate"
                      value={form.startDate}
                      onChange={handleChange}
                      InputLabelProps={{ shrink: true }}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <TextField
                      type="date"
                      label="종료일"
                      name="endDate"
                      value={form.endDate}
                      onChange={handleChange}
                      InputLabelProps={{ shrink: true }}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <FormControl fullWidth>
                      <InputLabel>사용여부</InputLabel>
                      <Select
                        name="useYn"
                        value={form.useYn}
                        label="사용여부"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="Y">사용</MenuItem>
                        <MenuItem value="N">미사용</MenuItem>
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={6}></Grid>

                  {/* Position and Size */}
                  <Grid item xs={3}>
                    <TextField
                      label="왼쪽 위치"
                      name="locLeft"
                      value={form.locLeft}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={3}>
                    <TextField
                      label="상단 위치"
                      name="locTop"
                      value={form.locTop}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={3}>
                    <TextField
                      label="너비"
                      name="width"
                      value={form.width}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={3}>
                    <TextField
                      label="높이"
                      name="height"
                      value={form.height}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>

                  <Grid item xs={12}>
                    <TextField
                      label="내용"
                      name="popContent"
                      value={form.popContent}
                      onChange={handleChange}
                      multiline
                      rows={6}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} display="flex" justifyContent="flex-end" gap={1}>
                    <Button variant="outlined" color="secondary" onClick={() => navigate("/popup")}>
                      목록
                    </Button>
                    {popNo && (
                      <Button variant="outlined" color="error" onClick={handleDelete}>
                        삭제
                      </Button>
                    )}
                    <Button variant="contained" color="info" onClick={handleSave}>
                      저장
                    </Button>
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

export default PopupDetail;
