package com.academy.shared.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

/**
 * 최초 부팅 시 {@code ADMIN_USERNAME}/{@code ADMIN_PASSWORD} 기반 관리자 1명과
 * {@code SUPER_ADMIN_EMAILS} 의 이메일 슈퍼관리자들을 {@code id_admin} 에 upsert.
 *
 * <p>이미 존재하는 username 은 덮어쓰지 않는다 (비밀번호 회전은 별도 절차).
 * SUPER_ADMIN_EMAILS 는 OAuth 전용이므로 임시 랜덤 password_hash 로 insert — 실제 로그인은 OAuth 흐름.
 */
@Component
public class AdminBootstrap implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminBootstrap.class);

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final String adminUsername;
    private final String adminPassword;
    private final String superAdminEmails;

    public AdminBootstrap(
        AdminMapper adminMapper,
        PasswordEncoder passwordEncoder,
        @Value("${app.admin.username:admin}") String adminUsername,
        @Value("${app.admin.password:dnflskfk}") String adminPassword,
        @Value("${app.super-admin-emails:}") String superAdminEmails
    ) {
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.superAdminEmails = superAdminEmails;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        ensureDefaultAdmin();
        ensureSuperAdmins();
    }

    private void ensureDefaultAdmin() {
        if (adminUsername == null || adminUsername.isBlank()) return;
        if (adminMapper.existsByUsername(adminUsername) > 0) {
            log.debug("default admin 이미 존재 — skip: {}", adminUsername);
            return;
        }
        AdminVO admin = new AdminVO();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setUsername(adminUsername);
        admin.setEmail(null);
        admin.setPasswordHash(passwordEncoder.encode(adminPassword));
        admin.setDisplayName("Default Administrator");
        admin.setRole("ROLE_ADMIN");
        admin.setEnabled(true);
        adminMapper.insert(admin);
        log.info("default admin 생성: {}", adminUsername);
    }

    private void ensureSuperAdmins() {
        if (superAdminEmails == null || superAdminEmails.isBlank()) return;
        Arrays.stream(superAdminEmails.split(","))
            .map(String::trim)
            .filter(s -> !s.isBlank())
            .forEach(this::ensureSuperAdmin);
    }

    private void ensureSuperAdmin(String email) {
        String username = email;
        if (adminMapper.existsByUsername(username) > 0) {
            return;
        }
        AdminVO admin = new AdminVO();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPasswordHash(passwordEncoder.encode(UUID.randomUUID().toString()));
        admin.setDisplayName("Super Admin (OAuth)");
        admin.setRole("ROLE_ADMIN");
        admin.setEnabled(true);
        adminMapper.insert(admin);
        log.info("super admin 생성 (OAuth 전용): {}", email);
    }
}
