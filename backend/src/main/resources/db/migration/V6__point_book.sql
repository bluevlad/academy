-- V6 — Sprint 4 Point + Book (ADR-005 prefix: pt_, bk_)

-- ===== 쿠폰 마스터 =====
CREATE TABLE IF NOT EXISTS pt_coupon (
    coupon_id       CHAR(36)        NOT NULL,
    name            VARCHAR(128)    NOT NULL,
    discount_type   VARCHAR(16)     NOT NULL DEFAULT 'AMOUNT',
    discount_value  DECIMAL(12,0)   NOT NULL,
    min_order       DECIMAL(12,0)   NOT NULL DEFAULT 0,
    valid_from      DATETIME        NOT NULL,
    valid_to        DATETIME        NOT NULL,
    is_active       TINYINT(1)      NOT NULL DEFAULT 1,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (coupon_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='쿠폰 마스터';

-- ===== 사용자 보유 쿠폰 =====
CREATE TABLE IF NOT EXISTS pt_coupon_user (
    coupon_user_id  CHAR(36)        NOT NULL,
    user_id         VARCHAR(64)     NOT NULL,
    coupon_id       CHAR(36)        NOT NULL,
    issued_at       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    used_at         DATETIME        NULL,
    order_id        CHAR(36)        NULL,
    PRIMARY KEY (coupon_user_id),
    KEY idx_pt_coupon_user_user (user_id),
    KEY idx_pt_coupon_user_coupon (coupon_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자 보유 쿠폰';

-- ===== 마일리지 원장 (append-only) =====
CREATE TABLE IF NOT EXISTS pt_mileage_ledger (
    ledger_id       BIGINT          NOT NULL AUTO_INCREMENT,
    user_id         VARCHAR(64)     NOT NULL,
    delta           DECIMAL(12,0)   NOT NULL,
    reason          VARCHAR(64)     NOT NULL,
    order_id        CHAR(36)        NULL,
    balance_after   DECIMAL(12,0)   NOT NULL,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ledger_id),
    KEY idx_pt_mileage_user (user_id, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='마일리지 원장 (append-only)';

-- ===== 교재 =====
CREATE TABLE IF NOT EXISTS bk_book (
    book_id         CHAR(36)        NOT NULL,
    title           VARCHAR(255)    NOT NULL,
    author          VARCHAR(128)    NULL,
    price           DECIMAL(12,0)   NOT NULL DEFAULT 0,
    stock           INT             NOT NULL DEFAULT 0,
    is_active       TINYINT(1)      NOT NULL DEFAULT 1,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='교재 카탈로그';

-- ===== 배송지 =====
CREATE TABLE IF NOT EXISTS bk_delivery_address (
    address_id      CHAR(36)        NOT NULL,
    user_id         VARCHAR(64)     NOT NULL,
    recipient       VARCHAR(64)     NOT NULL,
    phone           VARCHAR(32)     NULL,
    zip_code        VARCHAR(16)     NULL,
    address1        VARCHAR(255)    NOT NULL,
    address2        VARCHAR(255)    NULL,
    is_default      TINYINT(1)      NOT NULL DEFAULT 0,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (address_id),
    KEY idx_bk_addr_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='배송지';

-- ===== 배송 =====
CREATE TABLE IF NOT EXISTS bk_delivery (
    delivery_id     CHAR(36)        NOT NULL,
    order_id        CHAR(36)        NOT NULL,
    address_id      CHAR(36)        NOT NULL,
    status          VARCHAR(32)     NOT NULL DEFAULT 'READY',
    tracking_no     VARCHAR(64)     NULL,
    carrier         VARCHAR(32)     NULL,
    shipped_at      DATETIME        NULL,
    delivered_at    DATETIME        NULL,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (delivery_id),
    KEY idx_bk_delivery_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='배송 상태';
