/* eslint-disable prettier/prettier */
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// API
import { fetchScheduleList, fetchCounselReqList } from "api/counsel";

function CounselDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const date = queryParams.get("date");
  const catCd = queryParams.get("catCd");

  const [scheduleList, setScheduleList] = useState([]); // Times
  const [reservations, setReservations] = useState([]); // Users

  useEffect(() => {
    const load = async () => {
      if (date && catCd) {
        try {
          // Fetch Basic Schedule (Time slots)
          const scheduleResult = await fetchScheduleList({ SCH_DAY: date, CAT_CD: catCd });
          if (scheduleResult && scheduleResult.scheduleList) {
            setScheduleList(scheduleResult.scheduleList);
          }

          // Fetch Reservations (Who booked what)
          const reqResult = await fetchCounselReqList({ SCH_DAY: date, CAT_CD: catCd });
          if (reqResult && reqResult.counselReqList) {
            setReservations(reqResult.counselReqList);
          }
        } catch (e) {
          console.error(e);
        }
      }
    };
    load();
  }, [date, catCd]);

  // Helper to get users for a specific TS_IDX
  const getUsersForTime = (tsIdx) => {
    return reservations.filter(r => r.TS_IDX === tsIdx);
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
              >
                <Typography variant="h6" color="white">
                  상담 상세 ({date})
                </Typography>
              </Box>
              <Box p={3}>
                <table style={{ width: "100%", borderCollapse: "collapse" }}>
                  <thead>
                    <tr style={{ borderBottom: "2px solid #ddd" }}>
                      <th style={{ padding: "10px", textAlign: "left", width: "150px" }}>시간</th>
                      <th style={{ padding: "10px", textAlign: "left" }}>예약현황</th>
                    </tr>
                  </thead>
                  <tbody>
                    {scheduleList.map((time, idx) => (
                      <tr key={idx} style={{ borderBottom: "1px solid #eee" }}>
                        <td style={{ padding: "10px" }}>{time.TIME_SET}</td>
                        <td style={{ padding: "10px" }}>
                          {getUsersForTime(time.TS_IDX).map((r, rIdx) => (
                            <Box key={rIdx} component="span" mr={1} display="inline-block">
                              <Typography
                                variant="button"
                                color="info"
                                fontWeight="medium"
                                onClick={() => {
                                  alert(`User: ${r.USER_NM} (${r.USER_ID})`);
                                }}
                                style={{ cursor: 'pointer' }}
                              >
                                [{r.USER_NM}({r.USER_ID})]
                              </Typography>
                            </Box>
                          ))}
                        </td>
                      </tr>
                    ))}
                    {scheduleList.length === 0 && (
                      <tr>
                        <td colSpan={2} style={{ padding: "20px", textAlign: "center" }}>
                          예정된 상담이 없습니다.
                        </td>
                      </tr>
                    )}
                  </tbody>
                </table>
                <Box mt={2} display="flex" justifyContent="center">
                  <Button variant="outlined" color="dark" onClick={() => navigate("/counsel")}>
                    목록
                  </Button>
                </Box>
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default CounselDetail;
