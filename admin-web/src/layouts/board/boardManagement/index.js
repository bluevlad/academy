import { useState, useEffect, useMemo } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { boardMngSchema } from "types/schemas";

// @mui
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import Chip from "@mui/material/Chip";
import IconButton from "@mui/material/IconButton";

// MD Components
import Box from "@mui/material/Box";

import Button from "@mui/material/Button";

import Typography from "@mui/material/Typography";


// Layout
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// Shared Components
import { ServerDataTable } from "shared/components/DataTable";
import { FormTextField, FormSelect } from "shared/components/FormFields";

// API
import {
  getBoardMngList,
  insertBoardMng,
  updateBoardMng,
  deleteBoardMng,
  getBoardTypeList,
} from "api/board";

const YN_OPTIONS = [
  { value: "Y", label: "가능" },
  { value: "N", label: "불가" },
];

const USE_OPTIONS = [
  { value: "Y", label: "사용" },
  { value: "N", label: "미사용" },
];

const DEFAULT_VALUES = {
  boardMngName: "",
  boardMngType: "",
  isUse: "Y",
  attachFileYn: "Y",
  replyYn: "Y",
  commentYn: "Y",
};

function BoardManagement() {
  const [boardMngList, setBoardMngList] = useState([]);
  const [boardTypeList, setBoardTypeList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [paginationInfo, setPaginationInfo] = useState({});

  // Dialog
  const [dialogOpen, setDialogOpen] = useState(false);
  const [dialogMode, setDialogMode] = useState("add");
  const [editSeq, setEditSeq] = useState(null);

  // React Hook Form + Zod
  const {
    control,
    handleSubmit,
    reset,
    formState: { isSubmitting },
  } = useForm({
    resolver: zodResolver(boardMngSchema),
    defaultValues: DEFAULT_VALUES,
  });

  useEffect(() => {
    loadData();
    loadBoardTypes();
  }, [currentPage]);

  const loadData = async () => {
    setLoading(true);
    try {
      const data = await getBoardMngList({ currentPageNo: currentPage });
      setBoardMngList(data.boardMngList || []);
      setPaginationInfo(data.paginationInfo || {});
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const loadBoardTypes = async () => {
    try {
      const data = await getBoardTypeList();
      setBoardTypeList(data.boardTypeList || []);
    } catch (error) {
      console.error(error);
    }
  };

  const boardTypeOptions = boardTypeList.map((t) => ({
    value: t.CODE_CD,
    label: t.CODE_NM,
  }));

  const handleOpenDialog = (mode, item = null) => {
    setDialogMode(mode);
    if (mode === "edit" && item) {
      setEditSeq(item.boardMngSeq);
      reset({
        boardMngName: item.boardMngName || "",
        boardMngType: item.boardMngType || "",
        isUse: item.isUse || "Y",
        attachFileYn: item.attachFileYn || "Y",
        replyYn: item.replyYn || "Y",
        commentYn: item.commentYn || "Y",
      });
    } else {
      setEditSeq(null);
      reset(DEFAULT_VALUES);
    }
    setDialogOpen(true);
  };

  const handleCloseDialog = () => setDialogOpen(false);

  const onSubmit = async (formData) => {
    try {
      if (dialogMode === "add") {
        await insertBoardMng(formData);
      } else {
        await updateBoardMng({ ...formData, boardMngSeq: editSeq });
      }
      handleCloseDialog();
      loadData();
    } catch (error) {
      console.error(error);
      alert("처리 중 오류가 발생했습니다.");
    }
  };

  const handleDelete = async (boardMngSeq) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteBoardMng(boardMngSeq);
        loadData();
      } catch (error) {
        console.error(error);
        alert("삭제 중 오류가 발생했습니다.");
      }
    }
  };

  // TanStack Table 컬럼
  const columns = useMemo(
    () => [
      { accessorKey: "boardMngSeq", header: "번호", size: 70 },
      { accessorKey: "boardMngName", header: "게시판명", size: 180 },
      {
        accessorKey: "boardMngType",
        header: "유형",
        size: 100,
        cell: ({ row }) => row.original.boardMngTypeName || row.original.boardMngType,
      },
      {
        accessorKey: "isUse",
        header: "사용여부",
        size: 90,
        cell: ({ getValue }) => (
          <Chip
            label={getValue() === "Y" ? "사용" : "미사용"}
            size="small"
            color={getValue() === "Y" ? "success" : "default"}
            variant="outlined"
          />
        ),
      },
      {
        accessorKey: "attachFileYn",
        header: "첨부파일",
        size: 80,
        cell: ({ getValue }) => (getValue() === "Y" ? "가능" : "불가"),
      },
      {
        accessorKey: "replyYn",
        header: "답글",
        size: 70,
        cell: ({ getValue }) => (getValue() === "Y" ? "가능" : "불가"),
      },
      {
        accessorKey: "commentYn",
        header: "댓글",
        size: 70,
        cell: ({ getValue }) => (getValue() === "Y" ? "가능" : "불가"),
      },
      { accessorKey: "regDt", header: "등록일", size: 100 },
      {
        id: "action",
        header: "관리",
        size: 90,
        cell: ({ row }) => (
          <Box display="flex" justifyContent="center">
            <IconButton size="small" color="info" onClick={() => handleOpenDialog("edit", row.original)}>
              <Icon fontSize="small">edit</Icon>
            </IconButton>
            <IconButton size="small" color="error" onClick={() => handleDelete(row.original.boardMngSeq)}>
              <Icon fontSize="small">delete</Icon>
            </IconButton>
          </Box>
        ),
      },
    ],
    [boardTypeList]
  );

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
                  게시판 관리
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
              <Box p={2}>
                <ServerDataTable
                  columns={columns}
                  data={boardMngList}
                  pagination={paginationInfo}
                  onPageChange={(page) => setCurrentPage(page)}
                  loading={loading}
                />
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>

      {/* 등록/수정 Dialog - React Hook Form + Zod */}
      <Dialog open={dialogOpen} onClose={handleCloseDialog} maxWidth="sm" fullWidth>
        <DialogTitle>{dialogMode === "add" ? "게시판 등록" : "게시판 수정"}</DialogTitle>
        <DialogContent>
          <Box component="form" pt={2}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <FormTextField
                  control={control}
                  name="boardMngName"
                  label="게시판명"
                />
              </Grid>
              <Grid item xs={12}>
                <FormSelect
                  control={control}
                  name="boardMngType"
                  label="게시판 유형"
                  options={boardTypeOptions}
                />
              </Grid>
              <Grid item xs={6}>
                <FormSelect control={control} name="isUse" label="사용 여부" options={USE_OPTIONS} />
              </Grid>
              <Grid item xs={6}>
                <FormSelect control={control} name="attachFileYn" label="첨부파일 여부" options={YN_OPTIONS} />
              </Grid>
              <Grid item xs={6}>
                <FormSelect control={control} name="replyYn" label="답글 여부" options={YN_OPTIONS} />
              </Grid>
              <Grid item xs={6}>
                <FormSelect control={control} name="commentYn" label="댓글 여부" options={YN_OPTIONS} />
              </Grid>
            </Grid>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog} color="secondary">
            취소
          </Button>
          <Button
            onClick={handleSubmit(onSubmit)}
            color="info"
            variant="contained"
            disabled={isSubmitting}
          >
            {dialogMode === "add" ? "등록" : "수정"}
          </Button>
        </DialogActions>
      </Dialog>
      <Footer />
    </DashboardLayout>
  );
}

export default BoardManagement;
