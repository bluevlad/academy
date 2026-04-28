# academy-integrated

> **Academy 통합 학원 운영 시스템** — Sprint 0~6 backend 완료, ADR-006/007 기반 FE 재작성 중 (feat/fe-ant-design-rewrite).

---

## ⚠️ [SUSPENDED] 단독 docker 운영 중단 — 2026-04-28

이 repo 의 docker compose 운영은 **중단**되었습니다.
academy 가 **hopenvision 통합 환경으로 흡수**되어, 운영은 hopenvision 쪽 단일 compose 에서 관리합니다.

| 항목 | 상태 |
|---|---|
| 컨테이너 (backend·admin-web·user-web·redis·agent) | ⛔ 모두 stop + remove 완료 |
| 이미지 | ✅ 보존 (`docker images | grep academy`) — rebuild 없이 재기동 가능 |
| 볼륨 `academy_redis_data` | ✅ 보존 — 삭제 금지 |
| 자동 재시작 정책 | 🚫 모든 compose 의 `restart: "no"` — **서버/도커 재부팅 시 자동 기동되지 않음** |
| GitHub Actions `deploy-prod.yml` | 🚫 push 트리거 주석 처리, `workflow_dispatch` 수동 실행만 가능 |
| 소스 / `docker-compose.yml` / `Dockerfile` | ✅ 보존 — 단독 재개 가능성을 위해 그대로 둠 |

### 단독 재개가 필요한 경우 (긴급 시나리오 한정)

> ⚠️ 정상 운영은 hopenvision 으로 가세요. 아래는 hopenvision 다운 등 비상 시나리오 전용.

1. hopenvision 에서 academy 가 분리된 상태인지 확인 — 포트 9001 / 4001 / 4002 / 6379 / 9011 충돌 주의
2. 환경변수 SSoT 링크 확인: `/Users/rainend/GIT/Claude-Opus-bluevlad/infrastructure/docker/.env.production`
3. 기동:
   ```bash
   cd /Users/rainend/GIT/academy
   docker compose --profile all up -d
   ```
4. 운영 종료 즉시:
   ```bash
   docker compose --profile all down
   ```
5. 다시 영구 운영하려면 각 compose 파일의 `restart: "no"` → `unless-stopped` 로 되돌리고, `deploy-prod.yml` 의 `push:` 트리거 주석을 풀어야 함.

---

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

# 3) 통합 (Docker compose)  — ⚠️ SUSPENDED, hopenvision 통합으로 운영 이관됨
#    상단 [SUSPENDED] 섹션 참조. 비상 시나리오 외에는 사용 금지.
# docker compose --profile all up -d
```

## URL 경로 경계 (Sprint 1-5 · ADR-002)

| Prefix | 대상 | 인증 필터 |
|---|---|---|
| `/api/admin/**` · 그 외 `/api/<module>/**` | 관리자 (기존 모듈 전부 포함) | AdminJwtAuthenticationFilter + `hasRole("ADMIN")` |
| `/api/user/**` | 수강생 | UserJwtAuthenticationFilter + `hasRole("USER")` |
| `/api/auth/**` | 로그인·가입·리프레시·OAuth | permitAll |
| `/api/shared/**`, `/actuator/health`, `/swagger-ui/**` | 공용 | permitAll |

## 주요 문서

> 프로젝트 문서는 메타 repo `Claude-Opus-bluevlad/services/academy/` 로 이관되었습니다.
> 현재 폴더의 [`docs/README.md`](docs/README.md) 에 전체 색인이 있습니다.

**ADR** — `../Claude-Opus-bluevlad/services/academy/adr/`
- ADR-001 통합 전략 · ADR-002 인증 통합 · ADR-003 Response envelope
- ADR-004 트랙 수렴 기준 · ADR-005 DB 테이블 전략 · ADR-006 FE 재작성 · ADR-007 FE 기술 스택

**Sprint · 배포** — `../Claude-Opus-bluevlad/services/academy/`
- `SPRINTS.md` — Sprint 로드맵 + P0 매핑
- `DEPLOYMENT_CHECKLIST.md` — 배포 체크리스트
- `CI_CD_SETUP.md` — CI/CD 셋업
- `api/` — 모듈별 API 가이드

## 스택 요약

**Backend**: Spring Boot 3.2 · Java 21 · MyBatis · MariaDB · Redis · Flyway · ArchUnit
**FE**: Vite 7 · React 19 · TypeScript · Ant Design 6 · Tailwind · TanStack Query · Zustand · React Hook Form + Zod · React Router 6 · axios
**Infra**: Docker compose 4 컨테이너 · 외부 `database-network` 공유

## 커밋 히스토리 요약

`main`: Sprint 0~6 BE 완료 (14 commits) · `feat/fe-ant-design-rewrite`: FE 재작성 진행 중.

BE 테스트 현황: **58 run / 6 skip / 0 fail** (ArchUnit 5 rules 포함).
