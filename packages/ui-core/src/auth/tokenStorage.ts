const ACCESS_KEY = 'academy.accessToken';
const REFRESH_KEY = 'academy.refreshToken';
const USER_KEY = 'academy.user';

export interface StoredUser {
  userId: string;
  role: 'ADMIN' | 'USER';
  audience: 'admin' | 'user';
}

/**
 * 브라우저 storage 기반 토큰 보관소.
 * - accessToken: sessionStorage (탭 닫으면 증발)
 * - refreshToken: localStorage (장기 유지)
 * - user: localStorage
 */
export const tokenStorage = {
  getAccess(): string | null {
    if (typeof window === 'undefined') return null;
    return sessionStorage.getItem(ACCESS_KEY);
  },
  setAccess(token: string): void {
    sessionStorage.setItem(ACCESS_KEY, token);
  },
  clearAccess(): void {
    sessionStorage.removeItem(ACCESS_KEY);
  },

  getRefresh(): string | null {
    if (typeof window === 'undefined') return null;
    return localStorage.getItem(REFRESH_KEY);
  },
  setRefresh(token: string): void {
    localStorage.setItem(REFRESH_KEY, token);
  },
  clearRefresh(): void {
    localStorage.removeItem(REFRESH_KEY);
  },

  getUser(): StoredUser | null {
    if (typeof window === 'undefined') return null;
    const raw = localStorage.getItem(USER_KEY);
    if (!raw) return null;
    try {
      return JSON.parse(raw) as StoredUser;
    } catch {
      return null;
    }
  },
  setUser(user: StoredUser): void {
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  },
  clearUser(): void {
    localStorage.removeItem(USER_KEY);
  },

  clearAll(): void {
    this.clearAccess();
    this.clearRefresh();
    this.clearUser();
  },
};
