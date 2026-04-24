import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Alert, Button, Card, Empty, Form, Input, List, Space, Tag, Typography, message } from 'antd';
import { useMutation } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import { createInquiry, suggestRelated, type RelatedItem } from '../../api/inquiries';

const CAT_LABELS: Record<string, string> = {
  ACADEMIC: '학사·강의',
  ORDER: '수강·결제·배송',
  SYSTEM: '시스템·기술',
  OTHER: '기타',
};

export function InquiryWritePage() {
  const navigate = useNavigate();
  const [form] = Form.useForm<{ title: string; body: string }>();
  const [draftBody, setDraftBody] = useState('');
  const [suggestions, setSuggestions] = useState<RelatedItem[]>([]);
  const [suggestLoading, setSuggestLoading] = useState(false);
  const [suggestError, setSuggestError] = useState<string | null>(null);
  const debounceRef = useRef<number | null>(null);

  const createMut = useMutation({
    mutationFn: createInquiry,
    onSuccess: (d) => {
      message.success('문의가 등록되었습니다. AI 분류 중입니다.');
      navigate(`/support/inquiries/${d.csSeq}`);
    },
    onError: (e: Error) => message.error('등록 실패: ' + e.message),
  });

  // debounced suggest — 본문이 20자 이상이고 500ms 멈춤 후 호출
  useEffect(() => {
    if (debounceRef.current) window.clearTimeout(debounceRef.current);
    if (draftBody.trim().length < 20) {
      setSuggestions([]);
      setSuggestError(null);
      return;
    }
    debounceRef.current = window.setTimeout(async () => {
      setSuggestLoading(true);
      setSuggestError(null);
      try {
        const items = await suggestRelated(draftBody);
        setSuggestions(items);
      } catch (e) {
        setSuggestError((e as Error).message);
        setSuggestions([]);
      } finally {
        setSuggestLoading(false);
      }
    }, 500);
  }, [draftBody]);

  const goToRelated = (csSeq: number) => navigate(`/support/inquiries/${csSeq}`);

  return (
    <PageContainer title="1:1 문의 작성" crumbs={[{ label: '고객센터' }, { label: '1:1 문의 작성' }]}>
      <div style={{ display: 'grid', gridTemplateColumns: '1fr 380px', gap: 20 }}>
        <div>
          <Alert
            type="info"
            showIcon
            style={{ marginBottom: 16 }}
            message="본문을 입력하면 AI 가 유사한 기존 답변을 우측에 추천합니다."
            description="이미 답변이 있으면 등록 전에 바로 확인하실 수 있어요."
          />
          <Form<{ title: string; body: string }>
            form={form}
            layout="vertical"
            onFinish={(v) => createMut.mutate(v)}
            onValuesChange={(_, all) => setDraftBody(all.body ?? '')}
          >
            <Form.Item
              name="title"
              label="제목"
              rules={[{ required: true, message: '제목을 입력하세요.' }, { max: 300 }]}
            >
              <Input placeholder="한 줄 요약" />
            </Form.Item>
            <Form.Item
              name="body"
              label="본문"
              rules={[{ required: true, message: '본문을 입력하세요.' }]}
            >
              <Input.TextArea rows={12} placeholder="상황·오류 메시지·영상 URL 등 구체적으로 작성하면 답변이 빨라집니다." />
            </Form.Item>
            <Space>
              <Button type="primary" htmlType="submit" loading={createMut.isPending}>
                등록
              </Button>
              <Button onClick={() => navigate('/support/inquiries')}>내 문의 목록</Button>
            </Space>
          </Form>
        </div>

        <div>
          <Card title="💡 관련 있는 문의" size="small" styles={{ header: { fontWeight: 600 } }}>
            {suggestLoading && <Typography.Text type="secondary">검색 중…</Typography.Text>}
            {suggestError && (
              <Typography.Text type="warning" style={{ fontSize: 12 }}>
                {suggestError}
              </Typography.Text>
            )}
            {!suggestLoading && suggestions.length === 0 && !suggestError && (
              <Empty
                description={draftBody.trim().length < 20 ? '본문을 20자 이상 입력하면 추천 시작' : '유사 문의 없음'}
                imageStyle={{ height: 40 }}
              />
            )}
            <List
              dataSource={suggestions}
              renderItem={(it) => (
                <List.Item
                  style={{ cursor: 'pointer', padding: '8px 0' }}
                  onClick={() => goToRelated(it.cs_seq)}
                >
                  <List.Item.Meta
                    title={
                      <Space size={4}>
                        <Tag color="blue">{Math.round(it.similarity * 100)}%</Tag>
                        {it.category && <Tag>{CAT_LABELS[it.category] ?? it.category}</Tag>}
                        <span>{it.title}</span>
                      </Space>
                    }
                    description={
                      it.answer_excerpt && (
                        <Typography.Paragraph ellipsis={{ rows: 2 }} style={{ fontSize: 12, marginBottom: 0 }}>
                          {it.answer_excerpt.replace(/<[^>]*>/g, '')}
                        </Typography.Paragraph>
                      )
                    }
                  />
                </List.Item>
              )}
            />
          </Card>
        </div>
      </div>
    </PageContainer>
  );
}
