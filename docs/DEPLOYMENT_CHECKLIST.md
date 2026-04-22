# 배포 체크리스트 (Sprint 6-4)

> academy 통합 backend(9000) + admin-web(4001) + user-web(3003) + redis(6379) 단일 compose 배포.
> 기존 academy-admin(9001)/user(9002) 에서 integrated(9000) 으로 스위칭 시 참조.

## 0. 사전 검증 (배포 전날)

- [ ] `origin/main` 최신 커밋이 CI `./mvnw test` BUILD SUCCESS (현재 53 run / 6 skip / 0 fail)
- [ ] `docs/SPRINTS.md` 의 Sprint 0~6 체크리스트가 ✅ 로 마킹됨
- [ ] `docs/adr/` 의 ADR-001~005 내용이 현재 코드와 일치 (ADR drift 없음)
- [ ] Flyway V2~V7 migration 파일이 운영 DB 타겟 (MariaDB) 문법에 맞음 — `mariadb-server:11.x` 로 사전 dry-run

## 1. DB 작업 순서 (acm_basic 단일)

- [ ] 전체 `mysqldump acm_basic > backup-YYYYMMDD.sql` (롤백용 전체 스냅샷)
- [ ] `mysqldump --no-data acm_basic > docs/db/acm_basic.schema.sql` (스키마 스냅샷 갱신 · ADR-005)
- [ ] Flyway migration dry-run: `./mvnw flyway:info -Dflyway.url=... -Dflyway.user=... -Dflyway.password=...`
  - V1 baseline marker
  - V2 id_admin
  - V3 acm_member pwd VARCHAR(100) 확장
  - **V4 평문 → BCrypt 일괄 rehash** ⚠️ 한 번 실행되면 비가역
  - V5 enrollment + order (en_cart_item, od_order, od_order_item, od_payment, en_enrollment)
  - V6 point + book (pt_coupon, pt_coupon_user, pt_mileage_ledger, bk_book, bk_delivery_address, bk_delivery)
  - V7 mocktest (ex_mock_exam, ex_mock_attempt)
- [ ] 운영에 적용 (Flyway `migrate`) — 완료 후 `SELECT COUNT(*) FROM acm_member WHERE user_pwd NOT LIKE '$2%'` = 0 확인
- [ ] 관리자 로그인 smoke — `POST /api/auth/login` audience=admin + default 계정
- [ ] 수강생 샘플 계정으로 로그인 smoke (기존 평문 비번이 그대로 통해야 함)

## 2. 인프라

- [ ] 외부 `database-network` 에 backend 컨테이너가 합류 (`docker network connect`)
- [ ] Redis 컨테이너 기동 + backend 에서 `/actuator/health` 200
- [ ] `.env` 의 `JWT_SECRET` 최소 32B + 운영 고유값 (dev 기본값 사용 금지)
- [ ] `CORS_ALLOWED_ORIGINS` 에 운영 admin-web / user-web 도메인 등록

## 3. 서비스 스위칭

- [ ] 기존 academy-admin(9001) / academy-user(9002) 를 유지한 상태로 integrated(9000) 병행 기동
- [ ] Nginx/LB 에서 라우팅 점진 이동:
  1. `/api/auth/**` → 9000 (신규 JWT)
  2. `/api/user/**` → 9000
  3. `/api/admin/** + /api/<module>/**` → 9000
- [ ] 30분 모니터링 — 에러율, p95 latency, JWT 401 비율
- [ ] 문제 시 Nginx 라우팅만 원복 (DB 는 이미 migration 완료 상태로 유지)

## 4. 스위칭 후 정리

- [ ] 기존 9001/9002 컨테이너 중지 (데이터 없음, 삭제 가능)
- [ ] `~/GIT/academy-admin` / `~/GIT/academy-user` repo 는 read-only 아카이브 표시
- [ ] `docs/SPRINTS.md` 에 Exit Gate 결과 기록 (ADR-004) — E2E 95% / p95 / coverage

## 5. 롤백 시나리오

| 상황 | 조치 |
|---|---|
| JWT 401 대량 발생 | Nginx 를 9001/9002 로 되돌림 (DB migration 은 유지) |
| acm_member 비번 BCrypt 후 로그인 실패 | V4 가 누락한 row 조사 + 해당 유저에 임시 비번 재발급 — 평문으로 되돌리지 않음 |
| enrollment 발급 실패 (OrderCompletedEvent 유실) | admin 에서 `INSERT INTO en_enrollment` 수동 적용, 이벤트 재발행 코드는 Sprint 7 개선 |
| Flyway schema mismatch | `flyway_schema_history` 에서 해당 version entry 제거 후 재실행 — **백업에서 복구가 더 안전** |

## 6. 검증 스크립트 (수동)

```bash
# 0) health
curl -f http://localhost:9000/api/shared/health

# 1) admin 로그인 + 보호 경로
T=$(curl -s -X POST http://localhost:9000/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"userId":"admin","password":"dnflskfk","audience":"admin"}' \
  | jq -r .data.accessToken)
curl -H "Authorization: Bearer $T" http://localhost:9000/api/board/list | jq .

# 2) 수강생 가입 + 로그인 + mypage
curl -X POST http://localhost:9000/api/auth/signup \
  -H 'Content-Type: application/json' \
  -d '{"userId":"smoke-1","password":"p@ssword1","userNm":"스모크","email":"smoke@example.com"}'
U=$(curl -s -X POST http://localhost:9000/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"userId":"smoke-1","password":"p@ssword1","audience":"user"}' \
  | jq -r .data.accessToken)
curl -H "Authorization: Bearer $U" http://localhost:9000/api/user/mypage/profile | jq .
curl -H "Authorization: Bearer $U" http://localhost:9000/api/user/lecture | jq .
```

## 7. ADR-004 Exit Gate 평가

배포 7~14일 후 수집:
- [ ] Playwright E2E 95% 통과 (Sprint 6-1 에 정의 예정)
- [ ] 주요 조회 p95 < 300ms
- [ ] 에러율 < 0.1%
- [ ] MyBatis mapper 재사용률 80%+ (기존 Oracle SQL 깨진 엔드포인트 집계)
- [ ] ArchUnit 위반 0 건 (`./mvnw test -Dtest=ModularMonolithRulesTest`)

5개 전부 충족 → 트랙 B 고정, hopenvision PoC 브랜치 폐기 결정.
하나라도 미충족 → ADR-004 트리거 발동 여부 판단.
