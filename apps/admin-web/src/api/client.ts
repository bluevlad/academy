import axios from 'axios';
import { createApiClient, tokenStorage, unwrap } from '@academy/ui-core';
import type { ApiResponse, TokenResponse } from '@academy/ui-core';

const baseURL = import.meta.env.VITE_API_BASE_URL ?? '/api';

async function refreshAccessToken(): Promise<string | null> {
  const refreshToken = tokenStorage.getRefresh();
  if (!refreshToken) return null;
  try {
    const res = await axios.post<ApiResponse<TokenResponse>>(
      `${baseURL}/auth/refresh`,
      { refreshToken },
      { headers: { 'Content-Type': 'application/json;charset=UTF-8' } },
    );
    const token = unwrap<TokenResponse>({ data: res.data });
    tokenStorage.setAccess(token.accessToken);
    tokenStorage.setRefresh(token.refreshToken);
    tokenStorage.setUser({ userId: token.userId, role: token.role, audience: token.audience });
    return token.accessToken;
  } catch {
    tokenStorage.clearAll();
    return null;
  }
}

export const apiClient = createApiClient({
  baseURL,
  getAccessToken: () => tokenStorage.getAccess(),
  refreshAccessToken,
  onUnauthorized: () => {
    tokenStorage.clearAll();
    const current = window.location.pathname;
    if (!current.startsWith('/admin/login')) {
      window.location.href = '/admin/login';
    }
  },
});
