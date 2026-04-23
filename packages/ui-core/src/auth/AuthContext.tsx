import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
} from 'react';
import type { AxiosInstance } from 'axios';
import type { Audience, MeResponse, TokenResponse } from '../types';
import { unwrap } from '../api/client';
import { StoredUser, tokenStorage } from './tokenStorage';

export interface AuthContextValue {
  ready: boolean;
  authenticated: boolean;
  user: StoredUser | null;
  login: (userId: string, password: string) => Promise<void>;
  logout: () => Promise<void>;
  refresh: () => Promise<void>;
  /** 외부 IdP(Google 등) 로 이미 access/refresh 를 받았을 때 세션 확정. */
  setSession: (tokens: {
    accessToken: string;
    refreshToken: string;
    userId: string;
    role: 'ADMIN' | 'USER';
    audience: 'admin' | 'user';
  }) => void;
}

const AuthContext = createContext<AuthContextValue | null>(null);

export interface AuthProviderProps {
  /** 이 앱의 audience — 'admin' | 'user' */
  audience: Audience;
  /** ui-core createApiClient 로 만들어진 인스턴스 */
  apiClient: AxiosInstance;
  children: React.ReactNode;
}

export function AuthProvider({ audience, apiClient, children }: AuthProviderProps) {
  const [user, setUser] = useState<StoredUser | null>(() => tokenStorage.getUser());
  const [ready, setReady] = useState<boolean>(false);

  const login = useCallback(
    async (userId: string, password: string) => {
      const res = await apiClient.post<{ data: TokenResponse; success: boolean }>(
        '/auth/login',
        { userId, password, audience },
      );
      const token = unwrap<TokenResponse>({ data: res.data as never });
      tokenStorage.setAccess(token.accessToken);
      tokenStorage.setRefresh(token.refreshToken);
      const next: StoredUser = { userId: token.userId, role: token.role, audience: token.audience };
      tokenStorage.setUser(next);
      setUser(next);
    },
    [apiClient, audience],
  );

  const logout = useCallback(async () => {
    const refresh = tokenStorage.getRefresh();
    try {
      await apiClient.post('/auth/logout', refresh ? { refreshToken: refresh } : {});
    } catch {
      // ignore — storage 만 정리
    }
    tokenStorage.clearAll();
    setUser(null);
  }, [apiClient]);

  const refresh = useCallback(async () => {
    const access = tokenStorage.getAccess();
    if (!access) {
      setUser(null);
      return;
    }
    try {
      const res = await apiClient.get('/auth/me');
      const me = unwrap<MeResponse>({ data: res.data as never });
      if (me.audience !== audience) {
        tokenStorage.clearAll();
        setUser(null);
        return;
      }
      const next: StoredUser = { userId: me.userId, role: me.role, audience: me.audience };
      tokenStorage.setUser(next);
      setUser(next);
    } catch {
      tokenStorage.clearAll();
      setUser(null);
    }
  }, [apiClient, audience]);

  useEffect(() => {
    (async () => {
      await refresh();
      setReady(true);
    })();
  }, [refresh]);

  const setSession = useCallback<AuthContextValue['setSession']>((tokens) => {
    tokenStorage.setAccess(tokens.accessToken);
    tokenStorage.setRefresh(tokens.refreshToken);
    const next: StoredUser = {
      userId: tokens.userId,
      role: tokens.role,
      audience: tokens.audience,
    };
    tokenStorage.setUser(next);
    setUser(next);
  }, []);

  const value = useMemo<AuthContextValue>(
    () => ({ ready, authenticated: !!user, user, login, logout, refresh, setSession }),
    [ready, user, login, logout, refresh, setSession],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth(): AuthContextValue {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
}
