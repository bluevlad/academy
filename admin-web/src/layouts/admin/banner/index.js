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
import { getBannerList, insertBanner, updateBanner, deleteBanner } from "api/banner";

function AdminBanner() {
  const [bannerList, setBannerList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState(null);

  // Dialog state
  const [dialogOpen, setDialogOpen] = useState(false);
  const [dialogMode, setDialogMode] = useState("add");
  const [formData, setFormData] = useState({
    bannerCd: "",
    bannerNm: "",
    bannerCount: 0,
    startNum: 1,
    endNum: 1,
    isUse: "Y",
  });

  const loadBannerData = async () => {
    setLoading(true);
    try {
      const params = {
        pageIndex: currentPage,
      };
      const data = await getBannerList(params);
      setBannerList(data.bannerList || []);
      setPaginationInfo(data.paginationInfo || null);
      if (data.paginationInfo) {
        setTotalPages(
          Math.ceil(data.paginationInfo.totalRecordCount / data.paginationInfo.recordCountPerPage) ||
          1
        );
      }
    } catch (error) {
      console.error("Failed to load banner data:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadBannerData();
  }, [currentPage]);

  const handlePageChange = (event, value) => {
    setCurrentPage(value);
  };

  const handleOpenDialog = (mode, banner = null) => {
    setDialogMode(mode);
    if (mode === "edit" && banner) {
      setFormData({
        bannerCd: banner.bannerCd,
        bannerNm: banner.bannerNm,
        bannerCount: banner.bannerCount || 0,
        startNum: banner.startNum || 1,
        endNum: banner.endNum || 1,
        isUse: banner.isUse || "Y",
      });
    } else {
      setFormData({
        bannerCd: "",
        bannerNm: "",
        bannerCount: 0,
        startNum: 1,
        endNum: 1,
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
        await insertBanner(formData);
      } else {
        await updateBanner(formData);
      }
      handleCloseDialog();
      loadBannerData();
    } catch (error) {
      console.error("Error submitting banner:", error);
      alert("처리 중 오류가 발생했습니다.");
    }
  };

  const handleDelete = async (bannerCd) => {
    if (window.confirm("정말 삭제하시겠습니까? 연결된 아이템도 모두 삭제됩니다.")) {
      try {
        await deleteBanner(bannerCd);
        loadBannerData();
      } catch (error) {
        console.error("Error deleting banner:", error);
        alert("삭제 중 오류가 발생했습니다.");
      }
    }
  };

  const columns = [
    { Header: "배너코드", accessor: "bannerCd", align: "left" },
    { Header: "배너명", accessor: "bannerNm", align: "left" },
    { Header: "사용여부", accessor: "isUse", align: "center" },
    { Header: "등록일", accessor: "regDt", align: "center" },
    { Header: "Action", accessor: "action", align: "center" },
  ];

  const rows = bannerList.map((banner) => ({
    bannerCd: banner.bannerCd,
    bannerNm: banner.bannerNm,
    isUse: (
      <Box ml={-1}>
        <Badge
          badgeContent={banner.isUse === "Y" ? "활성" : "비활성"}
          color={banner.isUse === "Y" ? "success" : "dark"}
          variant="contained"
          size="sm"
        />
      </Box>
    ),
    regDt: (
      <Typography variant="caption" color="text">
        {banner.regDt}
      </Typography>
    ),
    action: (
      <Box display="flex" justifyContent="center">
        <Button
          variant="text"
          color="dark"
          iconOnly
          onClick={() => handleOpenDialog("edit", banner)}
        >
          <Icon>edit</Icon>
        </Button>
        <Button
          variant="text"
          color="error"
          iconOnly
          onClick={() => handleDelete(banner.bannerCd)}
        >
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
                배너 관리
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
        <DialogTitle>{dialogMode === "add" ? "배너 등록" : "배너 수정"}</DialogTitle>
        <DialogContent>
          <Box component="form" pt={2} px={2}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="배너 코드"
                  name="bannerCd"
                  value={formData.bannerCd}
                  onChange={handleFormChange}
                  disabled={dialogMode === "edit"}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="배너명"
                  name="bannerNm"
                  value={formData.bannerNm}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  type="number"
                  label="배너 수"
                  name="bannerCount"
                  value={formData.bannerCount}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  type="number"
                  label="시작 번호 (File Name Suffix)"
                  name="startNum"
                  value={formData.startNum}
                  onChange={handleFormChange}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  type="number"
                  label="종료 번호 (File Name Suffix)"
                  name="endNum"
                  value={formData.endNum}
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
            {dialogMode === "add" ? "등록" : "수정"}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}

export default AdminBanner;
