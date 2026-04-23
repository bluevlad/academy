package com.academy.auth;

import com.academy.shared.admin.AdminMapper;
import com.academy.shared.admin.AdminVO;
import com.academy.shared.common.ApiError;
import com.academy.shared.common.ApiResponse;
import com.academy.shared.security.Audience;
import com.academy.shared.security.JwtTokenProvider;
import com.academy.shared.security.RefreshTokenStore;
import com.academy.shared.security.Role;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Google ID Token 을 검증하고, super-admin 이메일이면 ADMIN audience JWT(access+refresh)를 발급.
 *
 * <p>/api/auth/google/verify 는 {@code /api/auth/**} 로 permitAll — JWT 필터 미통과.
 * 발급된 access/refresh 토큰은 {@link JwtTokenProvider}·{@link AdminJwtAuthenticationFilter}
 * 호환이라 이후 admin API 호출이 그대로 통과한다.
 */
@RestController
@RequestMapping("/api/auth/google")
@Tag(name = "Auth-Google", description = "Google OAuth 관리자 로그인")
public class GoogleOAuthApi {

    private static final Logger log = LoggerFactory.getLogger(GoogleOAuthApi.class);

    private final String googleClientId;
    private final String superAdminEmails;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenStore refreshStore;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public GoogleOAuthApi(
        @Value("${app.google.client-id:${GOOGLE_CLIENT_ID:}}") String googleClientId,
        @Value("${app.super-admin-emails:}") String superAdminEmails,
        JwtTokenProvider tokenProvider,
        RefreshTokenStore refreshStore,
        AdminMapper adminMapper,
        PasswordEncoder passwordEncoder
    ) {
        this.googleClientId = googleClientId;
        this.superAdminEmails = superAdminEmails;
        this.tokenProvider = tokenProvider;
        this.refreshStore = refreshStore;
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@code idToken} 을 정식 키로 사용 (hopenvision 프로젝트와 계약 통일).
     * 기존 {@code credential} 도 하위호환으로 수용.
     */
    public record GoogleVerifyRequest(String idToken, String credential) {
        public String token() {
            return (idToken != null && !idToken.isBlank()) ? idToken : credential;
        }
    }

    public record GoogleTokenResponse(
        String accessToken,
        String refreshToken,
        String userId,
        String role,
        String audience,
        Instant expiresAt,
        String email,
        String name,
        String picture
    ) {}

    @Operation(summary = "Google ID Token 검증 → 관리자 JWT 발급")
    @PostMapping("/verify")
    @Transactional
    public ResponseEntity<ApiResponse<GoogleTokenResponse>> verifyGoogleToken(
        @RequestBody GoogleVerifyRequest body
    ) {
        String token = body == null ? null : body.token();
        if (token == null || token.isBlank()) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.fail(ApiError.of("AUTH_GOOG_001", "idToken 이 필요합니다.")));
        }
        if (googleClientId == null || googleClientId.isBlank()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(ApiError.of(
                    "AUTH_GOOG_002", "서버에 GOOGLE_CLIENT_ID 가 설정되지 않았습니다.")));
        }

        GoogleIdToken.Payload payload;
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail(ApiError.of("AUTH_GOOG_003", "유효하지 않은 Google 토큰입니다.")));
            }
            payload = idToken.getPayload();
        } catch (Exception e) {
            log.warn("Google ID Token 검증 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.fail(ApiError.of(
                    "AUTH_GOOG_004", "Google 토큰 검증 실패: " + e.getMessage())));
        }

        String email = payload.getEmail();
        Boolean emailVerified = payload.getEmailVerified();
        if (email == null || !Boolean.TRUE.equals(emailVerified)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.fail(ApiError.of(
                    "AUTH_GOOG_005", "이메일이 확인되지 않은 Google 계정입니다.")));
        }
        if (!isSuperAdmin(email)) {
            log.info("관리자 권한 없는 Google 로그인 시도: {}", email);
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.fail(ApiError.of(
                    "AUTH_GOOG_006", "관리자 권한이 없는 계정입니다: " + email)));
        }

        String name = (String) payload.get("name");
        String picture = (String) payload.get("picture");

        AdminVO admin = upsertAdmin(email, name);

        // access + refresh 발급 — AuthService.issueTokens 와 동일 규약
        String access = tokenProvider.createAccessToken(admin.getUsername(), Role.ADMIN, Audience.ADMIN);
        JwtTokenProvider.IssuedRefreshToken refresh =
            tokenProvider.createRefreshToken(admin.getUsername(), Role.ADMIN, Audience.ADMIN);
        refreshStore.save(Audience.ADMIN, admin.getUsername(), refresh.jti(), refresh.ttl());
        adminMapper.updateLastLoginAt(admin.getAdminId());

        Instant expiresAt = Instant.now().plus(tokenProvider.accessTtl());
        log.info("Google 관리자 로그인 성공: {}", admin.getUsername());
        return ResponseEntity.ok(ApiResponse.ok(new GoogleTokenResponse(
            access, refresh.token(),
            admin.getUsername(), Role.ADMIN.name(), Audience.ADMIN.value(), expiresAt,
            admin.getEmail(), name, picture
        )));
    }

    private boolean isSuperAdmin(String email) {
        if (superAdminEmails == null || superAdminEmails.isBlank()) return false;
        String normalized = email.toLowerCase().trim();
        return Arrays.stream(superAdminEmails.split(","))
            .map(String::trim)
            .map(String::toLowerCase)
            .anyMatch(e -> e.equals(normalized));
    }

    /**
     * id_admin 테이블에서 이메일로 조회해 있으면 그대로, 없으면 새로 만들어서 반환.
     * username = email 로 통일해 AuthenticationManager · JWT sub 와 일치시킨다.
     */
    private AdminVO upsertAdmin(String email, String name) {
        return adminMapper.findByUsernameOrEmail(email)
            .orElseGet(() -> {
                AdminVO admin = new AdminVO();
                admin.setAdminId(UUID.randomUUID().toString());
                admin.setUsername(email);
                admin.setEmail(email);
                // OAuth 전용 — 폼 로그인 불가하도록 랜덤 hash
                admin.setPasswordHash(passwordEncoder.encode(UUID.randomUUID().toString()));
                admin.setDisplayName(name != null ? name : "Google Admin");
                admin.setRole("ROLE_ADMIN");
                admin.setEnabled(true);
                adminMapper.insert(admin);
                log.info("Google 첫 로그인 — id_admin 에 신규 생성: {}", email);
                return admin;
            });
    }
}
