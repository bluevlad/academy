import { apiClient } from './client';

export type MemberRole = 'USER' | 'ADMIN' | 'TEACHER';

export interface Member {
  USER_ID: string;
  USER_NM: string;
  USER_ROLE: MemberRole | string;
  ADMIN_ROLE?: string | null;
  SEX?: string | null;
  BIRTH_DAY?: string | null;
  EMAIL?: string | null;
  USER_POINT?: number | string | null;
  IS_USE?: 'Y' | 'N' | string;
  REG_DT?: string | null;
  REG_ID?: string | null;
  UPD_DT?: string | null;
  UPD_ID?: string | null;
  ISOK_SMS?: 'Y' | 'N' | string;
  ISOK_EMAIL?: 'Y' | 'N' | string;
}

export interface MemberDetail extends Member {
  USER_PWD?: string;
  ZIP_CODE?: string | null;
  ADDRESS1?: string | null;
  ADDRESS2?: string | null;
  MEMO?: string | null;
  PIC?: string | null;
  TOKEN?: string | null;
}

export interface MemberSearch {
  userId?: string;
  userNm?: string;
  email?: string;
  isUse?: '' | 'Y' | 'N';
  userRole?: '' | MemberRole;
  curPage?: number;
  pageUnit?: number;
  pageSize?: number;
}

export interface PaginationInfo {
  currentPageNo: number;
  recordCountPerPage: number;
  pageSize: number;
  totalRecordCount: number;
  firstRecordIndex: number;
  lastRecordIndex: number;
  firstPageNo?: number;
  lastPageNo?: number;
}

export interface MemberListResult {
  items: Member[];
  pagination: PaginationInfo;
}

export interface MemberUpsert {
  userId: string;
  userNm: string;
  userPwd?: string;
  sex?: string;
  userRole?: string;
  adminRole?: string;
  birthDay?: string;
  email?: string;
  zipCode?: string;
  address1?: string;
  address2?: string;
  userPoint?: number | string;
  memo?: string;
  pic?: string;
  isUse?: 'Y' | 'N';
  isokSms?: 'Y' | 'N';
  isokEmail?: 'Y' | 'N';
  regId?: string;
  updId?: string;
}

/**
 * 레거시 MemberApi 응답 어댑터.
 * `/api/member/*` 는 ApiResponse envelope 이 아닌 {memberList, paginationInfo} 형식.
 */
export async function listMembers(params: MemberSearch): Promise<MemberListResult> {
  const { data } = await apiClient.get('/member/getMemberList', { params });
  return {
    items: (data?.memberList ?? []) as Member[],
    pagination: (data?.paginationInfo ?? {
      currentPageNo: params.curPage ?? 1,
      recordCountPerPage: params.pageUnit ?? 10,
      pageSize: params.pageSize ?? 10,
      totalRecordCount: 0,
      firstRecordIndex: 0,
      lastRecordIndex: 0,
    }) as PaginationInfo,
  };
}

export async function getMemberDetail(userId: string): Promise<MemberDetail | null> {
  const { data } = await apiClient.get('/member/getMemberDetail', { params: { userId } });
  return (data?.memberDetail ?? null) as MemberDetail | null;
}

export async function insertMember(payload: MemberUpsert): Promise<void> {
  const { data } = await apiClient.get('/member/insertMember', { params: payload });
  if (data?.retMsg !== 'OK') {
    throw new Error('회원 등록에 실패했습니다.');
  }
}

export async function updateMember(payload: MemberUpsert): Promise<void> {
  const { data } = await apiClient.get('/member/updateMember', { params: payload });
  if (data?.retMsg !== 'OK') {
    throw new Error('회원 수정에 실패했습니다.');
  }
}

export async function deleteMember(userId: string): Promise<void> {
  const { data } = await apiClient.get('/member/deleteMember', { params: { userId } });
  if (data?.retMsg !== 'OK') {
    throw new Error('회원 삭제에 실패했습니다.');
  }
}
