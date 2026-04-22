# @academy/user-web

Academy 수강생 포털 (ADR-006/007 · Vite 7 + React 19 + TypeScript + Ant Design 6 + Tailwind).

## 실행

```bash
# 루트에서
npm install
npm run dev:user         # http://localhost:3003
```

## 화면 (MVP 사용자 5 플로우)

| 경로 | 설명 | 연동 API |
|---|---|---|
| `/login` | 로그인 | POST /api/auth/login (audience=user) |
| `/signup` | 회원가입 | POST /api/auth/signup |
| `/` | 홈 | — (홈 카드 3개) |
| `/lectures` | 강의 목록·검색 | GET /api/user/lecture (+ subject/teacher 필터) |
| `/lectures/:mstCode` | 강의 상세·챕터 | GET /api/user/lecture/{mstCode} |
| `/cart` | 장바구니·주문·결제 | /api/user/cart → /order/from-cart → /payment/mock |
| `/mypage/profile` | 내 정보·비번 변경·탈퇴·수강확인증 | /api/user/mypage/* (P0) |
| `/mypage/mylecture` | 내 강의실 | /api/user/mylecture |
| `/mocktest` | 모의고사 신청·응시 | /api/user/mocktest/* |

각 페이지 `Alert` placeholder — QA 단계에 API 연결 + 폼/테이블 채움.
