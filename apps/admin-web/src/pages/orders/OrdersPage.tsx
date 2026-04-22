import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function OrdersPage() {
  return (
    <PageContainer title="수강(주문) 관리" crumbs={[{ label: '운영' }, { label: '주문' }]}>
      <Alert
        type="info"
        message="주문 목록 · 환불 승인 · 수강권 상태 관리 구현 예정"
        description="od_order / od_order_item / od_payment / en_enrollment 조회·취소·환불 (Sprint 3 테이블 활용)"
      />
    </PageContainer>
  );
}
