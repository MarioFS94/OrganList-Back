package es.organlist.controller;

import es.organlist.model.dto.ProductDTO;
import es.organlist.model.entity.ProductEntity;
import es.organlist.service.ProductService;
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
@RequestMapping("/products")
@Tag(name = "ProductController", description = "Gestión de productos")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Recupera todos los productos",
            description = "Recupera todos los productos de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getAllProducts(
//            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productService.getProducts(/*lang*/);
    }

    @Operation(summary = "Cargar datos en BBDD",
            description = "Cargar datos en la BBDD desde la API de Mercadona",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @GetMapping(value = "load", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadProductsData() {
        return productService.loadData();
    }

    @Operation(
            summary = "Insertar producto",
            description = "Insertar producto en la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @PostMapping
    public ResponseEntity insertProduct(
            @Parameter(description = "Objeto de entrada con el nuevo producto")
            @RequestBody ProductDTO productDTO
    ) throws Exception {
        return productService.insertProduct(productDTO);
    }

    @Operation(summary = "Borrar un producto",
            description = "Borrar un producto de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @DeleteMapping
    public ResponseEntity deleteProduct(
            @Parameter(description = "Identificador del producto a eliminar")
            @RequestParam Integer productId
    ) {
        return productService.deleteProduct(productId);
    }

    @Operation(summary = "Modificación de un producto",
            description = "Modificación de un producto de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ProductEntity.class))
                    )
            })
    @DefaultDocumentation
    @PutMapping
    public ProductEntity updateProduct(
            @Parameter(description = "Nuevos valores de la lista a modificar")
            @RequestBody ProductDTO productDTO
    ) {
        return productService.updateProduct(productDTO);
    }

    @Operation(summary = "Modificación del valor de un producto esencial",
            description = "Modificación del valor de un producto esencial (en una receta/lista) en la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            })
    @DefaultDocumentation
    @PatchMapping
    public ResponseEntity updateEssentialProduct(
            @Parameter(description = "Identificador del producto a modificar")
            @RequestParam Integer productId
    ) {
        return productService.updateEssentialProduct(productId);
    }

}
