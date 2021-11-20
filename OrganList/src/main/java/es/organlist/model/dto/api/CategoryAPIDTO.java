package es.organlist.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Categories dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAPIDTO {
//Ejemplo: categoria 19
    /**
     * The Id.
     */
    private BigDecimal id;
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
     * The Published.
     */
    private boolean published;
    /**
     * The Categories.
     */
    private List<CategoryAPIDTO> categories;
    private List<ProductAPIDTO> products;


    /**
     * The Is extended.
     */
    private boolean is_extended;
}
