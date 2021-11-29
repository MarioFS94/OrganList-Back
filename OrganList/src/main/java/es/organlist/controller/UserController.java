package es.organlist.controller;

import es.organlist.model.dto.UserDTO;
import es.organlist.model.entity.UserEntity;
import es.organlist.service.UserService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "UserController", description = "Gestión de usuarios")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Recuperar un usuario",
            description = "Recuperar un usuario de la BBDD por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(
            @Parameter(description = "Identificador del usuario")
            @PathVariable Integer userId
    ) {
        return userService.getUser(userId);
    }

    @Operation(summary = "Recuperar todos los usuarios",
            description = "Recuperar todos los usuarios de la BBDD",
            responses = {
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

    @Operation(
            summary = "Insertar usuario",
            description = "Insertar usuario en la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @PostMapping
    public ResponseEntity insertUser(
            @Parameter(description = "Objeto de entrada con el nuevo usuario")
            @RequestBody UserDTO userDTO
    ) throws Exception {
        return userService.insertUser(userDTO);
    }

    @Operation(summary = "Borrar un usuario",
            description = "Borrar un usuario de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @DeleteMapping
    public ResponseEntity deleteUser(
            @Parameter(description = "Identificador del usuario a eliminar")
            @RequestParam Integer userId
    ) {
        return userService.deleteUser(userId);
    }

    @Operation(summary = "Modificación de un usuario",
            description = "Modificación de un usuario de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = UserEntity.class))
                    )
            })
    @DefaultDocumentation
    @PutMapping
    public UserEntity updateUser(
            @Parameter(description = "Nuevos valores del usuario a modificar")
            @RequestBody UserDTO userDTO
    ) {
        return userService.updateUser(userDTO);
    }

}
