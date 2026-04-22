-- V3 — acm_member.user_pwd 컬럼 확장 (Sprint 1-2 · ADR-002)
--
-- BCrypt 해시는 60자. 기존 평문 + 여유를 고려해 VARCHAR(100).
-- V4 (Java migration) 에서 평문 → BCrypt 일괄 rehash.

ALTER TABLE acm_member
    MODIFY COLUMN user_pwd VARCHAR(100) NULL COMMENT 'BCrypt hash (Sprint 1-2 이후)';
