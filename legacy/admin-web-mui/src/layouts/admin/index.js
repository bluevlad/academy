/* eslint-disable prettier/prettier */
import { useState } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import AppBar from "@mui/material/AppBar";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// Admin Sub-Layouts
import AdminAuth from "layouts/admin/auth";
import AdminCode from "layouts/admin/code";
import AdminBanner from "layouts/admin/banner";
import Menu from "layouts/menu"; // Re-use existing Menu layout

function Admin() {
  const [tabValue, setTabValue] = useState(0);

  const handleSetTabValue = (event, newValue) => setTabValue(newValue);

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <Box py={3}>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Box mb={3}>
              <AppBar position="static">
                <Tabs orientation="horizontal" value={tabValue} onChange={handleSetTabValue}>
                  <Tab
                    label="권한 관리"
                    icon={
                      <Icon fontSize="medium" sx={{ mt: -0.25 }}>
                        manage_accounts
                      </Icon>
                    }
                  />
                  <Tab
                    label="코드 관리"
                    icon={
                      <Icon fontSize="medium" sx={{ mt: -0.25 }}>
                        code
                      </Icon>
                    }
                  />
                  <Tab
                    label="메뉴 관리"
                    icon={
                      <Icon fontSize="medium" sx={{ mt: -0.25 }}>
                        menu
                      </Icon>
                    }
                  />
                  <Tab
                    label="배너 관리"
                    icon={
                      <Icon fontSize="medium" sx={{ mt: -0.25 }}>
                        image
                      </Icon>
                    }
                  />
                </Tabs>
              </AppBar>
            </Box>

            <Box>
              {tabValue === 0 && <AdminAuth />}
              {tabValue === 1 && <AdminCode />}
              {tabValue === 2 && <Menu />}
              {tabValue === 3 && <AdminBanner />}
            </Box>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

export default Admin;
