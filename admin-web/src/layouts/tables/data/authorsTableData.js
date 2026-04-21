/* eslint-disable react/prop-types */
/* eslint-disable react/function-component-definition */
// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Avatar from "@mui/material/Avatar";

import Badge from "@mui/material/Badge";


// Images
import team2 from "assets/images/team-2.jpg";
import team3 from "assets/images/team-3.jpg";
import team4 from "assets/images/team-4.jpg";

export default function data() {
  const Author = ({ image, name, email }) => (
    <Box display="flex" alignItems="center" lineHeight={1}>
      <Avatar src={image} name={name} size="sm" />
      <Box ml={2} lineHeight={1}>
        <Typography display="block" variant="button" fontWeight="medium">
          {name}
        </Typography>
        <Typography variant="caption">{email}</Typography>
      </Box>
    </Box>
  );

  const Job = ({ title, description }) => (
    <Box lineHeight={1} textAlign="left">
      <Typography display="block" variant="caption" color="text" fontWeight="medium">
        {title}
      </Typography>
      <Typography variant="caption">{description}</Typography>
    </Box>
  );

  return {
    columns: [
      { Header: "author", accessor: "author", width: "45%", align: "left" },
      { Header: "function", accessor: "function", align: "left" },
      { Header: "status", accessor: "status", align: "center" },
      { Header: "employed", accessor: "employed", align: "center" },
      { Header: "action", accessor: "action", align: "center" },
    ],

    rows: [
      {
        author: <Author image={team2} name="John Michael" email="john@creative-tim.com" />,
        function: <Job title="Manager" description="Organization" />,
        status: (
          <Box ml={-1}>
            <Badge badgeContent="online" color="success" variant="contained" size="sm" />
          </Box>
        ),
        employed: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            23/04/18
          </Typography>
        ),
        action: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            Edit
          </Typography>
        ),
      },
      {
        author: <Author image={team3} name="Alexa Liras" email="alexa@creative-tim.com" />,
        function: <Job title="Programator" description="Developer" />,
        status: (
          <Box ml={-1}>
            <Badge badgeContent="offline" color="dark" variant="contained" size="sm" />
          </Box>
        ),
        employed: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            11/01/19
          </Typography>
        ),
        action: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            Edit
          </Typography>
        ),
      },
      {
        author: <Author image={team4} name="Laurent Perrier" email="laurent@creative-tim.com" />,
        function: <Job title="Executive" description="Projects" />,
        status: (
          <Box ml={-1}>
            <Badge badgeContent="online" color="success" variant="contained" size="sm" />
          </Box>
        ),
        employed: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            19/09/17
          </Typography>
        ),
        action: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            Edit
          </Typography>
        ),
      },
      {
        author: <Author image={team3} name="Michael Levi" email="michael@creative-tim.com" />,
        function: <Job title="Programator" description="Developer" />,
        status: (
          <Box ml={-1}>
            <Badge badgeContent="online" color="success" variant="contained" size="sm" />
          </Box>
        ),
        employed: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            24/12/08
          </Typography>
        ),
        action: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            Edit
          </Typography>
        ),
      },
      {
        author: <Author image={team3} name="Richard Gran" email="richard@creative-tim.com" />,
        function: <Job title="Manager" description="Executive" />,
        status: (
          <Box ml={-1}>
            <Badge badgeContent="offline" color="dark" variant="contained" size="sm" />
          </Box>
        ),
        employed: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            04/10/21
          </Typography>
        ),
        action: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            Edit
          </Typography>
        ),
      },
      {
        author: <Author image={team4} name="Miriam Eric" email="miriam@creative-tim.com" />,
        function: <Job title="Programator" description="Developer" />,
        status: (
          <Box ml={-1}>
            <Badge badgeContent="offline" color="dark" variant="contained" size="sm" />
          </Box>
        ),
        employed: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            14/09/20
          </Typography>
        ),
        action: (
          <Typography component="a" href="#" variant="caption" color="text" fontWeight="medium">
            Edit
          </Typography>
        ),
      },
    ],
  };
}
