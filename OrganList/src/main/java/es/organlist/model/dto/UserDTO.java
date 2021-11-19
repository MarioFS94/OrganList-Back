package es.organlist.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User dto.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    /**
     * The Id.
     */
    @Schema(description = "User identifier")
    private Integer id;

    /**
     * The Name.
     */
    @Schema(description = "User name", example = "Mario")
    private String name;

    /**
     * The Email.
     */
    @Schema(description = "User email", example = "mariofernandezs1@gmail.com")
    private String email;

    /**
     * The Phone.
     */
    @Schema(description = "User phone", example = "666554433")
    private String phone;

    /**
     * The Pass.
     */
    @JsonIgnore
    @Schema(description = "User password", hidden = true)
    private String pass;
}
