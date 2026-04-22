import { Navigate, useLocation } from "react-router-dom";
import Box from "@mui/material/Box";
import { useAuth } from "shared/auth/AuthContext";

export default function ProtectedRoute({ children }) {
  const { isAuthenticated, loading } = useAuth();
  const location = useLocation();

  if (loading) {
    return (
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        minHeight="60vh"
      >
        인증 확인 중...
      </Box>
    );
  }

  if (!isAuthenticated) {
    return (
      <Navigate
        to="/authentication/sign-in"
        state={{ from: location }}
        replace
      />
    );
  }

  return children;
}
