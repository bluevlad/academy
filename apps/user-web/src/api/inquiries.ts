/**
 * 학생(USER) 1:1 문의 API — /api/user/inquiries/*
 */
import { unwrap } from '@academy/ui-core';
import type { ApiResponse, PagedResponse } from '@academy/ui-core';
import { apiClient } from './client';

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
  predictedCategory?: string | null;
  predictedConfidence?: number | null;
  classifiedByModel?: string | null;
  actualCategory?: string | null;
  answerBody?: string | null;
  answeredBy?: string | null;
  answeredAt?: string | null;
  resolutionState: string;
  userSatisfaction?: number | null;
  rerouteCount?: number;
}

export interface RelatedItem {
  cs_seq: number;
  title: string;
  answer_excerpt?: string | null;
  similarity: number;
  category?: string | null;
}

export async function createInquiry(payload: { title: string; body: string }): Promise<Inquiry> {
  const res = await apiClient.post<ApiResponse<Inquiry>>('/inquiries', payload);
  return unwrap<Inquiry>({ data: res.data });
}

export async function myInquiries(page = 1, size = 20): Promise<PagedResponse<Inquiry>> {
  const res = await apiClient.get<ApiResponse<PagedResponse<Inquiry>>>(
    '/inquiries',
    { params: { page, size } },
  );
  return unwrap<PagedResponse<Inquiry>>({ data: res.data });
}

export async function myInquiryDetail(csSeq: string): Promise<Inquiry> {
  const res = await apiClient.get<ApiResponse<Inquiry>>(`/inquiries/${encodeURIComponent(csSeq)}`);
  return unwrap<Inquiry>({ data: res.data });
}

export async function suggestRelated(draftBody: string, topK = 3): Promise<RelatedItem[]> {
  const res = await apiClient.post<ApiResponse<{ items: RelatedItem[] }>>(
    '/inquiries/suggest-related',
    { draftBody, topK },
  );
  const data = unwrap<{ items: RelatedItem[] }>({ data: res.data });
  return data.items ?? [];
}
