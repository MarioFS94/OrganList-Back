package es.organlist.controller;

import es.organlist.model.dto.ListDTO;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.entity.ListEntity;
import es.organlist.service.impl.ListService;
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
@RequestMapping("/lists")
@Tag(name = "ProductListController", description = "Gestión de listas")
@CrossOrigin(origins = "*")
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @Operation(summary = "Recupera todas las listas",
            description = "Recupera todas las listas de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ListDTO.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ListDTO> getLists() {
        return listService.getLists();
    }

    @Operation(summary = "Insertar una lista",
            description = "Insertar una lista de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))
                            )
                    )
            })
    @DefaultDocumentation
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertList(
            @Parameter(description = "Objeto de entrada con la nueva lista")
            @RequestBody ListDTO list
    ) {
        return listService.insertList(list);
    }

    @Operation(summary = "Borrar una lista",
            description = "Borrar una lista de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))
                            )
                    )
            })
    @DefaultDocumentation
    @DeleteMapping
    public ResponseEntity deleteList(
            @Parameter(description = "Identificador de la lista")
            @RequestParam Integer listId
    ) {
        return listService.deleteList(listId);
    }

    @Operation(summary = "Modificación de una lista",
            description = "Modificación de una lista de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ListEntity.class))
                            )
                    )
            })
    @DefaultDocumentation
    @PutMapping
    public ListEntity updateList(
            @Parameter(description = "Nuevos valores de la lista a modificar")
            @RequestBody ListDTO listDTO
    ) {
        return listService.updateList(listDTO);
    }

    @Operation(summary = "Modificación del valor favorito de una lista",
            description = "Modificación del valor favorito de una lista en la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))
                            )
                    )
            })
    @DefaultDocumentation
    @PatchMapping
    public ResponseEntity updateFavoriteList(
            @Parameter(description = "Identificador de la lista a modificar")
            @RequestParam Integer listId
    ) {
        return listService.updateFavoriteList(listId);
    }
}
