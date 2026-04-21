/* eslint-disable prettier/prettier */
/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */

import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";

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
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.COOP_CD}
        </MDTypography>
      ),
      coopNm: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.COOP_NM}
        </MDTypography>
      ),
      discount: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.DISCOUNT_PER}
        </MDTypography>
      ),
      desc: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.COOP_DESC}
        </MDTypography>
      ),
      ipCount: (
        <MDButton variant="text" color="info" size="small" onClick={() => onManageIp(item)}>
          {item.IP_CNT || 0}
        </MDButton>
      ),
      regDate: (
        <MDTypography variant="caption" color="text" fontWeight="medium">
          {item.REG_DT}
        </MDTypography>
      ),
      action: (
        <MDBox display="flex" justifyContent="center">
          <MDButton variant="text" color="info" size="small" onClick={() => onEdit(item)}>
            수정
          </MDButton>
          <MDButton variant="text" color="error" size="small" onClick={() => onDelete(item)}>
            삭제
          </MDButton>
        </MDBox>
      ),
    })),
  };
}
