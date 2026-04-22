package com.academy.user.payment;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import com.academy.user.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/payment")
@Tag(name = "User Payment", description = "결제 (Sprint 3 mock PG)")
@SecurityRequirement(name = "bearer-jwt")
public class PaymentApi {

    private final PaymentService service;

    public PaymentApi(PaymentService service) {
        this.service = service;
    }

    @Operation(summary = "mock 결제 승인 — 즉시 APPROVED + OrderCompletedEvent 발행")
    @PostMapping("/mock")
    public ResponseEntity<ApiResponse<Payment>> mock(@RequestBody PayRequest req) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.payMock(req.orderId())));
        } catch (OrderService.OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("PAY_404", e.getMessage())));
        }
    }

    public record PayRequest(@NotBlank String orderId) {}
}
