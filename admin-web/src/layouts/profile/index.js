import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Divider from "@mui/material/Divider";
import FacebookIcon from "@mui/icons-material/Facebook";
import TwitterIcon from "@mui/icons-material/Twitter";
import InstagramIcon from "@mui/icons-material/Instagram";
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";
import ProfileInfoCard from "examples/Cards/InfoCards/ProfileInfoCard";
import Header from "layouts/profile/components/Header";
import PlatformSettings from "layouts/profile/components/PlatformSettings";
import Button from "@mui/material/Button";

import { getProfile } from "api/login";

function Overview() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = sessionStorage.getItem("token");
        const storedProfile = sessionStorage.getItem("userProfile");
        const userId = storedProfile ? JSON.parse(storedProfile).userId : null;

        if (token && userId) {
          const data = await getProfile(userId, token);
          setUser(data);
        } else {
          navigate("/authentication/sign-in");
        }
      } catch (error) {
        console.error("Failed to load profile", error);
        navigate("/authentication/sign-in");
      }
    };
    fetchProfile();
  }, [navigate]);

  const logout = () => {
    sessionStorage.clear();
    // Also clear localStorage if we used it before, to be safe
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    navigate("/authentication/sign-in");
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <Box mb={2} />
      <Header user={user}>
        <Box mt={5} mb={3}>
          <Grid container spacing={1}>
            <Grid item xs={12} md={6} xl={4}>
              <PlatformSettings user={user} />
            </Grid>
            <Grid item xs={12} md={6} xl={4} sx={{ display: "flex" }}>
              <Divider orientation="vertical" sx={{ ml: -2, mr: 1 }} />
              {user ? (
                <ProfileInfoCard
                  title="profile information"
                  description={`${user.memo || user.userNm || user.userId}`}
                  info={{
                    fullName: user.userNm || user.userId || "",
                    userId: user.userId || "",
                    email: user.email || "",
                    location: user.address1
                      ? `${user.address1} ${user.address2 || ""}`
                      : "Unspecified",
                    birthDay: user.birthDay || "Unspecified",
                    sex: user.sex || "Unspecified",
                    point: user.userPoint ? `${user.userPoint}` : "0",
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
                <Box p={2}>
                  <Typography variant="body2">No profile data. Please login again.</Typography>
                </Box>
              )}
              <Divider orientation="vertical" sx={{ mx: 0 }} />
            </Grid>
            <Grid item xs={12} xl={4}>
              {/* Could add more profile content here */}
              <Box p={2}>
                <Button variant="contained" color="error" fullWidth onClick={logout}>
                  Logout
                </Button>
              </Box>
            </Grid>
          </Grid>
        </Box>
      </Header>
      <Footer />
    </DashboardLayout>
  );
}

export default Overview;
