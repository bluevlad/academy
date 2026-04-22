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
 * Phase 2 (Sprint 2) smoke — 인증된 수강생이 강의·과목·교수진 조회.
 *
 * <p>전제: Testcontainer MariaDB 에 TB_TOP_MST·TB_SUBJECT_INFO·TB_MA_MEMBER 가
 * 미리 로드되어 있어야 실 쿼리 검증 가능. 현재는 빈 스키마에서 빈 응답 검증까지.
 * OrbStack 호환 이슈로 @Disabled.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@MapperScan({
    "com.academy.mapper",
    "com.academy.shared.admin",
    "com.academy.user.login",
    "com.academy.user.content.lecture",
    "com.academy.user.content.subject",
    "com.academy.user.content.teacher"
})
@Import(Phase2IntegrationTest.TestStoreConfig.class)
@Disabled("Testcontainers / OrbStack 29.x docker-java 호환 이슈. 해결 시 제거.")
class Phase2IntegrationTest {

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
    void user_can_browse_content_after_signup_and_login() {
        rest.postForEntity("/api/auth/signup",
            Map.of("userId", "p2-stu", "password", "p@ssword1", "userNm", "Phase2", "email", "p2@example.com"),
            Map.class);
        ResponseEntity<Map> login = rest.postForEntity("/api/auth/login",
            Map.of("userId", "p2-stu", "password", "p@ssword1", "audience", "user"), Map.class);
        String access = (String) ((Map<?, ?>) login.getBody().get("data")).get("accessToken");

        ResponseEntity<Map> lectures = rest.exchange("/api/user/lecture?page=1&size=20",
            HttpMethod.GET, bearer(access), Map.class);
        assertThat(lectures.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Map> subjects = rest.exchange("/api/user/subject",
            HttpMethod.GET, bearer(access), Map.class);
        assertThat(subjects.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<Map> teachers = rest.exchange("/api/user/teacher",
            HttpMethod.GET, bearer(access), Map.class);
        assertThat(teachers.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private HttpEntity<Void> bearer(String access) {
        HttpHeaders h = new HttpHeaders();
        h.setBearerAuth(access);
        return new HttpEntity<>(h);
    }

    @TestConfiguration
    static class TestStoreConfig {
        @Bean @Primary RefreshTokenStore testRefreshStore() { return new InMemoryRefreshTokenStore(); }
    }
}
