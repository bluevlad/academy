import { createApiClient, tokenStorage } from '@academy/ui-core';

export const apiClient = createApiClient({
  baseURL: import.meta.env.VITE_API_BASE_URL ?? '/api',
  getAccessToken: () => tokenStorage.getAccess(),
  onUnauthorized: () => {
    tokenStorage.clearAll();
    const current = window.location.pathname;
    if (!current.startsWith('/login') && !current.startsWith('/signup')) {
      window.location.href = '/login';
    }
  },
});
