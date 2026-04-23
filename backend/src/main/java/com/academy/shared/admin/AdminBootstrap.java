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
 * 최초 부팅 시 {@code app.admin.usernames}(legacy: {@code app.admin.username}) +
 * {@code ADMIN_PASSWORD} 기반 관리자들과 {@code SUPER_ADMIN_EMAILS} 의 이메일 슈퍼관리자를
 * {@code id_admin} 에 upsert. 이미 존재하는 username 은 패스워드·이메일·role 만 동기화.
 *
 * <p>SUPER_ADMIN_EMAILS 는 OAuth 전용이므로 임시 랜덤 password_hash 로 insert — 실제 로그인은 OAuth 흐름.
 */
@Component
public class AdminBootstrap implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminBootstrap.class);

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final String adminUsernames;
    private final String adminPassword;
    private final String superAdminEmails;

    public AdminBootstrap(
        AdminMapper adminMapper,
        PasswordEncoder passwordEncoder,
        @Value("${app.admin.usernames:${app.admin.username:admin}}") String adminUsernames,
        @Value("${app.admin.password:dnflskfk}") String adminPassword,
        @Value("${app.super-admin-emails:}") String superAdminEmails
    ) {
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
        this.adminUsernames = adminUsernames;
        this.adminPassword = adminPassword;
        this.superAdminEmails = superAdminEmails;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        ensureDefaultAdmins();
        ensureSuperAdmins();
    }

    private void ensureDefaultAdmins() {
        if (adminUsernames == null || adminUsernames.isBlank()) return;
        Arrays.stream(adminUsernames.split(","))
            .map(String::trim)
            .filter(s -> !s.isBlank())
            .forEach(this::upsertDefaultAdmin);
    }

    private void upsertDefaultAdmin(String username) {
        String passwordHash = passwordEncoder.encode(adminPassword);
        if (adminMapper.existsByUsername(username) > 0) {
            adminMapper.updateAccount(username, passwordHash, null, "ROLE_ADMIN", true);
            log.info("default admin 동기화 (password reset): {}", username);
            return;
        }
        AdminVO admin = new AdminVO();
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setUsername(username);
        admin.setEmail(null);
        admin.setPasswordHash(passwordHash);
        admin.setDisplayName("Default Administrator");
        admin.setRole("ROLE_ADMIN");
        admin.setEnabled(true);
        adminMapper.insert(admin);
        log.info("default admin 생성: {}", username);
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
            // 이메일·role·enabled 만 정렬. 비밀번호는 OAuth 전용이라 그대로 둔다.
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
