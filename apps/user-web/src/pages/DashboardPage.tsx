import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Badge, Button, Card, Col, Row, Space, Tag, Typography } from 'antd';
import {
  ApiOutlined,
  BookOutlined,
  CreditCardOutlined,
  DashboardOutlined,
  FileTextOutlined,
  LoginOutlined,
  RobotOutlined,
  ToolOutlined,
} from '@ant-design/icons';
import { MermaidDiagram } from '../components/MermaidDiagram';
import { fetchDashboardSummary, type DashboardSummary } from '../api/dashboard';

const { Title, Paragraph, Text } = Typography;

const ADMIN_URL = import.meta.env.VITE_ACADEMY_ADMIN_URL ?? '/admin/';
const SWAGGER_URL = '/swagger-ui/index.html';

const ARCHITECTURE = `flowchart LR
  visitor["방문자<br/>academy.unmong.com"]
  nginx{{"nginx<br/>SSL · reverse proxy"}}
  user["user-web<br/>React 19 · Vite"]
  admin["admin-web<br/>/admin"]
  swagger["swagger-ui"]
  backend["backend<br/>Spring Boot 3.2"]
  db[("MariaDB<br/>acm_basic")]
  redis[("Redis<br/>cache · refresh-token")]
  agent["academy-agent<br/>FastAPI · Ollama qwen2.5:7b"]
  visitor --> nginx
  nginx -->|"/"| user
  nginx -->|"/admin"| admin
  nginx -->|"/swagger-ui"| swagger
  user -->|"/api/**"| backend
  admin -->|"/api/admin/**"| backend
  backend --- db
  backend --- redis
  backend -->|"분류·임베딩"| agent
  agent --- redis
  classDef svc fill:#eff6ff,stroke:#3b82f6,stroke-width:1.5px,color:#0f172a
  classDef store fill:#fef3c7,stroke:#d97706,color:#78350f
  classDef edge fill:#f1f5f9,stroke:#64748b,color:#0f172a
  class user,admin,swagger,backend,agent svc
  class db,redis store
  class visitor,nginx edge`;

const FLOW = `flowchart LR
  s1["① 학생 1:1 문의 작성<br/>tb_inquiry INSERT"]
  s2["② AI 자동 분류<br/>4분류 (qwen2.5:7b)"]
  s3["③ 운영자 응대 콘솔<br/>답변 · 재배정"]
  s4["④ 학습 데이터 누적<br/>tb_inquiry_train"]
  s1 --> s2 --> s3 --> s4
  s4 -.재학습.-> s2
  classDef step fill:#ecfdf5,stroke:#10b981,color:#064e3b
  class s1,s2,s3,s4 step`;

const TECH_STACK = [
  { group: 'Backend',  items: ['Java 17', 'Spring Boot 3.2', 'MyBatis 3.0', 'Flyway', 'JJWT'] },
  { group: 'DB · 캐시', items: ['MariaDB 10.x', 'Redis 7'] },
  { group: 'Frontend', items: ['React 19', 'Vite', 'Ant Design 6', 'TanStack Query'] },
  { group: 'AI Agent', items: ['Python · FastAPI', 'Ollama', 'qwen2.5:7b', 'ChromaDB'] },
  { group: '인프라',    items: ['Docker Compose', 'GitHub Actions', 'self-hosted runner'] },
];

const FEATURES = [
  {
    icon: <BookOutlined />,
    title: '수강 포털',
    description: '강의 검색·결제·진도 관리',
    href: '/lectures',
    color: '#3b82f6',
  },
  {
    icon: <ToolOutlined />,
    title: '관리자 콘솔',
    description: '회원·강의·강사·도서·쿠폰',
    href: ADMIN_URL,
    color: '#0ea5e9',
    external: true,
  },
  {
    icon: <FileTextOutlined />,
    title: '시험 · 모의고사',
    description: '신청 · 응시 · 채점 · 성적',
    href: '/mocktest',
    color: '#8b5cf6',
  },
  {
    icon: <CreditCardOutlined />,
    title: '주문 · 매출 · 쿠폰',
    description: '결제 흐름과 운영 통계',
    href: ADMIN_URL,
    color: '#f59e0b',
    external: true,
  },
  {
    icon: <RobotOutlined />,
    title: '1:1 문의 AI 자동분류',
    description: 'legacy 5,430건 + 신규 인입 통합',
    href: '/support/inquiry',
    color: '#10b981',
  },
  {
    icon: <ApiOutlined />,
    title: 'API 문서 (Swagger)',
    description: '전체 endpoint · 스키마 둘러보기',
    href: SWAGGER_URL,
    color: '#64748b',
    external: true,
  },
];

