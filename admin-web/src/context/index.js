/**
 * Material Dashboard UI Context → Zustand 브릿지
 *
 * 기존 17개 파일이 사용하는 API를 유지하면서 내부를 Zustand로 교체
 * - useMaterialUIController() → [state, noop] 반환 (dispatch는 미사용)
 * - setMiniSidenav(dispatch, value) → useUIStore.getState().setMiniSidenav(value)
 * - MaterialUIControllerProvider → 단순 children 패스스루 (Provider 불필요)
 */
import useUIStore from "stores/useUIStore";

// 기존 컴포넌트가 [controller, dispatch] 패턴으로 사용
// dispatch는 더 이상 필요 없지만 호환성을 위해 noop 반환
function useMaterialUIController() {
  const state = useUIStore();
  return [state, null];
}

// Provider는 Zustand에서 불필요하지만 기존 코드 호환을 위해 유지
function MaterialUIControllerProvider({ children }) {
  return children;
}

// Setter 함수들: 기존 시그니처 (dispatch, value) 유지
// dispatch는 무시하고 Zustand store를 직접 업데이트
const setMiniSidenav = (_dispatch, value) =>
  useUIStore.getState().setMiniSidenav(value);
const setTransparentSidenav = (_dispatch, value) =>
  useUIStore.getState().setTransparentSidenav(value);
const setWhiteSidenav = (_dispatch, value) =>
  useUIStore.getState().setWhiteSidenav(value);
const setSidenavColor = (_dispatch, value) =>
  useUIStore.getState().setSidenavColor(value);
const setTransparentNavbar = (_dispatch, value) =>
  useUIStore.getState().setTransparentNavbar(value);
const setFixedNavbar = (_dispatch, value) =>
  useUIStore.getState().setFixedNavbar(value);
const setOpenConfigurator = (_dispatch, value) =>
  useUIStore.getState().setOpenConfigurator(value);
const setDirection = (_dispatch, value) =>
  useUIStore.getState().setDirection(value);
const setLayout = (_dispatch, value) =>
  useUIStore.getState().setLayout(value);
const setDarkMode = (_dispatch, value) =>
  useUIStore.getState().setDarkMode(value);

export {
  MaterialUIControllerProvider,
  useMaterialUIController,
  setMiniSidenav,
  setTransparentSidenav,
  setWhiteSidenav,
  setSidenavColor,
  setTransparentNavbar,
  setFixedNavbar,
  setOpenConfigurator,
  setDirection,
  setLayout,
  setDarkMode,
};
