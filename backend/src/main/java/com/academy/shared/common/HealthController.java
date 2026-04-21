package com.academy.shared.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * academy-integrated 통합 상태 엔드포인트.
 *
 * <p>Sprint 0 의 통합 성공 지표 — /api/shared/health 가 200 응답하면
 * admin/user/shared 패키지가 한 Spring context 에서 부팅된 것.
 */
@RestController
@RequestMapping("/api/shared")
@Tag(name = "Shared — Health", description = "통합 상태·빌드 정보")
public class HealthController {

    @Value("${spring.application.name:academy-integrated}")
    private String appName;

    @Value("${spring.profiles.active:default}")
    private String profile;

    @GetMapping("/health")
    @Operation(summary = "통합 헬스 체크")
    public Map<String, Object> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "UP");
        body.put("application", appName);
        body.put("profile", profile);
        body.put("timestamp", Instant.now().toString());
        return body;
    }
}
