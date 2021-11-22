package es.organlist.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Integer id;

    private String name;

    private String description;

    @JsonProperty("important")
    private boolean essential;

    private String category;

    private BigDecimal price;
}
