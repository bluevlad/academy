# ADR-006 — FE 재작성: Material Dashboard 제거 + Ant Design 6 채택

- Status: Accepted · 2026-04-22
- Deciders: owner + Claude Code
- 상위: [ADR-001](./ADR-001-integration-strategy.md) · [ADR-004](./ADR-004-track-convergence-criteria.md)

## 문맥

Sprint 0~6 backend 가 완료되어 academy monorepo 가 MVP 11기능을 모두 실 DB 에서 서빙할 준비를 마침.
다만 FE 두 앱은 문제가 누적:

- **admin-web** — Vite 6 + MUI v6 기반이지만 `Material Dashboard 2 React` (Creative Tim) 템플릿 코드가
  layouts/examples 전반에 깔려 있음 → 라이센스(**Creative Tim License**) 상 상용 서비스 재배포 제한 회색 영역
- **user-web** — CRA + MUI v5 + `Material Dashboard` 파생. CRA 는 deprecated, 로그인 경로(`/login/login`) 가
  신규 통합 backend 와 불일치(깨진 상태)
- admin-web 과 user-web 사이 디자인 언어·상태관리·API 클라이언트가 제각각 — 공용 컴포넌트 추출 불가
- hopenvision 참조 UI 가 Ant Design 6 기반 — admin-web/user-web 과 이질적

ADR-001 의 "FE 2개 유지 + user-web 현대화(X1)" 원칙은 유효하되, Sprint 2-4 의 "CRA → Vite+MUI v6" 는
라이센스 이슈를 해결하지 못하므로 **방침 전환** 필요.

## 결정

**두 FE 앱을 Material Dashboard 제거 + Ant Design 6 + 자체 레이아웃으로 완전 재작성** (트랙 B 유지).

### 1. 범위

- `admin-web/` (기존 Vite+MUI+MD) → `apps/admin-web/` 신규
- `user-web/`  (기존 CRA+MUI+MD)  → `apps/user-web/`  신규
- 기존 두 repo 는 `legacy/admin-web-mui/`, `legacy/user-web-cra/` 로 보존 (참조용, gitignore 가능)
- `packages/ui-core/` 실 추출 — Ant Design 기반 공용 컴포넌트·훅

### 2. 보존 / 폐기

| 항목 | 결정 |
|---|---|
| Backend (Spring Boot 3.2 + MyBatis + MariaDB) | **유지** — Sprint 0~6 투자 100% 보존 |
| `acm_basic` DB + Flyway V2~V7 migration | 유지 |
| ADR-002/003/004/005 | 그대로 유효 |
| `@academy/ui-core` Sprint 2-5 초안 | 재작성 (TS + Ant Design 기반으로 완전 교체) |
| `feat/user-web-vite` 브랜치 | **폐기** (MUI 전제 가이드) |
| Material Dashboard 2 React 의존성 + 파생 코드 | **전량 제거** |
| admin-web/user-web 기존 디자인 자산 (로고·이미지 제외) | 기본적으로 **폐기** — 재작성 과정에서 필요 부분만 포팅 |

### 3. 재작성 원칙 (owner 가 명시한 "QA 반복 개선" 방향)

1. **기능 커버리지 우선** — 각 화면은 API 연동 + 기본 레이아웃 수준으로 먼저 구현, 시각 디테일은 후순위
2. **화면 단위 commit** — 로그인 / 회원가입 / 목록 / 상세 각각 작은 commit 으로 점진 누적
3. **QA 단계에서 피드백 루프** — 전체 화면 골격 완성 후 owner 가 브라우저로 순회 → 이슈 리스트 → QA 커밋으로 수정
4. **Lighthouse/접근성/i18n 등 비기능** — MVP 범위 밖, 배포 이후 별도 Sprint

### 4. ADR-004 Exit Gate 와의 관계

- E2E 95% · p95 · 에러율 · mapper 재사용 80%+ · ArchUnit 위반 0 은 그대로 유효
- FE 재작성은 Exit Gate 측정 **시점을 FE 완료 이후로 연기** (그 전에는 BE-only 평가)
- 트랙 B 고정 조건 자체는 변하지 않음

## 결과

- FE 라이센스 리스크 제거
- admin-web/user-web 디자인 언어 통일 → `@academy/ui-core` 유의미하게 공유
- hopenvision 의 UI 패턴을 직접 참조 가능 (같은 Ant Design)
- Sprint 2-4/2-5 의 "CRA → Vite+MUI" 경로는 폐기하되, Vite 인프라 자체는 재활용

## 리스크

| 리스크 | 완화 |
|---|---|
| FE 재작성 기간 4~6주 소요 — backend 기능은 이미 존재하나 사용자가 못 씀 | backend 는 그대로 운영, 기존 academy-admin 9001 유지 + 새 admin-web 준비되면 compose 스위칭 |
| admin-web 의 기존 화면 개수가 많음(회원·강의·과목·교수·쿠폰·교재·모의고사·게시판·이벤트·배너 등) — 범위 폭주 | MVP 11기능 범위만 재작성, 그 외(게시판·배너·이벤트) 는 Plan B 로 이월 + 기존 화면 legacy/ 참조 |
| Ant Design 디자인 언어가 브랜드와 충돌할 수 있음 | ConfigProvider token override (primary=#3b82f6 유지), 필요 시 CSS 최소한의 브랜드 커스텀 |
| 공용 컴포넌트 추상화 과다 — 유지비 증가 | Atomic Design 금지 — `DataTable`/`FormFields`/`AppShell` 등 진짜 중복되는 것만 승격 |
| Legacy 코드 참조 유혹 — 기존 JSX 를 복붙하는 충동 | 원칙적으로 재작성, legacy 폴더는 "비즈니스 로직 의도 이해용" 만 |

## 후속 결정

- **ADR-007 FE 기술 스택** — Ant Design 6 + Tailwind CSS + Recharts + React Hook Form + TanStack Query + Zustand
- 기존 admin-web/user-web 삭제 시점 — main 머지 시 `legacy/*-legacy/` 로 `git mv`
