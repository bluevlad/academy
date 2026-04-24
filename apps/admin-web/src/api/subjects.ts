/**
 * 과목 (Subject) API — backend `/api/subject/*` 바인딩.
 *
 * ⚠️ 현재 backend SQL 일부 Oracle 문법 잔존으로 MariaDB 환경에서 500 발생 중.
 * 본 모듈의 list/detail 호출은 동작하지 않음. 화면도 "MariaDB 호환 마이그레이션 대기" 안내.
 * 후속 PR 에서 lectureSubjectSQL.xml 정비 후 활성화.
 */
import { apiClient } from './client';

export interface Subject {
  SJT_CD: string;       // 과목 코드
  SJT_NM: string;       // 과목명
  P_SJT_CD?: string | null;
  SJT_DEPTH?: number | null;
  SJT_ORDR?: number | null;
  IS_USE?: 'Y' | 'N' | string;
  REG_DT?: string | null;
  REG_ID?: string | null;
}

export interface SubjectSearch {
  sjtNm?: string;
  isUse?: '' | 'Y' | 'N';
  currentPage?: number;
  pageRow?: number;
}

export interface SubjectListResult {
  items: Subject[];
  totalCount: number;
  totalPage: number;
  currentPage: number;
}

export async function listSubjects(params: SubjectSearch): Promise<SubjectListResult> {
  const { data } = await apiClient.get('/subject/list', { params });
  return {
    items: (data?.list ?? []) as Subject[],
    totalCount: data?.totalCount ?? 0,
    totalPage: data?.totalPage ?? 0,
    currentPage: data?.currentPage ?? params.currentPage ?? 1,
  };
}

export async function getSubjectDetail(sjtCd: string): Promise<Subject | null> {
  const { data } = await apiClient.get('/subject/view', { params: { sjtCd } });
  return (data?.view ?? null) as Subject | null;
}
