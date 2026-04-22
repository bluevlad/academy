/**
=========================================================
* Material Dashboard 2 React - v2.2.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

// prop-types is a library for typechecking of props.
import PropTypes from "prop-types";
import { useState, useEffect } from "react";

// @mui material components
import Card from "@mui/material/Card";
import Switch from "@mui/material/Switch";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

function PlatformSettings({ user }) {
  const [isokSms, setIsokSms] = useState(user?.isokSms === "Y" || user?.isokSms === true);
  const [isokEmail, setIsokEmail] = useState(user?.isokEmail === "Y" || user?.isokEmail === true);

  useEffect(() => {
    if (user) {
      setIsokSms(user.isokSms === "Y" || user.isokSms === true);
      setIsokEmail(user.isokEmail === "Y" || user.isokEmail === true);
    }
  }, [user]);

  return (
    <Card sx={{ boxShadow: "none" }}>
      <MDBox p={2}>
        <MDTypography variant="h6" fontWeight="medium" textTransform="capitalize">
          platform settings
        </MDTypography>
      </MDBox>
      <MDBox pt={1} pb={2} px={2} lineHeight={1.25}>
        <MDTypography variant="caption" fontWeight="bold" color="text" textTransform="uppercase">
          account
        </MDTypography>
        <MDBox display="flex" alignItems="center" mb={0.5} ml={-1.5}>
          <MDBox mt={0.5}>
            <Switch checked={isokSms} onChange={() => setIsokSms(!isokSms)} />
          </MDBox>
          <MDBox width="80%" ml={0.5}>
            <MDTypography variant="button" fontWeight="regular" color="text">
              Receive SMS notifications
            </MDTypography>
          </MDBox>
        </MDBox>
        <MDBox display="flex" alignItems="center" mb={0.5} ml={-1.5}>
          <MDBox mt={0.5}>
            <Switch checked={isokEmail} onChange={() => setIsokEmail(!isokEmail)} />
          </MDBox>
          <MDBox width="80%" ml={0.5}>
            <MDTypography variant="button" fontWeight="regular" color="text">
              Receive Email notifications
            </MDTypography>
          </MDBox>
        </MDBox>
      </MDBox>
    </Card>
  );
}

// Setting default props for the PlatformSettings
PlatformSettings.defaultProps = {
  user: null,
};

// Typechecking props for the PlatformSettings
PlatformSettings.propTypes = {
  user: PropTypes.object,
};

export default PlatformSettings;
