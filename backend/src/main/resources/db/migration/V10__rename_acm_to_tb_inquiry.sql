-- =====================================================================
-- V10: acm_* → tb_* RENAME (inquiry 도메인 네이밍 일관화)
-- =====================================================================
-- 배경:
--   Phase A~C 에서 acm_* prefix 로 생성한 테이블들을 tb_* 로 정렬.
--   기존 Oracle 이관 테이블(TB_BOARD_CS 등) 과 prefix 통일.
--   cs_seq 컬럼명도 inquiry_id 로 정렬 (신규 tb_inquiry.inquiry_id 와 매칭).
--
-- 변경 대상:
--   acm_board_cs               → tb_inquiry_train      (학습 데이터셋, 마스킹 5,430건)
--   acm_inquiry_analysis       → tb_inquiry_analysis   (AI 호출 이력)
--   acm_inquiry_routing_log    → tb_inquiry_routing_log(재배정 로그)
--   acm_inquiry_stats_monthly  → tb_inquiry_stats_monthly
--   acm_inquiry_embedding      → tb_inquiry_embedding
--
-- 학습셋에 source 추적 컬럼 추가 — 원본이 TB_BOARD_CS 인지 신규 tb_inquiry 인지 구분.
--
-- 주의: inquiry.xml 이 V11 과 함께 tb_* 로 갱신되어야 함 (동시 배포).
-- =====================================================================

-- ---------------------------------------------------------------------
-- 1. RENAME
-- ---------------------------------------------------------------------
RENAME TABLE acm_board_cs              TO tb_inquiry_train,
             acm_inquiry_analysis      TO tb_inquiry_analysis,
             acm_inquiry_routing_log   TO tb_inquiry_routing_log,
             acm_inquiry_stats_monthly TO tb_inquiry_stats_monthly,
             acm_inquiry_embedding     TO tb_inquiry_embedding;

-- ---------------------------------------------------------------------
-- 2. cs_seq → inquiry_id 컬럼명 정렬
-- ---------------------------------------------------------------------
ALTER TABLE tb_inquiry_train
    CHANGE COLUMN cs_seq inquiry_id BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE tb_inquiry_analysis
    CHANGE COLUMN cs_seq inquiry_id BIGINT NOT NULL;

ALTER TABLE tb_inquiry_routing_log
    CHANGE COLUMN cs_seq inquiry_id BIGINT NOT NULL;

ALTER TABLE tb_inquiry_embedding
    CHANGE COLUMN cs_seq inquiry_id BIGINT NOT NULL;

-- ---------------------------------------------------------------------
-- 3. tb_inquiry_train 에 source 추적 컬럼 추가
--    legacy TB_BOARD_CS 이관분 / 신규 tb_inquiry 마스킹분 구분용
-- ---------------------------------------------------------------------
ALTER TABLE tb_inquiry_train
    ADD COLUMN source_table VARCHAR(20) NOT NULL DEFAULT 'TB_BOARD_CS'
               COMMENT '원본 테이블 — TB_BOARD_CS | tb_inquiry' AFTER inquiry_id,
    ADD COLUMN source_id    VARCHAR(50) NULL
               COMMENT '원본 PK (TB_BOARD_CS.BOARD_SEQ 또는 tb_inquiry.inquiry_id)' AFTER source_table;

-- 기존 legacy_* 컬럼 값을 source_id 로 일원화
UPDATE tb_inquiry_train
   SET source_id = legacy_board_seq
 WHERE source_id IS NULL
   AND legacy_board_seq IS NOT NULL;

-- 인덱스 보강 (source_table, source_id) 로 역추적·중복 확인 쉽게
ALTER TABLE tb_inquiry_train
    ADD KEY idx_source (source_table, source_id);
