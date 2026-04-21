/* eslint-disable prettier/prettier */
/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";

import Checkbox from "@mui/material/Checkbox";

export default function data(scheduleList = [], onView, onEdit, onSelect) {
  return {
    columns: [
      { Header: "선택", accessor: "select", width: "5%", align: "center" },
      { Header: "No", accessor: "no", width: "5%", align: "left" },
      { Header: "직종", accessor: "category", align: "center" },
      { Header: "일자(요일)", accessor: "date", align: "center" },
      { Header: "신청현황", accessor: "status", align: "center" },
      { Header: "수정", accessor: "edit", align: "center" },
    ],

    rows: scheduleList.map((item, index) => ({
      select: (
        <Checkbox
          onChange={(e) => onSelect(e, item)}
          color="primary"
        />
      ),
      no: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {index + 1}
        </Typography>
      ),
      category: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.CAT_NM}
        </Typography>
      ),
      date: (
        <Typography
          variant="caption"
          color="info"
          fontWeight="medium"
          style={{ cursor: "pointer" }}
          onClick={() => onView(item)}
        >
          {item.SCH_DAY} ({item.WEEK})
        </Typography>
      ),
      status: (
        <Typography
          variant="caption"
          color={(item.MAX_USR - item.REQ_CNT) <= 0 ? "error" : "text"}
          fontWeight="medium"
          style={{ cursor: "pointer" }}
          onClick={() => onView(item)}
        >
          {item.REQ_CNT} / {item.MAX_USR}
        </Typography>
      ),
      edit: (
        <Button variant="text" color="dark" size="small" onClick={() => onEdit(item)}>
          수정
        </Button>
      ),
    })),
  };
}
