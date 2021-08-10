package com.pgjbz.hrpayroll.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI(@Value("${info.app.version}") String version,
                                     @Value("${info.app.name}") String name,
                                     @Value("${info.app.description}") String description) {
        return new OpenAPI()
                .info(new Info().title(name)
                        .description(description)
                        .version(version));
    }

}
