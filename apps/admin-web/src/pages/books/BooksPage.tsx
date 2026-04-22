import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function BooksPage() {
  return (
    <PageContainer title="교재 · 배송" crumbs={[{ label: '운영' }, { label: '교재' }]}>
      <Alert
        type="info"
        message="교재 카탈로그 · 재고 · 배송 상태 구현 예정"
        description="bk_book / bk_delivery / bk_delivery_address (Sprint 4 테이블)"
      />
    </PageContainer>
  );
}
