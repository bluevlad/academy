/* eslint-disable prettier/prettier */

/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Badge from "@mui/material/Badge";


// Router
import { Link } from "react-router-dom";

export default function data(bookList = []) {

  const getCoverTypeLabel = (type) => {
    switch (type) {
      case 'A': return '주문가능';
      case 'S': return '품절';
      case 'O': return '절판';
      case 'E': return '이벤트';
      case 'N': return '신규';
      default: return type;
    }
  };

  const getBadgeColor = (type) => {
    switch (type) {
      case 'A': return 'success';
      case 'S': return 'error';
      case 'O': return 'secondary';
      case 'E': return 'info';
      case 'N': return 'warning';
      default: return 'light';
    }
  };

  return {
    columns: [
      { Header: "No", accessor: "no", width: "5%", align: "left" },
      { Header: "관리번호", accessor: "rscId", align: "left" },
      { Header: "분류", accessor: "categoryNm", align: "left" },
      { Header: "상품명", accessor: "bookNm", align: "left", width: "30%" },
      { Header: "저자", accessor: "author", align: "left" },
      { Header: "출판사", accessor: "publisher", align: "left" },
      { Header: "재고", accessor: "stock", align: "center" },
      { Header: "상태", accessor: "status", align: "center" },
      { Header: "action", accessor: "action", align: "center" },
    ],

    rows: bookList.map((item, index) => ({
      no: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {index + 1}
        </Typography>
      ),
      rscId: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.RSC_ID}
        </Typography>
      ),
      categoryNm: (
        <Box display="flex" flexDirection="column">
          <Typography variant="caption" color="text" fontWeight="medium">
            {item.CATEGORY_NM}
          </Typography>
          <Typography variant="caption" color="text" fontWeight="light">
            {item.LEARNING_NM}
          </Typography>
        </Box>
      ),
      bookNm: (
        <Typography variant="button" color="text" fontWeight="bold">
          {item.BOOK_NM}
        </Typography>
      ),
      author: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.BOOK_AUTHOR}
        </Typography>
      ),
      publisher: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.BOOK_PUBLISHERS}
        </Typography>
      ),
      stock: (
        <Typography variant="caption" color="text" fontWeight="medium">
          {item.BOOK_STOCK}
        </Typography>
      ),
      status: (
        <Box ml={-1}>
          <Badge badgeContent={getCoverTypeLabel(item.COVER_TYPE)} color={getBadgeColor(item.COVER_TYPE)} variant="contained" size="sm" />
        </Box>
      ),
      action: (
        <Typography component={Link} to={`/book/write?seq=${item.SEQ}&rscId=${item.RSC_ID}`} variant="caption" color="info" fontWeight="medium">
          Edit
        </Typography>
      ),
    })),
  };
}
