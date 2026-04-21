// prop-types is a library for typechecking of props
import PropTypes from "prop-types";

// @mui material components
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


function Transaction({ color, icon, name, description, value }) {
  return (
    <Box key={name} component="li" py={1} pr={2} mb={1}>
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Box display="flex" alignItems="center">
          <Box mr={2}>
            <Button variant="outlined" color={color} iconOnly circular>
              <Icon sx={{ fontWeight: "bold" }}>{icon}</Icon>
            </Button>
          </Box>
          <Box display="flex" flexDirection="column">
            <Typography variant="button" fontWeight="medium" gutterBottom>
              {name}
            </Typography>
            <Typography variant="caption" color="text" fontWeight="regular">
              {description}
            </Typography>
          </Box>
        </Box>
        <Typography variant="button" color={color} fontWeight="medium">
          {value}
        </Typography>
      </Box>
    </Box>
  );
}

// Typechecking props of the Transaction
Transaction.propTypes = {
  color: PropTypes.oneOf([
    "primary",
    "secondary",
    "info",
    "success",
    "warning",
    "error",
    "light",
    "dark",
  ]).isRequired,
  icon: PropTypes.node.isRequired,
  name: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
};

export default Transaction;
