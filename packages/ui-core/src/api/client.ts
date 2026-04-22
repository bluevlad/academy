import axios, { AxiosError, AxiosInstance, InternalAxiosRequestConfig } from 'axios';
import type { ApiResponse } from '../types';

export interface CreateClientOptions {
  baseURL: string;
  /** access token 조회 함수 — storage 또는 store 참조 */
  getAccessToken: () => string | null;
  /** 401 시 실행 — 토큰 정리 + 리다이렉트 담당 */
  onUnauthorized?: () => void;
  /** 추가 헤더 (테스트/디버그) */
  extraHeaders?: Record<string, string>;
}

/**
 * academy 표준 axios 클라이언트.
 *
 * - 요청 시 Bearer 자동 주입
 * - 401 → onUnauthorized() 호출 (앱별 처리)
 * - ApiResponse envelope 를 그대로 반환 (unwrap 은 호출부 책임)
 */
export function createApiClient(opts: CreateClientOptions): AxiosInstance {
  const client = axios.create({
    baseURL: opts.baseURL,
    timeout: 60_000,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      Accept: 'application/json',
      'X-Timezone': Intl.DateTimeFormat().resolvedOptions().timeZone,
      ...(opts.extraHeaders ?? {}),
    },
  });

  client.interceptors.request.use((config: InternalAxiosRequestConfig) => {
    const token = opts.getAccessToken();
    if (token) {
      config.headers.set('Authorization', `Bearer ${token}`);
    }
    if (config.data instanceof FormData) {
      config.headers.delete('Content-Type');
    }
    return config;
  });

  client.interceptors.response.use(
    (res) => res,
    (err: AxiosError) => {
      if (err.response?.status === 401 && opts.onUnauthorized) {
        opts.onUnauthorized();
      }
      return Promise.reject(err);
    },
  );

  return client;
}

/** ApiResponse envelope 언래핑 — data 만 꺼냄, error 면 throw. */
export function unwrap<T>(res: { data: ApiResponse<T> }): T {
  const body = res.data;
  if (body.success && body.data !== null && body.data !== undefined) {
    return body.data;
  }
  throw new ApiRequestError(body.error?.code ?? 'UNKNOWN', body.error?.message ?? 'Unknown error');
}

export class ApiRequestError extends Error {
  constructor(
    public readonly code: string,
    message: string,
  ) {
    super(message);
    this.name = 'ApiRequestError';
  }
}
