import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Button from "@mui/material/Button";

import Typography from "@mui/material/Typography";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import {
  getBoardCodeList,
  insertBoardCatInfo,
  deleteBoardCatInfo,
  getBoardMngList,
} from "api/board";

function BoardViewManagement() {
  const [boardCodeList, setBoardCodeList] = useState([]);
  const [boardMngList, setBoardMngList] = useState([]);
  const [loading, setLoading] = useState(true);

  // Dialog
  const [dialogOpen, setDialogOpen] = useState(false);
  const [formData, setFormData] = useState({
    boardMngSeq: "",
    categoryCode: "",
    categoryName: "",
  });

  useEffect(() => {
    loadData();
    loadBoardMngs();
  }, []);

  const loadData = async (boardMngSeq = "") => {
    setLoading(true);
    try {
      const data = await getBoardCodeList({ boardMngSeq });
      setBoardCodeList(data.boardCodeList || []);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const loadBoardMngs = async () => {
    try {
      const data = await getBoardMngList({ pageIndex: 1, recordCountPerPage: 100 }); // Fetch all reasonable amount
      setBoardMngList(data.boardMngList || []);
    } catch (error) {
      console.error(error);
    }
  };

  const handleOpenDialog = () => {
    setFormData({
      boardMngSeq: "",
      categoryCode: "",
      categoryName: "",
    });
    setDialogOpen(true);
  };

  const handleCloseDialog = () => {
    setDialogOpen(false);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    try {
      await insertBoardCatInfo(formData);
      handleCloseDialog();
      loadData(formData.boardMngSeq);
    } catch (error) {
      console.error(error);
      alert("처리 중 오류가 발생했습니다.");
    }
  };

  const handleDelete = async (item) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteBoardCatInfo(item);
        loadData(item.boardMngSeq);
      } catch (error) {
        console.error(error);
        alert("삭제 중 오류가 발생했습니다.");
      }
    }
  };

  const handleBoardChange = (event) => {
    const seq = event.target.value;
    loadData(seq);
  };

  const columns = [
    { Header: "게시판 번호", accessor: "boardMngSeq", align: "center" },
    { Header: "카테고리 코드", accessor: "categoryCode", align: "center" },
    { Header: "카테고리 이름", accessor: "categoryName", align: "left" },
    { Header: "관리", accessor: "action", align: "center" },
  ];

  const rows = boardCodeList.map((item) => ({
    boardMngSeq: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {item.boardMngSeq}
      </Typography>
    ),
    categoryCode: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {item.categoryCode}
      </Typography>
    ),
    categoryName: (
      <Typography variant="caption" color="text">
        {item.categoryName}
      </Typography>
    ),
    action: (
      <Box display="flex" justifyContent="center">
        <Button variant="text" color="error" iconOnly onClick={() => handleDelete(item)}>
          <Icon>delete</Icon>
        </Button>
      </Box>
    ),
  }));

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
                bgColor="dark"
                borderRadius="lg"
                coloredShadow="dark"
                display="flex"
                justifyContent="space-between"
                alignItems="center"
              >
                <Typography variant="h6" color="white">
                  게시판 카테고리(View) 관리
                </Typography>
                <Button variant="contained" color="white" size="small" onClick={handleOpenDialog}>
                  <Icon>add</Icon>&nbsp;등록
                </Button>
              </Box>
              <Box p={3}>
                <Grid container spacing={3} mb={3}>
                  <Grid item xs={12} md={4}>
                    <FormControl fullWidth>
                      <InputLabel>게시판 선택</InputLabel>
                      <Select label="게시판 선택" onChange={handleBoardChange} defaultValue="">
                        <MenuItem value="">전체</MenuItem>
                        {boardMngList.map((b) => (
                          <MenuItem key={b.boardMngSeq} value={b.boardMngSeq}>
                            {b.boardMngName}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                </Grid>

                {loading ? (
                  <Box p={3} textAlign="center">
                    <Typography variant="caption">로딩 중...</Typography>
                  </Box>
                ) : (
                  <DataTable
                    table={{ columns, rows }}
                    isSorted={false}
                    entriesPerPage={false}
                    showTotalEntries={false}
                    noEndBorder
                  />
                )}
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>

      <Dialog open={dialogOpen} onClose={handleCloseDialog} maxWidth="sm" fullWidth>
        <DialogTitle>카테고리 등록</DialogTitle>
        <DialogContent>
          <Box component="form" pt={2}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <FormControl fullWidth>
                  <InputLabel>게시판</InputLabel>
                  <Select
                    name="boardMngSeq"
                    value={formData.boardMngSeq}
                    onChange={handleFormChange}
                    label="게시판"
                  >
                    {boardMngList.map((b) => (
                      <MenuItem key={b.boardMngSeq} value={b.boardMngSeq}>
                        {b.boardMngName}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="카테고리 코드"
                  name="categoryCode"
                  value={formData.categoryCode}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="카테고리 이름"
                  name="categoryName"
                  value={formData.categoryName}
                  onChange={handleFormChange}
                />
              </Grid>
            </Grid>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog} color="secondary">
            취소
          </Button>
          <Button onClick={handleSubmit} color="info" variant="contained">
            등록
          </Button>
        </DialogActions>
      </Dialog>
      <Footer />
    </DashboardLayout>
  );
}

export default BoardViewManagement;
