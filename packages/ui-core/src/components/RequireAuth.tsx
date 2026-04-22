import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { Spin } from 'antd';
import { useAuth } from '../auth/AuthContext';

export interface RequireAuthProps {
  /** 리다이렉트 대상 로그인 경로 (기본 `/login`) */
  loginPath?: string;
  /** 요구 role. 없으면 audience 일치만 검사 (AuthContext 가 audience 강제) */
  requireRole?: 'ADMIN' | 'USER';
  children: React.ReactNode;
}

export function RequireAuth({ loginPath = '/login', requireRole, children }: RequireAuthProps) {
  const { ready, authenticated, user } = useAuth();
  const location = useLocation();

  if (!ready) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <Spin size="large" />
      </div>
    );
  }

  if (!authenticated) {
    return <Navigate to={loginPath} state={{ from: location.pathname }} replace />;
  }

  if (requireRole && user?.role !== requireRole) {
    return <Navigate to={loginPath} replace />;
  }

  return <>{children}</>;
}
