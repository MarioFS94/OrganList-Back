package es.organlist.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @Schema(description = "User identifier")
    private Long id;

    @Schema(description = "User name")
    private String name;

    @Schema(description = "User email")
    private String email;

    @Schema(description = "User phone")
    private String phone;

    @JsonIgnore
    @Schema(description = "User password", hidden = true)
    private String pass;
}
