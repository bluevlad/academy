import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function MyLecturePage() {
  return (
    <PageContainer title="내 강의실" crumbs={[{ label: '학습' }, { label: '내 강의실' }]}>
      <Alert
        type="info"
        message="수강권 기반 목록 + 수동 진도율 입력 구현 예정"
        description="GET /api/user/mylecture · PUT /{enrollmentId}/progress?progress=0-100"
      />
    </PageContainer>
  );
}
