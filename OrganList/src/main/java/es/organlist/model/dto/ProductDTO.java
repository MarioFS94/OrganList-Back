package es.organlist.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Builder
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Schema(description = "Identificador del producto")
    private Integer id;

    @Schema(description = "Nombre del producto")
    private String name;

    @Schema(description = "Descripcion del producto")
    private String description;

    @Schema(description = "Indicador de si un producto es esencial en una receta, por ejemplo", example = "true")
    @JsonProperty("important")
    private boolean essential;

    @Schema(description = "Categoria del producto", example = "Bebidas sin alcohol")
    private String category;

    @Schema(description = "Precio del producto")
    private BigDecimal price;
}
