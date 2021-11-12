package es.organlist.controller;

import es.organlist.model.dto.CategoryAPIDTO;
import es.organlist.model.dto.ProductAPIDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @GetMapping("categories")
    public List<CategoryAPIDTO> getProductsCategories(
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productService.getProductsCategories(lang);
    }

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
    @GetMapping(value = "categories/{categoryId}")
    public CategoryAPIDTO getProductsCategoryById(
            @Parameter(description = "Identificador de categoria")
            @Pattern(regexp = "[1-9]", message = "La entrada debe ser numérica") @PathVariable Integer categoryId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productService.getProductsCategoryById(categoryId, lang);
    }

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
    @GetMapping(value = "{productId}")
    public ProductAPIDTO getProductById(
            @Parameter(description = "Identificador de producto") @PathVariable Integer productId,
            @Parameter(description = "Idioma usado") @RequestParam(required = false, defaultValue = "es") String lang) {
        return productService.getProductById(productId, lang);
    }

}
