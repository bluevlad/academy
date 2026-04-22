/**
 * UI 상태 관리 Zustand Store
 * 기존 context/index.js의 MaterialUIController를 대체
 */
import { create } from "zustand";

const useUIStore = create((set) => ({
  // State
  miniSidenav: false,
  transparentSidenav: false,
  whiteSidenav: false,
  sidenavColor: "info",
  transparentNavbar: true,
  fixedNavbar: true,
  openConfigurator: false,
  direction: "ltr",
  layout: "dashboard",
  darkMode: false,

  // Actions
  setMiniSidenav: (value) => set({ miniSidenav: value }),
  setTransparentSidenav: (value) => set({ transparentSidenav: value }),
  setWhiteSidenav: (value) => set({ whiteSidenav: value }),
  setSidenavColor: (value) => set({ sidenavColor: value }),
  setTransparentNavbar: (value) => set({ transparentNavbar: value }),
  setFixedNavbar: (value) => set({ fixedNavbar: value }),
  setOpenConfigurator: (value) => set({ openConfigurator: value }),
  setDirection: (value) => set({ direction: value }),
  setLayout: (value) => set({ layout: value }),
  setDarkMode: (value) => set({ darkMode: value }),
}));

export default useUIStore;
