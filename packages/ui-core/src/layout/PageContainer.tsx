import { Breadcrumb, Typography } from 'antd';
import React from 'react';

const { Title } = Typography;

export interface Crumb {
  label: string;
  to?: string;
}

export interface PageContainerProps {
  title: string;
  crumbs?: Crumb[];
  extra?: React.ReactNode;
  children: React.ReactNode;
}

export function PageContainer({ title, crumbs, extra, children }: PageContainerProps) {
  return (
    <div style={{ background: '#ffffff', borderRadius: 8, padding: 24 }}>
      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          marginBottom: 16,
          gap: 16,
        }}
      >
        <div>
          {crumbs && crumbs.length > 0 && (
            <Breadcrumb
              items={crumbs.map((c) => ({ title: c.label, href: c.to }))}
              style={{ marginBottom: 8 }}
            />
          )}
          <Title level={3} style={{ margin: 0 }}>
            {title}
          </Title>
        </div>
        {extra}
      </div>
      {children}
    </div>
  );
}
