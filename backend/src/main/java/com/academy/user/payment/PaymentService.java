package com.academy.user.payment;

import com.academy.user.order.Order;
import com.academy.user.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 결제 서비스 (Sprint 3 mock PG).
 *
 * <p>실 PG(LGD 등) 연동은 Sprint 4 이후. 현재는 요청 받으면 즉시 APPROVED 로 처리하고
 * {@link OrderService#markPaid} 를 호출해 OrderCompletedEvent 가 enrollment 발급을 트리거.
 */
@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentMapper paymentMapper;
    private final OrderService orderService;

    public PaymentService(PaymentMapper paymentMapper, OrderService orderService) {
        this.paymentMapper = paymentMapper;
        this.orderService = orderService;
    }

    @Transactional
    public Payment payMock(String orderId) {
        Order order = orderService.get(orderId);
        String paymentId = UUID.randomUUID().toString();
        paymentMapper.insert(paymentId, orderId, "MOCK", order.totalAmount());

        String txnId = "MOCK-" + UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        paymentMapper.markApproved(paymentId, txnId, now, "{\"mock\":true}");
        orderService.markPaid(orderId);
        log.info("mock 결제 승인: orderId={} paymentId={}", orderId, paymentId);

        return new Payment(paymentId, orderId, "MOCK", Payment.Status.APPROVED,
            txnId, order.totalAmount(), now, now);
    }
}
