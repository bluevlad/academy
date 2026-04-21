import { useMemo } from "react";

// porp-types is a library for typechecking of props
import PropTypes from "prop-types";

// react-chartjs-2 components
import { Pie } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

// @mui material components
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";


// PieChart configurations
import configs from "examples/Charts/PieChart/configs";

ChartJS.register(ArcElement, Tooltip, Legend);

function PieChart({ icon, title, description, height, chart }) {
  const { data, options } = configs(chart.labels || [], chart.datasets || {});

  const renderChart = (
    <Box py={2} pr={2} pl={icon.component ? 1 : 2}>
      {title || description ? (
        <Box display="flex" px={description ? 1 : 0} pt={description ? 1 : 0}>
          {icon.component && (
            <Box
              width="4rem"
              height="4rem"
              bgColor={icon.color || "dark"}
              variant="contained"
              coloredShadow={icon.color || "dark"}
              borderRadius="xl"
              display="flex"
              justifyContent="center"
              alignItems="center"
              color="white"
              mt={-5}
              mr={2}
            >
              <Icon fontSize="medium">{icon.component}</Icon>
            </Box>
          )}
          <Box mt={icon.component ? -2 : 0}>
            {title && <Typography variant="h6">{title}</Typography>}
            <Box mb={2}>
              <Typography component="div" variant="button" color="text">
                {description}
              </Typography>
            </Box>
          </Box>
        </Box>
      ) : null}
      {useMemo(
        () => (
          <Box height={height}>
            <Pie data={data} options={options} redraw />
          </Box>
        ),
        [chart, height]
      )}
    </Box>
  );

  return title || description ? <Card>{renderChart}</Card> : renderChart;
}

// Setting default values for the props of PieChart
PieChart.defaultProps = {
  icon: { color: "info", component: "" },
  title: "",
  description: "",
  height: "19.125rem",
};

// Typechecking props for the PieChart
PieChart.propTypes = {
  icon: PropTypes.shape({
    color: PropTypes.oneOf([
      "primary",
      "secondary",
      "info",
      "success",
      "warning",
      "error",
      "light",
      "dark",
    ]),
    component: PropTypes.node,
  }),
  title: PropTypes.string,
  description: PropTypes.oneOfType([PropTypes.string, PropTypes.node]),
  height: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  chart: PropTypes.objectOf(PropTypes.oneOfType([PropTypes.array, PropTypes.object])).isRequired,
};

export default PieChart;
