-- =====================================================================
-- V13: v_inquiry — legacy + 신규 통합 조회 view
-- =====================================================================
-- 배경:
--   운영자 콘솔·통계·유사문의 추천이 legacy TB_BOARD_CS 5,430건과
--   신규 tb_inquiry 인입분을 동일 스키마로 조회해야 함.
--
-- 운영 TB_BOARD_CS 실제 스키마 (DESCRIBE 확인):
--   - PK BOARD_SEQ VARCHAR(20)         (보드타입+시퀀스)
--   - REG_NM 부재 → CREATENAME 만 사용
--   - CONTENT longblob, ANSWER longblob 별도 컬럼
--   - REG_DT date (시각 손실)
--   - COUNSELOR_ID, ACTION_YN ('Y'/'N') 사용
--
-- 설계:
--   - inquiry_id = VARCHAR(50) 통일 (legacy 'CSBOARD_001' + 신규 BIGINT 양쪽 수용)
--   - source     = 'L' (Legacy TB_BOARD_CS) | 'N' (New tb_inquiry)
--   - longblob → CONVERT(... USING utf8mb4) 로 한글 인코딩 안전 처리
--   - legacy CS_DIV/CS_KIND → tb_category_mapping LEFT JOIN 으로 표준 4분류 환원
--   - legacy 는 read-only — UPDATE 쿼리는 v_inquiry 거치지 않고 tb_inquiry 직접 사용
-- =====================================================================

DROP VIEW IF EXISTS v_inquiry;

CREATE VIEW v_inquiry AS
-- ---------------------------------------------------------------------
-- Legacy: TB_BOARD_CS (Oracle 이관, longblob 본문/답변)
-- ---------------------------------------------------------------------
SELECT
    'L'                                                       AS source,
    L.BOARD_SEQ                                               AS inquiry_id,
    L.REG_ID                                                  AS user_id,
    L.CREATENAME                                              AS user_name,
    L.SUBJECT                                                 AS title,
    LEFT(CONVERT(L.CONTENT USING utf8mb4), 65535)             AS body,
    L.REG_DT                                                  AS inquiry_date,

    -- AI 분류 — legacy retro-classify 결과는 후속 PR 의 batch 가
    -- tb_inquiry_train 에 기록. v_inquiry 는 운영 colon 만 표시.
    NULL                                                      AS predicted_category,
    NULL                                                      AS predicted_confidence,
    NULL                                                      AS classified_by_model,
    NULL                                                      AS classified_at,

    M.std_category                                            AS actual_category,
    L.COUNSELOR_ID                                            AS assigned_to,
    0                                                         AS reroute_count,

    LEFT(CONVERT(L.ANSWER USING utf8mb4), 65535)              AS answer_body,
    L.COUNSELOR_ID                                            AS answered_by,
    CASE WHEN L.ANSWER IS NOT NULL THEN L.UPD_DT ELSE NULL END AS answered_at,
    CASE L.ACTION_YN
        WHEN 'Y' THEN 'RESOLVED'
        ELSE          'OPEN'
    END                                                       AS resolution_state,
    NULL                                                      AS user_satisfaction,

    L.REG_DT                                                  AS reg_dt,
    L.UPD_DT                                                  AS upd_dt,
    'N'                                                       AS is_deleted
FROM TB_BOARD_CS L
LEFT JOIN tb_category_mapping M
       ON M.legacy_cs_div  = L.CS_DIV
      AND (M.legacy_cs_kind = COALESCE(L.CS_KIND, '') OR M.legacy_cs_kind = '')

UNION ALL

-- ---------------------------------------------------------------------
-- New: tb_inquiry (Phase D 이후 신규 인입)
-- ---------------------------------------------------------------------
SELECT
    'N'                                                       AS source,
    CAST(N.inquiry_id AS CHAR(20))                            AS inquiry_id,
    N.user_id,
    N.user_name,
    N.title,
    N.body,
    N.inquiry_date,

    N.predicted_category,
    N.predicted_confidence,
    N.classified_by_model,
    N.classified_at,

    N.actual_category,
    N.assigned_to,
    N.reroute_count,

    N.answer_body,
    N.answered_by,
    N.answered_at,
    N.resolution_state,
    N.user_satisfaction,

    N.reg_dt,
    N.upd_dt,
    N.is_deleted
FROM tb_inquiry N
WHERE N.is_deleted = 'N';
