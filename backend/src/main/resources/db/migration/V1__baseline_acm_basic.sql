-- ============================================
-- V1 baseline — 기존 운영 DB(acm_basic) 기준선
-- ============================================
-- 이 파일은 Flyway 의 baseline 표식.
-- application.yml: spring.flyway.baseline-version=1, baseline-on-migrate=true
-- 기존 MariaDB acm_basic 에는 이미 모든 테이블이 존재.
-- Flyway 는 이 파일을 "V1 이 이미 실행됨"으로 간주하고 V2+ 만 추가 실행한다.
--
-- 실 스키마 덤프가 필요할 때:
--   mysqldump --no-data acm_basic > docs/db/acm_basic.schema.sql
--
-- 유의 사항:
--   - 이 프로젝트가 새 DB 에서 처음 기동되는 경우 V1 은 실제 스키마를 만들지 않는다.
--     → 그런 경우에는 docs/db/acm_basic.schema.sql 을 먼저 수동 import 한 뒤 Flyway 를 가동한다.
--   - Sprint 1 부터 V2__*.sql 로 스키마 확장을 관리한다.

SELECT 'academy-integrated V1 baseline marker' AS note;
