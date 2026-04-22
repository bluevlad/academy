# ADR-005 — DB 테이블 전략 (하이브리드: 기존 TB_* 유지 + 신규 MSA prefix)

- Status: Accepted · 2026-04-22
- Deciders: owner + Claude Code
- 상위: [ADR-001](./ADR-001-integration-strategy.md) · [IMPLEMENTATION_PLAN_v3](../../../DocumetsToAiPipeLine/docs/integration/IMPLEMENTATION_PLAN_v3.md)

## 문맥

`acm_basic` DB 에는 두 종류 테이블이 혼재:
- `acm_*` 신규 — academy-admin 시기에 추가
- `TB_*` — 2012 윌비스 WCA Oracle 스키마에서 MySQL 로 변환된 25년 축적 레거시
  - Phase 3 Gap 분석: academy 169 테이블 중 **127개(75%) 가 윌비스 레거시와 공통**
  - MyBatis mapper 400+ 가 `TB_*` 에 의존

Sprint 1~5 에서 user BE 22 모듈 + MVP 신규 기능 (회원가입·약관·수강신청·주문·쿠폰·교재·모의고사) 을 이관·추가해야 함.

## 결정

### C. 하이브리드 — 기존 유지 + 신규 확장

**원칙**:
1. **`TB_*` 는 읽기·쓰기 그대로 유지**. MyBatis mapper 재사용 100%.
2. **신규 기능의 데이터**는 MSA prefix 로 신규 테이블로 추가. 기존 테이블과는 FK 또는 1:1 JOIN 으로 연결.
3. Flyway baseline `V1` 은 `TB_*` + `acm_*` 현재 상태를 **동결**. `V2+` 는 신규 테이블만 추가.
4. `TB_*` 정규화·리네이밍은 **하지 않음** (MVP 범위 밖).

### Prefix 규칙 (MSA 9 경계와 일치)

| Prefix | MSA | 용도 |
|---|---|---|
| `id_` | identity-svc | 회원 확장, 약관, OAuth, 관리자 계정 |
| `ct_` | content-svc | (현재는 `TB_LEC_MST`·`TB_SUBJECT_INFO` 재사용) 신규 컬럼은 `ct_*_ext` |
| `en_` | enrollment-svc | 수강권, 진도, 수강 상태 |
| `od_` | order-svc | 주문 확장, 결제 메타, 환불 |
| `pt_` | point-svc | 쿠폰, 마일리지 원장 |
| `bk_` | book-svc | 교재, 배송, 배송지 |
| `pl_` | platform-svc | 공통코드, 메뉴, 권한 |
| `ex_` | exam-svc | (기존 `TB_EXAM_*` 재사용), 신규는 `ex_*` |
| `op_` | operation-svc | 사물함, 독서실 (MVP 이후) |

### Flyway 운영

- `V1__baseline_acm_basic.sql` — marker 전용 (현재 상태)
- `docs/db/acm_basic.schema.sql` — `mysqldump --no-data acm_basic` 결과 스냅샷 (Sprint 1 착수 전 생성)
- `V2__mvp_identity.sql` 부터 신규 테이블 append-only
- **`TB_*` 변경 금지**. 컬럼 추가가 필요하면 `id_*_ext` 로 새 테이블 + 1:1 JOIN
- `validate-on-migrate=true` 로 운영 환경에서 스키마 드리프트 차단

### ArchUnit 경계

- 테이블명이 MSA 경계를 보증하지 않으므로 Java 패키지로 강제:
  - `com.academy.user.identity` 는 `id_*` 와 `TB_MA_MEMBER` 만 접근
  - `com.academy.user.enrollment` 는 `en_*` + `TB_ORDERS` (읽기) 만 접근
- Sprint 6 에서 규칙을 strict (빈 레이어 금지) 로 전환

### 예시 — Sprint 1 회원가입·약관

```
TB_MA_MEMBER  ← 기존, 그대로 사용 (로그인·프로필)
id_member_ext ← 신규 (OAuth provider, CI 인증, 마케팅 수신 동의 등)
id_terms      ← 신규 (약관 마스터)
id_terms_agreement  ← 신규 (회원별 동의 이력)
```

`TB_MA_MEMBER.MEM_ID` ↔ `id_member_ext.mem_id` 1:1. 조회 API 는 `LEFT JOIN`.

## 결과

- Sprint 1~5 동안 기존 admin 기능 **0 리그레션**
- MyBatis mapper 재작성 작업 **불필요**
- 신규 기능은 처음부터 정규화된 스키마 + MSA 경계 준수
- hopenvision 트랙으로 수렴 시: `id_*`·`ct_*` 등 신규 스키마는 거의 그대로 JPA 엔티티 매핑 가능, `TB_*` 는 별도 레거시 어댑터 작업으로 격리

## 리스크

| 리스크 | 완화 |
|---|---|
| `TB_*` 네이밍 혼란으로 신규 개발자 진입 장벽 | `docs/db/` 에 prefix 맵 + ERD 요약 유지, 모듈별 `package-info.java` 에서 사용 테이블 명시 |
| 한 도메인이 `TB_*` 와 `id_*` 두 저장소 사용 → 트랜잭션 경계 주의 | 모든 쓰기 메서드 `@Transactional` 명시, 단일 DB 라 XA 불필요 |
| `TB_*` 에 스키마 변경이 불가피해지는 경우 | 예외 승인 절차: ADR-005 업데이트 + Flyway `V#` 로 명시 |
| hopenvision 이전 시 `TB_*` 변환 비용 이월 | 이월 동의 — MVP 이후 재평가 (ADR-004 와 연계) |

## Flyway baseline 생성 절차 (owner 작업 필요)

로컬 DB 접근 credential 이 필요하므로 owner 가 실행:

```bash
# 1. 운영 스키마 스냅샷
mysqldump --no-data --skip-comments --skip-opt \
  -h 172.30.1.72 -u <user> -p acm_basic > docs/db/acm_basic.schema.sql

# 2. Flyway 는 baseline=1 로 이미 설정되어 있으므로 운영 DB 에선 그대로 작동
#    새 로컬 환경에서 DB 복제가 필요할 때:
mysql -u root -p acm_basic < docs/db/acm_basic.schema.sql
./mvnw flyway:migrate
```

## 후속 결정

- `TB_*` Sprint 6 이후 정규화 시점 — hopenvision 수렴 여부와 묶어 재평가
- `acm_basic` DB 이름 변경 여부 — 현 그대로 유지
