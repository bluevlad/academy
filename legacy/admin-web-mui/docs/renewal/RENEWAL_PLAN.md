# Academy Admin Front-End 리뉴얼 플랜 가이드

## 1. 개요

### 1.1 목적
Material Dashboard 2 (CRA) 기반의 기존 프론트엔드를 현대적 기술 스택으로 점진적 전환하여,
개발 생산성, 유지보수성, 사용자 경험을 개선한다.

### 1.2 원칙
- **점진적 전환**: 전체 재작성이 아닌 단계별 마이그레이션
- **기능 유지**: 기존 20개+ 관리 페이지의 기능을 100% 보존
- **백엔드 독립**: 백엔드 API 변경 없이 프론트엔드만 전환
- **병행 운영**: 전환 중에도 기존 기능 정상 동작 보장

### 1.3 현재 상태 진단

| 영역 | 현재 기술 | 문제점 |
|------|-----------|--------|
| 빌드 도구 | CRA (react-scripts 5.0.1) | 유지보수 중단, 빌드 속도 느림 |
| HTTP 클라이언트 | Superagent + Axios 혼용 | 이중 관리, 인터셉터 불일치 |
| 상태관리 | Context + MobX(미사용) + React Query | MobX 14개 Store 껍데기 방치 |
| UI 프레임워크 | Material Dashboard 2 (MUI 5.12) | MD* 커스텀 컴포넌트 과도한 래핑 |
| 테이블 | react-table 7 (Legacy) | 공식 지원 종료 |
| 폼 관리 | 수동 state 관리 | 검증/에러 처리 비체계적 |
| i18n | 라이브러리만 설치, 미구현 | 한/영 하드코딩 혼재 |
| 라우팅 | routes.js 단일 배열 | 코드 스플리팅 없음 |
| 타입 안전성 | JavaScript + PropTypes | 런타임 에러 취약 |

---

## 2. 리뉴얼 Phase 구성

### Phase 1: 인프라 현대화 (기반 교체)
> 소스 코드 최소 변경으로 개발 환경과 기반 도구를 교체한다.

| 단계 | 작업 | 설명 |
|------|------|------|
| 1-1 | CRA → Vite 전환 | 빌드 도구 교체 |
| 1-2 | TypeScript 점진 도입 | allowJs: true로 공존, 새 파일부터 적용 |
| 1-3 | HTTP 클라이언트 통합 | Superagent 제거, Axios 단일화 |
| 1-4 | 상태관리 정리 | MobX 제거, Context → Zustand 전환 |

### Phase 2: UI/UX 현대화 (화면 리뉴얼)
> 사용자 인터페이스와 개발 패턴을 현대화한다.

| 단계 | 작업 | 설명 |
|------|------|------|
| 2-1 | MUI 6 업그레이드 | MD* 래퍼 제거, 테마 커스텀 |
| 2-2 | 라우팅 개선 | nested routes + lazy loading |
| 2-3 | 테이블 교체 | TanStack Table 8 도입 |
| 2-4 | 폼 관리 체계화 | React Hook Form + Zod |
| 2-5 | 에디터 교체 | react-quill → TipTap |
| 2-6 | 차트 유지/개선 | Chart.js 유지 또는 Recharts 검토 |

---

## 3. 실행 우선순위

| 순서 | 작업 | 영향도 | 기존 코드 변경 범위 | 선행 조건 |
|------|------|--------|---------------------|-----------|
| 1 | CRA → Vite 전환 | 높음 | 설정 파일만 | 없음 |
| 2 | TypeScript + API 타입 정의 | 높음 | 점진적 | Vite 전환 완료 |
| 3 | Superagent 제거 + API 훅 패턴 | 중간 | API 모듈 전체 | TypeScript 도입 |
| 4 | MobX 제거 + Zustand 도입 | 낮음 | stores/ + context/ | 없음 |
| 5 | 라우팅 lazy load | 중간 | routes.js + App.js | Vite 전환 완료 |
| 6 | 폼 체계화 (RHF + Zod) | 중간 | 페이지별 순차 | TypeScript 도입 |
| 7 | 테이블 교체 (TanStack Table 8) | 중간 | 페이지별 순차 | TypeScript 도입 |
| 8 | MD* 래퍼 제거 + MUI 6 | 높음 | 전체 컴포넌트 | Phase 1 완료 |

