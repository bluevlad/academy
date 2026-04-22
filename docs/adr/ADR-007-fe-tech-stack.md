# ADR-007 — FE 기술 스택 확정 (Ant Design 6 + Tailwind + Recharts)

- Status: Accepted · 2026-04-22
- Deciders: owner + Claude Code
- 상위: [ADR-006](./ADR-006-fe-rewrite-with-ant-design.md)

## 문맥

ADR-006 에서 "FE 재작성 + Material Dashboard 제거" 를 결정. 재작성 시 사용할 구체 라이브러리·도구를 확정 필요.

사용자 요청사항:
- 라이센스 안전
- hopenvision 의 UI 패턴 참조 가능
- "일반적인 React 코드" — 템플릿 대시보드 의존 금지, 원자 컴포넌트 조합

## 결정

### 스택

| 역할 | 라이브러리 | 버전 | 라이센스 | 근거 |
|---|---|---|---|---|
| 빌드 도구 | **Vite** | ^7 | MIT | CRA 는 이미 deprecated, Vite 가 표준 |
| 런타임 | **React** | ^19 | MIT | hopenvision 과 동일 |
| 언어 | **TypeScript** | ^5.5 | Apache-2.0 | **점진 도입** — 신규 파일은 TS, legacy 참조는 JS 유지 |
| UI 라이브러리 | **Ant Design (antd)** | ^6 | MIT | hopenvision 참조 일관성, 한국어 문서 풍부 |
| CSS | **Tailwind CSS** | ^3.4 | MIT | 유틸리티 클래스로 레이아웃 속도 확보 (Ant Design 과 병용) |
| 폼 | **React Hook Form** + **Zod** | ^7 / ^3 | MIT | Ant Design `Form` 도 있지만 Zod 스키마 공유 우선 |
| 서버 상태 | **TanStack Query** | ^5 | MIT | axios interceptor + cache/invalidate |
| 클라이언트 상태 | **Zustand** | ^5 | MIT | Redux 는 과스펙, admin-web 이 이미 Zustand |
| 라우팅 | **React Router** | ^6 | MIT | 업계 표준 |
| HTTP | **axios** | ^1 | MIT | 기존 client.js interceptor 재활용 |
| 차트 | **Recharts** | ^2 | MIT | MVP 차트 수요 낮음 (통계 1~2화면), 경량 |
| 아이콘 | **@ant-design/icons** | ^5 | MIT | Ant Design 표준 아이콘 |
| 에디터 (옵션) | **TipTap** | ^2 | MIT | 기존 admin-web 이 사용 — 게시판 등 Plan B 에서 재활용 |
| 날짜 | **dayjs** | ^1 | MIT | Ant Design 내부 의존 |
| Lint/Format | ESLint + Prettier | latest | MIT | |

### 정책

1. **Ant Design `ConfigProvider` 로 테마 통일**
   ```tsx
   <ConfigProvider theme={{ token: { colorPrimary: '#3b82f6', borderRadius: 6 } }}>
   ```
2. **Tailwind 는 유틸리티 용만** — 컴포넌트 전체 구조는 Ant Design. Tailwind 는 `flex gap-2`, `mt-4` 등 간격·정렬 보조
3. **"Material Dashboard 같은" 템플릿 import 금지** — `ProLayout` 등 꾸러미 제외. Atomic 만 (`Layout`·`Menu`·`Table`·`Form`·`Modal`)
4. **자체 AppShell** — 사이드바·헤더·PageContainer 는 `@academy/ui-core/layout` 에 직접 작성
5. **각 앱은 `ui-core` 를 `workspace:*` 로 참조** — monorepo npm workspaces 도입
6. **TypeScript 점진** — 새 파일 `.tsx`, 외부 라이브러리 type 은 선언. legacy 에서 가져오는 JS 는 `.jsx` 로 버림

### monorepo 구조

```
academy/
├── apps/
│   ├── admin-web/              # Vite 7 + React 19 + TS + Ant Design
│   └── user-web/               # 동일 스택
├── packages/
│   └── ui-core/                # @academy/ui-core (Ant Design 기반 공통)
├── legacy/
│   ├── admin-web-mui/          # 기존 Vite+MUI 버전 (참조)
│   └── user-web-cra/           # 기존 CRA 버전 (참조)
├── backend/                    # 그대로
├── docs/
├── infra/
└── docker-compose.yml          # apps/admin-web, apps/user-web 경로로 업데이트
```

### 라이센스 검증

- `antd` 6 · React 19 · Vite 7 · TanStack Query · Zustand · Recharts · Tailwind · dayjs — **전부 MIT**
- TypeScript — Apache-2.0 (상용 OK)
- React Hook Form · Zod · axios · React Router — MIT
- **Material Dashboard 2 React** (Creative Tim License) → **전량 제거 대상**
- @mui/material (MIT) → Ant Design 로 대체하므로 같이 제거

### 번들 크기 목표

| 앱 | 초기 JS (gzip) | 목표 |
|---|---|---|
| admin-web | < 400 KB | MVP 기준, 차트 lazy load |
| user-web  | < 300 KB | 로그인/목록 중심, 가벼움 |

Ant Design tree-shaking 이 자동으로 쓰임. `import { Button } from 'antd'` 식.

## 결과

- 라이센스 깨끗
- admin-web/user-web 동일 스택 → 공용 컴포넌트 추출 자연스러움
- hopenvision 과 UI 패턴 호환 (같은 Ant Design)
- TypeScript 도입 기반 마련

## 리스크

| 리스크 | 완화 |
|---|---|
| Ant Design v6 는 비교적 최근 (breaking changes) | 공식 마이그레이션 가이드 참조, MVP 는 최신 6.x 기준 |
| Tailwind + Ant Design 동시 사용 시 className 충돌 | Tailwind prefix 설정 X (자연스러운 사용), `!important` 피하는 원칙 |
| TS 점진 도입으로 JS/TS 혼재 | 새 파일만 TS, 기존 JS 참조 시 `@ts-ignore` 허용 (MVP 기간) |
| React 19 + Ant Design 호환성 | Ant Design v6 가 React 19 공식 지원 확인됨 |

## 후속

- `@academy/ui-core` 구조 세부는 구현 과정에서 확립 (Component vs Hook 분리, theme 파일 등)
- Storybook 도입 여부 — MVP 에는 생략, QA 이후 별도 결정
- 테스트 — Vitest + Testing Library (MVP 는 E2E 위주, unit 은 ui-core 핵심만)
