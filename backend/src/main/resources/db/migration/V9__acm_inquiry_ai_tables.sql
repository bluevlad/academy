-- =====================================================================
-- V9: AI 분석·라우팅·통계 부속 테이블 4종
-- =====================================================================
-- 빈 테이블 생성만. Phase B 이후 agent 서비스가 채움.
-- 관련 플랜: Claude-Opus-bluevlad/services/academy/CS_INQUIRY_AI_PLAN.md §4.2
-- =====================================================================

-- ---------------------------------------------------------------------
-- 1. AI 분석 이력 (감사·디버깅)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS acm_inquiry_analysis (
    analysis_seq    BIGINT        NOT NULL AUTO_INCREMENT,
    cs_seq          BIGINT        NOT NULL,

    -- 모델 메타
    model_name      VARCHAR(50)   NOT NULL COMMENT 'qwen2.5:7b 등',
    model_version   VARCHAR(50)   NULL     COMMENT 'Ollama digest',
    prompt_template VARCHAR(50)   NOT NULL COMMENT 'v1, v2 ... few-shot 버전',

    -- 결과
    raw_output      TEXT          NULL COMMENT 'LLM 원 응답 (감사)',
    parsed_category VARCHAR(30)   NULL,
    confidence      DECIMAL(5,4)  NULL,
    latency_ms      INT           NULL,
    error_msg       TEXT          NULL,

    created_at      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (analysis_seq),
    KEY idx_cs_time (cs_seq, created_at),
    KEY idx_model (model_name, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='AI 분류 원시 결과 이력 (재현·디버깅 용)';


-- ---------------------------------------------------------------------
-- 2. 담당자 재배정 로그 (학습 데이터)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS acm_inquiry_routing_log (
    log_seq         BIGINT        NOT NULL AUTO_INCREMENT,
    cs_seq          BIGINT        NOT NULL,

    from_category   VARCHAR(30)   NULL COMMENT 'AI 최초 분류 또는 직전 할당',
    to_category     VARCHAR(30)   NOT NULL,
    from_user       VARCHAR(50)   NULL,
    to_user         VARCHAR(50)   NOT NULL,

    reason          VARCHAR(500)  NULL,
    changed_by      VARCHAR(50)   NOT NULL,
    changed_at      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_ai_error     CHAR(1)       NOT NULL DEFAULT 'N'
                    COMMENT 'Y=AI 오분류로 간주 → few-shot 학습 셋 포함',

    PRIMARY KEY (log_seq),
    KEY idx_cs (cs_seq),
    KEY idx_learning (is_ai_error, changed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='재배정 이력. is_ai_error=Y 건은 주간 few-shot 갱신 입력';


-- ---------------------------------------------------------------------
-- 3. 월간 집계 (배치 갱신, materialized view 대체)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS acm_inquiry_stats_monthly (
    stat_ym          CHAR(7)       NOT NULL COMMENT 'YYYY-MM',
    category            VARCHAR(30)   NOT NULL,

    total_count         INT           NOT NULL DEFAULT 0,
    resolved_count      INT           NOT NULL DEFAULT 0,
    avg_response_hours  DECIMAL(8,2)  NULL,
    avg_satisfaction    DECIMAL(3,2)  NULL COMMENT '1-5',
    ai_correct_rate     DECIMAL(5,4)  NULL COMMENT 'is_ai_error=N 비율',
    mom_delta_pct       DECIMAL(6,2)  NULL COMMENT '전월 대비 변화율',

    updated_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
                        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (stat_ym, category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='월간 카테고리별 인사이트 (매월 1일 배치)';


-- ---------------------------------------------------------------------
-- 4. 임베딩 백업 (주 저장소는 ChromaDB. 재구축시 fallback)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS acm_inquiry_embedding (
    cs_seq          BIGINT        NOT NULL,
    model_name      VARCHAR(50)   NOT NULL COMMENT 'nomic-embed-text 등',
    dim             INT           NOT NULL,
    embedding       BLOB          NOT NULL COMMENT 'float32 array, little-endian',
    created_at      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (cs_seq, model_name),
    KEY idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='임베딩 벡터 DB 백업 — ChromaDB 컬렉션 유실시 재구축 용';
