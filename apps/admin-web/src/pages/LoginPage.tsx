import { useCallback, useEffect, useRef, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Alert, Button, Card, Divider, Form, Input, Spin, Typography } from 'antd';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { useAuth } from '@academy/ui-core';
import { apiClient } from '../api/client';
import { unwrap } from '@academy/ui-core/api';

const { Title } = Typography;

interface GoogleTokenResponse {
  accessToken: string;
  refreshToken: string;
  userId: string;
  role: 'ADMIN' | 'USER';
  audience: 'admin' | 'user';
  email: string;
  name?: string;
  picture?: string;
}

const GOOGLE_CLIENT_ID = import.meta.env.VITE_GOOGLE_CLIENT_ID ?? '';

declare global {
  interface Window {
    google?: {
      accounts: {
        id: {
          initialize: (cfg: {
            client_id: string;
            callback: (res: { credential: string }) => void;
            auto_select?: boolean;
            cancel_on_tap_outside?: boolean;
          }) => void;
          renderButton: (
            parent: HTMLElement,
            options: {
              theme?: 'outline' | 'filled_blue' | 'filled_black';
              size?: 'large' | 'medium' | 'small';
              width?: number;
              text?: 'signin_with' | 'signup_with' | 'continue_with' | 'signin';
              locale?: string;
            },
          ) => void;
        };
      };
    };
  }
}

export function LoginPage() {
  const { login, setSession } = useAuth();
  const navigate = useNavigate();
  const location = useLocation() as { state?: { from?: string } };
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);
  const googleBtnRef = useRef<HTMLDivElement | null>(null);
  const initializedRef = useRef(false);
  const [googleReady, setGoogleReady] = useState(false);

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

  const handleCredential = useCallback(
    async (credential: string) => {
      setError(null);
      try {
        const res = await apiClient.post('/auth/google/verify', { idToken: credential });
        const token = unwrap<GoogleTokenResponse>({ data: res.data as never });
        setSession({
          accessToken: token.accessToken,
          refreshToken: token.refreshToken,
          userId: token.userId,
          role: token.role,
          audience: token.audience,
        });
        navigate(location.state?.from ?? '/dashboard', { replace: true });
      } catch (e) {
        setError(e instanceof Error ? e.message : 'Google 로그인 실패');
      }
    },
    [navigate, location.state?.from, setSession],
  );

  useEffect(() => {
    if (initializedRef.current || !GOOGLE_CLIENT_ID) return;

    const init = () => {
      if (!window.google?.accounts?.id || !googleBtnRef.current) return;
      initializedRef.current = true;
      window.google.accounts.id.initialize({
        client_id: GOOGLE_CLIENT_ID,
        callback: (res) => {
          void handleCredential(res.credential);
        },
        cancel_on_tap_outside: true,
      });
      window.google.accounts.id.renderButton(googleBtnRef.current, {
        theme: 'outline',
        size: 'large',
        width: 320,
        text: 'signin_with',
        locale: 'ko',
      });
      setGoogleReady(true);
    };

    if (window.google?.accounts?.id) {
      init();
      return;
    }
    const scriptId = 'google-identity-services';
    const existing = document.getElementById(scriptId) as HTMLScriptElement | null;
    if (existing) {
      existing.addEventListener('load', init, { once: true });
      return;
    }
    const script = document.createElement('script');
    script.id = scriptId;
    script.src = 'https://accounts.google.com/gsi/client';
    script.async = true;
    script.defer = true;
    script.onload = init;
    document.head.appendChild(script);
  }, [handleCredential]);

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
          <Form.Item style={{ marginBottom: 0 }}>
            <Button type="primary" htmlType="submit" block size="large" loading={submitting}>
              로그인
            </Button>
          </Form.Item>
        </Form>

        {GOOGLE_CLIENT_ID ? (
          <>
            <Divider plain style={{ marginTop: 24, marginBottom: 16 }}>
              또는
            </Divider>
            <div className="flex justify-center" style={{ minHeight: 44, alignItems: 'center' }}>
              {!googleReady && <Spin size="small" />}
              <div ref={googleBtnRef} style={{ display: googleReady ? 'block' : 'none' }} />
            </div>
            <div className="text-center text-xs text-slate-400 mt-3">
              super-admin 이메일만 Google 로그인 가능
            </div>
          </>
        ) : (
          <Alert
            type="warning"
            showIcon
            className="mt-4"
            message="Google 로그인 비활성"
            description="VITE_GOOGLE_CLIENT_ID 가 설정되지 않았습니다."
          />
        )}
      </Card>
    </div>
  );
}
