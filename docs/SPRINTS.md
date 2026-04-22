# Academy 통합 — Sprint 로드맵

> MVP 11기능 (관리자 6 · 사용자 5) 을 13주 Sprint 0~6 에 배치.
> 상위 문서: [../README.md](../README.md) · [adr/ADR-001-integration-strategy.md](adr/ADR-001-integration-strategy.md)
> 원본 플랜: [DocumetsToAiPipeLine IMPLEMENTATION_PLAN_v3.md](../../DocumetsToAiPipeLine/docs/integration/IMPLEMENTATION_PLAN_v3.md)

## Sprint 0 — 기반 (1주, 완료 ✅)

| # | 작업 | 상태 |
|---|---|---|
| 0-1 | monorepo 디렉토리 구성 (`backend/admin-web/user-web/legacy/docs/infra`) | ✅ |
| 0-2 | 통합 `pom.xml` — admin base + user 유용 의존성 + Flyway + Redis + ArchUnit | ✅ |
| 0-3 | `application.yml` — MariaDB 드라이버·Flyway baseline·Redis·Actuator·Swagger·CORS·3프로파일 | ✅ |
| 0-4 | `docker-compose.yml` — backend·admin-web·user-web·redis 4 컨테이너 (database-network 공유) | ✅ |
| 0-5 | `.env.example` + `.gitignore` | ✅ |
| 0-6 | `shared/` · `user/` 패키지 skeleton (package-info.java) | ✅ |
| 0-7 | Flyway V1 baseline marker | ✅ |
| 0-8 | 통합 `README.md` + `CLAUDE.md` + Sprint 로드맵 | ✅ |
| 0-9 | `/api/shared/health` 엔드포인트 + 통합 smoke test (`HealthControllerTest`) + SecurityConfig permitAll 에 `/api/shared/**`·`/actuator/health` 개방 | ✅ |
| 0-10 | `./mvnw compile` BUILD SUCCESS + `./mvnw test` BUILD SUCCESS (`AcademyApplicationTests` 은 Sprint 1 Testcontainers 도입까지 `@Disabled`) | ✅ |

## Sprint 1 — Identity + Platform (2주)

목표: academy-user 의 **회원/인증 22 API** 를 `com.academy.user.*` 로 흡수하며 Spring Security + JWT 로 전환.

| # | 작업 | 비고 |
|---|---|---|
| 1-1a | ✅ `shared.security` — JwtTokenProvider(HS256, access 30m+refresh 14d, jti) · RefreshTokenStore(Redis) · AdminJwtAuthenticationFilter · UserJwtAuthenticationFilter · SecurityConfig STATELESS · `/api/auth/login\|refresh\|logout\|me` · ApiResponse envelope | + admin-web sign-in/AuthContext 를 JWT 로 교체, AdminSessionApi 제거 |
| 1-1b | ✅ 관리자 `id_admin` DB 전환 — Flyway V2 · AdminVO/Mapper/UserDetailsService · AdminBootstrap(BCrypt upsert) · Testcontainers 인프라 도입 | 통합 smoke 는 OrbStack 29.x 호환 이슈로 @Disabled |
| 1-2 | ✅ 수강생 로그인 React+JWT 전환 — `acm_member` 기반 `com.academy.user.login.*` · Flyway V3(컬럼 확장) + V4 Java migration (평문 → BCrypt 일괄) · AuthService.loginUser BCrypt matches · user-web 은 Sprint 2 Vite 현대화 시 `/api/auth/login` 전환 예정 | 기존 `/api/auth/sign-in` 은 V4 이후 작동 중단 (의도) |
| 1-3 | ✅ `user.signup` — `POST /api/auth/signup` (userId/email 중복 체크 + BCrypt + acm_member insert). OAuth · 약관은 후속 | `com.academy.user.signup` 패키지 신규 |
| 1-4 | ✅ `user.mypage.privateinfo` — P0 4건 (프로필 조회/수정 · 비번 변경 + 전 refresh 폐기 · 회원탈퇴 soft delete · 수강확인증 skeleton). cart/mylecture/counsel 등 나머지는 Sprint 3~5 로 | `@AuthenticationPrincipal` 전환 완료 |
| 1-5 | ✅ admin 경로 보안 강화 — AdminJwtAuthenticationFilter 를 `/api/` 전역으로 확장(user/auth/shared 제외), SecurityConfig `anyRequest` 를 `hasRole(ADMIN)` 로 — 기존 `/api/board`·`/api/book` 등 `/api/admin/` prefix 가 아닌 admin API 가 모두 ADMIN 검증 대상 | 이전 상태에서는 permitAll 누수 |
| 1-6 | ✅ `Phase1IntegrationTest` — signup→login(user)→profile→password→재로그인→탈퇴→admin login 8단계 e2e. Testcontainers OrbStack 호환 이슈로 `@Disabled` | owner 가 Docker Engine 27 이하 또는 호환 해결 시 활성화 |

