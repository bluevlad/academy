/**
 * Zustand Store - 기존 MobX를 대체
 * 현재는 Auth Store만 정의, UI Store는 context/index.js에서 관리 중
 * 향후 Context → Zustand 전환 시 useUIStore 추가 예정
 */
import { create } from "zustand";

/**
 * 인증 상태 관리 Store
 */
export const useAuthStore = create((set) => ({
  token: sessionStorage.getItem("token"),
  user: JSON.parse(sessionStorage.getItem("userProfile") || "null"),
  isAuthenticated: !!sessionStorage.getItem("token"),

  setAuth: (token, user) => {
    sessionStorage.setItem("token", token);
    sessionStorage.setItem("userProfile", JSON.stringify(user));
    set({ token, user, isAuthenticated: true });
  },

  clearAuth: () => {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userProfile");
    localStorage.removeItem("access-token");
    localStorage.removeItem("refresh-token");
    localStorage.removeItem("x-user-id");
    localStorage.removeItem("x-company-code");
    localStorage.removeItem("x-user-role");
    set({ token: null, user: null, isAuthenticated: false });
  },
}));
