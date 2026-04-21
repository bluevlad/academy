/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */
// @mui material components
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Avatar from "@mui/material/Avatar";

import LinearProgress from "@mui/material/LinearProgress";


// Images
import LogoAsana from "assets/images/small-logos/logo-asana.svg";
import logoGithub from "assets/images/small-logos/github.svg";
import logoAtlassian from "assets/images/small-logos/logo-atlassian.svg";
import logoSlack from "assets/images/small-logos/logo-slack.svg";
import logoSpotify from "assets/images/small-logos/logo-spotify.svg";
import logoInvesion from "assets/images/small-logos/logo-invision.svg";

export default function data() {
  const Project = ({ image, name }) => (
    <Box display="flex" alignItems="center" lineHeight={1}>
      <Avatar src={image} name={name} size="sm" variant="rounded" />
      <Typography display="block" variant="button" fontWeight="medium" ml={1} lineHeight={1}>
        {name}
      </Typography>
    </Box>
  );

  const Progress = ({ color, value }) => (
    <Box display="flex" alignItems="center">
      <Typography variant="caption" color="text" fontWeight="medium">
        {value}%
      </Typography>
      <Box ml={0.5} width="9rem">
        <LinearProgress variant="contained" color={color} value={value} />
      </Box>
    </Box>
  );

  return {
    columns: [
      { Header: "project", accessor: "project", width: "30%", align: "left" },
      { Header: "budget", accessor: "budget", align: "left" },
      { Header: "status", accessor: "status", align: "center" },
      { Header: "completion", accessor: "completion", align: "center" },
      { Header: "action", accessor: "action", align: "center" },
    ],

    rows: [
      {
        project: <Project image={LogoAsana} name="Asana" />,
        budget: (
          <Typography component="a" href="#" variant="button" color="text" fontWeight="medium">
            $2,500
          </Typography>
        ),
        status: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            working
          </Typography>
        ),
        completion: <Progress color="info" value={60} />,
        action: (
          <Typography component="a" href="#" color="text">
            <Icon>more_vert</Icon>
          </Typography>
        ),
      },
      {
        project: <Project image={logoGithub} name="Github" />,
        budget: (
          <Typography component="a" href="#" variant="button" color="text" fontWeight="medium">
            $5,000
          </Typography>
        ),
        status: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            done
          </Typography>
        ),
        completion: <Progress color="success" value={100} />,
        action: (
          <Typography component="a" href="#" color="text">
            <Icon>more_vert</Icon>
          </Typography>
        ),
      },
      {
        project: <Project image={logoAtlassian} name="Atlassian" />,
        budget: (
          <Typography component="a" href="#" variant="button" color="text" fontWeight="medium">
            $3,400
          </Typography>
        ),
        status: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            canceled
          </Typography>
        ),
        completion: <Progress color="error" value={30} />,
        action: (
          <Typography component="a" href="#" color="text">
            <Icon>more_vert</Icon>
          </Typography>
        ),
      },
      {
        project: <Project image={logoSpotify} name="Spotify" />,
        budget: (
          <Typography component="a" href="#" variant="button" color="text" fontWeight="medium">
            $14,000
          </Typography>
        ),
        status: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            working
          </Typography>
        ),
        completion: <Progress color="info" value={80} />,
        action: (
          <Typography component="a" href="#" color="text">
            <Icon>more_vert</Icon>
          </Typography>
        ),
      },
      {
        project: <Project image={logoSlack} name="Slack" />,
        budget: (
          <Typography component="a" href="#" variant="button" color="text" fontWeight="medium">
            $1,000
          </Typography>
        ),
        status: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            canceled
          </Typography>
        ),
        completion: <Progress color="error" value={0} />,
        action: (
          <Typography component="a" href="#" color="text">
            <Icon>more_vert</Icon>
          </Typography>
        ),
      },
      {
        project: <Project image={logoInvesion} name="Invesion" />,
        budget: (
          <Typography component="a" href="#" variant="button" color="text" fontWeight="medium">
            $2,300
          </Typography>
        ),
        status: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            done
          </Typography>
        ),
        completion: <Progress color="success" value={100} />,
        action: (
          <Typography component="a" href="#" color="text">
            <Icon>more_vert</Icon>
          </Typography>
        ),
      },
    ],
  };
}
