import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Divider from "@mui/material/Divider";
import FacebookIcon from "@mui/icons-material/Facebook";
import TwitterIcon from "@mui/icons-material/Twitter";
import InstagramIcon from "@mui/icons-material/Instagram";
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import ProfileInfoCard from "examples/Cards/InfoCards/ProfileInfoCard";
import Header from "layouts/profile/components/Header";
import PlatformSettings from "layouts/profile/components/PlatformSettings";
import MDButton from "components/MDButton";

function Overview() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Load user profile from session storage
    const token = sessionStorage.getItem("token") || localStorage.getItem("token");
    const storedProfile =
      sessionStorage.getItem("userProfile") || localStorage.getItem("userProfile");

    if (token && storedProfile) {
      try {
        const profileData = JSON.parse(storedProfile);
        setUser(profileData);
      } catch (error) {
        console.error("Failed to parse profile", error);
        navigate("/authentication/sign-in");
      }
    } else {
      navigate("/authentication/sign-in");
    }
  }, [navigate]);

  const logout = () => {
    // Clear session storage
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userProfile");
    // Clear localStorage (for "Remember me" feature)
    localStorage.removeItem("token");
    localStorage.removeItem("userProfile");
    navigate("/authentication/sign-in");
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox mb={2} />
      <Header user={user}>
        <MDBox mt={5} mb={3}>
          <Grid container spacing={1}>
            <Grid item xs={12} md={6} xl={4}>
              <PlatformSettings user={user} />
            </Grid>
            <Grid item xs={12} md={6} xl={4} sx={{ display: "flex" }}>
              <Divider orientation="vertical" sx={{ ml: -2, mr: 1 }} />
              {user ? (
                <ProfileInfoCard
                  title="profile information"
                  description={`Welcome, ${user.userName || user.userId}!`}
                  info={{
                    fullName: user.userName || user.userId || "",
                    userId: user.userId || "",
                    role: user.userRole || "USER",
                  }}
                  social={[
                    {
                      link: "https://www.facebook.com/",
                      icon: <FacebookIcon />,
                      color: "facebook",
                    },
                    {
                      link: "https://twitter.com/",
                      icon: <TwitterIcon />,
                      color: "twitter",
                    },
                    {
                      link: "https://www.instagram.com/",
                      icon: <InstagramIcon />,
                      color: "instagram",
                    },
                  ]}
                  action={{ route: "", tooltip: "Edit Profile" }}
                  shadow={false}
                />
              ) : (
                <MDBox p={2}>
                  <MDTypography variant="body2">No profile data. Please login again.</MDTypography>
                </MDBox>
              )}
              <Divider orientation="vertical" sx={{ mx: 0 }} />
            </Grid>
            <Grid item xs={12} xl={4}>
              {/* Could add more profile content here */}
              <MDBox p={2}>
                <MDButton variant="gradient" color="error" fullWidth onClick={logout}>
                  Logout
                </MDButton>
              </MDBox>
            </Grid>
          </Grid>
        </MDBox>
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
