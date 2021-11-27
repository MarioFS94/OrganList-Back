package es.organlist.controller;

import es.organlist.model.dto.ProductDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "ProductController", description = "Gestión de productos")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
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
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))
                            )
                    )
            })
    @DefaultDocumentation
    @GetMapping(value = "load", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadProductsData() {
        return productService.loadData();
    }

}
