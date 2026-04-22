/**
 * 수강생 회원가입 도메인 (Sprint 1-3).
 *
 * <p>기본 이메일 가입 (userId + 비밀번호 + userNm + email). OAuth 가입은
 * {@link com.academy.auth.GoogleOAuthApi} 가 담당. 저장은 BCrypt 기반
 * ({@link com.academy.user.login.UserAccountMapper} 재사용).
 */
package com.academy.user.signup;
