package com.academy.user.enrollment;

import com.academy.user.order.Order;
import com.academy.user.order.OrderCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 주문 결제 완료 → 각 order_item 별 수강권 자동 발급 (Sprint 3).
 *
 * <p>기본 수강 기간 6개월. 향후 강의별 기간 매핑 테이블 도입 시 교체.
 */
@Component
public class EnrollmentListener {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentListener.class);
    private static final int DEFAULT_PERIOD_MONTHS = 6;

    private final EnrollmentMapper mapper;

    public EnrollmentListener(EnrollmentMapper mapper) {
        this.mapper = mapper;
    }

    @EventListener
    @Transactional
    public void on(OrderCompletedEvent event) {
        Order order = event.order();
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusMonths(DEFAULT_PERIOD_MONTHS);
        if (order.items() == null) return;

        for (Order.OrderItem item : order.items()) {
            mapper.upsertActive(
                UUID.randomUUID().toString(),
                order.userId(),
                item.mstCode(),
                order.orderId(),
                start,
                end
            );
            log.info("수강권 발급: userId={} mstCode={} orderId={}",
                order.userId(), item.mstCode(), order.orderId());
        }
    }
}
