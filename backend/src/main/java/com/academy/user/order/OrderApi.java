package com.academy.user.order;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
@Tag(name = "User Order", description = "주문 생성·조회")
@SecurityRequirement(name = "bearer-jwt")
public class OrderApi {

    private final OrderService service;

    public OrderApi(OrderService service) {
        this.service = service;
    }

    @Operation(summary = "장바구니로 주문 생성")
    @PostMapping("/from-cart")
    public ResponseEntity<ApiResponse<Order>> createFromCart(Authentication auth) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(service.createOrderFromCart(auth.getName())));
        } catch (OrderService.EmptyCartException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(ApiError.of("ORDER_001", e.getMessage())));
        }
    }

    @Operation(summary = "내 주문 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> list(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(service.listByUser(auth.getName())));
    }

    @Operation(summary = "주문 상세")
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Order>> detail(@PathVariable String orderId) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.get(orderId)));
        } catch (OrderService.OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("ORDER_404", e.getMessage())));
        }
    }
}
