# academy-integrated

> **Academy 통합 학원 운영 시스템** — admin base + user 모듈 흡수 (MVP Sprint 0 착수)

## 아키텍처

```
academy/
├── backend/          ← Spring Boot 3.2 · Java 21 · MyBatis · MariaDB
│   └── com.academy/
│       ├── (기존 admin 모듈 ~38개)   ← 그대로 유지
│       ├── shared/                    ← 공통 인프라 (Sprint 1+ 이관 대상)
│       └── user/                      ← 수강생 모듈 (Sprint 1 에 academy-user 흡수)
├── admin-web/        ← Vite 6 · React 18 · MUI v6 · Zustand (관리자)
├── user-web/         ← CRA · React 18 · MUI v5 · Context API (Sprint 2 에 Vite+MUI6 로 현대화)
├── legacy/           ← academy-admin.old · academy-user.old (이력 보존 read-only)
├── docs/             ← ADR · 스프린트 플랜
├── infra/            ← DB 스크립트, 배포 자산
├── docker-compose.yml
└── .env.example
```

## 포트

| 서비스 | 포트 |
|---|---|
| backend (Spring Boot) | 9000 |
| admin-web | 4001 |
| user-web | 3003 |
| redis | 6379 |

## Quick Start

```bash
# 1) 환경변수
cp .env.example .env
#   DB_PASSWORD, JWT_SECRET 등 반드시 입력

# 2) 전체 기동
docker compose --profile all up -d

# 3) 확인
curl http://localhost:9000/actuator/health
open http://localhost:9000/swagger-ui.html
open http://localhost:4001
open http://localhost:3003
```

로컬 개발 (Docker 없이):
```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

cd ../admin-web
npm install && npm run dev    # http://localhost:5173

cd ../user-web
npm install && npm start       # http://localhost:3000
```

## URL 경로 경계

| Prefix | 대상 | 인증 |
|---|---|---|
| `/api/admin/**` | 관리자 기능 | JWT + ROLE_ADMIN |
| `/api/user/**`  | 수강생 기능 | JWT + ROLE_USER |
| `/api/shared/**` | 공통 (health·me·config) | 선택적 |
| `/swagger-ui.html`, `/api-docs` | API 문서 | dev 에서만 노출 |
| `/actuator/health` | 헬스체크 | 공개 |

## 문서

- [Sprint 로드맵](docs/SPRINTS.md)
- [ADR-001 통합 전략](docs/adr/ADR-001-integration-strategy.md)
- [개발 가이드](CLAUDE.md)

## 출처

| 서브트리 | 원본 repo | 처리 |
|---|---|---|
| `backend/` | [academy-admin-back-end-JavaSpring](https://github.com/bluevlad/academy-admin-back-end-JavaSpring) | Sprint 0 에서 복사, `com.academy.*` 루트 그대로 유지 |
| `admin-web/` | [academy-admin-front-end-React](https://github.com/bluevlad/academy-admin-front-end-React) | 복사, 수정 없음 |
| `user-web/` | [academy-user-front-end-React-material-dashboard](https://github.com/bluevlad/academy-user-front-end-React-material-dashboard) | 복사, Sprint 2 에서 Vite+MUI6 전환 |
| `legacy/` | 위 3개 + academy-user-back-end-JavaSpring | read-only 이력 보존 |

## 상위 구현 플랜

- [DocumetsToAiPipeLine/docs/integration/IMPLEMENTATION_PLAN_v3.md](../DocumetsToAiPipeLine/docs/integration/IMPLEMENTATION_PLAN_v3.md) — hopenvision 기반 MVP v3
- **본 repo** — academy 기반 통합 (hopenvision 이관은 후속 단계)
