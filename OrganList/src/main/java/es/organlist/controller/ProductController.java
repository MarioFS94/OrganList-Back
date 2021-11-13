package es.organlist.controller;

import es.organlist.model.dto.api.CategoryAPIDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/products")
@Tag(name = "ProductController", description = "Manage product data")
public class ProductController {

    /**
     * The Product service.
     */
    private final ProductServiceImpl productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /**
     * Gets products categories.
     *
     * @param lang the lang
     * @return the products categories
     */
    @Operation(summary = "Get products categories endpoint",
            description = "Get products categories endpoint",
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
        return productService.getProductsCategories(lang);
    }

    /**
     * Gets products category by id.
     *
     * @param categoryId the category id
     * @param lang       the lang
     * @return the products category by id
     */
    @Operation(summary = "Get products category by id endpoint",
            description = "Get products category by id endpoint",
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
            @Parameter(description = "Identificador de categoria") @PathVariable Integer categoryId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productService.getProductsCategoryById(categoryId, lang);
    }

    /**
     * Gets product by id.
     *
     * @param productId the product id
     * @param lang      the lang
     * @return the product by id
     */
    @Operation(summary = "Get products category by id endpoint",
            description = "Get products category by id endpoint",
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
        return productService.getProductById(productId, lang);
    }

    /**
     * Gets products by category.
     *
     * @param categoryId the category id
     * @param lang       the lang
     * @return the products by category
     */
    @Operation(summary = "Get product list by category id endpoint",
            description = "Get product list by category id endpoint",
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
            @Parameter(description = "Identificador de categoria") @PathVariable Integer categoryId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productService.getProductsByCategory(categoryId, lang);
    }


    /**
     * Gets all products.
     *
     * @param lang the lang
     * @return the all products
     */
    @Operation(summary = "Get all products endpoint",
            description = "Get all products endpoint",
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
        return productService.getAllProducts(lang);
    }

}
