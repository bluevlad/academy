package com.academy.shared.common;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Sprint 0 통합 smoke — {@code /api/shared/health} 가 기대한 JSON 구조로 200 응답하는지 검증.
 *
 * <p>Spring context 를 띄우지 않는 standalone MockMvc. DB/Redis 의존성 없이 controller 자체만
 * 검증한다. Security permitAll 규칙은 SecurityConfig 코드로 보장되며, 실환경에서는
 * docker-compose 기동 후 {@code curl -f http://localhost:9000/api/shared/health} 로 확인.
 */
class HealthControllerTest {

    private MockMvc mockMvc;

    @Test
    void health_returns_up_with_app_metadata() throws Exception {
        HealthController controller = new HealthController();
        ReflectionTestUtils.setField(controller, "appName", "academy-integrated");
        ReflectionTestUtils.setField(controller, "profile", "test");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/shared/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"))
            .andExpect(jsonPath("$.application").value("academy-integrated"))
            .andExpect(jsonPath("$.profile").value("test"))
            .andExpect(jsonPath("$.timestamp").exists());
    }
}
