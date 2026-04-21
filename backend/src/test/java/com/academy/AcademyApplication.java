package com.academy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.academy.mapper") // Mapper 인터페이스가 있는 패키지 경로
public class AcademyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcademyApplication.class, args);
    }
}