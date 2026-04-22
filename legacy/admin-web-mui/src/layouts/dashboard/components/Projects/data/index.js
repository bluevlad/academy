/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */
// @mui material components
import Tooltip from "@mui/material/Tooltip";
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Avatar from "@mui/material/Avatar";

import LinearProgress from "@mui/material/LinearProgress";


// Images
import logoXD from "assets/images/small-logos/logo-xd.svg";
import logoAtlassian from "assets/images/small-logos/logo-atlassian.svg";
import logoSlack from "assets/images/small-logos/logo-slack.svg";
import logoSpotify from "assets/images/small-logos/logo-spotify.svg";
import logoJira from "assets/images/small-logos/logo-jira.svg";
import logoInvesion from "assets/images/small-logos/logo-invision.svg";
import team1 from "assets/images/team-1.jpg";
import team2 from "assets/images/team-2.jpg";
import team3 from "assets/images/team-3.jpg";
import team4 from "assets/images/team-4.jpg";

export default function data() {
  const avatars = (members) =>
    members.map(([image, name]) => (
      <Tooltip key={name} title={name} placeholder="bottom">
        <Avatar
          src={image}
          alt="name"
          size="xs"
          sx={{
            border: ({ borders: { borderWidth }, palette: { white } }) =>
              `${borderWidth[2]} solid ${white.main}`,
            cursor: "pointer",
            position: "relative",

            "&:not(:first-of-type)": {
              ml: -1.25,
            },

            "&:hover, &:focus": {
              zIndex: "10",
            },
          }}
        />
      </Tooltip>
    ));

  const Company = ({ image, name }) => (
    <Box display="flex" alignItems="center" lineHeight={1}>
      <Avatar src={image} name={name} size="sm" />
      <Typography variant="button" fontWeight="medium" ml={1} lineHeight={1}>
        {name}
      </Typography>
    </Box>
  );

  return {
    columns: [
      { Header: "companies", accessor: "companies", width: "45%", align: "left" },
      { Header: "members", accessor: "members", width: "10%", align: "left" },
      { Header: "budget", accessor: "budget", align: "center" },
      { Header: "completion", accessor: "completion", align: "center" },
    ],

    rows: [
      {
        companies: <Company image={logoXD} name="Material UI XD Version" />,
        members: (
          <Box display="flex" py={1}>
            {avatars([
              [team1, "Ryan Tompson"],
              [team2, "Romina Hadid"],
              [team3, "Alexander Smith"],
              [team4, "Jessica Doe"],
            ])}
          </Box>
        ),
        budget: (
          <Typography variant="caption" color="text" fontWeight="medium">
            $14,000
          </Typography>
        ),
        completion: (
          <Box width="8rem" textAlign="left">
            <LinearProgress value={60} color="info" variant="contained" label={false} />
          </Box>
        ),
      },
      {
        companies: <Company image={logoAtlassian} name="Add Progress Track" />,
        members: (
          <Box display="flex" py={1}>
            {avatars([
              [team2, "Romina Hadid"],
              [team4, "Jessica Doe"],
            ])}
          </Box>
        ),
        budget: (
          <Typography variant="caption" color="text" fontWeight="medium">
            $3,000
          </Typography>
        ),
        completion: (
          <Box width="8rem" textAlign="left">
            <LinearProgress value={10} color="info" variant="contained" label={false} />
          </Box>
        ),
      },
      {
        companies: <Company image={logoSlack} name="Fix Platform Errors" />,
        members: (
          <Box display="flex" py={1}>
            {avatars([
              [team1, "Ryan Tompson"],
              [team3, "Alexander Smith"],
            ])}
          </Box>
        ),
        budget: (
          <Typography variant="caption" color="text" fontWeight="medium">
            Not set
          </Typography>
        ),
        completion: (
          <Box width="8rem" textAlign="left">
            <LinearProgress value={100} color="success" variant="contained" label={false} />
          </Box>
        ),
      },
      {
        companies: <Company image={logoSpotify} name="Launch our Mobile App" />,
        members: (
          <Box display="flex" py={1}>
            {avatars([
              [team4, "Jessica Doe"],
              [team3, "Alexander Smith"],
              [team2, "Romina Hadid"],
              [team1, "Ryan Tompson"],
            ])}
          </Box>
        ),
        budget: (
          <Typography variant="caption" color="text" fontWeight="medium">
            $20,500
          </Typography>
        ),
        completion: (
          <Box width="8rem" textAlign="left">
            <LinearProgress value={100} color="success" variant="contained" label={false} />
          </Box>
        ),
      },
      {
        companies: <Company image={logoJira} name="Add the New Pricing Page" />,
        members: (
          <Box display="flex" py={1}>
            {avatars([[team4, "Jessica Doe"]])}
          </Box>
        ),
        budget: (
          <Typography variant="caption" color="text" fontWeight="medium">
            $500
          </Typography>
        ),
        completion: (
          <Box width="8rem" textAlign="left">
            <LinearProgress value={25} color="info" variant="contained" label={false} />
          </Box>
        ),
      },
      {
        companies: <Company image={logoInvesion} name="Redesign New Online Shop" />,
        members: (
          <Box display="flex" py={1}>
            {avatars([
              [team1, "Ryan Tompson"],
              [team4, "Jessica Doe"],
            ])}
          </Box>
        ),
        budget: (
          <Typography variant="caption" color="text" fontWeight="medium">
            $2,000
          </Typography>
        ),
        completion: (
          <Box width="8rem" textAlign="left">
            <LinearProgress value={40} color="info" variant="contained" label={false} />
          </Box>
        ),
      },
    ],
  };
}
