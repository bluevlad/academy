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
    try {
      const res = await apiClient.get("/admin-auth/me");
      if (res.data?.authenticated) {
        setState({
          isAuthenticated: true,
          username: res.data.username,
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
    try {
      await apiClient.post("/admin-auth/logout");
    } catch {
      // ignore
    }
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userProfile");
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
