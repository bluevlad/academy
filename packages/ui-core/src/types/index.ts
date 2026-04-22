/**
 * academy 공용 타입 (ApiResponse envelope — ADR-003 대응).
 */
export interface ApiError {
  code: string;
  message: string;
  details?: Record<string, unknown> | null;
}

export interface ApiResponse<T> {
  success: boolean;
  data: T | null;
  error: ApiError | null;
  retMsg: string;
  timestamp: string;
}

export interface PagedResponse<T> {
  items: T[];
  page: number;
  size: number;
  totalItems: number;
  totalPages: number;
}

/** 로그인·리프레시 응답 */
export interface TokenResponse {
  accessToken: string;
  refreshToken: string;
  userId: string;
  role: 'ADMIN' | 'USER';
  audience: 'admin' | 'user';
  accessExpiresAt: string;
}

export interface MeResponse {
  userId: string;
  role: 'ADMIN' | 'USER';
  audience: 'admin' | 'user';
  expiresAt: string;
}

export type Audience = 'admin' | 'user';
