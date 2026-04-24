import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Badge, Button, Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { PlusOutlined, ReloadOutlined } from '@ant-design/icons';
import { useQuery } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import { myInquiries, type Inquiry } from '../../api/inquiries';

const CAT_LABELS: Record<string, string> = {
  ACADEMIC: '학사·강의',
  ORDER: '수강·결제·배송',
  SYSTEM: '시스템·기술',
  OTHER: '기타',
};
const STATE_LABELS: Record<string, { label: string; color: string }> = {
  OPEN: { label: '대기', color: 'red' },
  ANSWERED: { label: '답변완료', color: 'blue' },
  RESOLVED: { label: '해결됨', color: 'green' },
  CLOSED: { label: '종료', color: 'default' },
};

export function MyInquiriesPage() {
  const navigate = useNavigate();
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(20);

  const q = useQuery({
    queryKey: ['my-inquiries', page, size],
    queryFn: () => myInquiries(page, size),
    placeholderData: (p) => p,
  });

  const columns: ColumnsType<Inquiry> = [
    { title: '#', dataIndex: 'csSeq', key: 'csSeq', width: 70 },
    {
      title: '제목',
      dataIndex: 'inquiryTitle',
      key: 'inquiryTitle',
      ellipsis: true,
      render: (v, row) => <a onClick={() => navigate(`/support/inquiries/${row.csSeq}`)}>{v}</a>,
    },
    {
      title: '분류',
      key: 'category',
      width: 130,
      render: (_, row) => {
        const cat = row.actualCategory || row.predictedCategory;
        return cat ? <Tag>{CAT_LABELS[cat] ?? cat}</Tag> : <Tag color="default">분류 중</Tag>;
      },
    },
    {
      title: '상태',
      dataIndex: 'resolutionState',
      key: 'resolutionState',
      width: 100,
      render: (v: string) => {
        const s = STATE_LABELS[v] ?? { label: v, color: 'default' };
        return <Badge color={s.color} text={s.label} />;
      },
    },
    {
      title: '접수일',
      dataIndex: 'inquiryDate',
      key: 'inquiryDate',
      width: 110,
      render: (v: string) => v?.slice(0, 10) ?? '-',
    },
  ];

  return (
    <PageContainer
      title="내 문의 내역"
      crumbs={[{ label: '고객센터' }, { label: '내 문의' }]}
      extra={
        <Space>
          <Button icon={<ReloadOutlined />} onClick={() => q.refetch()}>
            새로고침
          </Button>
          <Button type="primary" icon={<PlusOutlined />} onClick={() => navigate('/support/inquiry')}>
            새 문의 작성
          </Button>
        </Space>
      }
    >
      <Table<Inquiry>
        rowKey="csSeq"
        columns={columns}
        dataSource={q.data?.items ?? []}
        loading={q.isFetching}
        size="middle"
        pagination={{
          current: page,
          pageSize: size,
          total: Number(q.data?.totalItems ?? 0),
          onChange: (p, s) => { setPage(p); setSize(s); },
          showSizeChanger: true,
        }}
      />
    </PageContainer>
  );
}
