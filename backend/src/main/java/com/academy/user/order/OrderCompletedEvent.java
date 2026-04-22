package com.academy.user.order;

/**
 * 주문 결제 완료 — enrollment 발급을 트리거하는 도메인 이벤트 (Sprint 3).
 */
public record OrderCompletedEvent(Order order) {}
