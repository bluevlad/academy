import { useMemo, useState } from 'react';
import {
  Alert,
  Badge,
  Button,
  Descriptions,
  Drawer,
  Form,
  Input,
  Progress,
  Select,
  Space,
  Table,
  Tag,
  message,
} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { ReloadOutlined, SearchOutlined, ThunderboltOutlined } from '@ant-design/icons';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import { InquiryStatsPanel } from './InquiryStatsPanel';
import {
  classifyNow,
  getInquiryDetail,
  listInquiries,
  postAnswer,
  reassignInquiry,
  type Inquiry,
  type InquirySearch,
  type ResolutionState,
} from '../../api/inquiries';

const CATEGORY_LABELS: Record<string, string> = {
  ACADEMIC: '학사·강의',
  ORDER: '수강·결제·배송',
  SYSTEM: '시스템·기술',
  OTHER: '기타',
};
const CATEGORY_COLORS: Record<string, string> = {
  ACADEMIC: 'geekblue',
  ORDER: 'orange',
  SYSTEM: 'purple',
  OTHER: 'default',
};
const STATE_COLORS: Record<string, string> = {
  OPEN: 'red',
  ANSWERED: 'blue',
  RESOLVED: 'green',
  CLOSED: 'default',
};

export function InquiriesPage() {
  const qc = useQueryClient();
  const [searchForm] = Form.useForm<InquirySearch>();
  const [answerForm] = Form.useForm<{ answerBody: string; resolutionState: ResolutionState }>();
  const [reassignForm] = Form.useForm<{ toCategory: string; toUser: string; reason?: string; isAiError?: boolean }>();

  const [filter, setFilter] = useState<InquirySearch>({});
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(20);
  const [detailTarget, setDetailTarget] = useState<string | null>(null);
  const [reassignOpen, setReassignOpen] = useState(false);

  const params: InquirySearch = useMemo(
    () => ({ ...filter, page, size }),
    [filter, page, size],
  );

  const list = useQuery({
    queryKey: ['inquiries', params],
    queryFn: () => listInquiries(params),
    placeholderData: (p) => p,
  });

  const detail = useQuery({
    queryKey: ['inquiry-detail', detailTarget],
    queryFn: () => (detailTarget ? getInquiryDetail(detailTarget) : Promise.resolve(null)),
    enabled: detailTarget !== null,
  });

  const invalidate = () => {
    qc.invalidateQueries({ queryKey: ['inquiries'] });
    if (detailTarget) qc.invalidateQueries({ queryKey: ['inquiry-detail', detailTarget] });
  };

  const classifyMut = useMutation({
    mutationFn: classifyNow,
    onSuccess: () => { message.success('AI 분류 완료'); invalidate(); },
    onError: (e: Error) => message.error('AI 분류 실패: ' + e.message),
  });

  const answerMut = useMutation({
    mutationFn: (v: { csSeq: string; answerBody: string; state?: ResolutionState }) =>
      postAnswer(v.csSeq, v.answerBody, v.state),
    onSuccess: () => { message.success('답변 저장'); answerForm.resetFields(['answerBody']); invalidate(); },
    onError: (e: Error) => message.error(e.message),
  });

  const reassignMut = useMutation({
    mutationFn: (v: { csSeq: string; toCategory: string; toUser: string; reason?: string; isAiError?: boolean }) =>
      reassignInquiry(v.csSeq, v),
    onSuccess: () => { message.success('재배정 완료'); setReassignOpen(false); reassignForm.resetFields(); invalidate(); },
    onError: (e: Error) => message.error(e.message),
  });

  const columns: ColumnsType<Inquiry> = [
    {
      title: '#',
      dataIndex: 'csSeq',
      key: 'csSeq',
      width: 130,
      render: (v: string, row) => (
        <Space size={4}>
          <Tag color={row.source === 'L' ? 'default' : 'green'} style={{ marginInlineEnd: 0 }}>
            {row.source === 'L' ? 'Legacy' : 'New'}
          </Tag>
          <span>{v}</span>
        </Space>
      ),
    },
    {
      title: '제목',
      dataIndex: 'inquiryTitle',
      key: 'inquiryTitle',
      ellipsis: true,
      render: (v, row) => <a onClick={() => setDetailTarget(row.csSeq)}>{v}</a>,
    },
    {
      title: '작성자',
      dataIndex: 'inquiryName',
      key: 'inquiryName',
      width: 110,
    },
    {
      title: '카테고리',
      key: 'category',
      width: 160,
      render: (_, row) => {
        const cat = row.actualCategory || row.predictedCategory;
        if (!cat) return <Tag color="default">미분류</Tag>;
        const conf = row.predictedConfidence ? Number(row.predictedConfidence) : null;
        return (
          <Space size={4} direction="vertical" style={{ lineHeight: 1.3 }}>
            <Tag color={CATEGORY_COLORS[cat] ?? 'default'}>{CATEGORY_LABELS[cat] ?? cat}</Tag>
            {row.actualCategory ? (
              <span style={{ fontSize: 11, color: '#64748b' }}>(확정)</span>
            ) : conf !== null ? (
              <span style={{ fontSize: 11, color: '#64748b' }}>AI {(conf * 100).toFixed(0)}%</span>
            ) : null}
          </Space>
        );
      },
    },
    {
      title: '상태',
      dataIndex: 'resolutionState',
      key: 'resolutionState',
      width: 100,
      render: (v: string) => <Badge color={STATE_COLORS[v] ?? 'default'} text={v} />,
    },
    {
      title: '담당자',
      dataIndex: 'assignedTo',
      key: 'assignedTo',
      width: 110,
      render: (v) => v || '-',
    },
    {
      title: '접수일',
      dataIndex: 'inquiryDate',
      key: 'inquiryDate',
      width: 120,
      render: (v: string) => (v ? v.slice(0, 10) : '-'),
    },
  ];

  const target = detail.data ?? null;
  const currentCategory = target?.actualCategory || target?.predictedCategory;

  return (
    <PageContainer
      title="1:1 문의 응대"
      crumbs={[{ label: '고객센터' }, { label: '1:1 문의 응대' }]}
      extra={
        <Space>
          <Button icon={<ReloadOutlined />} onClick={() => list.refetch()}>
            새로고침
          </Button>
        </Space>
      }
    >
      <InquiryStatsPanel />
      <Alert
        type="info"
        showIcon
        style={{ marginBottom: 16 }}
        message="AI 분류는 agent (qwen2.5:7b) 가 처리. 운영자가 재배정하면 학습 데이터에 누적."
        description="데이터가 없을 경우 Phase A ETL 을 먼저 실행하세요: scripts/data-migrate/board/"
      />

      <Form<InquirySearch>
        form={searchForm}
        layout="inline"
        onFinish={(v) => { setFilter(v); setPage(1); }}
        style={{ marginBottom: 16, rowGap: 8 }}
      >
        <Form.Item name="keyword" label="키워드">
          <Input allowClear placeholder="제목·본문" style={{ width: 220 }} />
        </Form.Item>
        <Form.Item name="category" label="카테고리" initialValue="">
          <Select
            style={{ width: 170 }}
            options={[
              { value: '', label: '전체' },
              { value: 'ACADEMIC', label: '학사·강의' },
              { value: 'ORDER', label: '수강·결제·배송' },
              { value: 'SYSTEM', label: '시스템·기술' },
              { value: 'OTHER', label: '기타' },
            ]}
          />
        </Form.Item>
        <Form.Item name="resolutionState" label="상태" initialValue="">
          <Select
            style={{ width: 140 }}
            options={[
              { value: '', label: '전체' },
              { value: 'OPEN', label: '대기 (OPEN)' },
              { value: 'ANSWERED', label: '답변 (ANSWERED)' },
              { value: 'RESOLVED', label: '해결 (RESOLVED)' },
              { value: 'CLOSED', label: '종료 (CLOSED)' },
            ]}
          />
        </Form.Item>
        <Form.Item name="assignedTo" label="담당자">
          <Input allowClear placeholder="user_id" style={{ width: 150 }} />
        </Form.Item>
        <Form.Item>
          <Space>
            <Button type="primary" htmlType="submit" icon={<SearchOutlined />}>검색</Button>
            <Button onClick={() => { searchForm.resetFields(); setFilter({}); setPage(1); }}>초기화</Button>
          </Space>
        </Form.Item>
      </Form>

      <Table<Inquiry>
        rowKey="csSeq"
        columns={columns}
        dataSource={list.data?.items ?? []}
        loading={list.isFetching}
        scroll={{ x: 1100 }}
        size="middle"
        pagination={{
          current: page,
          pageSize: size,
          total: Number(list.data?.totalItems ?? 0),
          showSizeChanger: true,
          showTotal: (t) => `총 ${t.toLocaleString()}건`,
          onChange: (p, s) => { setPage(p); setSize(s); },
        }}
      />

      <Drawer
        title={target ? `문의 #${target.csSeq} — ${target.inquiryTitle}` : '문의 상세'}
        width={720}
        open={detailTarget !== null}
        onClose={() => setDetailTarget(null)}
        destroyOnClose
        loading={detail.isLoading}
        extra={
          target && target.source === 'N' && (
            <Space>
              <Button
                icon={<ThunderboltOutlined />}
                loading={classifyMut.isPending}
                onClick={() => classifyMut.mutate(target.csSeq)}
              >
                AI 재분류
              </Button>
              <Button onClick={() => setReassignOpen(true)}>재배정</Button>
            </Space>
          )
        }
      >
        {target && (
          <>
            <Descriptions column={2} bordered size="small" style={{ marginBottom: 16 }}>
              <Descriptions.Item label="작성자">{target.inquiryName} ({target.inquiryUserId})</Descriptions.Item>
              <Descriptions.Item label="접수일">{target.inquiryDate?.slice(0, 19).replace('T', ' ')}</Descriptions.Item>
              <Descriptions.Item label="AI 분류">
                {target.predictedCategory ? (
                  <Space>
                    <Tag color={CATEGORY_COLORS[target.predictedCategory] ?? 'default'}>
                      {CATEGORY_LABELS[target.predictedCategory] ?? target.predictedCategory}
                    </Tag>
                    {target.predictedConfidence && (
                      <Progress
                        percent={Math.round(Number(target.predictedConfidence) * 100)}
                        size="small"
                        steps={10}
                        style={{ minWidth: 180 }}
                      />
                    )}
                  </Space>
                ) : '미분류'}
              </Descriptions.Item>
              <Descriptions.Item label="확정 분류">
                {target.actualCategory ? (
                  <Tag color={CATEGORY_COLORS[target.actualCategory] ?? 'default'}>
                    {CATEGORY_LABELS[target.actualCategory] ?? target.actualCategory}
                  </Tag>
                ) : '-'}
              </Descriptions.Item>
              <Descriptions.Item label="담당자">{target.assignedTo || '-'}</Descriptions.Item>
              <Descriptions.Item label="재배정 횟수">{target.rerouteCount}</Descriptions.Item>
              <Descriptions.Item label="상태" span={2}>
                <Badge color={STATE_COLORS[target.resolutionState] ?? 'default'} text={target.resolutionState} />
              </Descriptions.Item>
              <Descriptions.Item label="모델" span={2}>
                {target.classifiedByModel ? `${target.classifiedByModel} @ ${target.classifiedAt?.slice(0, 19).replace('T', ' ')}` : '-'}
              </Descriptions.Item>
            </Descriptions>

            <h4>본문</h4>
            <div
              style={{
                padding: 12,
                background: '#f8fafc',
                borderRadius: 6,
                whiteSpace: 'pre-wrap',
                wordBreak: 'break-word',
                maxHeight: 300,
                overflowY: 'auto',
              }}
              dangerouslySetInnerHTML={{ __html: target.body || '(본문 없음)' }}
            />

            {target.answerBody && (
              <>
                <h4 style={{ marginTop: 20 }}>기존 답변</h4>
                <div
                  style={{
                    padding: 12,
                    background: '#eff6ff',
                    borderRadius: 6,
                    whiteSpace: 'pre-wrap',
                    wordBreak: 'break-word',
                  }}
                  dangerouslySetInnerHTML={{ __html: target.answerBody }}
                />
              </>
            )}

            {target.source === 'N' ? (
              <>
                <h4 style={{ marginTop: 20 }}>답변 {target.answerBody ? '수정' : '등록'}</h4>
                <Form
                  form={answerForm}
                  layout="vertical"
                  onFinish={(v) =>
                    answerMut.mutate({
                      csSeq: target.csSeq,
                      answerBody: v.answerBody,
                      state: v.resolutionState,
                    })
                  }
                >
                  <Form.Item name="answerBody" rules={[{ required: true, message: '답변 본문을 입력하세요.' }]}>
                    <Input.TextArea rows={6} placeholder="답변 내용 (Markdown/HTML)" />
                  </Form.Item>
                  <Form.Item name="resolutionState" label="처리 상태" initialValue="ANSWERED">
                    <Select
                      style={{ width: 200 }}
                      options={[
                        { value: 'ANSWERED', label: '답변 완료' },
                        { value: 'RESOLVED', label: '해결됨' },
                        { value: 'CLOSED', label: '종료' },
                      ]}
                    />
                  </Form.Item>
                  <Button type="primary" htmlType="submit" loading={answerMut.isPending}>
                    답변 저장
                  </Button>
                </Form>
              </>
            ) : (
              <Alert
                type="warning"
                showIcon
                style={{ marginTop: 20 }}
                message="Legacy 아카이브 — 읽기 전용"
                description="원본 TB_BOARD_CS 데이터입니다. 답변·재배정·재분류는 신규 문의에서만 가능합니다."
              />
            )}
          </>
        )}
      </Drawer>

      <Drawer
        title="카테고리·담당자 재배정"
        width={420}
        open={reassignOpen}
        onClose={() => setReassignOpen(false)}
        destroyOnClose
      >
        {target && (
          <Form
            form={reassignForm}
            layout="vertical"
            initialValues={{ toCategory: currentCategory ?? 'OTHER', isAiError: true }}
            onFinish={(v) => reassignMut.mutate({ csSeq: target.csSeq, ...v })}
          >
            <Form.Item name="toCategory" label="새 카테고리" rules={[{ required: true }]}>
              <Select
                options={[
                  { value: 'ACADEMIC', label: '학사·강의' },
                  { value: 'ORDER', label: '수강·결제·배송' },
                  { value: 'SYSTEM', label: '시스템·기술' },
                  { value: 'OTHER', label: '기타' },
                ]}
              />
            </Form.Item>
            <Form.Item name="toUser" label="담당자 user_id" rules={[{ required: true }]}>
              <Input placeholder="admin" />
            </Form.Item>
            <Form.Item name="reason" label="사유">
              <Input.TextArea rows={3} />
            </Form.Item>
            <Form.Item name="isAiError" label="AI 오분류로 기록" valuePropName="checked">
              <Select
                options={[
                  { value: true, label: 'Y (학습 셋에 포함)' },
                  { value: false, label: 'N (일반 재배정)' },
                ]}
              />
            </Form.Item>
            <Button type="primary" htmlType="submit" loading={reassignMut.isPending}>
              재배정 실행
            </Button>
          </Form>
        )}
      </Drawer>
    </PageContainer>
  );
}
