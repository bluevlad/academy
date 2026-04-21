/* eslint-disable prettier/prettier */
/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import { Link } from "react-router-dom";

export default function data(ddayList = []) {
  return {
    columns: [
      { Header: "직종", accessor: "category", align: "center" },
      { Header: "D-day 설명", accessor: "name", align: "left", width: "40%" },
      { Header: "D-day 날짜", accessor: "date", align: "center" },
    ],

    rows: ddayList.map((item) => ({
      category: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.DDAY_CATEGORY_NM || item.DDAY_CATEGORY}
        </MDTypography>
      ),
      name: (
        <MDTypography
          component={Link}
          to={`/dday/detail?ddayIdx=${item.DDAY_IDX}`}
          variant="button"
          color="info"
          fontWeight="medium"
        >
          {item.DDAY_NAME}
        </MDTypography>
      ),
      date: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.DDAY_DATE}
        </MDTypography>
      ),
    })),
  };
}
