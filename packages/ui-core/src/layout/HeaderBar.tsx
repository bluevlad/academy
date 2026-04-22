import { Avatar, Dropdown, Layout, Space } from 'antd';
import { UserOutlined, LogoutOutlined } from '@ant-design/icons';

const { Header } = Layout;

export interface HeaderBarUser {
  userId: string;
  role: 'ADMIN' | 'USER';
}

export interface HeaderBarProps {
  user: HeaderBarUser | null;
  onLogout: () => void;
}

export function HeaderBar({ user, onLogout }: HeaderBarProps) {
  const menuItems = [
    {
      key: 'logout',
      icon: <LogoutOutlined />,
      label: '로그아웃',
      onClick: onLogout,
    },
  ];

  return (
    <Header
      style={{
        display: 'flex',
        justifyContent: 'flex-end',
        alignItems: 'center',
        padding: '0 24px',
        background: '#ffffff',
        boxShadow: '0 1px 2px rgba(0,0,0,0.04)',
      }}
    >
      {user ? (
        <Dropdown menu={{ items: menuItems }} placement="bottomRight" trigger={['click']}>
          <Space style={{ cursor: 'pointer' }}>
            <Avatar size="small" icon={<UserOutlined />} />
            <span>{user.userId}</span>
            <span style={{ color: '#9ca3af', fontSize: 12 }}>({user.role})</span>
          </Space>
        </Dropdown>
      ) : null}
    </Header>
  );
}
