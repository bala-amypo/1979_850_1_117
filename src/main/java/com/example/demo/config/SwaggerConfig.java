package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IT Policy Violation Detection API")
                        .version("1.0")
                        .description("API for IT Policy Violation Detection System with Test Cases"))
                .servers(List.of(
                        new Server().url("https://9280.408procr.amypo.ai/").description("Local server")
                ));
    }
}