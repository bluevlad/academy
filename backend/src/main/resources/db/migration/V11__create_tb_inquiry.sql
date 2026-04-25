-- =====================================================================
-- V11: tb_inquiry / tb_inquiry_file — 신규 운영 테이블
-- =====================================================================
-- 배경:
--   legacy TB_BOARD_CS 는 read-only 아카이브로 보존 (Oracle CLOB 본문, 보드타입+시퀀스 PK).
--   신규 1:1 문의 인입은 모두 tb_inquiry 로. AI 분류 표준 컬럼 일체 포함.
--
--   첨부파일은 별도 테이블 tb_inquiry_file 로 분리 — DB 본문 BLOB 저장 금지.
--
-- 표준:
--   PK              : BIGINT AUTO_INCREMENT
--   본문            : MEDIUMTEXT (16MB) utf8mb4
--   답변            : answer_body 별도 컬럼 — Phase D 미답변 판별 단순화 (IS NULL)
--   카테고리        : 4분류 (ACADEMIC|ORDER|SYSTEM|OTHER) — predicted/actual 분리
--   resolution_state: OPEN → ANSWERED → RESOLVED → CLOSED 4단계
--
-- 관련 표준 문서: docs/standards/INQUIRY_MODEL_STANDARD.md (별도 PR)
-- =====================================================================

CREATE TABLE IF NOT EXISTS tb_inquiry (
    inquiry_id              BIGINT        NOT NULL AUTO_INCREMENT,

    -- 사용자 (내부망 평문, 외부 API/화면 노출 시 별칭으로 마스킹)
    user_id                 VARCHAR(50)   NOT NULL,
    user_name               VARCHAR(50)   NOT NULL,

    -- 본문
    title                   VARCHAR(300)  NOT NULL,
    body                    MEDIUMTEXT    NOT NULL,
    inquiry_date            DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- AI 분류 (Phase B 이후 agent 가 채움)
    predicted_category      VARCHAR(30)   NULL  COMMENT 'ACADEMIC|ORDER|SYSTEM|OTHER',
    predicted_confidence    DECIMAL(5,4)  NULL  COMMENT '0.0000 ~ 1.0000',
    classified_by_model     VARCHAR(50)   NULL  COMMENT 'qwen2.5:7b@v3 식 모델 버전',
    classified_at           DATETIME      NULL,

    -- 운영자 처리 (Phase C)
    actual_category         VARCHAR(30)   NULL  COMMENT '운영자 확정 카테고리',
    assigned_to             VARCHAR(50)   NULL  COMMENT '담당자 user_id',
    reroute_count           INT           NOT NULL DEFAULT 0,

    -- 답변
    answer_body             MEDIUMTEXT    NULL,
    answered_by             VARCHAR(50)   NULL,
    answered_at             DATETIME      NULL,
    resolution_state        VARCHAR(20)   NOT NULL DEFAULT 'OPEN'
                            COMMENT 'OPEN|ANSWERED|RESOLVED|CLOSED',
    user_satisfaction       TINYINT       NULL  COMMENT '1-5 별점',

    -- 감사
    reg_dt                  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    upd_dt                  DATETIME      NULL ON UPDATE CURRENT_TIMESTAMP,
    is_deleted              CHAR(1)       NOT NULL DEFAULT 'N',

    PRIMARY KEY (inquiry_id),
    KEY idx_user      (user_id, inquiry_date),
    KEY idx_state     (resolution_state, inquiry_date),
    KEY idx_predicted (predicted_category, classified_at),
    KEY idx_actual    (actual_category, reg_dt),
    KEY idx_assigned  (assigned_to, resolution_state),
    KEY idx_inquiry_date (inquiry_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='1:1 문의 운영 테이블 (Phase D 이후 신규 인입 전용)';


-- ---------------------------------------------------------------------
-- tb_inquiry_file: 첨부파일 메타 (실 파일은 디스크/S3)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_inquiry_file (
    file_seq        BIGINT        NOT NULL AUTO_INCREMENT,
    inquiry_id      BIGINT        NOT NULL,

    file_name       VARCHAR(300)  NOT NULL COMMENT '원본 파일명',
    stored_path     VARCHAR(500)  NOT NULL COMMENT '저장 경로 (서버 디스크 또는 S3 key)',
    mime_type       VARCHAR(100)  NULL,
    size_bytes      BIGINT        NULL,

    upload_target   VARCHAR(20)   NOT NULL DEFAULT 'QUESTION'
                    COMMENT 'QUESTION (사용자 첨부) | ANSWER (운영자 첨부)',

    reg_dt          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted      CHAR(1)       NOT NULL DEFAULT 'N',

    PRIMARY KEY (file_seq),
    KEY idx_inquiry (inquiry_id, upload_target)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='1:1 문의 첨부파일 메타';
