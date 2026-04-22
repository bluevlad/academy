import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Alert, Button, Card, Form, Input, Typography } from 'antd';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { useAuth } from '@academy/ui-core';

const { Title } = Typography;

export function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation() as { state?: { from?: string } };
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const onFinish = async (values: { userId: string; password: string }) => {
    setError(null);
    setSubmitting(true);
    try {
      await login(values.userId, values.password);
      navigate(location.state?.from ?? '/dashboard', { replace: true });
    } catch (e) {
      setError(e instanceof Error ? e.message : '로그인 실패');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-slate-50">
      <Card className="w-full max-w-md" style={{ boxShadow: '0 8px 24px rgba(0,0,0,0.06)' }}>
        <div className="text-center mb-6">
          <Title level={3} style={{ margin: 0 }}>
            Academy Admin
          </Title>
          <div className="text-slate-500 mt-2">관리자 로그인</div>
        </div>
        {error && <Alert type="error" message={error} className="mb-4" showIcon />}
        <Form layout="vertical" onFinish={onFinish} autoComplete="off">
          <Form.Item
            label="아이디"
            name="userId"
            rules={[{ required: true, message: '아이디를 입력하세요' }]}
          >
            <Input prefix={<UserOutlined />} autoFocus size="large" />
          </Form.Item>
          <Form.Item
            label="비밀번호"
            name="password"
            rules={[{ required: true, message: '비밀번호를 입력하세요' }]}
          >
            <Input.Password prefix={<LockOutlined />} size="large" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" block size="large" loading={submitting}>
              로그인
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
}
