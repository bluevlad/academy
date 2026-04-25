-- =====================================================================
-- V13: v_inquiry — legacy + 신규 통합 조회 view
-- =====================================================================
-- 배경:
--   운영자 콘솔·통계·유사문의 추천이 legacy TB_BOARD_CS 5,430건과
--   신규 tb_inquiry 인입분을 동일 스키마로 조회해야 함.
--
-- 설계:
--   - inquiry_id = VARCHAR(50) 통일 (legacy BOARD_SEQ 'CSBOARD_001' 형식 + 신규 BIGINT 양쪽 수용)
--   - source     = 'L' (Legacy TB_BOARD_CS) | 'N' (New tb_inquiry)
--   - legacy CS_DIV/CS_KIND → tb_category_mapping LEFT JOIN 으로 표준 4분류 환원
--   - legacy 는 read-only — UPDATE 쿼리는 v_inquiry 거치지 않고 tb_inquiry 직접 사용
--
-- 컬럼 구성: InquiryDto 와 1:1 정렬.
-- =====================================================================

CREATE OR REPLACE VIEW v_inquiry AS
-- ---------------------------------------------------------------------
-- Legacy: TB_BOARD_CS (Oracle 이관, CLOB 본문)
-- ---------------------------------------------------------------------
SELECT
    'L'                                              AS source,
    L.BOARD_SEQ                                      AS inquiry_id,
    L.REG_ID                                         AS user_id,
    COALESCE(L.REG_NM, L.CREATENAME)                 AS user_name,
    L.SUBJECT                                        AS title,
    LEFT(CONVERT(L.CONTENT USING utf8mb4), 65535)    AS body,
    L.REG_DT                                         AS inquiry_date,

    -- AI 분류 — legacy retro-classify 결과는 tb_inquiry_train 에 저장되므로
    -- 여기서는 NULL. 운영자 콘솔에는 표준 매핑만 표시.
    NULL                                             AS predicted_category,
    NULL                                             AS predicted_confidence,
    NULL                                             AS classified_by_model,
    NULL                                             AS classified_at,

    M.std_category                                   AS actual_category,
    L.COUNSELOR_ID                                   AS assigned_to,
    0                                                AS reroute_count,

    -- legacy 는 답변이 CONTENT 에 합쳐있을 수 있어 별도 노출 불가
    NULL                                             AS answer_body,
    L.COUNSELOR_ID                                   AS answered_by,
    NULL                                             AS answered_at,
    CASE L.ACTION_YN
        WHEN 'Y' THEN 'RESOLVED'
        ELSE          'OPEN'
    END                                              AS resolution_state,
    NULL                                             AS user_satisfaction,

    L.REG_DT                                         AS reg_dt,
    NULL                                             AS upd_dt,
    'N'                                              AS is_deleted
FROM TB_BOARD_CS L
LEFT JOIN tb_category_mapping M
       ON M.legacy_cs_div  = L.CS_DIV
      AND (M.legacy_cs_kind = COALESCE(L.CS_KIND, '') OR M.legacy_cs_kind = '')
WHERE L.OPEN_YN <> 'D' OR L.OPEN_YN IS NULL  -- 삭제 표시가 있는 경우 대비

UNION ALL

-- ---------------------------------------------------------------------
-- New: tb_inquiry (Phase D 이후 신규 인입)
-- ---------------------------------------------------------------------
SELECT
    'N'                                              AS source,
    CAST(N.inquiry_id AS CHAR(20))                   AS inquiry_id,
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
