# 데이터 이관·마스킹 표준 (DRAFT v0.1)

> **확정일**: 2026-04-24
> **적용 범위**: 레거시 (공무원·경찰 학원 운영 DB) → 표준 academy 시스템 이관 + 향후 수집 데이터의 개발/스테이징 환경 노출

## 핵심 원칙

> **"개인정보·민감정보는 모두 더미로 대체. 통계·수치·구조 데이터는 실데이터 활용."**

1. **보존(실데이터)** — 비즈니스 인사이트·통계 가치가 있고 개인 식별이 불가한 영역
2. **대체(더미)** — 직접/간접 식별 가능한 영역 (PII), 초상권·저작권 우려 영역, 본문 콘텐츠
3. **referential 보존** — 동일 인물 → 동일 더미 ID 매핑으로 관계 그래프 유지
4. **운영(prod) DB 직접 노출 금지** — 마스킹은 추출·복제 시점에 수행

## 영역별 정책

### 보존 (실데이터 그대로)
| 영역 | 항목 | 비고 |
|------|------|------|
| 강의 구조 | 과목·강사ID·기수·차시·교재매핑·가격 | 비즈니스 핵심 자산 |
| 수강 통계 | 수강신청 건수·완강률·평점 분포·결제 금액·환불률 | 익명 집계 가능 |
| 시험 통계 | 응시 인원·평균/표준편차·합격률 | 개인 점수는 익명화 후 보존 |
| 교재 | 제목·저자·재고·판매수 | 외부 공개 가능한 정보 |
| 메뉴/코드 | 공통코드·카테고리·메뉴 트리 | 시스템 메타 |
| 시간 메타 | 가입일·수강일·결제일·이벤트 timestamp | 개인 미식별 |

### 대체 (더미 데이터 — 자동 생성)
| 영역 | 컬럼 | 대체 방식 |
|------|------|----------|
| **회원** | name, phone, email, addr, birth, ssn(있다면) | Faker (한국어 로케일) |
| **회원** | password_hash | 표준 dev 비밀번호로 reset (`devpass1234` 등) |
| **강사** | name, photo_url, bio, profile_text, contact | Faker + placeholder 이미지 |
| **결제** | buyer_name, card_no, account_no, receipt | 익명화 (이름→Faker, 카드→`****-****-****-XXXX`) |
| **상담·문의** | content, answer | 카테고리만 보존, 본문은 LLM 가짜 본문 생성 |
| **첨부 파일** | 실 파일 경로 | placeholder 파일 또는 제거 |

### 별도 검토 필요
| 영역 | 사유 | 결정 미정 |
|------|------|----------|
| 합격 후기 | 실명·소속 노출. 가짜 후기 생성 | 카테고리/시험명은 보존, 본문은 더미 |
| 강의 영상 URL | 저작권 영역 | 더미 영상 (placeholder mp4) 또는 비활성화 |
| 강의 자료(PDF) | 저작권 영역 | 더미 PDF (워터마크 placeholder) |

## 이관 파이프라인 (개략)

```
[레거시 운영 DB]
       │
       │ extract (read-only replica 권장)
       ▼
[ETL 워커: scripts/data-migrate/]
   ├─ PII 컬럼 매핑표 기반 자동 치환
   ├─ Faker 로 더미 생성 (locale=ko_KR)
   ├─ 동일 사용자 → 동일 더미 ID (id_map 캐시)
   └─ 본문 콘텐츠 → 로컬 LLM agent 호출해 가짜 본문 생성
       │
       ▼
[표준 시스템 DB (acm_basic on dev/staging)]
```

- **운영 → 운영 직접 복사 금지**. 모든 환경에 마스킹된 데이터만 흐른다.
- 운영 DB 는 진짜 회원 데이터를 보유하지만, 개발자/Claude 는 절대 직접 접근하지 않음.

## 구현 가이드 (향후 Sprint)

### 도구
- **Faker**: Java `net.datafaker:datafaker` 권장 (한국어 로케일 지원). 또는 Python `faker`.
- **본문 생성**: 로컬 LLM agent (별도 서비스, AI 라우팅과 동일 인프라 재사용)
- **PII 컬럼 매핑표**: `scripts/data-migrate/pii-mapping.yml`

### 디렉터리 구조 (제안)
```
scripts/data-migrate/
  ├── pii-mapping.yml         # 테이블·컬럼별 마스킹 정책
  ├── extract.sh              # 레거시 → CSV/SQL dump
  ├── mask.py (또는 .java)    # PII 마스킹 로직
  └── load.sh                 # 표준 시스템 DB 적재
```

