import { createContext, useContext, useEffect, useState, useCallback } from "react";
import apiClient from "shared/api/client";

const AuthContext = createContext({
  isAuthenticated: false,
  username: null,
  loading: true,
  refresh: async () => {},
  logout: async () => {},
});

export function AuthProvider({ children }) {
  const [state, setState] = useState({
    isAuthenticated: false,
    username: null,
    loading: true,
  });

  const refresh = useCallback(async () => {
    const token = sessionStorage.getItem("token");
    if (!token) {
      setState({ isAuthenticated: false, username: null, loading: false });
      return;
    }
    try {
      const res = await apiClient.get("/auth/me");
      const me = res.data?.data;
      if (me?.userId) {
        setState({
          isAuthenticated: true,
          username: me.userId,
          loading: false,
        });
      } else {
        setState({ isAuthenticated: false, username: null, loading: false });
      }
    } catch {
      setState({ isAuthenticated: false, username: null, loading: false });
    }
  }, []);

  const logout = useCallback(async () => {
    const refreshToken = localStorage.getItem("refresh-token");
    try {
      await apiClient.post("/auth/logout", refreshToken ? { refreshToken } : {});
    } catch {
      // ignore
    }
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userProfile");
    localStorage.removeItem("refresh-token");
    localStorage.removeItem("x-user-id");
    localStorage.removeItem("x-user-role");
    setState({ isAuthenticated: false, username: null, loading: false });
    window.location.href = "/admin/dashboard";
  }, []);

  useEffect(() => {
    refresh();
  }, [refresh]);

  return (
    <AuthContext.Provider value={{ ...state, refresh, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
