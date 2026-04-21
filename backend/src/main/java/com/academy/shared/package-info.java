/**
 * Shared 모듈 — admin·user 공통 인프라.
 *
 * <p>포함 대상:
 * <ul>
 *   <li>{@code shared.config}  — Spring 공통 설정 (Redis, MyBatis, Web, OpenAPI)</li>
 *   <li>{@code shared.security} — JWT, OAuth2, RequestFilter, Role</li>
 *   <li>{@code shared.common}   — DTO · Response Envelope · Pagination · Util</li>
 * </ul>
 *
 * <p>주의: admin/user 패키지에서만 의존(shared → admin/user 역참조 금지).
 *
 * <p>Sprint 0 단계에서는 <strong>비어 있음</strong>. 기존 {@code com.academy.auth/common/config} 가
 * 실질적 shared 역할을 한다. Sprint 1+ 에서 점진 이관 예정.
 */
package com.academy.shared;
