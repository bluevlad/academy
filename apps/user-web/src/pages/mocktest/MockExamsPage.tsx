import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function MockExamsPage() {
  return (
    <PageContainer title="모의고사" crumbs={[{ label: '학습' }, { label: '모의고사' }]}>
      <Alert
        type="info"
        message="공개 모의고사 목록 + 신청(멱등) + 응시 + 성적 조회"
        description="GET /api/user/mocktest/exams · POST /register · POST /submit · GET /attempts"
      />
    </PageContainer>
  );
}
