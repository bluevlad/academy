# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 실행 환경 감지 (SSH 재접속 금지)

- Claude는 현재 호스트에서 직접 실행 중 — **SSH 재접속을 시도하지 말 것**
- `uname -s` = `Darwin` → MacBook 운영환경 (172.30.1.72), docker/docker compose 직접 실행 가능
- `uname -s` 결과가 Windows/MINGW/MSYS → Windows 개발환경 (172.30.1.100)
- Docker 명령은 현재 호스트에서 바로 실행 (별도 SSH 접속 불필요)
- compose 파일 선택: Darwin → `docker-compose.yml` / Windows → `docker-compose.local.yml`

## Project Overview

Online Academy User Frontend - React Material Dashboard 기반 사용자 포털

## Environment

- **Framework**: React (Material Dashboard)
- **Port**: 4002
- **Domain**: study.unmong.com

## Help Page 관리

> 작성 표준: [HELP_PAGE_GUIDE.md](https://github.com/bluevlad/Ai-Legacy-bluevlad/blob/main/standards/documentation/HELP_PAGE_GUIDE.md)
> HTML 템플릿: [help-page-template.html](https://github.com/bluevlad/Ai-Legacy-bluevlad/blob/main/standards/documentation/templates/help-page-template.html)

- **기능 추가/변경/삭제 시 반드시 헬프 페이지도 함께 업데이트**
- 헬프 파일 위치: `public/help/`
- 서비스 accent-color: `#3b82f6` (Blue)
- 대상 가이드 파일:
  - `user-guide.html` — 사용자 포털 가이드
  - `api-guide.html` — 사용자 API 가이드

## Do NOT

- .env 파일 커밋 금지
- 운영 Docker 컨테이너 직접 조작 금지
