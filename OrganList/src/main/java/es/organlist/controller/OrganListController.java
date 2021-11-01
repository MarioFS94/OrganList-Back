package es.organlist.controller;

import es.organlist.utils.DefaultDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "OrganListController", description = "Initial controller")
public class OrganListController {

    @Operation(summary = "Welcome endpoint", description = "Welcome!!", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "You are welcome!",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping
    public String index(
            @RequestParam(required = false)
            @Parameter(description = "Nombre del autor") String name
    ) {
        return name == null ? "Bienvenido a OrganList!" : "Bienvenido " + name + " a OrganList!";
    }

}
