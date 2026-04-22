/**
 * User (수강생) 인증·로그인 도메인 (Sprint 1-2).
 *
 * <p>ADR-001 "admin BE 가 user 를 흡수" 에 따라 academy-user 의 로그인 기능이
 * {@code com.academy.user.login} 아래로 점진 이관된다.
 *
 * <p>이 패키지는 {@code acm_member} 테이블을 기준으로 사용자 조회·비밀번호 업데이트를
 * 담당하며, JWT 발급은 {@link com.academy.shared.auth.AuthService} 가 담당한다.
 * 비밀번호 저장 방식은 BCrypt (ADR-002 · Sprint 1-2 에서 평문 → BCrypt 마이그레이션 완료).
 */
package com.academy.user.login;
