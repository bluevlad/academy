import { useParams } from 'react-router-dom';
import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function LectureDetailPage() {
  const { mstCode } = useParams();
  return (
    <PageContainer
      title={`강의 상세 — ${mstCode ?? ''}`}
      crumbs={[{ label: '학습' }, { label: '강의', to: '/lectures' }, { label: mstCode ?? '' }]}
    >
      <Alert
        type="info"
        message="강의 상세 + 챕터 목록 + 장바구니 담기 버튼 구현 예정"
        description={`GET /api/user/lecture/${mstCode}`}
      />
    </PageContainer>
  );
}