### 실행 환경
- 개발/스테이징 환경에만 실행
- 운영 환경에서 실행 시 즉시 abort (스크립트 첫 줄에서 환경 변수 체크)

## CS 게시판 (`acm_board_cs`) 이관 정책

`tb_board_cs` (legacy, 약 5,430건) → `acm_board_cs` 이관 시:

| 원본 컬럼 | 처리 | 비고 |
|----------|------|------|
| `REG_ID` (작성자 ID) | `usr_{sha256[0:8]}` 결정적 해시 → `inquiry_user_id` | 동일 원값→동일 더미 (id_map 캐시) |
| `CREATENAME` (이름) | Faker ko_KR 이름 → `inquiry_name` | user_id 매핑 고정 |
| `COUNSELOR_ID` (담당자 ID) | 동일 해시 매핑 → `answered_by`/`assigned_to` | 직원 프라이버시 |
| `SUBJECT` (제목) | 원본 보존 → `inquiry_title` | 정규식 스크러빙만 (전화·이메일) |
| `CONTENT` / `ANSWER` (longblob) | UTF-8 디코딩 + 정규식 PII 치환 → `inquiry_body` / `answer_body` | 연락처·주민번호·이메일·한글이름(화이트리스트 제외) |
| `CS_DIV` / `CS_KIND` | `legacy_cs_div` / `legacy_cs_kind` 로 보존 | AI Ground Truth 학습용 |
| `REG_DT` (date) | `inquiry_date` (datetime) | 시분초 없음 |

### 본문 스크러빙 정규식

- 휴대폰: `01[016789][- ]?\d{3,4}[- ]?\d{4}` → `010-XXXX-XXXX`
- 주민등록번호: `\d{6}[- ]?[1-4]\d{6}` → `XXXXXX-XXXXXXX`
- 이메일: 표준 패턴 → `[email]`
- 한글 이름 (보수적): 2-4음절 + 상위 20개 성씨 → `[이름]` (화이트리스트 단어 제외)

### 실행 환경 가드

ETL 스크립트는 다음 조건 불충족시 즉시 abort:
- `DATA_MIGRATE_ENV` ∈ {dev, staging}
- `TARGET_DB_HOST` = localhost/127.0.0.1 또는 `staging` 포함 호스트명

### 학습 Ground Truth 활용

Legacy `CS_DIV` 값 (`CSCOUNSEL` / `CSREFUND`) 을 `acm_board_cs.actual_category` 에 매핑 적재:
- `CSCOUNSEL` → `ACADEMIC` (가정, 운영자 재검토 유도)
- `CSREFUND` → `ORDER`
- Phase B 의 AI 분류는 이 초기 값을 ground truth 로 **정확도 측정** 가능 (별도 수동 라벨링 불필요)

## 강사·교수소개 특별 정책

저작권·초상권 우려로 **교수소개 영역은 전면 더미 데이터**:

- 강사 사진 → placeholder 이미지 (성별·연령대만 매칭)
- 강사 실명 → Faker 한국 이름 (referential 보존: 같은 강사ID는 항상 같은 더미 이름)
- 약력·경력 → 템플릿 기반 자동 생성 ("○○대 졸업 / ○○년 강의 경력")
- 담당 과목 매핑은 실데이터 보존 (구조 정보, 비식별)

## 보안·컴플라이언스

- 운영 DB credentials → 1인 (시스템관리자) 만 보유
- 마스킹된 dev DB 도 외부 공개 금지 (재식별 위험 잔존)
- ETL 로그에 PII 노출 금지 (id_map 캐시는 별도 암호화 저장)
- 백업/스냅샷도 마스킹된 데이터만 외부 저장소로

## 관련 문서

- [docs/roles/03-operator.md](roles/03-operator.md) — 운영자가 다루는 데이터 영역
- [docs/roles/04-sysadmin.md](roles/04-sysadmin.md) — 시스템관리자의 백업·로그 권한
- [docs/workflow/branch-strategy.md](workflow/branch-strategy.md) — 브랜치 전략

## 변경 이력

| 일자 | 버전 | 변경 | 작성 |
|------|------|------|------|
| 2026-04-24 | v0.1 | 초안 — 보존/대체 영역 정의, 강사 더미 정책, ETL 골격 | Claude |
