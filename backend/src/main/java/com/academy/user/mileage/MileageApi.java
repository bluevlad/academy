package com.academy.user.mileage;

import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/mileage")
@Tag(name = "User Mileage", description = "마일리지 잔액·이력")
@SecurityRequirement(name = "bearer-jwt")
public class MileageApi {

    private final MileageMapper mapper;

    public MileageApi(MileageMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "마일리지 잔액")
    @GetMapping("/balance")
    public ResponseEntity<ApiResponse<Map<String, BigDecimal>>> balance(Authentication auth) {
        BigDecimal b = mapper.sumBalance(auth.getName());
        return ResponseEntity.ok(ApiResponse.ok(Map.of("balance", b == null ? BigDecimal.ZERO : b)));
    }

    @Operation(summary = "마일리지 원장 이력 (최근 limit 건, 기본 50)")
    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<MileageLedgerEntry>>> history(
        Authentication auth,
        @RequestParam(defaultValue = "50") int limit
    ) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findHistory(auth.getName(), Math.min(limit, 500))));
    }
}
