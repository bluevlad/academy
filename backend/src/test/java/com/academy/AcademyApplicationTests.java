package com.academy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 전체 Spring context 부팅 검증. 실제 MariaDB · Redis 에 연결되므로
 * 인프라 기동된 환경에서만 수행 가능.
 *
 * <p>Sprint 0 에서는 {@link com.academy.shared.common.HealthControllerTest} 로
 * controller smoke 만 검증한다. Sprint 1+ 에서 Testcontainers 도입 후 재활성화.
 */
@SpringBootTest
@MapperScan("com.academy.mapper")
@Disabled("Sprint 1 에 Testcontainers(MariaDB+Redis) 도입 후 활성화")
class AcademyApplicationTests {

	@Test
	void contextLoads() {
	}

}
