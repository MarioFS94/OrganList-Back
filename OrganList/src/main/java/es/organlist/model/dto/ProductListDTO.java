package es.organlist.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {
    
    @Schema(description = "Identificador del registro que relaciona los productos con las listas")
    private Integer id;

    @Schema(description =  "producto")
    private Integer product;

    @Schema(description =  "lista")
    private Integer list;

    @Schema(description =  "unidades")
    private int units;
}
