-- V7 — Sprint 5 Mock Exam (ADR-005 prefix: ex_)

CREATE TABLE IF NOT EXISTS ex_mock_exam (
    exam_id         CHAR(36)        NOT NULL,
    name            VARCHAR(255)    NOT NULL,
    subject_cd      VARCHAR(32)     NULL,
    schedule_date   DATE            NOT NULL,
    max_score       INT             NOT NULL DEFAULT 100,
    is_open         TINYINT(1)      NOT NULL DEFAULT 1,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (exam_id),
    KEY idx_ex_mock_date (schedule_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='모의고사 마스터';

CREATE TABLE IF NOT EXISTS ex_mock_attempt (
    attempt_id      CHAR(36)        NOT NULL,
    user_id         VARCHAR(64)     NOT NULL,
    exam_id         CHAR(36)        NOT NULL,
    score           INT             NULL,
    status          VARCHAR(32)     NOT NULL DEFAULT 'REGISTERED',
    answer_sheet    TEXT            NULL,
    registered_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    submitted_at    DATETIME        NULL,
    PRIMARY KEY (attempt_id),
    UNIQUE KEY uk_ex_attempt_user_exam (user_id, exam_id),
    KEY idx_ex_attempt_exam (exam_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='모의고사 응시 기록';
