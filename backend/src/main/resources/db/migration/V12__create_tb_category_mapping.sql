-- =====================================================================
-- V12: tb_category_mapping — legacy CS_DIV/CS_KIND → 표준 4분류 매핑 마스터
-- =====================================================================
-- 배경:
--   legacy TB_BOARD_CS 5,430건은 (CS_DIV, CS_KIND) 2단 코드로 분류됨.
--   신규 tb_inquiry 는 4분류 표준 (ACADEMIC|ORDER|SYSTEM|OTHER) 사용.
--   v_inquiry view 가 LEFT JOIN 으로 legacy 분류를 표준 라벨로 환원.
--
--   시드 INSERT 는 운영자 합의 후 별도 마이그레이션 (V12_1) 또는
--   scripts/seed/category_mapping_seed.sql 로 분리 — 본 V12 는 스키마만 생성.
--
-- 매핑 결정 사전자료:
--   SELECT CS_DIV, CS_KIND, COUNT(*) FROM TB_BOARD_CS GROUP BY CS_DIV, CS_KIND;
-- =====================================================================

CREATE TABLE IF NOT EXISTS tb_category_mapping (
    legacy_cs_div   VARCHAR(20)  NOT NULL  COMMENT 'TB_BOARD_CS.CS_DIV',
    legacy_cs_kind  VARCHAR(20)  NOT NULL DEFAULT ''
                    COMMENT 'TB_BOARD_CS.CS_KIND. 빈 문자열 = CS_DIV 만으로 매칭',
    std_category    VARCHAR(30)  NOT NULL
                    COMMENT '표준 4분류 — ACADEMIC|ORDER|SYSTEM|OTHER',
    remark          VARCHAR(200) NULL,
    reg_dt          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    upd_dt          DATETIME     NULL ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (legacy_cs_div, legacy_cs_kind),
    KEY idx_std (std_category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='legacy CS 분류 ↔ 신규 4분류 매핑 마스터';
