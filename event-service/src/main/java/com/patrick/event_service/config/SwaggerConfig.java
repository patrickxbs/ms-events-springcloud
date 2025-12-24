package com.patrick.event_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Microservice API")
                        .description("Service responsible for managing events, including creation, listing, retrieval by ID, " +
                                "and capacity updates. Provides the necessary data for the registration workflow.")
                        .version("1.0"))
                .servers(List.of(new Server()
                        .url("http://localhost:8000")
                        .description("Event Server")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project repository")
                        .url("https://github.com/patrickxbs/ms-events-springcloud"));
    }

}
