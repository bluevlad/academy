/**
 * 인증 관련 타입 정의
 * 백엔드 /api/auth 엔드포인트 기반
 */

/** 로그인 요청 */
export interface SignInRequest {
  userId: string;
  userPwd: string;
}

/** 회원가입 요청 */
export interface SignUpRequest {
  userId: string;
  userPwd: string;
  userNm: string;
  email?: string;
  sex?: string;
  birthDay?: string;
  zipCode?: string;
  address1?: string;
  address2?: string;
}

/** 사용자 프로필 */
export interface UserProfile {
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
  userPoint?: number;
  memo?: string;
  pic?: string;
  isokSms?: string;
  isokEmail?: string;
}
