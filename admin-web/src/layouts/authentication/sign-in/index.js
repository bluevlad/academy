import { useEffect, useRef, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Card from "@mui/material/Card";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Divider from "@mui/material/Divider";

import BasicLayout from "layouts/authentication/components/BasicLayout";
import bgImage from "assets/images/bg-sign-in-basic.jpeg";
import apiClient from "shared/api/client";
import { useAuth } from "shared/auth/AuthContext";

const GOOGLE_CLIENT_ID = import.meta.env.VITE_GOOGLE_CLIENT_ID || "";

function Basic() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [submitting, setSubmitting] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();
  const { refresh } = useAuth();
  const googleBtnRef = useRef(null);

  const from = location.state?.from?.pathname || "/dashboard";

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSubmitting(true);
    try {
      await apiClient.post("/admin-auth/login", { username, password });
      await refresh();
      navigate(from, { replace: true });
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.message ||
          "로그인에 실패했습니다."
      );
    } finally {
      setSubmitting(false);
    }
  };

  // Google Identity Services 로드 및 버튼 렌더
  useEffect(() => {
    if (!GOOGLE_CLIENT_ID) return;

    const existing = document.getElementById("google-gsi-script");
    const init = () => {
      if (!window.google || !googleBtnRef.current) return;
      window.google.accounts.id.initialize({
        client_id: GOOGLE_CLIENT_ID,
        callback: async (resp) => {
          try {
            await apiClient.post("/auth/google/verify", {
              credential: resp.credential,
            });
            await refresh();
            navigate(from, { replace: true });
          } catch (err) {
            setError(
              err.response?.data?.error ||
                err.message ||
                "Google 로그인에 실패했습니다."
            );
          }
        },
      });
      window.google.accounts.id.renderButton(googleBtnRef.current, {
        theme: "outline",
        size: "large",
        width: 280,
        text: "signin_with",
      });
    };

    if (existing) {
      init();
      return;
    }
    const script = document.createElement("script");
    script.id = "google-gsi-script";
    script.src = "https://accounts.google.com/gsi/client";
    script.async = true;
    script.defer = true;
    script.onload = init;
    document.head.appendChild(script);
  }, [from, navigate, refresh]);

  return (
    <BasicLayout image={bgImage}>
      <Card>
        <Box
          variant="contained"
          bgColor="info"
          borderRadius="lg"
          coloredShadow="info"
          mx={2}
          mt={-3}
          p={2}
          mb={1}
          textAlign="center"
        >
          <Typography variant="h4" fontWeight="medium" color="white" mt={1}>
            관리자 로그인
          </Typography>
          <Typography variant="caption" color="white">
            지정된 관리자 계정 또는 Google 계정으로만 접근할 수 있습니다.
          </Typography>
        </Box>
        <Box pt={4} pb={3} px={3}>
          <Box component="form" role="form" onSubmit={handleSubmit}>
            <Box mb={2}>
              <TextField
                type="text"
                label="관리자 ID"
                fullWidth
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                autoComplete="username"
              />
            </Box>
            <Box mb={2}>
              <TextField
                type="password"
                label="비밀번호"
                fullWidth
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                autoComplete="current-password"
              />
            </Box>
            {error && (
              <Box mt={2} mb={1}>
                <Typography variant="caption" color="error">
                  {error}
                </Typography>
              </Box>
            )}
            <Box mt={3} mb={1}>
              <Button
                variant="contained"
                color="info"
                fullWidth
                type="submit"
                disabled={submitting}
              >
                {submitting ? "로그인 중..." : "관리자 계정으로 로그인"}
              </Button>
            </Box>
          </Box>

          <Box my={3}>
            <Divider>
              <Typography variant="caption" color="text">
                또는
              </Typography>
            </Divider>
          </Box>

          <Box display="flex" justifyContent="center">
            {GOOGLE_CLIENT_ID ? (
              <div ref={googleBtnRef} />
            ) : (
              <Typography variant="caption" color="text" textAlign="center">
                Google 로그인을 사용하려면{" "}
                <code>VITE_GOOGLE_CLIENT_ID</code> 환경변수를 설정하고 서버를
                재시작해 주세요.
              </Typography>
            )}
          </Box>
        </Box>
      </Card>
    </BasicLayout>
  );
}

export default Basic;
