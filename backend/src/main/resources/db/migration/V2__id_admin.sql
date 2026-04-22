-- V2 — 관리자 계정 (ADR-002 / ADR-005)
--
-- Sprint 1-1a 의 InMemoryUserDetailsManager 를 DB 기반으로 교체하기 위한 최초 테이블.
-- prefix = id_ (identity-svc).

CREATE TABLE IF NOT EXISTS id_admin (
    admin_id        CHAR(36)     NOT NULL,
    username        VARCHAR(64)  NOT NULL,
    email           VARCHAR(255) NULL,
    password_hash   VARCHAR(100) NOT NULL,
    display_name    VARCHAR(100) NULL,
    role            VARCHAR(32)  NOT NULL DEFAULT 'ROLE_ADMIN',
    enabled         TINYINT(1)   NOT NULL DEFAULT 1,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login_at   DATETIME     NULL,
    PRIMARY KEY (admin_id),
    UNIQUE KEY uk_id_admin_username (username),
    UNIQUE KEY uk_id_admin_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='관리자 계정 (Sprint 1-1b — ADR-002/005)';
