package com.academy.user.order;

import com.academy.user.cart.CartItem;
import com.academy.user.cart.CartMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;
    private final ApplicationEventPublisher events;

    public OrderService(OrderMapper orderMapper, CartMapper cartMapper, ApplicationEventPublisher events) {
        this.orderMapper = orderMapper;
        this.cartMapper = cartMapper;
        this.events = events;
    }

    /** 장바구니 전체를 주문으로 전환 (Sprint 3 기본 흐름). */
    @Transactional
    public Order createOrderFromCart(String userId) {
        List<CartItem> items = cartMapper.findByUserId(userId);
        if (items.isEmpty()) {
            throw new EmptyCartException("장바구니가 비어 있습니다.");
        }
        BigDecimal total = items.stream()
            .map(i -> i.priceSnapshot().multiply(BigDecimal.valueOf(i.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        String orderId = UUID.randomUUID().toString();
        orderMapper.insertOrder(orderId, userId, total);
        for (CartItem item : items) {
            orderMapper.insertItem(UUID.randomUUID().toString(), orderId,
                item.mstCode(), item.quantity(), item.priceSnapshot());
        }
        cartMapper.clear(userId);
        return orderMapper.findById(orderId)
            .orElseThrow(() -> new IllegalStateException("주문 저장 직후 조회 실패: " + orderId));
    }

    /** 결제 성공 시 호출 — 상태 변경 + OrderCompletedEvent 발행. */
    @Transactional
    public Order markPaid(String orderId) {
        Order o = orderMapper.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("주문을 찾을 수 없습니다: " + orderId));
        if (!Order.Status.PENDING.equals(o.status())) {
            return o;  // 이미 처리된 주문 — 멱등
        }
        orderMapper.markPaid(orderId, LocalDateTime.now());
        Order updated = orderMapper.findById(orderId).orElseThrow();
        events.publishEvent(new OrderCompletedEvent(updated));
        return updated;
    }

    public Order get(String orderId) {
        return orderMapper.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("주문을 찾을 수 없습니다: " + orderId));
    }

    public List<Order> listByUser(String userId) {
        return orderMapper.findByUserId(userId);
    }

    public static class EmptyCartException extends RuntimeException {
        public EmptyCartException(String m) { super(m); }
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String m) { super(m); }
    }
}
