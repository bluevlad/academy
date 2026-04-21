package com.academy;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.academy.mapper") // Mapper 인터페이스가 있는 패키지 경로class AcademyApplication {
class AcademyApplicationTests {

	@Test
	void contextLoads() {
	}

}
