package es.organlist.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {
    @Schema(description = "Identificador del registro que relaciona los productos con las listas")
    private BigDecimal id;

    @Schema(description =  "producto")
    private BigDecimal product;

    @Schema(description =  "lista")
    private BigDecimal list;

    @Schema(description =  "unidades")
    private int units;
}
