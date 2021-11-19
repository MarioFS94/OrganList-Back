package es.organlist.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Ternary category apidto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryAPIDTO {
    /**
     * The Id.
     */
    private BigDecimal id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Level.
     */
    private int level;
    //private List<CategoriesLevelAPIDTO> categories;
    private List<ProductCategoryAPIDTO> categories;
    //private Object categories;
    /**
     * The Order.
     */
    private int order;
}
