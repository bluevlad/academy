// prop-types is a library for typechecking of props
import PropTypes from "prop-types";

// @mui material components
import Link from "@mui/material/Link";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";


// Material Dashboard 2 React base styles
import typography from "assets/theme/base/typography";

function Footer({ company, links, userCompany }) {
  const { href, name } = company;
  const { href: userHref, name: userName } = userCompany;
  const { size } = typography;

  const renderLinks = () =>
    links.map((link) => (
      <Box key={link.name} component="li" px={2} lineHeight={1}>
        <Link href={link.href} target="_blank">
          <Typography variant="button" fontWeight="regular" color="text">
            {link.name}
          </Typography>
        </Link>
      </Box>
    ));

  return (
    <Box
      width="100%"
      display="flex"
      flexDirection={{ xs: "column", lg: "row" }}
      justifyContent="space-between"
      alignItems="center"
      px={1.5}
    >
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        flexWrap="wrap"
        color="text"
        fontSize={size.sm}
        px={1.5}
      >
        &copy; {new Date().getFullYear()},
        <Link href={href} target="_blank">
          <Typography variant="button" fontWeight="medium">
            &nbsp;Online Academy System by Material&nbsp;
          </Typography>
        </Link>
        <Box component="span" mx={2} color="text">
          &nbsp;&nbsp;
        </Box>
        Developed by
        <Link href={userHref} target="_blank">
          <Typography variant="button" fontWeight="medium">
            &nbsp;{userName}&nbsp;
          </Typography>
        </Link>
      </Box>
      <Box
        component="ul"
        sx={({ breakpoints }) => ({
          display: "flex",
          flexWrap: "wrap",
          alignItems: "center",
          justifyContent: "center",
          listStyle: "none",
          mt: 3,
          mb: 0,
          p: 0,

          [breakpoints.up("lg")]: {
            mt: 0,
          },
        })}
      >
        {renderLinks()}
      </Box>
    </Box>
  );
}

// Setting default values for the props of Footer
Footer.defaultProps = {
  company: { href: "https://www.creative-tim.com/", name: "Creative Tim" },
  userCompany: { href: "https://academy.unmong.com", name: "Unmong Systems" },
  links: [
    { href: "https://academy.unmong.com", name: "Unmong Systems" },
    { href: "https://www.creative-tim.com/presentation", name: "About Us" },
    { href: "https://www.creative-tim.com/blog", name: "Blog" },
    { href: "https://www.creative-tim.com/license", name: "License" },
  ],
};

// Typechecking props for the Footer
Footer.propTypes = {
  company: PropTypes.objectOf(PropTypes.string),
  userCompany: PropTypes.objectOf(PropTypes.string),
  links: PropTypes.arrayOf(PropTypes.object),
};

export default Footer;
