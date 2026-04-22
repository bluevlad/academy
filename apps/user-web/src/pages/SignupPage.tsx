import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Alert, Button, Card, Form, Input, Typography } from 'antd';
import { apiClient } from '../api/client';
import type { ApiResponse } from '@academy/ui-core';

const { Title } = Typography;

interface SignupValues {
  userId: string;
  password: string;
  userNm: string;
  email?: string;
}

export function SignupPage() {
  const navigate = useNavigate();
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const onFinish = async (values: SignupValues) => {
    setError(null);
    setSubmitting(true);
    try {
      const res = await apiClient.post<ApiResponse<unknown>>('/auth/signup', values);
      if (!res.data.success) {
        throw new Error(res.data.error?.message ?? '회원가입에 실패했습니다.');
      }
      navigate('/login', { replace: true, state: { signedUp: true } });
    } catch (e) {
      setError(e instanceof Error ? e.message : '회원가입 실패');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-slate-50">
      <Card className="w-full max-w-md" style={{ boxShadow: '0 8px 24px rgba(0,0,0,0.06)' }}>
        <div className="text-center mb-6">
          <Title level={3} style={{ margin: 0 }}>
            회원가입
          </Title>
          <div className="text-slate-500 mt-2">Academy 수강생 계정 생성</div>
        </div>
        {error && <Alert type="error" message={error} className="mb-4" showIcon />}
        <Form layout="vertical" onFinish={onFinish} autoComplete="off">
          <Form.Item
            label="아이디"
            name="userId"
            rules={[
              { required: true, message: '아이디를 입력하세요' },
              { pattern: /^[A-Za-z0-9._-]{4,20}$/, message: '영문·숫자·._- 4~20자' },
            ]}
          >
            <Input size="large" />
          </Form.Item>
          <Form.Item
            label="비밀번호 (8자 이상)"
            name="password"
            rules={[
              { required: true, message: '비밀번호를 입력하세요' },
              { min: 8, message: '8자 이상' },
            ]}
            hasFeedback
          >
            <Input.Password size="large" />
          </Form.Item>
          <Form.Item
            label="비밀번호 확인"
            name="passwordConfirm"
            dependencies={['password']}
            hasFeedback
            rules={[
              { required: true, message: '비밀번호 확인을 입력하세요' },
              ({ getFieldValue }) => ({
                validator(_, v) {
                  if (!v || getFieldValue('password') === v) return Promise.resolve();
                  return Promise.reject(new Error('비밀번호가 일치하지 않습니다'));
                },
              }),
            ]}
          >
            <Input.Password size="large" />
          </Form.Item>
          <Form.Item
            label="이름"
            name="userNm"
            rules={[{ required: true, message: '이름을 입력하세요' }]}
          >
            <Input size="large" />
          </Form.Item>
          <Form.Item label="이메일 (선택)" name="email" rules={[{ type: 'email', message: '이메일 형식이 올바르지 않습니다' }]}>
            <Input size="large" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" block size="large" loading={submitting}>
              가입
            </Button>
          </Form.Item>
        </Form>
        <div className="text-center text-sm text-slate-500">
          이미 계정이 있으신가요? <Link to="/login">로그인</Link>
        </div>
      </Card>
    </div>
  );
}
