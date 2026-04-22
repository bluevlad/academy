package com.academy.user.order;

import com.academy.user.cart.CartItem;
import com.academy.user.cart.CartMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderMapper orderMapper;
    private CartMapper cartMapper;
    private ApplicationEventPublisher events;
    private OrderService service;

    @BeforeEach
    void setUp() {
        orderMapper = mock(OrderMapper.class);
        cartMapper = mock(CartMapper.class);
        events = mock(ApplicationEventPublisher.class);
        service = new OrderService(orderMapper, cartMapper, events);
    }

    @Test
    void create_from_cart_builds_order_and_clears_cart() {
        when(cartMapper.findByUserId("u1")).thenReturn(List.of(
            new CartItem("c1", "u1", "L1", 1, new BigDecimal("100000"), LocalDateTime.now()),
            new CartItem("c2", "u1", "L2", 2, new BigDecimal("50000"), LocalDateTime.now())
        ));
        when(orderMapper.findById(anyString())).thenReturn(Optional.of(fixturePending()));

        service.createOrderFromCart("u1");

        ArgumentCaptor<BigDecimal> totalCap = ArgumentCaptor.forClass(BigDecimal.class);
        verify(orderMapper).insertOrder(anyString(), anyString(), totalCap.capture());
        assertThat(totalCap.getValue()).isEqualByComparingTo("200000");  // 100k + 50k×2
        verify(orderMapper, times(2)).insertItem(any(), any(), any(), anyInt(), any());
        verify(cartMapper).clear("u1");
    }

    @Test
    void create_from_empty_cart_fails() {
        when(cartMapper.findByUserId("u1")).thenReturn(List.of());
        assertThatThrownBy(() -> service.createOrderFromCart("u1"))
            .isInstanceOf(OrderService.EmptyCartException.class);
    }

    @Test
    void mark_paid_publishes_event_once() {
        Order pending = fixturePending();
        Order paid = pendingToPaid(pending);
        when(orderMapper.findById("o1")).thenReturn(Optional.of(pending), Optional.of(paid));

        service.markPaid("o1");

        verify(orderMapper).markPaid(eq("o1"), any());
        verify(events).publishEvent(any(OrderCompletedEvent.class));
    }

    @Test
    void mark_paid_is_idempotent() {
        Order already = pendingToPaid(fixturePending());
        when(orderMapper.findById("o1")).thenReturn(Optional.of(already));

        service.markPaid("o1");

        verify(orderMapper, never()).markPaid(anyString(), any());
        verify(events, never()).publishEvent(any());
    }

    private Order fixturePending() {
        return new Order("o1", "u1", "PENDING", new BigDecimal("200000"),
            BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now(), null, null, List.of());
    }

    private Order pendingToPaid(Order p) {
        return new Order(p.orderId(), p.userId(), "PAID", p.totalAmount(),
            p.discountAmount(), p.mileageUsed(), p.createdAt(), LocalDateTime.now(), null, p.items());
    }
}
