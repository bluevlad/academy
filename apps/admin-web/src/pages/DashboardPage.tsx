import { Card, Col, Row, Statistic } from 'antd';
import { PageContainer } from '@academy/ui-core';

export function DashboardPage() {
  return (
    <PageContainer title="대시보드" crumbs={[{ label: '대시보드' }]}>
      <Row gutter={16}>
        <Col xs={24} sm={12} lg={6}>
          <Card>
            <Statistic title="활성 회원" value={0} suffix="명" />
          </Card>
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <Card>
            <Statistic title="대기 주문" value={0} suffix="건" />
          </Card>
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <Card>
            <Statistic title="금월 매출" value={0} suffix="원" />
          </Card>
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <Card>
            <Statistic title="활성 수강권" value={0} suffix="건" />
          </Card>
        </Col>
      </Row>
      <div className="mt-6 text-slate-500">
        실제 통계 집계 API 는 Sprint 6 QA 단계에서 연결 — 현재는 골격만.
      </div>
    </PageContainer>
  );
}
