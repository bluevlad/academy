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

export interface Inquiry {
  csSeq: number;
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

export async function getInquiryDetail(csSeq: number): Promise<Inquiry> {
  const res = await apiClient.get<ApiResponse<Inquiry>>(`/inquiries/${csSeq}`);
  return unwrap<Inquiry>({ data: res.data });
}

export async function classifyNow(csSeq: number): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>(`/inquiries/${csSeq}/classify`);
  return unwrap<Inquiry>({ data: res.data });
}

export async function postAnswer(
  csSeq: number,
  answerBody: string,
  resolutionState?: ResolutionState,
): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>(`/inquiries/${csSeq}/answer`, {
    answerBody,
    resolutionState,
  });
  return unwrap<Inquiry>({ data: res.data });
}

export async function reassignInquiry(
  csSeq: number,
  payload: { toCategory: string; toUser: string; reason?: string; isAiError?: boolean },
): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>(`/inquiries/${csSeq}/reassign`, payload);
  return unwrap<Inquiry>({ data: res.data });
}

export async function getRelated(csSeq: number): Promise<SuggestResponse> {
  const res = await apiClient.get<ApiResponse<SuggestResponse>>(`/inquiries/${csSeq}/related`);
  return unwrap<SuggestResponse>({ data: res.data });
}
