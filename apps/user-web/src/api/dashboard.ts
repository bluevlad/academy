/**
 * Public dashboard API — academy.unmong.com 진입 시 비로그인 호출.
 * 응답에는 % 만 포함, 절대값(count) 없음.
 */
import axios from 'axios';

const baseURL = import.meta.env.VITE_API_BASE_URL ?? '/api';

export interface DashboardMetric {
  key: string;
  label: string;
  valueRatio: number | null;
  deltaPct: number | null;
  trend: 'up' | 'down' | 'flat';
  sentimentInverted: boolean;
}

export interface DashboardSummary {
  service: { id: string; name: string; tagline: string };
  metrics: DashboardMetric[];
  uptime: { backend: boolean; agent: boolean; db: boolean };
  ymBasis: string;
}

export async function fetchDashboardSummary(): Promise<DashboardSummary> {
  // 비로그인 호출 — apiClient (Authorization 헤더 부착) 대신 plain axios 사용
  const res = await axios.get<DashboardSummary>(`${baseURL}/shared/dashboard/summary`);
  return res.data;
}
