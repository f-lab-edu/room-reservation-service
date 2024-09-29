package com.ryan.roomreservationservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("객실 예약 관리 서비스")
                        .description("객실 예약 관리 서비스 서버 API")
                        .version("v1"))
                .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")))
                .security(Collections.singletonList(new SecurityRequirement().addList("bearerAuth")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/member/sign-up", "/member/sign-in")
                .build();
    }

    @Bean
    public GroupedOpenApi securedApi() {
        return GroupedOpenApi.builder()
                .group("secured")
                .pathsToMatch("/**")
                .pathsToExclude("/member/sign-up", "/member/sign-in")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
                    return operation;
                })
                .build();
    }
}
