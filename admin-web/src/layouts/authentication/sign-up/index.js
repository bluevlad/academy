import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Card from "@mui/material/Card";
import Checkbox from "@mui/material/Checkbox";
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import TextField from "@mui/material/TextField";

import Button from "@mui/material/Button";

import CoverLayout from "layouts/authentication/components/CoverLayout";
import bgImage from "assets/images/bg-sign-up-cover.jpeg";
import { register } from "api/login";

function Cover() {
  const [name, setName] = useState("");
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await register({ id, password, name, email });
      setSuccess(true);
      setTimeout(() => navigate("/authentication/sign-in"), 2000);
    } catch (err) {
      setError(err.message || "Registration failed");
    }
  };

  return (
    <CoverLayout image={bgImage}>
      <Card>
        <Box
          variant="contained"
          bgColor="info"
          borderRadius="lg"
          coloredShadow="success"
          mx={2}
          mt={-3}
          p={3}
          mb={1}
          textAlign="center"
        >
          <Typography variant="h4" fontWeight="medium" color="white" mt={1}>
            Join us today
          </Typography>
          <Typography display="block" variant="button" color="white" my={1}>
            Enter your details to register
          </Typography>
        </Box>
        <Box pt={4} pb={3} px={3}>
          <Box component="form" role="form" onSubmit={handleSubmit}>
            <Box mb={2}>
              <TextField
                type="text"
                label="Name"
                variant="standard"
                fullWidth
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </Box>
            <Box mb={2}>
              <TextField
                type="text"
                label="ID"
                variant="standard"
                fullWidth
                value={id}
                onChange={(e) => setId(e.target.value)}
              />
            </Box>
            <Box mb={2}>
              <TextField
                type="email"
                label="Email"
                variant="standard"
                fullWidth
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </Box>
            <Box mb={2}>
              <TextField
                type="password"
                label="Password"
                variant="standard"
                fullWidth
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Box>
            <Box display="flex" alignItems="center" ml={-1}>
              <Checkbox />
              <Typography
                variant="button"
                fontWeight="regular"
                color="text"
                sx={{ cursor: "pointer", userSelect: "none", ml: -1 }}
              >
                &nbsp;&nbsp;I agree the&nbsp;
              </Typography>
              <Typography
                component="a"
                href="#"
                variant="button"
                fontWeight="bold"
                color="info"
              >
                Terms and Conditions
              </Typography>
            </Box>
            {error && (
              <Box mt={2} mb={1}>
                <Typography variant="caption" color="error">
                  {error}
                </Typography>
              </Box>
            )}
            {success && (
              <Box mt={2} mb={1}>
                <Typography variant="caption" color="success">
                  Registration successful! Redirecting...
                </Typography>
              </Box>
            )}
            <Box mt={4} mb={1}>
              <Button variant="contained" color="info" fullWidth type="submit">
                sign up
              </Button>
            </Box>
            <Box mt={3} mb={1} textAlign="center">
              <Typography variant="button" color="text">
                Already have an account?{" "}
                <Typography
                  component={Link}
                  to="/authentication/sign-in"
                  variant="button"
                  color="info"
                  fontWeight="medium"
                >
                  Sign In
                </Typography>
              </Typography>
            </Box>
          </Box>
        </Box>
      </Card>
    </CoverLayout>
  );
}

export default Cover;
