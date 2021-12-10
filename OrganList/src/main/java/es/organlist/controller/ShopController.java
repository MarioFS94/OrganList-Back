package es.organlist.controller;

import es.organlist.model.dto.ShopDTO;
import es.organlist.service.ShopService;
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
@RequestMapping("/shops")
@Tag(name = "ShopController", description = "Gestión de las tiendas disponibles")
@CrossOrigin(origins = "*")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    @Operation(summary = "Recupera todas las tiendas",
            description = "Recupera todas las tiendas de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ShopDTO.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopDTO> getShops() {
        return shopService.getShops();
    }

    @Operation(summary = "Recuperar una tienda",
            description = "Recuperar una tienda de la BBDD por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ShopDTO.class))
                    )
            })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShopDTO getShop(
            @Parameter(description = "Identificador de la tienda")
            @PathVariable Integer shopId
    ) {
        return shopService.getShop(shopId);
    }

    @Operation(summary = "Insertar una tienda",
            description = "Insertar una tienda en la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertShop(@Parameter(description = "Objeto de entrada con la nueva tienda")
                                             @RequestBody ShopDTO shopDTO) {
        return shopService.insertShop(shopDTO);
    }

    @Operation(summary = "Borrar una tienda",
            description = "Borrar una tienda de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @DeleteMapping
    public ResponseEntity deleteShop(
            @Parameter(description = "Identificador de la tienda")
            @RequestParam Integer shopId
    ) {
        return shopService.deleteShop(shopId);
    }
    /*
    @Operation(summary = "Recuperar una tienda",
            description = "Recuperar una tienda de la BBDD por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ListDTO.class))
                    )
            })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping(value = "{listId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListDTO getList(
            @Parameter(description = "Identificador de la lista")
            @PathVariable Integer listId
    ) {
        return listService.getList(listId);
    }




    @Operation(summary = "Modificación de una tienda",
            description = "Modificación de una tienda de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ListEntity.class))
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

  */
}
