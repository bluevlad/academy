import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Collapse from "@mui/material/Collapse";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import Switch from "@mui/material/Switch";
import FormControlLabel from "@mui/material/FormControlLabel";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import Tooltip from "@mui/material/Tooltip";
import Chip from "@mui/material/Chip";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// API
import {
  fetchMenuTree,
  fetchPassMenuTree,
  fetchDetailMenu,
  fetchPassDetailMenu,
  updateMenu,
  updatePassMenu,
  deleteMenu,
  deletePassMenu,
  checkMenuId,
  getMaxMenuId,
  getPassMaxMenuId,
  insertMenu,
  insertPassMenu,
} from "api/menu";

function Menu() {
  const [menuTree, setMenuTree] = useState([]);
  const [loading, setLoading] = useState(true);
  const [viewMode, setViewMode] = useState("tree"); // tree or table
  const [menuType, setMenuType] = useState("normal"); // normal or pass
  const [openNodes, setOpenNodes] = useState({});

  // Dialog state
  const [dialogOpen, setDialogOpen] = useState(false);
  const [dialogMode, setDialogMode] = useState("add"); // add or edit
  const [currentMenu, setCurrentMenu] = useState(null);

  // Snackbar state
  const [snackbar, setSnackbar] = useState({
    open: false,
    message: "",
    severity: "success",
  });

  // Form state
  const [formData, setFormData] = useState({
    menuId: "",
    menuTitle: "",
    menuNm: "",
    menuUrl: "",
    menuIcon: "",
    menuDepth: 0,
    menuUpperId: "",
    menuNo: "",
    isUse: "Y",
  });

  useEffect(() => {
    loadMenuData();
  }, [menuType]);

  const loadMenuData = async () => {
    setLoading(true);
    try {
      const data = menuType === "normal" ? await fetchMenuTree() : await fetchPassMenuTree();
      const menuData = data.menuTree || [];
      setMenuTree(menuData);

      // 초기 로드 시 최상위 메뉴들을 자동으로 열기
      const initialOpenState = {};
      menuData.forEach((node) => {
        if (node.children && node.children.length > 0) {
          const nodeId = String(node.menuNo || node.menuId || Math.random());
          initialOpenState[nodeId] = true;
        }
      });
      setOpenNodes(initialOpenState);
    } catch (error) {
      console.error("Failed to load menu data:", error);
      showSnackbar("메뉴 데이터 로드 실패", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleToggle = (nodeId) => {
    setOpenNodes((prev) => ({
      ...prev,
      [nodeId]: !prev[nodeId],
    }));
  };

  const showSnackbar = (message, severity = "success") => {
    setSnackbar({ open: true, message, severity });
  };

  const handleCloseSnackbar = () => {
    setSnackbar({ ...snackbar, open: false });
  };

  const handleOpenDialog = (mode, menu = null) => {
    setDialogMode(mode);
    setCurrentMenu(menu);

    if (mode === "add") {
      setFormData({
        menuId: "",
        menuTitle: "",
        menuNm: "",
        menuUrl: "",
        menuIcon: "",
        menuDepth: menu ? (menu.menuDepth || 0) + 1 : 0,
        menuUpperId: menu ? menu.menuId : "",
        menuNo: "",
        isUse: "Y",
      });
    } else if (mode === "edit" && menu) {
      setFormData({
        menuId: menu.menuId || "",
        menuTitle: menu.menuTitle || menu.menuNm || "",
        menuNm: menu.menuNm || menu.menuTitle || "",
        menuUrl: menu.menuUrl || "",
        menuIcon: menu.menuIcon || "",
        menuDepth: menu.menuDepth || 0,
        menuUpperId: menu.menuUpperId || "",
        menuNo: menu.menuNo || "",
        isUse: menu.isUse || "Y",
      });
    }

    setDialogOpen(true);
  };

  const handleCloseDialog = () => {
    setDialogOpen(false);
    setCurrentMenu(null);
    setFormData({
      menuId: "",
      menuTitle: "",
      menuNm: "",
      menuUrl: "",
      menuIcon: "",
      menuDepth: 0,
      menuUpperId: "",
      menuNo: "",
      isUse: "Y",
    });
  };

  const handleFormChange = (field, value) => {
    setFormData((prev) => ({
      ...prev,
      [field]: value,
    }));
  };

  const handleSubmit = async () => {
    try {
      if (dialogMode === "add") {
        // 메뉴 ID 중복 체크
        if (formData.menuId) {
          const checkResult = await checkMenuId(formData.menuId);
          if (checkResult && checkResult.exists) {
            showSnackbar("이미 존재하는 메뉴 ID입니다.", "error");
            return;
          }
        }

        // 메뉴 등록
        if (menuType === "normal") {
          await insertMenu(formData);
        } else {
          await insertPassMenu(formData);
        }
        showSnackbar("메뉴가 성공적으로 등록되었습니다.", "success");
      } else {
        // 메뉴 수정
        if (menuType === "normal") {
          await updateMenu(formData);
        } else {
          await updatePassMenu(formData);
        }
        showSnackbar("메뉴가 성공적으로 수정되었습니다.", "success");
      }

      handleCloseDialog();
      loadMenuData();
    } catch (error) {
      console.error("Error submitting menu:", error);
      showSnackbar(`메뉴 ${dialogMode === "add" ? "등록" : "수정"} 실패`, "error");
    }
  };

  const handleDelete = async (menu) => {
    if (!window.confirm(`"${menu.menuTitle || menu.menuNm}" 메뉴를 삭제하시겠습니까?`)) {
      return;
    }

    try {
      if (menuType === "normal") {
        await deleteMenu(menu.menuId);
      } else {
        await deletePassMenu(menu.menuId);
      }
      showSnackbar("메뉴가 성공적으로 삭제되었습니다.", "success");
      loadMenuData();
    } catch (error) {
      console.error("Error deleting menu:", error);
      showSnackbar("메뉴 삭제 실패", "error");
    }
  };

  const handleAddChild = (parentMenu) => {
    handleOpenDialog("add", parentMenu);
  };

  // Recursive function to render tree items
  const renderTreeItems = (nodes, level = 0) => {
    if (!Array.isArray(nodes)) return null;

    return nodes.map((node, index) => {
      const nodeId = String(node.menuNo || node.menuId || index);
      const hasChildren = node.children && Array.isArray(node.children) && node.children.length > 0;
      const isOpen = openNodes[nodeId];
      const menuTitle = node.menuTitle || node.menuNm || node.name || "이름 없음";
      const menuUrl = node.menuUrl || "";
      const menuIcon = node.menuIcon || "";
      const isUse = node.isUse === "Y" || node.isUse === true;

      return (
        <div key={nodeId}>
          <ListItem
            disablePadding
            sx={{
              pl: level * 3,
              bgcolor: !isUse ? "action.disabledBackground" : "transparent",
            }}
            secondaryAction={
              <Box display="flex" gap={0.5}>
                <Tooltip title="하위 메뉴 추가">
                  <Button
                    variant="text"
                    color="info"
                    size="small"
                    iconOnly
                    onClick={() => handleAddChild(node)}
                  >
                    <Icon fontSize="small">add_circle</Icon>
                  </Button>
                </Tooltip>
                <Tooltip title="수정">
                  <Button
                    variant="text"
                    color="dark"
                    size="small"
                    iconOnly
                    onClick={() => handleOpenDialog("edit", node)}
                  >
                    <Icon fontSize="small">edit</Icon>
                  </Button>
                </Tooltip>
                <Tooltip title="삭제">
                  <Button
                    variant="text"
                    color="error"
                    size="small"
                    iconOnly
                    onClick={() => handleDelete(node)}
                  >
                    <Icon fontSize="small">delete</Icon>
                  </Button>
                </Tooltip>
              </Box>
            }
          >
            <ListItemButton onClick={() => hasChildren && handleToggle(nodeId)}>
              <ListItemIcon>
                {menuIcon ? (
                  <Icon fontSize="small">{menuIcon}</Icon>
                ) : (
                  <Icon fontSize="small">{hasChildren ? "folder" : "description"}</Icon>
                )}
              </ListItemIcon>
              <ListItemText
                primary={
                  <Box display="flex" alignItems="center" gap={1}>
                    <Typography variant="button" fontWeight="medium">
                      {menuTitle}
                    </Typography>
                    {menuUrl && (
                      <Typography variant="caption" color="text">
                        ({menuUrl})
                      </Typography>
                    )}
                    {!isUse && (
                      <Chip label="미사용" size="small" color="error" variant="outlined" />
                    )}
                  </Box>
                }
                secondary={
                  <Typography variant="caption" color="text">
                    ID: {node.menuId || nodeId} | Depth: {node.menuDepth || level}
                    {node.menuUpperId && ` | 상위: ${node.menuUpperId}`}
                  </Typography>
                }
              />
              {hasChildren && (isOpen ? <ExpandLess /> : <ExpandMore />)}
            </ListItemButton>
          </ListItem>
          {hasChildren && (
            <Collapse in={isOpen} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>
                {renderTreeItems(node.children, level + 1)}
              </List>
            </Collapse>
          )}
        </div>
      );
    });
  };

  // Flatten menu tree for table view
  const flattenMenuTree = (nodes, level = 0) => {
    let result = [];
    if (!Array.isArray(nodes)) return result;

    nodes.forEach((node) => {
      result.push({ ...node, level });
      if (node.children && node.children.length > 0) {
        result = result.concat(flattenMenuTree(node.children, level + 1));
      }
    });
    return result;
  };

  const flatMenuList = flattenMenuTree(menuTree);

  // Table columns
  const columns = [
    { Header: "메뉴번호", accessor: "menuNo", width: "8%", align: "center" },
    { Header: "메뉴ID", accessor: "menuId", width: "12%", align: "left" },
    { Header: "메뉴명", accessor: "menuTitle", width: "20%", align: "left" },
    { Header: "메뉴 URL", accessor: "menuUrl", width: "15%", align: "left" },
    { Header: "아이콘", accessor: "menuIcon", width: "10%", align: "center" },
    { Header: "깊이", accessor: "menuDepth", width: "8%", align: "center" },
    { Header: "상위메뉴", accessor: "menuUpperId", width: "10%", align: "center" },
    { Header: "사용여부", accessor: "isUse", width: "8%", align: "center" },
    { Header: "액션", accessor: "action", width: "12%", align: "center" },
  ];

  const rows = flatMenuList.map((menu, index) => ({
    menuNo: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {menu.menuNo || "-"}
      </Typography>
    ),
    menuId: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {menu.menuId || "-"}
      </Typography>
    ),
    menuTitle: (
      <Box display="flex" alignItems="center">
        <Box ml={menu.level * 2}>
          <Typography variant="caption" color="text" fontWeight="medium">
            {"　".repeat(menu.level)}
            {menu.level > 0 && "└─ "}
            {menu.menuTitle || menu.menuNm || menu.name || "-"}
          </Typography>
        </Box>
      </Box>
    ),
    menuUrl: (
      <Typography variant="caption" color="text" fontWeight="regular">
        {menu.menuUrl || "-"}
      </Typography>
    ),
    menuIcon: (
      <Box display="flex" justifyContent="center">
        {menu.menuIcon ? (
          <Icon fontSize="small">{menu.menuIcon}</Icon>
        ) : (
          <Typography variant="caption" color="text">
            -
          </Typography>
        )}
      </Box>
    ),
    menuDepth: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {menu.menuDepth !== undefined ? menu.menuDepth : menu.level}
      </Typography>
    ),
    menuUpperId: (
      <Typography variant="caption" color="text" fontWeight="medium">
        {menu.menuUpperId || "-"}
      </Typography>
    ),
    isUse: (
      <Typography
        variant="caption"
        color={menu.isUse === "Y" ? "success" : "error"}
        fontWeight="medium"
      >
        {menu.isUse === "Y" ? "사용" : "미사용"}
      </Typography>
    ),
    action: (
      <Box display="flex" justifyContent="center" gap={0.5}>
        <Tooltip title="하위 메뉴 추가">
          <Button
            variant="text"
            color="info"
            size="small"
            iconOnly
            onClick={() => handleAddChild(menu)}
          >
            <Icon fontSize="small">add_circle</Icon>
          </Button>
        </Tooltip>
        <Tooltip title="수정">
          <Button
            variant="text"
            color="dark"
            size="small"
            iconOnly
            onClick={() => handleOpenDialog("edit", menu)}
          >
            <Icon fontSize="small">edit</Icon>
          </Button>
        </Tooltip>
        <Tooltip title="삭제">
          <Button
            variant="text"
            color="error"
            size="small"
            iconOnly
            onClick={() => handleDelete(menu)}
          >
            <Icon fontSize="small">delete</Icon>
          </Button>
        </Tooltip>
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
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <Box display="flex" justifyContent="space-between" alignItems="center">
                  <Typography variant="h6" color="white">
                    메뉴 관리 {menuType === "pass" && "(Pass)"}
                  </Typography>
                  <Box display="flex" gap={1}>
                    <Button
                      variant="contained"
                      color="white"
                      size="small"
                      onClick={() => handleOpenDialog("add")}
                    >
                      <Icon>add</Icon>
                      &nbsp;메뉴 추가
                    </Button>
                    <Button
                      variant={menuType === "normal" ? "contained" : "outlined"}
                      color="white"
                      size="small"
                      onClick={() => setMenuType("normal")}
                    >
                      일반 메뉴
                    </Button>
                    <Button
                      variant={menuType === "pass" ? "contained" : "outlined"}
                      color="white"
                      size="small"
                      onClick={() => setMenuType("pass")}
                    >
                      Pass 메뉴
                    </Button>
                    <Button
                      variant={viewMode === "tree" ? "contained" : "outlined"}
                      color="white"
                      size="small"
                      onClick={() => setViewMode("tree")}
                    >
                      <Icon>account_tree</Icon>
                      &nbsp;트리뷰
                    </Button>
                    <Button
                      variant={viewMode === "table" ? "contained" : "outlined"}
                      color="white"
                      size="small"
                      onClick={() => setViewMode("table")}
                    >
                      <Icon>table_view</Icon>
                      &nbsp;테이블뷰
                    </Button>
                  </Box>
                </Box>
              </Box>
              <Box pt={3} pb={3} px={3}>
                {loading ? (
                  <Box p={3} textAlign="center">
                    <Typography variant="caption">로딩 중...</Typography>
                  </Box>
                ) : (
                  <>
                    {viewMode === "tree" ? (
                      <Box>
                        {menuTree.length > 0 ? (
                          <List
                            sx={{ width: "100%", bgcolor: "background.paper" }}
                            component="nav"
                            aria-labelledby="menu-tree-list"
                          >
                            {renderTreeItems(menuTree)}
                          </List>
                        ) : (
                          <Box p={3} textAlign="center">
                            <Typography variant="caption" color="text">
                              메뉴 데이터가 없습니다.
                            </Typography>
                          </Box>
                        )}
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
                  </>
                )}
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>

      {/* Add/Edit Dialog */}
      <Dialog open={dialogOpen} onClose={handleCloseDialog} maxWidth="md" fullWidth>
        <DialogTitle>
          {dialogMode === "add" ? "메뉴 추가" : "메뉴 수정"}
          {currentMenu && dialogMode === "add" && (
            <Typography variant="caption" color="text" display="block">
              상위 메뉴: {currentMenu.menuTitle || currentMenu.menuNm}
            </Typography>
          )}
        </DialogTitle>
        <DialogContent>
          <Box component="form" pt={2}>
            <Grid container spacing={2}>
              <Grid item xs={12} md={6}>
                <TextField
                  fullWidth
                  label="메뉴 ID"
                  value={formData.menuId}
                  onChange={(e) => handleFormChange("menuId", e.target.value)}
                  disabled={dialogMode === "edit"}
                  required
                  margin="normal"
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <TextField
                  fullWidth
                  label="메뉴 번호"
                  value={formData.menuNo}
                  onChange={(e) => handleFormChange("menuNo", e.target.value)}
                  margin="normal"
                  type="number"
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <TextField
                  fullWidth
                  label="메뉴명 (Title)"
                  value={formData.menuTitle}
                  onChange={(e) => handleFormChange("menuTitle", e.target.value)}
                  required
                  margin="normal"
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <TextField
                  fullWidth
                  label="메뉴명 (Name)"
                  value={formData.menuNm}
                  onChange={(e) => handleFormChange("menuNm", e.target.value)}
                  margin="normal"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  label="메뉴 URL"
                  value={formData.menuUrl}
                  onChange={(e) => handleFormChange("menuUrl", e.target.value)}
                  margin="normal"
                  placeholder="/example/path"
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <TextField
                  fullWidth
                  label="메뉴 아이콘"
                  value={formData.menuIcon}
                  onChange={(e) => handleFormChange("menuIcon", e.target.value)}
                  margin="normal"
                  placeholder="dashboard, settings, person"
                  helperText="Material Icons 이름을 입력하세요"
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <FormControl fullWidth margin="normal">
                  <InputLabel>상위 메뉴 ID</InputLabel>
                  <Select
                    value={formData.menuUpperId}
                    onChange={(e) => handleFormChange("menuUpperId", e.target.value)}
                    label="상위 메뉴 ID"
                  >
                    <MenuItem value="">
                      <em>없음 (최상위)</em>
                    </MenuItem>
                    {flatMenuList.map((menu) => (
                      <MenuItem
                        key={menu.menuId}
                        value={menu.menuId}
                        disabled={menu.menuId === formData.menuId}
                      >
                        {"　".repeat(menu.level)}
                        {menu.level > 0 && "└─ "}
                        {menu.menuTitle || menu.menuNm} ({menu.menuId})
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>
              <Grid item xs={12} md={6}>
                <TextField
                  fullWidth
                  label="메뉴 깊이"
                  value={formData.menuDepth}
                  onChange={(e) => handleFormChange("menuDepth", parseInt(e.target.value) || 0)}
                  margin="normal"
                  type="number"
                />
              </Grid>
              <Grid item xs={12} md={6}>
                <FormControl fullWidth margin="normal">
                  <FormControlLabel
                    control={
                      <Switch
                        checked={formData.isUse === "Y"}
                        onChange={(e) => handleFormChange("isUse", e.target.checked ? "Y" : "N")}
                      />
                    }
                    label="사용 여부"
                  />
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
            {dialogMode === "add" ? "추가" : "수정"}
          </Button>
        </DialogActions>
      </Dialog>

      {/* Snackbar for notifications */}
      <Snackbar
        open={snackbar.open}
        autoHideDuration={3000}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: "top", horizontal: "center" }}
      >
        <Alert onClose={handleCloseSnackbar} severity={snackbar.severity} sx={{ width: "100%" }}>
          {snackbar.message}
        </Alert>
      </Snackbar>

      <Footer />
    </DashboardLayout>
  );
}

export default Menu;
