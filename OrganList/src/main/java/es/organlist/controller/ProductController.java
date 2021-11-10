package es.organlist.controller;

import es.organlist.model.dto.CategoryAPIDTO;
import es.organlist.service.impl.ProductServiceImpl;
import es.organlist.utils.DefaultDocumentation;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/products/categories")
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
                                    array = @ArraySchema(schema = @Schema(implementation = Object.class))
                            )
                    )
            })
    //Common ApiResponses
    @DefaultDocumentation
    @GetMapping
    public List<CategoryAPIDTO> getProductsCategories() {
        return productService.getProductsCategories();
    }

    @Operation(summary = "Get products endpoint",
            description = "Get products endpoint",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully!",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Object.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(value = "{category}")
    public Object getProductsSubcategoriesByCategory(
            @PathVariable Integer category,
            @RequestParam(required = false, defaultValue = "es") String lang
    ) {
        return productService.getProductsSubcategoriesByCategory(category, lang);
    }

}