---

## 4. 기술 스택 전환 맵

```
[현재]                          [리뉴얼 후]
CRA 5                    →     Vite 6
JavaScript               →     TypeScript (strict 점진 적용)
MUI 5 + MD2 래퍼          →     MUI 6 (래퍼 제거, 테마 커스텀)
Context + MobX(미사용)     →     Zustand
Superagent + Axios        →     Axios 단일
React Query               →     React Query (유지, 커스텀 훅 패턴)
React Router 6 (flat)     →     React Router 6 (nested + lazy)
react-table 7             →     TanStack Table 8
수동 state + Yup          →     React Hook Form + Zod
react-quill               →     TipTap
Chart.js                  →     Chart.js (유지)
i18next (미구현)           →     i18next (실제 적용)
```

---

## 5. 프로젝트 구조 (목표)

```
src/
├── app/                        # 앱 설정
│   ├── App.tsx                  # 루트 컴포넌트
│   ├── routes.tsx               # 라우트 정의 (lazy loading)
│   ├── providers.tsx            # Provider 조합 (QueryClient, Theme 등)
│   └── theme/                   # MUI 6 테마 (light/dark)
│       ├── index.ts
│       ├── palette.ts
│       ├── typography.ts
│       └── components.ts
├── shared/                      # 공용 모듈
│   ├── api/                     # Axios 인스턴스, 인터셉터
│   │   ├── client.ts
│   │   └── interceptors.ts
│   ├── components/              # 공통 UI 컴포넌트
│   │   ├── DataTable/
│   │   ├── FormFields/
│   │   ├── Modal/
│   │   ├── Layout/              # DashboardLayout, Sidenav, Navbar
│   │   └── Feedback/            # Alert, Snackbar, Loading
│   ├── hooks/                   # 공통 훅 (useAuth, usePagination 등)
│   ├── stores/                  # Zustand 글로벌 스토어
│   │   ├── useUIStore.ts
│   │   └── useAuthStore.ts
│   ├── types/                   # 공통 타입 (Pagination, ApiResponse 등)
│   └── utils/                   # 유틸 함수
├── features/                    # 기능별 모듈
│   ├── auth/                    # 인증
│   │   ├── api.ts               # sign-in, sign-up, profile API
│   │   ├── hooks.ts             # useSignIn, useProfile
│   │   ├── types.ts             # LoginRequest, UserProfile
│   │   └── pages/
│   │       ├── SignIn.tsx
│   │       ├── SignUp.tsx
│   │       └── ResetPassword.tsx
│   ├── dashboard/               # 대시보드
│   ├── board/                   # 게시판 (TB_BOARD, TB_BOARD_MNG)
│   ├── lecture/                 # 강의 관리 (온라인/오프라인)
│   ├── exam/                    # 시험 관리 (고시/모의)
│   ├── member/                  # 회원 관리
│   ├── order/                   # 주문 관리 (상품/쿠폰/무료수강)
│   ├── event/                   # 이벤트
│   ├── counsel/                 # 상담
│   ├── coop/                    # 협력사
│   ├── book/                    # 교재
│   ├── banner/                  # 배너
│   ├── dday/                    # D-Day
│   ├── note/                    # 쪽지
│   ├── popup/                   # 팝업
│   ├── survey/                  # 설문
│   ├── stat/                    # 통계
│   ├── manage/                  # 경영관리 (매출/강의/정산)
│   └── admin/                   # 시스템관리
│       ├── code/                # 공통코드 관리
│       ├── menu/                # 메뉴 관리
│       ├── auth/                # 권한 관리
│       └── banner/              # 배너 관리
└── types/                       # 글로벌 타입
    └── index.ts
```

---

## 6. 백엔드 API 매핑

현재 백엔드에서 제공하는 API 모듈과 프론트엔드 feature 매핑:

