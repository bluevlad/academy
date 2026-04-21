/**
 * User 모듈 — 수강생 사용자(비관리자) 기능.
 *
 * <p>Sprint 1 에 academy-user 저장소의 22 모듈을 여기로 흡수한다.
 * 흡수 과정에서 각 API 의 인증 방식을 {@code HttpServletRequest} 기반 레거시에서
 * Spring Security + JWT(@AuthenticationPrincipal) 로 전환한다.
 *
 * <p>URL 규칙: {@code /api/user/**}.
 *
 * <p>하위 구성(예정):
 * <ul>
 *   <li>login/ signup/ mypage/</li>
 *   <li>cart/ pay/ order/</li>
 *   <li>lecture/ book/ event/</li>
 *   <li>mocktest/ board/ community/</li>
 * </ul>
 */
package com.academy.user;
