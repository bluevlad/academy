package com.academy.auth;

import com.academy.common.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

@RestController
@RequestMapping("/api/auth/google")
public class GoogleOAuthApi {

    @Value("${GOOGLE_CLIENT_ID:}")
    private String googleClientId;

    @Value("${app.super-admin-emails:}")
    private String superAdminEmails;

    private final JwtUtil jwtUtil;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public GoogleOAuthApi(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyGoogleToken(@RequestBody Map<String, String> body,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        String idTokenString = body.get("credential");
        if (idTokenString == null || idTokenString.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing credential"));
        }

        if (googleClientId == null || googleClientId.isEmpty()) {
            return ResponseEntity.status(500)
                .body(Map.of("error", "서버에 GOOGLE_CLIENT_ID 환경변수가 설정되지 않았습니다."));
        }

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String picture = (String) payload.get("picture");

            if (!isSuperAdmin(email)) {
                return ResponseEntity.status(403)
                    .body(Map.of("error", "관리자 권한이 없는 계정입니다: " + email));
            }

            // 세션 기반 인증 컨텍스트 저장
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                email,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);

            // 하위 호환: JWT도 함께 발급
            String token = jwtUtil.generateToken(email, "ADMIN");

            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("email", email);
            result.put("name", name);
            result.put("picture", picture);
            result.put("role", "ADMIN");
            result.put("loginType", "google");
            result.put("message", "Google admin login successful");

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(Map.of("error", "Google token verification failed: " + e.getMessage()));
        }
    }

    private boolean isSuperAdmin(String email) {
        if (superAdminEmails == null || superAdminEmails.isEmpty()) {
            return false;
        }
        return Arrays.stream(superAdminEmails.split(","))
            .map(String::trim)
            .map(String::toLowerCase)
            .anyMatch(e -> e.equals(email.toLowerCase().trim()));
    }
}
