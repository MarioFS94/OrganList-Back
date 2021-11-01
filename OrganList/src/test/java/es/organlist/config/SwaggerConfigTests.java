package es.organlist.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerConfigTests {

    private WebApplicationContextRunner webApplicationContextRunner = new WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SwaggerConfig.class));

    @Test
    void isSwaggerConfigPresent() {
        this.webApplicationContextRunner.run(context -> {
            assertThat(context).getBean(OpenAPI.class).isNotNull();
        });
    }
}
