/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";

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
import { fetchCoopBoardDetail, insertCoopBoard, updateCoopBoard, deleteCoopBoard, fetchCoopBoardList } from "api/coop";

function CoopBoardWrite() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const boardSeq = queryParams.get("boardSeq");
  const boardMngSeq = queryParams.get("boardMngSeq") || "COOP_NOTICE";

  const [formData, setFormData] = useState({
    BOARD_MNG_SEQ: boardMngSeq,
    SUBJECT: "",
    CONTENT: "",
    NOTICE_YN: "N",
    COOP_AREA: "",
    COOP_HSPT: ""
  });

  const [codes, setCodes] = useState({
    areaList: [],
    hsptList: []
  });

  useEffect(() => {
    const loadInit = async () => {
      // Fetch codes even for new write by calling detail or list? 
      // Detail with empty query usually returns codes if controller is set up right, 
      // OR we can make a dummy call to list list to get codes.
      // CoopApi.getCoopBoardList returns codeAreaList.
      const listResult = await fetchCoopBoardList({ pageIndex: 1, pageUnit: 1 });
      if (listResult) {
        setCodes({
          areaList: listResult.codeAreaList || [],
          hsptList: listResult.codeHsptList || []
        });
      }

      if (boardSeq) {
        const result = await fetchCoopBoardDetail({ BOARD_SEQ: boardSeq, BOARD_MNG_SEQ: boardMngSeq });
        if (result && result.coopBoardDetail) {
          const data = result.coopBoardDetail;
          setFormData({
            BOARD_SEQ: data.BOARD_SEQ,
            BOARD_MNG_SEQ: data.BOARD_MNG_SEQ,
            SUBJECT: data.SUBJECT,
            CONTENT: data.CONTENT,
            NOTICE_YN: data.NOTICE_YN || "N",
            COOP_AREA: data.COOP_AREA || "",
            COOP_HSPT: data.COOP_HSPT || ""
          });
        }
      }
    };
    loadInit();
  }, [boardSeq, boardMngSeq]);

  const handleChange = (e) => {
    const { name, value, checked, type } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? (checked ? "Y" : "N") : value
    }));
  };

  const handleSubmit = async () => {
    if (!formData.SUBJECT) { alert("제목을 입력하세요"); return; }

    try {
      if (boardSeq) {
        await updateCoopBoard(formData);
        alert("수정되었습니다.");
      } else {
        await insertCoopBoard(formData);
        alert("등록되었습니다.");
      }
      navigate("/coop/board");
    } catch (e) {
      alert("저장 중 오류 발생");
    }
  };

  const handleDelete = async () => {
    if (window.confirm("삭제하시겠습니까?")) {
      await deleteCoopBoard({ BOARD_SEQ: formData.BOARD_SEQ, BOARD_MNG_SEQ: formData.BOARD_MNG_SEQ });
      navigate("/coop/board");
    }
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox pt={6} pb={3}>
        <Grid container spacing={3}>
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
                  제휴사 게시판 {boardSeq ? "수정" : "등록"}
                </MDTypography>
              </MDBox>
              <MDBox p={3}>
                <Grid container spacing={2}>
                  <Grid item xs={12} md={4}>
                    <FormControl fullWidth>
                      <InputLabel>지역</InputLabel>
                      <Select
                        name="COOP_AREA"
                        value={formData.COOP_AREA}
                        label="지역"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="">선택</MenuItem>
                        {codes.areaList.map(c => <MenuItem key={c.CODE} value={c.CODE}>{c.NAME}</MenuItem>)}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <FormControl fullWidth>
                      <InputLabel>병원</InputLabel>
                      <Select
                        name="COOP_HSPT"
                        value={formData.COOP_HSPT}
                        label="병원"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="">선택</MenuItem>
                        {codes.hsptList.map(c => <MenuItem key={c.CODE} value={c.CODE}>{c.NAME}</MenuItem>)}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <FormControlLabel control={<Checkbox checked={formData.NOTICE_YN === 'Y'} name="NOTICE_YN" onChange={handleChange} />} label="공지여부" />
                  </Grid>
                  <Grid item xs={12}>
                    <MDInput
                      label="제목"
                      name="SUBJECT"
                      fullWidth
                      value={formData.SUBJECT}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <MDInput
                      label="내용"
                      name="CONTENT"
                      fullWidth
                      multiline
                      rows={10}
                      value={formData.CONTENT}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} display="flex" justifyContent="center">
                    <MDButton variant="gradient" color="info" onClick={handleSubmit}>
                      저장
                    </MDButton>
                    {boardSeq && (
                      <MDBox ml={1}>
                        <MDButton variant="outlined" color="error" onClick={handleDelete}>
                          삭제
                        </MDButton>
                      </MDBox>
                    )}
                    <MDBox ml={1}>
                      <MDButton variant="outlined" color="secondary" onClick={() => navigate("/coop/board")}>
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

export default CoopBoardWrite;
