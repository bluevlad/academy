# ADR-001 — 통합 전략 (A2 + B1 + X1)

- Status: Accepted · 2026-04-21
- Deciders: owner + Claude Code
- 상위 문서: [IMPLEMENTATION_PLAN_v3.md](../../../DocumetsToAiPipeLine/docs/integration/IMPLEMENTATION_PLAN_v3.md)

## 문맥

현재 운영 환경:
- `academy-admin-back-end-JavaSpring` (Spring Boot 3.2 / MyBatis / MariaDB / 38 모듈)
- `academy-user-back-end-JavaSpring` (Spring Boot 3.2 / MyBatis / 22 모듈, API 94.7% 가 레거시 HttpServletRequest 인증)
- `academy-admin-front-end-React` (Vite 6 / MUI v6 / Zustand / 부분 TS)
- `academy-user-front-end-React-material-dashboard` (CRA / MUI v5 / Context API / JS)
- 두 backend 는 **동일 DB `acm_basic`** 을 공유 중 → 스키마 마이그레이션 부담 거의 없음

상위 목표: **관리자 6 모듈 + 사용자 5 플로우 MVP** 를 **하나의 docker compose** 에서 기동 가능한 통합 시스템으로 만든다.

## 결정

### A2 — 신규 monorepo `~/GIT/academy/`

선택지:
- A1) `~/GIT/academy-admin/` 기존 경로 재활용
- **A2) `~/GIT/academy/` 신규 monorepo** ← 선택
- A3) 기존 두 repo 를 git submodule 로 묶기

**이유**: admin 이름에 user까지 섞이는 어색함을 피하고, 새 CI/CD · 브랜치 전략을 깨끗하게 수립하기 위함.

### B1 — admin BE 를 base, user 를 모듈로 흡수 (단일 Spring Boot)

선택지:
- **B1) admin BE 가 user 를 흡수** ← 선택
- B2) BE 2 개 유지 + Gateway(Nginx)

**이유**:
- user BE 의 36/38 API 가 레거시 HttpServletRequest 인증 → **어차피 재작성 필요**. 흡수하면서 JWT 전환하는 편이 자연스러움.
- 단일 Jar 로 배포 가능 → 운영 복잡도↓.
- DB 이미 공유이므로 트랜잭션 일관성 확보 쉬움.
- MSA 분리가 필요해지면 URL prefix (`/api/user/**`) 기반으로 재분리 가능 (Strangler Fig).

### X1 — FE 2개 유지 + user-web 현대화

선택지:
- **X1) FE 2 개 유지 (admin-web, user-web). user-web 은 CRA → Vite + MUI v6 로 현대화** ← 선택
- X2) 단일 React SPA (`/admin/*` vs `/*` role-based)

**이유**:
- 운영 트래픽 경로 유지 (기존 포트 · 도메인 호환 쉬움).
- admin 은 이미 Vite + MUI v6 + Zustand 로 현대적 → 그대로 활용.
- user-web 은 어차피 재작업 필요하므로 이참에 stack 맞춤.
- X2 는 MVP 이후 고려 대상.

## 결과

- `backend/` 단일 Spring Boot (포트 9000) — `/api/admin/**`, `/api/user/**`, `/api/shared/**` 경계
- `admin-web/` (포트 4001) · `user-web/` (포트 3003) 분리 유지
- `redis` 1 컨테이너 추가
- MariaDB 는 외부 `database-network` 에서 공유
- 단일 `docker compose --profile all up -d` 로 전체 기동

## 리스크

| 리스크 | 완화 |
|---|---|
| admin/user 양쪽에 같은 모듈명 (board/book/event/...) → Bean/URL 충돌 | `com.academy.user.*` 네임스페이스 + URL prefix + `@Service("userXxx")` bean 명시 |
| user 의 레거시 HttpServletRequest 제거 비용 | Sprint 1 의 주요 작업으로 명시. JWT 전환 패턴을 1건 선행 후 템플릿화 |
| user-web Vite 전환 시 빌드 환경 호환성 | 별도 브랜치 `feat/user-web-vite` 에서 1~2일 PoC 선행 |
| Flyway baseline 상태에서 스키마 변경 누락 시 prod 정합성 | V2+ 추가 전 `mysqldump --no-data` 로 현재 스키마를 `docs/db/acm_basic.schema.sql` 에 스냅샷 |

## 후속 결정

- **ADR-002 인증 통합 방식** — Sprint 1 착수 직전
- **ADR-003 공통 Response envelope 포맷** — Sprint 1 착수 직전
- **ADR-004 hopenvision 통합 여부 / 시점** — MVP 완료 이후
