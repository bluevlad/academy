# academy-integrated

> **Academy 통합 학원 운영 시스템** — 단일 Docker compose 로 관리자·수강생 풀 스택 배포.
> Sprint 0~6 backend 완료, ADR-006/007 기반 FE 재작성 중 (feat/fe-ant-design-rewrite).

## 아키텍처

```
academy/
├── backend/              ← Spring Boot 3.2 · Java 21 · MyBatis · MariaDB · Redis
│   └── com.academy/
│       ├── shared/                    ← 공통 (auth/security/admin/common)
│       ├── user/                      ← 수강생 도메인 (login/signup/mypage/content/cart/order/...)
│       ├── auth/ login/ lecture/ ... ← 기존 admin 모듈 (그대로 유지)
│       └── user/                      ← Sprint 1~5 신규 BE
├── apps/                 ← npm workspaces FE 앱 (ADR-006/007)
│   ├── admin-web/        ← Vite 7 · React 19 · TS · Ant Design 6 · Tailwind (port 4001)
│   └── user-web/         ← 동일 스택                                         (port 3003)
├── packages/
│   └── ui-core/          ← @academy/ui-core — theme · AppShell · DataTable · api · auth
├── legacy/               ← 참조용
│   ├── admin-web-mui/    ← 구 MUI 버전 admin-web (FE 재작성 중 참조, 완료 후 삭제)
│   ├── user-web-cra/     ← 구 CRA 버전 user-web (동일)
│   ├── academy-admin.old/ ← 원본 academy-admin (.gitignore)
│   └── academy-user.old/  ← 원본 academy-user  (.gitignore)
├── docs/                 ← ADR 7건, SPRINTS, DEPLOYMENT_CHECKLIST
├── infra/
├── docker-compose.yml    ← 4 컨테이너 (backend · admin-web · user-web · redis)
├── package.json          ← npm workspaces 루트
└── .env.example
```

## 포트

| 서비스 | 포트 (host) | 비고 |
|---|---|---|
| backend (Spring Boot) | 9000 | `/api`, `/actuator`, `/swagger-ui.html` |
| admin-web | 4001 | Ant Design + Tailwind |
| user-web | 3003 | 동일 |
| redis | 6379 | JWT refresh · 캐시 |

## Quick Start

```bash
# 1) 환경변수
cp .env.example .env
#    DB_PASSWORD, JWT_SECRET 반드시 설정

# 2) 로컬 개발 (Docker 없이, 빠른 iteration)
cd backend && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
# 다른 터미널:
npm install                 # workspaces 설치 (루트에서)
npm run dev:admin            # http://localhost:4001
npm run dev:user             # http://localhost:3003

# 3) 통합 (Docker compose)
docker compose --profile all up -d
curl http://localhost:9000/api/shared/health
open http://localhost:4001
open http://localhost:3003
```

## URL 경로 경계 (Sprint 1-5 · ADR-002)

| Prefix | 대상 | 인증 필터 |
|---|---|---|
| `/api/admin/**` · 그 외 `/api/<module>/**` | 관리자 (기존 모듈 전부 포함) | AdminJwtAuthenticationFilter + `hasRole("ADMIN")` |
| `/api/user/**` | 수강생 | UserJwtAuthenticationFilter + `hasRole("USER")` |
| `/api/auth/**` | 로그인·가입·리프레시·OAuth | permitAll |
| `/api/shared/**`, `/actuator/health`, `/swagger-ui/**` | 공용 | permitAll |

## 주요 문서

**ADR**
- [ADR-001 통합 전략](docs/adr/ADR-001-integration-strategy.md)
- [ADR-002 인증 통합 (Stateless JWT + Redis Refresh)](docs/adr/ADR-002-auth-scheme.md)
- [ADR-003 Response envelope](docs/adr/ADR-003-response-envelope.md)
- [ADR-004 트랙 수렴 기준](docs/adr/ADR-004-track-convergence-criteria.md)
- [ADR-005 DB 테이블 전략 (하이브리드)](docs/adr/ADR-005-db-table-strategy.md)
- [ADR-006 FE 재작성 (Ant Design 전환)](docs/adr/ADR-006-fe-rewrite-with-ant-design.md)
- [ADR-007 FE 기술 스택](docs/adr/ADR-007-fe-tech-stack.md)

**Sprint · 배포**
- [Sprint 로드맵 + P0 매핑](docs/SPRINTS.md)
- [배포 체크리스트](docs/DEPLOYMENT_CHECKLIST.md)

## 스택 요약

**Backend**: Spring Boot 3.2 · Java 21 · MyBatis · MariaDB · Redis · Flyway · ArchUnit
**FE**: Vite 7 · React 19 · TypeScript · Ant Design 6 · Tailwind · TanStack Query · Zustand · React Hook Form + Zod · React Router 6 · axios
**Infra**: Docker compose 4 컨테이너 · 외부 `database-network` 공유

## 커밋 히스토리 요약

`main`: Sprint 0~6 BE 완료 (14 commits) · `feat/fe-ant-design-rewrite`: FE 재작성 진행 중.

BE 테스트 현황: **58 run / 6 skip / 0 fail** (ArchUnit 5 rules 포함).
