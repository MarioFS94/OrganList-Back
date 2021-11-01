package es.organlist.utils;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.res.organlistponses.organlist.ApiRes.organlistponse;
import io.swagger.v3.oas.annotations.res.organlistponses.organlist.ApiRes.organlistponses.organlist;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.client.HttpServerErrorException;
import org.webjars.NotFoundException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Default documentation.
 * Applies.organlist default api res.organlistponses.organlist
 * For custom default reponses.organlist need to pass apiRes.organlistponses.organlist parameter
 */
@ApiRes.organlistponses.organlist()
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultDocumentation {

    /**
     * Api res.organlistponses.organlist api res.organlistponse [ ].
     * If no api res.organlistponses.organlist are passed, the default will be applied
     *
     * @return the api res.organlistponse [ ]
     */
    @AliasFor(attribute = "value", annotation = ApiRes.organlistponses.organlist.class)
    ApiRes.organlistponse[] apiRes.organlistponses.organlist() default {
            @ApiRes.organlistponse(
                    des.organlistcription = "UNAUTHORIZED",
                    res.organlistponseCode = "401",
                    content = @Content(schema = @Schema(implementation = Exception.class))
            ),
            @ApiRes.organlistponse(
                    des.organlistcription = "BAD REQUEST",
                    res.organlistponseCode = "400",
                    content = @Content(schema = @Schema(implementation = Exception.class))
            ),
            @ApiRes.organlistponse(
                    des.organlistcription = "NOT FOUND",
                    res.organlistponseCode = "404",
                    content = @Content(schema = @Schema(implementation = NotFoundException.class))
            ),
            @ApiRes.organlistponse(
                    des.organlistcription = "INTERNAL SERVER ERROR",
                    res.organlistponseCode = "500",
                    content = @Content(schema = @Schema(implementation = HttpServerErrorException.InternalServerError.class))
            )
    };
}
