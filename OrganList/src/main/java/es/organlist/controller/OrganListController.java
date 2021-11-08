package es.organlist.controller;

import es.organlist.model.dto.UserDTO;
import es.organlist.service.UserService;
import es.organlist.utils.DefaultDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "OrganListController", description = "Initial controller")
public class OrganListController {

    private final UserService userService;

    @Autowired
    public OrganListController(UserService userService) {
        this.userService = userService;
    }

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
            @Parameter(description = "Author name") String name
    ) {
        return name == null ? "Welcome to OrganList!" : "Welcome " + name + " to OrganList!";
    }

    @Operation(summary = "Get users endpoint", description = "Get users endpoint", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "You are welcome!",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            )
    })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping("users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

}
