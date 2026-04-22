package com.academy.user.delivery;

import com.academy.shared.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/delivery")
@Tag(name = "User Delivery Address", description = "배송지 관리")
@SecurityRequirement(name = "bearer-jwt")
public class DeliveryApi {

    private final DeliveryMapper mapper;

    public DeliveryApi(DeliveryMapper mapper) {
        this.mapper = mapper;
    }

    @Operation(summary = "내 배송지 목록")
    @GetMapping("/addresses")
    public ResponseEntity<ApiResponse<List<DeliveryAddress>>> addresses(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.ok(mapper.findAddressesByUserId(auth.getName())));
    }

    @Operation(summary = "배송지 등록")
    @PostMapping("/addresses")
    public ResponseEntity<ApiResponse<Void>> addAddress(Authentication auth, @Valid @RequestBody AddressRequest req) {
        mapper.insertAddress(UUID.randomUUID().toString(), auth.getName(),
            req.recipient(), req.phone(), req.zipCode(), req.address1(), req.address2(), req.isDefault());
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @Operation(summary = "배송지 삭제")
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<ApiResponse<Void>> delete(Authentication auth, @PathVariable String addressId) {
        mapper.deleteAddress(addressId, auth.getName());
        return ResponseEntity.ok(ApiResponse.ok());
    }

    public record AddressRequest(
        @NotBlank String recipient,
        String phone,
        String zipCode,
        @NotBlank String address1,
        String address2,
        boolean isDefault
    ) {}
}