Go/No-Go: `/api/user/**` 36 endpoint 중 P0 (15개) 가 JWT 기반 200 응답 + Swagger 에 표기.

## Sprint 2 — Content (2주)

목표: 강의·과목·교수진 CRUD (admin) + 목록·상세 (user). user-web 현대화 병행.

| # | 작업 |
|---|---|
| 2-1 | ✅ admin 경로 보호 (Sprint 1-5 로 자동 적용). 기존 Oracle 레거시 쿼리(ROWNUM/NVL/DECODE/`(+)`)의 MariaDB 호환 여부는 **owner 수동 smoke** 필요 — TB_TOP_MST/TB_SUBJECT_INFO/TB_MA_MEMBER 등 실데이터 확인 후 쿼리 재작성 대상 식별 |
| 2-2 | ✅ `user.content.lecture` 신규 — `GET /api/user/lecture` 목록(keyword·subjectCd·teacherId 필터, pagination) + `GET /api/user/lecture/{mstCode}` 상세 + TB_MST_BRIDGE 기반 챕터 목록. MariaDB 표준 문법 (LIMIT offset/size, LEFT JOIN) 으로 신규 작성 |
| 2-3 | ✅ `user.content.teacher` 신규 — `GET /api/user/teacher` (최소 1개 강의 담당 회원) + `/{teacherId}` 상세. `user.content.subject` 신규 — `GET /api/user/subject` 활성 과목 |
| 2-4 | ⏸ user-web **CRA → Vite + MUI v6** 마이그레이션 — 별도 세션 (FE 전체 재작성 범위 큼, `feat/user-web-vite` 브랜치) |
| 2-5 | ⏸ `@academy/ui-core` 공용 컴포넌트 패키지 — 2-4 완료 후 추출 |

## Sprint 3 — Enrollment + Order (2주) ✅

| # | 작업 |
|---|---|
| 3-1 | ✅ `user.cart` `user.order` — en_cart_item (upsert 수량 합산), od_order/od_order_item |
| 3-2 | ✅ `user.payment` — PG mock (`PaymentService.payMock` 즉시 APPROVED + OrderCompletedEvent) |
| 3-3 | ✅ 수강권 발급 이벤트 — `EnrollmentListener @EventListener` 가 OrderCompletedEvent 수신 → en_enrollment upsert (기본 6개월) |
| 3-4 | ⏸ admin 주문관리·환불승인 UI 점검 — admin-web 변경 없음, 기존 `/api/admin/order/*` 엔드포인트 활용 가능 |

## Sprint 4 — Point + Book (2주) ✅

| # | 작업 |
|---|---|
| 4-1 | ✅ `user.coupon` — pt_coupon + pt_coupon_user (미사용·유효·활성 필터, markUsed 멱등) |
| 4-2 | ✅ 마일리지 원장 append-only — pt_mileage_ledger (BIGINT AUTO_INCREMENT, balance 합산) |
| 4-3 | ✅ 교재·배송지 — bk_book · bk_delivery_address · bk_delivery (배송 상태는 Sprint 5 이후 order 연결) |
| 4-4 | ⏸ admin 교재·배송 관리 UI — 별도 FE 세션 |

## Sprint 5 — Learning(동영상 제외) + Mock Exam (2주) ✅

| # | 작업 |
|---|---|
| 5-1 | ✅ `user.mylecture` — en_enrollment + TB_TOP_MST/TB_SUBJECT_INFO/TB_MA_MEMBER JOIN, 수동 진도율 (0~100) |
| 5-2 | ✅ `user.mocktest` — ex_mock_exam/ex_mock_attempt, register 멱등 + submit (자동 채점 score 옵션) |
| 5-3 | ⏸ admin 모의고사 관리·통계 — admin-web 별도 세션 |
| 5-4 | ⏸ hopenvision 시험 채점 API 연동 — Plan B |
| — | ✅ 부가: Sprint 1-4 certificate 가 en_enrollment 실 데이터로 전환 (MyLectureMapper 재사용) |

