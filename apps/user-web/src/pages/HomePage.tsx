import { Link } from 'react-router-dom';
import { Card, Col, Row, Typography } from 'antd';
import { BookOutlined, PlayCircleOutlined, FileTextOutlined } from '@ant-design/icons';
import { PageContainer, useAuth } from '@academy/ui-core';

const { Title, Paragraph } = Typography;

export function HomePage() {
  const { user } = useAuth();
  return (
    <PageContainer title={`안녕하세요, ${user?.userId ?? ''}님`}>
      <Paragraph className="text-slate-500">원하는 강의를 찾거나 내 학습 현황을 확인하세요.</Paragraph>
      <Row gutter={[16, 16]}>
        <Col xs={24} md={8}>
          <Link to="/lectures">
            <Card hoverable>
              <BookOutlined style={{ fontSize: 28, color: '#3b82f6' }} />
              <Title level={4} className="!mt-2">강의 둘러보기</Title>
              <Paragraph className="text-slate-500 !mb-0">카테고리·교수별로 검색</Paragraph>
            </Card>
          </Link>
        </Col>
        <Col xs={24} md={8}>
          <Link to="/mypage/mylecture">
            <Card hoverable>
              <PlayCircleOutlined style={{ fontSize: 28, color: '#3b82f6' }} />
              <Title level={4} className="!mt-2">내 강의실</Title>
              <Paragraph className="text-slate-500 !mb-0">수강 중인 강의 + 진도</Paragraph>
            </Card>
          </Link>
        </Col>
        <Col xs={24} md={8}>
          <Link to="/mocktest">
            <Card hoverable>
              <FileTextOutlined style={{ fontSize: 28, color: '#3b82f6' }} />
              <Title level={4} className="!mt-2">모의고사</Title>
              <Paragraph className="text-slate-500 !mb-0">신청 · 응시 · 성적</Paragraph>
            </Card>
          </Link>
        </Col>
      </Row>
    </PageContainer>
  );
}
