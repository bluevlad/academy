import { Outlet } from 'react-router-dom';
import {
  AppShell,
  useAuth,
  SidebarMenuGroup,
} from '@academy/ui-core';
import {
  DashboardOutlined,
  UserOutlined,
  BookOutlined,
  ShoppingCartOutlined,
  GiftOutlined,
  ReadOutlined,
  FileTextOutlined,
} from '@ant-design/icons';

const menuGroups: SidebarMenuGroup[] = [
  {
    key: 'overview',
    label: '개요',
    items: [{ key: 'dashboard', icon: <DashboardOutlined />, label: '대시보드', to: '/dashboard' }],
  },
  {
    key: 'operation',
    label: '운영',
    items: [
      { key: 'members', icon: <UserOutlined />, label: '회원관리', to: '/members' },
      { key: 'lectures', icon: <BookOutlined />, label: '강의/과목/교수', to: '/lectures' },
      { key: 'orders', icon: <ShoppingCartOutlined />, label: '수강(주문)관리', to: '/orders' },
      { key: 'coupons', icon: <GiftOutlined />, label: '포인트/쿠폰', to: '/coupons' },
      { key: 'books', icon: <ReadOutlined />, label: '교재/배송', to: '/books' },
      { key: 'mocktest', icon: <FileTextOutlined />, label: '모의고사', to: '/mocktest' },
    ],
  },
];

export function AdminShell() {
  const { user, logout } = useAuth();
  return (
    <AppShell
      appTitle="Academy Admin"
      menuGroups={menuGroups}
      user={user ? { userId: user.userId, role: user.role } : null}
      onLogout={logout}
    >
      <Outlet />
    </AppShell>
  );
}
