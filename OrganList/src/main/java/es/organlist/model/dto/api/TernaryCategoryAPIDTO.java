package es.organlist.model.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Subcategory dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TernaryCategoryAPIDTO {
    //Ejemplo: categoria terciaria 556 ---> producto 13418
    /**
     * The Id.
     */
    private int id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Order.
     */
    private int order;
    /**
     * The Layout.
     */
    private int layout;
    /**
     * The Products.
     */
    private List<ProductAPIDTO> products;
    /**
     * The Published.
     */
    private boolean published;
    /**
     * The Is extended.
     */
    private boolean is_extended;
}