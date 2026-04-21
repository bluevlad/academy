/**
 * 백엔드 API 공통 응답 타입
 * 백엔드 컨트롤러의 JSONObject 응답 구조를 기반으로 정의
 */

/** API 응답 상태 코드 */
export type RetMsg = "OK" | "FAIL" | "DUPLICATE";

/** 페이지네이션 정보 */
export interface PaginationInfo {
  currentPageNo: number;
  totalPageCount: number;
  totalRecordCount: number;
  recordCountPerPage: number;
}

/** API 기본 응답 */
export interface ApiResponse<T = unknown> {
  retMsg: RetMsg;
  message?: string;
  data?: T;
  list?: T[];
  totalCount?: number;
  paginationInfo?: PaginationInfo;
}

/** 공통 페이지네이션 요청 파라미터 */
export interface PaginationParams {
  currentPageNo?: number;
  recordCountPerPage?: number;
  searchType?: string;
  searchValue?: string;
}

/** 공통 엔티티 필드 (등록/수정 정보) */
export interface AuditFields {
  regId?: string;
  regDt?: string;
  modId?: string;
  modDt?: string;
}
