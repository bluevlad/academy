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
import { getCodeList, insertCode, updateCode, deleteCode } from "api/admin/code";

function AdminCode() {
  const [codeList, setCodeList] = useState([]);

  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  // Dialog state
  const [dialogOpen, setDialogOpen] = useState(false);
  const [dialogMode, setDialogMode] = useState("add");
  const [formData, setFormData] = useState({
    codeNo: "",
    codeCd: "",
    codeNm: "",
    codeVal: "",
    sysCd: "",
    isUse: "Y",
  });

  const loadCodeData = async () => {
    setLoading(true);
    try {
      const params = {
        pageIndex: currentPage,
      };
      const data = await getCodeList(params);
      setCodeList(data.codeList || []);
      setPaginationInfo(data.paginationInfo || null);
      if (data.paginationInfo) {
        setTotalPages(
          Math.ceil(data.paginationInfo.totalRecordCount / data.paginationInfo.recordCountPerPage) ||
          1
        );
      }
    } catch (error) {
      console.error("Failed to load code data:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadCodeData();
  }, [currentPage]);

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const handleOpenDialog = (mode, code = null) => {
    setDialogMode(mode);
    if (mode === "edit" && code) {
      setFormData({
        codeNo: code.CODE_NO,
        codeCd: code.CODE_CD,
        codeNm: code.CODE_NM,
        codeVal: code.CODE_VAL,
        sysCd: code.SYS_CD,
        isUse: code.ISUSE,
      });
    } else {
      setFormData({
        codeNo: "",
        codeCd: "",
        codeNm: "",
        codeVal: "",
        sysCd: "",
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
        await insertCode(formData);
      } else {
        await updateCode(formData);
      }
      handleCloseDialog();
      loadCodeData();
    } catch (error) {
      console.error("Error submitting code:", error);
      alert("처리 중 오류가 발생했습니다.");
    }
  };

  const handleDelete = async (codeNo) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteCode(codeNo);
        loadCodeData();
      } catch (error) {
        console.error("Error deleting code:", error);
        alert("삭제 중 오류가 발생했습니다.");
      }
    }
  };

  const columns = [
    { Header: "공통코드명", accessor: "sysNm", align: "left" },
    { Header: "공통코드", accessor: "sysCd", align: "left" },
    { Header: "설정코드명", accessor: "codeNm", align: "left" },
    { Header: "설정코드", accessor: "codeCd", align: "left" },
    { Header: "설정값", accessor: "codeVal", align: "center" },
    { Header: "사용여부", accessor: "isUse", align: "center" },
    { Header: "등록일", accessor: "regDt", align: "center" },
    { Header: "Action", accessor: "action", align: "center" },
  ];

  const rows = codeList.map((code) => ({
    sysNm: code.SYS_NM,
    sysCd: code.SYS_CD,
    codeNm: code.CODE_NM,
    codeCd: code.CODE_CD,
    codeVal: code.CODE_VAL,
    isUse: (
      <Box ml={-1}>
        <Badge
          badgeContent={code.ISUSE === "Y" ? "활성" : "비활성"}
          color={code.ISUSE === "Y" ? "success" : "dark"}
          variant="contained"
          size="sm"
        />
      </Box>
    ),
    regDt: (
      <Typography variant="caption" color="text">
        {code.REG_DT}
      </Typography>
    ),
    action: (
      <Box display="flex" justifyContent="center">
        <Button
          variant="text"
          color="dark"
          iconOnly
          onClick={() => handleOpenDialog("edit", code)}
        >
          <Icon>edit</Icon>
        </Button>
        <Button variant="text" color="error" iconOnly onClick={() => handleDelete(code.CODE_NO)}>
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
                공통코드 관리
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
        <DialogTitle>{dialogMode === "add" ? "코드 등록" : "코드 수정"}</DialogTitle>
        <DialogContent>
          <Box component="form" pt={2} px={2}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="시스템 코드 (SYS_CD)"
                  name="sysCd"
                  value={formData.sysCd}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="설정 코드 (CODE_CD)"
                  name="codeCd"
                  value={formData.codeCd}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="설정 코드명 (CODE_NM)"
                  name="codeNm"
                  value={formData.codeNm}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="설정값 (CODE_VAL)"
                  name="codeVal"
                  value={formData.codeVal}
                  onChange={handleFormChange}
                />
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

export default AdminCode;
