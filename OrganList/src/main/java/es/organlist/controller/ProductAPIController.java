package es.organlist.controller;

import es.organlist.model.dto.api.CategoryAPIDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
import es.organlist.service.ProductAPIService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "ProductAPIController", description = "Gestión de productos de la API de Mercadona")
public class ProductAPIController {

    private final ProductAPIService productServiceApi;

    @Autowired
    public ProductAPIController(ProductAPIService productServiceApi) {
        this.productServiceApi = productServiceApi;
    }

    @Operation(summary = "Recuperar las categorias de los productos",
            description = "Recuperar las categorias de los productos de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryAPIDTO.class))
                            )
                    )
            })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping(value = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryAPIDTO> getProductsCategories(
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productServiceApi.getProductsCategories(lang);
    }

    @Operation(summary = "Recuperar las categorias de un producto por id",
            description = "Recuperar las categorias de un producto por id de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    schema = @Schema(implementation = CategoryAPIDTO.class)
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(value = "categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryAPIDTO getProductsCategoryById(
            @Parameter(description = "Identificador de categoria") @PathVariable BigDecimal categoryId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productServiceApi.getProductsCategoryById(categoryId, lang);
    }

    @Operation(summary = "Recuperar un producto por id",
            description = "Recuperar un producto por id de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    schema = @Schema(implementation = ProductAPIDTO.class)
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(value = "{productId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ProductAPIDTO getProductById(
            @Parameter(description = "Identificador de producto") @PathVariable Integer productId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang) {
        return productServiceApi.getProductById(productId, lang);
    }

    @Operation(summary = "Recuperar los productos por el id de la categoria",
            description = "Recuperar los productos por el id de la categoria de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ProductAPIDTO.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(value = "categories/{categoryId}/all-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductAPIDTO> getProductsByCategory(
            @Parameter(description = "Identificador de categoria") @PathVariable BigDecimal categoryId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productServiceApi.getProductsByCategory(categoryId, lang);
    }


    @Operation(summary = "Recuperar todos los productos",
            description = "Recuperar todos los productos de la BBDD",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ProductAPIDTO.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductAPIDTO> getAllProducts(
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productServiceApi.getAllProducts(lang);
    }

}
