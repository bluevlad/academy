/**
 * 강의 (Lecture) API — backend `/api/lecture/*` 바인딩.
 *
 * ⚠️ 현재 backend SQL 일부 Oracle 문법 잔존으로 MariaDB 환경에서 500 발생 중.
 * 본 모듈의 list/detail 호출은 동작하지 않음. 후속 PR 에서 lectureLectureSQL.xml 정비 후 활성화.
 */
import { apiClient } from './client';

export interface Lecture {
  SEQ: number;
  LECCODE: string;
  SUBJECT_TITLE: string;
  SUBJECT_PRICE?: string | null;
  SUBJECT_DISCOUNT?: string | null;
  CATEGORY_CD?: string | null;
  LEARNING_CD?: string | null;
  MSTCODE?: string | null;
  SUBJECT_CD?: string | null;
  TEACHER_CD?: string | null;
  IS_USE?: 'Y' | 'N' | string;
  REG_DT?: string | null;
}

export interface LectureSearch {
  subjectTitle?: string;
  teacherNm?: string;
  isUse?: '' | 'Y' | 'N';
  currentPage?: number;
  pageRow?: number;
}

export interface LectureListResult {
  items: Lecture[];
  totalCount: number;
  totalPage: number;
  currentPage: number;
}

export async function listLectures(params: LectureSearch): Promise<LectureListResult> {
  const { data } = await apiClient.get('/lecture/list', { params });
  return {
    items: (data?.list ?? []) as Lecture[],
    totalCount: data?.totalCount ?? 0,
    totalPage: data?.totalPage ?? 0,
    currentPage: data?.currentPage ?? params.currentPage ?? 1,
  };
}

export async function getLectureDetail(seq: number | string): Promise<Lecture | null> {
  const { data } = await apiClient.get('/lecture/view', { params: { seq } });
  return (data?.view ?? null) as Lecture | null;
}
