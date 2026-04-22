# @academy/admin-web

Academy 관리자 포털 (ADR-006/007 · Vite 7 + React 19 + TypeScript + Ant Design 6 + Tailwind).

## 실행

```bash
# 루트에서
npm install              # workspaces 전체 설치
npm run dev:admin        # http://localhost:4001
```

## 구조

```
apps/admin-web/src/
├── api/
│   └── client.ts        # @academy/ui-core createApiClient 래퍼
├── pages/
│   ├── LoginPage.tsx
│   ├── AdminShell.tsx   # Sidebar + Header + Outlet (@academy/ui-core AppShell)
│   ├── DashboardPage.tsx
│   ├── members/
│   ├── lectures/
│   ├── orders/
│   ├── coupons/
│   ├── books/
│   └── mocktest/
├── styles/
│   └── index.css        # Tailwind directives
├── App.tsx              # 라우트 정의
└── main.tsx             # ConfigProvider + QueryClient + AuthProvider 조립
```

## 화면 (MVP 관리자 6 기능)

1. 대시보드 (통계 — Sprint 6 QA 에서 실 API 연결)
2. 회원관리 — /api/member/*
3. 강의·과목·교수진 — /api/lecture/*, /subject/*, /teacher/*
4. 수강(주문)관리 — od_order, en_enrollment (Sprint 3)
5. 포인트·쿠폰 — pt_coupon, pt_mileage_ledger (Sprint 4)
6. 교재·배송 — bk_book, bk_delivery (Sprint 4)
7. 모의고사 — ex_mock_exam, ex_mock_attempt (Sprint 5)

각 페이지는 현재 placeholder. QA 단계에서 API 연결 + 테이블/폼 구현.

## 테마

- Ant Design ConfigProvider + `@academy/ui-core` `academyTheme`
- primaryColor `#3b82f6`, borderRadius 6
- Tailwind 는 유틸리티 (`flex`, `gap`, `mt-4` 등) 용만 — `preflight: false` 로 Ant Design 과 공존
