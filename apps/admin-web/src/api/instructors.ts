/**
 * 강사 (Instructor) API — acm_member 테이블의 user_role='PRF' 래퍼.
 *
 * 별도 엔드포인트 없음. members API 를 PRF 필터로 재사용.
 * 강사 전용 추가 정보 (담당 강의·과목 매핑 등) 는 향후 별도 엔드포인트로 확장 예정.
 */
import {
  listMembers,
  getMemberDetail,
  type Member,
  type MemberDetail,
  type MemberSearch,
  type MemberListResult,
} from './members';

export type Instructor = Member;
export type InstructorDetail = MemberDetail;

export interface InstructorSearch extends Omit<MemberSearch, 'userRole'> {
  /** PRF 강사 안에서의 추가 검색 필드는 향후 확장 */
}

export async function listInstructors(params: InstructorSearch): Promise<MemberListResult> {
  return listMembers({ ...params, userRole: 'PRF' });
}

export async function getInstructorDetail(userId: string): Promise<InstructorDetail | null> {
  return getMemberDetail(userId);
}
