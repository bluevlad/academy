import React, { useState } from 'react';
import { Layout } from 'antd';
import { Sidebar, SidebarMenuGroup } from './Sidebar';
import { HeaderBar, HeaderBarUser } from './HeaderBar';

const { Content } = Layout;

export interface AppShellProps {
  appTitle: string;
  menuGroups: SidebarMenuGroup[];
  user: HeaderBarUser | null;
  onLogout: () => void;
  children: React.ReactNode;
}

/**
 * academy 자체 AppShell — Material Dashboard 대체 (ADR-006 §3).
 * Sidebar(접힘 지원) + HeaderBar(유저/로그아웃) + Content.
 */
export function AppShell({ appTitle, menuGroups, user, onLogout, children }: AppShellProps) {
  const [collapsed, setCollapsed] = useState<boolean>(() => {
    if (typeof window === 'undefined') return false;
    return window.localStorage.getItem('academy.sidebarCollapsed') === '1';
  });

  const handleCollapse = (next: boolean) => {
    setCollapsed(next);
    if (typeof window !== 'undefined') {
      window.localStorage.setItem('academy.sidebarCollapsed', next ? '1' : '0');
    }
  };

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Sidebar
        appTitle={appTitle}
        menuGroups={menuGroups}
        collapsed={collapsed}
        onCollapse={handleCollapse}
      />
      <Layout>
        <HeaderBar user={user} onLogout={onLogout} />
        <Content style={{ margin: 16, minHeight: 'calc(100vh - 64px - 32px)' }}>
          {children}
        </Content>
      </Layout>
    </Layout>
  );
}
