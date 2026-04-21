/* eslint-disable prettier/prettier */
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

import Badge from "@mui/material/Badge";


// Material Dashboard 2 React example components
import DataTable from "examples/Tables/DataTable";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";

// API
import { getAuthList, insertAuth, updateAuth, deleteAuth } from "api/admin/auth";

function AdminAuth() {
  const [authList, setAuthList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  // Dialog state
  const [dialogOpen, setDialogOpen] = useState(false);
  const [dialogMode, setDialogMode] = useState("add"); // add or edit
  const [formData, setFormData] = useState({
    siteId: "",
    siteNm: "",
    onoffDiv: "A", // A: 전체, O: 온라인, F: 오프라인, T: 모의고사, M: 모바일
    isUse: "Y",
  });

  const loadAuthData = async () => {
    setLoading(true);
    try {
      const params = {
        pageIndex: currentPage,
      };
      const data = await getAuthList(params);
      setAuthList(data.authList || []);
      setPaginationInfo(data.paginationInfo || null);
      if (data.paginationInfo) {
        setTotalPages(
          Math.ceil(data.paginationInfo.totalRecordCount / data.paginationInfo.recordCountPerPage) ||
          1
        );
      }
    } catch (error) {
      console.error("Failed to load auth data:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadAuthData();
  }, [currentPage]);

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const handleOpenDialog = (mode, auth = null) => {
    setDialogMode(mode);
    if (mode === "edit" && auth) {
      setFormData({
        siteId: auth.SITE_ID,
        siteNm: auth.SITE_NM,
        onoffDiv: auth.ONOFF_DIV,
        isUse: auth.ISUSE,
      });
    } else {
      setFormData({
        siteId: "",
        siteNm: "",
        onoffDiv: "A",
        isUse: "Y",
      });
    }
    setDialogOpen(true);
  };

  const handleCloseDialog = () => {
    setDialogOpen(false);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    try {
      if (dialogMode === "add") {
        await insertAuth(formData);
      } else {
        await updateAuth(formData);
      }
      handleCloseDialog();
      loadAuthData();
    } catch (error) {
      console.error("Error submitting auth:", error);
      alert("처리 중 오류가 발생했습니다.");
    }
  };

  const handleDelete = async (siteId) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteAuth(siteId);
        loadAuthData();
      } catch (error) {
        console.error("Error deleting auth:", error);
        alert("삭제 중 오류가 발생했습니다.");
      }
    }
  };

  const columns = [
    { Header: "사이트ID", accessor: "siteId", align: "left" },
    { Header: "사이트명", accessor: "siteNm", align: "left" },
    { Header: "관리메뉴", accessor: "onoffDiv", align: "center" },
    { Header: "사용여부", accessor: "isUse", align: "center" },
    { Header: "등록일", accessor: "regDt", align: "center" },
    { Header: "Action", accessor: "action", align: "center" },
  ];

  const rows = authList.map((auth) => ({
    siteId: auth.SITE_ID,
    siteNm: auth.SITE_NM,
    onoffDiv: (
      <Typography variant="caption" color="text">
        {auth.ONOFF_DIV_NM || auth.ONOFF_DIV}
      </Typography>
    ),
    isUse: (
      <Box ml={-1}>
        <Badge
          badgeContent={auth.ISUSE === "Y" ? "활성" : "비활성"}
          color={auth.ISUSE === "Y" ? "success" : "dark"}
          variant="contained"
          size="sm"
        />
      </Box>
    ),
    regDt: (
      <Typography variant="caption" color="text">
        {auth.REG_DT}
      </Typography>
    ),
    action: (
      <Box display="flex" justifyContent="center">
        <Button
          variant="text"
          color="dark"
          iconOnly
          onClick={() => handleOpenDialog("edit", auth)}
        >
          <Icon>edit</Icon>
        </Button>
        <Button variant="text" color="error" iconOnly onClick={() => handleDelete(auth.SITE_ID)}>
          <Icon>delete</Icon>
        </Button>
      </Box>
    ),
  }));

  return (
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
                권한 관리
              </Typography>
              <Button
                variant="contained"
                color="white"
                size="small"
                onClick={() => handleOpenDialog("add")}
              >
                <Icon>add</Icon>&nbsp;등록
              </Button>
            </Box>
            <Box pt={3}>
              {loading ? (
                <Box p={3} textAlign="center">
                  <Typography variant="caption">로딩 중...</Typography>
                </Box>
              ) : (
                <>
                  <DataTable
                    table={{ columns, rows }}
                    isSorted={false}
                    entriesPerPage={false}
                    showTotalEntries={false}
                    noEndBorder
                  />
                  {paginationInfo && (
                    <Box
                      p={3}
                      display="flex"
                      justifyContent="space-between"
                      alignItems="center"
                    >
                      <Box>
                        <Typography variant="caption" color="text">
                          전체 {paginationInfo.totalRecordCount}건 중{" "}
                          {paginationInfo.firstRecordIndex + 1} -{" "}
                          {Math.min(
                            paginationInfo.lastRecordIndex + 1,
                            paginationInfo.totalRecordCount
                          )}
                          건 표시
                        </Typography>
                      </Box>
                      <Stack spacing={2}>
                        <Pagination
                          count={totalPages}
                          page={currentPage}
                          onChange={handlePageChange}
                          color="primary"
                          shape="rounded"
                          showFirstButton
                          showLastButton
                        />
                      </Stack>
                    </Box>
                  )}
                </>
              )}
            </Box>
          </Card>
        </Grid>
      </Grid>

      {/* Dialog */}
      <Dialog open={dialogOpen} onClose={handleCloseDialog} maxWidth="sm" fullWidth>
        <DialogTitle>{dialogMode === "add" ? "권한 등록" : "권한 수정"}</DialogTitle>
        <DialogContent>
          <Box component="form" pt={2} px={2}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="사이트 ID"
                  name="siteId"
                  value={formData.siteId}
                  onChange={handleFormChange}
                  disabled={dialogMode === "edit"}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="사이트명"
                  name="siteNm"
                  value={formData.siteNm}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth>
                  <InputLabel>관리메뉴 구분</InputLabel>
                  <Select
                    name="onoffDiv"
                    value={formData.onoffDiv}
                    label="관리메뉴 구분"
                    onChange={handleFormChange}
                  >
                    <MenuItem value="A">전체</MenuItem>
                    <MenuItem value="O">온라인</MenuItem>
                    <MenuItem value="F">오프라인</MenuItem>
                    <MenuItem value="T">모의고사</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth>
                  <InputLabel>사용여부</InputLabel>
                  <Select
                    name="isUse"
                    value={formData.isUse}
                    label="사용여부"
                    onChange={handleFormChange}
                  >
                    <MenuItem value="Y">사용</MenuItem>
                    <MenuItem value="N">미사용</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
            </Grid>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog} color="secondary">
            취소
          </Button>
          <Button onClick={handleSubmit} color="info" variant="contained">
            {dialogMode === "add" ? "등록" : "수정"}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}

export default AdminAuth;
