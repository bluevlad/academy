import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function MockExamsPage() {
  return (
    <PageContainer title="모의고사" crumbs={[{ label: '운영' }, { label: '모의고사' }]}>
      <Alert
        type="info"
        message="모의고사 마스터 · 응시 기록 · 통계 구현 예정"
        description="ex_mock_exam / ex_mock_attempt (Sprint 5 테이블)"
      />
    </PageContainer>
  );
}
