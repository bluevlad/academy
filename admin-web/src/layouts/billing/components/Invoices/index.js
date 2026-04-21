// @mui material components
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Button from "@mui/material/Button";


// Billing page components
import Invoice from "layouts/billing/components/Invoice";

function Invoices() {
  return (
    <Card sx={{ height: "100%" }}>
      <Box pt={2} px={2} display="flex" justifyContent="space-between" alignItems="center">
        <Typography variant="h6" fontWeight="medium">
          Invoices
        </Typography>
        <Button variant="outlined" color="info" size="small">
          view all
        </Button>
      </Box>
      <Box p={2}>
        <Box component="ul" display="flex" flexDirection="column" p={0} m={0}>
          <Invoice date="March, 01, 2020" id="#MS-415646" price="$180" />
          <Invoice date="February, 10, 2021" id="#RV-126749" price="$250" />
          <Invoice date="April, 05, 2020" id="#QW-103578" price="$120" />
          <Invoice date="June, 25, 2019" id="#MS-415646" price="$180" />
          <Invoice date="March, 01, 2019" id="#AR-803481" price="$300" noGutter />
        </Box>
      </Box>
    </Card>
  );
}

export default Invoices;
