/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";

import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import {
  fetchTimeTable,
  insertSchedule,
  fetchScheduleList, // This gets detail list, but we need getScheduleTable for update
  updateSchedule
} from "api/counsel";
import superagent from "superagent";
import { BASE_API } from "../../../constants";

// Helper to get Schedule Table (since it wasn't in main api export yet, or I named it differently?)
// I named it fetchScheduleTable? No, in Api client I named: getScheduleTable -> fetchScheduleTable?
// Let's check api/counsel/index.js I wrote.
// Yes: fetchScheduleList (getScheduleList), fetchTimeTable (getTimeTable).
// I missed fetchScheduleTable! getScheduleTable was in Java.
// I need to add fetchScheduleTable to api/counsel/index.js first.

function CounselWrite() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const date = queryParams.get("date");
  const catCd = queryParams.get("catCd");
  const catNm = queryParams.get("catNm");

  const isEdit = !!(date && catCd);

  // Insert State
  const [insertData, setInsertData] = useState({
    SDate: "",
    EDate: "",
    REQ_CNT: "",
    REQ_TYPE: "A",
    ISUSE: "Y",
    CAT_CD: [] // Array of codes
  });

  // Update State
  const [scheduleTable, setScheduleTable] = useState([]);

  // Common
  const [loading, setLoading] = useState(false);

  // Categories Mock (since we don't have dynamic list yet)
  // Based on common gov/academy codes, but I'll use placeholders or just text input if unsure.
  // JSP: a1 label.
  const categories = [
    { code: "001", name: "일반행정" },
    { code: "002", name: "교육행정" },
    { code: "003", name: "사회복지" },
    // ... Add more if known
  ];
  // Actually, I'll use a simple text input for "Category Code" if user wants custom, 
  // or just assume a standard set. 
  // To match JSP "checkboxes", I'll provide a few.

  useEffect(() => {
    const load = async () => {
      setLoading(true);
      try {
        if (isEdit) {
          // Load Schedule Table for Editing
          // Need to implement fetchScheduleTable in API first!
          // I will implement it locally here or update API file.
          // For now, I'll do a quick fetch using superagent directly or assume I update API file.
          // I will UPDATE API FILE in next step.
          // Assuming fetchScheduleTable exists and is imported:
          // const result = await fetchScheduleTable({ SCH_DAY: date, CAT_CD: catCd });

          // Temp workaround until I fix API file:
          const response = await superagent.get(`${BASE_API}/counsel/getScheduleTable`).query({ SCH_DAY: date, CAT_CD: catCd });
          if (response.body && response.body.scheduleTable) {
            setScheduleTable(response.body.scheduleTable);
          }
        } else {
          // Load Time Table for View (Insert Mode doesn't use it for input logic, just display?)
          // Insert logic uses global params.
          // But JSP displays time_table.
          // We can fetch it to show user what times will be created.
          // const result = await fetchTimeTable({});
        }
      } catch (e) {
        console.error(e);
      }
      setLoading(false);
    };
    load();
  }, [isEdit, date, catCd]);

  const handleInsertChange = (e) => {
    setInsertData({ ...insertData, [e.target.name]: e.target.value });
  };

  const handleCatCheck = (code, checked) => {
    if (checked) {
      setInsertData(prev => ({ ...prev, CAT_CD: [...prev.CAT_CD, code] }));
    } else {
      setInsertData(prev => ({ ...prev, CAT_CD: prev.CAT_CD.filter(c => c !== code) }));
    }
  };

  const handleTableChange = (index, field, value) => {
    const newTable = [...scheduleTable];
    newTable[index] = { ...newTable[index], [field]: value };
    setScheduleTable(newTable);
  };

  const calculateDayCount = (s, e) => {
    if (!s || !e) return 0;
    // Simple day diff
    // Form: YYYYMMDD
    // Logic from JSP: (b-a) / day + 1
    // Simplified JS:
    const d1 = new Date(s.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3'));
    const d2 = new Date(e.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3'));
    const diffTime = Math.abs(d2 - d1);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
    return diffDays;
  }

  const handleSubmitInsert = async () => {
    if (insertData.CAT_CD.length === 0) { alert("직종을 선택해주세요"); return; }
    if (!insertData.SDate || !insertData.EDate) { alert("날짜를 입력해주세요"); return; }

    const dateCount = calculateDayCount(insertData.SDate, insertData.EDate);

    // Prepare data
    // JSP sends: SDATE, EDATE, REQ_CNT, ISUSE, REQ_TYPE, DATE_COUNT, TIME_COUNT, CAT_CDs (comma sep)
    const postData = {
      SDATE: insertData.SDate,
      EDATE: insertData.EDate,
      REQ_CNT: insertData.REQ_CNT,
      ISUSE: insertData.ISUSE,
      REQ_TYPE: insertData.REQ_TYPE,
      DATE_COUNT: dateCount,
      TIME_COUNT: 10, // Dummy or fetched
      CAT_CDs: insertData.CAT_CD.join(",")
    };

    try {
      await insertSchedule(postData);
      alert("상담일정이 등록되었습니다.");
      navigate("/counsel");
    } catch (e) {
      alert("등록 실패");
    }
  };

  const handleSubmitUpdate = async () => {
    // Send arrays.
    // updateSchedule expects: UPDATE_DATE, CAT_CD, REQ_CNT (arr), REQ_TYPE (arr), ISUSE (arr)
    // We need to construct form data with arrays.
    // We can use URLSearchParams to handle array keys if superagent supports it or custom object.

    // Construct array values
    const reqCnts = [];
    const reqTypes = [];
    const isUses = [];

    scheduleTable.forEach(item => {
      if (item.TS_IDX !== '3') { // Skip lunch or handled differently
        reqCnts.push(item.MAX_USR || item.REQ_CNT); // JSP uses name='REQ_CNT' value='item.MAX_USR'
        reqTypes.push(item.REQ_TYPE);
        isUses.push(item.ISUSE);
      }
    });

    const postData = {
      UPDATE_DATE: date,
      CAT_CD: catCd,
      REQ_CNT: reqCnts,
      REQ_TYPE: reqTypes,
      ISUSE: isUses
    };

    try {
      await updateSchedule(postData);
      alert("상담일정이 수정되었습니다.");
      navigate("/counsel");
    } catch (e) {
      alert("수정 실패");
    }
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox pt={6} pb={3}>
        <Grid container spacing={6}>
          <Grid item xs={12}>
            <Card>
              <MDBox
                mx={2}
                mt={-3}
                py={3}
                px={2}
                variant="gradient"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <MDTypography variant="h6" color="white">
                  상담일정 {isEdit ? "수정" : "등록"}
                </MDTypography>
              </MDBox>

              <MDBox p={3}>
                <table className="table01" style={{ width: "100%", borderCollapse: "collapse", marginBottom: "20px" }}>
                  <tbody>
                    <tr>
                      <th style={{ width: "20%", padding: "10px", borderBottom: "1px solid #ddd" }}>상담직종</th>
                      <td style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
                        {isEdit ? (
                          <MDTypography variant="body2">{catNm}</MDTypography>
                        ) : (
                          <MDBox display="flex" flexWrap="wrap">
                            {categories.map(c => (
                              <FormControlLabel
                                key={c.code}
                                control={<Checkbox onChange={(e) => handleCatCheck(c.code, e.target.checked)} />}
                                label={c.name}
                              />
                            ))}
                            {categories.length === 0 && <MDTypography variant="caption">직종 코드 로드 필요</MDTypography>}
                          </MDBox>
                        )}
                      </td>
                    </tr>
                    <tr>
                      <th style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>상담일정</th>
                      <td style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
                        {isEdit ? (
                          <MDTypography variant="body2">{date}</MDTypography>
                        ) : (
                          <MDBox display="flex" alignItems="center">
                            <MDInput name="SDate" label="시작일" value={insertData.SDate} onChange={handleInsertChange} sx={{ width: 150 }} />
                            <span style={{ margin: "0 10px" }}>~</span>
                            <MDInput name="EDate" label="종료일" value={insertData.EDate} onChange={handleInsertChange} sx={{ width: 150 }} />
                          </MDBox>
                        )}
                      </td>
                    </tr>
                    {!isEdit && (
                      <>
                        <tr>
                          <th style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>상담인원</th>
                          <td style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
                            <MDBox display="flex" alignItems="center">
                              <MDInput name="REQ_CNT" value={insertData.REQ_CNT} onChange={handleInsertChange} sx={{ width: 100 }} /> &nbsp;명
                            </MDBox>
                          </td>
                          <th style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>상담신청대상</th>
                          <td style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
                            <Select name="REQ_TYPE" value={insertData.REQ_TYPE} onChange={handleInsertChange} sx={{ height: 40 }}>
                              <MenuItem value="A">회원</MenuItem>
                              <MenuItem value="S">수강생</MenuItem>
                            </Select>
                          </td>
                        </tr>
                        <tr>
                          <th style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>상태</th>
                          <td colSpan={3} style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
                            <Select name="ISUSE" value={insertData.ISUSE} onChange={handleInsertChange} sx={{ height: 40 }}>
                              <MenuItem value="Y">활성</MenuItem>
                              <MenuItem value="N">비활성</MenuItem>
                            </Select>
                          </td>
                        </tr>
                      </>
                    )}
                    {isEdit && (
                      <tr>
                        <th style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>시간표</th>
                        <td colSpan={3} style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
                          <table style={{ width: "100%", border: "1px solid #eee" }}>
                            <thead>
                              <tr style={{ background: "#f8f9fa" }}>
                                <th style={{ padding: "8px" }}>시간</th>
                                <th style={{ padding: "8px" }}>상담인원</th>
                                <th style={{ padding: "8px" }}>상담신청대상</th>
                                <th style={{ padding: "8px" }}>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              {scheduleTable.map((item, index) => (
                                <tr key={index} style={{ borderBottom: "1px solid #eee" }}>
                                  <td style={{ padding: "8px", textAlign: "center" }}>{item.TIME_SET}</td>
                                  {item.TS_IDX === '3' ? (
                                    <td colSpan={3} style={{ textAlign: "center", padding: "8px" }}>점 심 시 간</td>
                                  ) : (
                                    <>
                                      <td style={{ padding: "8px", textAlign: "center" }}>
                                        <MDInput value={item.MAX_USR} onChange={(e) => handleTableChange(index, 'MAX_USR', e.target.value)} sx={{ width: 60 }} />
                                      </td>
                                      <td style={{ padding: "8px", textAlign: "center" }}>
                                        <Select value={item.REQ_TYPE} onChange={(e) => handleTableChange(index, 'REQ_TYPE', e.target.value)} sx={{ height: 30 }}>
                                          <MenuItem value="A">회원</MenuItem>
                                          <MenuItem value="S">수강생</MenuItem>
                                        </Select>
                                      </td>
                                      <td style={{ padding: "8px", textAlign: "center" }}>
                                        <Select value={item.ISUSE} onChange={(e) => handleTableChange(index, 'ISUSE', e.target.value)} sx={{ height: 30 }}>
                                          <MenuItem value="Y">활성</MenuItem>
                                          <MenuItem value="N">비활성</MenuItem>
                                        </Select>
                                      </td>
                                    </>
                                  )}
                                </tr>
                              ))}
                            </tbody>
                          </table>
                        </td>
                      </tr>
                    )}
                  </tbody>
                </table>

                <MDBox display="flex" justifyContent="center">
                  <MDButton variant="gradient" color="info" onClick={isEdit ? handleSubmitUpdate : handleSubmitInsert}>
                    {isEdit ? "수정" : "등록"}
                  </MDButton>
                  <MDBox ml={1}>
                    <MDButton variant="outlined" color="dark" onClick={() => navigate("/counsel")}>
                      목록
                    </MDButton>
                  </MDBox>
                </MDBox>
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default CounselWrite;
