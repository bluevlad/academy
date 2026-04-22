package com.academy;

import com.academy.shared.security.InMemoryRefreshTokenStore;
import com.academy.shared.security.RefreshTokenStore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Phase 1 (Sprint 1) 통합 golden path smoke — Testcontainers MariaDB 위에서:
 *
 * <ol>
 *   <li>POST /api/auth/signup — 신규 수강생</li>
 *   <li>POST /api/auth/login (audience=user) — access/refresh 발급</li>
 *   <li>GET /api/user/mypage/profile (Bearer) — 200</li>
 *   <li>PUT /api/user/mypage/password — 비밀번호 변경 + refresh 일괄 폐기</li>
 *   <li>POST /api/auth/login (새 비번) — 재로그인</li>
 *   <li>DELETE /api/user/mypage/account — 탈퇴 (soft delete)</li>
 *   <li>POST /api/auth/login (탈퇴 계정) — 401</li>
 *   <li>POST /api/auth/login (audience=admin, default bootstrap 계정) — 200</li>
 * </ol>
 *
 * <p>로컬 OrbStack 29.x 와 Testcontainers 1.21 docker-java 간 API 버전 불일치로
 * 현재 기본 {@code @Disabled}. owner 가 Docker Engine 27 이하 또는 호환 해결 후 활성화.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@MapperScan({
    "com.academy.mapper",
    "com.academy.shared.admin",
    "com.academy.user.login"
})
@Import(Phase1IntegrationTest.TestStoreConfig.class)
@Disabled(
    "Testcontainers docker-java 가 OrbStack 29.x 와 API 버전 negotiation 실패. " +
    "owner: DOCKER_HOST=unix:///Users/rainend/.orbstack/run/docker.sock 후 활성화."
)
class Phase1IntegrationTest {

    @Container
    static final MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb:11.4")
        .withDatabaseName("acm_basic")
        .withUsername("test")
        .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry reg) {
        reg.add("spring.datasource.url", mariadb::getJdbcUrl);
        reg.add("spring.datasource.username", mariadb::getUsername);
        reg.add("spring.datasource.password", mariadb::getPassword);
        reg.add("spring.datasource.driver-class-name", () -> "org.mariadb.jdbc.Driver");
        reg.add("spring.flyway.baseline-on-migrate", () -> "true");
        reg.add("spring.flyway.baseline-version", () -> "1");
        reg.add("spring.data.redis.host", () -> "localhost");
        reg.add("spring.data.redis.port", () -> "6379");
        reg.add("app.admin.username", () -> "admin");
        reg.add("app.admin.password", () -> "dnflskfk");
        reg.add("app.super-admin-emails", () -> "");
    }

    @Autowired TestRestTemplate rest;

    @Test
    void phase1_golden_path() {
        // 1. signup
        ResponseEntity<Map> signup = rest.postForEntity(
            "/api/auth/signup",
            Map.of("userId", "p1-stu", "password", "p@ssword1", "userNm", "Phase1 학생", "email", "p1@example.com"),
            Map.class
        );
        assertThat(signup.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // 2. user login
        Map<String, Object> token = userLogin("p1-stu", "p@ssword1");
        String access = (String) token.get("accessToken");

        // 3. mypage profile
        ResponseEntity<Map> profile = rest.exchange(
            "/api/user/mypage/profile", HttpMethod.GET, bearer(access), Map.class
        );
        assertThat(profile.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((Map<?, ?>) profile.getBody().get("data")).get("userId")).isEqualTo("p1-stu");

        // 4. change password
        ResponseEntity<Map> pwd = rest.exchange(
            "/api/user/mypage/password", HttpMethod.PUT,
            bearer(access, Map.of("currentPassword", "p@ssword1", "newPassword", "brandNew99")),
            Map.class
        );
        assertThat(pwd.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 5. re-login with new password
        Map<String, Object> token2 = userLogin("p1-stu", "brandNew99");
        String access2 = (String) token2.get("accessToken");
        assertThat(access2).isNotBlank().isNotEqualTo(access);

        // 6. withdraw
        ResponseEntity<Map> out = rest.exchange(
            "/api/user/mypage/account", HttpMethod.DELETE, bearer(access2), Map.class
        );
        assertThat(out.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 7. login after withdraw — 401
        ResponseEntity<Map> fail = rest.postForEntity(
            "/api/auth/login",
            Map.of("userId", "p1-stu", "password", "brandNew99", "audience", "user"),
            Map.class
        );
        assertThat(fail.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        // 8. admin login (default bootstrap)
        ResponseEntity<Map> admin = rest.postForEntity(
            "/api/auth/login",
            Map.of("userId", "admin", "password", "dnflskfk", "audience", "admin"),
            Map.class
        );
        assertThat(admin.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((Map<?, ?>) admin.getBody().get("data")).get("role")).isEqualTo("ADMIN");
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> userLogin(String userId, String password) {
        ResponseEntity<Map> res = rest.postForEntity(
            "/api/auth/login",
            Map.of("userId", userId, "password", password, "audience", "user"),
            Map.class
        );
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        return (Map<String, Object>) res.getBody().get("data");
    }

    private HttpEntity<Void> bearer(String access) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Map<String, Object>> bearer(String access, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access);
        return new HttpEntity<>(body, headers);
    }

    @TestConfiguration
    static class TestStoreConfig {
        @Bean
        @Primary
        RefreshTokenStore testRefreshStore() {
            return new InMemoryRefreshTokenStore();
        }
    }
}
