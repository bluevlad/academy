# academy — 문서 위치 안내

> **모든 프로젝트 문서는 `Claude-Opus-bluevlad/services/academy/` 로 이관되었습니다.**
>
> ENVIRONMENT_STANDARD §1 (Single Source of Truth) 에 따라 서비스 설계·운영 문서는
> 메타 repo 의 `services/<name>/` 아래에 집중해 관리합니다. 본 `docs/` 폴더는
> 과거 호환을 위한 포인터만 유지합니다.

## 이관된 문서 목록

| 주제 | 새 경로 |
|---|---|
| 스프린트 로드맵 | [`services/academy/SPRINTS.md`](../../Claude-Opus-bluevlad/services/academy/SPRINTS.md) |
| 배포 체크리스트 | [`services/academy/DEPLOYMENT_CHECKLIST.md`](../../Claude-Opus-bluevlad/services/academy/DEPLOYMENT_CHECKLIST.md) |
| CI/CD 셋업 | [`services/academy/CI_CD_SETUP.md`](../../Claude-Opus-bluevlad/services/academy/CI_CD_SETUP.md) |
| 서비스 개요 | [`services/academy/README.md`](../../Claude-Opus-bluevlad/services/academy/README.md) |
| Claude 작업 가이드 | [`services/academy/CLAUDE.service.md`](../../Claude-Opus-bluevlad/services/academy/CLAUDE.service.md) |
| ADR (설계 결정서) | [`services/academy/adr/`](../../Claude-Opus-bluevlad/services/academy/adr/) |
| DB 스키마 노트 | [`services/academy/db/README.md`](../../Claude-Opus-bluevlad/services/academy/db/README.md) |
| API 모듈별 가이드 | [`services/academy/api/`](../../Claude-Opus-bluevlad/services/academy/api/) |

## ADR 인덱스 (바로가기)

- ADR-001 — integration strategy
- ADR-002 — auth scheme (JWT access/refresh · admin DB · USER 포트)
- ADR-003 — response envelope (`ApiResponse<T>`)
- ADR-004 — track convergence criteria
- ADR-005 — db table strategy (prefix `id_`, `acm_`)
- ADR-006 — FE rewrite (Ant Design 6)
- ADR-007 — FE tech stack

## 절대 경로 (메타 repo 기준)

```
/Users/rainend/GIT/Claude-Opus-bluevlad/services/academy/
├── README.md
├── CLAUDE.service.md
├── SPRINTS.md
├── DEPLOYMENT_CHECKLIST.md
├── CI_CD_SETUP.md
├── adr/
│   └── ADR-001 ~ 007*.md
├── db/
│   └── README.md
└── api/
    ├── API_OVERVIEW.md
    └── *_README.md  (모듈별)
```

## 변경 원칙

- 새 ADR/설계 문서는 **무조건 `Claude-Opus-bluevlad/services/academy/` 하위에 작성**
- 이 `docs/` 폴더에는 더 이상 문서를 추가하지 않음 (포인터만 유지)
- 프로젝트 루트 [`CLAUDE.md`](../CLAUDE.md) 의 참조 링크도 위 경로를 사용
