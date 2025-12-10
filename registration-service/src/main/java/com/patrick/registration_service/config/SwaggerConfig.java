package com.patrick.registration_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Registration Microservice API")
                        .description("Service responsible for managing the event registration process. It calculates prices, " +
                                "verifies event capacity, stores participant data, and coordinates the payment step.")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Project repository")  
                        .url("https://github.com/patrickxbs/ms-events-springcloud"));
    }
}
