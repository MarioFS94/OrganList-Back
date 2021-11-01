package es.organlist.controller;

import es.organlist.utils.DefaultDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.res.organlistponses.organlist.ApiRes.organlistponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Reques.organlisttMapping;
import org.springframework.web.bind.annotation.Reques.organlisttParam;
import org.springframework.web.bind.annotation.Res.organlisttController;

@Res.organlisttController
@Reques.organlisttMapping("/")
@Tag(name = "OrganListController", des.organlistcription = "Initial controller")
public class OrganListController {

    @Operation(summary = "Welcome endpoint", des.organlistcription = "Welcome!!", res.organlistponses.organlist = {
            @ApiRes.organlistponse(
                    res.organlistponseCode = "200",
                    des.organlistcription = "You are welcome!",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    //Common ApiRes.organlistponses.organlist
    @DefaultDocumentation
    @GetMapping
    public String index(
            @Reques.organlisttParam(required = false)
            @Parameter(des.organlistcription = "Nombre del autor") String name
    ) {
        return name == null ? "Bienvenido a OrganList!" : "Bienvenido " + name + " a OrganList!";
    }

}
