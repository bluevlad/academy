/* eslint-disable prettier/prettier */
/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


export default function data(orderList = [], onOpenPayDetail) {
  return {
    columns: [
      { Header: "No", accessor: "no", width: "5%", align: "left" },
      { Header: "주문번호", accessor: "orderNo", align: "center" },
      { Header: "회원명", accessor: "userName", align: "center" },
      { Header: "연락처", accessor: "phone", align: "center" },
      { Header: "상품명", accessor: "productName", align: "left", width: "30%" },
      { Header: "결제금액", accessor: "price", align: "right" },
      { Header: "결제상태", accessor: "status", align: "center" },
      { Header: "주문일", accessor: "regDate", align: "center" },
    ],

    rows: orderList.map((item, index) => ({
      no: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {index + 1}
        </Typography>
      ),
      orderNo: (
        <Typography
          variant="caption"
          color="info"
          fontWeight="medium"
          style={{ cursor: 'pointer' }}
          onClick={() => onOpenPayDetail && onOpenPayDetail(item)}
        >
          {item.ORDERNO}
        </Typography>
      ),
      userName: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.USER_NM} ({item.USER_ID})
        </Typography>
      ),
      phone: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.PHONE_NO}
        </Typography>
      ),
      productName: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.ORDER_NM}
        </Typography>
      ),
      price: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {Number(item.PAY_PRICE).toLocaleString()}
        </Typography>
      ),
      status: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.STATUS_NM}
        </Typography>
      ),
      regDate: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.REG_DT}
        </Typography>
      ),
    })),
  };
}
