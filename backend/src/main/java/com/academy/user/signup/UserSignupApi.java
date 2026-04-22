package com.academy.user.signup;

import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import com.academy.user.signup.dto.SignupRequest;
import com.academy.user.signup.dto.SignupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 수강생 이메일 가입 엔드포인트 (Sprint 1-3).
 *
 * <p>경로는 {@code /api/auth/**} 하위 — SecurityConfig permitAll 적용.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Signup", description = "수강생 회원가입")
public class UserSignupApi {

    private final UserSignupService signupService;

    public UserSignupApi(UserSignupService signupService) {
        this.signupService = signupService;
    }

    @Operation(summary = "이메일 회원가입", description = "userId 중복 시 409, email 중복 시 409")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@Valid @RequestBody SignupRequest req) {
        try {
            SignupResponse resp = signupService.signup(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(resp));
        } catch (UserSignupService.DuplicateAccountException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.fail(ApiError.of("SIGNUP_001", e.getMessage())));
        }
    }
}
