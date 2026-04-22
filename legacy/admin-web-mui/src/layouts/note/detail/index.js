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
import { getNoteDetail, insertNote, deleteNote } from "api/note";

function NoteDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const noteNo = queryParams.get("noteNo");

  const [form, setForm] = useState({
    recvId: "",
    noteContent: "",
  });

  const [isReadOnly, setIsReadOnly] = useState(false);

  useEffect(() => {
    if (noteNo) {
      loadDetail();
      setIsReadOnly(true);
    }
  }, [noteNo]);

  const loadDetail = async () => {
    try {
      const result = await getNoteDetail({ noteNo });
      if (result && result.data) {
        const d = result.data;
        setForm({
          noteNo: d.NOTE_NO,
          recvId: d.RECV_ID || "",
          noteContent: d.NOTE_CONTENT || "",
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

  const handleSend = async () => {
    if (!form.recvId) {
      alert("받는 사람 ID를 입력하세요.");
      return;
    }
    if (!form.noteContent) {
      alert("내용을 입력하세요.");
      return;
    }

    try {
      const result = await insertNote(form);
      if (result.retMsg === "OK") {
        alert("쪽지를 보냈습니다.");
        navigate("/note");
      } else {
        alert("발송 실패: " + result.errMsg);
      }
    } catch (error) {
      console.error(error);
      alert("오류가 발생했습니다.");
    }
  };

  const handleDelete = async () => {
    if (!window.confirm("삭제하시겠습니까?")) return;
    try {
      const result = await deleteNote({ noteNo });
      if (result.retMsg === "OK") {
        alert("삭제되었습니다.");
        navigate("/note");
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
                  {noteNo ? "쪽지 상세" : "쪽지 보내기"}
                </Typography>
              </Box>
              <Box p={3}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <TextField
                      label="받는 사람 (ID)"
                      name="recvId"
                      value={form.recvId}
                      onChange={handleChange}
                      fullWidth
                      disabled={isReadOnly}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      label="내용"
                      name="noteContent"
                      value={form.noteContent}
                      onChange={handleChange}
                      multiline
                      rows={6}
                      fullWidth
                      disabled={isReadOnly}
                    />
                  </Grid>
                  <Grid item xs={12} display="flex" justifyContent="flex-end" gap={1}>
                    <Button variant="outlined" color="secondary" onClick={() => navigate("/note")}>
                      목록
                    </Button>
                    {noteNo && (
                      <Button variant="outlined" color="error" onClick={handleDelete}>
                        삭제
                      </Button>
                    )}
                    {!noteNo && (
                      <Button variant="contained" color="info" onClick={handleSend}>
                        보내기
                      </Button>
                    )}
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

export default NoteDetail;
