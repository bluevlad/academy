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
| 1-1a | ✅ `shared.security` — JwtTokenProvider(HS256, access 30m+refresh 14d, jti) · RefreshTokenStore(Redis) · AdminJwtAuthenticationFilter · UserJwtAuthenticationFilter · SecurityConfig STATELESS · `/api/auth/login\|refresh\|logout\|me` · ApiResponse envelope | 테스트 20건, 관리자 InMemory 유지 |
| 1-1b | ⏳ 관리자 저장소 `id_admin` DB 전환 (Flyway V2 + BCrypt) | ADR-002 후속 |
| 1-2 | `user.login` 이관 — academy-user `com.academy.login` → `com.academy.user.login` | 레거시 HttpServletRequest → `@AuthenticationPrincipal` 전환 |
| 1-3 | `user.signup` 신규 — 이메일 가입·OAuth·약관 | academy-user 원본엔 signup 흐름 부분구현 |
| 1-4 | `user.mypage` 이관 — privateinfo/counsel/coupon/cart/note/pay/passlecture/mylecture 8 하위 | 범위 큼, 우선 핵심 3 (privateinfo/cart/mylecture) |
| 1-5 | `admin` 회원관리 화면 연결 확인 | 기존 admin-web 화면 재사용 |
| 1-6 | 통합 smoke: 로그인→토큰→ /api/user/mypage 200 | |

Go/No-Go: `/api/user/**` 36 endpoint 중 P0 (15개) 가 JWT 기반 200 응답 + Swagger 에 표기.

## Sprint 2 — Content (2주)

목표: 강의·과목·교수진 CRUD (admin) + 목록·상세 (user). user-web 현대화 병행.

| # | 작업 |
|---|---|
| 2-1 | admin 강의·과목·교수진 API 검증 (기존 admin 모듈 살아있는지 smoke) |
| 2-2 | `user.lecture` 이관 — 강의 목록·상세·검색 (동영상 플레이어 **미포함**, 챕터 목록만) |
| 2-3 | `user.teacher` 이관 |
| 2-4 | user-web **CRA → Vite + MUI v6** 마이그레이션 (별도 브랜치 `feat/user-web-vite`) |
| 2-5 | admin-web · user-web 에 `@academy/ui-core` 공용 컴포넌트 패키지 초안 |

## Sprint 3 — Enrollment + Order (2주)

| # | 작업 |
|---|---|
| 3-1 | `user.cart` `user.order` 이관 |
| 3-2 | `user.pay` 이관 — PG sandbox 연동 (LGD 가 아직 없으면 mock) |
| 3-3 | 수강권 발급 이벤트 (결제 완료 → 수강권 → 수강 상태) |
| 3-4 | admin 주문관리·환불승인 UI 점검 |

## Sprint 4 — Point + Book (2주)

| # | 작업 |
|---|---|
| 4-1 | `user.coupon` 이관 — 발행·보유·사용 flow |
| 4-2 | 마일리지 원장 (append-only, admin 조정 UI) |
| 4-3 | 교재 카탈로그·재고·배송지·배송 상태 |
| 4-4 | admin 교재·배송 관리 화면 |

## Sprint 5 — Learning(동영상 제외) + Mock Exam (2주)

| # | 작업 |
|---|---|
| 5-1 | `user.mypage.mylecture` "내 강의실" 화면 — 수강권 기반 목록, 진도는 수동 체크 |
| 5-2 | `user.mocktest` 이관 — 신청·응시·성적 조회 |
| 5-3 | admin 모의고사 관리·통계 점검 |
| 5-4 | hopenvision 시험 채점 API 연동 검토 (선택) |

## Sprint 6 — 검증·이관 (2주)

| # | 작업 |
|---|---|
| 6-1 | Playwright E2E — 관리자 6 × 사용자 5 골든 시나리오 |
| 6-2 | Flyway 전체 리허설 (clean DB → V1 baseline 수동 import → V2+) |
| 6-3 | ArchUnit 규칙 강화 (빈 레이어 금지로 전환) |
| 6-4 | 배포 스위칭: 기존 academy-admin(9001)/user(9002) → integrated(9000) |

## P0 canonical 매핑 — Sprint 배치

> 원본: [DocumetsToAiPipeLine 07-msa-design.md §5](../../DocumetsToAiPipeLine/docs/legacy/willbis-wca/07-msa-design.md) — P0 14건 (variants ≥ 10)
> Sprint 완료 조건: 아래 **MVP 범위 P0** 9건이 해당 Sprint 골든 시나리오에 포함되어야 한다.

| Canonical | MSA | Variants | 배치 Sprint | 상태 |
|---|---|---|---|---|
| 회원탈퇴 | identity-svc | 18 | **1** | ⏳ |
| 비밀번호 변경 | identity-svc | 12 | **1** | ⏳ |
| 개인정보수정 (→ identity 로 재분류) | community-svc→identity-svc | 12 | **1** | ⏳ |
| 수강확인증 출력 | identity-svc | 12 | **1** (마이페이지 발급) | ⏳ |
| 형법 (→ 과목·강의 대표) | content-svc | 660 | **2** (과목·강의 CRUD) | ⏳ |
| 수강종료/재수강 신청 | enrollment-svc | 12 | **3** | ⏳ |
| 포인트/쿠폰 | billing-svc (→ point-svc) | 12 | **4** | ⏳ |
| 기출문제 | exam-svc | 14 | **5** (모의고사 근접) | ⏳ |
| 나의 D-day/나의 목표 | exam-svc | 12 | **5** (내 강의실 인접) | ⏳ |

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
