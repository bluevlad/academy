import { Layout, Menu } from 'antd';
import type { MenuProps } from 'antd';
import { useLocation, useNavigate } from 'react-router-dom';

const { Sider } = Layout;

export interface SidebarMenuItem {
  key: string;
  label: string;
  icon?: React.ReactNode;
  to?: string;
  children?: SidebarMenuItem[];
}

export interface SidebarMenuGroup {
  key: string;
  label: string;
  items: SidebarMenuItem[];
}

export interface SidebarProps {
  appTitle: string;
  menuGroups: SidebarMenuGroup[];
  collapsed: boolean;
  onCollapse: (next: boolean) => void;
}

export function Sidebar({ appTitle, menuGroups, collapsed, onCollapse }: SidebarProps) {
  const navigate = useNavigate();
  const location = useLocation();

  const items: MenuProps['items'] = menuGroups.flatMap((group) => [
    {
      type: 'group' as const,
      key: group.key,
      label: collapsed ? undefined : group.label,
      children: group.items.map((item) => ({
        key: item.key,
        icon: item.icon,
        label: item.label,
        children: item.children?.map((c) => ({ key: c.key, label: c.label })),
      })),
    },
  ]);

  const flat = menuGroups.flatMap((g) => g.items.flatMap((i) => [i, ...(i.children ?? [])]));
  const active = flat.find((i) => i.to && location.pathname.startsWith(i.to));

  return (
    <Sider collapsible collapsed={collapsed} onCollapse={onCollapse} width={240} theme="dark">
      <div
        style={{
          color: 'white',
          textAlign: 'center',
          padding: 16,
          fontWeight: 600,
          fontSize: collapsed ? 14 : 18,
          borderBottom: '1px solid #374151',
        }}
      >
        {collapsed ? appTitle.slice(0, 1).toUpperCase() : appTitle}
      </div>
      <Menu
        theme="dark"
        mode="inline"
        selectedKeys={active ? [active.key] : []}
        items={items}
        onClick={({ key }) => {
          const target = flat.find((i) => i.key === key);
          if (target?.to) navigate(target.to);
        }}
      />
    </Sider>
  );
}
