package com.patrick.payment_service.config;

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
                        .title("Payment Microservice API")
                        .description("Service responsible for simulating payment processing. It receives requests from the " +
                                "registration service and returns a payment status (approved or rejected) randomly.")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Project repository")
                        .url("https://github.com/patrickxbs/ms-events-springcloud"));
    }
}