| 백엔드 API 경로 | 프론트엔드 feature | 주요 기능 |
|-----------------|-------------------|-----------|
| `/api/auth` | `features/auth` | 로그인, 회원가입, 프로필 |
| `/api/dashboard` | `features/dashboard` | 대시보드 통계 |
| `/api/board` | `features/board` | 게시판 CRUD, 댓글, 파일 |
| `/api/lecture` | `features/lecture` | 온라인/오프라인 강의 관리 |
| `/api/exam`, `/api/gosi` | `features/exam` | 시험, 고시 관리 |
| `/api/mocktest` | `features/exam` | 모의고사 관리 |
| `/api/member` | `features/member` | 회원 관리 |
| `/api/productorder` | `features/order` | 상품 주문, 쿠폰 |
| `/api/freeOrder` | `features/order` | 무료수강 등록 |
| `/api/event` | `features/event` | 이벤트 관리 |
| `/api/counsel` | `features/counsel` | 상담 예약/관리 |
| `/api/coop` | `features/coop` | 협력사 관리 |
| `/api/book` | `features/book` | 교재 관리 |
| `/api/banner` | `features/banner` | 배너 관리 |
| `/api/dday` | `features/dday` | D-Day 관리 |
| `/api/note` | `features/note` | 쪽지 관리 |
| `/api/popup` | `features/popup` | 팝업 관리 |
| `/api/survey` | `features/survey` | 설문 관리 |
| `/api/stat` | `features/stat` | 통계 |
| `/api/manage` | `features/manage` | 매출/강의/정산 |
| `/api/admin/code` | `features/admin/code` | 공통코드 관리 |
| `/api/admin/menu` | `features/admin/menu` | 메뉴 관리 |
| `/api/admin/auth` | `features/admin/auth` | 권한 관리 |
| `/api/menu` | `features/admin/menu` | 사용자 메뉴 |
| `/api/box` | `features/box` | 사물함 관리 |
| `/api/locker` | `features/box` | 라커 관리 |

---

## 7. 마일스톤

### M1: 인프라 전환 완료 (Phase 1) - 2026-04-07 완료
- [x] Vite 6 빌드 정상 동작 (CRA → Vite 전환, dev 220ms)
- [x] TypeScript 설정 완료 (allowJs: true, strict: false), API 공통 타입 정의
- [x] Axios 단일화 (shared/api/client.js), Superagent 제거 (35개 파일 전환)
- [x] MobX 제거, Zustand 도입 (useAuthStore)
- [x] 불필요 패키지 정리 (moment-timezone, qs 등)

### M2: 핵심 페이지 리뉴얼 (Phase 2 전반) - 2026-04-07 공통 컴포넌트 완료
- [x] 라우팅 lazy loading 적용 (50개+ 컴포넌트, 초기 번들 60% 감소)
- [x] ServerDataTable 공통 컴포넌트 생성 (TanStack Table 8, 서버사이드 페이지네이션)
- [x] 공통 폼 필드 생성 (React Hook Form + Zod + FormTextField/FormSelect/FormSwitch)
- [x] TipTap 리치 에디터 공통 컴포넌트 (react-quill 대체)
- [x] i18n 설정 완료 (한국어/영어 로케일)
- [x] 게시판 목록/관리 페이지에 공통 컴포넌트 적용 (대표 패턴)

### M3: 전체 페이지 전환 완료 (Phase 2 후반) - 2026-04-07 인프라 완료
- [x] MUI 5.12 → MUI 6.5 업그레이드
- [x] Context → Zustand 브릿지 패턴 적용 (17개 파일 무변경 호환)
- [x] 게시판 목록: ServerDataTable (TanStack Table 8) 적용
- [x] 게시판 관리: React Hook Form + Zod 적용
- [ ] 나머지 feature 페이지에 공통 컴포넌트 순차 적용
- [ ] MD* 래퍼 컴포넌트 점진적 정리
- [ ] 다크모드/RTL 테마 정상 동작 검증

---

## 8. 관련 문서

- [초기 구현 가이드](./IMPLEMENTATION_GUIDE.md) - Phase 1 상세 구현 방법
- [기술 대체 방안](./TECH_ALTERNATIVES.md) - 기술 선택 비교 및 대체안
- [CI/CD 설정](../CI_CD_SETUP.md) - 배포 파이프라인
