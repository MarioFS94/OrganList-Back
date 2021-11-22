package es.organlist.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductShopDTO {
    @Schema(description = "Identificador del registro que relaciona los productos con las tiendas")
    private Integer id;

    @Schema(description =  "producto")
    private BigDecimal product;

    @Schema(description =  "tienda")
    private Integer shop;

}
