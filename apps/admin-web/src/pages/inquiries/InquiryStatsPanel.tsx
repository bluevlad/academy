import { useMemo, useState } from 'react';
import { Card, Col, DatePicker, Empty, Progress, Row, Space, Statistic, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { useQuery } from '@tanstack/react-query';
import { ArrowDownOutlined, ArrowUpOutlined } from '@ant-design/icons';
import dayjs, { type Dayjs } from 'dayjs';
import { getMonthlyStats, type CategoryStat } from '../../api/inquiries';

const CAT_LABELS: Record<string, string> = {
  ACADEMIC: '학사·강의',
  ORDER: '수강·결제·배송',
  SYSTEM: '시스템·기술',
  OTHER: '기타',
};
const CAT_COLORS: Record<string, string> = {
  ACADEMIC: 'geekblue',
  ORDER: 'orange',
  SYSTEM: 'purple',
  OTHER: 'default',
};

export function InquiryStatsPanel() {
  const [month, setMonth] = useState<Dayjs>(() => dayjs().startOf('month'));
  const ym = month.format('YYYY-MM');

  const q = useQuery({
    queryKey: ['inquiry-stats', ym],
    queryFn: () => getMonthlyStats(ym),
    placeholderData: (p) => p,
  });

  const stats = q.data;
  const resRate = stats?.resolutionRate;
  const aiRate = stats?.aiAccuracyRate;

  const catCols: ColumnsType<CategoryStat> = useMemo(
    () => [
      {
        title: '카테고리',
        dataIndex: 'category',
        render: (v: string) => (
          <Tag color={CAT_COLORS[v] ?? 'default'}>{CAT_LABELS[v] ?? v}</Tag>
        ),
      },
      { title: '총건수', dataIndex: 'totalCount', align: 'right', width: 90 },
      { title: '해결', dataIndex: 'resolvedCount', align: 'right', width: 90 },
      {
        title: '전월 대비',
        dataIndex: 'momDeltaPct',
        align: 'right',
        width: 130,
        render: (v: number | null, row) => {
          if (v == null) return <span style={{ color: '#94a3b8' }}>-</span>;
          const icon = row.decreasing ? <ArrowDownOutlined /> : <ArrowUpOutlined />;
          const color = row.decreasing ? '#16a34a' : '#dc2626';
          return <span style={{ color }}>{icon} {Math.abs(Number(v)).toFixed(1)}%</span>;
        },
      },
      {
        title: '만족도',
        dataIndex: 'avgSatisfaction',
        align: 'right',
        width: 90,
        render: (v) => (v == null ? '-' : Number(v).toFixed(2)),
      },
    ],
    [],
  );

  return (
    <Card
      size="small"
      style={{ marginBottom: 16 }}
      title={
        <Space>
          <span>월간 통계</span>
          <DatePicker
            picker="month"
            value={month}
            onChange={(v) => v && setMonth(v)}
            format="YYYY-MM"
            allowClear={false}
            size="small"
          />
        </Space>
      }
    >
      <Row gutter={16} style={{ marginBottom: 16 }}>
        <Col xs={24} sm={12} lg={6}>
          <Statistic title="총 문의" value={stats?.totalInquiries ?? 0} suffix="건" />
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <Statistic title="해결 완료" value={stats?.resolvedCount ?? 0} suffix="건" />
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <Space direction="vertical" size={2} style={{ width: '100%' }}>
            <span style={{ fontSize: 12, color: '#64748b' }}>해결률</span>
            <Progress
              percent={resRate != null ? Math.round(Number(resRate) * 100) : 0}
              format={(p) => `${p}%`}
              size="small"
            />
          </Space>
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <Space direction="vertical" size={2} style={{ width: '100%' }}>
            <span style={{ fontSize: 12, color: '#64748b' }}>
              AI 분류 정확도
              <span style={{ color: '#94a3b8', marginLeft: 4 }}>
                (재배정 {stats?.totalRoutingChanges ?? 0}건 중 오분류 {stats?.aiErrorCount ?? 0})
              </span>
            </span>
            <Progress
              percent={aiRate != null ? Math.round(Number(aiRate) * 100) : 0}
              format={(p) => `${p}%`}
              size="small"
              status={aiRate != null && Number(aiRate) < 0.7 ? 'exception' : 'normal'}
            />
          </Space>
        </Col>
      </Row>

      <Row gutter={16}>
        <Col xs={24} lg={14}>
          <h4 style={{ marginBottom: 8 }}>카테고리별 분포</h4>
          {stats && stats.categories.length > 0 ? (
            <Table
              rowKey="category"
              columns={catCols}
              dataSource={stats.categories}
              size="small"
              pagination={false}
            />
          ) : (
            <Empty description="이번 월 문의 없음" />
          )}
        </Col>
        <Col xs={24} lg={10}>
          <h4 style={{ marginBottom: 8 }}>장기 미해결 top 5</h4>
          {stats && stats.unresolvedTop.length > 0 ? (
            <Table
              rowKey="csSeq"
              size="small"
              pagination={false}
              columns={[
                { title: '#', dataIndex: 'csSeq', width: 60 },
                { title: '제목', dataIndex: 'inquiryTitle', ellipsis: true },
                {
                  title: '접수일',
                  dataIndex: 'inquiryDate',
                  width: 100,
                  render: (v: string) => v?.slice(0, 10) ?? '-',
                },
              ]}
              dataSource={stats.unresolvedTop}
            />
          ) : (
            <Empty description="미해결 문의 없음" />
          )}
        </Col>
      </Row>
    </Card>
  );
}
