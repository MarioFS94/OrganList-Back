package es.organlist.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    @Schema(description = "Identificador de la tienda")
    private Integer id;

    @Schema(description = "Nombre de la tienda")
    private String name;

    @Schema(description = "Tipo de tienda")
    private String type;

}
