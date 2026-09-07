-- =====================================================================
-- V8: acm_board_cs — CS/1:1문의 전용 테이블 신설
-- =====================================================================
-- 배경:
--   legacy tb_board_cs 에 5,430건의 CS 문의 누적. 본문·답변이 longblob 이라
--   AI 분석·통계 집계에 부적합. acm_* 규약(ADR-005) 맞춰 TEXT 타입으로 재설계하고
--   AI 분석에 필요한 컬럼(predicted_category, confidence, assigned_to 등) 추가.
--
-- 데이터 이관은 별도 ETL 스크립트 (scripts/data-migrate/board/) 에서 수행.
-- 본 migration 은 빈 테이블만 생성 → prod 자동 실행 안전.
--
-- 관련 플랜: Ai-Legacy-bluevlad/services/academy/CS_INQUIRY_AI_PLAN.md §4.2
-- =====================================================================

CREATE TABLE IF NOT EXISTS acm_board_cs (
    cs_seq                  BIGINT        NOT NULL AUTO_INCREMENT,

    -- 원본 참조 (이관 추적용, 재실행시 중복 방지)
    legacy_board_seq        VARCHAR(20)   NULL COMMENT 'tb_board_cs.BOARD_SEQ',
    legacy_cs_div           VARCHAR(20)   NULL COMMENT 'tb_board_cs.CS_DIV — CSCOUNSEL/CSREFUND 등',
    legacy_cs_kind          VARCHAR(20)   NULL COMMENT 'tb_board_cs.CS_KIND — CSC120/CSR220 등',

    -- 사용자 (PII 마스킹된 더미 값)
    inquiry_user_id         VARCHAR(50)   NOT NULL COMMENT '마스킹된 usr_{hash}',
    inquiry_name            VARCHAR(50)   NOT NULL COMMENT 'Faker 한국어 이름',

    -- 문의 본문
    inquiry_title           VARCHAR(300)  NOT NULL,
    inquiry_body            MEDIUMTEXT    NOT NULL COMMENT '본문 (정규식 PII 치환 후)',
    inquiry_date            DATETIME      NOT NULL,

    -- AI 분류 결과 (Phase B 에서 채움)
    predicted_category      VARCHAR(30)   NULL  COMMENT 'ACADEMIC|ORDER|SYSTEM|OTHER',
    predicted_confidence    DECIMAL(5,4)  NULL  COMMENT '0.0000 ~ 1.0000',
    classified_by_model     VARCHAR(50)   NULL  COMMENT 'ex: qwen2.5:7b',
    classified_at           DATETIME      NULL,

    -- 운영자 확정 카테고리 (재배정 반영)
    actual_category         VARCHAR(30)   NULL,
    assigned_to             VARCHAR(50)   NULL  COMMENT '담당자 user_id',
    reroute_count           INT           NOT NULL DEFAULT 0,

    -- 답변·해결여부
    answer_body             MEDIUMTEXT    NULL,
    answered_by             VARCHAR(50)   NULL,
    answered_at             DATETIME      NULL,
    resolution_state        VARCHAR(20)   NOT NULL DEFAULT 'OPEN'
                            COMMENT 'OPEN|ANSWERED|RESOLVED|CLOSED',
    user_satisfaction       TINYINT       NULL COMMENT '1-5 별점',

    -- 감사
    reg_dt                  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    upd_dt                  DATETIME      NULL ON UPDATE CURRENT_TIMESTAMP,
    is_deleted              CHAR(1)       NOT NULL DEFAULT 'N',

    PRIMARY KEY (cs_seq),
    UNIQUE KEY uk_legacy (legacy_board_seq),
    KEY idx_predicted (predicted_category, classified_at),
    KEY idx_actual (actual_category, reg_dt),
    KEY idx_state (resolution_state),
    KEY idx_assigned (assigned_to, resolution_state),
    KEY idx_inquiry_date (inquiry_date),
    KEY idx_user (inquiry_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='CS/1:1문의 — AI 분류·라우팅 대상. tb_board_cs 이관';
