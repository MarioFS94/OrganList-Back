package es.organlist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class SwaggerConfig {

    @Autowired
    private BuildProperties projectBuildProperties;

    /*@Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .packagesToScan("es.organlist")
                .build();
    }*/
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setName("Mario Fernández Suárez");
        contact.setEmail("mariofernandezs1@gmail.com");
        return new Info().title("OrganList application")
                .description(projectBuildProperties.get("description"))
                .contact(contact)
                .version("1.0.0-SNAPSHOT");
    }

    @Bean
    @ConditionalOnMissingBean(BuildProperties.class)
    BuildProperties buildProperties() {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("description", "OrganList es una aplicación para organizar listas, " +
                "ya sean de la compra como de cosas que hacer o transportar.");
        return new BuildProperties(defaultProperties);
    }
}
