/**
 * CS 문의 운영자 콘솔 API. (CS_INQUIRY_AI_PLAN Phase C)
 *
 * backend /api/inquiries 와 academy-agent (로컬 Ollama) 연동.
 * 신 ApiResponse envelope 사용 (unwrap).
 */
import { unwrap } from '@academy/ui-core';
import type { ApiResponse, PagedResponse } from '@academy/ui-core';
import { apiClient } from './client';

export type InquiryCategory = 'ACADEMIC' | 'ORDER' | 'SYSTEM' | 'OTHER';
export type ResolutionState = 'OPEN' | 'ANSWERED' | 'RESOLVED' | 'CLOSED';

export type InquirySource = 'L' | 'N';

export interface Inquiry {
  source: InquirySource;
  csSeq: string;
  inquiryUserId: string;
  inquiryName: string;
  inquiryTitle: string;
  bodyPreview?: string | null;
  body?: string | null;
  inquiryDate: string;
  predictedCategory?: InquiryCategory | string | null;
  predictedConfidence?: number | null;
  classifiedByModel?: string | null;
  classifiedAt?: string | null;
  actualCategory?: InquiryCategory | string | null;
  assignedTo?: string | null;
  rerouteCount: number;
  answerBody?: string | null;
  answeredBy?: string | null;
  answeredAt?: string | null;
  resolutionState: ResolutionState | string;
  userSatisfaction?: number | null;
}

export interface InquirySearch {
  category?: '' | InquiryCategory;
  resolutionState?: '' | ResolutionState;
  keyword?: string;
  assignedTo?: string;
  page?: number;
  size?: number;
}

export interface RelatedItem {
  cs_seq: number;
  title: string;
  answer_excerpt?: string | null;
  similarity: number;
  category?: string | null;
}

export interface SuggestResponse {
  items: RelatedItem[];
  query_embedding_dim: number;
  model: string;
}

export async function listInquiries(params: InquirySearch): Promise<PagedResponse<Inquiry>> {
  const res = await apiClient.get<ApiResponse<PagedResponse<Inquiry>>>('/inquiries', { params });
  return unwrap<PagedResponse<Inquiry>>({ data: res.data });
}

export async function getInquiryDetail(csSeq: string): Promise<Inquiry> {
  const res = await apiClient.get<ApiResponse<Inquiry>>(`/inquiries/${encodeURIComponent(csSeq)}`);
  return unwrap<Inquiry>({ data: res.data });
}

export async function classifyNow(csSeq: string): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>(`/inquiries/${encodeURIComponent(csSeq)}/classify`);
  return unwrap<Inquiry>({ data: res.data });
}

export async function postAnswer(
  csSeq: string,
  answerBody: string,
  resolutionState?: ResolutionState,
): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>(
    `/inquiries/${encodeURIComponent(csSeq)}/answer`,
    { answerBody, resolutionState },
  );
  return unwrap<Inquiry>({ data: res.data });
}

export async function reassignInquiry(
  csSeq: string,
  payload: { toCategory: string; toUser: string; reason?: string; isAiError?: boolean },
): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>(
    `/inquiries/${encodeURIComponent(csSeq)}/reassign`,
    payload,
  );
  return unwrap<Inquiry>({ data: res.data });
}

export async function getRelated(csSeq: string): Promise<SuggestResponse> {
  const res = await apiClient.get<ApiResponse<SuggestResponse>>(
    `/inquiries/${encodeURIComponent(csSeq)}/related`,
  );
  return unwrap<SuggestResponse>({ data: res.data });
}

export interface CategoryStat {
  category: string;
  totalCount: number;
  resolvedCount: number;
  prevMonthCount: number;
  momDeltaPct: number | null;
  decreasing: boolean;
  avgSatisfaction: number | null;
}

export interface MonthlyStats {
  yearMonth: string;
  categories: CategoryStat[];
  totalInquiries: number;
  resolvedCount: number;
  resolutionRate: number | null;
  aiAccuracyRate: number | null;
  aiErrorCount: number;
  totalRoutingChanges: number;
  unresolvedTop: Inquiry[];
}

export async function getMonthlyStats(ym: string): Promise<MonthlyStats> {
  const res = await apiClient.get<ApiResponse<MonthlyStats>>('/inquiries/stats', {
    params: { ym },
  });
  return unwrap<MonthlyStats>({ data: res.data });
}
