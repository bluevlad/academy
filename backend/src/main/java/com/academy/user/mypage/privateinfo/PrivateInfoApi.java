package com.academy.user.mypage.privateinfo;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import com.academy.user.mypage.privateinfo.dto.CertificateResponse;
import com.academy.user.mypage.privateinfo.dto.PasswordChangeRequest;
import com.academy.user.mypage.privateinfo.dto.ProfileResponse;
import com.academy.user.mypage.privateinfo.dto.ProfileUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 수강생 마이페이지 — 개인정보·비밀번호·회원탈퇴·수강확인증 (P0 4건, Sprint 1-4).
 *
 * <p>{@code /api/user/**} prefix → UserJwtAuthenticationFilter 통과 + hasRole(USER) 필수.
 * 사용자 식별은 JWT 의 {@code sub} claim ({@link Authentication#getName()}).
 */
@RestController
@RequestMapping("/api/user/mypage")
@Tag(name = "User Mypage - PrivateInfo", description = "개인정보·비밀번호·회원탈퇴·수강확인증")
@SecurityRequirement(name = "bearer-jwt")
public class PrivateInfoApi {

    private final PrivateInfoService service;

    public PrivateInfoApi(PrivateInfoService service) {
        this.service = service;
    }

    @Operation(summary = "내 프로필 조회")
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(Authentication auth) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.getProfile(auth.getName())));
        } catch (PrivateInfoService.AccountNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    @Operation(summary = "개인정보 수정 — userNm, email")
    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
        Authentication auth,
        @Valid @RequestBody ProfileUpdateRequest req
    ) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.updateProfile(auth.getName(), req)));
        } catch (PrivateInfoService.AccountNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    @Operation(summary = "비밀번호 변경 — 기존 비번 확인 후 업데이트, 모든 refresh 폐기")
    @PutMapping("/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
        Authentication auth,
        @Valid @RequestBody PasswordChangeRequest req
    ) {
        try {
            service.changePassword(auth.getName(), req);
            return ResponseEntity.ok(ApiResponse.ok());
        } catch (PrivateInfoService.PasswordMismatchException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.fail(ApiError.of("PRIVATE_001", e.getMessage())));
        } catch (PrivateInfoService.PasswordPolicyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(ApiError.of("PRIVATE_002", e.getMessage())));
        } catch (PrivateInfoService.AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ApiError.of("PRIVATE_003", e.getMessage())));
        }
    }

    @Operation(summary = "회원탈퇴 — soft delete (is_use=N), 모든 refresh 폐기, 멱등")
    @DeleteMapping("/account")
    public ResponseEntity<ApiResponse<Void>> withdraw(Authentication auth) {
        service.withdraw(auth.getName());
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @Operation(summary = "수강확인증 — MVP skeleton (Sprint 3 enrollment 이후 실 데이터)")
    @GetMapping("/certificate")
    public ResponseEntity<ApiResponse<CertificateResponse>> certificate(Authentication auth) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(service.issueCertificate(auth.getName())));
        } catch (PrivateInfoService.AccountNotFoundException e) {
            return notFound(e.getMessage());
        }
    }

    private <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.fail(ApiError.of("PRIVATE_003", message)));
    }
}
