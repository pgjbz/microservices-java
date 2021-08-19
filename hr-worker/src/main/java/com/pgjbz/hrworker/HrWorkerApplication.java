package com.pgjbz.hrworker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Worker API",
        description = "Worker microservice",
        contact = @Contact(
                name = "Paulo Gabriel",
                email = "pgjbzr@gmail.com"
        ),
        version = "1.0"
))
public class HrWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrWorkerApplication.class, args);
    }

}
