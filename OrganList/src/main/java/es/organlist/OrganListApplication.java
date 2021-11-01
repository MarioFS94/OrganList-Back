package es.organlist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class OrganListApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganListApplication.class, args);
    }

}
