import { Outlet } from 'react-router-dom';
import { AppShell, useAuth, SidebarMenuGroup } from '@academy/ui-core';
import {
  HomeOutlined,
  BookOutlined,
  ShoppingCartOutlined,
  UserOutlined,
  PlayCircleOutlined,
  FileTextOutlined,
} from '@ant-design/icons';

const menuGroups: SidebarMenuGroup[] = [
  {
    key: 'home',
    label: '',
    items: [{ key: 'home', icon: <HomeOutlined />, label: '홈', to: '/' }],
  },
  {
    key: 'study',
    label: '학습',
    items: [
      { key: 'lectures', icon: <BookOutlined />, label: '강의 둘러보기', to: '/lectures' },
      { key: 'mylecture', icon: <PlayCircleOutlined />, label: '내 강의실', to: '/mypage/mylecture' },
      { key: 'mocktest', icon: <FileTextOutlined />, label: '모의고사', to: '/mocktest' },
    ],
  },
  {
    key: 'my',
    label: '마이',
    items: [
      { key: 'cart', icon: <ShoppingCartOutlined />, label: '장바구니', to: '/cart' },
      { key: 'profile', icon: <UserOutlined />, label: '내 정보', to: '/mypage/profile' },
    ],
  },
];

export function UserShell() {
  const { user, logout } = useAuth();
  return (
    <AppShell
      appTitle="Academy"
      menuGroups={menuGroups}
      user={user ? { userId: user.userId, role: user.role } : null}
      onLogout={logout}
    >
      <Outlet />
    </AppShell>
  );
}
