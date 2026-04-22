/* eslint-disable prettier/prettier */
/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import { Link } from "react-router-dom";

export default function data(boardList = []) {
  return {
    columns: [
      { Header: "No", accessor: "no", width: "5%", align: "left" },
      { Header: "지역", accessor: "area", align: "center" },
      { Header: "병원", accessor: "hspt", align: "center" },
      { Header: "제목", accessor: "subject", align: "left", width: "40%" },
      { Header: "작성자", accessor: "writer", align: "center" },
      { Header: "첨부", accessor: "file", align: "center" },
      { Header: "조회수", accessor: "hits", align: "center" },
      { Header: "등록일", accessor: "regDate", align: "center" },
    ],

    rows: boardList.map((item, index) => ({
      no: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.NOTICE_YN === 'Y' ? '공지' : index + 1}
        </MDTypography>
      ),
      area: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.COOP_AREA_NM}
        </MDTypography>
      ),
      hspt: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.COOP_HSPT_NM}
        </MDTypography>
      ),
      subject: (
        <MDTypography
          component={Link}
          to={`/coop/board/detail?boardSeq=${item.BOARD_SEQ}&boardMngSeq=${item.BOARD_MNG_SEQ}`}
          variant="button"
          color="info"
          fontWeight="medium"
        >
          {item.SUBJECT}
        </MDTypography>
      ),
      writer: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.CREATENAME}
        </MDTypography>
      ),
      file: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.FILE_YN === 'Y' ? 'O' : ''}
        </MDTypography>
      ),
      hits: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.HITS}
        </MDTypography>
      ),
      regDate: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.REG_DT}
        </MDTypography>
      ),
    })),
  };
}
