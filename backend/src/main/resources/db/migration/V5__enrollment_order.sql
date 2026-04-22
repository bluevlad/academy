-- V5 — Sprint 3 Enrollment + Order (ADR-005 prefix: en_, od_)
--
-- 플로우: cart_item → order → payment(mock) → enrollment(수강권)

-- ===== 장바구니 =====
CREATE TABLE IF NOT EXISTS en_cart_item (
    cart_item_id      CHAR(36)        NOT NULL,
    user_id           VARCHAR(64)     NOT NULL,
    mst_code          VARCHAR(32)     NOT NULL,
    quantity          INT             NOT NULL DEFAULT 1,
    price_snapshot    DECIMAL(12,0)   NOT NULL DEFAULT 0,
    added_at          DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cart_item_id),
    UNIQUE KEY uk_en_cart_user_mst (user_id, mst_code),
    KEY idx_en_cart_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='수강 장바구니';

-- ===== 주문 =====
CREATE TABLE IF NOT EXISTS od_order (
    order_id          CHAR(36)        NOT NULL,
    user_id           VARCHAR(64)     NOT NULL,
    status            VARCHAR(32)     NOT NULL DEFAULT 'PENDING',
    total_amount      DECIMAL(12,0)   NOT NULL DEFAULT 0,
    discount_amount   DECIMAL(12,0)   NOT NULL DEFAULT 0,
    mileage_used      DECIMAL(12,0)   NOT NULL DEFAULT 0,
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    paid_at           DATETIME        NULL,
    canceled_at       DATETIME        NULL,
    PRIMARY KEY (order_id),
    KEY idx_od_order_user (user_id),
    KEY idx_od_order_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='주문';

CREATE TABLE IF NOT EXISTS od_order_item (
    order_item_id     CHAR(36)        NOT NULL,
    order_id          CHAR(36)        NOT NULL,
    mst_code          VARCHAR(32)     NOT NULL,
    quantity          INT             NOT NULL DEFAULT 1,
    unit_price        DECIMAL(12,0)   NOT NULL DEFAULT 0,
    PRIMARY KEY (order_item_id),
    KEY idx_od_order_item_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='주문 아이템';

-- ===== 결제 =====
CREATE TABLE IF NOT EXISTS od_payment (
    payment_id        CHAR(36)        NOT NULL,
    order_id          CHAR(36)        NOT NULL,
    method            VARCHAR(32)     NOT NULL DEFAULT 'MOCK',
    status            VARCHAR(32)     NOT NULL DEFAULT 'PENDING',
    pg_txn_id         VARCHAR(128)    NULL,
    amount            DECIMAL(12,0)   NOT NULL,
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    approved_at       DATETIME        NULL,
    canceled_at       DATETIME        NULL,
    raw_response      TEXT            NULL,
    PRIMARY KEY (payment_id),
    KEY idx_od_payment_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='결제 (Sprint 3 mock PG · Sprint 4+ 실 연동)';

-- ===== 수강권 =====
CREATE TABLE IF NOT EXISTS en_enrollment (
    enrollment_id     CHAR(36)        NOT NULL,
    user_id           VARCHAR(64)     NOT NULL,
    mst_code          VARCHAR(32)     NOT NULL,
    order_id          CHAR(36)        NULL,
    status            VARCHAR(32)     NOT NULL DEFAULT 'ACTIVE',
    period_start      DATE            NULL,
    period_end        DATE            NULL,
    manual_progress   INT             NOT NULL DEFAULT 0,
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    canceled_at       DATETIME        NULL,
    PRIMARY KEY (enrollment_id),
    UNIQUE KEY uk_en_enroll_user_mst (user_id, mst_code),
    KEY idx_en_enroll_user (user_id),
    KEY idx_en_enroll_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='수강권';