## Sprint 6 — 검증·이관 (2주) ✅ (backend 부분 완료)

| # | 작업 |
|---|---|
| 6-1 | ⏸ Playwright E2E — Phase1/Phase2IntegrationTest 로 backend smoke 확보. Playwright FE 시나리오는 user-web Vite 전환(Sprint 2-4) 이후 |
| 6-2 | ⏸ Flyway 전체 리허설 — Testcontainers OrbStack 호환 이슈로 @Disabled. owner 가 실 DB 로 V1→V7 dry-run 필요 |
| 6-3 | ✅ ArchUnit 경계 규칙 — `ModularMonolithRulesTest` 5 rules (shared↔admin/user 역참조 금지 등). 빈 레이어 strict 모드는 Sprint 7 에 |
| 6-4 | ✅ 배포 체크리스트 — `docs/DEPLOYMENT_CHECKLIST.md` (7섹션, 롤백·Exit Gate 평가·수동 smoke 스크립트 포함) |

## P0 canonical 매핑 — Sprint 배치

> 원본: [DocumetsToAiPipeLine 07-msa-design.md §5](../../DocumetsToAiPipeLine/docs/legacy/willbis-wca/07-msa-design.md) — P0 14건 (variants ≥ 10)
> Sprint 완료 조건: 아래 **MVP 범위 P0** 9건이 해당 Sprint 골든 시나리오에 포함되어야 한다.

| Canonical | MSA | Variants | 배치 Sprint | 상태 |
|---|---|---|---|---|
| 회원탈퇴 | identity-svc | 18 | **1** | ✅ `DELETE /api/user/mypage/account` |
| 비밀번호 변경 | identity-svc | 12 | **1** | ✅ `PUT /api/user/mypage/password` |
| 개인정보수정 | identity-svc | 12 | **1** | ✅ `PUT /api/user/mypage/profile` |
| 수강확인증 출력 | identity-svc | 12 | **1→5** | ✅ `GET /api/user/mypage/certificate` (Sprint 5 에서 en_enrollment 실데이터로 연결) |
| 형법 (→ 과목·강의 대표) | content-svc | 660 | **2** | ✅ `/api/user/lecture` · `/subject` · `/teacher` (경량 MariaDB 쿼리) |
| 수강종료/재수강 신청 | enrollment-svc | 12 | **3** | 🟡 en_enrollment 상태 ACTIVE/CANCELED/EXPIRED 존재. admin 취소/재수강 UI 는 별도 |
| 포인트/쿠폰 | billing-svc (→ point-svc) | 12 | **4** | ✅ `/api/user/coupon` · `/api/user/mileage/balance` |
| 기출문제 | exam-svc | 14 | **5** | 🟡 ex_mock_attempt.answer_sheet 에 수용. 기출 전용 카탈로그는 별도 |
| 나의 D-day/나의 목표 | exam-svc | 12 | **5** | ⏸ Plan B (UI-가까움, MVP 범위 밖) |

**MVP 범위 밖 P0** (5건) — Plan B 로 이관:
- 경찰간부후보생은? · 개정법령 · 형사소송법 · 무료특강 · 영어 (모두 community-svc, MVP 에 커뮤니티 미포함)

**P1 16건**: 대부분 community-svc 부가 콘텐츠 → Plan B. 예외:
- 동영상 강의 공지사항 (content-svc) → Sprint 2 에 선택
- 재수강신청 (enrollment-svc) → Sprint 3 에 P0 "수강종료/재수강" 과 묶어 처리
- 학원 강의 시간표 (content-svc, 한림) → Sprint 2 에 선택

## 후속 (Plan B) — MVP 이후

- 동영상 플레이어 · 커뮤니티 4종 · Exam 고도화 · Operation(사물함·독서실) · Analytics 이벤트
- MVP 범위 밖 P0 5건 + P1 잔여 → 커뮤니티·공지·가이드 형태로 통합
- hopenvision 통합 검토 (ADR-004 Exit Gate 기준 평가)
- academy-user-back-end repo 아카이브