function pct(value: number | null, fractionDigits = 1): string {
  if (value == null || isNaN(value)) return '—';
  return `${(value * 100).toFixed(fractionDigits)}%`;
}

function deltaText(value: number | null, inverted: boolean): { text: string; color: string } {
  if (value == null) return { text: '', color: '#64748b' };
  const positive = inverted ? value < 0 : value > 0;
  const sign = value > 0 ? '+' : '';
  return {
    text: `${sign}${value.toFixed(1)}% (전월 대비)`,
    color: positive ? '#16a34a' : '#64748b',
  };
}

export function DashboardPage() {
  const [summary, setSummary] = useState<DashboardSummary | null>(null);

  useEffect(() => {
    let cancelled = false;
    fetchDashboardSummary().then((s) => { if (!cancelled) setSummary(s); }).catch(() => {});
    return () => { cancelled = true; };
  }, []);

  return (
    <div style={{ background: '#ffffff', minHeight: '100vh' }}>
      {/* ==================== Hero ==================== */}
      <section
        style={{
          padding: '80px 24px 56px',
          background: 'linear-gradient(180deg, #f8fafc 0%, #ffffff 100%)',
          borderBottom: '1px solid #e2e8f0',
        }}
      >
        <div style={{ maxWidth: 1100, margin: '0 auto' }}>
          <Tag color="blue" style={{ marginBottom: 12 }}>Portfolio · Academy</Tag>
          <Title level={1} style={{ margin: 0, fontSize: 40, lineHeight: 1.2 }}>
            Academy
          </Title>
          <Paragraph
            style={{ fontSize: 18, color: '#475569', marginTop: 12, marginBottom: 28, maxWidth: 720 }}
          >
            학원 운영·학습·1:1 문의 AI 자동분류를 통합한 학원 관리 시스템.
            Spring Boot · React · MariaDB · Ollama 기반.
          </Paragraph>
          <Space size={12} wrap>
            <Link to="/login">
              <Button type="primary" size="large" icon={<LoginOutlined />}>학생 로그인</Button>
            </Link>
            <Button size="large" icon={<DashboardOutlined />} href={ADMIN_URL}>
              운영자 콘솔
            </Button>
            <Button size="large" icon={<ApiOutlined />} href={SWAGGER_URL} target="_blank">
              API 문서
            </Button>
          </Space>
        </div>
      </section>

      {/* ==================== 기능 카드 ==================== */}
      <section style={{ padding: '56px 24px', maxWidth: 1100, margin: '0 auto' }}>
        <Title level={3} style={{ marginBottom: 24 }}>주요 기능</Title>
        <Row gutter={[20, 20]}>
          {FEATURES.map((f) => {
            const card = (
              <Card hoverable style={{ height: '100%' }}>
                <Space direction="vertical" size={6} style={{ width: '100%' }}>
                  <span style={{ fontSize: 28, color: f.color }}>{f.icon}</span>
                  <Title level={5} style={{ margin: 0 }}>{f.title}</Title>
                  <Text type="secondary" style={{ fontSize: 13 }}>{f.description}</Text>
                </Space>
              </Card>
            );
            return (
              <Col key={f.title} xs={24} sm={12} md={8}>
                {f.external ? (
                  <a href={f.href} target={f.href.startsWith('http') ? '_blank' : undefined} rel="noreferrer">
                    {card}
                  </a>
                ) : (
                  <Link to={f.href}>{card}</Link>
                )}
              </Col>
            );
          })}
        </Row>
      </section>

      {/* ==================== 아키텍처 ==================== */}
      <section style={{ padding: '56px 24px', background: '#f8fafc', borderTop: '1px solid #e2e8f0' }}>
        <div style={{ maxWidth: 1100, margin: '0 auto' }}>
          <Title level={3} style={{ marginBottom: 8 }}>시스템 아키텍처</Title>
          <Paragraph type="secondary" style={{ marginBottom: 24 }}>
            nginx 가 academy.unmong.com 의 path 별로 user-web · admin-web · backend · swagger 로 분기.
            backend 는 MariaDB·Redis·academy-agent (Ollama) 와 연동.
          </Paragraph>
          <Card>
            <MermaidDiagram chart={ARCHITECTURE} />
          </Card>
        </div>
      </section>

      {/* ==================== 서비스 흐름 ==================== */}
      <section style={{ padding: '56px 24px', maxWidth: 1100, margin: '0 auto' }}>
        <Title level={3} style={{ marginBottom: 8 }}>1:1 문의 AI 자동분류 흐름</Title>
        <Paragraph type="secondary" style={{ marginBottom: 24 }}>
          legacy <code>TB_BOARD_CS</code> 5,430건은 read-only 아카이브로 보존,
          신규 인입은 <code>tb_inquiry</code> 로. 운영자 정정 라벨은 학습 데이터셋에 누적.
        </Paragraph>
        <Card>
          <MermaidDiagram chart={FLOW} />
        </Card>
      </section>

      {/* ==================== 기술 스택 ==================== */}
      <section style={{ padding: '56px 24px', background: '#f8fafc', borderTop: '1px solid #e2e8f0' }}>
        <div style={{ maxWidth: 1100, margin: '0 auto' }}>
          <Title level={3} style={{ marginBottom: 24 }}>기술 스택</Title>
          <Row gutter={[20, 20]}>
            {TECH_STACK.map((g) => (
              <Col key={g.group} xs={24} sm={12} md={8}>
                <Card size="small" title={g.group}>
                  <Space size={[8, 8]} wrap>
                    {g.items.map((it) => (
                      <Tag key={it} style={{ fontSize: 12 }}>{it}</Tag>
                    ))}
                  </Space>
                </Card>
              </Col>
            ))}
          </Row>
        </div>
      </section>

      {/* ==================== 라이브 시그널 ==================== */}
      <section style={{ padding: '40px 24px 80px', maxWidth: 1100, margin: '0 auto' }}>
        <Title level={5} style={{ marginBottom: 16, color: '#475569' }}>운영 신호</Title>
        <Row gutter={[16, 16]}>
          {summary?.metrics.map((m) => {
            const d = deltaText(m.deltaPct, m.sentimentInverted);
            return (
              <Col key={m.key} xs={24} sm={12} md={8}>
                <Card size="small">
                  <Text type="secondary" style={{ fontSize: 12 }}>{m.label}</Text>
                  <div style={{ fontSize: 28, fontWeight: 700, color: '#0f172a', lineHeight: 1.2 }}>
                    {m.valueRatio != null ? pct(m.valueRatio) : (d.text || '—')}
                  </div>
                  {m.valueRatio != null && d.text && (
                    <Text style={{ fontSize: 12, color: d.color }}>{d.text}</Text>
                  )}
                </Card>
              </Col>
            );
          })}
          <Col xs={24} sm={12} md={8}>
            <Card size="small">
              <Text type="secondary" style={{ fontSize: 12 }}>시스템 상태</Text>
              <Space size={12} style={{ marginTop: 8 }} wrap>
                <Badge status={summary?.uptime.backend ? 'success' : 'default'} text="backend" />
                <Badge status={summary?.uptime.agent ? 'success' : 'default'} text="agent" />
                <Badge status={summary?.uptime.db ? 'success' : 'default'} text="db" />
              </Space>
            </Card>
          </Col>
        </Row>
        {summary && (
          <Text type="secondary" style={{ fontSize: 11, marginTop: 8, display: 'block' }}>
            기준 월: {summary.ymBasis} · 절대값은 비공개, 비율(%)만 표시
          </Text>
        )}
      </section>
    </div>
  );
}
