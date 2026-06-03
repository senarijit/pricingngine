package com.autofinance.pricingengine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pricingEngineOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Auto Finance Pricing Engine API")
                        .description("API for vehicle retail estimate generation and pricing rules.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Auto Finance Team")
                                .email("support@autofinance.example.com")));
    }
}
