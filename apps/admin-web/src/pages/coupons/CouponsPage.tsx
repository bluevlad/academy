import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function CouponsPage() {
  return (
    <PageContainer title="포인트 · 쿠폰" crumbs={[{ label: '운영' }, { label: '포인트/쿠폰' }]}>
      <Alert
        type="info"
        message="쿠폰 발행/조회 · 마일리지 원장 열람 구현 예정"
        description="pt_coupon / pt_coupon_user / pt_mileage_ledger (Sprint 4 테이블)"
      />
    </PageContainer>
  );
}
