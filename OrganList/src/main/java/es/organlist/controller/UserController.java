package es.organlist.controller;

import es.organlist.model.dto.UserDTO;
import es.organlist.service.UserService;
import es.organlist.service.impl.ProductServiceImpl;
import es.organlist.utils.DefaultDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "UserController", description = "Manage user data")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get users endpoint", description = "Get users endpoint", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully!",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))
                    )
            )
    })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

}
