# DB 스키마 스냅샷

> ADR-005 — 하이브리드 DB 전략에 따라 `TB_*` 레거시 + `acm_*` 신규 테이블이 공존.
> 이 디렉토리는 **운영 스키마의 구조 스냅샷**을 유지한다. 실제 운영 DB 는 변경하지 않는다.

## 파일

- `acm_basic.schema.sql` — `mysqldump --no-data` 결과. **owner 가 최초 1회 생성 후 커밋.**
- `prefix-map.md` — (향후) 각 prefix(`TB_*`, `acm_*`, `id_*` ...) 의 소속 MSA / 담당 모듈 매핑

## 최초 스냅샷 생성 (owner 작업 필요)

```bash
# 운영 DB 기준 (172.30.1.72 또는 dev 로컬 3306)
mysqldump --no-data --skip-comments --skip-opt \
  --single-transaction \
  -h <host> -u <user> -p acm_basic > docs/db/acm_basic.schema.sql

# 스냅샷 크기 확인 (예상: 100KB~500KB)
ls -lh docs/db/acm_basic.schema.sql

# 커밋
git add docs/db/acm_basic.schema.sql
git commit -m "docs(db): acm_basic 스키마 스냅샷 baseline"
```

## 새 로컬 환경 셋업 시

```bash
# 1. 빈 acm_basic DB 생성
mysql -u root -p -e "CREATE DATABASE acm_basic DEFAULT CHARSET utf8mb4;"

# 2. 스키마 복원
mysql -u root -p acm_basic < docs/db/acm_basic.schema.sql

# 3. Flyway 가 V1 marker 를 이미 실행된 것으로 간주하고 V2+ 만 적용
cd backend && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 운영 테이블 변경 정책

- **`TB_*` 테이블 변경 금지** (ADR-005). 컬럼 추가 필요 시 `id_*_ext` 등 신규 테이블 1:1 JOIN.
- `acm_*` 및 신규 prefix (`id_`, `ct_`, `en_`, `od_`, `pt_`, `bk_`, `pl_`, `ex_`, `op_`) 은 Flyway V2+ 로 관리.
- 스키마 변경 시 이 `acm_basic.schema.sql` 스냅샷 갱신 + 커밋 (PR 리뷰로 검증).
