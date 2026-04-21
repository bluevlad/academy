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
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";

// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import { getEventDetail, insertEvent, updateEvent, deleteEvent } from "api/event";

function EventDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const eventNo = queryParams.get("eventNo");

  const [form, setForm] = useState({
    eventTitle: "",
    eventContent: "",
    startDt: "",
    endDt: "",
    useYn: "Y",
  });

  useEffect(() => {
    if (eventNo) {
      loadDetail();
    }
  }, [eventNo]);

  const loadDetail = async () => {
    try {
      const result = await getEventDetail({ eventNo });
      if (result && result.detail) {
        const d = result.detail;
        setForm({
          eventNo: d.EVENT_NO,
          eventTitle: d.EVENT_TITLE || "",
          eventContent: d.EVENT_CONTENT || "",
          startDt: d.START_DT || "",
          endDt: d.END_DT || "",
          useYn: d.USE_YN || "Y",
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
    if (!form.eventTitle) {
      alert("제목을 입력하세요.");
      return;
    }

    try {
      let result;
      if (eventNo) {
        result = await updateEvent(form);
      } else {
        result = await insertEvent(form);
      }

      if (result.retMsg === "OK") {
        alert("저장되었습니다.");
        navigate("/event");
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
      const result = await deleteEvent({ eventNo });
      if (result.retMsg === "OK") {
        alert("삭제되었습니다.");
        navigate("/event");
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
                  {eventNo ? "이벤트 수정" : "이벤트 등록"}
                </MDTypography>
              </MDBox>
              <MDBox p={3}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <MDInput
                      label="제목"
                      name="eventTitle"
                      value={form.eventTitle}
                      onChange={handleChange}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <TextField
                      type="date"
                      label="시작일"
                      name="startDt"
                      value={form.startDt}
                      onChange={handleChange}
                      InputLabelProps={{ shrink: true }}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <TextField
                      type="date"
                      label="종료일"
                      name="endDt"
                      value={form.endDt}
                      onChange={handleChange}
                      InputLabelProps={{ shrink: true }}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12}>
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
                  <Grid item xs={12}>
                    <MDInput
                      label="내용"
                      name="eventContent"
                      value={form.eventContent}
                      onChange={handleChange}
                      multiline
                      rows={6}
                      fullWidth
                    />
                  </Grid>
                  <Grid item xs={12} display="flex" justifyContent="flex-end" gap={1}>
                    <MDButton
                      variant="outlined"
                      color="secondary"
                      onClick={() => navigate("/event")}
                    >
                      목록
                    </MDButton>
                    {eventNo && (
                      <MDButton variant="outlined" color="error" onClick={handleDelete}>
                        삭제
                      </MDButton>
                    )}
                    <MDButton variant="gradient" color="info" onClick={handleSave}>
                      저장
                    </MDButton>
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

export default EventDetail;
