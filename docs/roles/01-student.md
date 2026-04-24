# 학생 (Student) — 메뉴 명세 (DRAFT v0.2)

> **Role**: `STUDENT` (현 `USER` 호환)
> **앱**: `user-web` (https://academy.unmong.com)
> **현행 구현**: `apps/user-web/src/pages/UserShell.tsx:12-35`

## 역할 정의

수강생. 학원 강의를 검색·구매·수강하고, 모의고사 응시·자기 학습 이력 관리.
공개 영역(학원소개·강의 카탈로그·교재·학습정보 등) 은 [00-public.md](00-public.md) 참조.

## 메뉴 트리 (개인 영역 — 로그인 후)

### 🏫 내강의실 (통합 진입점)
| 메뉴 | 경로 | 설명 | 상태 |
|------|------|------|------|
| 내강의실 | `/classroom` | role 기반 분기 진입점 (아래 라우팅 규칙) | 신규 (현 `/mypage/mylecture` 흡수) |
| └ 학생 진입 시 | `/classroom/student` | 수강 중 강의·진도·과제·시험 응시 이력 | 현행 통합 |
| └ 1:1 학습 Q&A | `/classroom/qna` | 강사에게 질문 / 답변 확인 | 신규 |

### 👤 마이페이지
| 메뉴 | 경로 | 설명 | 상태 |
|------|------|------|------|
| 내 정보 | `/mypage/profile` | 프로필·비밀번호·연락처 수정 | 현행 |
| 주문·결제 내역 | `/mypage/orders` | 결제 내역 / 환불 요청 | TBD (신규) |
| 쿠폰·포인트 | `/mypage/benefits` | 보유 쿠폰·포인트 / 사용 이력 | TBD (신규) |
| 장바구니 | `/cart` | 강의·교재 담기 → 결제 | 현행 |
| 알림 | `/mypage/notifications` | 강의·주문·공지 알림함 | TBD (신규) |
| 학습 이력 | `/mypage/history` | 수강 완료 강의·시험 응시 이력 | TBD (신규) |

## 내강의실 라우팅 규칙

```
[클릭: 내강의실]
       │
   ┌───┴────┐
   │ 로그인? │── No → /login?next=/classroom
   └───┬────┘
       │ Yes
   ┌───┴──────────┐
   │ user.role 검사│
   └───┬──────────┘
       ├ STUDENT     → /classroom/student
       ├ INSTRUCTOR  → /classroom/instructor       (02-instructor.md 참조)
       └ OPERATOR
         · SYS_ADMIN → /admin (admin-web 외부 이동)
```

→ 헤더에 항상 노출되는 단일 진입점. role 별로 알아서 분기.

## 검토 필요 (사용자 의견 요청)

1. **"내강의실" 안 구성** — 강의 시청 / 자료 / Q&A / 출결 / 노트 — 어디까지 포함?
2. **모의고사 응시 위치** — 공개 영역 "학습정보 > 시험 일정" 에서 진입 / 또는 내강의실 안 별도 탭?
3. **학습 이력** — 별도 메뉴 vs "내강의실 > 이력 탭" 통합?
4. **알림 채널** — 사이트 내 알림함만 / 이메일·카카오톡 푸시까지?

## 레거시 잔재 검토

| 레거시 기능 | 위치 | 범용화 방향 |
|-----------|------|-----------|
| 공무원 직급별 강의 분류 (DUTYCODE) | `productOrderSQL.xml:2298` | "강의 카테고리" 마스터로 일반화 → 수강신청 정렬 필터 "과목"/"분류" |
| 고시 합격선·경쟁률 | `backend/.../gosi/` | 공개 영역 "학습정보 > 합격 후기·통계" 로 이전 |

## 백엔드 매핑

- 현행: `/api/user/**` → `STUDENT` role 필요 (현 `USER` 호환)
- 신규: `/api/user/classroom/**` (수강 강의실), `/api/user/orders/**` (주문 내역), `/api/user/inquiries/**` (1:1 문의)
- 주요 컨트롤러: `apps/user-web/src/api/*` 참조
