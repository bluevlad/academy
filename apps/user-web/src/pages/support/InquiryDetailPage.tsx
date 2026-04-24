import { useParams, useNavigate } from 'react-router-dom';
import { Alert, Badge, Button, Card, Descriptions, Space, Tag, Typography } from 'antd';
import { useQuery } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import { myInquiryDetail } from '../../api/inquiries';

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

export function InquiryDetailPage() {
  const { csSeq } = useParams();
  const navigate = useNavigate();
  const seqNum = Number(csSeq);

  const q = useQuery({
    queryKey: ['my-inquiry', seqNum],
    queryFn: () => myInquiryDetail(seqNum),
    enabled: !isNaN(seqNum),
  });

  const d = q.data;
  const cat = d?.actualCategory || d?.predictedCategory;
  const state = d ? STATE_LABELS[d.resolutionState] ?? { label: d.resolutionState, color: 'default' } : null;

  return (
    <PageContainer
      title="문의 상세"
      crumbs={[{ label: '고객센터' }, { label: '내 문의' }, { label: csSeq ?? '' }]}
      extra={
        <Space>
          <Button onClick={() => navigate('/support/inquiries')}>목록</Button>
          <Button type="primary" onClick={() => navigate('/support/inquiry')}>
            새 문의 작성
          </Button>
        </Space>
      }
    >
      {q.isError && <Alert type="error" message="문의를 불러올 수 없습니다." />}
      {d && (
        <Card>
          <Descriptions column={2} bordered size="small" style={{ marginBottom: 16 }}>
            <Descriptions.Item label="제목" span={2}>
              {d.inquiryTitle}
            </Descriptions.Item>
            <Descriptions.Item label="접수일">{d.inquiryDate?.slice(0, 19).replace('T', ' ')}</Descriptions.Item>
            <Descriptions.Item label="상태">
              {state && <Badge color={state.color} text={state.label} />}
            </Descriptions.Item>
            <Descriptions.Item label="분류" span={2}>
              {cat ? <Tag>{CAT_LABELS[cat] ?? cat}</Tag> : <Tag color="default">분류 중 (AI 처리)</Tag>}
            </Descriptions.Item>
          </Descriptions>

          <Typography.Title level={5}>문의 내용</Typography.Title>
          <div
            style={{ padding: 12, background: '#f8fafc', borderRadius: 6, whiteSpace: 'pre-wrap' }}
            dangerouslySetInnerHTML={{ __html: d.body || '-' }}
          />

          {d.answerBody ? (
            <>
              <Typography.Title level={5} style={{ marginTop: 20 }}>답변</Typography.Title>
              <div
                style={{ padding: 12, background: '#eff6ff', borderRadius: 6, whiteSpace: 'pre-wrap' }}
                dangerouslySetInnerHTML={{ __html: d.answerBody }}
              />
              {d.answeredAt && (
                <Typography.Text type="secondary" style={{ fontSize: 12 }}>
                  {d.answeredAt.slice(0, 19).replace('T', ' ')} · {d.answeredBy ?? '-'}
                </Typography.Text>
              )}
            </>
          ) : (
            <Alert
              type="info"
              style={{ marginTop: 20 }}
              message={d.resolutionState === 'OPEN' ? '답변 대기 중입니다.' : '답변이 등록되지 않았습니다.'}
            />
          )}
        </Card>
      )}
    </PageContainer>
  );
}
