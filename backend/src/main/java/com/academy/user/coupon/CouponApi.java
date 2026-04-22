package com.academy.user.coupon;

import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/coupon")
@Tag(name = "User Coupon", description = "보유 쿠폰 조회")
@SecurityRequirement(name = "bearer-jwt")
public class CouponApi {

    private final CouponMapper mapper;

    public CouponApi(CouponMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "내가 보유한 사용 가능 쿠폰 (미사용·유효기간 내·활성)")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CouponUser>>> usable(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findUsableByUserId(auth.getName())));
    }
}
