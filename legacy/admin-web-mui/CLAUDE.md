# Academy Admin Frontend

## 실행 환경 감지 (SSH 재접속 금지)

- Claude는 현재 호스트에서 직접 실행 중 — **SSH 재접속을 시도하지 말 것**
- `uname -s` = `Darwin` → MacBook 운영환경 (172.30.1.72), docker/docker compose 직접 실행 가능
- `uname -s` 결과가 Windows/MINGW/MSYS → Windows 개발환경 (172.30.1.100)
- Docker 명령은 현재 호스트에서 바로 실행 (별도 SSH 접속 불필요)
- compose 파일 선택: Darwin → `docker-compose.yml` / Windows → `docker-compose.local.yml`

## 프로젝트 개요

학원 관리 시스템 관리자 프론트엔드 (React 18 + Vite 6 + MUI 6)

## 기술 스택

- **빌드**: Vite 6, TypeScript (점진 도입)
- **UI**: MUI 6, Material Dashboard 2 커스텀 테마
- **상태**: Zustand (UI), React Query (서버)
- **HTTP**: Axios (shared/api/client.js)
- **테이블**: TanStack Table 8 (shared/components/DataTable)
- **폼**: React Hook Form + Zod (shared/components/FormFields)
- **에디터**: TipTap (shared/components/RichEditor)
- **다국어**: i18next (shared/i18n)

## 실행

```bash
npm run dev          # Vite 개발 서버 (포트 3000)
npm run build        # 프로덕션 빌드
docker compose up -d --build   # Docker 컨테이너
```

## 연동 백엔드

- **프로젝트**: `../backend/`
- **API**: `http://localhost:9001/api`
- **DB**: MariaDB 172.30.1.72:3306/acm_basic (academyAdmin)
- **CLAUDE.md**: `../backend/CLAUDE.md`

프론트에서 필요한 API가 백엔드에 없으면, 백엔드 프로젝트에서 먼저 구현 후 프론트 연동.

## 리뉴얼 문서

- [리뉴얼 플랜](docs/renewal/RENEWAL_PLAN.md)
- [구현 가이드](docs/renewal/IMPLEMENTATION_GUIDE.md)
- [기술 대체 방안](docs/renewal/TECH_ALTERNATIVES.md)

## Help Page 관리

> 작성 표준: [HELP_PAGE_GUIDE.md](https://github.com/bluevlad/Claude-Opus-bluevlad/blob/main/standards/documentation/HELP_PAGE_GUIDE.md)
> HTML 템플릿: [help-page-template.html](https://github.com/bluevlad/Claude-Opus-bluevlad/blob/main/standards/documentation/templates/help-page-template.html)

- **기능 추가/변경/삭제 시 반드시 헬프 페이지도 함께 업데이트**
- 헬프 파일 위치: `public/help/`
- 서비스 accent-color: `#3b82f6` (Blue)
- 대상 가이드 파일:
  - `admin-guide.html` — 관리자 콘솔 가이드
  - `api-guide.html` — 관리자 API 가이드

## Do NOT

- .env 파일 커밋 금지
- 운영 Docker 컨테이너 직접 조작 금지
