package com.academy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(List.of(
                        new Server().url("https://academy.unmong.com/admin").description("Production Server"),
                        new Server().url("http://academy.unmong.com:9001/admin").description("Local Development Server")
                ));
    }

    private Info apiInfo() {
        return new Info()
                .title("Academy Backend API")
                .description("Small Online Academy Backend Service API Documentation")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Academy Admin")
                        .email("admin@academy.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0"));
    }

}