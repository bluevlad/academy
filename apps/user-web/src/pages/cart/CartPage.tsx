import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function CartPage() {
  return (
    <PageContainer title="장바구니" crumbs={[{ label: '마이' }, { label: '장바구니' }]}>
      <Alert
        type="info"
        message="장바구니 목록 + 주문 생성(from-cart) + 결제 mock 구현 예정"
        description="/api/user/cart · /api/user/order/from-cart · /api/user/payment/mock"
      />
    </PageContainer>
  );
}
