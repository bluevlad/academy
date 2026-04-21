/* eslint-disable prettier/prettier */
/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


export default function data(coopList = [], onEdit, onDelete, onManageIp) {
  return {
    columns: [
      { Header: "제휴사ID", accessor: "coopId", align: "left" },
      { Header: "제휴사명", accessor: "coopNm", align: "left" },
      { Header: "할인율(%)", accessor: "discount", align: "center" },
      { Header: "설명", accessor: "desc", align: "left" },
      { Header: "등록아이피", accessor: "ipCount", align: "center" },
      { Header: "등록일", accessor: "regDate", align: "center" },
      { Header: "관리", accessor: "action", align: "center" },
    ],

    rows: coopList.map((item) => ({
      coopId: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.COOP_CD}
        </Typography>
      ),
      coopNm: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.COOP_NM}
        </Typography>
      ),
      discount: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.DISCOUNT_PER}
        </Typography>
      ),
      desc: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.COOP_DESC}
        </Typography>
      ),
      ipCount: (
        <Button variant="text" color="info" size="small" onClick={() => onManageIp(item)}>
          {item.IP_CNT || 0}
        </Button>
      ),
      regDate: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.REG_DT}
        </Typography>
      ),
      action: (
        <Box display="flex" justifyContent="center">
          <Button variant="text" color="info" size="small" onClick={() => onEdit(item)}>
            수정
          </Button>
          <Button variant="text" color="error" size="small" onClick={() => onDelete(item)}>
            삭제
          </Button>
        </Box>
      ),
    })),
  };
}
