import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function MembersPage() {
  return (
    <PageContainer title="회원관리" crumbs={[{ label: '운영' }, { label: '회원관리' }]}>
      <Alert
        type="info"
        message="회원 목록 / 상세 / id_admin 관리 구현 예정"
        description="GET /api/member/list · GET /api/member/{id} · id_admin (Sprint 1-1b) + TB_MA_MEMBER 통합 테이블 (QA 단계에서 채움)"
      />
    </PageContainer>
  );
}
