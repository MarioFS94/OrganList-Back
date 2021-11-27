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
public class ListDTO {

    @Schema(name = "Identificador de la lista")
    private Integer id;

    @Schema(name = "Nombre de la lista")
    private String name;

    @Schema(name = "Descripción de la lista")
    private String description;

    @Schema(name = "Indicador de si una lista es favorita o recurrente")
    @JsonProperty("favorite")
    private boolean fav;

    @Schema(name = "Usuario creador de la lista")
    private Integer user;
}
