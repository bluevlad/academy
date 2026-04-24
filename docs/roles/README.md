# academy — 역할별 메뉴 명세 (DRAFT v0.2)

> **목적**: 공무원/경찰 학원 레거시에서 출발한 기능을 **범용 학원 운영 시스템** 으로 표준화.
> 4개 역할별 메뉴를 단일 출처로 정의하고, PDF 산출물로 운영자에게 배포.
>
> **상태**: 초안 (사용자 검토 → 메뉴 명칭/배치 조정 단계)

## 4 역할 정의

| 역할 | Role enum (제안) | 핵심 책임 | 현재 매핑 |
|------|----------|----------|----------|
| **사용자(학생)** | `STUDENT` | 강의 구매·수강·시험 응시 | 현 `USER` |
| **강사** | `INSTRUCTOR` | 자기 강의/수강생 진도/출제 | 신규 (현재 없음) |
| **운영자** | `OPERATOR` | 회원·강의·주문·교재·시험 운영 | 현 `ADMIN` 의 대부분 |
| **시스템관리자** | `SYS_ADMIN` | 권한·메뉴·코드·로그·환경설정 | 현 `ADMIN` 의 시스템 영역 |

> **마이그레이션 메모**: `STUDENT=USER alias`, `OPERATOR ⊂ ADMIN`, `SYS_ADMIN ⊃ OPERATOR` 로 점진 분화. JWT claim 에 `role` 추가 시 기존 `USER/ADMIN` 토큰 호환 유지 (Role enum 확장).

## 영역 모델 (v0.2)

```
user-web  (academy.unmong.com)
  ├─ 🌐 공개 영역     — 비로그인 가능 (학원소개·교수소개·수강신청·교재·학습정보·멤버쉽·고객센터)
  └─ 🔐 개인 영역     — 로그인 후 (내강의실 · 마이페이지)

admin-web (academy.unmong.com/admin)
  ├─ 🛠️ 운영자 콘솔   — 회원·강의·주문·교재·시험·공개콘텐츠·고객센터 응대
  └─ ⚙️ 시스템 콘솔   — 권한·메뉴·코드·로그·AI라우팅·환경설정

instructor 화면    — user-web 의 `/classroom/instructor` (내강의실 진입시 role 분기)
```

## 문서 구성

| 파일 | 내용 |
|------|------|
| [menu-matrix.md](menu-matrix.md) | 역할 × 메뉴 권한 매트릭스 (단일 출처) |
| [00-public.md](00-public.md) | **공개 영역** 메뉴 (비로그인 가능 — 학원소개·수강신청·교재·학습정보·고객센터 등) |
| [01-student.md](01-student.md) | 학생 개인 영역 (내강의실 · 마이페이지) |
| [02-instructor.md](02-instructor.md) | 강사 메뉴 트리 |
| [03-operator.md](03-operator.md) | 운영자 메뉴 (운영·공개콘텐츠·고객센터 응대) |
| [04-sysadmin.md](04-sysadmin.md) | 시스템관리자 (권한·메뉴·AI라우팅) |

## 관련 표준 문서

- [`../data-strategy.md`](../data-strategy.md) — 데이터 이관·마스킹 표준 (개인정보 더미 + 통계 실데이터 정책)
- [`../workflow/branch-strategy.md`](../workflow/branch-strategy.md) — 브랜치·배포 표준 프로세스

## 표기 규약

- **현행**: 현재 코드에 이미 구현된 메뉴 (`AdminShell.tsx` / `UserShell.tsx`)
- **신규**: 4역할 분화에 따라 새로 추가할 메뉴
- **레거시(공무원/경찰 특화)**: 범용화 시 제거 또는 일반화 검토 대상
- **TBD**: 미정 / 검토 필요

## 검토 가이드 (사용자용)

각 역할 문서를 보면서 다음을 확인:
1. **메뉴 명칭** — "회원관리"·"수강관리" 같은 용어가 학원 표준 용어와 맞는지
2. **메뉴 배치** — 어느 역할이 어느 메뉴에 접근해야 하는지
3. **누락** — 빠진 메뉴 (예: 출결, 공지, 상담 등)
4. **레거시 잔재** — 공무원/경찰 특화 메뉴 중 유지/제거 결정

조정사항은 이 파일에 직접 메모 → 다음 라운드에 반영합니다.

## PDF 출력

```bash
./scripts/docs-to-pdf.sh           # 전체 역할 문서 PDF 생성 → docs/dist/
./scripts/docs-to-pdf.sh student   # 특정 역할만
```
