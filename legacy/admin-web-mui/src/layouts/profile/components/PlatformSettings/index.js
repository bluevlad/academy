// prop-types is a library for typechecking of props.
import PropTypes from "prop-types";
import { useState, useEffect } from "react";

// @mui material components
import Card from "@mui/material/Card";
import Switch from "@mui/material/Switch";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";


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
      <Box p={2}>
        <Typography variant="h6" fontWeight="medium" textTransform="capitalize">
          platform settings
        </Typography>
      </Box>
      <Box pt={1} pb={2} px={2} lineHeight={1.25}>
        <Typography variant="caption" fontWeight="bold" color="text" textTransform="uppercase">
          account
        </Typography>
        <Box display="flex" alignItems="center" mb={0.5} ml={-1.5}>
          <Box mt={0.5}>
            <Switch checked={isokSms} onChange={() => setIsokSms(!isokSms)} />
          </Box>
          <Box width="80%" ml={0.5}>
            <Typography variant="button" fontWeight="regular" color="text">
              Receive SMS notifications
            </Typography>
          </Box>
        </Box>
        <Box display="flex" alignItems="center" mb={0.5} ml={-1.5}>
          <Box mt={0.5}>
            <Switch checked={isokEmail} onChange={() => setIsokEmail(!isokEmail)} />
          </Box>
          <Box width="80%" ml={0.5}>
            <Typography variant="button" fontWeight="regular" color="text">
              Receive Email notifications
            </Typography>
          </Box>
        </Box>
      </Box>
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
