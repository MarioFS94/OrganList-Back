package es.organlist.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @Schema(description = "Identificador del usuario")
    private Integer id;

    @Schema(description = "Nombre del usuario", example = "Mario")
    private String name;

    @Schema(description = "Email del usuario", example = "mariofernandezs1@gmail.com")
    private String email;

    @Schema(description = "Telefono del usuario", example = "666554433")
    private String phone;

    //@JsonIgnore
    @Schema(description = "Contraseña del usuario", hidden = true)
    private String pass;
}
