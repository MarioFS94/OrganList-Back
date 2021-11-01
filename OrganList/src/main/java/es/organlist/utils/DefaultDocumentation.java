package es.organlist.utils;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.client.HttpServerErrorException;
import org.webjars.NotFoundException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Default documentation.
 * Applies default api responses
 * For custom default reponses need to pass apiResponses parameter
 */
@ApiResponses()
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultDocumentation {

    /**
     * Api responses api response [ ].
     * If no api responses are passed, the default will be applied
     *
     * @return the api response [ ]
     */
    @AliasFor(attribute = "value", annotation = ApiResponses.class)
    ApiResponse[] apiResponses() default {
            @ApiResponse(
                    description = "UNAUTHORIZED",
                    responseCode = "401",
                    content = @Content(schema = @Schema(implementation = Exception.class))
            ),
            @ApiResponse(
                    description = "BAD REQUEST",
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = Exception.class))
            ),
            @ApiResponse(
                    description = "NOT FOUND",
                    responseCode = "404",
                    content = @Content(schema = @Schema(implementation = NotFoundException.class))
            ),
            @ApiResponse(
                    description = "INTERNAL SERVER ERROR",
                    responseCode = "500",
                    content = @Content(schema = @Schema(implementation = HttpServerErrorException.InternalServerError.class))
            )
    };
}
