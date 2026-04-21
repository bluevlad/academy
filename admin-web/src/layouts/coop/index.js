/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import TextField from "@mui/material/TextField";
import IconButton from "@mui/material/IconButton";
import Icon from "@mui/material/Icon";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import ListItemSecondaryAction from "@mui/material/ListItemSecondaryAction";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import DataTable from "examples/Tables/DataTable";

// Data
import coopListData from "layouts/coop/data/coopListData";

// API
import {
  fetchCoopList,
  insertCoop,
  updateCoop,
  deleteCoop,
  fetchCoopIpList,
  insertCoopIp,
  deleteCoopIp
} from "api/coop";

function CoopList() {
  const [tableData, setTableData] = useState({ columns: [], rows: [] });
  const [loading, setLoading] = useState(false);
  const [openDialog, setOpenDialog] = useState(false);
  const [openIpDialog, setOpenIpDialog] = useState(false);

  // Coop Form State
  const [currentCoop, setCurrentCoop] = useState(null); // null means new
  const [formData, setFormData] = useState({
    COOP_CD: "",
    COOP_NM: "",
    DISCOUNT_PER: "",
    COOP_DESC: ""
  });

  // IP Management State
  const [currentIpCoop, setCurrentIpCoop] = useState(null);
  const [ipList, setIpList] = useState([]);
  const [newIp, setNewIp] = useState("");

  const loadData = async () => {
    setLoading(true);
    const result = await fetchCoopList({ pageIndex: 1, pageUnit: 100 });
    if (result && result.coopList) {
      setTableData(coopListData(result.coopList, handleEdit, handleDelete, handleManageIp));
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, []);

  // Create / Edit Handlers
  const handleAdd = () => {
    setCurrentCoop(null);
    setFormData({
      COOP_CD: "",
      COOP_NM: "",
      DISCOUNT_PER: "",
      COOP_DESC: ""
    });
    setOpenDialog(true);
  };

  const handleEdit = (item) => {
    setCurrentCoop(item);
    setFormData({
      COOP_CD: item.COOP_CD,
      COOP_NM: item.COOP_NM,
      DISCOUNT_PER: item.DISCOUNT_PER,
      COOP_DESC: item.COOP_DESC
    });
    setOpenDialog(true);
  };

  const handleDelete = async (item) => {
    if (window.confirm(`제휴사 [${item.COOP_NM}]을 삭제하시겠습니까?`)) {
      await deleteCoop({ COOP_CD: item.COOP_CD });
      alert("삭제되었습니다.");
      loadData();
    }
  };

  const handleFormChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
    try {
      if (currentCoop) {
        // Update
        await updateCoop(formData);
        alert("수정되었습니다.");
      } else {
        // Insert
        await insertCoop(formData);
        alert("등록되었습니다.");
      }
      setOpenDialog(false);
      loadData();
    } catch (e) {
      alert("오류가 발생했습니다.");
    }
  };

  // IP Management Handlers
  const handleManageIp = async (item) => {
    setCurrentIpCoop(item);
    setNewIp("");
    const result = await fetchCoopIpList({ COOP_CD: item.COOP_CD });
    if (result && result.coopIpList) {
      setIpList(result.coopIpList);
    } else {
      setIpList([]);
    }
    setOpenIpDialog(true);
  };

  const handleAddIp = async () => {
    if (!newIp) return;
    try {
      await insertCoopIp({ COOP_CD: currentIpCoop.COOP_CD, COOP_IP: newIp });
      const result = await fetchCoopIpList({ COOP_CD: currentIpCoop.COOP_CD });
      if (result && result.coopIpList) setIpList(result.coopIpList);
      setNewIp("");
    } catch (e) {
      alert("IP 등록 실패");
    }
  };

  const handleDeleteIp = async (seq) => {
    if (!window.confirm("삭제하시겠습니까?")) return;
    try {
      await deleteCoopIp({ SEQ: seq });
      const result = await fetchCoopIpList({ COOP_CD: currentIpCoop.COOP_CD });
      if (result && result.coopIpList) setIpList(result.coopIpList);
    } catch (e) {
      alert("IP 삭제 실패");
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
                  제휴사 관리
                </Typography>
                <Button variant="contained" color="dark" onClick={handleAdd}>
                  제휴사 등록
                </Button>
              </Box>
              <Box pt={3}>
                {loading ? (
                  <Typography p={3}>Loading...</Typography>
                ) : (
                  <DataTable
                    table={tableData}
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

      {/* Coop Form Dialog */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="sm" fullWidth>
        <DialogTitle>{currentCoop ? "제휴사 수정" : "제휴사 등록"}</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            name="COOP_CD"
            label="제휴사ID"
            type="text"
            fullWidth
            value={formData.COOP_CD}
            onChange={handleFormChange}
            disabled={!!currentCoop} // ID cannot be changed on edit typically
          />
          <TextField
            margin="dense"
            name="COOP_NM"
            label="제휴사명"
            type="text"
            fullWidth
            value={formData.COOP_NM}
            onChange={handleFormChange}
          />
          <TextField
            margin="dense"
            name="DISCOUNT_PER"
            label="할인율(%)"
            type="number"
            fullWidth
            value={formData.DISCOUNT_PER}
            onChange={handleFormChange}
          />
          <TextField
            margin="dense"
            name="COOP_DESC"
            label="설명"
            type="text"
            fullWidth
            multiline
            rows={3}
            value={formData.COOP_DESC}
            onChange={handleFormChange}
          />
        </DialogContent>
        <DialogActions>
          <Button color="secondary" onClick={() => setOpenDialog(false)}>취소</Button>
          <Button color="info" onClick={handleSave}>{currentCoop ? "수정" : "등록"}</Button>
        </DialogActions>
      </Dialog>

      {/* IP Management Dialog */}
      <Dialog open={openIpDialog} onClose={() => setOpenIpDialog(false)} maxWidth="sm" fullWidth>
        <DialogTitle>IP 관리 - {currentIpCoop?.COOP_NM}</DialogTitle>
        <DialogContent>
          <Box display="flex" mb={2} alignItems="center">
            <TextField
              label="New IP Address"
              value={newIp}
              onChange={(e) => setNewIp(e.target.value)}
              size="small"
              sx={{ flexGrow: 1, mr: 1 }}
            />
            <Button variant="contained" color="info" onClick={handleAddIp}>추가</Button>
          </Box>
          <List>
            {ipList.map((ip) => (
              <ListItem key={ip.SEQ}>
                <ListItemText primary={ip.COOP_IP} secondary={ip.REG_DT} />
                <ListItemSecondaryAction>
                  <IconButton edge="end" aria-label="delete" onClick={() => handleDeleteIp(ip.SEQ)}>
                    <Icon>delete</Icon>
                  </IconButton>
                </ListItemSecondaryAction>
              </ListItem>
            ))}
            {ipList.length === 0 && <Typography variant="caption">등록된 IP가 없습니다.</Typography>}
          </List>
        </DialogContent>
        <DialogActions>
          <Button color="secondary" onClick={() => { setOpenIpDialog(false); loadData(); /* Refresh main list count */ }}>닫기</Button>
        </DialogActions>
      </Dialog>

      <Footer />
    </DashboardLayout>
  );
}

export default CoopList;
