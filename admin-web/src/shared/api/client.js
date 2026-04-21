/**
 * 통합 API 클라이언트
 * 기존 Superagent + Axios 이중 구조를 Axios 단일 인스턴스로 통합
 * - headersConfig.js의 헤더 주입 로직 통합
 * - request.js의 토큰 갱신 로직 통합
 * - BASE_API 환경변수 대응
 */
import axios from "axios";

const BASE_API =
  import.meta.env.VITE_API_URL || "http://academy.unmong.com:9001/admin/api";

const apiClient = axios.create({
  baseURL: BASE_API,
  timeout: 60 * 1000,
  withCredentials: true, // 세션 쿠키 전송 (관리자 인증)
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
    Accept: "application/json",
  },
});

// 요청 인터셉터: 인증 헤더 자동 주입
apiClient.interceptors.request.use(
  (config) => {
    // Bearer 토큰 (sessionStorage 우선, localStorage 폴백)
    const token =
      sessionStorage.getItem("token") ||
      localStorage.getItem("access-token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }

    // Refresh 토큰
    const refreshToken = localStorage.getItem("refresh-token");
    if (refreshToken) {
      config.headers["X-Refresh-Token"] = refreshToken;
    }

    // 사용자 정보 헤더
    const userId = localStorage.getItem("x-user-id");
    const companyCode = localStorage.getItem("x-company-code");
    const userRole = localStorage.getItem("x-user-role");

    if (userId) config.headers["X-User-Id"] = userId;
    if (companyCode) config.headers["X-Company-Code"] = companyCode;
    if (userRole) config.headers["X-User-Role"] = userRole;

    // 네비게이션 정보
    config.headers["X-Current-URL"] = window.location.pathname;
    const currentMenuId = localStorage.getItem("current-menu-id");
    if (currentMenuId) config.headers["X-Current-Menu-Id"] = currentMenuId;

    // 타임존
    config.headers["X-Timezone"] =
      Intl.DateTimeFormat().resolvedOptions().timeZone;

    // Accept-Language
    config.headers["Accept-Language"] = navigator.language || "ko-KR";

    // MultiPart 요청 시 Content-Type 자동 제거 (브라우저가 boundary 설정)
    if (config.data instanceof FormData) {
      delete config.headers["Content-Type"];
    }

    return config;
  },
  (error) => Promise.reject(error)
);

// 응답 인터셉터: 401 처리
// 공개 화면(/dashboard, /authentication)에서 발생한 401은 리다이렉트하지 않고
// 호출자가 조용히 무시하도록 한다. 그 외에는 로그인 페이지로 이동.
const PUBLIC_PATH_PATTERNS = ["/dashboard", "/authentication"];

apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem("userProfile");
      const currentPath = window.location.pathname;
      const isOnPublicPage = PUBLIC_PATH_PATTERNS.some((p) =>
        currentPath.includes(p)
      );
      if (!isOnPublicPage) {
        window.location.href = "/admin/authentication/sign-in";
      }
    }
    return Promise.reject(error);
  }
);

export { BASE_API };
export default apiClient;
