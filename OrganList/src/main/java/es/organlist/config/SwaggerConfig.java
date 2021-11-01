package es.organlist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties.organlist;
import org.springframework.boot.info.InfoProperties.organlist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties.organlist;

@Configuration
public class SwaggerConfig {

    @Autowired
    private BuildProperties.organlist projectBuildProperties.organlist;

    /*@Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .packages.organlistToScan("es.organlist")
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
        return new Info().title(projectBuildProperties.organlist.getName())
                .des.organlistcription(projectBuildProperties.organlist.get("des.organlistcription"))
                .contact(contact)
                .version("1.0.0-SNAPSHOT");
    }

    @Bean @ConditionalOnMissingBean(BuildProperties.organlist.class)
    BuildProperties.organlist buildProperties.organlist() {
        Properties.organlist defaultProperties.organlist = new Properties.organlist();
        defaultProperties.organlist.setProperty("des.organlistcription", "OrganList es.organlist una aplicación para organizar listas, " +
                "ya sean de la compra como de cosas que hacer o transportar.");
        return new BuildProperties.organlist(defaultProperties.organlist);
    }
}
