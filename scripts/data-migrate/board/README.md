# Board ETL — tb_board_cs → acm_board_cs

CS/1:1 문의 데이터를 legacy `tb_board_cs` 에서 신규 `acm_board_cs` 로 이관.
**PII 마스킹 필수** (userId → 해시·이름 → Faker·본문 내 연락처·이메일 정규식 치환).

관련 플랜: `Claude-Opus-bluevlad/services/academy/CS_INQUIRY_AI_PLAN.md` (Phase A)

## 사전 요구

- Python 3.11+
- MariaDB 10.x 로컬 / staging 접근 (운영 DB 접근 금지)
- Faker ko_KR, mariadb python 커넥터

```bash
cd scripts/data-migrate/board
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
cp .env.example .env
# .env 편집: DATA_MIGRATE_ENV=dev, DB credentials
```

## 실행

```bash
# 1. dry-run: DB 쓰기 없이 처음 5건 변환 결과만 출력
python migrate_cs.py --dry-run --limit 100

# 2. 부분 실행 (처음 500건)
python migrate_cs.py --limit 500

# 3. 전체 이관
python migrate_cs.py

# 4. 증분 이관 (이미 적재된 legacy_board_seq 스킵)
python migrate_cs.py --only-new
```

## 환경 가드

스크립트는 실행 전 다음을 체크:

1. `DATA_MIGRATE_ENV` 가 `dev` 또는 `staging` — 아니면 즉시 abort
2. `TARGET_DB_HOST` 가 localhost/127.0.0.1 또는 `staging` 문자열 포함
3. 위반 시 exit code 2

운영 DB 에는 절대 실행 금지. 운영 DB 스냅샷은 시스템관리자가 별도로 dev 로 복제.

## PII 마스킹 규칙

| 원본 | 타겟 | 방식 |
|------|------|------|
| `REG_ID` | `inquiry_user_id` | `usr_{sha256[0:8]}` (결정적) |
| `CREATENAME` | `inquiry_name` | Faker ko_KR (user_id 매핑 고정) |
| `COUNSELOR_ID` | `answered_by` | 위와 동일 매핑표 |
| 본문 `CONTENT/ANSWER` | `inquiry_body/answer_body` | 정규식: 휴대폰·주민번호·이메일·한글이름 치환 |

`id_map.cache.json` 에 원→더미 매핑이 평문 JSON 으로 저장됨. **권한 0600 필수**.
재실행 시 이 파일이 있으면 동일 사용자는 동일 더미 유지 (referential 보존).

```bash
chmod 600 id_map.cache.json
```

## 검증

이관 후 샘플 확인:

```sql
-- 건수
SELECT COUNT(*) FROM tb_board_cs;          -- 원본 건수
SELECT COUNT(*) FROM acm_board_cs;         -- 이관 건수 (같아야)

-- PII 유출 여부 (원 user_id 가 노출되면 FAIL)
SELECT inquiry_user_id, inquiry_name FROM acm_board_cs LIMIT 10;
-- inquiry_user_id 는 usr_xxxxxxxx 형식, inquiry_name 은 랜덤 한국 이름

-- 본문 내 연락처·주민번호 남아있는지
SELECT cs_seq FROM acm_board_cs
WHERE inquiry_body REGEXP '01[016789][- ]?[0-9]{3,4}[- ]?[0-9]{4}'
   OR inquiry_body REGEXP '[0-9]{6}[- ]?[1-4][0-9]{6}'
LIMIT 5;
-- 결과 0 건이어야 함

-- 카테고리 힌트 (legacy CS_DIV 매핑)
SELECT actual_category, COUNT(*) FROM acm_board_cs GROUP BY actual_category;
-- ACADEMIC (CSCOUNSEL), ORDER (CSREFUND), NULL (기타)
```

## 재실행 안전성

- `acm_board_cs.legacy_board_seq` 에 UNIQUE 제약 → 중복 삽입 방지
- `INSERT ... ON DUPLICATE KEY UPDATE` 로 본문·답변만 갱신
- `--only-new` 옵션: 이미 있는 legacy_board_seq 는 source 쿼리 단계에서 skip

## 다음 단계 (Phase B)

1. Agent 서비스 (`services/agent/`) 기동 → qwen2.5:7b 로 분류
2. 배치: `acm_board_cs` 전 행 반복하며 `/classify` 호출 → `predicted_category` 채움
3. 결과를 `actual_category` (legacy CS_DIV 힌트) 와 비교 → 초기 정확도 측정
