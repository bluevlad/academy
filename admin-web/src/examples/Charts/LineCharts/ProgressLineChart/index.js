import { useMemo } from "react";

// porp-types is a library for typechecking of props
import PropTypes from "prop-types";

// react-chartjs-2 components
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler,
} from "chart.js";

// @mui material components
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import LinearProgress from "@mui/material/LinearProgress";


// ProgressLineChart configurations
import configs from "examples/Charts/LineCharts/ProgressLineChart/config";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  Filler
);

function ProgressLineChart({ color, icon, title, count, progress, height, chart }) {
  const { data, options } = configs(color, chart.labels || [], title, chart.data || []);

  return (
    <Card>
      <Box display="flex" alignItems="center" pt={2} px={2}>
        <Box
          width="3rem"
          height="3rem"
          display="grid"
          justifyContent="center"
          alignItems="center"
          borderRadius="md"
          shadow="md"
          color="white"
          bgColor={color}
          variant="contained"
        >
          <Icon fontSize="default">{icon}</Icon>
        </Box>
        <Box ml={2} lineHeight={1}>
          <Typography
            variant="button"
            fontWeight="regular"
            textTransform="capitalize"
            color="text"
          >
            {title}
          </Typography>
          {count ? (
            <Typography variant="h5" fontWeight="bold">
              {count}
            </Typography>
          ) : null}
        </Box>
        <Box width="25%" ml="auto">
          <Typography display="block" variant="caption" fontWeight="medium" color="text">
            {progress}%
          </Typography>
          <Box mt={0.25}>
            <LinearProgress variant="contained" color={color} value={progress} />
          </Box>
        </Box>
      </Box>
      {useMemo(
        () => (
          <Box mt={2}>
            <Line data={data} options={options} style={{ height }} redraw />
          </Box>
        ),
        [chart, height, color]
      )}
    </Card>
  );
}

// Setting default values for the props of ProgressLineChart
ProgressLineChart.defaultProps = {
  color: "info",
  count: 0,
  height: "6.25rem",
};

// Typechecking props for the ProgressLineChart
ProgressLineChart.propTypes = {
  color: PropTypes.oneOf(["primary", "secondary", "info", "success", "warning", "error", "dark"]),
  icon: PropTypes.node.isRequired,
  title: PropTypes.string.isRequired,
  count: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  progress: PropTypes.number.isRequired,
  height: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  chart: PropTypes.objectOf(PropTypes.array).isRequired,
};

export default ProgressLineChart;
