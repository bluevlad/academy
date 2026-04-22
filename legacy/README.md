# legacy/ — 참조용 원본 코드

academy-integrated 로 흡수·재구성되기 전의 원본 repo 들 + ADR-006 으로 재작성 대상이 된 FE 앱들.

## 분류

| 경로 | 용도 | Git 추적 | 최종 처리 |
|---|---|---|---|
| `legacy/admin-web-mui/` | ADR-006 이전의 Vite+MUI+Material Dashboard 버전 admin-web | **tracked** (재작성 중 참조 필요) | FE 재작성 완료 + 배포 후 삭제 commit |
| `legacy/user-web-cra/` | ADR-006 이전의 CRA+MUI+Material Dashboard 버전 user-web | **tracked** | 위와 동일 |
| `legacy/academy-admin.old/` | 원본 academy-admin repo 복제 (참조 전용) | **untracked** (`.gitignore`) | 로컬에만 보존, SSOT 는 원본 github repo |
| `legacy/academy-user.old/` | 원본 academy-user repo 복제 | **untracked** | 로컬에만 보존 |

## 원본 repo (SSOT)

- `legacy/academy-admin.old/` ← github.com/bluevlad/academy-admin-back-end-JavaSpring + academy-admin-front-end-React
- `legacy/academy-user.old/`  ← github.com/bluevlad/academy-user-back-end-JavaSpring + academy-user-front-end-React-material-dashboard

## 참조 규칙

- **읽기 전용** — legacy 내부 파일 수정 금지
- **복붙 금지** — 신규 `apps/*` 는 Ant Design + TS 로 재작성 (ADR-007). 비즈니스 로직 의도만 파악
- **복구 필요 시** 원본 github repo 에서 clone (별도 경로)

## 재작성 흡수 진행

- [x] admin backend → `backend/` (Spring Boot 통합, Sprint 0)
- [x] user backend → `backend/src/main/java/com/academy/user/*` (Sprint 1~5)
- [ ] admin frontend → `apps/admin-web/` (ADR-006 · Ant Design 6)
- [ ] user frontend  → `apps/user-web/`  (ADR-006 · Ant Design 6)
