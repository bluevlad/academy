package com.academy.shared.auth;

import com.academy.shared.auth.dto.LoginRequest;
import com.academy.shared.auth.dto.LogoutRequest;
import com.academy.shared.auth.dto.MeResponse;
import com.academy.shared.auth.dto.RefreshRequest;
import com.academy.shared.auth.dto.TokenResponse;
import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import com.academy.shared.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 엔드포인트 (ADR-002).
 *
 * <p>모든 엔드포인트는 {@code /api/auth/**} 로 permitAll — 별도 JwtFilter 미통과.
 * {@link #me}는 Authorization 헤더를 직접 파싱해서 claims 반환 (SecurityContext 미의존).
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "로그인·리프레시·로그아웃·프로필 (통합)")
public class AuthApi {

    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;

    public AuthApi(AuthService authService, JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
    }

    @Operation(summary = "로그인", description = "audience=admin|user 로 발급 대상 구분")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest req) {
        try {
            TokenResponse token = authService.login(req);
            return ResponseEntity.ok(ApiResponse.ok(token));
        } catch (AuthService.AuthFailedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.fail(ApiError.of("AUTH_001", e.getMessage())));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(ApiError.of("AUTH_002", e.getMessage())));
        }
    }

    @Operation(summary = "access 토큰 재발급", description = "refreshToken 유효 시 새 access(+refresh) 반환")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@Valid @RequestBody RefreshRequest req) {
        try {
            TokenResponse token = authService.refresh(req.refreshToken());
            return ResponseEntity.ok(ApiResponse.ok(token));
        } catch (AuthService.AuthFailedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.fail(ApiError.of("AUTH_003", e.getMessage())));
        }
    }

    @Operation(summary = "로그아웃", description = "refreshToken 전달 시 해당 토큰 폐기 (멱등)")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody(required = false) LogoutRequest req) {
        String refresh = req == null ? null : req.refreshToken();
        authService.logout(refresh);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @Operation(summary = "현재 토큰 정보", description = "Authorization: Bearer <access> 필수")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MeResponse>> me(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.fail(ApiError.of("AUTH_004", "Bearer 토큰이 필요합니다.")));
        }
        String token = header.substring("Bearer ".length()).trim();
        try {
            JwtTokenProvider.ParsedToken parsed = tokenProvider.parse(token);
            if (!parsed.isAccess()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail(ApiError.of("AUTH_005", "access 토큰이 아닙니다.")));
            }
            return ResponseEntity.ok(ApiResponse.ok(new MeResponse(
                parsed.userId(), parsed.role().name(), parsed.audience().value(), parsed.expiresAt()
            )));
        } catch (JwtTokenProvider.InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.fail(ApiError.of("AUTH_006", e.getMessage())));
        }
    }
}
