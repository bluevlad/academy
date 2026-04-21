import { useState } from "react";

// @mui material components
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import DataTable from "examples/Tables/DataTable";

// Data
import data from "layouts/rtl/components/Projects/data";

function Projects() {
  const { columns, rows } = data();
  const [menu, setMenu] = useState(null);

  const openMenu = ({ currentTarget }) => setMenu(currentTarget);
  const closeMenu = () => setMenu(null);

  const renderMenu = (
    <Menu
      id="simple-menu"
      anchorEl={menu}
      anchorOrigin={{
        vertical: "top",
        horizontal: "left",
      }}
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={Boolean(menu)}
      onClose={closeMenu}
    >
      <MenuItem onClick={closeMenu}>عمل</MenuItem>
      <MenuItem onClick={closeMenu}>عمل آخر</MenuItem>
      <MenuItem onClick={closeMenu}>شيء آخر هنا</MenuItem>
    </Menu>
  );

  return (
    <Card>
      <Box display="flex" justifyContent="space-between" alignItems="center" p={3}>
        <Box>
          <Typography variant="h6" gutterBottom>
            المشاريع
          </Typography>
          <Box display="flex" alignItems="center" lineHeight={0}>
            <Icon
              sx={{
                fontWeight: "bold",
                color: ({ palette: { info } }) => info.main,
                mt: -0.5,
              }}
            >
              done
            </Icon>
            <Typography variant="button" fontWeight="regular" color="text">
              &nbsp;<strong>30 انتهى</strong> هذا الشهر
            </Typography>
          </Box>
        </Box>
        <Box color="text" px={2}>
          <Icon sx={{ cursor: "pointer", fontWeight: "bold" }} fontSize="small" onClick={openMenu}>
            more_vert
          </Icon>
        </Box>
        {renderMenu}
      </Box>
      <Box>
        <DataTable
          table={{ columns, rows }}
          showTotalEntries={false}
          isSorted={false}
          noEndBorder
          entriesPerPage={false}
        />
      </Box>
    </Card>
  );
}

export default Projects;
