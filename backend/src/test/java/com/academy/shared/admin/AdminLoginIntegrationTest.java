package com.academy.shared.admin;

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
 * Sprint 1-1b 통합 smoke — Testcontainers MariaDB 위에서:
 * <ol>
 *   <li>Flyway V2 가 {@code id_admin} 테이블 생성</li>
 *   <li>{@link AdminBootstrap} 이 기본 관리자 upsert</li>
 *   <li>{@code POST /api/auth/login} 로 JWT 발급</li>
 * </ol>
 *
 * <p>Redis 는 {@link InMemoryRefreshTokenStore} 로 대체 (실제 Redis 미구동).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@MapperScan({"com.academy.mapper", "com.academy.shared.admin"})
@Import(AdminLoginIntegrationTest.TestStoreConfig.class)
@Disabled(
    "docker-java (Testcontainers 전이 의존) 가 API v1.32 로 negotiation — " +
    "OrbStack 29.x 는 v1.40 이상만 허용해 로컬에서 부팅 실패. " +
    "환경 호환 해결 시 (또는 CI 가 Docker Engine 27 이하를 사용할 때) 제거. " +
    "owner: DOCKER_HOST=unix:///Users/rainend/.orbstack/run/docker.sock 후 ./mvnw test -Dtest=AdminLoginIntegrationTest"
)
class AdminLoginIntegrationTest {

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
        // Flyway 는 baseline=1 이므로 V1 을 skip 하고 V2 부터 실행
        reg.add("spring.flyway.baseline-on-migrate", () -> "true");
        reg.add("spring.flyway.baseline-version", () -> "1");
        // Redis 는 실제로 호출되지 않지만 auto-config 가 bean 을 생성하므로 그대로 둔다
        reg.add("spring.data.redis.host", () -> "localhost");
        reg.add("spring.data.redis.port", () -> "6379");
        // 부팅 시 AdminBootstrap 이 이 계정을 upsert
        reg.add("app.admin.username", () -> "admin");
        reg.add("app.admin.password", () -> "dnflskfk");
        reg.add("app.super-admin-emails", () -> "");
    }

    @Autowired TestRestTemplate rest;

    @Test
    void default_admin_can_login_via_auth_api() {
        ResponseEntity<Map> res = rest.postForEntity(
            "/api/auth/login",
            Map.of("userId", "admin", "password", "dnflskfk", "audience", "admin"),
            Map.class
        );

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, Object> body = res.getBody();
        assertThat(body).isNotNull();
        assertThat(body.get("success")).isEqualTo(true);
        Map<String, Object> data = (Map<String, Object>) body.get("data");
        assertThat(data.get("accessToken")).isNotNull();
        assertThat(data.get("refreshToken")).isNotNull();
        assertThat(data.get("role")).isEqualTo("ADMIN");
        assertThat(data.get("audience")).isEqualTo("admin");
    }

    @Test
    void wrong_password_returns_401() {
        ResponseEntity<Map> res = rest.postForEntity(
            "/api/auth/login",
            Map.of("userId", "admin", "password", "WRONG", "audience", "admin"),
            Map.class
        );
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Map<String, Object> body = res.getBody();
        assertThat(body.get("success")).isEqualTo(false);
    }

    @Test
    void unknown_admin_returns_401() {
        ResponseEntity<Map> res = rest.postForEntity(
            "/api/auth/login",
            Map.of("userId", "ghost", "password", "x", "audience", "admin"),
            Map.class
        );
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
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
