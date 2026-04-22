package com.academy.user.cart;

import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/cart")
@Tag(name = "User Cart", description = "수강 장바구니")
@SecurityRequirement(name = "bearer-jwt")
public class CartApi {

    private final CartMapper mapper;

    public CartApi(CartMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "내 장바구니")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItem>>> list(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findByUserId(auth.getName())));
    }

    @Operation(summary = "장바구니 담기 (동일 mstCode 면 수량 합산)")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> add(Authentication auth, @Valid @RequestBody AddRequest req) {
        mapper.upsert(UUID.randomUUID().toString(), auth.getName(), req.mstCode(), req.quantity(), req.priceSnapshot());
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @Operation(summary = "장바구니에서 제거")
    @DeleteMapping("/{mstCode}")
    public ResponseEntity<ApiResponse<Void>> remove(Authentication auth, @PathVariable String mstCode) {
        mapper.delete(auth.getName(), mstCode);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @Operation(summary = "장바구니 비우기")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> clear(Authentication auth) {
        mapper.clear(auth.getName());
        return ResponseEntity.ok(ApiResponse.ok());
    }

    public record AddRequest(@NotBlank String mstCode, @Min(1) int quantity, BigDecimal priceSnapshot) {}
}
