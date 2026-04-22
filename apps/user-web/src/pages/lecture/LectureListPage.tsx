import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function LectureListPage() {
  return (
    <PageContainer title="강의 둘러보기" crumbs={[{ label: '학습' }, { label: '강의' }]}>
      <Alert
        type="info"
        message="강의 목록 + 필터 + pagination 구현 예정"
        description="GET /api/user/lecture?keyword=&subjectCd=&teacherId=&page=&size="
      />
    </PageContainer>
  );
}
