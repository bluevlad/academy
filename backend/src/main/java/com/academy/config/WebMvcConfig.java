package com.academy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.academy.common.ApiLoggingInterceptor;
import com.academy.common.PaginationAuthInterceptor;

@Configuration
@SuppressWarnings("null")
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApiLoggingInterceptor apiLoggingInterceptor;
    private final PaginationAuthInterceptor paginationAuthInterceptor;

    @Autowired
    public WebMvcConfig(@NonNull ApiLoggingInterceptor apiLoggingInterceptor,
                        @NonNull PaginationAuthInterceptor paginationAuthInterceptor) {
        this.apiLoggingInterceptor = apiLoggingInterceptor;
        this.paginationAuthInterceptor = paginationAuthInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        // API 로깅 인터셉터
        registry.addInterceptor(apiLoggingInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**"
                );

        // 페이지네이션 권한 체크 인터셉터
        // - 첫 페이지는 누구나 조회 가능
        // - 2페이지 이상은 ADMIN 권한 + 유효한 토큰 필요
        registry.addInterceptor(paginationAuthInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                    "/api/auth/**",           // 로그인/회원가입 API 제외
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**"
                );
    }
}