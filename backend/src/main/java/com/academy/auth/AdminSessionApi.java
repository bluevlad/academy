package com.academy.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 관리자 세션 인증 API.
 *  - POST /api/admin-auth/login   : 폼 기반 관리자 로그인 (세션 생성)
 *  - POST /api/admin-auth/logout  : 세션 만료
 *  - GET  /api/admin-auth/me      : 현재 인증 상태 조회 (항상 200 반환)
 */
@RestController
@RequestMapping("/api/admin-auth")
public class AdminSessionApi {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AdminSessionApi(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        String username = body.get("username");
        String password = body.get("password");

        if (username == null || password == null) {
            return ResponseEntity.status(400).body(Map.of("error", "username/password is required"));
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);

            Map<String, Object> result = new HashMap<>();
            result.put("username", auth.getName());
            result.put("role", "ADMIN");
            result.put("loginType", "admin-form");
            return ResponseEntity.ok(result);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "아이디 또는 비밀번호가 일치하지 않습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "로그인 실패: " + e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "로그아웃 되었습니다."));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> result = new HashMap<>();
        if (auth == null
            || !auth.isAuthenticated()
            || "anonymousUser".equals(String.valueOf(auth.getPrincipal()))) {
            result.put("authenticated", false);
            return ResponseEntity.ok(result);
        }
        result.put("authenticated", true);
        result.put("username", auth.getName());
        result.put("authorities", auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));
        return ResponseEntity.ok(result);
    }
}
